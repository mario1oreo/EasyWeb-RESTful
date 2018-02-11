$(function() {
	//渲染表格
	layui.table.render({
		elem : '#table',
		url : 'api/user',
 		where: {
	  		token : getToken()
		},
		page: true,
		cols: [[
			{type:'numbers'},
			{field:'userId', sort: true, title: 'ID'},
			{field:'userAccount', sort: true, title: '账号'},
			{field:'userNickname', sort: true, title: '用户名'},
			{field:'mobilePhone', sort: true, title: '手机号'},
			{field:'sex', sort: true, title: '性别'},
			{field:'roleName', sort: true,title: '角色'},
			{field:'createTime', sort: true, templet:function(d){ return layui.util.toDateString(d.createTime); }, title: '创建时间'},
			{field:'userStatus', sort: true, templet: '#statusTpl', title: '状态'},
			{align:'center', toolbar: '#barTpl', title: '操作'}
    	]]
	});
	
	//添加按钮点击事件
	$("#addBtn").click(function(){
		showEditModel(null);
	});
	
	//表单提交事件
	layui.form.on('submit(btnSubmit)', function(data) {
		data.field.token = getToken();
		data.field._method = $("#editForm").attr("method");
		layer.load(1);
		$.post("api/user", data.field, function(data){
			if(data.code==200){
				layer.msg(data.msg,{icon: 1});
				layer.closeAll('page');
				layui.table.reload('table', {});
				layer.closeAll('loading');
			}else{
				layer.msg(data.msg,{icon: 2});
			}
		}, "JSON");
		return false;
	});
	
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
 
		if(layEvent === 'edit'){ //修改
			showEditModel(data);
		} else if(layEvent === 'del'){ //删除
			doDelete(obj);
		}
	});
	
	//监听状态开关操作
	layui.form.on('switch(statusCB)', function(obj){
		updateStatus(obj);
	});
	
	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		doSearch(table);
	});
});

//显示表单弹窗
function showEditModel(data){
	layer.open({
		type: 1,
		title: data==null?"添加用户":"修改用户",
		area: '450px',
		offset: '120px',
		content: $("#addModel").html()
	});
	$("#editForm")[0].reset();
	$("#editForm").attr("method","POST");
	var selectItem = "";
	if(data!=null){
		$("#editForm input[name=userId]").val(data.userId);
		$("#editForm input[name=userAccount]").val(data.userAccount);
		$("#editForm input[name=userNickname]").val(data.userNickname);
		$("#editForm input[name=mobilePhone]").val(data.mobilePhone);
		$("#editForm").attr("method","PUT");
		selectItem = data.roleId;
		if('男'==data.sex){
			$("#sexMan").attr("checked","checked");
			$("#sexWoman").removeAttr("checked");
		}else{
			$("#sexWoman").attr("checked","checked");
			$("#sexMan").removeAttr("checked");
		}
		layui.form.render('radio');
	}
	$("#btnCancel").click(function(){
		layer.closeAll('page');
	});
	
	getRoles(selectItem);
}

//获取所有角色
var roles = null;
function getRoles(selectItem){
	if(roles!=null) {
		layui.laytpl(rolesSelect.innerHTML).render(roles, function(html){
			$("#role-select").html(html);
			$("#role-select").val(selectItem);
			layui.form.render('select');
			layer.closeAll('loading');
		});
	}else{
		layer.load(1);
		$.get("api/role",{
			token: getToken(),
			isDelete: 0
		}, function(data){
			if(0==data.code){
				roles = data.data;
				getRoles(selectItem);
			}
		});
	}
}

//删除
function doDelete(obj){
	layer.confirm('确定要删除吗？', function(index){
		layer.close(index);
		layer.load(1);
		$.ajax({
			url: "api/user/"+obj.data.userId+"?token="+getToken(), 
			type: "DELETE", 
			dataType: "JSON", 
			success: function(data){
				layer.closeAll('loading');
				if(data.code==200){
					layer.msg(data.msg,{icon: 1});
					obj.del();
				}else{
					layer.msg(data.msg,{icon: 2});
				}
			}
		});
	});
}

//更改状态
function updateStatus(obj){
	layer.load(1);
	var newStatus = obj.elem.checked?0:1;
	$.post("api/user/status", {
		userId: obj.elem.value,
		status: newStatus,
		_method: "PUT",
		token: getToken()
	}, function(data){
		layer.closeAll('loading');
		if(data.code==200){
			layui.table.reload('table', {});
		}else{
			layer.msg(data.msg,{icon: 2});
			layui.table.reload('table', {});
		}
	});
}

//搜索
function doSearch(table){
	var key = $("#searchKey").val();
	var value = $("#searchValue").val();
	if (value=='') {
		key = '';
	}
	layui.table.reload('table', {where: {searchKey: key,searchValue: value}});
}