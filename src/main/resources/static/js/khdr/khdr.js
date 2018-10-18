$(document).ready(function() {
    $('#khdr_file').on('change',function(){
        if(document.getElementById('khdr_file').files.length > 0){
            $('#khdr_yxzts').text(document.getElementById('khdr_file').files[0].name);
        }
    });
});
function khdr_file_add(){
    if(document.getElementById('khdr_file').files.length > 0){

        var formData = new FormData();
        formData.append('files', $("#khdr_file")[0].files[0]);

        $.ajax({
            url: path+"/data/khdr/khdr",
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
                    alert(req.message);
                }else{
                    alert(req.message);
                }
            },
            error: erryFunction
        });
    }else
        alert('请先选择文件');
}
function erryFunction() {
    alert("错误!");
}