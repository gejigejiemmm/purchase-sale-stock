//一般直接写在一个js文件中
layui.use(['laypage','element','jquery','flow'], function(){
    var laypage = layui.laypage;
    var element = layui.element;
    var flow = layui.flow;
    var $ = layui.jquery;
    var navBtn = $('.nav-btn');
    var nav = $('.layui-nav');
    //错误提示
    var error = $('.error');

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

    //flow
    flow.load({
      elem: '#content' //指定列表容器
      ,mb:150
      ,isAuto:true
      ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
        var lis = [];

    $.ajax({
        type: "GET",
        url: 'http://localhost/goods/getGoods?p='+page,
        success: function(res){
          let list = res.data.goods.list;
                    //假设你的列表返回在data集合中
                    layui.each(res.data.goods.list, function(index, item){
                      lis.push(
                        `<div class='card-box'>
                        <a href='javascript:;' class='card'>
                        <img src='http://localhost/images/img2.jpeg'  />
                        <p  class='good-name'>
                              <span>${item.goodsChName}</span>
                              <span>${item.goodsEnName}</span>
                        </p>
                        <p  class='good-price'>
                              <span>¥${item.goodsPrice}</span>
                              <span><button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal">查看详情</button></span>
                        </p>
                        </a>
                        </div>`);
                    }); 

                    //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                    //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                    next(lis.join(''), page < res.data.goods.pages);    
        },
        error:function(){
            // 获取失败友情提示
            $('.layui-flow-more').hide();
            error.show();
        }
     });
      }
    });

    //ajax
    // $.ajax({
    //     type: "GET",
    //     url: "http://localhost/goods/getGoods",
    //     success: function(result){
    //         loading.hide();
    //         let list = result.data.goods.list;
    //         list.map(function(item, index){
    //             var card_box = $("<div class='card-box'></div>");
    //             var card = $("<a href='javascript:;' class='card'></a>");
    //             var good_img = $("<img src='http://localhost/images/img2.jpeg'  />");
    //             var good_name = $("<p  class='good-name'>" + item.goodsChName + "</p>")
    //             var good_price = $("<p class='good-price'>¥" + item.goodsPrice + "</p>")
    //             card.append(good_img);
    //             card.append(good_name);
    //             card.append(good_price);
    //             card_box.append(card);
    //             goods.append(card_box);
    //         })
    //     },
    //     error:function(){
    //         // 获取失败友情提示
    //         loadingText.html('非常抱歉,获取商品信息失败了,请刷新再试.');
    //         loadingBtn.removeClass('layui-icon-loading-1 layui-anim-rotate layui-anim layui-anim-loop');
    //         loadingBtn.addClass(' layui-icon-face-surprised')
    //     }
    //  });
});
