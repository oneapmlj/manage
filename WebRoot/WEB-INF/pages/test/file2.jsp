<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>OneAPM</title>
   	 <script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
     <script type="text/javascript" src="${applicationScope.staticPath}skin/js/ajaxfileupload.js"></script>
     <script type="text/javascript">
	    function upload(){
	        $.ajaxFileUpload (
			{	url:'file_upload.action',//用于文件上传的服务器端请求地址
	                secureuri:false,//一般设置为false
	                fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
	                dataType: 'json',//返回值类型 一般设置为json
	                success: function (da){
	                   if(da.status == 1){
	                	   alert("成功");
	                   }else{
	                	   alert(da.msg);
	                   }
	                },
	            }
	        )
	    }
	    function download(){
	        location.href="file_download.action?filePath="+"file/skdnfkdshfkdshnks324yinsdk";
	    }
    </script>
    </head>
    <body>
        <!-- <img src="loading.gif" id="loading" style="display: none;"> -->
        <input type="file" id="file" name="file" />
        <br />
        <input type="button" value="上传" onclick="upload();">

        <input type="button" value="下载" onclick="download();"/>
    </body>
</html>	