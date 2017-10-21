<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>登录</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
  <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
  <style type="text/css">
		body{
			background-image:url(/static/dist/img/body-bg.png);
			padding-top: 120px;
		}
		#youke{
			position:absolute;
			right:-230px;
			bottom:20px;
		}
		#apply{
			position:absolute;
			right:-340px;
			bottom:20px;
		}
	
	</style>
</head>
<body class="col-sm-offset-3 col-sm-6 col-lg-offset-4 col-lg-4 ">

  <div style="background-color: #fff;">
    <!-- Main content -->
    <section class="content">
      <div class="" style="border-bottom: 1px solid #f0f0f0">
        <div class="box-header">
        <h3 class="text-center">博客登录</h3>
        </div>
      </div>
    

       <div class="box-solid">
        <div class="box-body">
            <form class="form-horizontal" method="post" id="loginForm">
            <input type="hidden" name="callback" value=${param.logincallback }/>
              <div class="box-body">
                <div class="alert alert-error" hidden><button class="close" data-dismiss="alert">×</button>用户名或密码错误</div>
                <fieldset>
                  <div class="form-group">
                     	<label class="col-sm-1 control-label"><i class="fa fa-user"></i></label>
					    <div class="col-sm-11">
					      <input type="text" class="form-control" id="username" name="username" placeholder="登录帐号">
					    </div>
                  </div>
                  <div class="form-group">
                      	<label class="col-sm-1 control-label"><i class="fa fa-lock"></i></label>
					    <div class="col-sm-11">
					      <input type="password" class="form-control" id="password" name="password" placeholder="登录密码">
					    </div>
					    <input type="hidden" id="callback" value="${param.callback }" />
                  </div>
                </fieldset>
                <br>  
                <div id="remember-me" class="form-group">
					<div class="col-sm-offset-1 col-sm-11">
						<input type="checkbox" name="remember" <c:if test="${not empty username}">checked</c:if> id="remember" style="margin-right:4px;">
						<label id="remember" for="remember">记住用户名</label>
					</div>
                   
                </div>
                
                </div>
               
                <br>
            </form>
            <div>
              <button type="button" id="loginBtn" class="btn btn-info  btn-lg btn-block">
                	进入系统
              </button>
            </div>
        </div>
        </div>
    </section>
  </div>
	<div id="youke"><a href="/user/home">以游客身份浏览</a></div>
	<div id="apply"><a href="/user/apply">申请成为管理员</a></div>
<script src="/static/bootstrap/js/jquery-3.2.1.min.js"></script>
<script src="/static/dist/js/jquery.validate.min.js"></script>
<script src="/static/layui/layui.js"></script>
<script>	
	$(function(){
		
		$("#loginBtn").click(function(){
			$("#loginForm").submit();
		});
		$("#loginForm").validate({
			errorClass : "text-danger",
            errorElement : "span",
			rules: {
				username: {
					required:true
				},
				password: {
					required:true
				}
			},
			messages: {
				username: {
					required: "请输入用户名"
				},
				password: {
					required: "请输入密码"
				}
			},
			submitHandler: function(form){
				var username = $("#username").val();
				var password = $("#password").val();
				var callback = $("#callback").val();
				$.post("/login",{"username":username,"password":password,"callback":callback}).done(function(json){
					if(json.state == "success"){
						if(json.callback){
							window.location.href = json.callback;
						}else{
							window.location.href = "/user/home";
						}
					}else{
						alert("账号或密码错误");
					}
		        });
			}
		});
		
	});
</script>

</body>
</html>