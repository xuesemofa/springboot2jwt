var gjjl_pageNow = 1;
$(document).ready(function() {
    gjjl_page_query();
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

    $('#gjjl_add2_form_a').datepicker({
//        defaultDate: $('.ui_timepicker').val(),
        dateFormat: "yy-mm-dd",
        showSecond: true,
        timeFormat: 'hh:mm:ss',
        stepHour: 1,
        stepMinute: 1,
        stepSecond: 1
    });
});
function gjjl_page_query(){
    $('#gjjl_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/gjjl/gjjl/"+Number(gjjl_pageNow),
        async: false,
        type: 'get',
        data:{"cusid":$('#qjbl_uuid').val()},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>'+e.gjsj+'</td>'
                    +'<td>'+e.gjr+'</td>'
                    +'<td>'+e.gjfs+'</td>'
                    +'<td>'+e.lxr+'</td>'
                    +'<td>'+e.gjqk+'</td>'
                    +'</tr>';
                    $('#gjjl_page_table_data').append(h);
                });
                gjjl_page_pages(req.data);
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
function gjjl_add(){
    $('#gjjl_add').click();
    $('#gjjl_add2_form_cusid').val($('#qjbl_uuid').val());
    gjjl_find_gjfs();
    gjjl_find_lxxx();
}
function gjjl_add2_form(){
    $('#gjjl_add2_form_cusid').val($('#qjbl_uuid').val());
    $.ajax({
        url: path+"/data/gjjl/gjjl",
        async: false,
        type: 'post',
        data:$('#gjjl_add2_form').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#gjjl_add').click();
                document.getElementById("gjjl_add2_form").reset();
                gjjl_page_query(1);
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
//获取跟进方式
function gjjl_find_gjfs(){
    $('#gjjl_add2_form_gjfs').find('option').remove();
    $('#gjjl_add2_form_gjfs').append('<option value="">请选择</option>');
    $.ajax({
        url: path+"/data/gjfs/gjfs",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    $('#gjjl_add2_form_gjfs').append('<option value="'+e.uuid+'">'+e.mc+'</option>');
                });
            }
        },
        error: erryFunction
    });
}
//联系信息
function gjjl_find_lxxx(){
    $('#gjjl_add2_form_lxr').find('option').remove();
    $('#gjjl_add2_form_lxr').append('<option value="">请选择</option>');
    $.ajax({
        url: path+"/data/lxfs/lxfs2/"+$('#qjbl_uuid').val(),
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    $('#gjjl_add2_form_lxr').append('<option value="'+e.uuid+'">'+e.xm+'</option>');
                });
            }
        },
        error: erryFunction
    });
}