<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>节点列表</title>
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
          <a href="#" class="btn btn-success pull-right"  data-toggle="modal" data-target="#addModal">新增节点</a>
        </div>

        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
              <tr >
                <th>节点名称</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="no" items="${page.item }">
              <tr>
                <td>${no.nodename }</td>
                <td>
                  <a href="javascript:;" class="del" rel="${no.nid }">删除</a> 
                  <a href="">修改</a> 
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


  <div class="modal fade" id="addModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">新增节点</h4>
        </div>
        <div class="modal-body">
        
        <!-- 这里是新增节点弹出层 -->
        
          <form action="" class="form-horizontal" id="addForm">
              <div class="form-group">
                  <label class="col-sm-2 control-label">节点名称:</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="addNnodeName" placeholder="请输入节点名称">
                  </div>
                </div>

          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary pull-left" id="newBtn">保存</button>
          <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->





<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<%@ include file="include/js.jsp"%>

<script>
    $(function(){
      
        $("#pagination").twbsPagination({
         totalPages:"${page.pageTotal}",
         visiblePages:"${page.pageTotal}",
         href:"/admin/articlenodemanager?page={{number}}",
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       });  

      

       $(".update").click(function(){
         $("#updateNodeName").val("java");
          $('#updateModal').modal({
            keyboard: false
          });
       });
       
       $(".del").click(function(){
    	   var r = $(this).attr("rel");
       		layer.confirm('确定删除?', {
       		  btn: ['确定','取消'] //按钮
       		}, function(){
       			$.get("/admin/articlenodedel?del="+r).done(function(json){
       				if(json.state == "success"){
       					window.history.go(0)
       				}else{
       					layer.alert('该节点下有文章，不允许删除', {
							  icon: 1,
							  skin: 'layer-ext-moon' 
							})
       				}
       			}).fail(function(){
       				layer.alert('服务器故障')
       			});
       			
       		});
       });
       
       $("#newBtn").click(function(){
    	   
    	   var nn = $("#addNnodeName").val();
    	   $.get("/admin/articlenodeadd?newnodename="+nn).done(function(json){
  				if(json.state == "success"){
  					window.history.go(0)
  				}else{
  					layer.alert('此节点名称已存在', {
						  icon: 1,
						  skin: 'layer-ext-moon' 
						})
  				}
  			}).fail(function(){
  				layer.alert('服务器故障')
  			});
       });
      
    });
    </script>
</body>
</html>
