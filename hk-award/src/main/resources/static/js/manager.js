//全选和全不选
	function allcheck(num) {
		var check1 = document.getElementById("personlist")
				.getElementsByTagName("input");
		var check2 = document.getElementById("personlist2")
				.getElementsByTagName("input");
		if (num == 1) {
			for ( var i = 0; i < check1.length; i++) {
				check1[i].checked = "checked";
			}

		} else if (num == 0) {
			for ( var i = 0; i < check1.length; i++) {
				check1[i].checked = "";
			}
		}
	}

	function allcheck2(num) {
		var check2 = document.getElementById("personlist2").getElementsByTagName("input");
		if (num == 1) {
			for ( var j = 0; j < check2.length; j++) {
				check2[j].checked = "checked";
			}
		} else  if (num == 0) {
			for ( var j = 0; j < check2.length; j++) {
				check2[j].checked = "";
			}
		}
	}

	//删除按钮
	function deletePerson() {
		var check1 = document.getElementById("personlist")
				.getElementsByTagName("input");
		var check2 = document.getElementById("personlist2")
				.getElementsByTagName("input");
		var id = "";
		var flag = 0;
		for ( var i = 0; i < check1.length; i++) {
			if (check1[i].checked && i < check1.length) {
				id = id + "id=" + check1[i].getAttribute("id") + "&";
				flag = 1;
			}
		}

		for ( var j = 0; j < check2.length; j++) {
			if (check2[j].checked && j < check2.length) {
				id = id + "id=" + check2[j].getAttribute("id") + "&";
				flag = 1;
			}
		}
		id = id.substring(3, id.length - 1);
		if (flag == 0) {
			alert("请选择要删除的信息！");
			return;
		}
		window.location.href = "../DelPersonServlet?id=" + id;
	}

	//增加按钮
	function addP() {
		var div = document.getElementById("add");
		var text = document.getElementById("name");
		if (div.style.display == "block") {
			div.style.display = "";
		} else {
			div.style.display = "block";
			text.focus();
		}
	}

	function check() {
		var user = document.getElementById("name").value;
		if (user == "") {
			alert("姓名不能为空！");
			return false;
		} else {
			var div = document.getElementById("add");
			div.style.display = "";
			return true;
		}
	}
	var num = 0;
	function sel() {
		var check1 = document.getElementById("personlist")
				.getElementsByTagName("input");
		var check2 = document.getElementById("personlist2")
				.getElementsByTagName("input");
		for ( var x = 0; x < check1.length; x++) {
			if (check1[x].checked) {
				num++;
				if (num >= 2) {
					alert("只能选一个！");
					return num;
				}
			}
		}

		for ( var y = 0; y < check2.length; y++) {
			if (check2[y].checked) {
				num++;
				if (num >= 2) {
					alert("只能选一个！");
					return num;
				}
			}
		}
	}

	//修改页面
	function modifyP() {
		closes();
		if (sel() >= 2) {
			num = 0;
			return;
		}
		var check1 = document.getElementById("personlist")
				.getElementsByTagName("input");
		var check2 = document.getElementById("personlist2")
				.getElementsByTagName("input");
		var id = "";

		for ( var i = 0; i < check1.length; i++) {
			if (check1[i].checked && i < check1.length) {
				id = check1[i].getAttribute("id");
				window.location.href = "../ModifyServlet?id=" + id;
				return;
			}
		}

		for ( var j = 0; j < check2.length; j++) {
			if (check2[j].checked && j < check2.length) {
				id = check2[j].getAttribute("id");
				window.location.href = "../ModifyServlet?id=" + id;
				return;
			}
		}
		alert("请选择要修改的信息！");
	}

	function closes() {
		var div = document.getElementById("add");
		document.getElementById("name").value = "";
		div.style.display = "none";
	}

	//一键复位
	function resetInfo() {
		var str = "";
		var check2 = document.getElementById("personlist2")
				.getElementsByTagName("input");
		for ( var y = 0; y < check2.length; y++) {
			if (check2[y].checked) {
				str = str + "id=" + check2[y].getAttribute("id") + "&";
			}
		}
		if (str.length > 0) {
			str = str.substring(0, str.length - 1);
			if (confirm("您确定要全部恢复初始值？")) {
				window.location.href = "../ResetSevlet?" + str;
			}
		} else {
			alert("请选择要恢复的信息！");
		}
	}