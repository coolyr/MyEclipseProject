<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'form.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function checkUser(userNode) {

		var user = userNode.value;
		//a - z 的任意5个字母
		//^开始符号
		//$结束符号
		//使用正则表达式对象RegExp
		var userReg = new RegExp("^[a-z]{5}$", "i");//i代表忽略大小写
		//g （全文查找出现的所有 pattern） 
		//i （忽略大小写） 
		//m （多行查找） 
		var yesspan = document.getElementById("yesspan");
		var nospan = document.getElementById("nospan");
		//user.match('regExp')使用的是字符串的match方法来匹配正则表达式，
		//返回的的是符合要求的对象数组，所以当返回不为空的时候是正确的，null为错误
		if (user.match(userReg)) {
			yesspan.style.display = "inline";
			yesspan.style.color = "green";
			nospan.style.display = "none";
		} else {
			nospan.style.display = "inline";
			nospan.style.color = "red";
			yesspan.style.display = "none";
		}
	}

	function checkPwd(pwdNode) {
		var passwd = pwdNode.value;
		//var passReg = new RegExp("^\\d{5}$");//  ‘//d’表示数字
		var passReg = /^\d{5}$/;
		var pwdspan = document.getElementById("pwdspan");
		if (passwd.match(passReg)) {
			pwdspan.innerHTML = "密码正确！".fontcolor("green");
		} else {
			pwdspan.innerHTML = "密码错误！".fontcolor("red");

		}
	}

	function checkEmail(emailNode) {
		var mail = emailNode.value;
		var mailRegExp = /^\w+@\w+(\.\w+)+$/;
		emailspan = document.getElementById("emailspan");
		//使用的是正则表达式对象的test方法，输入参数为需要检验的字符串，输出boolean对象
		if (mailRegExp.test(mail)) {
			emailspan.innerHTML = "邮件正确！".fontcolor("green");
		} else {

			emailspan.innerHTML = "邮件错误！".fontcolor("red");
		}
	}

	function checkForm() {

		var form = document.forms[0];//获取表单对象，就可以获取表单里的内容了
		//alert(form.user.value);
		if (checkUser(form.user) && checkEmail(form.mail)) {
			//要再次使用checkXX()函数进行一遍校验！！
			//因为发生在失去焦点的时候，防止用户校验成功后有更改了里面的数据
		}

		//方法一  通过event的returnValue属性取消提交时间(不需要return)
		//<form action="http://127.0.0.1" onsubmit="checkForm()">
		//event.returnValue = false;

		//方法二  通过返回真价值给form表单的onsubmit事件来取消提交
		//注意：onsubmit = checkForm()是给该事件绑定了一个函数，事件的值 = 函数地址
		//onsubmit = return checkForm() 才是给该事件返回了一个真假值
		//<form action="http://127.0.0.1" onsubmit="return checkForm()">

		return false;
	}
</script>

<style type="text/css">
#yesspan,#nospan {
	display: none
}
</style>
</head>

<body>
	<form action="http://127.0.0.1" onsubmit="return checkForm()">
		用户名称：<input type="text" name="user" onblur="checkUser(this)" /> <span
			id="yesspan">用户名正确！</span> <span id="nospan">用户名错误！</span> <br />
		输入密码： <input type="text" name="pwd" onblur="checkPwd(this)" /> <span
			id="pwdspan"></span> <br /> 确认密码： <input type="text" name="repwd" /><br />
		邮件地址：<input type="text" name="mail" onblur="checkEmail(this)" />
		<!-- onblur焦点失去事件 -->
		<span id="emailspan"></span> <br /> <input type="submit"
			value="提交查询内容" />
	</form>

</body>
</html>








