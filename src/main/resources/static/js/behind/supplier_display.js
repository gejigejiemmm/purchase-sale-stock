// 记录当前请求地址
let currentUrl = "/supplier/getSuppliersBylimte?";
// 判断请求一共有多少页数据
let dataLen = 0;

// 初始化页面，自动调用一次
tableInfo(currentUrl, 1, 10, 0);
getAllType();

// 表格生成请求函数
function tableInfo(url, page, limit, type) {
    $.get(url + `page=${page}&limte=${limit}`, function (res) {
        let data = res.data.data;

        // 创建分页滚动数，只在第一次初始化时调用，即type==0时, 参数应该是后台返回的总共有多少条数据
        if (type == 0) {
            if (res.data.count != null) {
                createPaginationNum(res.data.count);
            } else {
                $.get('/supplier/getall', function (res) {
                    createPaginationNum(res.data.count);
                })
            }
        }
        //生成表格
        createTable(data);
    })
}

// 发送请求获取所有类型
function getAllType() {
    // 赋值前，需要把原先的值清空
    $(".search-content .selectQuery").html("");
    $.get('/supplier/getInitTypes', function (res) {
        let data = res.data.data;
        // 获取select元素
        let $select = $(".search-content .selectQuery");
        for (let i = 0; i < data.length; i++) {
            let option = $("<option value=" + data[i].supplierType + ">" + data[i].supplierType + "</option>");
            $select.append(option);
        }
    })
}

//  表格生成函数
function createTable(data) {
    // 如果不是第一次初始化表格，就需要在初始化前，先把页面上原来的tbody删除
    $("#table-info .tbody").html("");

    let tbody = $("#table-info .tbody");
    for (let i = 0; i < data.length; i++) {
        let tr = $("<tr></tr>");
        let tds = `<td>` + data[i].supplierId + `</td><td>` + data[i].supplierName + `</td>`
                       + `<td>` + data[i].supplierUnit + `</td><td>` + data[i].supplierNo + `</td><td>`
            + data[i].supplierSpell + `</td><td>` + data[i].supplierType + `</td><td>`
            + data[i].supplierBrand + `</td><td>` + data[i].supplierLocation + `</td><td>`
            + data[i].supplierTelphone + `</td>`;
        let tdOperation = $("<td class='operation'><button class='check'>查看</button><button class='edit'>编辑</button><button class='delete'>删除</button></td>");
        tr.append(tds);
        tr.append(tdOperation);
        tbody.append(tr);
    }
    $("#table-info").append(tbody);
}


// 表格查看按钮点击事件
$("body").on('click', '#table-info .check', function () {
    // 模态框更换标题
    $(".model .model-content .model-top h2").html("查看供应商");

    let parent = $(this).parents("tr");
    addModelInfo(parent);

    $(".model input").attr('disabled', "true");
    $(".model").fadeIn();
    $(".mask").fadeIn();
})

// 表格编辑按钮点击事件
$("body").on('click', '#table-info .edit', function () {
    // 模态框更换标题
    $(".model .model-content .model-top h2").html("编辑供应商");

    let parent = $(this).parents("tr");
    addModelInfo(parent);

    $(".model input").removeAttr('disabled');
    $(".model").fadeIn();
    $(".mask").fadeIn();

    // 对输入框进行监测
    monitorModelInput();

    // 表格编辑确定按钮点击事件
    $(".model .model-opera .ok").click(function (event) {
        if(!event.isPropagationStopped()) {
            let flag = verification();
            if (flag) {
                // js解构赋值
                let [id, name, unit, spell, type, brand, location, phone] = getModelInputInfo();

                // 发送请求
                let parameter = `name=${name}&unit=${unit}&spell=${spell}&type=${type}&brand=${brand}`
                    + `&location=${location}&phone=${phone}&id=${id}`;

                console.log(parameter)
                $.post('/supplier/updateSupplier?' + parameter, function (data) {
                    if (data.code == 100) {
                        // 操作成功以后，需要先把模态框，遮罩层关闭
                        $(".model").fadeOut();
                        $(".mask").fadeOut();
                        //弹出提示操作成功
                        layer.msg(data.data.flag);

                        // 清空模态框
                        clearModelInput();

                        // 获取当前页数，重新发送请求，获得最新数据
                        let spanStr = $(".pagination .numContent span.cur").html();
                        // 发请求，首先获取num-select中select的option选中的值
                        let optionVal = $(".pagination-content .num-select select option:selected").val();
                        // 发送请求
                        tableInfo(currentUrl, spanStr, optionVal, 1);
                    }
                })
            } else {
                layer.msg("输入信息不正确，请检查后重新输入");
            }
        }
        event.stopPropagation();
    })
})

// 获取修改后，模态框内所填信息
function getModelInputInfo() {
    let inputInfoArr = [];
    $(".model .model-item input").each(function (i, item) {
        inputInfoArr.push($(this).val());
    })
    return inputInfoArr;
}

// 表格删除按钮点击事件
$("body").on('click', '#table-info .delete', function () {
    let name = $(this).parents("tr").children("td").eq(1).html();
    let id = $(this).parents("tr").children("td").eq(0).html();
    layer.confirm(`确定要删除供应商${name}的信息吗？`, {
        title: '谨慎',
        btn: ['确认', '取消']
        , btn1: function (index, layero) {
            // 发送请求，进行删除
            $.post(`/supplier/deleteSupplier?id=${id}`, function (data) {
                if (data.code == 100) {
                    layer.msg("删除成功");

                    // 获取当前页数，重新发送请求，获得最新数据
                    let spanStr = $(".pagination .numContent span.cur").html();
                    // 发请求，首先获取num-select中select的option选中的值
                    let optionVal = $(".pagination-content .num-select select option:selected").val();
                    // 发送请求，获取数据
                    tableInfo(currentUrl, spanStr, optionVal, 0);
                    // 发送请求，获取所有类型，防止将某个类型删除空
                    getAllType();
                }
            })
            // 关闭当前弹出层
            layer.close(index)
        }, btn2: function (index, layero) {
            // 取消会自动关闭弹出层
        }
    });
})

// 搜索框点击事件
$("body").on("keydown", ".search-content input", function (e) {
    // 获取自身值
    let searchVal = $(this).val();

    let keyCode = null;
    if (e.keyCode) {           // 非火狐
        keyCode = e.keyCode;
    } else if (e.which) {      // 火狐
        keyCode = e.which;
    }

    if (keyCode == 13) {
        // 发请求
    }
})

// 查询按钮点击事件
$(".search-content .search").click(function () {
    // 获取下拉框选择类型值
    let type = $(this).siblings(".selectQuery").children("option:selected").val();
    currentUrl = `/supplier/selectByType?type=${type}&`;
    tableInfo(currentUrl, 1, 10, 0);
})

// 添加按钮点击事件 (添加新的信息)
$(".search-wrapper .search-content .addSupplier").click(function () {
    // 模态框更换标题
    $(".model .model-content .model-top h2").html("添加供应商");

    $(".model").fadeIn();
    $(".mask").fadeIn();

    // 对输入框进行监测
    monitorModelInput();

    // 提交按钮点击事件
    $(".model .model-opera .addok").click(function (event) {
        if(!event.isPropagationStopped()) {
            let flag = verification();
            if (flag) {
                // js解构赋值
                let [id, name, unit, spell, type, brand, location, phone] = getModelInputInfo();
                // 发送请求
                let parameter = `name=${name}&unit=${unit}&spell=${spell}&type=${type}&brand=${brand}
                &location=${location}&phone=${phone}`;
                $.post("/supplier/addSupplier?" + parameter, function (data) {
                    if (data.code == 100) {
                        // 关闭模态框
                        $(".model").fadeOut();
                        $(".mask").fadeOut();
                        layer.msg("添加信息成功");
                        // 清空模态框
                        clearModelInput();
                        // 发送请求，获取最新数据
                        tableInfo(currentUrl, 1, 10, 0);
                        // 获取最新类型
                        getAllType();
                    }
                })
            } else {
                layer.msg("输入信息不正确，请检查后重新输入");
            }
        }
        event.stopPropagation();
    })
})

// 为模态框添加信息函数
function addModelInfo(parent) {
    let supplierId = parent.children("td").eq(0).html();
    let supplierUnit = parent.children("td").eq(2).html();
    let supplierType = parent.children("td").eq(5).html();
    let supplierBrand = parent.children("td").eq(6).html();
    let supplierName = parent.children("td").eq(1).html();
    let supplierSpell = parent.children("td").eq(4).html();
    let supplierLocation = parent.children("td").eq(7).html();
    let supplierTelphone = parent.children("td").eq(8).html();

    let model = $(".model");

    model.find("input[name='supplierId']").val(supplierId);
    model.find("input[name='supplierUnit']").val(supplierUnit)
    model.find("input[name='supplierType']").val(supplierType)
    model.find("input[name='supplierBrand']").val(supplierBrand)

    model.find("input[name='supplierName']").val(supplierName)
    model.find("input[name='supplierSpell']").val(supplierSpell)
    model.find("input[name='supplierLocation']").val(supplierLocation)
    model.find("input[name='supplierTelphone']").val(supplierTelphone)
}

// 页面刷新重新获取数据事件
$(".nav-header .layui-nav .refresh").click(function () {
    currentUrl = "/supplier/getSuppliersBylimte?";
    // 初始化页面，自动调用一次
    tableInfo(currentUrl, 1, 10, 0);
    getAllType();
})
