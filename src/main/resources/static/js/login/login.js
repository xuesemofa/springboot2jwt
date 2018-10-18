$(document).ready(function() {

    $('#dataInput').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            $('#login_button').click();
        }
    });

    if(self!=top){
    //不是顶层页面
        alert("您的登陆已超时，请重新登陆！");
        top.location.href=path+"/index";
    }

    $('#login_button').click(function(){
        $('#login_button').attr('disabled', true);

        var account = $('input[name="account"]').val();
        if(account == ''){
            alert('账户不能为空');
            $('#login_button').attr('disabled', false);
            return false;
        }
        var password = $('input[name="password"]').val();
        if(password == ''){
            alert('密码不能为空');
            $('#login_button').attr('disabled', false);
            return false;
        }
        $('#login_error').text('');
        $.ajax({
            url: path+"/data/login/login",
            async: false,
            type: 'post',
            data:$('#login_form').serialize(),
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend:function(){
                $('#login2').hide();
                $('#login1').show();
            },
            success: function (req) {
                if(req.success){
                    var timename=setTimeout(function(){
                        window.location.href="/home/index";
                    },5000);
                }else{
                    $('#login_error').text(req.message);
//                    倒计时
                    var timename=setTimeout(function(){
                        $('#login_button').attr('disabled', false);
                    },2000);
                }
            },
            complete:function(){　　//请求完成时的处理
                $('#login_button').attr('disabled', false);
                var timename=setTimeout(function(){
                    $('#login2').show();
                    $('#login1').hide();
                },5000);
            },
            error: erryFunction
        });
        return false;
    });
});

function erryFunction() {
    alert("错误!");
}
