//一般直接写在一个js文件中
layui.use(['layer', 'form','element','jquery'], function(){
    var layer = layui.layer
    ,form = layui.form;
    var element = layui.element;
    var $ = layui.jquery;
    var navBtn = $(".nav-btn");
    var nav = $(".layui-nav")

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