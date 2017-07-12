//更新设置
function updateSet() {
	var token = getCookie("adminToken");
	var adminName = getCookie("adminName");
	var timeStr = $("#week-select").val()+"/"+$("#start").val()+"-"+$("#end").val();
	var timeScope = $("#default-scope").val();
	var mode = $("#mode").val();
	var ok = true;
	if(token == null) {
		//ok = false;
		console.log("todo");
	}
	if(ok) {
			$.ajax({
				url : path+"/set/set_time.do",
				type : "post",
				data : {
					"adminToken" : token,
					"adminName" : adminName,
					"timeStr" : timeStr,
					"timeScope" : timeScope,
					"mode" : mode
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
//加载设置
function loadSet() {
	var token = getCookie("adminToken");
	var adminName = getCookie("adminName");
	var ok = true;
	$("#ban-time tr").remove();
	if(token == null) {
		//ok = false;
		console.log("todo");
	}
	if(ok) {
		$.ajax({
			url : path+"/set/load.do",
			type : "post",
			data : {
				"adminToken" : token,
				"adminName" : adminName
			},
			dataType : "json",
			success :function(result) {
				if(result.status == 0) {
					var data = result.data;
					var banTimeStr = data.banTimeStr;
					var timeScope = data.timeScope;
					var mode = data.selectMode;
					var banTimeArray = banTimeStr.split(",");
					for(var i = 0; i < banTimeArray.length-1; i++) {
						var str = "";
						str += '<tr><th class="ban-time-th">';
						str += "星期";
						str += banTimeArray[i].split("/")[0];
						str += '</th><td class="ban-time-td">';
						str += banTimeArray[i].split("/")[1];
						str += '</td><td class="div-center">';
						str += '   <button class="btn btn-warning btn-xs delete-set">';
						str += '       &nbsp;&nbsp;&nbsp;&nbsp;';
						str += '       <span class="glyphicon glyphicon-remove"></span>';
						str += '       &nbsp;&nbsp;&nbsp;&nbsp;';
						str += '   </button>';
						str += '</td></tr>';
						$tr = $(str);
						$("#ban-time").append($tr);
					}
					$("#default-scope").val(timeScope);
					$("#mode").val(mode);
				} else if(result.status == -1) {
					window.location.href = path + "/login.html";
				}
			},
			error : function() {
				alert("加载失败");
			}
		});
	}
}