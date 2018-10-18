$(document).ready(function() {
    $('#lxxx_img_up_file').on('change',function(){
        if(document.getElementById('lxxx_img_up_file').files.length > 0){
            $('#lxxx_img_up_yxzts').text(document.getElementById('lxxx_img_up_file').files[0].name);
        }
    });
});
function lxxx_img_up_file_add(){
    if(document.getElementById('lxxx_img_up_file').files.length > 0){

        var formData = new FormData();
        formData.append('files', $("#lxxx_img_up_file")[0].files[0]);

        $.ajax({
            url: path+"/data/lxfs/upimg",
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
                    $('#lxxx_img_data').html('<img style="width:30px;height:30px;" ondblclick="lxxx_img_img(this)" src="'+path+"/"+req.data+'"/>');
                    $('#lxxx_img_data2').val(req.data);
                    $('#lxxx_img_up_file').val(null);
                    $('#lxxx_img_up_yxzts').text('未选择图片');
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
function lxxx_img_img(obj){
    $('#lxxx_img_img1').click();
    $('#lxxx_img_img').attr('src',$(obj).attr('src'));
}