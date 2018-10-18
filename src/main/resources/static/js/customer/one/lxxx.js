var lxxx_pageNow = 1;
$(document).ready(function() {
    lxxx_page_query();
    //    分页相关
    $('#lxxx_page_first').on('click',function(){
        lxxx_pageNow = Number(lxxx_pageNow) - 1;
        lxxx_page_query();
    });
    $('#lxxx_page_last').on('click',function(){
        lxxx_pageNow = Number(lxxx_pageNow) + 1;
        lxxx_page_query();
    });
    $('#lxxx_page_to_first').on('click',function(){
        lxxx_pageNow = 1;
        lxxx_page_query();
    });
    $('#lxxx_page_to_last').on('click',function(){
        lxxx_pageNow = $('#lxxx_page_pages').text().split(':')[1];
        lxxx_page_query();
    });

    $('#lxxx_add2_form_a').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: false,
        autoclose: true,
        format: "yyyy-mm-dd"
    });
    $('#lxxx_add2_form_b').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: false,
        autoclose: true,
        format: "yyyy-mm-dd"
    });
});
function lxxx_page_query(){
    $('#lxxx_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/lxfs/lxfs/"+Number(lxxx_pageNow)+"/"+$('#qjbl_uuid').val(),
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>'+e.xm+'</td>'
                    +'<td>'+e.xb+'</td>'
                    +'<td>'+e.zw+'</td>'
                    +'<td>'+e.sr+'</td>'
                    +'<td>'+e.dh+'</td>'
                    +'<td>'+e.cz+'</td>'
                    +'<td>'+e.dzyj+'</td>'
                    +'<td>'+e.sjh+'</td>'
                    +'<td>'+e.alww+'</td>'
                    +'<td>'+e.qq+'</td>'
                    +'<td>'+e.dd+'</td>'
                    +'<td>'+e.qtlxfs+'</td>'
                    +'<td>'+e.hf+'</td>'
                    +'<td>'+e.jtcy+'</td>'
                    +'<td>'+e.xg+'</td>'
                    +'<td>'+e.zyrz+'</td>'
                    +'<td><img style="width:30px;height:30px;" src="'+path+"/"+e.imgs+'"/></td>'
                    +'<td><input class="btn btn-primary btn-xs" type="button" value="修改" onclick="lxxx_update_form(\''+e.uuid+'\')"/></td>'
                    +'</tr>';
                    $('#lxxx_page_table_data').append(h);
                });
                lxxx_page_pages(req.data);
            }
        },
        error: erryFunction
    });
}
//分页
function lxxx_page_pages(o){
//    当前页
    lxxx_pageNow = o.pageNum;
    $('#lxxx_page_now').text('当前页:'+lxxx_pageNow);
//    总页数
    $('#lxxx_page_pages').text('总页数:'+o.pages);
//    总条数
    $('#lxxx_page_total').text('总条数:'+o.total);

    if(o.hasPreviousPage){
        $('#lxxx_page_first').show();
        $('#lxxx_page_to_first').show();
    }else{
        $('#lxxx_page_first').hide();
        $('#lxxx_page_to_first').hide();
    }
    if(o.hasNextPage){
        $('#lxxx_page_last').show();
        $('#lxxx_page_to_last').show();
    }else{
        $('#lxxx_page_last').hide();
        $('#lxxx_page_to_last').hide();
    }
}
function lxxx_add(){
    $('#lxxx_add').click();
    $('#lxxx_add2_form_button').show();
    $('#lxxx_update2_form_button').hide();
}
function lxxx_add2_form(){
    $('#lxxx_add2_form_cusid').val($('#qjbl_uuid').val());
    $.ajax({
        url: path+"/data/lxfs/lxfs",
        async: false,
        type: 'post',
        data:$('#lxxx_add2_form').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#lxxx_add').click();
                document.getElementById("lxxx_add2_form").reset();
                lxxx_page_query(1);
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
//修改
var lxxx_update_uuid;
function lxxx_update_form(obj){
    $('#lxxx_add').click();
    lxxx_update_uuid = obj;
    $('#lxxx_add2_form_button').hide();
    $('#lxxx_update2_form_button').show();
    lxxx_update2_form_uuid_data();
}
function lxxx_update2_form_uuid_data(){
    $.ajax({
        url: path+"/data/lxfs/lxfs/"+lxxx_update_uuid,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#lxxx_add2_form_cusid').val(req.data.cusid);
                $('#lxxx_add2_form').find('input[name="xm"]').val(req.data.xm);
                $('#lxxx_add2_form').find('input[name="zw"]').val(req.data.zw);
                $('#lxxx_add2_form').find('input[name="dh"]').val(req.data.dh);
                $('#lxxx_add2_form').find('input[name="dzyj"]').val(req.data.dzyj);
                $('#lxxx_add2_form').find('input[name="alww"]').val(req.data.alww);
                $('#lxxx_add2_form').find('input[name="dd"]').val(req.data.dd);
                $('#lxxx_add2_form').find('input[name="xg"]').val(req.data.xg);
                $('#lxxx_add2_form').find('select[name="xb"]').val(req.data.xb);
                $('#lxxx_add2_form').find('input[name="sr"]').val(req.data.sr);
                $('#lxxx_add2_form').find('input[name="cz"]').val(req.data.cz);
                $('#lxxx_add2_form').find('input[name="sjh"]').val(req.data.sjh);
                $('#lxxx_add2_form').find('input[name="qq"]').val(req.data.qq);
                $('#lxxx_add2_form').find('input[name="qtlxfs"]').val(req.data.qtlxfs);
                $('#lxxx_add2_form').find('input[name="jtcy"]').val(req.data.jtcy);
                $('#lxxx_add2_form').find('input[name="zyrz"]').val(req.data.zyrz);
                $('#lxxx_add2_form').find('select[name="hf"]').val(req.data.hf);
                $('#lxxx_add2_form').find('select[name="imgs"]').val(req.data.imgs);
                $('#lxxx_img_data').html('<img style="width:30px;height:30px;" src="'+path+"/"+req.data.imgs+'"/>');
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
function lxxx_update2_form(){
    $.ajax({
        url: path+"/data/lxfs/lxfs/"+lxxx_update_uuid,
        async: false,
        type: 'put',
        data:$('#lxxx_add2_form').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#lxxx_add').click();
                document.getElementById("lxxx_add2_form").reset();
                lxxx_page_query(1);
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}