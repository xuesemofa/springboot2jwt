var ctcx2_pageNow = 1;
$(document).ready(function() {
});
function ctcx22_page_query(pageNow){
    var gsmc = $('#ctcx2_ctcx_query').find('input[name="gsmc"]').val();
    if(gsmc == ''){
        alert('请输入公司名称');
        return false;
    }
    ctcx2_pageNow = pageNow;
//    $('#ctcx2_page_table_data').find('tr').remove();
    $('#ctcx2_page_table_data').find('div').remove();
    $.ajax({
        url: path+"/data/customer/customer5/"+pageNow,
        async: false,
        type: 'get',
        data: $('#ctcx2_ctcx_query').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var s = '<input class="btn btn-primary btn-xs" type="button" value="移入私海" onclick="customer_ctcx_query_db_gh(\''+e.uuid+'\')"/>';
                    if(e.gsr != '0')
                        s = '';
                    var h = '<div class="row">'
                            +'     <div class="hr-line-dashed"></div>'
                            +'     <div class="search-result">'
                            +'         <h3>'
                            +'             <a>'+e.gsmc+'</a>&nbsp;&nbsp;&nbsp;<font color="red">'+((e.gsr == '0' || e.gsr == '' || e.gsr == null) ? '公海' : e.gsr)+'&nbsp;&nbsp;&nbsp;'+s+'</font>'
                            +'         </h3>'
                            +'         <a class="search-link">'+e.sf+'&nbsp;&nbsp;'+e.dq+'&nbsp;&nbsp;'+e.cs+'&nbsp;&nbsp;'+e.khly+'&nbsp;&nbsp;'+e.system+'</a>'
                            +'         <p>'+e.xsdz+'</p>'
                            +'     </div>'
                            +'</div>';
                      $('#ctcx2_page_table_data').append(h);
//                    var s = '<input class="btn btn-primary btn-xs" type="button" value="移入私海" onclick="customer_ctcx_query_db_gh(\''+e.uuid+'\')"/>';
//                    if(e.gsr != '0')
//                        s = '';
//                    $('#ctcx2_page_table_data').append('<tr>'
//                    +'<td>'+(index+1)+'</td>'
//                    +'<td>'+e.gsmc+'</td>'
//                    +'<td>'+e.xsdz+'</td>'
//                    +'<td>'+((e.gsr == '0' || e.gsr == '' || e.gsr == null) ? '公海' : e.gsr)+'</td>'
//                    +'<td>'+s+'</td>'
//                    +'</tr>');
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//分页
function ctcx2_page_pages(o){

    if(Number(o) == 0)
        ctcx2_pageNow = Number(ctcx2_pageNow) - 1;
    if(Number(o) == 1)
        ctcx2_pageNow = Number(ctcx2_pageNow) + 1;

    if(Number(ctcx2_pageNow) < 1)
        ctcx2_pageNow = 1;

    $('#ctcx2_page_now').text('当前页数:'+ctcx2_pageNow);

    if(Number(ctcx2_pageNow) == 0)
        $('#ctcx2_page_first').hide();
    else
        $('#ctcx2_page_first').show();

    ctcx22_page_query(ctcx2_pageNow);
}
//移入私海
function customer_ctcx_query_db_gh(obj){
    var r=confirm("是否确定移入私海?");
    if(r){
        $.ajax({
            url: path+"/data/customer/customer/gsr",
            async: false,
            type: 'put',
            data:{"uuid":obj,"gsr":"1"},
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    ctcx22_page_query(0);
                }else{
                    alert(req.message);
                }
            },
            error: erryFunction
        });
    }
}