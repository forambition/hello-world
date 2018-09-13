/**
 * 系统的主管理JS
 * 作者：吕海东
 */
//页面载入成功事件
$(document).ready(function(){
	//菜单连接的点击事件
	$("ul#side-menu li ul li a").on("click",function(event){
		//取得超链接的属性hred
		var href=$(this).attr("href");
		//载入href指定的页面到主页的maincontent区域
		$("div#maincontent").load(href);
		
		//阻止超链接的默认跳转行为
		event.preventDefault();
	});
});