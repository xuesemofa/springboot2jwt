var qbkh_pageNow = 1;
$(document).ready(function() {
    qbkh_page_query();
    $('#qbkh_page_first').on('click',function(){
        qbkh_pageNow = Number(qbkh_pageNow) - 1;
        qbkh_page_query();
    });
    $('#qbkh_page_last').on('click',function(){
        qbkh_pageNow = Number(qbkh_pageNow) + 1;
        qbkh_page_query();
    });
    $('#qbkh_page_to_first').on('click',function(){
        qbkh_pageNow = 1;
        qbkh_page_query();
    });
    $('#qbkh_page_to_last').on('click',function(){
        qbkh_pageNow = $('#qbkh_page_pages').text().split(':')[1];
        qbkh_page_query();
    });
});
function qbkh_page_query(){
    $('#qbkh_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/customer/customer5/"+Number(qbkh_pageNow),
        async: false,
        type: 'get',
        data: $('#qbkh_page_query').serialize(),
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
                    +'<td>'+e.khly+'</td>'
                    +'<td>'+e.khdj+'</td>'
                    +'<td>'+(e.gsr == null ? "公海" : e.gsr)+'</td>'
                    +'<td><input class="btn btn-primary btn-xs" type="button" onclick="qbkh_page_query_db_gh(\''+e.uuid+'\')" value="强制移入公海"/></td></tr>';
                    $('#qbkh_page_table_data').append(h);
                });
                qbkh_page_pages(req.data);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//分页
function qbkh_page_pages(o){
//    当前页
    qbkh_pageNow = o.pageNum;
    $('#qbkh_page_now').text('当前页:'+qbkh_pageNow);
//当前页条数
//    o.size;
//    总页数
//    o.pages;
    $('#qbkh_page_pages').text('总页数:'+o.pages);
//    是否是首页
//    o.isFirstPage;
//    是否是末页
//    o.isLastPage;
//    是否有上一页
//    o.hasPreviousPage;
//    是否有下一页
//    o.hasNextPage;
//    页码集合
//    o.navigateoageNums;
//    总条数
//    o.total;
    $('#qbkh_page_total').text('总条数:'+o.total);

    if(o.hasPreviousPage){
        $('#qbkh_page_first').show();
        $('#qbkh_page_to_first').show();
    }else{
        $('#qbkh_page_first').hide();
        $('#qbkh_page_to_first').hide();
    }
    if(o.hasNextPage){
        $('#qbkh_page_last').show();
        $('#qbkh_page_to_last').show();
    }else{
        $('#qbkh_page_last').hide();
        $('#qbkh_page_to_last').hide();
    }
}
//移入公海
function qbkh_page_query_db_gh(obj){
    var r=confirm("是否确定移入公海?");
    if(r){
        $.ajax({
            url: path+"/data/customer/customer/gsr3",
            async: false,
            type: 'put',
            data:{"uuid":obj},
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    qbkh_page_query(0);
                }else{
                    alert(req.message);
                }
            },
            error: erryFunction
        });
    }
}