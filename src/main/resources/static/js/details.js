//一般直接写在一个js文件中
layui.use(['jquery'], function(){
    var $ = layui.jquery;
    var search = window.location.search.split("");
    var detailImg = document.querySelector(".detailImg");
    var detailPrice = document.querySelector(".banner-right-txt1-price");
    var detailName = document.querySelector(".banner-right-txt1-name");

    //删除search数组的前9位留下goodsId的值
    search.splice(0, 9);

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
            } = res[0];

            //填充商品价格,名称，图片
            detailPrice.innerHTML = '￥' + goodsInitPrice;
            detailName.innerHTML = goodsChName;
            // detailImg.src = goodsImageUrl;

        },
        error:function(res){
            console.log(res);
        }
    })

    // 根据ajax的success返回内容修改页面dom元素的函数
    function render(good){
        const {
            goodsImageUrl,
            goodsInitPrice,
            goodsChName,
        } = good;


    }
})