$(document).ready(function() {
    gjfs_page_query();
});
function gjfs_page_query(){
    $('#gjfs_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/gjfs/gjfs",
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
                    +'<input class="btn btn-primary btn-xs" type="button" onclick="gjfs_page_update(\''+e.uuid+'\')" value="修改"/>'
                    +'</td></tr>';
                    $('#gjfs_page_table_data').append(h);
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function gjfs_page_add(){
    $('#gjfs_page_add2').click();
    $('#gjfs_page_add211').show();
    $('#gjfs_page_update212').hide();
}
function gjfs_page_add21(){
    var mc = $('#gjfs_model_a3').val();
    $.ajax({
        url: path+"/data/gjfs/gjfs",
        async: false,
        type: 'post',
        data:{"mc":mc},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#gjfs_page_add2').click();
                $('#gjfs_model_a3').val('');
                gjfs_page_query();
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
function gjfs_page_update(obj){
    $('#gjfs_page_add2').click();
    $('#gjfs_page_add211').hide();
    $('#gjfs_page_update212').show();
    $('#gjfs_model_a2').val(obj);
}
function gjfs_page_update2(){
    var mc = $('#gjfs_model_a3').val();
    var id = $('#gjfs_model_a2').val();
    $.ajax({
        url: path+"/data/gjfs/gjfs",
        async: false,
        type: 'put',
        data:{"mc":mc,"uuid":id},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#gjfs_page_add2').click();
                $('#gjfs_model_a3').val('');
                $('#gjfs_model_a3').val('');
                gjfs_page_query();
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}