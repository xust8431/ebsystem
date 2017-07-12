//显示用户自己预约实验的信息
function ReserveMsg(userNick){
	$.ajax({
		url:path+"/reserve/select.do",
		type:"post",
		data:{"userNick":userNick},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var datas = result.data;
				for(var i=0;i<datas.length;i++){
					var item = datas[i].reserve_item;
					var date = datas[i].reserve_date;
					var starttime = datas[i].reserve_starttime;
					var endtime = datas[i].reserve_endtime;
					var time = datas[i].reserve_time;
					var id = datas[i].reserve_id;
					var sli = "";
					var $li = null;
					//$("#tbody").remove();
					if(datas[i].reserve_examine_status == 1 && datas[i].reserve_complete_status == 1){
						sli = '<tr class="success">';
						sli += '<td>';
						sli += item;
						sli += '</td>';
						sli += '<td>';
						sli +=  date;
						sli += '</td>';
						sli += '<td>';
						sli += starttime;
						sli += '</td>';
						sli += '<td>';
						sli += endtime;
                        sli += '</td>';
                        sli += '<td>';
                        sli += json2TimeStamp(time);
                        sli += ' </td>';
                        sli += '<td>';
                        sli += '   <a class="reason-info" href="javascript:;">已完成</a>';
                        sli += '</td>';
                        sli += '<td>';
                        sli += '</td>';
                        sli += '</tr>';
                        $li = $(sli);
                        $("#tbody").append($li);
					}
					if(datas[i].reserve_examine_status == 2 && datas[i].reserve_complete_status == 0 ){
							sli += '<tr class="defeat">';
							sli += '<td>';
							sli += item;
							sli += '</td>';
							sli += '<td>';
							sli +=  date;
							sli += '</td>';
							sli += '<td>';
							sli += starttime;
							sli += '</td>';
							sli += '<td>';
							sli += endtime;
	                        sli += '</td>';
	                        sli += '<td>';
	                        sli += json2TimeStamp(time);
	                        sli += ' </td>';
	                        sli += '<td>';
	                        sli += '   <a class="reason-info" href="javascript:;">审核失败</a>';
	                        sli += '</td>';
	                        sli += '<td>';
	                        sli += '</td>';
	                        sli += '</tr>';
	                        $li = $(sli);
	                        $li.data("id",id);
	                        $("#tbody").append($li);
					}
					if(datas[i].reserve_examine_status == 1 && datas[i].reserve_complete_status == 0 ){
						sli += '<tr class="info">';
						sli += '<td>';
						sli += item;
						sli += '</td>';
						sli += '<td>';
						sli +=  date;
						sli += '</td>';
						sli += '<td>';
						sli += starttime;
						sli += '</td>';
						sli += '<td>';
						sli += endtime;
                        sli += '</td>';
                        sli += '<td>';
                        sli += json2TimeStamp(time);
                        sli += ' </td>';
                        sli += '<td>';
                        sli += '   <a class="reason-info" href="javascript:;">未完成</a>';
                        sli += '</td>';
                        sli += '<td>';
                        sli += ' <button class="btn btn-warning btn-xs delete-button">';
                        sli += '   &nbsp;&nbsp;<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;';
                        sli += ' </button>';
                        sli += '</td>';
                        sli += '</tr>';
                        $li = $(sli);
                        $li.data("id",id);
                        $("#tbody").append($li);
				}
					
					if(datas[i].reserve_examine_status == 0 && datas[i].reserve_complete_status == 0){
						sli += '<tr class="warning">';
						sli += '<td>';
						sli += item;
						sli += '</td>';
						sli += '<td>';
						sli +=  date;
						sli += '</td>';
						sli += '<td>';
						sli += starttime;
						sli += '</td>';
						sli += '<td>';
						sli += endtime;
                        sli += '</td>';
                        sli += '<td>';
                        sli += json2TimeStamp(time);
                        sli += ' </td>';
                        sli += '<td>';
                        sli += '   <a class="reason-info" href="javascript:;">待审核</a>';
                        sli += '</td>';
                        sli += '<td>';
                        sli += ' <button class="btn btn-warning btn-xs delete-button">';
                        sli += '   &nbsp;&nbsp;<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;';
                        sli += ' </button>';
                        sli += '</td>';
                        sli += '</tr>';
                        $li = $(sli);
                        $li.data("id",id);
                        $("#tbody").append($li);
					}
					if(datas[i].reserve_complete_status == 2){
						sli += '<tr class="cancel">';
						sli += '<td>';
						sli += item;
						sli += '</td>';
						sli += '<td>';
						sli +=  date;
						sli += '</td>';
						sli += '<td>';
						sli += starttime;
						sli += '</td>';
						sli += '<td>';
						sli += endtime;
                        sli += '</td>';
                        sli += '<td>';
                        sli += json2TimeStamp(time);
                        sli += ' </td>';
                        sli += '<td>';
                        sli += '   <a class="reason-info" href="javascript:;">已取消</a>';
                        sli += '</td>';
                        sli += '<td>';
                        sli += '</td>';
                        sli += '</tr>';
                        $li = $(sli);
                        $("#tbody").append($li);
				}	
				}
			}else if(result.status == 1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("查看已预约信息失败");
		}
	});
}

//显示用户信息
function showMyMessage(userNick){
	$.ajax({
		url:path+"/user/show.do",
		type:"post",
		data:{"userNick":userNick},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var nick = result.data.user_nick;
				var name = result.data.user_name;
				var phone = result.data.user_phone;
				var tel = result.data.user_tel;
				var company = result.data.user_company;
				var email = result.data.user_email;
				var password = getCookie("userPassword");
				var reputation = result.data.user_reputation;
				messageTable(nick,name,phone,tel,company,email,password,reputation)
			}else if(result.status == 1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("查看信息失败");
		}
	});
}

//拼接用户信息表
function messageTable(nick,name,phone,tel,company,email,password,reputation){
	$("#message_table tr").remove();
	var sli = "";
	sli += '<tr>';
	sli += '<th>用户名(不可修改)</th>';
	sli += '<td>'+nick+'</td>';
	sli += '</tr>';
	sli += '<tr>';
	sli += '<th>真实姓名(不可修改)</th>';
	sli += '<td>'+name+'</td>';
	sli += '</tr>';
	sli += '<tr>';
	sli += '<th>联系电话</th>';
	sli += '<td><input type="text" name="" value="'+phone+'" id="phone"></td>';
	sli += '</tr>';
	sli += '<tr>';
	sli += '<th>备用电话</th>';
	sli += '<td><input type="text" value="'+tel+'" id="tel"></td>';
	sli += '</tr>';
	sli += '<tr>';
	sli += '<th>工作单位</th>';
	sli += '<td><input type="text" value="'+company+'" id="company"></td>';
	sli += '</tr>';
	sli += '<tr>';
	sli += '<th>电子邮箱</th>';
	sli += '<td><input type="email" value="'+email+'" id="email"></td>';
	sli += '</tr>';
	sli += '<tr>';
	sli += '<th>密码</th>';
	sli += '<td><input type="password" value="'+password+'" id="password"></td>';
	sli += '</tr>';
	sli += '<tr>';
	sli += '<th>信誉度(不可修改)</th>';
	sli += '<td><input type="text" value="'+reputation+'" readonly></td>';
	sli += '</tr>';
	var $li = $(sli);
	$("#message_table").append($li);
}

//更新用户信息
function updateUserMsg(userNick){
	//alert("-----");
	var userPhone = $("#phone").val().trim();
	var userTel = $("#tel").val().trim();
	var company = $("#company").val().trim();
	var email = $("#email").val().trim();
	var password = $("#password").val().trim();
	addCookie("userPassword",password,2);
	//console.log(userPhone);
	//console.log(userTel);
	$.ajax({
		url:path+"/user/update.do",
		type:"post",
		data:{"userNick":userNick,"userPhone":userPhone,"userCompany":company,
			"userTel":userTel,"userEmail":email,"userPassword":password},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				//$("#message_table").remove();
				showMyMessage(userNick);
				alert("修改成功");
			}
		},
		error:function(){
			alert("修改信息失败");
		}
	});
}
//取消预约
function cancelReserve(userNick, $tr){
	var id = $tr.data("id");
	var date = $tr.find("td").eq(1).text();
	alert(id + ":" + date);
	$.ajax({
		url:path+"/reserve/updateCancel.do",
		type:"post",
		data:{"reserveId":id,"reserveDate":date},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				$("#tbody tr").remove();
				ReserveMsg(userNick);
			}else if(result.status == 1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("取消预约失败");
		}
	});
}

//拼接时间下拉选
function SelectTime(result){
	var startTime = result.data;
	var i;
	var sli;
	var $li;
	$("#startTime option").remove();
	sli = "";
	sli +='<option>---</option>';
	for(i=0;i<startTime.length;i++){
		//console.log(startTime[i]);
		sli +='<option>'+startTime[i]+'</option>';
	}
	$li = $(sli);
	$("#startTime").append($li);
}
//拼接日期下拉选
function dateSelect() {
	var i;
	var sli;
	var $li;
	var time = [];
	time = getNowFormatDate();
	
	for(i=0;i<time.length;i++){
		sli = "";
		sli +='<option>'+time[i]+'</option>';
		$li = $(sli);
		$("#date").append($li);
	}
}

//日期下拉框点击事件
function SelectDate(){
	var reserveItem = $("#item").val();
	var reserveHour = $("#hour").val().trim().slice(0,1);//截取X小时中的X
	var reserveDate = $("#date").val();
	if(reserveItem == ""){
		ok = false;
		alert("您没有选择实验项目");
		return ;
	}
	if(reserveHour == ""){
		ok = false;
		alert("您没有选择时长");
		return ;
	}
	
	if(ok){
		$.ajax({
			url:path+"/reserve/selectDate.do",
			type:"post",
			data:{"reserveHour":reserveHour,"reserveDate":reserveDate},
			dataType:"json",
			success:function(result){
				if(result.status == 0){
					SelectTime(result);	
				} else if(result.status == 1){
					alert(result.msg);
				}else if(result.status == 2){
					SelectTime(result);
				}else if(result.status == 3){
					SelectTime(result);
				}else if(result.status == 4){
					SelectTime(result);
				}else if(result.status == 5){
					SelectTime(result);
				}else if(result.status == 6){
					SelectTime(result);
				}else if(result.status == 7){
					alert(result.msg);
				}else if(result.status == 8){
					SelectTime(result);
				}else if(result.status == 9){
					SelectTime(result);
				}
			},
			error:function(){
				alert("选择实验日期失败");
			}
		});
	}
}

//添加预约
function addReserve(userNick) {
	var item = $("#item").val();
	//console.log(reserveItem);
	var hour = $("#hour").val().slice(0,1);;
	//console.log(reserveHour);
	var date = $("#date").val();
	var startTime = $("#startTime").val();
	var reputation = getCookie("userReputation");
	//console.log(date + "-" + startTime + "-" + reputation);
	if (item == "") {
		ok = false;
		alert("您没有选择实验项目");
		return;
	}
	if (hour == "") {
		ok = false;
		alert("您没有选择时长");
		return;
	}
	if (date == "") {
		ok = false;
		alert("您没有选择日期");
		return;
	}
	if(startTime = "") {
		ok = false;
		alert("您没有选择开始时间");
	}

	if (ok) {
		$.ajax({
			url : path + "/reserve/addReserve.do",
			type : "post",
			data : {
				"item" : item,
				"hour" : hour,
				"date" : date,
				"startTime" : startTime,
				"reputation" : reputation,
				"userNick" : userNick
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 0) {
					alert(result.msg);
					ReserveMsg(userNick);
				} else if (result.status == 1) {
					alert(result.msg);
				}
			},
			error : function() {
				alert("预约失败");
			}
		});
	}
}