/**
 * 系统的主管理JS
 * 作者：吕海东
 * 
 */
//jQuery.ajaxSettings.traditional = true;

$.ajaxSetup ({ 
	traditional:true,
    cache: false //close AJAX cache 
});
 
//jQuery.ajaxSettings.cache =false;  //取消jQuery AJAX缓存
//页面载入成功事件
$(document).ready(function(){
	
	var employeeInfo=null;
	
	$.getJSON("employee/checkLogin.mvc",function(checkResult){
		if(checkResult.status=="N"){
			location.href="login.html";
		}
		else{
			//取得登录员工的信息
			$.getJSON("employee/getLoginEmployee.mvc",function(employeeData){
				employeeInfo=employeeData;
				$("span#loginEmployeeName").html(employeeInfo.name);
				//取得系统的模块列表，生成模块导航菜单
				$.getJSON("module/list/all.mvc",function(moduleList){
					$.each(moduleList,function(index,module){
						$("ul#side-menu").append("<li><a href='#'><i class='fa fa-bar-chart-o fa-fw'></i>"+module.name+"<span class='fa arrow'></span></a><ul class='nav nav-second-level' id='module_"+module.no+"' ></ul></li>");
						
					});
					//取得此登录员工被授予的功能列表
					$.getJSON("employee/getGrantFunctions.mvc",{id:employeeInfo.id},function(functionList){
						$.each(functionList,function(indexofFun,fm){
							$("ul#module_"+fm.module.no).append("<li><a href='"+fm.url+"'>"+fm.name+"</a></li>");
						});
						
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
					//启用伸缩菜单
					$('#side-menu').metisMenu();
					
					
					
				});
			});
			
			
			
			
			
			//
			
			//启动自动载入Home页面
			$("div#maincontent").load("home/main.html");
			//
			$("a#EmployeeLogoutLink").on("click",function(){
				$.getJSON("employee/logout.mvc",function(){
					location.href="login.html";
				});
				
			});
			
			
			
			$("ul#side-menu li a[href='home/main.html']").on("click",function(event){
				$("div#maincontent").load("home/main.html");
				event.preventDefault();
			});
		}
	});
	
	
});