function click_ggsjh(){
    $.ajax({
        url: path+"/data/account/sjh",
        async: false,
        type: 'get',
        data:{'sjh':$('#grzl_sjh').val()},
        dataType: 'json',
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function erryFunction() {
    alert("错误!");
}