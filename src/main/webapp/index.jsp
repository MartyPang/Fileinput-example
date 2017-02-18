<%@ include file="taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>你好</title>
    <!--BootStrap-->
    <link rel="stylesheet" href="${ctx}/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script src="${ctx}/assets/bootstrap-3.3.7-dist/js/jquery-3.1.1.js"></script>
    <script src="${ctx}/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <!--BootStrap-loading-->
    <link rel="stylesheet" href="${ctx}/assets/bootstrap-loading/dist/ladda-themeless.min.css">
    <script src="${ctx}/assets/bootstrap-loading/dist/spin.min.js"></script>
    <script src="${ctx}/assets/bootstrap-loading/dist/ladda.min.js"></script>

    <!--fileinput-->
    <link href="${ctx}/assets/file-input/css/fileinput.css" rel="stylesheet">
    <script src="${ctx}/assets/file-input/js/fileinput.js"></script>
    <script src="${ctx}/assets/file-input/js/locales/zh.js"></script>

    <!--jquery-confirm-->
    <link href="${ctx}/assets/jquery-confirm/css/jquery-confirm.css" rel="stylesheet">
    <script src="${ctx}/assets/jquery-confirm/js/jquery-confirm.js"></script>

    <script type="text/javascript">
        Ladda.bind( 'input[type=submit]' );

        function upload() {
            $("#input_upload").fileinput('refresh');
        }
    </script>
</head>
<body>
<div class="container">
    <div class="panel panel-default" style="min-height: 700px;">
        <div class="panel-body" style="padding: 25px;">
            <div>
                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modal_upload"
                        onclick="upload()">
                    <span class="glyphicon glyphicon-open"></span>&nbsp;&nbsp;upload
                </button>
            </div>
            <h4 id="label_dir_path" style="display: none">/</h4>
        </div>
    </div>
</div>
<!--文件上传的modal-->
<div class="modal fade modal_upload" id="modal_upload" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Upload</h4>
            </div>
            <div class="modal-body">
                <input id="input_upload" name="uploadFile" type="file" multiple>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $("#input_upload").fileinput({
        language: 'en',
        uploadUrl: "${ctx}/upload?type=text",
        showCaption: true,
        browseClass: "btn btn-info",
        allowedPreviewTypes: ['text'],
        allowedFileExtensions: ['txt','doc','docx','xls','xlsx','ppt','pptx']
    });
</script>
</body>

</html>
