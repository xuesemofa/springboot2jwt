var yrghjl_pageNow = 1;
$(document).ready(function() {
    yrghjl_page_query(yrghjl_pageNow);
});
function yrghjl_page_query(pageNow){
    yrghjl_pageNow = pageNow;
    $('#yrghjl_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/yrghjl/yrghjl/"+pageNow,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>'+e.czr+'</td>'
                    +'<td>'+e.ygsr+'</td>'
                    +'<td>'+e.systimes+'</td>'
                    +'<td>'+(e.ddhgsr == '0' ? '公海' : e.ddhgsr)+'</td>'
                    +'<td>'+e.bdddkh+'</td>'
                    +'<td>'+e.ddyy+'</td>'
                    +'</tr>';
                    $('#yrghjl_page_table_data').append(h);
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//分页
function yrghjl_page_pages(o){

    if(Number(o) == 0)
        yrghjl_pageNow = Number(yrghjl_pageNow) - 1;
    if(Number(o) == 1)
        yrghjl_pageNow = Number(yrghjl_pageNow) + 1;

    if(Number(yrghjl_pageNow) < 1)
        yrghjl_pageNow = 1;

    $('#yrghjl_page_now').text('当前页数:'+yrghjl_pageNow);

    if(Number(yrghjl_pageNow) == 0)
        $('#yrghjl_page_first').hide();
    else
        $('#yrghjl_page_first').show();

    yrghjl_page_query(yrghjl_pageNow);
}
//移入公海
function yrghjl_page_query_db_gh(obj){
    var r=confirm("是否确定移入公海?");
    if(r){
        $('#yrghjl_model_button2').click();
        $('#yrghjl_model_a3').val(obj);
    }
}
function yrghjl_page_query_db_gh22(){
    $.ajax({
        url: path+"/data/customer/customer/gsr",
        async: false,
        type: 'put',
        data:{"uuid":$('#yrghjl_model_a3').val(),"gsr":"0","bz":$('#yrghjl_model_a2').val()},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                yrghjl_page_query(0);
                $('#yrghjl_model_button2').click();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//查看详情
function yrghjl_page_query_query_xq2(obj){
    $('#yrghjl_model_button').click();
    yrghjl_page_query_query_xq(obj);
}
function yrghjl_page_query_query_xq(obj){
    $.ajax({
        url: path+"/data/customer/customer/id2/"+obj,
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $('#yrghjl_model_a').find('span').eq(0).text(req.data.gsmc);
                $('#yrghjl_model_a').find('span').eq(1).text(req.data.xsdz);
                $('#yrghjl_model_a').find('span').eq(2).text(req.data.khly);
                $('#yrghjl_model_a').find('span').eq(3).text(req.data.khdj);
                $('#yrghjl_model_a').find('span').eq(4).text(req.data.clsj);
                $('#yrghjl_model_a').find('span').eq(5).text(req.data.zycp);
                $('#yrghjl_model_a').find('span').eq(6).text(req.data.xsqy);
                $('#yrghjl_model_a').find('span').eq(7).text(req.data.ygrs);
                $('#yrghjl_model_a').find('span').eq(8).text(req.data.xsqd);

                $('#yrghjl_model_b').find('span').eq(0).text(req.data.sf);
                $('#yrghjl_model_b').find('span').eq(1).text(req.data.dq);
                $('#yrghjl_model_b').find('span').eq(2).text(req.data.cs);
                $('#yrghjl_model_b').find('span').eq(3).text(req.data.qylx);
                $('#yrghjl_model_b').find('span').eq(4).text(req.data.pp);
                $('#yrghjl_model_b').find('span').eq(5).text(req.data.wz);
                $('#yrghjl_model_b').find('span').eq(6).text(req.data.ncke);
                $('#yrghjl_model_b').find('span').eq(7).text(req.data.ndde);
                $('#yrghjl_model_b').find('span').eq(8).text(req.data.sydjh);

                $('#yrghjl_model_c').find('span').eq(0).text(req.data.cw_mc);
                $('#yrghjl_model_c').find('span').eq(1).text(req.data.cw_nsrsbh);
                $('#yrghjl_model_c').find('span').eq(2).text(req.data.cw_dz);
                $('#yrghjl_model_c').find('span').eq(3).text(req.data.cw_dh);
                $('#yrghjl_model_c').find('span').eq(4).text(req.data.cw_khh);
                $('#yrghjl_model_c').find('span').eq(5).text(req.data.cw_zh);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}