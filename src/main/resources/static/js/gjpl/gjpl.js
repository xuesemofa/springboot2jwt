$(document).ready(function() {
    gjpl_get();
});
function gjpl_get(){
    $.ajax({
        url: path+"/data/gjpl/gjpl/3",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#gjpl_set').find('input[name="zq"]').val(req.data.zq);
            }
        },
        error: erryFunction
    });
}
function gjpl_set(){
    $.ajax({
        url: path+"/data/gjpl/gjpl",
        async: false,
        type: 'post',
        data:$('#gjpl_set').serialize(),
        dataType: 'json',
        timeout: 1000,
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