<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script language="javascript">
	// 在DOM中插入一个上传文件列表项（div元素)和一个<input type="file"/>元素  
	function insertNextFile(obj) {
		// 获取上传控制个数  
		var childnum = document.getElementById("files").getElementsByTagName(
				"input").length;
		var id = childnum - 1;
		var fullName = obj.value;
		// 插入<div>元素及其子元素  
		var fileHtml = '';
		fileHtml += '<div  id = "file_preview' + id + '" style ="border-bottom: 1px solid #CCC;">';
		fileHtml += '<img  width =30 height = 30 src ="images/file.jpg" title="' + fullName + '"/>';
		fileHtml += '<a href="javascript:;" onclick="removeFile(' + id
				+ ');">删除</a>   ';
		fileHtml += fullName.substr(fullName.lastIndexOf('\\') + 1)
				+ '  </div>';

		var fileElement = document.getElementById("files_preview");
		fileElement.innerHTML = fileElement.innerHTML + fileHtml;
		obj.style.display = 'none'; // 隐藏当前的<input type=”file”/>元素  
		addUploadFile(childnum); // 插入新的<input type=”file”/>元素  
	}
	//  插入新的<input type=”file”/>元素，适合于不同的浏览器（包括IE、FireFox等）  
	function addUploadFile(index) {
		try // 用于IE浏览器  
		{
			var uploadHTML = document
					.createElement("<input type='file' id='file_" + index
							+ "' name='file[" + index
							+ "]' onchange='insertNextFile(this)'/>");
			document.getElementById("files").appendChild(uploadHTML);
		} catch (e) // 用于其他浏览器  
		{
			var uploadObj = document.createElement("input");
			uploadObj.setAttribute("name", "file[" + index + "]");
			uploadObj.setAttribute("onchange", "insertNextFile(this)");
			uploadObj.setAttribute("type", "file");
			uploadObj.setAttribute("id", "file_" + index);
			document.getElementById("files").appendChild(uploadObj);
		}
	}
	function removeFile(index) // 删除当前文件的<div>和<input type=”file”/>元素  
	{
		document.getElementById("files_preview").removeChild(
				document.getElementById("file_preview" + index));
		document.getElementById("files").removeChild(
				document.getElementById("file_" + index));
	}
	function showStatus(obj) // 显示“正在上传文件”提示信息  
	{
		document.getElementById("status").style.visibility = "visible";
	}
</script>
</head>

<body>
  <!-- 上传type="file"类型的文件必须修改表单的类型：enctype="multipart/form-data" -->
	<html:form enctype="multipart/form-data" action="uploadMore">
	
		<span id="files"> <%--  在此处插入用于上传文件的input元素 --%> 
		<input type="file" id="file_0" name="file[0]" onchange="insertNextFile(this)" />
		</span>
		
		<html:submit value=" 上传 " onclick="showStatus(this);" />
	</html:form>
	
	<p>
	<div id="status" style="visibility: hidden; color: Red">正在上传文件</div>
	<p>
		<%--  在此处用DOM技术插入上传文件列表项  --%>
	<div id="files_preview" style="width: 500px; height: 500px; overflow: auto"></div>
</body>
</html>
