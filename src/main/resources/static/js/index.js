//一般直接写在一个js文件中
layui.use(['laypage','element','jquery'], function(){
    var laypage = layui.laypage;
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

    //分页
    laypage.render({
        elem: 'test1'
        ,count: 8 //数据总数，从服务端得到
        ,jump: function(obj, first){
            console.log(obj);
          //obj包含了当前分页的所有参数，比如：
        //   console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
        //   console.log(obj.limit); //得到每页显示的条数
          console.log(first);
          //首次不执行
          if(!first){
            //do something
          }
        }
      });

    //ajaxTest
    $.ajax({
        type: "GET",
        url: "http://localhost/goods/getGoods?p=2",
        success: function(result){
            loading.hide();
            let lists = result.data.goods.list;
            lists.map(function(item, index){
                var card_box = $("<div class='card-box'></div>");
                var card = $("<a href='javascript:;' class='card'></a>");
                var good_img = $("<img src='http://localhost/images/img1.jpeg'  />");
                var good_name = $("<p  class='good-name'>" + item.goodsChName + "</p>")
                var good_price = $("<p class='good-price'>¥" + item.goodsAvgPrice + "</p>")
                card.append(good_img);
                card.append(good_name);
                card.append(good_price);
                card_box.append(card);
                goods.append(card_box);

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
