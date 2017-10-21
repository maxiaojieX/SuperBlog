<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>申请</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">


  <style type="text/css">
		body{
			background-image:url(/static/dist/img/body-bg.png);
			padding-top: 120px;
		}
		#code{
			width:150px;
		}
		#getcode{
			position:absolute;
			left:200px;
			bottom:3px;
		}
	
	</style>
</head>
<body class="col-sm-offset-3 col-sm-6 col-lg-offset-4 col-lg-4 ">

  <div style="background-color: #fff;">
    <!-- Main content -->
    <section class="content">
      <div class="" style="border-bottom: 1px solid #f0f0f0">
        <div class="box-header">
        <h3 class="text-center">管理员申请</h3>
        </div>
      </div>
    

       <div class="box-solid">
        <div class="box-body">
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" method="post" id="applyform">
              <div class="box-body">
                <div class="alert alert-error" hidden><button class="close" data-dismiss="alert">×</button>用户名或密码错误</div>
                <fieldset>
                  <div class="form-group">
                     	<label class="col-sm-1 control-label"><i class="fa fa-user"></i></label>
					    <div class="col-sm-11">
					      <input type="text" class="form-control" id="username" name="username" placeholder="注册账号">
					    </div>
                  </div>
                  <div class="form-group">
                      	<label class="col-sm-1 control-label"><i class="fa fa-lock"></i></label>
					    <div class="col-sm-11">
					      <input type="password" class="form-control" id="password" name="password" placeholder="注册密码">
					    </div>
                  </div>
                  <div class="form-group">
                      	<label class="col-sm-1 control-label"><i class="fa fa-envelope-open-o"></i></label>
					    <div class="col-sm-11">
					      <input type="text" class="form-control" id="email" name="email" placeholder="您的邮箱地址">
					    </div>
                  </div>
                  <div class="form-group">
                      	<label class="col-sm-1 control-label"><i class="fa fa-plug"></i></label>
					    <div class="col-sm-11">
					      <input type="text" class="form-control" id="code" name="code" placeholder="邮箱验证码" >
					      <button type="button" class="btn btn-warning" id="getcode">获取验证码</button>
					    </div>
                  </div>
                </fieldset>
                <br>  
                
                </div>
                <!-- /.box-body -->
               
                <br>
            </form>
            <div>
              <button type="button" id="applybtn" class="btn btn-info  btn-lg btn-block">
                	申请
              </button>
            </div>
        </div>
        </div>
    </section>
  </div>


<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/dist/js/jquery.validate.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/layui/layui.js"></script>
<script>

	$("#applybtn").click(function(){
		
		var username = $("#username").val();
		var password = $("#password").val();
		var email = $("#email").val();
		var code = $("#code").val();
		
		$.post("/user/getcode",{"username":username,"password":password,"email":email,"code":code}).done(function(json){
			if(json.state == "success"){
					layer.msg('注册成功，即将返回登录界面');
				setTimeout(function () {
					window.location.href = "/login";
				  }, 2000)
				
			}else if(json.state == "empty"){
				layer.msg('请先输入');
			}else{
				layer.msg('验证码错误');
			}
        });
		
		
	});
	$("#applyform").validate({
		errorClass : "text-danger",
        errorElement : "span",
		rules: {
			username: {
				required:true
			},
			password: {
				required:true
			},
			email: {
				required:true,
				email:true
			},
			code:{
				required:true,
				digits:true
			}
		},
		messages: {
			username: {
				required: "请输入要注册的用户名"
			},
			password: {
				required: "请输入要注册的密码"
			},
			email: {
				required: "请输入您的邮箱",
				email: "请输入正确的邮箱格式"
			},
			code:{
				required: "请输入验证码",
				digits: "验证码格式不正确"
			}
		}
	});
	
	$("#getcode").click(function(){
		var szReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		var m = $("#email").val();
		if(szReg.test(m)){
			//alert("正确格式");
			$.get("/user/getcode",{"email":m}).done(function(json){
				if(json.state == "success"){
					layer.msg('验证码已发送');
					$("#getcode").attr("disabled", true);
					
				}else{
					layer.msg('发送失败');
				}
	        });
		}
	});
	
	
	

</script>
</body>
</html>
    