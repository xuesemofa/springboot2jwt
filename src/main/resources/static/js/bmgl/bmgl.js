$(document).ready(function() {
});
function bmgl_tree(ob){
    var obj = $(ob).parent('div');
    var i = $(obj).find('div').length;
    if(i > 0)
        $(obj).find('div').remove();
    else{
        $.ajax({
            url: path+"/data/bmgl/parents/"+$(obj).attr('data-id'),
            async: false,
            type: 'get',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    var ii = $(obj).attr('data-id');
                    $(req.data).each(function(index,e){
                        $(obj).append('<div style="padding-left:10px;width:100%;" data-id="'+e.uuid+'">'
                        +'---<i class="fa fa-plus" onclick="bmgl_tree(this)"></i><button type="button" '
                        +'class="btn btn-default btn-xs" data-id="'+e.uuid+'">'
                        +e.mc+'</button>'
                        +'<button type="button" class="btn btn-default btn-xs" onclick="bmgl_delete(\''+e.uuid+'\')">删除</button>'
                        +'<button type="button" class="btn btn-default btn-xs" onclick="bmgl_update(\''+e.uuid+'\')">修改</button>'
                        +'<button type="button" class="btn btn-default btn-xs" onclick="bmgl_save(\''+e.uuid+'\')">新增下级</button>'
                        +'</div>');
                    });
                }else{
                    alert(req.message);
                }
            },
            error: erryFunction
        });
    }
}
function bmgl_save(obj){
    $('#bmgl_save_parents').val(obj);
    $('#bmgl_save').show();
}
function bmgl_save2(){
    $.ajax({
        url: path+"/data/bmgl/bmgl",
        async: false,
        type: 'post',
        data:$('#bmgl_save').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#bmgl_save_parents').val('');
                $('#bmgl_save_mc').val('');
                $('#bmgl_save').hide();
//                $('#bmgl_tree_one').click();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function bmgl_delete(obj){
    $.ajax({
        url: path+"/data/bmgl/bmgl/"+obj,
        async: false,
        type: 'delete',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
//                $('#bmgl_tree_one').click();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function bmgl_update(obj){
    $('#bmgl_update').click();
    $('#bmgl_update_uuid').val(obj);
}
function bmgl_update2(){
    $.ajax({
        url: path+"/data/bmgl/bmgl2",
        async: false,
        type: 'put',
        data:{"uuid":$('#bmgl_update_uuid').val(),"mc":$('#bmgl_update_bmmc').val()},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#bmgl_update').click();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}