// 创建分页滚动数函数
function createPaginationNum(count) {
    // 创建时，先把之前填写过的清空掉
    $(".pagination .num-carousel .numContent").html("");
    // 先把页面上的共多少条填上
    $(".pagination .total-num span").html(count);
    // 获取每页显示多少条，进行对比
    let size = $(".pagination-content .num-select select option:selected").val();
    let spanStr = "";
    let flag = false;
    if (parseInt(count) > parseInt(size)) {
        // 一共多少页
        dataLen = Math.ceil(count / size);
        flag = true;

        if (dataLen <= 3) {
            for (let i = 0; i < dataLen; i++) {
                if (i == 0) {
                    spanStr += "<span class='cur'>" + (i + 1) + "</span>";
                } else {
                    spanStr += "<span>" + (i + 1) + "</span>";
                }
            }
        } else if (dataLen >= 4) {
            for (let i = 0; i < 3; i++) {
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
        if(dataLen >= 4) {
            $(".pagination-content .num-carousel").css('width', 40 + 4 * 30 + 'px')
        } else {
            $(".pagination-content .num-carousel").css('width', 50 + (dataLen - 1) * 30 + 'px')
        }
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
    let spanVal = $(this).html();

    // 发请求，首先获取num-select中select的option选中的值
    let optionVal = $(".pagination-content .num-select select option:selected").val();
    console.log(currentUrl);
    // 调用函数，发送请求
    tableInfo(currentUrl, spanVal, optionVal, 1);

})

// 页面左箭头点击函数
$("body").on('click', '.pagination-content .num-carousel .leftArrow', function () {
    // 点击前需要知道当前cur在哪个span身上 使用过滤器，过滤掉没有cur的span，只返回有的
    let spanIdx;
    $(".numContent span").filter(function (idx, item) {
        if ($(this).hasClass('cur')) {
            // 如果当前为第一个，左箭头就不能点击
            if (idx == 0) return false;

            // 发请求，首先获取num-select中select的option选中的值
            let optionVal = $(".pagination-content .num-select select option:selected").val();

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
                let num = parseInt($(".numContent span").eq(4).html());
                // 调用函数，发送请求
                tableInfo(currentUrl, num - 1, optionVal, 1);
                if (num == 4) {
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
    let spanStr = $(".numContent span");
    let idx;
    for (let i = 0; i < spanStr.length; i++) {
        if ($(spanStr[i]).hasClass('cur')) {
            idx = i;
            break;
        }
    }
    // 如果当前为最后一页，右箭头就不能点击
    if ($(spanStr[idx]).html() == dataLen) return false;

    // 发请求，首先获取num-select中select的option选中的值
    let optionVal = $(".pagination-content .num-select select option:selected").val();

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
            let num = parseInt($(".numContent span").eq(4).html());
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
    let jumpVal = $(this).val();

    let keyCode = null;
    if (e.keyCode) {           // 非火狐
        keyCode = e.keyCode;
    } else if (e.which) {      // 火狐
        keyCode = e.which;
    }

    if (keyCode == 13) {      // 表明按下enter键
        if (jumpVal && jumpVal <= dataLen) {
            // 发请求，首先获取num-select中select的option选中的值
            let optionVal = $(".pagination-content .num-select select option:selected").val();
            // 调用函数，发送请求
            tableInfo(currentUrl, jumpVal, optionVal, 1);
            // 改变当前页数span显示方式
            let curVal = $(".pagination-content .numContent span.curVal");
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
    let jumpVal = $(this).siblings("input").val();
    if (jumpVal && jumpVal <= dataLen) {
        // 发请求，首先获取num-select中select的option选中的值
        let optionVal = $(".pagination-content .num-select select option:selected").val();
        // 调用函数，发送请求
        tableInfo(currentUrl, jumpVal, optionVal, 1);

        // 改变当前页数span显示方式
        let curVal = $(".pagination-content .numContent span.curVal");
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
    let optionVal = $(this).val();
    // 获取当前位于第几页
    let spanStr = $(".pagination-content .num-carousel .numContent span.cur").html();
    // 数据条数发生变化，所以carousel中的span可点击数量也应该有所改变
    // 获取数据总条数
    let totalNum = $(".pagination .total-num span").html();
    createPaginationNum(totalNum);
    /* 发送请求之前，还需要在判断，当前所在的这一页，与你选择的页面每页显示数据的乘积是否会超过数据量
       情况：如果目前是一页5条，总数据14条，但是你想点一页20，或者30，那么就需要显示第一页
    */
    let index = Math.ceil(parseInt(totalNum) / parseInt(optionVal));
    if (index < parseInt(spanStr)) {
        // 调用函数，发送请求
        tableInfo(currentUrl, 1, optionVal, 1);
    } else {
        // 如果当前不是在第一页的话，那么就需要改变cur的位置，即如果当前是第二页，那么就需要第二个span带cur
        let idx = parseInt(spanStr);
        $(".pagination .numContent span").removeClass("cur");
        if (idx >= 4) {
            $(".pagination .numContent span.curVal").addClass("cur");
        } else {
            $(".pagination .numContent span").eq(idx - 1).addClass("cur");
        }
        // 调用函数，发送请求
        tableInfo(currentUrl, spanStr, optionVal, 1);
    }
})

/* 常规操作 */
//模态框中点击事件
$(".model .back, .model .cancel").click(function () {
    $(".model").fadeOut();
    $(".mask").fadeOut();

    //要把原先的输入框err类去掉
    $(".model .model-item input").each(function (i, item) {
        $(this).removeClass("err");
    })

    // 清空模态框
    clearModelInput();
})

// 对模态框中的输入框进行监测
function monitorModelInput() {
    $(".model .model-item input").each(function (i, item) {
        $(this).blur(function () {
            if ($(this).val() == "") {
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
    let inputInfoArr = [];
    $(".model .model-item input").each(function (i, item) {
        inputInfoArr.push($(this).val());
    })
    return inputInfoArr;
}

// 修改信息后，校验函数
function verification() {
    let flag = true;
    $(".model .model-item input").each(function (i, item) {
        if ($(this).hasClass("err")) {
            flag = false;
        }
    })
    return flag;
}

// 清空模态框信息函数
function clearModelInput() {
    $(".model .model-item input").each(function (i, item) {
        $(this).val("");
    })
}
