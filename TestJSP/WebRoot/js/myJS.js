//验证用户输入是否正确
function checkNum() {

	var num1 = document.getElementById("num1").value;
	var num2 = document.getElementById("num2").value;

	//window.alert(num1 + num2);
	
	// 验证两个数字是否为空
	if (num1 == "" || num2 == "") {
		window.alert("不能为空");
		return false;
	}

	// 使用JS正则表达式验证数字
	var reg = /^[1-9]\d*(\.[0-9]+)?$|^0$/i;
	// 验证num1是否为数字
	if (!reg.test(num1)) {
		window.alert("num1不是数字！");
		return false;
	}

	// num2是否为数字
	if (!reg.test(num2)) {
		window.alert("num2不是数字！");
		return false;
	}
	//return true;
}
