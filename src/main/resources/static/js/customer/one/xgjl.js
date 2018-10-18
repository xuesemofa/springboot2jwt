var xgjl_pageNow = 1;
$(document).ready(function() {
    xgjl_page_query();
    //    分页相关
    $('#xgjl_page_first').on('click',function(){
        xgjl_pageNow = Number(xgjl_pageNow) - 1;
        xgjl_page_query();
    });
    $('#xgjl_page_last').on('click',function(){
        xgjl_pageNow = Number(xgjl_pageNow) + 1;
        xgjl_page_query();
    });
    $('#xgjl_page_to_first').on('click',function(){
        xgjl_pageNow = 1;
        xgjl_page_query();
    });
    $('#xgjl_page_to_last').on('click',function(){
        xgjl_pageNow = $('#xgjl_page_pages').text().split(':')[1];
        xgjl_page_query();
    });
});
function xgjl_page_query(){
    $('#xgjl_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/customer/customer/xgjl3/"+Number(xgjl_pageNow)+"/"+$('#qjbl_uuid').val(),
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>'+e.backsystimes+'</td>'
                    +'<td>'+e.xgr+'</td>'
                    +'<td>'+e.yz+'</td>'
                    +'<td>'+e.xz+'</td>'
                    +'</tr>';
                    $('#xgjl_page_table_data').append(h);
                });
                xgjl_page_pages(req.data);
            }
        },
        error: erryFunction
    });
}
//分页
function xgjl_page_pages(o){
//    当前页
    xgjl_pageNow = o.pageNum;
    $('#xgjl_page_now').text('当前页:'+xgjl_pageNow);
//    总页数
    $('#xgjl_page_pages').text('总页数:'+o.pages);
//    总条数
    $('#xgjl_page_total').text('总条数:'+o.total);

    if(o.hasPreviousPage){
        $('#xgjl_page_first').show();
        $('#xgjl_page_to_first').show();
    }else{
        $('#xgjl_page_first').hide();
        $('#xgjl_page_to_first').hide();
    }
    if(o.hasNextPage){
        $('#xgjl_page_last').show();
        $('#xgjl_page_to_last').show();
    }else{
        $('#xgjl_page_last').hide();
        $('#xgjl_page_to_last').hide();
    }
}
//查看详情
function xgjl_ckxq(obj){
    $('#xgjl_ckxq').click();
    $.ajax({
        url: path+"/data/customer/customer/xgjl/"+obj,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#cus_one_xq_a12').find('td').eq(3).html('<font style="color:#0055ff;">'+req.data.system+'</font>');
                var bh = req.data.bh;
                var b = bh.toString();
                var b = 6 - b.length;
                for(var i = 0;i < b;i++){
                    bh = '0'+bh;
                }
                $('#cus_one_xq_a12').find('td').eq(5).html('<font style="color:#0055ff;">'+bh+'</font>');

                $('#cus_one_xq_a2').find('td').eq(1).html('<font style="color:#0055ff;">'+req.data.gsmc+'</font>');
                $('#cus_one_xq_a2').find('td').eq(3).html('<font style="color:#0055ff;">'+req.data.xsdz+'</font>');
                $('#cus_one_xq_a2').find('td').eq(5).html('<font style="color:#0055ff;">'+req.data.khly+'</font>');
                $('#cus_one_xq_a2').find('td').eq(7).html('<font style="color:#0055ff;">'+req.data.khdj+'</font>');
                $('#cus_one_xq_a2').find('td').eq(9).html('<font style="color:#0055ff;">'+req.data.clsj+'</font>');
                $('#cus_one_xq_a2').find('td').eq(11).html('<font style="color:#0055ff;">'+req.data.zycp+'</font>');

                $('#cus_one_xq_b2').find('td').eq(1).html('<font style="color:#0055ff;">'+req.data.xsqy+'</font>');
                $('#cus_one_xq_b2').find('td').eq(3).html('<font style="color:#0055ff;">'+req.data.ygrs+'</font>');
                $('#cus_one_xq_b2').find('td').eq(5).html('<font style="color:#0055ff;">'+req.data.xsqd+'</font>');
                $('#cus_one_xq_b2').find('td').eq(7).html('<font style="color:#0055ff;">'+req.data.sf+'</font>');
                $('#cus_one_xq_b2').find('td').eq(9).html('<font style="color:#0055ff;">'+req.data.dq+'</font>');
                $('#cus_one_xq_b2').find('td').eq(11).html('<font style="color:#0055ff;">'+req.data.cs+'</font>');

                $('#cus_one_xq_c2').find('td').eq(1).html('<font style="color:#0055ff;">'+req.data.qylx+'</font>');
                $('#cus_one_xq_c2').find('td').eq(3).html('<font style="color:#0055ff;">'+req.data.pp+'</font>');
                $('#cus_one_xq_c2').find('td').eq(5).html('<font style="color:#0055ff;">'+req.data.wz+'</font>');
                $('#cus_one_xq_c2').find('td').eq(7).html('<font style="color:#0055ff;">'+req.data.ncke+'</font>');
                $('#cus_one_xq_c2').find('td').eq(9).html('<font style="color:#0055ff;">'+req.data.ndde+'</font>');
                $('#cus_one_xq_c2').find('td').eq(11).html('<font style="color:#0055ff;">'+req.data.sydjh+'</font>');
//
                $('#cus_one_xq_d2').find('td').eq(1).html('<font style="color:#0055ff;">'+req.data.cw_mc+'</font>');
                $('#cus_one_xq_d2').find('td').eq(3).html('<font style="color:#0055ff;">'+req.data.cw_nsrsbh+'</font>');
                $('#cus_one_xq_d2').find('td').eq(5).html('<font style="color:#0055ff;">'+req.data.cw_dz+'</font>');
                $('#cus_one_xq_d2').find('td').eq(7).html('<font style="color:#0055ff;">'+req.data.cw_dh+'</font>');
                $('#cus_one_xq_d2').find('td').eq(9).html('<font style="color:#0055ff;">'+req.data.cw_khh+'</font>');
                $('#cus_one_xq_d2').find('td').eq(11).html('<font style="color:#0055ff;">'+req.data.cw_zh+'</font>');
            }
        },
        error: erryFunction
    });
}