<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>文章详情</title>
      <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/plugins/editer/styles/simditor.css">
    <link rel="stylesheet" href="/static/dist/css/style.css">
    <style>
        body {
            margin-top: 60px;
        }
        #houtai{
        	position:relative;
        	left:750px;
        }
        
    </style>
</head>
<body>
        <nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
                <div class="container">
                  <!-- Brand and toggle get grouped for better mobile display -->
                  <div class="navbar-header">
                    <a class="navbar-brand" href="/user/home"><i class="fa fa-weibo"></i></a>
                  </div>
                  <div class="navbar-header">
                    <a class="navbar-brand" href="/admin/articleadd"><i class="fa fa-pencil-square-o"></i></a>
                  </div>
                  <div class="navbar-header">
                    <a class="navbar-brand" href="/admin/ArticleList" id="houtai"><i class="fa fa-user-md"></i></a>
                  </div>
              
                  <!-- Collect the nav links, forms, and other content for toggling -->
                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    
                    <form class="navbar-form navbar-right">
                      <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                      </div>
                      <button class="btn btn-default"><i class="fa fa-search"></i></button>
                    </form>
                   
                  </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
              </nav>
              <!-- 导航栏结束 -->

              <!-- 文章列表开始 -->

              <div class="container">
                    <div class="box">
                            <ul class="breadcrumb" style="background-color: #fff;margin-bottom: 0px;">
                                <li><a href="#">首页</a></li>
                                <li class="active">javaEE</li>
                            </ul>
                            
                            <div class="topic-head">
                                <h3 class="title">${articlevo.article.title }</h3>
                            </div>
                           
                            <div class="topic-body">
                                    <p class="">${articlevo.article.content }</p>
                                </div>
                            <div class="topic-toolbar">
                                   
                                <ul class="list-inline text-muted">
                                    <li><i class="fa fa-eye"></i>${articlevo.article.scannum }</li>
                                    <li><i class="fa fa-commenting"></i>${articlevo.article.replynum }</li>
                                </ul>
                            </div>
                        </div>
                        <!--box end-->
                
                        <div class="box" style="margin-top:20px;">
                            <div class="talk-item muted" style="font-size: 12px">
                                ${articlevo.article.replynum }个回复 
                            </div>
                            <c:set var="a" value="0"></c:set>
                            <%int b = 1; %>
                            <c:forEach var="item" items="${replylist }"  varStatus="vs">
                            <c:if test="${item.pid == 0}">
                            <div class="talk-item">
                                <table class="talk-table">
                                    <tr>
                                        <td width="auto">
                                            <a href="" style="font-size: 12px">${item.userip }</a> <span style="font-size: 12px" class="reply">${item.righttime }</span>
                                            <br>
                                            <p style="font-size: 14px">${item.content }</p>
                                        </td>
                                        <td width="70" align="right" style="font-size: 12px">
                                            <a href="javascrpt:;" title="回复" rel="${item.id }"  class="reReply"><i class="fa fa-reply"></i></a>&nbsp;
                                            <span class="badge" ><%=b++ %></span>
                                        </td>
                                    </tr>
                                    <c:forEach var="it" items="${replylist }"  varStatus="vs">
                                    <c:if test="${item.id == it.pid }">
                                    <table class="table table-bordered">
                                    <tr>
                                        <td width="auto">
                                            <a href="" style="font-size: 12px">${it.userip }</a> <span style="font-size: 12px" class="reply">${it.righttime }</span>
                                            <br>
                                            <p style="font-size: 14px">${it.content }</p>
                                        </td>
                                    </tr>
                                	</table>
                                	</c:if>
                                    </c:forEach>
                                    
                                </table>
                            </div>
                            </c:if>
                			</c:forEach>
                        </div>
                
                        <div class="box" style="margin:20px 0px;">
                            <div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>
                            <form action="/user/detail" method="post" id="replyForm" style="padding: 15px;margin-bottom:0px;">
                            <input type="hidden" name="id" value="${param.id}"/>
                            <input type="hidden" name="pid" id="pid" value=""/>
                            <a name="mao"></a>
                                <textarea name="editor" id="editor"></textarea>
                            </form>
                            <div class="talk-item muted" style="text-align: right;font-size: 12px">
                                <span class="pull-left">请尽量让自己的回复能够对别人有帮助回复</span>
                                <button class="btn btn-primary" id="replyBtn">发布</button>
                            </div>
                        </div>
              </div>

             

              
    <!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>     
<script src="/static/plugins/editer/scripts/module.min.js"></script>
<script src="/static/plugins/editer/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/editer/scripts/uploader.min.js"></script>
<script src="/static/plugins/editer/scripts/simditor.min.js"></script>
<script src="/static/moment/moment.js"></script>
<script>
    $(function(){
        var editor = new Simditor({
                textarea: $('#editor')
            });
        
        moment.locale("zh-cn");
		$(".reply").text(function(){
			var time = $(this).text();
			return moment(time).fromNow();
		});
		
       $("#replyBtn").click(function(){
    	   $("#replyForm").submit();
       });
       
       $(".reReply").click(function(){
    	   $("#pid").attr("value",$(this).attr("rel"));
    	   window.location.href="#mao";
    	   editor.setValue("回复第"+$(this).siblings().text()+"楼: ");
    	   editor.focus();
    	   
       });
       
       
       
    });
</script>   
</body>
</html>