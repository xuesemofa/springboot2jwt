$(document).ready(function() {
    customer_one_xq();
});
//刷新
function customer_one_xq_sx(){
    customer_one_xq();
    lxxx_page_query();
    gjjl_page_query();
    dzjl_page_query();
    xgjl_page_query();
    alert('已刷新');
}
//默认
function customer_one_xq(){
    cus_save_form_query_sf();
    $.ajax({
        url: path+"/data/customer/customer/id3/"+$('#qjbl_uuid').val(),
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){

                $('#cus_one_xq_a1').find('td').eq(3).html('<font style="color:#0055ff;">'+req.data.system+'</font>');
                var bh = req.data.bh;
                var b = bh.toString();
                var b = 6 - b.length;
                for(var i = 0;i < b;i++){
                    bh = '0'+bh;
                }
                $('#cus_one_xq_a1').find('td').eq(5).html('<font style="color:#0055ff;">'+bh+'</font>');
                $('#cus_one_xq_a1').find('td').eq(7).find('div').find('img').remove();
                $(req.data.list).each(function(index,e){
                    $('#cus_one_xq_a1').find('td').eq(7).find('div').append('<img style="height:30px;width:30px;" src="'+path+"/"+e.imgs+'"/>');
                });

                $('#cus_one_xq_a').find('td').eq(1).html('<font style="color:#0055ff;">'+req.data.gsmc+'</font>');
                $('#cus_one_xq_a').find('td').eq(3).html('<font style="color:#0055ff;">'+req.data.xsdz+'</font>');
                $('#cus_one_xq_a').find('td').eq(5).html('<font style="color:#0055ff;">'+req.data.khly+'</font>');
                var khlx = "";
                if(req.data.khlx == 1)
                    khlx = "未签约";
                if(req.data.khlx == 2)
                    khlx = "断约";
                if(req.data.khlx == 3)
                    khlx = "合作";
                $('#cus_one_xq_a').find('td').eq(7).html('<font style="color:#0055ff;">'+khlx+'</font>');
                $('#cus_one_xq_a').find('td').eq(9).html('<font style="color:#0055ff;">'+req.data.khdj+'</font>');
                $('#cus_one_xq_a').find('td').eq(11).html('<font style="color:#0055ff;">'+req.data.clsj+'</font>');

                $('#cus_one_xq_b').find('td').eq(1).html('<font style="color:#0055ff;">'+req.data.xsqy+'</font>');
                $('#cus_one_xq_b').find('td').eq(3).html('<font style="color:#0055ff;">'+req.data.ygrs+'</font>');
                $('#cus_one_xq_b').find('td').eq(5).html('<font style="color:#0055ff;">'+req.data.xsqd+'</font>');
                $('#cus_one_xq_b').find('td').eq(7).html('<font style="color:#0055ff;">'+req.data.sf+'</font>');
                $('#cus_one_xq_b').find('td').eq(9).html('<font style="color:#0055ff;">'+req.data.dq+'</font>');
                $('#cus_one_xq_b').find('td').eq(11).html('<font style="color:#0055ff;">'+req.data.cs+'</font>');

                $('#cus_one_xq_c').find('td').eq(1).html('<font style="color:#0055ff;">'+req.data.qylx+'</font>');
                $('#cus_one_xq_c').find('td').eq(3).html('<font style="color:#0055ff;">'+req.data.pp+'</font>');
                $('#cus_one_xq_c').find('td').eq(5).html('<font style="color:#0055ff;">'+req.data.wz+'</font>');
                $('#cus_one_xq_c').find('td').eq(7).html('<font style="color:#0055ff;">'+req.data.ncke+'</font>');
                $('#cus_one_xq_c').find('td').eq(9).html('<font style="color:#0055ff;">'+req.data.ndde+'</font>');
                $('#cus_one_xq_c').find('td').eq(11).html('<font style="color:#0055ff;">'+req.data.sydjh+'</font>');
//
                $('#cus_one_xq_d').find('td').eq(1).html('<font style="color:#0055ff;">'+req.data.cw_mc+'</font>');
                $('#cus_one_xq_d').find('td').eq(3).html('<font style="color:#0055ff;">'+req.data.cw_nsrsbh+'</font>');
                $('#cus_one_xq_d').find('td').eq(5).html('<font style="color:#0055ff;">'+req.data.cw_dz+'</font>');
                $('#cus_one_xq_d').find('td').eq(7).html('<font style="color:#0055ff;">'+req.data.cw_dh+'</font>');
                $('#cus_one_xq_d').find('td').eq(9).html('<font style="color:#0055ff;">'+req.data.cw_khh+'</font>');
                $('#cus_one_xq_d').find('td').eq(11).html('<font style="color:#0055ff;">'+req.data.cw_zh+'</font>');

                customer_add_form_find_khly();
                customer_add_form_find_khdj();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//获取客户来源
function customer_add_form_find_khly(){
    var t = $('#cus_one_xq_a').find('td').eq(5).text();
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
                    if(e.uuid == t)
                        $('#cus_one_xq_a').find('td').eq(5).html('<font style="color:#0055ff;">'+e.mc+'</font>');
                });
            }
        },
        error: erryFunction
    });
}
//获取客户等级
function customer_add_form_find_khdj(){
    var t = $('#cus_one_xq_a').find('td').eq(7).text();
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
                    if(e.uuid == t)
                        $('#cus_one_xq_a').find('td').eq(7).html('<font style="color:#0055ff;">'+e.mc+'</font>');
                });
            }
        },
        error: erryFunction
    });
}
function cus_save_form_query_sf(){
    $('#cus_page_update_data').find('select[name="sf"]').find('option').remove();
    $('#cus_page_update_data').find('select[name="dq"]').find('option').remove();
    $('#cus_page_update_data').find('select[name="cs"]').find('option').remove();
    $('#cus_page_update_data').find('select[name="sf"]').append('<option value="">请选择</option>');
    $('#cus_page_update_data').find('select[name="dq"]').append('<option value="">请选择</option>');
    $('#cus_page_update_data').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query("0");
    if(dd != null)
    $(dd).each(function(index,e){
        $('#cus_page_update_data').find('select[name="sf"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_form_query_dq(){
    $('#cus_page_update_data').find('select[name="dq"]').find('option').remove();
    $('#cus_page_update_data').find('select[name="cs"]').find('option').remove();
    $('#cus_page_update_data').find('select[name="dq"]').append('<option value="">请选择</option>');
    $('#cus_page_update_data').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query($('#cus_page_update_data').find('select[name="sf"]').val());
    if(dd != null)
    $(dd).each(function(index,e){
        $('#cus_page_update_data').find('select[name="dq"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_form_query_cs(){
    $('#cus_page_update_data').find('select[name="cs"]').find('option').remove();
    $('#cus_page_update_data').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query($('#cus_page_update_data').find('select[name="dq"]').val());
    if(dd != null)
    $(dd).each(function(index,e){
        $('#cus_page_update_data').find('select[name="cs"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_save_form_query(obj){
    if(obj == '' || obj == null){
        return null;
    }else{
        var dd;
        $.ajax({
            url: path+"/data/dywh/dywh/"+obj,
            async: false,
            type: 'get',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    dd = req.data;
                }else{
                    alert(req.message);
                }
            },
            error: erryFunction
        });
        return dd;
    }
}