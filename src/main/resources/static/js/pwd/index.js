$(document).ready(function() {
    $('#pwd_button').click(function(){
        $('#pwd_error').text('');
        $.ajax({
            url: "/data/account/pwd",
            async: false,
            type: 'put',
            data:$('#pwd_form').serialize(),
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    window.location.href="/home/index";
                }else{
                    $('#pwd_error').text(req.message);
                }
            },
            error: erryFunction
        });
    });
});
function erryFunction() {
    alert("错误!");
}