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

<title>My JSP 'form2.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
	function inputColor(input) {
		input.className = "norm";

		input.onfocus = function() {
			this.className = "focus";
		};
	}

	window.onload = function() {
		with (document.forms[0]) {
			inputColor(user);
			inputColor(pwd);
			inputColor(repwd);
			inputColor(mail);
		}
	};

	function checkUser(userNode) {
		/*
			var value = userNode.value;
			var regExp = /^\w{3,5}$/;
			var div = document.getElementById("userdiv");
			if (regExp.test(value)) {
				userNode.className = "norm";
				div.style.display = "none";
			} else {
				userNode.className = "error";
				div.style.display = "block";
			}
		 */

		var regex = /^\w{3,5}$/;
		return check(userNode, regex, "userdiv");
	}

	function checkPwd(pwdNode) {
		var regex = /^[a-z0-9]{3,5}$/i;
		return check(pwdNode, regex, "pwddiv");
	}

	function checkRepwd(repwdNode) {

		var b = false;
		var repwd = repwdNode.value;
		var pwd = document.getElementsByName("pwd")[0].value;
		var div = document.getElementById("repwddiv");

		if (pwd == repwd) {
			repwdNode.className = "norm";
			div.style.display = "none";
			b = true;
		} else {
			repwdNode.className = "error";
			div.style.display = "block";
		}
		return b;
	}

	function checkMail(mailNode) {
		var regex = /^\w+@\w+(\.\w+)+$/;
		return check(mailNode, regex, "maildiv");

	}
	//提取成函数，增强复用性
	function check(inputNode, regex, divId) {

		var b = false;
		var div = document.getElementById(divId);
		if (regex.test(inputNode.value)) {
			inputNode.className = "norm";
			div.style.display = "none";
			b = true;
		} else {
			inputNode.className = "error";
			div.style.display = "block";
		}

		return b;
	}
	//校验表单
	function checkForm(form) {
		with (form) {
			if (checkUser(user) && checkPwd(pwd) && checkRepwd(repwd)
					&& checkMail(mail))
				event.returnValue = true;
			else
				event.returnValue = false;
		}
	}
</script>
<style type="text/css">
table {
	border: #0066FF 1px solid;
	width: 600px;
	border-collapse: collapse;
}

table td,table th {
	border: #0066FF 1px solid;
	padding: 10px;
}

table td {
	background-color: #FFFF99;
}

table th {
	background-color: #FF9900;
}

#repwdspan {
	margin-left: 140px;
}

.errinfo {
	color: #FF0000;
	display: none;
}

.focus {
	border: #0099FF 2px solid;
}

.norm {
	border: #999999 1px solid;
}

.error {
	border: #FF0000 2px solid;
}
</style>
</head>

<body>
	<form action="http://127.0.0.1" onsubmit="checkForm(this)">
		<table>
			<tr>
				<th>注册表单</th>
			</tr>
			<tr>
				<td>
					<div>用户名</div>
					<div>
						<input type="text" name="user" onblur="checkUser(this)" />
					</div>
					<div class="errinfo" id="userdiv">用户名错误，请按要求输入.</div>
					<div>用户名必须3-5位，由字母(a-z),数字(0-9),下划线(_)组成.</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<span>密码</span> <span id="repwdspan">确认密码</span>
					</div>
					<div>
						<input type="password" name="pwd" onblur="checkPwd(this)" /> <input
							type="password" name="repwd" onblur="checkRepwd(this)" />
					</div>
					<div class="errinfo" id="pwddiv">密码格式错误，请按规范输入</div>
					<div class="errinfo" id="repwddiv">两次密码输入不一致</div>
					<div>密码必须是3-5位，由字母(a-z),数字(0-9)组成</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>邮件地址</div>
					<div>
						<input type="text" name="mail" onblur="checkMail(this)" />
					</div>
					<div class="errinfo" id="maildiv">邮件地址错误，请按要求填写</div>
				</td>
			</tr>
			<tr>
				<th><input type="submit" value="提交数据" /></th>
			</tr>

		</table>
	</form>
</body>
</html>
