//js逻辑
var status = 1;
$(function() {
	//点击<a>改变查询状态
	$("#a1").click(function(){
		$("#select-status a").removeClass("active");
		$(this).addClass("active");
		status = 1;
		loadReserve();
	});
	$("#a2").click(function(){
		$("#select-status a").removeClass("active");
		$(this).addClass("active");
		status = 2;
		loadReserve();
	});
	$("#a3").click(function(){
		$("#select-status a").removeClass("active");
		$(this).addClass("active");
		status = 3;
		loadReserve();
	});
	$("#reserve-table").on("click", ".examine_true", function() {
		var id = $(this).parents("tr").data("reserveId");
		examine(id, 1);
	});
	$("#reserve-table").on("click", ".examine_false", function() {
		var id = $(this).parents("tr").data("reserveId");
		examine(id, 2);
	});
	$("#reserve-table").on("click", ".completed", completed);
	
	$("#reserve-li").click(function() {
		loadReserve();
	});
	$("#userinfo-li").click(function() {
		loadUserinfo();
	});
	$("#manage-li").click(function() {
		loadSet();
	});
	$("#sure-set").click(function() {
		updateSet();
	});
	$("#ban-time").on("click", ".delete-set", function() {
		var $tr = $(this).parents("tr");
		deleteSet($tr);
	});
	$("#logout").click(function() {
		delCookie("adminToken");
		delCookie("adminName");
		window.location.href = path + "/login.html";
	});   		
	
	displayLoginFlag();
	$("#a1").click();
	
});

//显示登陆标志
function displayLoginFlag() {
	var adminName = getCookie("adminName");
	var adminToken = getCookie("adminToken");
	if(adminName != null) {
		$("#login-flag").text(adminName);
		$("#logout a").text("注销");
	} else {
		$("#login-flag").text("登录");
		$("#logout a").text("登录");
	}
}
//删除禁止时间段设置
function deleteSet($tr) {
	var adminName = getCookie("adminName");
	var token = getCookie("adminToken");
	var week = $tr.find(".ban-time-th").text();
	var time = $tr.find(".ban-time-td").text();
	var timeStr = "";
	if(week == "星期1") {
		timeStr += "1/";
	} else if(week == "星期2") {
		timeStr += "2/";
	} else if(week == "星期3") {
		timeStr += "3/";
	} else if(week == "星期4") {
		timeStr += "4/";
	} else if(week == "星期5") {
		timeStr += "5/";
	} else if(week == "星期6") {
		timeStr += "6/";
	} else if(week == "星期7") {
		timeStr += "7/";
	}
	timeStr += time;
	var ok = true;
	if(token == null) {
		//ok = false;
		console.log("todo");
	}
	if(ok) {
		$.ajax({
			url : path+"/set/delete_set.do",
			type : "post",
			data : {
				"adminToken" : token,
				"adminName" : adminName,
				"timeStr" : timeStr
			},
			dataType : "json",
			success : function(result) {
				if(result.status == 0) {
					$("#manage-li").click();
				} else if(result.status == -1) {
					window.location.href = path + "/login.html";
				}
			},
			error : function() {
				alert("修改失败");
			}
		});
	}
}
//加载用户信息列表
function loadUserinfo() {
	var adminName = getCookie("adminName");
	var token = getCookie("adminToken");
	var ok = true;
	$("#user-info-table tr").remove();
	var str = "";
	str += '<tr>';
	str += '<th>用户名</th>';
	str += '<td>真实姓名</td>';
	str += '<td>联系电话</td>';
	str += '<td>备用电话</td>';
	str += '<td>工作单位</td>';
	str += '<td>电子邮箱</td>';
	str += '<td>信誉度</td>';
	str += '</tr>';
	var $tr = $(str);
	$("#user-info-table").append($tr);
	if(token == null) {
		//ok = false;
		console.log("todo");
	}
	if(ok) {
		$.ajax({
			url : path+"/user/load_userinfo.do",
			type : "post",
			data : {
				"adminToken" : token,
				"adminName" : adminName
			},
			dataType : "json",
			success : function(result) {
				if(result.status == 0) {
					var data = result.data;
					var nick = null;
					var name = null;
					var phone = null;
					var tel = null;
					var company = null;
					var email = null;
					var reputation = null;
					for(var i = 0; i < data.length; i++) {
						nick = data[i].user_nick;
						name = data[i].user_name;
						phone = data[i].user_phone;
						tel = data[i].user_tel;
						company = data[i].user_company;
						email = data[i].user_email;
						reputation = data[i].user_reputation;
						str = "";
						str += '<tr><th>';
						str += nick;
						str += '</th><td>';
						str += name;
						str += '</td><td>';
						str += phone;
						str += '</td><td>';
						str += tel;
						str += '</td><td>';
						str += company;
						str += '</td><td>';
						str += email;
						str += '</td><td>';
						str += reputation;
						str += '</td></tr>';
						$tr = $(str);
		    			$("#user-info-table").append($tr);
					}
				} else if(result.status == 1) {
					alert(result.msg);
				} else if(result.status == -1) {
					window.location.href = path + "/login.html";
				}
			},
			error : function() {
				alert("查询失败");
			}
		});
	}
}
//确认完成状态
function completed() {
	var adminName = getCookie("adminName");
	var token = getCookie("adminToken");
	var id = $(this).parents("tr").data("reserveId");
	var ok = true;
	if(token == null) {
		//ok = false;
		console.log("todo");
	}
	if(ok) {
		$.ajax({
			url : path+"/a_reserve/completed.do",
			type : "post",
			data : {
				"adminToken" : token,
				"adminName" : adminName,
				"reserveId" : id
			},
			dataType : "json",
			success : function(result) {
				if(result.status == 0) {
					$("#a2").click();
				} else if(result.status == -1) {
					window.location.href = path + "/login.html";
				}
			},
			error : function() {
				
			}
		});
	}
}
//确认审核状态
function examine(id, status) {
	var adminName = getCookie("adminName");
	var token = getCookie("adminToken");
	var reason;
	var ok = true;
	if(token == null) {
		//ok = false;
		console.log("todo");
	}
	if(status == 2) {
		reason = prompt("请输入拒绝原因: ", "");
		if(reason == null || reason == "" || reason == undefined) {
			ok = false;
		}
	}
	
	if(ok) {
		$.ajax({
			url : path+"/a_reserve/examine_true.do",
			type : "post",
			data : {
				"adminToken" : token,
				"adminName" : adminName,
				"reserveId" : id,
				"status" : status,
				"reason" : reason
			},
			dataType : "json",
			success : function(result) {
				if(result.status == 0) {
					$("#a1").click();
				} else if(result.status == -1) {
					window.location.href = path + "/login.html";
				}
			},
			error : function() {
				alert("更新失败");
			}
		});
	}
}

//加载预约信息表
function loadReserve() {
    var adminName = getCookie("adminName");
    var token = getCookie("adminToken");
	var ok = true;
	$("#reserve-table tr").remove();
	if(token == null) {
		//ok = false;
		console.log("todo");
	}
	if(ok) {
		$.ajax({
			url : path+"/a_reserve/load_reserve.do",
			type : "post",
			data : {
				"adminToken" : token,
				"adminName" : adminName,
				"status" : status
			},
			dataType : "json",
			success : function(result) {
				if(result.status == 0) {
					var data = result.data;
					var id = null;
					var item = null;
					var user = null;
					var date = null;
					var start_time = null;
					var end_time = null;
					var reserve_time = null;
					for(var i = 0; i < data.length; i++) {
						id = data[i].reserve_id;
						item = data[i].reserve_item;
						user = data[i].user_nick;
						date = data[i].reserve_date;
						start_time = data[i].reserve_starttime
						end_time = data[i].reserve_endtime;
						reserve_time = json2TimeStamp(data[i].reserve_time);
						
						var str = "";
						str += '<tr><td>';
						str += item;
						str += '</td><td>';
						str += date;
						str += '</td><td>';
						str += start_time;
						str += '</td><td>';
						str += end_time;
						str += '</td><td>';
						str += reserve_time;
						str += '</td><td>';
						str += user;
						str += '</td>';
						
						if(status == 1) {
							str += '<td>';
							str +=     '<button class="examine_true btn btn-success btn-xs">通过</button>'
							str +=     '<button class="examine_false btn btn-warning btn-xs">拒绝</button>'
							str += '</td><td>';
							str += '未完成';
							str += '</td>';
						} else if(status == 2) {
							str += '<td>';
							str += '已完成';
							str += '</td>';
							str += '<td>';
							str += '<button class="completed btn btn-success btn-xs">确认完成</button>';
							str += '</td>';
						}else if(status == 3) {
							str += '<td>';
							str += '已完成';
							str += '</td>';
							str += '<td>';
							str += '已确认';
							str += '</td>';
						}
						
						str += '</tr>';
						var $tr = $(str);
						$tr.data("reserveId", id);
						$("#reserve-table").append($tr);
					}
				} else if(result.status == -1) {
					window.location.href = path + "/login.html";
				}
			},
			error : function() {
				alert("查询失败");
			}
		});
}
}