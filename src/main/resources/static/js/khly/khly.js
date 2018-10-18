$(document).ready(function() {
    khly_page_query();
});
function khly_page_query(){
    $('#khly_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/khly/khly",
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
                    +'<input class="btn btn-primary btn-xs" type="button" onclick="khly_page_update(\''+e.uuid+'\')" value="修改"/>'
                    +'</td></tr>';
                    $('#khly_page_table_data').append(h);
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function khly_page_add(){
    $('#khly_page_add2').click();
    $('#khly_page_add211').show();
    $('#khly_page_update212').hide();
}
function khly_page_add21(){
    var mc = $('#khly_model_a3').val();
    $.ajax({
        url: path+"/data/khly/khly",
        async: false,
        type: 'post',
        data:{"mc":mc},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#khly_page_add2').click();
                $('#khly_model_a3').val('');
                khly_page_query();
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
function khly_page_update(obj){
    $('#khly_page_add2').click();
    $('#khly_page_add211').hide();
    $('#khly_page_update212').show();
    $('#khly_model_a2').val(obj);
}
function khly_page_update2(){
    var mc = $('#khly_model_a3').val();
    var id = $('#khly_model_a2').val();
    $.ajax({
        url: path+"/data/khly/khly",
        async: false,
        type: 'put',
        data:{"mc":mc,"uuid":id},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#khly_page_add2').click();
                $('#khly_model_a3').val('');
                $('#khly_model_a3').val('');
                khly_page_query();
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
function erryFunction() {
    alert("错误!");
}