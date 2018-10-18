var khdb_pageNow = 1;
$(document).ready(function() {
    khdb_page_query();
    //    分页相关
    $('#khdb_page_first').on('click',function(){
        khdb_pageNow = Number(khdb_pageNow) - 1;
        khdb_page_query();
    });
    $('#khdb_page_last').on('click',function(){
        khdb_pageNow = Number(khdb_pageNow) + 1;
        khdb_page_query();
    });
    $('#khdb_page_to_first').on('click',function(){
        khdb_pageNow = 1;
        khdb_page_query();
    });
    $('#khdb_page_to_last').on('click',function(){
        khdb_pageNow = $('#khdb_page_pages').text().split(':')[1];
        khdb_page_query();
    });

//    排序
        $('.px_zd').on('mouseover',function(){
            $(this).find('span').show();
        });
        $('.px_zd').on('mouseout',function(){
            $(this).find('span').hide();
        });
        $('.px_zd_zd').on('click',function(){
            $('#fields').val($(this).attr('data-fields'));
            $('#orderBys').val($(this).attr('data-orderBys'));
            khdb_page_query();
        });

    //查询条件-------------------
    cus_page_cxtj_sf();
    cus_page_cxtj_khly();
    cus_page_cxtj_gsr();
});
function khdb_page_query(){
    $('#khdb_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/customer/customer4/"+Number(khdb_pageNow),
        async: false,
        type: 'get',
        data: $('#khdb_page_query').serialize(),
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
//                    +'<td>'+e.khly+'</td>'
//                    +'<td>'+e.khdj+'</td>'
                    +'<td>'+e.gsr+'</td>'
                    +'<td>'+e.zhgjsj+'</td>'
                    +'<td><input class="btn btn-primary btn-xs" type="button" '
                    +'onclick="khdb_dbz(\''+e.uuid+'\')" value="调拨"/>'
                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" onclick="customer_ghcx_query_xq2(\''+e.uuid+'\',\''+e.gsmc+'\')" value="查看详情"/>'
                    +'</td></tr>';
                    $('#khdb_page_table_data').append(h);
                });
                khdb_page_pages(req.data);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//分页
function khdb_page_pages(o){
//    当前页
    khdb_pageNow = o.pageNum;
    $('#khdb_page_now').text('当前页:'+khdb_pageNow);
//    总页数
    $('#khdb_page_pages').text('总页数:'+o.pages);
//    总条数
    $('#khdb_page_total').text('总条数:'+o.total);

    if(o.hasPreviousPage){
        $('#khdb_page_first').show();
        $('#khdb_page_to_first').show();
    }else{
        $('#khdb_page_first').hide();
        $('#khdb_page_to_first').hide();
    }
    if(o.hasNextPage){
        $('#khdb_page_last').show();
        $('#khdb_page_to_last').show();
    }else{
        $('#khdb_page_last').hide();
        $('#khdb_page_to_last').hide();
    }
}
//调拨至
function khdb_dbz(obj){
    $('#khdb_dbz22').val(obj);
    ghcx_page_query_db_gh();
    $('#khdb_page_query_db_gh_model_button').click();
}
//下级人员
function ghcx_page_query_db_gh(){
    $.ajax({
        url: path+"/data/account/parents/"+$('#home_uuid').text(),
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#khdb_dbz21').append('<option value="1">自己</option>');
                $(req.data).each(function(index,e){
                    $('#khdb_dbz21').append('<option value="'+e.uuid+'">'+e.name+'</option>');
                });
            }else{
                $('#khdb_dbz21').append('<option value="1">自己</option>');
            }
        },
        error: erryFunction
    });
}
//移入私海
function khdb_query_db_gh(){
    var d21 = $('#khdb_dbz21').val();
    if(d21 != ''){
        var r=confirm("是否确定移入?");
        if(r){
            $.ajax({
                url: path+"/data/customer/customer/gsr",
                async: false,
                type: 'put',
                data:{"uuid":$('#khdb_dbz22').val(),"gsr":d21,"bz":$('#khdb_dbz23').val()},
                dataType: 'json',
                timeout: 1000,
                cache: false,
                success: function (req) {
                    if(req.success){
                        $('#khdb_page_query_db_gh_model_button').click();
                        khdb_page_query(0);
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
    $('#khdb_page_query').find('select[name="sf"]').find('option').remove();
    $('#khdb_page_query').find('select[name="dq"]').find('option').remove();
    $('#khdb_page_query').find('select[name="cs"]').find('option').remove();
    $('#khdb_page_query').find('select[name="sf"]').append('<option value="">请选择</option>');
    $('#khdb_page_query').find('select[name="dq"]').append('<option value="">请选择</option>');
    $('#khdb_page_query').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query("0");
    if(dd != null)
    $(dd).each(function(index,e){
        $('#khdb_page_query').find('select[name="sf"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_cxtj_dq(){
    $('#khdb_page_query').find('select[name="dq"]').find('option').remove();
    $('#khdb_page_query').find('select[name="cs"]').find('option').remove();
    $('#khdb_page_query').find('select[name="dq"]').append('<option value="">请选择</option>');
    $('#khdb_page_query').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query($('#khdb_page_query').find('select[name="sf"]').val());
    if(dd != null)
    $(dd).each(function(index,e){
        $('#khdb_page_query').find('select[name="dq"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_cxtj_cs(){
    $('#khdb_page_query').find('select[name="cs"]').find('option').remove();
    $('#khdb_page_query').find('select[name="cs"]').append('<option value="">请选择</option>');
    var dd = cus_save_form_query($('#khdb_page_query').find('select[name="dq"]').val());
    if(dd != null)
    $(dd).each(function(index,e){
        $('#khdb_page_query').find('select[name="cs"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
    });
}
function cus_page_cxtj_khly(){
    $('#khdb_page_query').find('select[name="khly"]').find('option').remove();
    $('#khdb_page_query').find('select[name="khly"]').append('<option value="">请选择</option>');
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
                    $('#khdb_page_query').find('select[name="khly"]').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
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
function cus_page_cxtj_gsr(){
    $('#khdb_page_query').find('select[name="gsr"]').find('option').remove();
    $.ajax({
        url: path+"/data/account/parents/"+$('#home_uuid').text(),
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#khdb_page_query').find('select[name="gsr"]').append('<option value="">请选择</option>');
                $(req.data).each(function(index,e){
                    $('#khdb_page_query').find('select[name="gsr"]').append('<option value="'+e.uuid+'">'+e.name+'</option>');
                });
            }else{
                $('#khdb_page_query').find('select[name="gsr"]').append('<option value="">请选择</option>');
            }
        },
        error: erryFunction
    });
}