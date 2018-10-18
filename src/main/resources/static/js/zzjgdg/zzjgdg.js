$(document).ready(function() {
    zzjgdg_rygl();
});
function zzjgdg_rygl(){
    $('#zzjgdg_table_data').find('tr').remove();
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
                    $('#zzjgdg_table_data').append('<tr><td>'+(index+1)+'</td><td>'+e.account+'</td><td>'+e.name+'</td><td>'+e.phone+'</td><td>'
                    +'<select onchange="zzjgdg_dg(\''+e.uuid+'\',this)" data_id="'+e.bmid+'"></select>'
                    +'</td></tr>');
                });
                rygl_bmgl()();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//获取所有的岗位
function rygl_bmgl(){
    $.ajax({
        url: path+"/data/bmgl/findAll",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#zzjgdg_table_data').find('tr').each(function(index1,e1){
                    $(e1).find('td').eq(4).find('select').find('option').remove();
                    $(req.data).each(function(index,e){
                        $(e1).find('td').eq(4).find('select').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
                    });
                    $(e1).find('td').eq(4).find('select').val($(e1).find('td').eq(4).find('select').attr('data_id'));
                });
            }
        },
        error: erryFunction
    });
}
function zzjgdg_dg(o,obj){
    $.ajax({
        url: path+"/data/account/zzjgdg",
        async: false,
        type: 'POST',
        data:{"a":o,"b":$(obj).val()},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                zzjgdg_rygl();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}