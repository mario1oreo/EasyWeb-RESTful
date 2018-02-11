$(function() {
	//渲染表格
	layui.table.render({
		elem : '#table',
		url : 'api/loginRecord',
 		where: {
	  		token : getToken()
		},
		page: true,
		cols: [[
			{type:'numbers'},
			{field:'userAccount', sort: true, title: '账号'},
			{field:'userNickName', sort: true, title: '用户名'},
			{field:'ipAddress', sort: true, title: 'IP'},
			{field:'device', sort: true, title: '设备'},
			{field:'osName', sort: true, title: '设备类型'},
			{field:'browserType', sort: true, title: '浏览器'},
			{field:'createTime', sort: true, templet:function(d){ return layui.util.toDateString(d.createTime); }, title: '登录时间'}
    	]]
	});

	//时间范围
	layui.laydate.render({
		elem: '#searchDate',
		type: 'date',
		range: true,
		theme: '#393D49'
	});
	
	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		doSearch();
	});
});

//搜索
function doSearch(){
	var searchDate = $("#searchDate").val().split(" - ");
	var searchAccount = $("#searchAccount").val();
	layui.table.reload('table', {where: {startDate: searchDate[0], endDate: searchDate[1], account: searchAccount}});
}
