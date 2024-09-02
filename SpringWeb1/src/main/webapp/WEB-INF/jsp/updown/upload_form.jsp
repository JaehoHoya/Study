<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Upload Form</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#btnUpload').on('click', function(event) {
        event.preventDefault();
        
        var form = $('#uploadForm')[0]; // jQuery object to JavaScript standard object
        var data = new FormData(form);
        
        $('#btnUpload').prop('disabled', true);
       
        $.ajax({
            method: "post",
            enctype: 'multipart/form-data',
            url: "/files/upload",
            data: data,
            dataType: 'text',
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                $('#btnUpload').prop('disabled', false);
                alert('success');
            },
            error: function (e) {
                $('#btnUpload').prop('disabled', false);
                alert('fail');
            }
        });
    });
});
</script>
</head>

<body>
<h3>업로드</h3>
<form id="uploadForm" action="/files/upload" method="post" enctype="multipart/form-data">
   제목: <input type="text" name="title" id="title" ><br>
   작성자: <input type="text" name="writer" id="writer"><br>
   파일: <input type="file" name="files" multiple="multiple" id="file"><br>
   내용: <input type="text" name="content" id="content" ><br>
   <button id="btnUpload">Upload</button>
</form>
</body>
</html>
