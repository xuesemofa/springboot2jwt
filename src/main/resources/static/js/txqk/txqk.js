$(document).ready(function() {
    get_txqk_form();
});
//获取值
function get_txqk_form(){
    $.ajax({
        url: path+"/data/txqk/txqk",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#txqk_form').find('input[name="txsj"]').val(req.data.txsj);
                $('#txqk_form').find('input[name="qzyrghsj"]').val(req.data.qzyrghsj);
            }else{
                $('#txqk_form').find('input[name="txsj"]').val(0);
                $('#txqk_form').find('input[name="qzyrghsj"]').val(0);
            }
        },
        error: erryFunction
    });
}
//更改时间
function txqk_form(){
    var txsj = $('#txqk_form').find('input[name="txsj"]').val();
    var qzyrghsj = $('#txqk_form').find('input[name="qzyrghsj"]').val();
    if(txsj == ''){
        alert('提醒时间不能为空');
        return false;
    }
    if(qzyrghsj == ''){
        alert('强制解除关系时间不能为空');
        return false;
    }
    $.ajax({
        url: path+"/data/txqk/txqk",
        async: false,
        type: 'post',
        data: $('#txqk_form').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            alert(req.message);
        },
        error: erryFunction
    });
}