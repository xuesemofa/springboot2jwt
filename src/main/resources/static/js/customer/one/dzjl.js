var dzjl_pageNow = 1;
$(document).ready(function() {
    dzjl_page_query();
    //    分页相关
    $('#dzjl_page_first').on('click',function(){
        dzjl_pageNow = Number(dzjl_pageNow) - 1;
        dzjl_page_query();
    });
    $('#dzjl_page_last').on('click',function(){
        dzjl_pageNow = Number(dzjl_pageNow) + 1;
        dzjl_page_query();
    });
    $('#dzjl_page_to_first').on('click',function(){
        dzjl_pageNow = 1;
        dzjl_page_query();
    });
    $('#dzjl_page_to_last').on('click',function(){
        dzjl_pageNow = $('#dzjl_page_pages').text().split(':')[1];
        dzjl_page_query();
    });
});
function dzjl_page_query(){
    $('#dzjl_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/yrghjl/yrghjl/"+Number(dzjl_pageNow),
        async: false,
        type: 'get',
        data:{"uuid":$('#qjbl_uuid').val()},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>'+e.czr+'</td>'
                    +'<td>'+e.ygsr+'</td>'
                    +'<td>'+e.systimes+'</td>'
                    +'<td>'+(e.ddhgsr == '0' ? '公海' : e.ddhgsr)+'</td>'
                    +'<td>'+e.ddyy+'</td>'
                    +'</tr>';
                    $('#dzjl_page_table_data').append(h);
                });
                dzjl_page_pages(req.data);
            }
        },
        error: erryFunction
    });
}
//分页
function dzjl_page_pages(o){
//    当前页
    dzjl_pageNow = o.pageNum;
    $('#dzjl_page_now').text('当前页:'+dzjl_pageNow);
//    总页数
    $('#dzjl_page_pages').text('总页数:'+o.pages);
//    总条数
    $('#dzjl_page_total').text('总条数:'+o.total);

    if(o.hasPreviousPage){
        $('#dzjl_page_first').show();
        $('#dzjl_page_to_first').show();
    }else{
        $('#dzjl_page_first').hide();
        $('#dzjl_page_to_first').hide();
    }
    if(o.hasNextPage){
        $('#dzjl_page_last').show();
        $('#dzjl_page_to_last').show();
    }else{
        $('#dzjl_page_last').hide();
        $('#dzjl_page_to_last').hide();
    }
}