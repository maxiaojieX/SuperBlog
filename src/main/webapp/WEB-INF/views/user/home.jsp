<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home</title>
      <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
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
                    <ul class="nav navbar-nav">
                      <li class="active"><a href="/user/home">全部</a></li>
                      <c:forEach var="item" items="${nodelist}" varStatus="status">
                      	  <li><a href="/user/home?nodename=${item.nodename }">${item.nodename }</a></li>
                      </c:forEach>
                    </ul>
                    <form  method="get" id="searchForm"class="navbar-form navbar-right">
                      <div class="form-group">
                        <input type="text" id="keys" class="form-control" placeholder="根据标题搜索">
                      </div>
                      <p class="btn btn-default" id="searchBtn"><i class="fa fa-search"></i></p>
                    </form>
                   
                  </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
         </nav>
              <!-- 导航栏结束 -->

              <!-- 文章列表开始 -->

        <div class="container">
            <div class="row">
                <div class="col-md-9">
                <c:forEach var="item" items="${page.item}" varStatus="status">
                      
                        <div class="article-span">
                                <div class="media article">
                                            <div class="media-body">
                                                <a href="/user/detail?id=${item.id }"><span class="media-heading">${item.title }</span></a> <span class="time">${item.righttime }</span>
    
                                                <p class="">${item.simplecontent }</p>
                                                <div class="meta">
                                                <c:forEach var="it" items="${item.lablelist}" varStatus="status">
                                                	<a href="/user/home?lablename=${it.lablename}"><span class="label label-info">${it.lablename }</span></a>
                                                </c:forEach>
                                            </div> 
                                            </div>
                                        
                                            <div class="media-right">
                                            	${item.simplepic }
                                            </div>
                                    </div>
                        </div>
    			</c:forEach>
                        <div class="text-center">
                            <ul id="pagination" class="pagination pagination-lg"></ul>
                        </div>
                </div>    
                <div class="col-md-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">浏览排行</h3>
                        </div>

                            <!-- List group -->
                            <ul class="list-group text-primary">
                            <c:forEach var="item" items="${ranking}" varStatus="status">
                            <a href="/user/detail?id=${item.id }"><li class="list-group-item">${item.title }<label class="label label-danger" style="float:right">${item.scannum }</label></li></a>
                            </c:forEach>
                            </ul>
                        
                    </div>
                </div>
            </div>
        </div>

             

              
    <!-- jQuery 2.2.3 -->
    <script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>     
    <!-- page -->
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){
      
    	$("#pagination").twbsPagination({
            totalPages:"${page.pageTotal}",
            visiblePages:"${page.pageTotal}",
            href:"/user/home?page={{number}}&keys=${urikeys}&nodename=${urinodename}&lablename=${urilablename}",
            first: "首页",
            prev: "上一页",
            next:"下一页",
            last:"末页"
          });  
    	
    	
    	$("#searchBtn").click(function(){
    		var keys = $("#keys").val();
    		window.location.href = "/user/home?keys="+keys;
    	});
    });
</script>   
</body>
</html>