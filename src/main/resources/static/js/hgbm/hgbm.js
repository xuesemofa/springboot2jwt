$(document).ready(function() {
});
function hgbm_tree(ob){
    var obj = $(ob).parent('div');
    var i = $(obj).find('div').length;
    if(i > 0)
        $(obj).find('div').remove();
    else{
        $.ajax({
            url: path+"/data/hgbm/hgbm/"+$(obj).attr('data-id'),
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
                        +'---<i class="fa fa-plus" onclick="hgbm_tree(this)"></i><button type="button" '
                        +'class="btn btn-default btn-xs" data-id="'+e.uuid+'">'
                        +e.mc+'</button>'
//                        +'<button type="button" class="btn btn-default btn-xs" onclick="hgbm_delete(\''+e.uuid+'\')">删除</button>'
                        +'<button type="button" class="btn btn-default btn-xs" onclick="hgbm_update(\''+e.uuid+'\')">修改</button>'
                        +'<button type="button" class="btn btn-default btn-xs" onclick="hgbm_save(\''+e.uuid+'\')">新增下级</button>'
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
function hgbm_save(obj){
    $('#hgbm_save_parents').val(obj);
    $('#hgbm_save').show();
}
function hgbm_save2(){
    $.ajax({
        url: path+"/data/hgbm/hgbm",
        async: false,
        type: 'post',
        data:$('#hgbm_save').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#hgbm_save_parents').val('');
                $('#hgbm_save_mc').val('');
                $('#hgbm_save').hide();
//                $('#hgbm_tree_one').click();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function hgbm_delete(obj){
    $.ajax({
        url: path+"/data/hgbm/hgbm/"+obj,
        async: false,
        type: 'delete',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
//                $('#hgbm_tree_one').click();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function hgbm_update(obj){
    $('#hgbm_update').click();
    $('#hgbm_update_uuid').val(obj);
}
function hgbm_update2(){
    $.ajax({
        url: path+"/data/hgbm/hgbm",
        async: false,
        type: 'put',
        data:{"uuid":$('#hgbm_update_uuid').val(),"mc":$('#hgbm_update_bmmc').val()},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#hgbm_update').click();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}