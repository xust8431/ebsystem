function login(value){
	if(value == 1){
		var nickOrPhone = $("#name").val().trim();
		console.log(nickOrPhone);
		var password = $("#password").val().trim();
		var ok = true;
		if(nickOrPhone == ""){
			alert("用户名或手机号码不能为空");
			ok = false;
			return;
		}
		if(password == ""){
			alert("密码不能为空");
			ok = false;
			return;
		}
		if(ok){
			$.ajax({
				url:path+"/user/login.do",
				type:"post",
				data:{"nickOrPhone":nickOrPhone,"password":password,"value":value},
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						var userNick = result.data.user_nick;
						var userReputation = result.data.user_reputation;
						var userPhone = result.data.user_phone;
						var userName = result.data.user_name;
						addCookie("userNick",userNick,2);
						addCookie("userPassword",password,2);
						addCookie("userReputation",userReputation,2);
						addCookie("userPhone",userPhone,2);
						addCookie("userName",userName,2);
						window.location.href="u_reserve.html";
					}else if(result.status == 1){
						alert(result.msg);
					}else if(result.status == 2){
						alert(result.msg);
					}else if(result.status == 3){
						alert(result.msg);
					}
				},
				error:function(){
					alert("登录失败");
				}
			});
		}
	}else{
		var adminName = $("#name").val().trim();
		var password = $("#password").val().trim();
		var ok = true;
		if(adminName == ""){
			alert("用户名不能为空");
			ok = false;
			return;
		}
		if(password == ""){
			alert("密码不能为空");
			ok = false;
			return;
		}
		if(ok){
			$.ajax({
				url:path+"/admin/adminLogin.do",
				type:"post",
				data:{"adminName":adminName,"password":password,"value":value},
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						console.log("111");
						var adminName = result.data.admin_name;
						var adminToken = result.data.admin_token;
						addCookie("adminName",adminName, 2);
						addCookie("adminToken", adminToken, 2);
						window.location.href="a_reserve.html";
					}else if(result.status == 1){
						alert(result.msg);
					}else if(result.status == 2){
						alert(result.msg);
					}else if(result.status == 3){
						alert(result.msg);
					}
				},
				error:function(){
					alert("登录失败");
				}
			
			});
		}
	}
}

function regist(){
	var userNick = $("#userNick").val().trim();
	var userName = $("#userName").val().trim();
	var userPassword = $("#userPassword").val().trim();
	var userPassword2 = $("#userPassword2").val().trim();
	var userPhone = $("#userPhone").val().trim();
	var userTel = $("#userTel").val().trim();
	var userCompany = $("#userCompany").val().trim();
	var userEmail = $("#userEmail").val().trim();
	var ok = true;
	if(userNick == ""){
		alert("用户名不能为空");
		ok = false;
		return;
	}
	if(userPassword == ""){
		alert("密码不能为空");
		ok = false;
		return;
	}else if(userPassword.length>0&&userPassword<6){
		alert("密码应大于6位");
		ok = false;
		return;
	}
	if(userPassword2 == ""){
		alert("确认密码不能为空");
		ok = false;
		return;
	}else if(userPassword2 != userPassword){
		alert("两个输入的密码不一致");
		ok = false;
		return;
	}
	if(userPhone == ""){
		alert("联系电话不能为空");
		ok = false;
		return;
	}
	if(ok){
		$.ajax({
			url:path+"/user/add.do",
			type:"post",
			data:{"nick":userNick,"name":userName,"phone":userPhone,
				"tel":userTel,"company":userCompany,"email":userEmail,"password":userPassword},
			dataType:"json",
			success:function(result){
				if(result.status == 0){
					alert(result.msg);
					window.location.href="login.html";
				}else if(result.status == 1){
					alert(result.msg);
				}else if(result.status == 2){
					alert(result.msg);
				}
			},
			error:function(){
				alert("注册失败");
			}
		});
	}
}