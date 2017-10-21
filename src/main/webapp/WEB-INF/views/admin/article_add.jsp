<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>写文章</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
  <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="/static/plugins/editer/styles/simditor.css">
  <link rel="stylesheet" href="/static/dist/css/style.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

     <%@ include file="include/header.jsp"%>
  <!-- =================header============================== -->
	<%@ include file="include/sidebar.jsp"%>
  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <!-- Default box -->     
      <div class="box box-primary">
          <div class="container">
          
          
            <form action="" class="form-horizontal" id="addForm">
              <br>
              
               <div class="form-group">
               		<input type="hidden" value="${article.id }" name="modifyid" />
                  <input type="text" class="form-control" name="title" placeholder="请输入主题标题" value="${article.title }">
               </div>
               <div class="form-group">
                  <input type="text" class="form-control" name="labelnames" placeholder="请为主题贴上标签，多个标签使用，号分开" value="<c:forEach items="${lablelist}" var="la" varStatus="status">${la.lablename }<c:if test="${not status.last }">,</c:if></c:forEach>">
               </div>
               
               <div class="form-group"> 
                  
                  <textarea name="content" class="from-control" id="editor"  placeholder="正文从这里开始...">${article.content }</textarea>
               </div>
              
               <div class="form-group">
                 <div class="col-sm-6" style="padding:0px">
                  <select name="node" id="" class="form-control" style="margin-top:15px">
                      <option value="">请选择节点</option>
                      <c:forEach items="${nodelist}" var="node">
                      
                      		<option value="${node.nid}" >${node.nodename}</option>
                      		
                      </c:forEach>
                      <!--<c:if test="${article.nodeid == node.id }"> selected </c:if> -->
                  </select>
                </div>
               </div>
               <br>
               <div class="form-group">
                  <button class="btn btn-primary" id="addBtn">发布文章</button>
               </div>
            </form>

          </div>
  
      </div>
      <!-- /.box -->
    
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.3.8
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- jquery validate -->
<script src="/static/dist/js/jquery.validate.min.js"></script>

<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/static/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<!-- 富文本编辑器 -->
<script src="/static/plugins/editer/scripts/module.min.js"></script>
<script src="/static/plugins/editer/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/editer/scripts/uploader.min.js"></script>
<script src="/static/plugins/editer/scripts/simditor.min.js"></script>
<script src="/static/notify/notify.js"></script>

<script>
  $(function(){
    var editor = new Simditor({
        textarea: $('#editor'),
        upload:{
            url: '/admin/picupload',
            fileKey: 'file_key'
        },
    });
    
    
    
    $.validator.addMethod("labelValidate", function(value, element) {
    	  return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5]+(,[a-zA-Z0-9\u4e00-\u9fa5]+)*$/.test(value);
    }, "please notice your doc");
    
    $("#addForm").validate({
    	errorClass : 'text-danger',
    	errorElement : 'span',
    	rules : {
    		title : {
    			required : true
    		},
    		labelnames : {
    			required : true,
    			labelValidate : true
    		},
    		content : {
    			required : true
    		},
    		node : {
    			required : true
    		}
    	},
    	messages : {
    		title : {
    			required : "请填写文章标题"
    		},
    		labelnames : {
    			required : "请填写文章标签",
    			labelValidate : "格式不正确,请用英文,隔开"
    		},
    		content : {
    			required : "请填写文章内容"
    		},
    		node : {
    			required : "请选择文章节点"
    		}
    	}, 
    	submitHandler : function(form) {
    		$.ajax({
    			url : '/admin/articleadd',
    			type : 'post',
    			//表单序列化
    			data : $(form).serialize(),
    			beforeSend : function(){
    				$("#addBtn").text("发布中...").attr("disabled","disabled");
    			},
    			success : function(json) {
    				if(json.state == "success") {
    					window.location.href = "/user/detail?id="+json.data;
    				} else {
    					layer.msg(json.state+json.data)
    				}
    			},
    			error : function(){
    				layer.alert("系统异常，请稍后再试")
    			},
    			complete : function(){
    				$("#addBtn").text("发布文章").removeAttr("disabled");
    			}
    		})
    	}
    })
    
  });


</script>
</body>
</html>
