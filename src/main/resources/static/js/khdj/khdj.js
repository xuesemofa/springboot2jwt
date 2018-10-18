$(document).ready(function() {
    khdj_page_query();
});
function khdj_page_query(){
    $('#khdj_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/khdj/khdj",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>'+e.mc+'</td>'
                    +'<td>'
                    +'<input class="btn btn-primary btn-xs" type="button" onclick="khdj_page_update(\''+e.uuid+'\')" value="修改"/>'
                    +'</td></tr>';
                    $('#khdj_page_table_data').append(h);
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function khdj_page_add(){
    $('#khdj_page_add2').click();
    $('#khdj_page_add211').show();
    $('#khdj_page_update212').hide();
}
function khdj_page_add21(){
    var mc = $('#khdj_model_a3').val();
    $.ajax({
        url: path+"/data/khdj/khdj",
        async: false,
        type: 'post',
        data:{"mc":mc},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#khdj_page_add2').click();
                $('#khdj_model_a3').val('');
                khdj_page_query();
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
function khdj_page_update(obj){
    $('#khdj_page_add2').click();
    $('#khdj_page_add211').hide();
    $('#khdj_page_update212').show();
    $('#khdj_model_a2').val(obj);
}
function khdj_page_update2(){
    var mc = $('#khdj_model_a3').val();
    var id = $('#khdj_model_a2').val();
    $.ajax({
        url: path+"/data/khdj/khdj",
        async: false,
        type: 'put',
        data:{"mc":mc,"uuid":id},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#khdj_page_add2').click();
                $('#khdj_model_a3').val('');
                $('#khdj_model_a3').val('');
                khdj_page_query();
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
function erryFunction() {
    alert("错误!");
}