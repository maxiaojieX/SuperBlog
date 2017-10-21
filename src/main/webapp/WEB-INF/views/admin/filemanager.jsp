<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>我的云盘</title>
    <link rel="stylesheet" href="/static/webupload/webuploader.css" />
     <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<br>
		<div id="uploader" class="wu-example">
		    <!--用来存放文件信息-->
		    <div id="thelist" class="uploader-list"></div>
		    <div class="btns">
		        <div id="picker">选择文件</div>
		        <div id="list"></div>
		        <button id="ctlBtn" class="btn btn-default">开始上传</button>
		    </div>
		</div>
		<hr>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>全部文件 - 下载</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${filenamelist }">
				<tr>
					<td><a href="/admin/filedownload?filename=${item}" class="down">${item }</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
	</div>

    <script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="/static/webupload/webuploader.js"></script>
    <script>
    	$(function(){
    		var uploader = WebUploader.create({
    	        // swf文件路径
    	        swf: '/static/webupload/Uploader.swf',
    	        // 文件接收服务端。
    	        server: '/admin/fileupload',
    	        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    	        pick: '#picker',
    	        fileVal: 'file',
    	    });
    		
    		// 当有文件添加进来的时候
    		uploader.on( 'fileQueued', function( file ) {
    			var li = "<p id="+ file.id+" class='bg-success'>"+ file.name +"<span></span></p>";
    		    $("#list").append(li);
    		    
    		    
    		    uploader.makeThumb( file, function( error, src ) {
    		    	var img = "<img src="+src+">";
    		    	$("#list").append(img);
    		        
    		    }, 100, 100 );
    		});
    		//进度条-上传中
    		uploader.on('uploadProgress',function(file,percentage){
    			$("#"+file.id).find("span").html("<div class='progress'><div class='progress-bar' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: "+parseInt(percentage*100)+"%;'></div></div>");
    		});
    		//上传成功-参数json可以接收服务器返回的json,在这里没做处理
    		uploader.on('uploadSuccess',function(file,json){
    			$("#"+file.id).find("span").html("<div class='progress'><div class='progress-bar progress-bar-info' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: 100%;'>上传成功！</div></div>");
    			if(json.state == "success"){
    				window.history.go(0);
    			}
    		});
    		//上传失败
    		uploader.on('uploadError',function(file){
    			$("#"+file.id).find("span").html("<div class='progress'><div class='progress-bar progress-bar-danger' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: 100%;'>上传成功！</div></div>");
    		});
    		
    		
    		$("#ctlBtn").click(function(){
    			uploader.upload();
    		});
    		//队列
    		
    		
    	});
    </script>
</body>
</html>