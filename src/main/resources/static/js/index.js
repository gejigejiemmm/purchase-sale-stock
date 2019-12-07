//一般直接写在一个js文件中
layui.use(['jquery','flow'], function(){
    var flow = layui.flow;
    var $ = layui.jquery;
    
    

    //错误提示
    var error = $('.error');

    //layui的流动加载组件
    flow.load({
      elem: '#content' //指定列表容器
      ,mb:100
      ,isAuto:true
      ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
      var lis = [];

    //开局一个ajax请求获取商品信息
    $.ajax({
        type: "GET",
        url: '/goods/getGoods?p='+page,
        success: function(res){
          let list = res.data.goods.list;

          //假设你的列表返回在data集合中
          layui.each(list, function(index, item){
            
            //注意下面第3行中onclick事件里的js
            lis.push(
              `<div class='card-box'>
              <a onclick="location.assign('/details.html?goodsId=${item.goodsId}')" href='javascript:;' id=${item.goodsId} class='card'>
              <img src='/images/img2.jpeg'  />
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
              console.log(lis);
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




});
