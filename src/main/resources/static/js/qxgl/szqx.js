$(document).ready(function() {
});
function szqx_tree(ob){
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
                        +'---<i class="fa fa-plus" onclick="szqx_tree(this)"></i><button type="button" '
                        +'class="btn btn-default btn-xs" data-id="'+e.uuid+'">'
                        +e.mc+'</button>'
                        +'<button type="button" class="btn btn-default btn-xs" onclick="szqx_glqx(\''+e.uuid+'\')">管理权限</button>'
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
function szqx_glqx(obj){
    $('#szqx_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/jurisdiction/findAll",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    $('#szqx_table_data').append("<tr data-id='"+e.uuid+"'><td>"+(index+1)+"</td><td>"+e.name+"</td><td>"
                    +"<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"szqx_glqx_sfsyqx(\'"+e.uuid+"\',\'"+obj+"\')\">授予</button>"
                    +"</td></tr>");
                });
                szqx_glqx_yyqx(obj);
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
//获取已有的权限
function szqx_glqx_yyqx(obj){
    $.ajax({
        url: path+"/data/jurbm/bmid/"+obj,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#szqx_table_data').find('tr').each(function(index,e){
                    var i = $(e).attr('data-id');
                    $(req.data).each(function(index1,e1){
                        if(i == e1.jurId){
                            $(e).find('td:eq(2)').html("<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"szqx_glqx_sfsyqx(\'"+i+"\',\'"+obj+"\')\">取消</button>");
                        }
                    });
                });
            }
        },
        error: erryFunction
    });
}
//设置权限
function szqx_glqx_sfsyqx(uuid,obj){
    $.ajax({
        url: path+"/data/jurbm/jurbm/"+uuid+"/"+obj,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                szqx_glqx(obj);
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}