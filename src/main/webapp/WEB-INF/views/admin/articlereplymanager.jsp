<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>文章回复管理</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
	<%@ include file="include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
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
      <div class="box">
        <div class="box-header with-border">
         <!--  <h5 class="pull-left">文章列表</h5> -->
         
        </div>

        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
              <tr >
                <th>文章标题</th>
                <th>评论内容</th>
                <th>评论IP</th>
                <th>评论时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr>
              <c:forEach var="item" items="${page.item}">
                <td>${item.title }</td>
                <td>${item.content }</td>
                <td>${item.userip }</td>
                <td>${item.createtime }</td>
                <td>
                  <a href="javascript:;"class="del" rel="${item.id }">删除</a> 
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
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<%@ include file="include/js.jsp"%>
<script>
    $(function(){
      
        $("#pagination").twbsPagination({
         totalPages:"${page.pageTotal}",
         visiblePages:"${page.pageTotal}",
         href:"/admin/articlereplymanager?page={{number}}",
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
       			$.get("/admin/articlereplydel?del="+r).done(function(json){
       				if(json.state == "success"){
       					window.history.go(0)
       				}else{
       					layer.alert('删除失败', {
							  icon: 1,
							  skin: 'layer-ext-moon' 
							})
       				}
       			}).fail(function(){
       				layer.alert('服务器故障')
       			});
       			
       		});
        });
      
    });
    </script>
</body>
</html>
