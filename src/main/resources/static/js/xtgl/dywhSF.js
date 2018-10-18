$(document).ready(function() {
    dywh_page_sf_query();
});
function dywh_page_sf_query(){
    $('#dywh_page_sf_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/dywh/dywh/0",
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
                    +'<input class="btn btn-primary btn-xs" type="button" onclick="dywh_page_sf_delete(\''+e.uuid+'\')" value="删除"/>'
                    +'<input class="btn btn-primary btn-xs" type="button" onclick="dywh_page_sf_update(\''+e.uuid+'\')" value="修改"/>'
                    +'</td></tr>';
                    $('#dywh_page_sf_table_data').append(h);
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function dywh_page_sf_delete(obj){
    var r=confirm("是否确定删除?");
    if(r){
        $.ajax({
            url: path+"/data/dywh/dywh/"+obj,
            async: false,
            type: 'delete',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    dywh_page_sf_query();
                }else{
                    alert(req.message);
                }
            },
            error: erryFunction
        });
    }
}
function dywh_page_sf_save(){
    $.ajax({
        url: path+"/data/dywh/dywh",
        async: false,
        type: 'post',
        data:$('#dywh_page_sf_save').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('input[name="mc"]').val('');
                dywh_page_sf_query();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function dywh_page_sf_update(obj){
    $('#dywh_page_sf_update').click();
    $('#dywh_page_sf_update_uuid').val(obj);
}
function dywh_page_sf_update2(){
    $.ajax({
        url: path+"/data/dywh/dywh2",
        async: false,
        type: 'put',
        data:{"uuid":$('#dywh_page_sf_update_uuid').val(),"mc":$('#dywh_page_sf_update_mc').val()},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#dywh_page_sf_update').click();
                dywh_page_sf_query();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}