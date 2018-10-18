$(document).ready(function() {
});
function dg_tree(ob){
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
                        $(obj).append('<div style="padding-left:10px;width:100%;" data-id="'+e.uuid+'">'
                        +'---<i class="fa fa-plus" onclick="dg_tree(this)"></i><button type="button" '
                        +'class="btn btn-default btn-xs" data-id="'+e.uuid+'">'+e.account+'</button>'
                        +'&nbsp;&nbsp;<button class="btn btn-primary btn-xs" onclick="dg_button_dgB(\''+e.uuid+'\',\''+e.account+'\')">选择为被调岗</button>'
                        +'&nbsp;&nbsp;<button class="btn btn-danger btn-xs" onclick="dg_button_dgM(\''+e.uuid+'\',\''+e.account+'\')">选择为目标岗</button>'
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
//选择被调岗账户
function dg_button_dgB(obj,obj2){
    $('#dg_td_B').find('span').remove();
    $('#dg_td_B').append('<span data-id="'+obj+'">'+obj2+'</span>');
}
function dg_button_dgM(obj,obj2){
    $('#dg_td_M').find('span').remove();
    $('#dg_td_M').append('<span data-id="'+obj+'">'+obj2+'</span>');
}
//保存调岗状态
function dg_button_dg_save(){
    $.ajax({
        url: path+"/data/account/dg",
        async: false,
        type: 'POST',
        data:{"a":$('#dg_td_B').find('span:eq(0)').attr('data-id'),"b":$('#dg_td_M').find('span:eq(0)').attr('data-id')},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#dg_dg_tree_i').click();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function erryFunction() {
    alert("错误!");
}