var rygl_uuid = 0;
$(document).ready(function() {
});
function rygl_tree(ob){
    var obj = $(ob).parent('div');
    var i = $(obj).find('div').length;
    if(i > 0)
        $(obj).find('div').remove();
    else{
        $.ajax({
            url: path+"/data/account/parents/"+$(obj).attr('data-id'),
            async: false,
            type: 'get',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    var ii = $(obj).attr('data-id');
                    $(req.data).each(function(index,e){
                        var h = '&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="禁止登陆" onclick="rygl_jzdl(\''+e.uuid+'\',\'N\')"/>';
                        if(e.isLogin != 'Y')
                            h = '&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="允许登陆" onclick="rygl_jzdl(\''+e.uuid+'\',\'Y\')"/>';

                        $(obj).append('<div style="padding-left:10px;width:100%;" data-id="'+e.uuid+'">'
                        +'---<i class="fa fa-plus" onclick="rygl_tree(this)"></i><button type="button" '
                        +'class="btn btn-default btn-xs" data-id="'+e.uuid+'">'
                        +e.name+'</button>'
                        +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="删除" onclick="rygl_delete(\''+e.uuid+'\')"/>'
                        + h
                        +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="团队调岗" onclick="rygl_dg(\''+e.uuid+'\')"/>'
                        +'&nbsp;&nbsp;<button type="button" class="btn btn-default btn-xs" onclick="rygl_add(\''+e.uuid+'\')">新增人员</button>'
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
function rygl_rygl(){
    $('#rygl_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/account/parents/-1",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    var h = '&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="禁止登陆" onclick="rygl_jzdl(\''+e.uuid+'\',\'N\')"/>';
                    if(e.isLogin != 'Y')
                        h = '&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="允许登陆" onclick="rygl_jzdl(\''+e.uuid+'\',\'Y\')"/>';
                    $('#rygl_table_data').append('<tr><td>'+(index+1)+'</td><td>'+e.account+'</td><td>'+e.name+'</td><td>'+e.phone+'</td><td>'
                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="重置密码" onclick="rygl_czmm(\''+e.uuid+'\')"/>'
                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="删除" onclick="rygl_delete(\''+e.uuid+'\')"/>'
                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="团队调岗" onclick="rygl_dg(\''+e.uuid+'\')"/>'
                    + h
                    +'</td></tr>');
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function rygl_add(obj){
    $('#rygl_save').show();
    $('#rygl_save_parents').val(obj);
}
function rygl_save2(){
    $.ajax({
        url: path+"/data/account/account",
        async: false,
        type: 'post',
        data: $('#rygl_save').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#rygl_save')[0].reset();
                $('#rygl_save').hide();
                rygl_rygl(rygl_uuid);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function rygl_delete(obj){
    var r=confirm("是否确定删除!");
    if(r){
        $.ajax({
        url: path+"/data/account/account/"+obj,
        async: false,
        type: 'delete',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                rygl_rygl(rygl_uuid);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
        });
    }
}
//调岗
function rygl_dg(obj){
    $('#rygl_model_dg').click();
    $('#rygl_model_dg_uuid').val(obj);
    rygl_bmgl();
}
//获取所有的人员
function rygl_bmgl(){
    $('#rygl_model_dg_parents').find('option').remove();
    $('#rygl_model_dg_parents').append('<option value="">请选择</option>');
    $.ajax({
        url: path+"/data/account/parents/-1",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#rygl_model_dg_parents').append('<option value="0">最高父级</option>');
                $(req.data).each(function(index,e){
                    $('#rygl_model_dg_parents').append('<option value="'+e.uuid+'">'+e.name+'</option>');
                });
            }else{
                alert("未获取到记录");
            }
        },
        error: erryFunction
    });
}
function rygl_model_dg2(){
    $.ajax({
        url: path+"/data/account/dg",
        async: false,
        type: 'POST',
        data:{"a":$('#rygl_model_dg_uuid').val(),"b":$('#rygl_model_dg_parents').val()},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#rygl_model_dg').click();
                rygl_rygl(rygl_uuid);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//禁止登陆
function rygl_jzdl(obj,isLogin){
    var r=confirm("是否确定禁止登陆!");
    if(r){
        $.ajax({
        url: path+"/data/account/isLogin",
        async: false,
        type: 'put',
        data:{'uuid':obj,"isLogin":isLogin},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                rygl_rygl(rygl_uuid);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
        });
    }
}
//重置密码
function rygl_czmm(uuid){
    var r=confirm("是否确定重置密码?");
    if(r){
        $.ajax({
        url: path+"/data/account/czmm",
        async: false,
        type: 'put',
        data:{'uuid':uuid},
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
}