var account_pageNow = 1;
$(document).ready(function() {
    account_page_query(account_pageNow);
});
function account_page_query(pageNow){
    account_pageNow = pageNow;
    $('#account_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/account/account/"+pageNow,
        async: false,
        type: 'get',
        data: $('#account_page_query').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    var s = e.isLogin == 'Y' ?
                     '&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="禁止登录" onclick="account_page_query_jzdl(\''+e.uuid+'\',\'N\')"/>'
                    :'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" value="允许登录" onclick="account_page_query_jzdl(\''+e.uuid+'\',\'Y\')"/>';
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>'+e.account+'</td>'
                    +'<td>'+e.name+'</td>'
                    +'<td>'+e.phone+'</td>'
                    +'<td><input class="btn btn-primary btn-xs" type="button" value="删除" onclick="account_page_query_delete(\''+e.uuid+'\')"/>'
                    + s
                    +'</td></tr>';
                    $('#account_page_table_data').append(h);
                });
//                account_page_pages(req.data);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//分页
function account_page_pages(o){

    if(Number(o) == 0)
        account_pageNow = Number(account_pageNow) - 1;
    if(Number(o) == 1)
        account_pageNow = Number(account_pageNow) + 1;

    if(Number(account_pageNow) < 1)
        account_pageNow = 1;

    $('#account_page_now').text('当前页数:'+account_pageNow);

    if(Number(account_pageNow) == 0)
        $('#account_page_first').hide();
    else
        $('#account_page_first').show();

    account_page_query(account_pageNow);
}
function account_page_query_delete(obj){
    var r=confirm("是否确定删除!");
    if(r){
        $.ajax({
            url: path+"/data/account/account/"+obj,
            async: false,
            type: 'delete',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    account_page_query(account_pageNow);
                }else{
                    alert(req.message);
                }
            },
            error: erryFunction
        });
    }
}
function account_page_query_jzdl(obj,isLogin){
    var r=confirm("是否确定更改登录状态!");
    if(r){
        $.ajax({
            url: path+"/data/account/isLogin",
            async: false,
            type: 'put',
            data:{"uuid":obj,"isLogin":isLogin},
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    account_page_query(account_pageNow);
                }else{
                    alert(req.message);
                }
            },
            error: erryFunction
        });
    }
}
function erryFunction() {
    alert("错误!");
}