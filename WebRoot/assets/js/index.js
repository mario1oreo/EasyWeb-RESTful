var refreshNav = true;
$(function() {
	initUserInfo();  //获取用户信息
	initNav();  //获取导航栏
	
	//路由注册
	Q.reg('home',function(){
		load('home');
	}).reg('system',function(path){
		load('system/'+path);
	}).init({
		index: 'home'
	});
	
	//点击导航切换页面时不刷新导航,其他方式切换页面要刷新导航
	layui.element.on('nav(index-nav)', function(elem){
		refreshNav = false;
	});
});

//异步加载子页面
function load(path) {
	if(refreshNav){
		activeNav(path);
	}
	refreshNav = true;
	$("#main-content").load("views/" + path +".html",function(){
		layui.element.render('breadcrumb');
		layui.form.render('select');
	});
}

//获取左侧导航栏
function initNav(){
	var indexNavStr = sessionStorage.getItem("index-nav");
	var indexNav = JSON.parse(indexNavStr);
	if(indexNav==null){
		$.get("api/menu", {
			token : getToken()
		}, function (data) {
			if(200==data.code){
				sessionStorage.setItem("index-nav",JSON.stringify(data.menus));
				initNav();
			}else{
				layer.msg("获取导航失败",{icon: 2});
			}
		},"json");
	}else{
		layui.laytpl(sideNav.innerHTML).render(indexNav, function(html){
			$("#index-nav").html(html);
			layui.element.render('nav', 'index-nav');
		});
	}
}

//获取用户信息
function initUserInfo(){
	try {
		var user = getCurrentUser();
		//$("#userHead").attr("src", user.);
		$("#userNickName").text(user.userNickname);
	} catch (e) {
		console.log(e.message);
	}
}

//退出登录
function loginOut(){
	localStorage.removeItem("token");
	localStorage.removeItem("user");
	sessionStorage.removeItem("index-nav");
	layer.load(1);
	$.ajax({
		url: "api/login?token="+getToken(), 
		type: "DELETE", 
		dataType: "JSON", 
		success: function(data){
			location.replace("login.html");
		}
	});
}