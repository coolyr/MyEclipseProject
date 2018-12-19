<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'FileUp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <!-- 上传type="file"类型的文件必须修改表单的类型：enctype="multipart/form-data" -->
    <form action="/TestStruts/fileUp.do" enctype="multipart/form-data" method="post">
    name:<input type="text" name="name"/><br/>
    passwd:<input type="password" name="password"/><br/>
    photo:<input type="file" name="photo"/><br>
    <input type="submit" value="上传"/>
    </form>
  </body>
</html>
