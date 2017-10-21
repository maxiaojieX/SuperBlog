<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <aside class="main-sidebar">
      <!-- sidebar: style can be found in sidebar.less -->
      <section class="sidebar">
       
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
         
          <li class="treeview">
            <a href="/admin/ArticleList">
              <i class="fa fa-file-text "></i> <span>文章管理</span>
            </a>
            
          </li>
          <li class="treeview">
            <a href="/admin/articlenodemanager">
              <i class="fa fa-files-o"></i> <span>分类管理</span>
            </a>
           
          </li>
          <li class="treeview">
            <a href="/admin/articlereplymanager">
              <i class="fa fa-comments"></i> <span>评论管理</span>
            </a>
          </li>
          <li class="filemanager">
            <a href="/admin/fileupload"    onclick="window.open(this.href,'_blank','scrollbars=0,resizable=0,top=143,left=481,width=380,height=400');return    false">
              <i class="fa fa-file"></i> <span>我的云盘</span>
            </a>
          </li>
          
        </ul>
      </section>
      <!-- /.sidebar -->
    </aside>