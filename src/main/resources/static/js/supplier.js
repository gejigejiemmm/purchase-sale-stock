$(document).ready(function () {
    $('#submit').on("click", function () {
        alert("666");
        getAll();
    })

    function getAll(){
        $.ajax({
            type: "GET",
            url: "/supplier/getall",
            async : true,
            success:function(data){
                console.log(data.data);
                window.location.reload();
            },
            error:function(){
                alert("请求失败，系统内部问题");
            }
        })
    }
});