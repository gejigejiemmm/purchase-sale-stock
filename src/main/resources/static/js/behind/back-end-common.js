var $, element, util;
layui.use([ 'element', 'util', 'jquery'], function() {
    element = layui.element;
    util = layui.util;
    $ = layui.jquery;
});


var isShow = true;

$(window).resize(function() {
    if (parseInt($(this).width()) <= 992) {
        
        $(".layui-nav.layui-layout-left").css('left', "0px");
        
        //设置主体部分left值
        $('.layui-body').css('left', 0 + 'px');
        
        //设置footer部分left值
        $('.layui-footer').css('left', 0 + 'px');
        
        $(".layui-layout-admin .layui-side").css("transform", "translate3d(-220px,0,0)");
        $(".layadmin-body-shade").css("display", "none");
    } else {
        
        $('.layui-layout-admin .layui-logo').width(190).find("span").show().siblings("i").addClass("layui-hide");
        
        //选择出所有的一级菜单名称,进行显示
        $('.layui-side.layui-side-menu .layui-nav-item cite').each(function() {
            $(this).show();
        });
        
        //设置二级菜单显示隐藏
        $('.layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child a').each(function() {
            $(this).show();
        });
        
        //设置顶部导航栏左边部分left值
        $(".layui-nav.layui-layout-left").css('left', "220px");
        
        //设置主体部分left值
        $('.layui-body').css('left', 220 + 'px');
        
        //设置footer部分left值
        $('.layui-footer').css('left', 220 + 'px');
        
        $(".layui-layout-admin .layui-side").css({
            "transform": "translate3d(0px,0,0)",
            "width": "220px"
        });
        $(".layadmin-body-shade").css("display", "none");
    }
    
    isShow = true;
})

$('.layadmin-flexible').click(function() {
    if (parseInt($(window).width()) <= 992) {
        //选择出所有的一级菜单名称,并判断是不是hidden
        $('.layui-side.layui-side-menu .layui-nav-item cite').each(function() {
            if ($(this).is(':hidden')) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
        
        if (isShow) {
            $('.layui-layout-admin .layui-logo').width(190).find("span").show().siblings("i").addClass("layui-hide");
            
            //选择出所有的一级菜单名称,进行显示
            $('.layui-side.layui-side-menu .layui-nav-item cite').each(function() {
                $(this).show();
            });
            //设置顶部导航栏左边部分left值
            $(".layui-nav.layui-layout-left").css('left', "220px");

            //设置主体部分left值
            $('.layui-body').css('left', 220 + 'px');

            //设置footer部分left值
            $('.layui-footer').css('left', 220 + 'px');

            //设置侧边栏显示
            $(".layui-layout-admin .layui-side").css({
                "transform": "translate3d(0px,0,0)",
                "width": "220px"
            });
            //设置二级菜单显示隐藏
            $('.layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child a').each(function() {
                $(this).show();
            });
            $(".layadmin-body-shade").css("display", "block");
            
            isShow = false;
        } else {
            //设置顶部导航栏左边部分left值
            $(".layui-nav.layui-layout-left").css('left', "0px");

            //设置主体部分left值
            $('.layui-body').css('left', 0 + 'px');

            //设置footer部分left值
            $('.layui-footer').css('left', 0 + 'px');

            $(".layui-layout-admin .layui-side").css("transform", "translate3d(-220px,0,0)");
            //设置二级菜单显示隐藏
            $('.layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child a').each(function() {
                $(this).hide();
            });
            $(".layadmin-body-shade").css("display", "none");

            isShow = true;
        }

    } else {

        //选择出所有的一级菜单名称,并判断是不是hidden
        $('.layui-side.layui-side-menu .layui-nav-item cite').each(function() {
            if ($(this).is(':hidden')) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });

        //判断isshow的状态
        if (isShow) {
            //设置侧边菜单宽度
            $('.layui-side.layui-side-menu').width(60);

            //设置侧边顶部logo标识部分宽度
            $('.layui-layout-admin .layui-logo').width(30).find("span").hide().siblings("i").removeClass("layui-hide");

            //设置顶部导航栏左边部分left值
            $(".layui-nav.layui-layout-left").css('left', "60px");

            //设置主体部分left值
            $('.layui-body').css('left', 60 + 'px');

            //设置footer部分left值
            $('.layui-footer').css('left', 60 + 'px');

            //设置二级菜单显示隐藏
            $('.layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child a').each(function() {
                $(this).hide();
            });

            isShow = false;
        } else {
            $('.layui-side.layui-side-menu').width(220);

            $('.layui-layout-admin .layui-logo').width(190).find("span").show().siblings("i").addClass("layui-hide");

            $(".layui-nav.layui-layout-left").css('left', "220px");

            $('.layui-body').css('left', 220 + 'px');

            $('.layui-footer').css('left', 220 + 'px');

            $('.layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child a').each(function() {
                $(this).show();
            });
            isShow = true;
        }
    }


});
 
$(".layadmin-body-shade").click(function(){
    if(!isShow) {
        $(".layui-nav.layui-layout-left").css('left', "0px");
        
        //设置主体部分left值
        $('.layui-body').css('left', 0 + 'px');
        
        //设置footer部分left值
        $('.layui-footer').css('left', 0 + 'px');
        
        $(".layui-layout-admin .layui-side").css("transform", "translate3d(-220px,0,0)");
        $(".layadmin-body-shade").css("display", "none");
        
        isShow = true;
    }
})

$('.layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child a').click(function(){
    if($(".layadmin-body-shade").is(":visible")) {
        if($(this).siblings("dl").hasClass("layui-nav-child")) {
            $(this).siblings("dl").find("a").click(function() {
                
                $(".layui-nav.layui-layout-left").css('left', "0px");
                
                //设置主体部分left值
                $('.layui-body').css('left', 0 + 'px');
                
                //设置footer部分left值
                $('.layui-footer').css('left', 0 + 'px');
                
                $(".layui-layout-admin .layui-side").css("transform", "translate3d(-220px,0,0)");
                $(".layadmin-body-shade").css("display", "none");
                
                isShow = true;
            })
        } else {
            $(".layui-nav.layui-layout-left").css('left', "0px");
            
            //设置主体部分left值
            $('.layui-body').css('left', 0 + 'px');
            
            //设置footer部分left值
            $('.layui-footer').css('left', 0 + 'px');
            
            $(".layui-layout-admin .layui-side").css("transform", "translate3d(-220px,0,0)");
            $(".layadmin-body-shade").css("display", "none");
            
            isShow = true;
        }
    }
})
 