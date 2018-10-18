var customer_ghcx_pageNow = 1;
$(document).ready(function() {
    customer_ghcx_query();
    //    分页相关
    $('#customer_ghcx_page_first').on('click',function(){
        customer_ghcx_pageNow = Number(customer_ghcx_pageNow) - 1;
        customer_ghcx_query();
    });
    $('#customer_ghcx_page_last').on('click',function(){
        customer_ghcx_pageNow = Number(customer_ghcx_pageNow) + 1;
        customer_ghcx_query();
    });
    $('#customer_ghcx_page_to_first').on('click',function(){
        customer_ghcx_pageNow = 1;
        customer_ghcx_query();
    });
    $('#customer_ghcx_page_to_last').on('click',function(){
        customer_ghcx_pageNow = $('#customer_ghcx_page_pages').text().split(':')[1];
        customer_ghcx_query();
    });
    $('#customer_ghcx_page_to_tz').on('click',function(){
        var a = $('#customer_ghcx_page_pages').text().split(':')[1];
        var b = $('#customer_ghcx_page_to_tz_sz').val();
        if(b == '' || b == null)
            return false;
        if(isNaN(b))
            return false;
        if(Number(b) > Number(a))
            alert('超出总页数');
        else{
            customer_ghcx_pageNow = b;
            customer_ghcx_query();
        }
    });

//    排序
    $('.px_zd_zd').on('click',function(){
        $('#fields').val($(this).attr('data-fields'));
        $('#orderBys').val($(this).attr('data-orderBys'));
        customer_ghcx_query();
    });

    //查询条件-------------------
        cus_page_cxtj_sf();
        cus_page_cxtj_khly();
});
function customer_ghcx_query(){
    $('#customer_ghcx_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/customer/customer3/"+Number(customer_ghcx_pageNow),
        async: false,
        type: 'get',
        data: $('#customer_ghcx_query').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>'+e.gsmc+'</td>'
                    +'<td>'+e.sf+'</td>'
                    +'<td>'+e.dq+'</td>'
                    +'<td>'+e.cs+'</td>'
                    +'<td>'+e.xsdz+'</td>'
                    +'<td>'+e.zhgjsj+'</td>'
//                    +'<td>'+e.khly+'</td>'
//                    +'<td>'+e.khdj+'</td>'
                    +'<td>'
                    + '<input class="btn btn-primary btn-xs" type="button" value="调拨" onclick="customer_ghcx_dbz(\''+e.uuid+'\')"/>'
                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" onclick="customer_ghcx_query_xq2(\''+e.uuid+'\',\''+e.gsmc+'\')" value="查看详情"/>'
                    +'</td>'
                    +'</tr>';
                    $('#customer_ghcx_table_data').append(h);
                });
                customer_ghcx_page_pages(req.data);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//分页
function customer_ghcx_page_pages(o){
//    当前页
    customer_ghcx_pageNow = o.pageNum;
    $('#customer_ghcx_page_now').text('当前页:'+customer_ghcx_pageNow);
//    总页数
    $('#customer_ghcx_page_pages').text('总页数:'+o.pages);
//    总条数
    $('#customer_ghcx_page_total').text('总条数:'+o.total);

    if(o.hasPreviousPage){
        $('#customer_ghcx_page_first').show();
        $('#customer_ghcx_page_to_first').show();
    }else{
        $('#customer_ghcx_page_first').hide();
        $('#customer_ghcx_page_to_first').hide();
    }
    if(o.hasNextPage){
        $('#customer_ghcx_page_last').show();
        $('#customer_ghcx_page_to_last').show();
    }else{
        $('#customer_ghcx_page_last').hide();
        $('#customer_ghcx_page_to_last').hide();
    }
}
//调拨至
function customer_ghcx_dbz(obj){
    $('#customer_ghcx_dbz22').val(obj);
    ghcx_page_query_db_gh();
    $('#customer_ghcx_dbz').click();
}
//下级人员
function ghcx_page_query_db_gh(){
    $('#customer_ghcx_dbz21').find('option').remove();
    $.ajax({
        url: path+"/data/account/parents/"+$('#home_uuid').text(),
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#customer_ghcx_dbz21').append('<option value="1">自己</option>');
                $(req.data).each(function(index,e){
                    $('#customer_ghcx_dbz21').append('<option value="'+e.uuid+'">'+e.name+'</option>');
                });
            }else{
                $('#customer_ghcx_dbz21').append('<option value="1">自己</option>');
            }
        },
        error: erryFunction
    });
}
//移入私海
function customer_ghcx_query_db_gh(){
    var d21 = $('#customer_ghcx_dbz21').val();
    if(d21 != ''){
        var r=confirm("是否确定移入?");
        if(r){
            $.ajax({
                url: path+"/data/customer/customer/gsr",
                async: false,
                type: 'put',
                data:{"uuid":$('#customer_ghcx_dbz22').val(),"gsr":d21,"bz":$('#customer_ghcx_dbz23').val()},
                dataType: 'json',
                timeout: 1000,
                cache: false,
                success: function (req) {
                    if(req.success){
                        customer_ghcx_query(0);
                        $('#customer_ghcx_dbz').click();
                    }else{
                        alert(req.message);
                    }
                },
                error: erryFunction
            });
        }
    }else
        alert('请选择调拨目标');
}
//查看详情
function customer_ghcx_query_xq2(obj,obj2){
    $('#qjbl_uuid').val(obj);
    cli('/customer/one','客户详情');
}

//查询条件-----------------------------------
function cus_page_cxtj_sf(){
    $('#customer_ghcx_query').find('select[name="sf"]').find('option').remove();
    $('#customer_ghcx_query').find('select[name="dq"]').find('option').remove();
    $('#customer_ghcx_query').find('select[name="cs"]').find('option').remove();
    $('#customer_ghcx_query').find('select[name="sf"]').append('<option value="">请选择</option>');
    $('#customer_ghcx_query').find('select[name="dq"]').append('<option value="">请选择</option>');
    $('#customer_ghcx_query').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query("0");
    if(dd != null)
    $(dd).each(function(index,e){
        $('#customer_ghcx_query').find('select[name="sf"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_cxtj_dq(){
    $('#customer_ghcx_query').find('select[name="dq"]').find('option').remove();
    $('#customer_ghcx_query').find('select[name="cs"]').find('option').remove();
    $('#customer_ghcx_query').find('select[name="dq"]').append('<option value="">请选择</option>');
    $('#customer_ghcx_query').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query($('#customer_ghcx_query').find('select[name="sf"]').val());
    if(dd != null)
    $(dd).each(function(index,e){
        $('#customer_ghcx_query').find('select[name="dq"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_cxtj_cs(){
    $('#customer_ghcx_query').find('select[name="cs"]').find('option').remove();
    $('#customer_ghcx_query').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query($('#customer_ghcx_query').find('select[name="dq"]').val());
    if(dd != null)
    $(dd).each(function(index,e){
        $('#customer_ghcx_query').find('select[name="cs"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_cxtj_khly(){
    $('#customer_ghcx_query').find('select[name="khly"]').find('option').remove();
    $('#customer_ghcx_query').find('select[name="khly"]').append('<option value="">请选择</option>');
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
                    $('#customer_ghcx_query').find('select[name="khly"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
                });
            }
        },
        error: erryFunction
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