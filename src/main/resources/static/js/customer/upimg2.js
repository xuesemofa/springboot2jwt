$(document).ready(function() {
    $('#cus_img_up_file2').on('change',function(){
        if(document.getElementById('cus_img_up_file2').files.length > 0){
            $('#cus_img_up_yxzts2').text(document.getElementById('cus_img_up_file2').files[0].name);
        }
    });
});
function cus_img_up_file_add2(){
    if(document.getElementById('cus_img_up_file2').files.length > 0){

        var formData = new FormData();
        formData.append('files', $("#cus_img_up_file2")[0].files[0]);

        $.ajax({
            url: path+"/data/customer/upimg",
            async: false,
            type: 'post',
            data: formData,
            dataType: 'json',
            timeout: 1000,
            cache: false,
            processData:false,  //tell jQuery not to process the data
            contentType: false,  //tell jQuery not to set contentType
            success: function (req) {
                if(req.success){
                    $('#cus_img_data2').append('<img style="width:30px;height:30px;" ondblclick="cus_img_img(this)" src="'+path+"/"+req.data+'"/>');
                    $('#cus_img_up_file2').val(null);
                    $('#cus_img_up_yxzts2').text('未选择图片');
                }else{
                    alert(req.message);
                }
            },
            error: erryFunction
        });
    }else
        alert('请先选择文件');
}
//双击显示大图
function cus_img_img2(obj){
    $('#cus_img_img12').click();
    $('#cus_img_img2').attr('src',$(obj).attr('src'));
}