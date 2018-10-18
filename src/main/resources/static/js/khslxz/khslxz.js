$(document).ready(function() {
    khslxz_query();
});
function khslxz_query(){
    $.ajax({
        url: path+"/data/khslxz/khslxz",
        async: false,
        type: 'get',
        dataType: 'json',
        cache: false,
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    if(e.lx == "1")
                        $('#khslxz_form').find('input[name="sl"]').val(e.sl);
                });
            }
        },
        error: erryFunction
    });
}
function erryFunction() {
    alert("错误!");
}
function khslxz_add(){
    $.ajax({
        url: path+"/data/khslxz/khslxz",
        async: false,
        type: 'post',
        data:$('#khslxz_form').serialize(),
        dataType: 'json',
        cache: false,
        success: function (req) {
            if(req.success){
                alert(req.message);
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}