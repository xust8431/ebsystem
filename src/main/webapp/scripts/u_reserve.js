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
                        sli += '   <a href="javascript:check();">已完成</a>';
                        sli += '</td>';
                        sli += '<td>';
                        sli += '</td>';
                        sli += '</tr>';
                        $li = $(sli);
                        $("#tbody").append($li);
					}
					if(datas[i].reserve_examine_status == 2 && datas[i].reserve_complete_status == 0 ){
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
	                        sli += '   <a href="javascript:check();">审核失败</a>';
	                        sli += '</td>';
	                        sli += '<td>';
	                        sli += '</td>';
	                        sli += '</tr>';
	                        $li = $(sli);
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
                        sli += '   <a href="javascript:check();">未完成</a>';
                        sli += '</td>';
                        sli += '<td>';
                        sli += ' <button class="btn btn-warning btn-xs">';
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
                        sli += '   <a href="javascript:check();">待审核</a>';
                        sli += '</td>';
                        sli += '<td>';
                        sli += ' <button class="btn btn-warning btn-xs">';
                        sli += '   &nbsp;&nbsp;<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;';
                        sli += ' </button>';
                        sli += '</td>';
                        sli += '</tr>';
                        $li = $(sli);
                        $li.data("id",id);
                        $("#tbody").append($li);
					}
					if(datas[i].complete_status == 2){
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
                        sli += '   <a href="javascript:check();">已取消</a>';
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
	sli += '<td><input type="text" value="'+password+'" id="password"></td>';
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
