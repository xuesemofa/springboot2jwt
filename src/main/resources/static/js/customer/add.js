$(document).ready(function() {
//    customer_page_query(customer_pageNow);
    cus_save_form_query_sf();
    $('#customer_add_clsj_a').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: false,
        autoclose: true,
        format: "yyyy-mm-dd"
    });
    customer_add_form_find_khly();
//    customer_add_form_find_khdj();
    customer_add_form_zycp1("0");
    $('#customer_add_form_zycp1').on('change',function(){
        customer_add_form_zycp2();
    });
    $('#customer_add_form_zycp2').on('change',function(){
        customer_add_form_zycp3();
    });
//    选择客户类型更改客户等级
    $('#customer_add_form_khlx').on('change',function(){
        customer_add_form_khlx_change($('#customer_add_form_khlx').val());
    });
});
//选择客户类型更改客户等级
function customer_add_form_khlx_change(obj){
    $('#customer_add_form_khdj').find('option').remove();
    if(obj != ''){
        for(var i = 0;i < 3;i++){
            $('#customer_add_form_khdj').append('<option value="'+(String.fromCharCode(65+i))+'">'+(String.fromCharCode(65+i))+'</option>');
        }
        if(obj != 3){
            $('#customer_add_form_khdj').append('<option value="'+(String.fromCharCode(68))+'">'+(String.fromCharCode(68))+'</option>');
        }
    }else
        $('#customer_add_form_khdj').append('<option value="">请选择</option>');
}
function cus_save_form_query_sf(){
    $('select[name="sf"]').find('option').remove();
    $('select[name="dq"]').find('option').remove();
    $('select[name="cs"]').find('option').remove();
    $('select[name="sf"]').append('<option value="">请选择</option>');
    $('select[name="dq"]').append('<option value="">请选择</option>');
    $('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query("0");
    $(dd).each(function(index,e){
        $('select[name="sf"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_save_form_query_dq(){
    $('select[name="dq"]').find('option').remove();
    $('select[name="cs"]').find('option').remove();
    $('select[name="dq"]').append('<option value="">请选择</option>');
    $('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query($('select[name="sf"]').val());
    $(dd).each(function(index,e){
        $('select[name="dq"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_save_form_query_cs(){
    $('select[name="cs"]').find('option').remove();
    $('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query($('select[name="dq"]').val());
    $(dd).each(function(index,e){
        $('select[name="cs"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_save_form_query(obj){
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
            }
        },
        error: erryFunction
    });
    return dd;
}
function customer_add_form(){
    cus_img_list();
    $.ajax({
        url: path+"/data/customer/customer",
        async: false,
        type: 'post',
        data:$('#customer_add_form').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                document.getElementById("customer_add_form").reset();
                cus_save_form_query_sf();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//获取客户来源
function customer_add_form_find_khly(){
    $('#customer_add_form_khly').find('option').remove();
    $('#customer_add_form_khly').append('<option value="">请选择</option>');
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
                    $('#customer_add_form_khly').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
                });
            }
        },
        error: erryFunction
    });
}
//获取客户等级
//function customer_add_form_find_khdj(){
//    $('#customer_add_form_khdj').find('option').remove();
//    $('#customer_add_form_khdj').append('<option value="">请选择</option>');
//    $.ajax({
//        url: path+"/data/khdj/khdj",
//        async: false,
//        type: 'get',
//        dataType: 'json',
//        timeout: 1000,
//        cache: false,
//        success: function (req) {
//            if(req.success){
//                $(req.data).each(function(index,e){
//                    $('#customer_add_form_khdj').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
//                });
//            }
//        },
//        error: erryFunction
//    });
//}
//提交之前整理图片
function cus_img_list(){
    $('#cus_img_list').find('input').remove();
    $('#cus_img_data').find('img').each(function(index,e){
        var s = $(e).attr('src');
        $('#cus_img_list').append('<input type="hidden" name="list['+index+'].imgs" value="'+s+'"/>');
    });
}
//获取海关编码
function customer_add_form_zycp1(obj){
    var a = customer_add_form_find_hgbm(obj);
    $('#customer_add_form_zycp1').find('option').remove();
    $('#customer_add_form_zycp1').append('<option value="">请选择</option>');
    $(a).each(function(index,e){
        $('#customer_add_form_zycp1').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function customer_add_form_zycp2(){
    var a = customer_add_form_find_hgbm($('#customer_add_form_zycp1').val());
    $('#customer_add_form_zycp2').find('option').remove();
    $('#customer_add_form_zycp2').append('<option value="">请选择</option>');
    $('#customer_add_form_zycp3').find('option').remove();
    $('#customer_add_form_zycp3').append('<option value="">请选择</option>');
    $(a).each(function(index,e){
        $('#customer_add_form_zycp2').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function customer_add_form_zycp3(){
    var a = customer_add_form_find_hgbm($('#customer_add_form_zycp2').val());
    $('#customer_add_form_zycp3').find('option').remove();
    $('#customer_add_form_zycp3').append('<option value="">请选择</option>');
    $(a).each(function(index,e){
        $('#customer_add_form_zycp3').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function customer_add_form_find_hgbm(obj){
    var a;
    $.ajax({
        url: path+"/data/hgbm/hgbm/"+obj,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success)
                a = req.data;
        },
        error: erryFunction
    });
    return a;
}