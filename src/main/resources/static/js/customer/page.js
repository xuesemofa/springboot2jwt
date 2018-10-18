var customer_pageNow = 1;
$(document).ready(function() {
    customer_page_query();
    //    分页相关
    $('#customer_page_first').on('click',function(){
        customer_pageNow = Number(customer_pageNow) - 1;
        customer_page_query();
    });
    $('#customer_page_last').on('click',function(){
        customer_pageNow = Number(customer_pageNow) + 1;
        customer_page_query();
    });
    $('#customer_page_to_first').on('click',function(){
        customer_pageNow = 1;
        customer_page_query();
    });
    $('#customer_page_to_last').on('click',function(){
        customer_pageNow = $('#customer_page_pages').text().split(':')[1];
        customer_page_query();
    });

    $('#customer_update_clsj_a').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: false,
        autoclose: true,
        format: "yyyy-mm-dd"
    });
    customer_add_form_find_khly();
//    customer_add_form_find_khdj();
    //    选择客户类型更改客户等级
    $('#customer_page_form_khlx').on('change',function(){
        customer_page_form_khlx_change($('#customer_page_form_khlx').val());
    });

    //    排序
        $('.px_zd_zd').on('click',function(){
            $('#fields').val($(this).attr('data-fields'));
            $('#orderBys').val($(this).attr('data-orderBys'));
            customer_page_query();
        });

//查询条件-------------------
    cus_page_cxtj_sf();
    cus_page_cxtj_khly();
});
//选择客户类型更改客户等级
function customer_page_form_khlx_change(obj){
    $('#customer_page_form_khdj').find('option').remove();
    if(obj != ''){
        for(var i = 0;i < 3;i++){
            $('#customer_page_form_khdj').append('<option value="'+(String.fromCharCode(65+i))+'">'+(String.fromCharCode(65+i))+'</option>');
        }
        if(obj != 3){
            $('#customer_page_form_khdj').append('<option value="'+(String.fromCharCode(68))+'">'+(String.fromCharCode(68))+'</option>');
        }
    }else
        $('#customer_page_form_khdj').append('<option value="">请选择</option>');
}
function customer_page_query(){
    $('#customer_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/customer/customer/"+Number(customer_pageNow),
        async: false,
        type: 'get',
        data: $('#customer_page_query').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var s = "";
                    if(e.khlx == 1)
                        s = "未签约";
                    if(e.khlx == 2)
                        s = "断约";
                    if(e.khlx == 3)
                        s = "合作";
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>'+e.gsmc+'</td>'
                    +'<td>'+e.sf+'</td>'
                    +'<td>'+e.dq+'</td>'
                    +'<td>'+e.cs+'</td>'
                    +'<td>'+e.xsdz+'</td>'
                    +'<td>'+e.khly+'</td>'
                    +'<td>'+s+'</td>'
                    +'<td>'+e.khdj+'</td>'
                    +'<td>'+e.zhgjsj+'</td>'
                    +'<td>'
                    +'<input class="btn btn-primary btn-xs" type="button" onclick="customer_page_query_query_xq222(\''+e.uuid+'\')" value="修改"/>'
                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" onclick="customer_page_query_db_gh(\''+e.uuid+'\')" value="移入公海"/>'
                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" onclick="customer_page_query_query_xq2(\''+e.uuid+'\',\''+e.gsmc+'\')" value="查看详情"/>'
                    +'</td></tr>';
                    $('#customer_page_table_data').append(h);
                });
                customer_page_pages(req.data);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//分页
function customer_page_pages(o){
//    当前页
    customer_pageNow = o.pageNum;
    $('#customer_page_now').text('当前页:'+customer_pageNow);
//    总页数
    $('#customer_page_pages').text('总页数:'+o.pages);
//    总条数
    $('#customer_page_total').text('总条数:'+o.total);

    if(o.hasPreviousPage){
        $('#customer_page_first').show();
        $('#customer_page_to_first').show();
    }else{
        $('#customer_page_first').hide();
        $('#customer_page_to_first').hide();
    }
    if(o.hasNextPage){
        $('#customer_page_last').show();
        $('#customer_page_to_last').show();
    }else{
        $('#customer_page_last').hide();
        $('#customer_page_to_last').hide();
    }
}
//移入公海
function customer_page_query_db_gh(obj){
    var r=confirm("是否确定移入公海?");
    if(r){
        $('#customer_model_button2').click();
        $('#customer_model_a3').val(obj);
    }
}
function customer_page_query_db_gh22(){
    $.ajax({
        url: path+"/data/customer/customer/gsr",
        async: false,
        type: 'put',
        data:{"uuid":$('#customer_model_a3').val(),"gsr":"0","bz":$('#customer_model_a2').val()},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                customer_page_query(0);
                $('#customer_model_button2').click();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//查看详情
function customer_page_query_query_xq2(obj,obj2){
    $('#qjbl_uuid').val(obj);
    cli('/customer/one','客户详情');
//    $('#customer_model_button').click();
//    customer_page_query_query_xq3(obj,1);
}
//修改
function customer_page_query_query_xq222(obj){
    $('#customer_model_button').click();
    customer_page_query_query_xq(obj,2);
}
function customer_page_query_query_xq(obj,o){
    $('#cus_img_data2').find('img').remove();
    cus_save_form_query_sf();
    $.ajax({
        url: path+"/data/customer/customer/id/"+obj,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#customer_model_a').find('input').eq(0).val(req.data.gsmc);
                $('#customer_model_a').find('input').eq(1).val(req.data.xsdz);
                $('#customer_model_a').find('select').eq(0).val(req.data.khly);
                $('#customer_model_a').find('select').eq(1).val(req.data.khlx);
                customer_page_form_khlx_change(req.data.khlx);
                $('#customer_model_a').find('select').eq(2).val(req.data.khdj);
                $('#customer_update_clsj_a').find('input').eq(0).val(req.data.clsj);
                $('#customer_model_a').find('input').eq(3).val(req.data.zycp);
                $('#customer_model_a').find('input').eq(4).val(req.data.xsqy);
                $('#customer_model_a').find('input').eq(5).val(req.data.ygrs);
                $('#customer_model_a').find('input').eq(6).val(req.data.xsqd);
                $('#customer_model_a_uuid').val(req.data.uuid);
//                $('#customer_model_a').find('input').eq(8).val(req.data.khly);

                $('#customer_model_b').find('select').eq(3).val(req.data.qylx);
                $('#customer_model_b').find('input').eq(0).val(req.data.pp);
                $('#customer_model_b').find('input').eq(1).val(req.data.wz);
                $('#customer_model_b').find('input').eq(2).val(req.data.ncke);
                $('#customer_model_b').find('input').eq(3).val(req.data.ndde);
                $('#customer_model_b').find('input').eq(4).val(req.data.sydjh);

                $('#customer_model_c').find('input').eq(0).val(req.data.cw_mc);
                $('#customer_model_c').find('input').eq(1).val(req.data.cw_nsrsbh);
                $('#customer_model_c').find('input').eq(2).val(req.data.cw_dz);
                $('#customer_model_c').find('input').eq(3).val(req.data.cw_dh);
                $('#customer_model_c').find('input').eq(4).val(req.data.cw_khh);
                $('#customer_model_c').find('input').eq(5).val(req.data.cw_zh);

                if(o == 2)
                    $('#customer_page_query_query_xq222').show();
                else
                    $('#customer_page_query_query_xq222').hide();

                $('#customer_model_b').find('select').eq(0).val(req.data.sf);
                cus_page_form_query_dq();
                $('#customer_model_b').find('select').eq(1).val(req.data.dq);
                cus_page_form_query_cs();
                $('#customer_model_b').find('select').eq(2).val(req.data.cs);
                $(req.data.list).each(function(index,e){
                    $('#cus_img_data2').append('<img style="width:30px;height:30px;" ondblclick="cus_img_img2(this)" src="'+path+"/"+e.imgs+'"/>');
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}

//提交之前整理图片
function cus_img_list2(){
    $('#cus_img_list2').find('input').remove();
    $('#cus_img_data2').find('img').each(function(index,e){
        var s = $(e).attr('src');
        $('#cus_img_list').append('<input type="hidden" name="list['+index+'].imgs" value="'+s+'"/>');
    });
}

function customer_page_update(){
    cus_img_list2();
    $.ajax({
        url: path+"/data/customer/customer",
        async: false,
        type: 'put',
        data: $('#customer_page_update').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#customer_model_button').click();
                customer_page_query(customer_pageNow);
            }else{
                alert(req.message);
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

//获取客户来源
function customer_add_form_find_khly(){
    $('#customer_page_form_khly').find('option').remove();
    $('#customer_page_form_khly').append('<option value="">请选择</option>');
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
                    $('#customer_page_form_khly').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
                });
            }
        },
        error: erryFunction
    });
}
//查询条件-----------------------------------
function cus_page_cxtj_sf(){
    $('#customer_page_query').find('select[name="sf"]').find('option').remove();
    $('#customer_page_query').find('select[name="dq"]').find('option').remove();
    $('#customer_page_query').find('select[name="cs"]').find('option').remove();
    $('#customer_page_query').find('select[name="sf"]').append('<option value="">请选择</option>');
    $('#customer_page_query').find('select[name="dq"]').append('<option value="">请选择</option>');
    $('#customer_page_query').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query("0");
    if(dd != null)
    $(dd).each(function(index,e){
        $('#customer_page_query').find('select[name="sf"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_cxtj_dq(){
    $('#customer_page_query').find('select[name="dq"]').find('option').remove();
    $('#customer_page_query').find('select[name="cs"]').find('option').remove();
    $('#customer_page_query').find('select[name="dq"]').append('<option value="">请选择</option>');
    $('#customer_page_query').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query($('#customer_page_query').find('select[name="sf"]').val());
    if(dd != null)
    $(dd).each(function(index,e){
        $('#customer_page_query').find('select[name="dq"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_cxtj_cs(){
    $('#customer_page_query').find('select[name="cs"]').find('option').remove();
    $('#customer_page_query').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query($('#customer_page_query').find('select[name="dq"]').val());
    if(dd != null)
    $(dd).each(function(index,e){
        $('#customer_page_query').find('select[name="cs"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_cxtj_khly(){
    $('#customer_page_query').find('select[name="khly"]').find('option').remove();
    $('#customer_page_query').find('select[name="khly"]').append('<option value="">请选择</option>');
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
                    $('#customer_page_query').find('select[name="khly"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
                });
            }
        },
        error: erryFunction
    });
}
//导出结果
function customer_page_exp(){
    $('#customer_page_query').attr('action',path+"/data/customer/customer/exp");
    $('#customer_page_query').attr('method',"get");
    $('#customer_page_query').submit();

//    $.ajax({
//        url: path+"/data/customer/customer/exp",
//        async: false,
//        type: 'get',
//        data: $('#customer_page_query').serialize(),
//        dataType: 'json',
//        timeout: 1000,
//        cache: false,
//        success: function (req) {
//            if(!req.success)
//                alert(req.message);
//        },
//        error: erryFunction
//    });
}