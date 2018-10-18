var gjjl_pageNow = 1;
$(document).ready(function() {
    gjjl_page_query();
    gjjl_page_query_gjfs();
    gjjl_page_gsr();
    //    分页相关
    $('#gjjl_page_first').on('click',function(){
        gjjl_pageNow = Number(gjjl_pageNow) - 1;
        gjjl_page_query();
    });
    $('#gjjl_page_last').on('click',function(){
        gjjl_pageNow = Number(gjjl_pageNow) + 1;
        gjjl_page_query();
    });
    $('#gjjl_page_to_first').on('click',function(){
        gjjl_pageNow = 1;
        gjjl_page_query();
    });
    $('#gjjl_page_to_last').on('click',function(){
        gjjl_pageNow = $('#gjjl_page_pages').text().split(':')[1];
        gjjl_page_query();
    });

    $('#gjjl_gjsjStr').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: false,
        autoclose: true,
        format: "yyyy-mm-dd"
    });
    $('#gjjl_gjsjEnd').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: false,
        autoclose: true,
        format: "yyyy-mm-dd"
    });
});
function gjjl_page_query(){
    $('#gjjl_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/gjjl/gjjl2/"+Number(gjjl_pageNow),
        async: false,
        type: 'get',
        data: $('#gjjl_page_query').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>'+e.cusid+'</td>'
                    +'<td>'+e.gjsj+'</td>'
                    +'<td>'+e.gjr+'</td>'
                    +'<td>'+e.gjfs+'</td>'
                    +'<td>'+e.lxr+'</td>'
                    +'<td>'+e.gjqk+'</td>'
                    +'</tr>';
                    $('#gjjl_page_table_data').append(h);
                });
                gjjl_page_pages(req.data);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//分页
function gjjl_page_pages(o){
//    当前页
    gjjl_pageNow = o.pageNum;
    $('#gjjl_page_now').text('当前页:'+gjjl_pageNow);
//    总页数
    $('#gjjl_page_pages').text('总页数:'+o.pages);
//    总条数
    $('#gjjl_page_total').text('总条数:'+o.total);

    if(o.hasPreviousPage){
        $('#gjjl_page_first').show();
        $('#gjjl_page_to_first').show();
    }else{
        $('#gjjl_page_first').hide();
        $('#gjjl_page_to_first').hide();
    }
    if(o.hasNextPage){
        $('#gjjl_page_last').show();
        $('#gjjl_page_to_last').show();
    }else{
        $('#gjjl_page_last').hide();
        $('#gjjl_page_to_last').hide();
    }
}
function gjjl_page_query_gjfs(){
    $('#gjjl_page_gjfs').find('option').remove();
    $.ajax({
        url: path+"/data/gjfs/gjfs",
        async: false,
        type: 'get',
        dataType: 'json',
        cache: false,
        success: function (req) {
            if(req.success){
                $('#gjjl_page_gjfs').append("<option value=''>请选择</option>");
                $(req.data).each(function(index,e){
                    $('#gjjl_page_gjfs').append("<option value='"+e.uuid+"'>"+e.mc+"</option>");
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
function gjjl_page_gsr(){
    $('#gjjl_page_gjr').find('option').remove();
    $.ajax({
        url: path+"/data/account/parents/"+$('#home_uuid').text(),
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#gjjl_page_gjr').append('<option value="">请选择</option>');
                $('#gjjl_page_gjr').append('<option value="'+$("#home_uuid").text()+'">自己</option>');
                $(req.data).each(function(index,e){
                    $('#gjjl_page_gjr').append('<option value="'+e.uuid+'">'+e.name+'</option>');
                });
            }else{
                $('#gjjl_page_gjr').append('<option value="">请选择</option>');
                $('#gjjl_page_gjr').append('<option value="'+$("#home_uuid").text()+'">自己</option>');
            }
        },
        error: erryFunction
    });
}