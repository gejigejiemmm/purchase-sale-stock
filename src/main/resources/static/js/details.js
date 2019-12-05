//一般直接写在一个js文件中
layui.use(['jquery'], function(){
    var $ = layui.jquery;
    var search = window.location.search.split("");
    var detailImg = document.querySelector(".detailImg");
    var detailPrice = document.querySelector(".banner-right-txt1-price");
    var detailName = document.querySelector(".banner-right-txt1-name");
    var infoTableTd = document.querySelectorAll(".info-table-td");    
    search.splice(0, 9);
    search = search.join("");

    //调用规定商品数量input输入格式检查函数
    inputHandle();

    //根据goodsId获取商品详情
    $.ajax({
        type:"POST",
        url:"/goods/getGood",
        data:`goodsId=${search}`,
        success:function(res){
            const {
                goodsImageUrl,
                goodsInitPrice,
                goodsChName,
                goodsTrivialName,
                goodsEnName,
                goodsMolecularFormula,
                goodsForm,
                goodsUnit,
                goodsCondition,
            } = res[0];

            //新城参数数组，该数组元素顺序需要和html中的InfoTable的顺序一致
            const infoArr = [
                goodsEnName,
                goodsTrivialName,
                goodsMolecularFormula,
                goodsForm,
                goodsUnit,
                goodsCondition
            ]
            //填充商品价格,名称，图片
            detailPrice.innerHTML = '￥' + goodsInitPrice;
            detailName.innerHTML = goodsChName;
            // detailImg.src = goodsImageUrl;

            //填充商品详细参数：英文名、俗名、化学方程式、形态、单位、存储条件
            // infoArr.forEach((item, index) => {
            //     let tr = document.createElement("tr");
            //     let td1 = document.createElement("td1");
            //     let td2 = document.createElement("td2");
            // })

            infoTableTd.forEach((item, index) => {
                item.innerHTML = infoArr[index];
            })

        },
        error:function(res){
            console.log(res);
        }
    })

    //规定商品数量输入的input的值只能为整数
    function inputHandle(){
        let subBtn = document.querySelector(".detail-sub-btn");
        let addBtn = document.querySelector(".detail-add-btn");
        let goodCountInput = document.querySelector(".goodCountInput");
        let inputValue = goodCountInput.value;
        goodCountInput.onkeyup = function(event){
            if(isNaN(event.key)){
                //输入不是数字
                goodCountInput.value = inputValue;
            }else{
                inputValue = goodCountInput.value;
            }
        }
        subBtn.onclick = function(){
            goodCountInput.value > 1 ? goodCountInput.value = goodCountInput.value - 1 : null;
        }
        addBtn.onclick = function(){
            goodCountInput.value = 1 * goodCountInput.value + 1
        }
    }
})