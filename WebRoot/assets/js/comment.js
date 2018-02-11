var sideNavExpand = true;  //导航栏是否折叠
$(function() {
	if(getCurrentUser()==null){
		location.replace("login.html");
	}
	//切换导航栏按钮点击事件
	$("#switchNav").click(function(){
		switchNav(!sideNavExpand);
		sideNavExpand = !sideNavExpand;
	});
});

//折叠显示导航栏
function switchNav(expand){
	if(expand) {
		$(".layui-layout-admin .layui-side").animate({left:'0px'});
		$(".layui-layout-admin .layui-header .layui-logo").animate({left:'0px'});
		$(".layui-layout-admin .layui-header .layui-layout-left").animate({left:'250px'});
		$(".layui-layout-admin .layui-body").animate({left:'250px'});
		$(".layui-layout-admin .layui-footer").animate({left:'250px'});
		$('#switchNav>i').html('&#xe668;');
	} else {
		$(".layui-layout-admin .layui-side").animate({left:'-250px'});
		$(".layui-layout-admin .layui-header .layui-logo").animate({left:'-250px'});
		$(".layui-layout-admin .layui-header .layui-layout-left").animate({left:'0px'});
		$(".layui-layout-admin .layui-body").animate({left:'0px'});
		$(".layui-layout-admin .layui-footer").animate({left:'0px'});
		$('#switchNav>i').html('&#xe66b;');
	}
}

//获取当前token
function getToken() {
	return localStorage.getItem("token");
}

//获取当前登录的user
function getCurrentUser(){
	return JSON.parse(localStorage.getItem("user"));
}

//设置选中导航栏
function activeNav(path_name){
	$(".layui-side ul.layui-nav li.layui-nav-item .layui-nav-child dd").removeClass("layui-this");
	$(".layui-side ul.layui-nav li.layui-nav-item").removeClass("layui-nav-itemed");
	var $a = $(".layui-side ul.layui-nav>li.layui-nav-item>.layui-nav-child>dd>a[href='#!"+path_name+"']");
	$a.parent("dd").addClass("layui-this");
	$a.parent("dd").parent("dl.layui-nav-child").parent("li.layui-nav-item").addClass("layui-nav-itemed");
	layui.element.render('nav', 'index-nav');
}