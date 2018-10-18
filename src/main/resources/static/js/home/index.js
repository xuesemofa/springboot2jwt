var home_customer_pageNow = 1;
var home_customer_pageNow_hz = 1;
$(document).ready(function() {
    $('#side-menu').show();
    getZw();
//    长时间未拜访的非合作客户
    if(document.getElementById("home_customer_page_table_data")){
        home_customer_page_query();
        //    分页相关
        $('#home_customer_page_first').on('click',function(){
            home_customer_pageNow = Number(home_customer_pageNow) - 1;
            home_customer_page_query();
        });
        $('#home_customer_page_last').on('click',function(){
            home_customer_pageNow = Number(home_customer_pageNow) + 1;
            home_customer_page_query();
        });
        $('#home_customer_page_to_first').on('click',function(){
            home_customer_pageNow = 1;
            home_customer_page_query();
        });
        $('#home_customer_page_to_last').on('click',function(){
            home_customer_pageNow = $('#home_customer_page_pages').text().split(':')[1];
            home_customer_page_query();
        });
    }
//    长时间未拜访的合作客户
    if(document.getElementById("home_customer_page_table_data_hz")){
        home_customer_page_query_hz();
        //    分页相关
        $('#home_customer_page_first_hz').on('click',function(){
            home_customer_pageNow_hz = Number(home_customer_pageNow_hz) - 1;
            home_customer_page_query_hz();
        });
        $('#home_customer_page_last_hz').on('click',function(){
            home_customer_pageNow_hz = Number(home_customer_pageNow_hz) + 1;
            home_customer_page_query_hz();
        });
        $('#home_customer_page_to_first_hz').on('click',function(){
            home_customer_pageNow_hz = 1;
            home_customer_page_query_hz();
        });
        $('#home_customer_page_to_last_hz').on('click',function(){
            home_customer_pageNow_hz = $('#home_customer_page_pages_hz').text().split(':')[1];
            home_customer_page_query_hz();
        });
    }
    message2();
});
//<!--跳转页面-->
function cli(a,obj){
    var o = obj;
    $.ajax({
        url:path+'/views'+a,
        cache:false,
        success:function(data){

            if(o.indexOf("客户新增") != -1 || o.indexOf("客户管理") != -1 || o.indexOf("公海查询") != -1 || o.indexOf("客户调拨") != -1){
                var D = 0;
                $('#nav_label_title').find('li').each(function(i,f){
                    var ff = $(f).find('a').text();
                    if(ff.indexOf("客户新增") != -1 || ff.indexOf("客户管理") != -1 || ff.indexOf("公海查询") != -1 || ff.indexOf("客户调拨") != -1){
                        alert('因\'客户新增/客户管理/公海查询/客户调拨\'页面存在兼容问题，所以以上页面只允许同时打开其一');
                        D = 1;
                        return false;
                    }
                });
                if(D > 0)
                    return false;
            }

//        添加标签
            var L = 0;
            $('#nav_label_title').find('a').each(function(index,e){
                if($(e).attr('href') == ('#'+o)){
                    L = 1;
                    $(e).trigger('click');
                    return false;
                }
            });
            var i = '#'+o;
            if(L == 0){
                $('#nav_label_title').find('a').each(function(index,e){
                    $(e).find("span").trigger('click');
                });
                $('#nav_label_body').append('<div class="tab-pane row content" id="'+o+'"></div>');
                $(i).html(data);
                $('#nav_label_title').append('<li><a href="#'+o+'" data-toggle="tab" style="float:left;">'
                +o
                +'&nbsp;<span onclick="tab_close(this)"><i class="glyphicon glyphicon-remove fa fa-times"></i></span>'
                +'</a></li>')
                .find('a').trigger('click');
//                var g = $('#nav_label_title').find('li').length;
//                    if(g <= 11){
//                        $('#nav_label_body').append('<div class="tab-pane row content" id="'+o+'"></div>');
//                        $(i).html(data);
//                        $('#nav_label_title').append('<li><a href="#'+o+'" data-toggle="tab" style="float:left;">'
//                        +o
//                        +'&nbsp;<span onclick="tab_close(this)"><i class="glyphicon glyphicon-remove fa fa-times"></i></span>'
//                        +'</a></li>')
//                        .find('a').trigger('click');
//                    }else
//                        alert('当前活动窗口过多,请先关闭一些!');
                return false;
            }
        }
    }).done(function(d){
    }).fail(function() {
        alert('维护中');
    })
    .always(function() {
    });
}
function cli2(a,o){
    var ht = '#'+o;
    $.ajax({
        url:path+'/views'+a,
        cache:false,
        success:function(data){
            $(ht).html(data);
            return false;
        }
    }).done(function(d){
    }).fail(function() {
        alert('维护中');
    })
    .always(function() {
    });
}
function tab_close(obj){
    var i = $(obj).parent('a').attr('href');
    $(obj).parents('li').prev('li').find('a').trigger('click');
    $(obj).parents('li').remove();
    $(i).remove();
    return false;
}

function getZw(){
    $.ajax({
        url:path+'/data/home/zw',
        cache:false,
        beforeSend:function(XMLHttpRequest){
            XMLHttpRequest.setRequestHeader("LTokenD","");
        },
        success:function(req){
            if(req.success){
                $('#home_zw').html(req.data.mc);
                $('body').show();
            }else
                window.location.href="/views/login/login";
        }
    });
}
//全员公告
function message2(){
    $.ajax({
        url:path+'/data/qygg/qygg',
        cache:false,
        success:function(req){
            if(req.success)
                $('#message2').html(req.data.bodys);
        }
    });
}

function erryFunction() {
    alert("错误!");
}
//长时间未拜访客户(非合作客户)
function home_customer_page_query(){
    $('#home_customer_page_table_data').find('tr').remove();
    $.ajax({
        url: path+"/data/customer/home/customer/"+Number(home_customer_pageNow),
        async: false,
        type: 'get',
        dataType: 'json',
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var f = '#ff9900';
                    if(Number(e.flag) < 4)
                        f = '#ff0000';
                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>&nbsp;<font style="color:'+f+'">['+e.flag+']&nbsp;&nbsp;天后强制移入公海</font></td>'
                    +'<td>'+e.gsmc+'</td>'
                    +'<td>'+e.sf+'</td>'
                    +'<td>'+e.dq+'</td>'
                    +'<td>'+e.cs+'</td>'
                    +'<td>'+e.xsdz+'</td>'
                    +'<td>'+e.khly+'</td>'
//                    +'<td>'+e.khdj+'</td>'
//                    +'<td>'
//                    +'<input class="btn btn-primary btn-xs" type="button" onclick="customer_page_query_query_xq222(\''+e.uuid+'\')" value="修改"/>'
//                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" onclick="customer_page_query_db_gh(\''+e.uuid+'\')" value="移入公海"/>'
//                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" onclick="customer_page_query_query_xq2(\''+e.uuid+'\',\''+e.gsmc+'\')" value="查看详情"/>'
//                    +'</td>'
                    +'</tr>';
                    $('#home_customer_page_table_data').append(h);
                });
                home_customer_page_pages(req.data);
            }
        },
        error: erryFunction
    });
}
//分页
function home_customer_page_pages(o){
//    当前页
    home_customer_pageNow = o.pageNum;
    $('#home_customer_page_now').text('当前页:'+home_customer_pageNow);
//    总页数
    $('#home_customer_page_pages').text('总页数:'+o.pages);
//    总条数
    $('#home_customer_page_total').text('总条数:'+o.total);

    if(o.hasPreviousPage){
        $('#home_customer_page_first').show();
        $('#home_customer_page_to_first').show();
    }else{
        $('#home_customer_page_first').hide();
        $('#home_customer_page_to_first').hide();
    }
    if(o.hasNextPage){
        $('#home_customer_page_last').show();
        $('#home_customer_page_to_last').show();
    }else{
        $('#home_customer_page_last').hide();
        $('#home_customer_page_to_last').hide();
    }
}
function home_cus_sx(){
    home_customer_page_query(1);
    alert('已刷新');
}
//长时间未拜访客户(合作客户)
function home_customer_page_query_hz(){
    $('#home_customer_page_table_data_hz').find('tr').remove();
    $.ajax({
        url: path+"/data/customer/home/customer/hz/"+Number(home_customer_pageNow_hz),
        async: false,
        type: 'get',
        dataType: 'json',
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var f = '#ff9900';
                    if(Number(e.flag) < 4)
                        f = '#ff0000';

                    var h = '<tr><td>'+(index+1)+'</td>'
                    +'<td>&nbsp;<font style="color:'+f+'">['+e.flag+']&nbsp;&nbsp;天未拜访</font></td>'
                    +'<td>'+e.gsmc+'</td>'
                    +'<td>'+e.sf+'</td>'
                    +'<td>'+e.dq+'</td>'
                    +'<td>'+e.cs+'</td>'
                    +'<td>'+e.xsdz+'</td>'
                    +'<td>'+e.khly+'</td>'
//                    +'<td>'+e.khdj+'</td>'
//                    +'<td>'
//                    +'<input class="btn btn-primary btn-xs" type="button" onclick="customer_page_query_query_xq222(\''+e.uuid+'\')" value="修改"/>'
//                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" onclick="customer_page_query_db_gh(\''+e.uuid+'\')" value="移入公海"/>'
//                    +'&nbsp;&nbsp;<input class="btn btn-primary btn-xs" type="button" onclick="customer_page_query_query_xq2(\''+e.uuid+'\',\''+e.gsmc+'\')" value="查看详情"/>'
//                    +'</td>'
                    +'</tr>';
                    $('#home_customer_page_table_data_hz').append(h);
                });
                home_customer_page_pages_hz(req.data);
            }
        },
        error: erryFunction
    });
}
//分页
function home_customer_page_pages_hz(o){
//    当前页
    home_customer_pageNow_hz = o.pageNum;
    $('#home_customer_page_now_hz').text('当前页:'+home_customer_pageNow_hz);
//    总页数
    $('#home_customer_page_pages_hz').text('总页数:'+o.pages);
//    总条数
    $('#home_customer_page_total_hz').text('总条数:'+o.total);

    if(o.hasPreviousPage){
        $('#home_customer_page_first_hz').show();
        $('#home_customer_page_to_first_hz').show();
    }else{
        $('#home_customer_page_first_hz').hide();
        $('#home_customer_page_to_first_hz').hide();
    }
    if(o.hasNextPage){
        $('#home_customer_page_last_hz').show();
        $('#home_customer_page_to_last_hz').show();
    }else{
        $('#home_customer_page_last_hz').hide();
        $('#home_customer_page_to_last_hz').hide();
    }
}
function home_cus_sx_hz(){
    home_customer_page_query_hz(1);
    alert('已刷新');
}