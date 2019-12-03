$(function () {
    // 记录当前请求地址
    let currentUrl = "/supplier/getSuppliersBylimte?";
    // 初始化页面，自动调用一次
    tableInfo(currentUrl,1, 10, 0);
    getAllType();

    // 判断请求一共有多少页数据
    var dataLen = 0;

    // 发送请求获取所有类型
    function getAllType() {
        // 赋值前，需要把原先的值清空
        $(".search-content .selectQuery").html("");
        $.get('/supplier/getInitTypes', function(res) {
            var data = res.data.data;
            // 获取select元素
            var $select = $(".search-content .selectQuery");
            for(var i = 0; i < data.length; i++) {
                var option = $("<option value=" + data[i].supplierType + ">" + data[i].supplierType + "</option>");
                $select.append(option);
            }
        })
    }


    // 表格生成请求函数
    function tableInfo(url, page, limit, type) {
        $.get(url + `page=${page}&limte=${limit}`, function (res) {
            var data = res.data.data;

            // 创建分页滚动数，只在第一次初始化时调用，即type==0时, 参数应该是后台返回的总共有多少条数据
            if(type == 0) {
                if(res.data.count != null){
                    createPaginationNum(res.data.count);
                }else{
                    $.get('/supplier/getall', function(res){
                        createPaginationNum(res.data.count);
                    })
                }
            }
            //生成表格
            createTable(data);
        })
    }

    //  表格生成函数
    function createTable(data) {
        // 如果不是第一次初始化表格，就需要在初始化前，先把页面上原来的tbody删除
        $("#table-info .tbody").html("");

        var tbody = $("#table-info .tbody");
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            var tds = `<td>` + data[i].supplierId + `</td><td>` + data[i].supplierName + `</td>
                        <td>` + data[i].supplierUnit + `</td><td>` + data[i].supplierNo + `</td><td>`
                + data[i].supplierSpell + `</td><td>` + data[i].supplierType + `</td><td>`
                + data[i].supplierBrand + `</td><td>` + data[i].supplierLocation + `</td><td>`
                + data[i].supplierTelphone + `</td>`;
            var tdOperation = $("<td class='operation'><button class='check'>查看</button><button class='edit'>编辑</button><button class='delete'>删除</button></td>");
            tr.append(tds);
            tr.append(tdOperation);
            tbody.append(tr);
        }
        $("#table-info").append(tbody);
    }

    // 创建分页滚动数函数
    function createPaginationNum(count) {
        // 创建时，先把之前填写过的清空掉
        $(".pagination .num-carousel .numContent").html("");
        // 先把页面上的共多少条填上
        $(".pagination .total-num span").html(count);
        // 获取每页显示多少条，进行对比
        var size = $(".pagination-content .num-select select option:selected").val();
        var spanStr = "";
        var flag = false;
        if (parseInt(count) > parseInt(size)) {
            // 一共多少页
            dataLen = Math.ceil(count / size);
            flag = true;

            if (dataLen <= 3) {
                for (var i = 0; i < dataLen; i++) {
                    if (i == 0) {
                        spanStr += "<span class='cur'>" + (i + 1) + "</span>";
                    } else {
                        spanStr += "<span>" + (i + 1) + "</span>";
                    }
                }
            } else if (dataLen >= 4) {
                for (var i = 0; i < 3; i++) {
                    if (i == 0) {
                        spanStr += "<span class='cur'>" + (i + 1) + "</span>";
                    } else {
                        spanStr += "<span>" + (i + 1) + "</span>";
                    }
                }
                spanStr += "<span class='noClick'>...</span>";
                // 为了便于在后面点击jump跳转时，更改显示使用
                spanStr += "<span class='curVal'>4</span>";
            }

        } else if (count <= size || count > 0) {
            dataLen = 1;
            spanStr = "<span class='cur'>1</span>";
        } else {
            $(".pagination").css('display', 'none');
        }

        if (flag) { // 如果数据大于当前每页显示条数的话，也就是可以进行分页
            $(".pagination-content .num-carousel").css('width', 45 + (dataLen - 1) * 30 + 'px')
        } else {
            $(".pagination-content .num-carousel").css('width', '56px')
        }

        //生成完以后，添加到页面上
        $(".pagination-content .numContent").append(spanStr);
    }

    // 页数span点击事件
    $("body").on('click', '.pagination-content .numContent span:not(".noClick")', function () {
        // 改变当前页数span显示方式
        $(this).addClass('cur').siblings().removeClass('cur');

        // 获取当前值
        var spanVal = $(this).html();

        // 发请求，首先获取num-select中select的option选中的值
        var optionVal = $(".pagination-content .num-select select option:selected").val();
        console.log(currentUrl);
        // 调用函数，发送请求
        tableInfo(currentUrl, spanVal, optionVal, 1);

    })

    // 页面左箭头点击函数
    $("body").on('click', '.pagination-content .num-carousel .leftArrow', function () {
        // 点击前需要知道当前cur在哪个span身上 使用过滤器，过滤掉没有cur的span，只返回有的
        var spanIdx;
        $(".numContent span").filter(function (idx, item) {
            if ($(this).hasClass('cur')) {
                // 如果当前为第一个，左箭头就不能点击
                if (idx == 0) return false;

                // 发请求，首先获取num-select中select的option选中的值
                var optionVal = $(".pagination-content .num-select select option:selected").val();

                // 一旦找到，就先把原来的删除
                $(this).removeClass('cur');
                if (idx == 1) {
                    // 调用函数，发送请求
                    tableInfo(currentUrl, idx, optionVal, 1);
                    $(this).prev().addClass('cur');
                } else if (idx == 2) {
                    // 调用函数，发送请求
                    tableInfo(currentUrl, idx, optionVal, 1);
                    $(this).prev().addClass('cur');
                } else if (idx == 4) {
                    var num = parseInt($(".numContent span").eq(4).html());
                    // 调用函数，发送请求
                    tableInfo(currentUrl, num - 1, optionVal, 1);
                    if(num == 4) {
                        $(this).prev().prev().addClass('cur');
                    } else {
                        $(this).addClass('cur');
                        $(".numContent span").eq(4).html(num - 1)
                    }
                }
            }
        })
    })

    // 页面右箭头点击函数
    $("body").on('click', '.pagination-content .num-carousel .rightArrow', function () {
        // 点击前需要知道当前cur在哪个span身上 使用过滤器，过滤掉没有cur的span，只返回有的
        var spanStr = $(".numContent span");
        var idx;
        for (var i = 0; i < spanStr.length; i++) {
            if ($(spanStr[i]).hasClass('cur')) {
                var idx = i;
                break;
            }
        }
        // 如果当前为最后一页，右箭头就不能点击
        if ($(spanStr[idx]).html() == dataLen) return false;

        // 发请求，首先获取num-select中select的option选中的值
        var optionVal = $(".pagination-content .num-select select option:selected").val();

        // 一旦找到，就先把原来的删除
        $(spanStr[idx]).removeClass('cur');
        switch (idx) {
            case 0:
            case 1:
                // 调用函数，发送请求
                tableInfo(currentUrl, idx + 2, optionVal, 1);
                $(".numContent span").eq(idx + 1).addClass('cur');
                break;
            case 2:
                // 调用函数，发送请求
                tableInfo(currentUrl, idx + 2, optionVal, 1);
                $(".numContent span").eq(4).addClass('cur');
                break;
            case 4:
                $(".numContent span").eq(4).addClass('cur');
                var num = parseInt($(".numContent span").eq(4).html());
                // 调用函数，发送请求
                tableInfo(currentUrl, num + 1, optionVal, 1);
                $(".numContent span").eq(4).html(num + 1)
                break;
            default:
                break;
        }
    })

    // 跳转页面数键盘按下事件
    $("body").on('keydown', '.pagination-content .jump input', function (e) {
        // 获取输入值
        var jumpVal = $(this).val();

        var keyCode = null;
        if (e.keyCode) {           // 非火狐
            keyCode = e.keyCode;
        } else if (e.which) {      // 火狐
            keyCode = e.which;
        }

        if (keyCode == 13) {      // 表明按下enter键
            if (jumpVal && jumpVal <= dataLen) {
                // 发请求，首先获取num-select中select的option选中的值
                var optionVal = $(".pagination-content .num-select select option:selected").val();
                // 调用函数，发送请求
                tableInfo(currentUrl, jumpVal, optionVal, 1);
                // 改变当前页数span显示方式
                var curVal = $(".pagination-content .numContent span.curVal");
                // 判断当前跳转值是否比curVal值大
                if (parseInt(jumpVal) >= parseInt(curVal.html())) {
                    curVal.html(jumpVal);

                    // 让cur类从原来的的兄弟元素span去掉，然后加到自己身上
                    curVal.addClass('cur').siblings("span").removeClass('cur');
                } else if (parseInt(jumpVal) <= 3) {
                    $(".pagination-content .numContent span").removeClass('cur').eq(jumpVal - 1).addClass('cur');
                }
            } else {
                layer.msg('输入值范围错误');
                return false;
            }
        }
    });

    // 跳转页面数确定按钮点击事件
    $("body").on('click', '.pagination-content .jump button', function () {
        var jumpVal = $(this).siblings("input").val();
        if (jumpVal && jumpVal <= dataLen) {
            // 发请求，首先获取num-select中select的option选中的值
            var optionVal = $(".pagination-content .num-select select option:selected").val();
            // 调用函数，发送请求
            tableInfo(currentUrl, jumpVal, optionVal, 1);

            // 改变当前页数span显示方式
            var curVal = $(".pagination-content .numContent span.curVal");
            // 判断当前跳转值是否比curVal值大
            if (parseInt(jumpVal) >= parseInt(curVal.html())) {
                curVal.html(jumpVal);

                // 让cur类从原来的的兄弟元素span去掉，然后加到自己身上
                curVal.addClass('cur').siblings("span").removeClass('cur');
            } else if (parseInt(jumpVal) <= 3) {
                $(".pagination-content .numContent span").removeClass('cur').eq(jumpVal - 1).addClass('cur');
            }
        } else {
            layer.msg('输入值范围错误');
            return false;
        }
    })

    // 页面条数显示选择变化事件
    $("body").on('change', '.pagination-content .num-select select', function () {
        // 获取自身值
        var optionVal = $(this).val();
        // 获取当前位于第几页
        var spanStr = $(".pagination-content .num-carousel .numContent span.cur").html();
        // 数据条数发生变化，所以carousel中的span可点击数量也应该有所改变
        // 获取数据总条数
        var totalNum = $(".pagination .total-num span").html();
        createPaginationNum(totalNum);
        /* 发送请求之前，还需要在判断，当前所在的这一页，与你选择的页面每页显示数据的乘积是否会超过数据量
           情况：如果目前是一页5条，总数据14条，但是你想点一页20，或者30，那么就需要显示第一页
        */
        var index = Math.ceil(parseInt(totalNum) / parseInt(optionVal));
        if(index < parseInt(spanStr)) {
            // 调用函数，发送请求
            tableInfo(currentUrl, 1, optionVal, 1);
        } else {
            // 如果当前不是在第一页的话，那么就需要改变cur的位置，即如果当前是第二页，那么就需要第二个span带cur
            var idx = parseInt(spanStr);
            $(".pagination .numContent span").removeClass("cur");
            if(idx >= 4) {
                $(".pagination .numContent span.curVal").addClass("cur");
            } else {
                $(".pagination .numContent span").eq(idx - 1).addClass("cur");
            }
            // 调用函数，发送请求
            tableInfo(currentUrl, spanStr, optionVal, 1);
        }
    })

    // 表格查看按钮点击事件
    $("body").on('click', '#table-info .check', function () {
        var parent = $(this).parents("tr");
        addModelInfo(parent);

        $(".model input").attr('disabled', "true");
        $(".model").fadeIn();
        $(".mask").fadeIn();
    })

    // 表格编辑按钮点击事件
    $("body").on('click', '#table-info .edit', function () {
        var parent = $(this).parents("tr");
        addModelInfo(parent);

        $(".model input").removeAttr('disabled');
        $(".model").fadeIn();
        $(".mask").fadeIn();

        // 对输入框进行监测
        monitorModelInput();

        // 表格编辑确定按钮点击事件
        $(".model .model-opera .ok").click(function() {
            var flag = verification();
            if(flag) {
                // js解构赋值
                var [id, name, unit, spell, type, brand, location, phone] = getModelInputInfo();

                // 发送请求
                var parameter = `name=${name}&unit=${unit}&spell=${spell}&type=${type}&brand=${brand}
                &location=${location}&phone=${phone}&id=${id}`;
                $.post('/supplier/updateSupplier?' + parameter, function(data) {
                    if(data.code == 100) {
                        // 操作成功以后，需要先把模态框，遮罩层关闭
                        $(".model").fadeOut();
                        $(".mask").fadeOut();
                        //弹出提示操作成功
                        layer.msg(data.data.flag);

                        // 获取当前页数，重新发送请求，获得最新数据
                        var spanStr = $(".pagination .numContent span.cur").html();
                        // 发请求，首先获取num-select中select的option选中的值
                        var optionVal = $(".pagination-content .num-select select option:selected").val();
                        // 发送请求
                        tableInfo(currentUrl, spanStr, optionVal, 1);
                    }
                })
            } else {
               layer.msg("输入信息不正确，请检查后重新输入");
            }
        })
    })

    // 对模态框中的输入框进行监测
    function monitorModelInput() {
        $(".model .model-item input").each(function(i, item) {
            $(this).blur(function() {
                if($(this).val() == "") {
                    $(this).addClass("err");
                    layer.msg("选项值不能置空");
                } else {
                    $(this).removeClass("err");
                }
            })
        })
    }

    // 获取修改后，模态框内所填信息
    function getModelInputInfo() {
        var inputInfoArr = [];
        $(".model .model-item input").each(function(i, item) {
            inputInfoArr.push($(this).val());
        })
        return inputInfoArr;
    }

    // 修改信息后，校验函数
    function verification() {
        var flag = true;
        $(".model .model-item input").each(function(i, item) {
            if($(this).hasClass("err")) {
                flag = false;
            }
        })
        return flag;
    }

    // 表格删除按钮点击事件
    $("body").on('click', '#table-info .delete', function () {
        var name = $(this).parents("tr").children("td").eq(1).html();
        var id = $(this).parents("tr").children("td").eq(0).html();
        layer.confirm(`确定要删除供应商${name}的信息吗？`, {
            title: '谨慎',
            btn: ['确认', '取消']
            , btn1: function (index, layero) {
                // 发送请求，进行删除
                $.post(`/supplier/deleteSupplier?id=${id}`, function (data) {
                    if (data.code == 100) {
                        layer.msg("删除成功");

                        // 获取当前页数，重新发送请求，获得最新数据
                        var spanStr = $(".pagination .numContent span.cur").html();
                        // 发请求，首先获取num-select中select的option选中的值
                        var optionVal = $(".pagination-content .num-select select option:selected").val();
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
        var searchVal = $(this).val();

        var keyCode = null;
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
    $(".search-content .search").click(function() {
        // 获取下拉框选择类型值
        var type = $(this).siblings(".selectQuery").children("option:selected").val();
        currentUrl = `/supplier/selectByType?type=${type}&`;
        tableInfo(currentUrl, 1, 10, 0);
    })

    // 添加按钮点击事件 (添加新的信息)
    $(".search-wrapper .search-content .addSupplier").click(function() {
        $(".model").fadeIn();
        $(".mask").fadeIn();

        // 对输入框进行监测
        monitorModelInput();

        // 提交按钮点击事件
        $(".model .model-opera .addok").click(function() {
            var flag = verification();
            if(flag) {
                // js解构赋值
                var [id, name, unit, spell, type, brand, location, phone] = getModelInputInfo();

                // 发送请求
                var parameter = `name=${name}&unit=${unit}&spell=${spell}&type=${type}&brand=${brand}
                &location=${location}&phone=${phone}`;
                $.post("/supplier/addSupplier?" + parameter, function(data) {
                    if(data.code == 100) {
                        // 关闭模态框
                        $(".model").fadeOut();
                        $(".mask").fadeOut();
                        layer.msg("添加信息成功");

                        // 发送请求，获取最新数据
                        tableInfo(currentUrl, 1, 10, 0);
                        // 获取最新类型
                        getAllType();
                    }
                })
            } else {
                layer.msg("输入信息不正确，请检查后重新输入");
            }
        })
    })

    // 为模态框添加信息函数
    function addModelInfo(parent) {
        var supplierId = parent.children("td").eq(0).html();
        var supplierUnit = parent.children("td").eq(2).html();
        var supplierType = parent.children("td").eq(5).html();
        var supplierBrand = parent.children("td").eq(6).html();
        var supplierName = parent.children("td").eq(1).html();
        var supplierSpell = parent.children("td").eq(4).html();
        var supplierLocation = parent.children("td").eq(7).html();
        var supplierTelphone = parent.children("td").eq(8).html();

        var model = $(".model");

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
    $(".nav-header .layui-nav .refresh").click(function() {
        currentUrl = "/supplier/getSuppliersBylimte?";
        // 初始化页面，自动调用一次
        tableInfo(currentUrl,1, 10, 0);
        getAllType();
    })

    //模态框中点击事件
    $(".model .back, .model .cancel").click(function () {
        $(".model").fadeOut();
        $(".mask").fadeOut();

        //要把原先的输入框err类去掉
        $(".model .model-item input").each(function(i, item) {
            $(this).removeClass("err");
        })
    })
}) 