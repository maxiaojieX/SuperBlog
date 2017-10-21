<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>文章列表</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="include/css.jsp"%>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="include/header.jsp"%>
  <!-- =================header============================== -->
	<%@ include file="include/sidebar.jsp"%>
  <!-- =============================================== -->

  <div class="content-wrapper">
  
    <!-- Main content -->
    <section class="content">
      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          
          
          <a href="/admin/articleadd" class="btn btn-success pull-right">写文章</a>
        </div>

        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
              <tr >
                <th>文章标题</th>
                <th>模块</th>
                <th>标签</th>
                <th>发表时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr>
              <c:forEach var="item" items="${page.item}" varStatus="status">
                <td>${item.title }</td>
                <td>${item.nodename }</td>
                <td><c:forEach var="it" items="${item.lablelist}" varStatus="status">
                ${it.lablename }
                <c:if test="${not status.last }">/</c:if>
                </c:forEach></td>
                <td>${item.righttime }</td>
                <td>
                  <a href="javascript:;" class="del" rel="${item.id }">删除</a> 
                  <a href="/admin/modify?modifyId=${item.id }">修改</a> 
                </td>
              </tr>
                </c:forEach>
              
            </tbody>
          </table>
          <br> 
          <ul id="pagination" class="pagination pull-right"></ul>
        </div>
        <!-- /.box-body -->
        
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

	<%@ include file="include/js.jsp"%>
<script>
    $(function(){
      
        $("#pagination").twbsPagination({
         totalPages:"${page.pageTotal}",
         visiblePages:"${page.pageTotal}",
         href:"/admin/ArticleList?currentpage={{number}}",
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       });  
        
        $(".del").click(function(){
        	var r = $(this).attr("rel");
        	layer.confirm('确定删除?', {
        		  btn: ['确定','取消'] //按钮
        		}, function(){
        		  window.location.href = "/admin/articlelistdel?del="+r;
        		});
        });
      
    });
    </script>
</body>
</html>
    