<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>消息中心</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

 <%@ include file="include/header.jsp"%>
 <%@ include file="include/sidebar.jsp"%>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Main content -->
    <section class="content">
      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
            <div class="box-header">
				
				<button id="markBtn" style="margin-left: 8px;" disabled class="btn btn-success">标记为已读</button>
			</div>
        </div>

        <div class="box-body">
          
          <table class="table">
            <thead>
                <tr>
                    <th width="30"><input type="checkbox" id="ckFather"></th>
                    <th width="200">发布日期</th>
                    <th>内容</th>
                </tr>
            </thead>
            <tbody>
		       <c:forEach var="item" items="${page.item }">
		           <c:choose>
               			<c:when test="${item.state == 0 }">
      				<tr style="font-weight:900;">
                       <td><input value="${item.id }" type="checkbox" class="ckSon"></td>
                       <td>${item.createtime }</td>
                       <td>${item.content }</td>
                   </tr>
                   </c:when>
               	   <c:otherwise>
            		<tr>
                       <td></td>
                       <td>${item.createtime }</td>
                       <td>${item.content }</td>
                   </tr>
                   </c:otherwise>
               	</c:choose>
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
      	var judge = function(){
      		if($(".ckSon").length == 0){
      			$("#ckFather").attr("hidden",true);
      		}
      	};
      	judge();
    	
        $("#pagination").twbsPagination({
         totalPages:"${page.pageTotal}",
         visiblePages:"${page.pageTotal}",
         href:"/admin/notify?p={{number}}",
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       });  
        
        $("#ckFather").click(function(){
        		for(var i = 0;i < $(".ckSon").length;i++){
        			$(".ckSon")[i].checked = $("#ckFather")[0].checked;
        		}
        		if($("#ckFather")[0].checked){
        			$("#markBtn").removeAttr("disabled");
        		}else{
        			$("#markBtn").attr("disabled","disabled");
        		}
        });
        $(".ckSon").click(function(){
        var cknum = 0;
        	for(var i = 0;i < $(".ckSon").length;i++){
        		if($(".ckSon")[i].checked){
        			cknum++;
        		}
    		}
        	if($(".ckSon").length == cknum){
        		$("#ckFather")[0].checked = true;
        	}else{
        		$("#ckFather")[0].checked = false;
        	}
        	if(cknum > 0){
        		$("#markBtn").removeAttr("disabled");
        	}else if(cknum == 0){
        		$("#markBtn").attr("disabled","disabled");
        	}
        });
        $("#markBtn").click(function(){
        	//获取所有ids
        	var ids = [];
			for(var i = 0;i < $(".ckSon").length;i++){
				if($(".ckSon")[i].checked){
					ids.push($(".ckSon")[i].value);
				}
			}
        	$.post("/admin/readnotify",{"ids":ids.join(",")},function(json){
        		if(json.state == "success"){
        			window.history.go(0)
        		}
        	});
        });
      
    });
    </script>
</body>
</html>
    