/**
 * 
 */
$(document).ready(function(){
	
	
	$("form#EmployeeLoginForm").validate({
		rules:{
			id:{
				required:true
			},
			password:{
				required:true
			}
		},
		messages:{
			id:{
				required:"账号为空"
			},
			password:{
				required:"密码为空"
			}
		}
	});
	
	$("form#EmployeeLoginForm").ajaxForm(function(loginResult){
		if(loginResult.status=="Y"){
			window.location.href="index.html";
		}
		else{
			BootstrapDialog.show({
				title:"员工登录提示",
				message:"<h4>"+loginResult.message+"</h4>",
				buttons: [{
	                label: '关闭',
	                action: function(dialog) {
	                    	dialog.close();
	                	}
	            	}
				]
			});
		}
	});
	
});