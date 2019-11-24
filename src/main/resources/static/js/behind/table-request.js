layui.use('table', function () {
    var table = layui.table;

    //第一个实例
    table.render({
        elem: '#table-info'
        , height: 500
        , url: '../json/user.json' //数据接口
        // 分页 curr起始页，groups连续显示的页码，默认每页显示的条数
        , page: true
        , limit: 5
        , cols: [[ //表头
            { field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left' }
            , { field: 'username', title: '用户名', width: 80 }
            , { field: 'sex', title: '性别', width: 80, sort: true }
            , { field: 'city', title: '城市', width: 80 }
            , { field: 'sign', title: '签名', width: 177 }
            , { field: 'experience', title: '积分', width: 80, sort: true }
            , { field: 'score', title: '评分', width: 80, sort: true }
            , { field: 'classify', title: '职业', width: 80 }
            , { field: 'wealth', title: '财富', width: 135, sort: true }
            , { title: '操作', fixed: 'right', width: 220, align: 'center', toolbar: '#barDemo' }
        ]]
    });

    // 执行搜索，表格重载
    $('.search').on('click', function () {
        // 搜索条件
        var send_info = $('.search-info').val();

        table.reload('table-info', {
            url: '../json/reload.json'          // 对应后台的接口
            , page: {
                curr: 1
            }
        });
    });

    function writeCommon(obj, type) {
        var $model = $(".model");
        $(".model").fadeIn();
        $(".mask").fadeIn();

        if (type === 'check') {
            $(".model input").attr('disabled', 'disabled');
        } else if (type === 'edit') {
            $(".model input").removeAttr('disabled');
        }

        $(".model .username").val(obj.username);
        $(".model .city").val(obj.city);
        $(".model .sign").val(obj.sign);
    }

    table.on('tool(test)', function (obj) {
        var data = obj.data;                  //获得当前行数据
        var layEvent = obj.event;             //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if (layEvent === 'check') {           //查看
            writeCommon(data, layEvent);
        } else if (layEvent === 'delete') {   //删除
            layer.confirm('真的删除这条数据吗？', function (index) {
                obj.del();  
                layer.close(index);
                //上边只是从表面上删除了，刷新还会出现，接下来要想后台发送请求
                //也是可以直接向后台发送data，就是对应的这条数据
            });
        } else if (layEvent === 'edit') {     //编辑         
            writeCommon(data, layEvent);
            //编辑完以后，获取编辑后的值，发送请求
        }
    });

});

$(function () {
    //模态框中点击事件
    $(".model .back, .model .cancel").click(function () {
        $(".model").fadeOut();
        $(".mask").fadeOut();
    })
}) 