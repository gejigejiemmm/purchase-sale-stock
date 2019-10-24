//一般直接写在一个js文件中
layui.use(['layer', 'form','element','jquery'], function(){
    var layer = layui.layer
    ,form = layui.form;
    var element = layui.element;
    var $ = layui.jquery;
    var navBtn = $('.nav-btn');
    var nav = $('.layui-nav');
    var loading = $('.loading');
    var loadingBtn = $('.loading i');
    var loadingText = $('.loading p');
    var goods = $('.goods');

    //下拉按钮单机事件
    navBtn.click(function(){
        if(nav.hasClass('layui-nav-pc')){
            nav.removeClass('layui-nav-pc');
            nav.addClass('layui-nav-mobile');
            navBtn.removeClass('nav-btn-status2');
            navBtn.addClass('nav-btn-status1');
        }else{
            nav.removeClass('layui-nav-mobile');
            nav.addClass('layui-nav-pc');
            navBtn.removeClass('nav-btn-status1');
            navBtn.addClass('nav-btn-status2');
        }
    })


    //ajaxTest
    $.ajax({
        type: "GET",
        url: "http://localhost/goods/getGoods",
        success: function(result){
            loading.hide();
            let lists = result.data.goods.list;
            lists.map(function(item, index){
                var card = $("<div class='card'></div>");
                var good_img = $("<img src='http://localhost/images/img" + index + ".jpeg'  />");
                var good_name = $("<p  class='good-name'>" + item.goodsChName + "</p>")
                var good_price = $("<p class='good-price'>¥" + item.goodsAvgPrice + "</p>")
                console.log(item);
                card.append(good_img);
                card.append(good_name);
                card.append(good_price);
                goods.append(card);

            })
        },
        error:function(){
            // 获取失败友情提示
            loadingText.html('非常抱歉,获取商品信息失败了,请刷新再试.');
            loadingBtn.removeClass('layui-icon-loading-1 layui-anim-rotate layui-anim layui-anim-loop');
            loadingBtn.addClass(' layui-icon-face-surprised')
        }
     });
});
