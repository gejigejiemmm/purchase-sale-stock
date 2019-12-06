//一般直接写在一个js文件中
layui.use(['jquery'], function(){
    var $ = layui.jquery;
    var navBtn = $('.nav-btn');
    var nav = $('.layui-nav');
        //错误提示
        var error = $('.error');
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


  });