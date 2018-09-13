/**
 * 部门管理主JS
 * 开发者:吕海东
 */
//页面载入成功事件
$(document).ready(function(){
	var departmentNo=0; //选择的部门编号
	
	//请求取得部门列表的REST API
	function showDepartmentList(){
		
		//var department={no:1,code:'D01',name:"DDDD"};
		
		
		$("div#DepartmentMainContent").load("department/list.html",function(){
			
			$.getJSON("department/list/all.mvc",function(departmentList){
				var lines="";
				for(var i=0;i<departmentList.length;i++){
					lines=lines+"<tr data-no='"+departmentList[i].no+"'><td>"+departmentList[i].code+"</td><td>"+departmentList[i].name+"</td></tr>"
				}
				$("table#departmentListTable tbody").html(lines);
				//点击TR事件处理
				$("table#departmentListTable tbody tr").on("click",function(){
					departmentNo=$(this).attr("data-no");
					$("table#departmentListTable tbody tr").css("background-color","#FFFFFF")
					$(this).css("background-color","#EEEE");
					
				});
			});
		});
	}
	
	//点击增加按钮事件处理
	$("a#DepartmentAddLink").on("click",function(){
		$("div#DepartmentMainContent").load("department/add.html",function(event){
			
			
			$("button#DepartmentAddButton").on("click",function(){
				//取得输入的CODE和name值
				var code=$("input[name='code']").val();
				var name=$("input[name='name']").val();
				//请求REST API
				$.post("department/add.mvc",{code:code,name:name},function(resultData){
					if(resultData=="ok"){
						alert("增加部门成功");
					}
					else{
						alert("增加部门失败");
					}
					
					showDepartmentList();
				});
			});
			//点击返回按钮事件处理	
			$("button#DepartmentCancelButton").on("click",function(){
				showDepartmentList();
			});
		});
	});
	//点击修改按钮事件处理
	$("a#DepartmentModifyLink").on("click",function(){
		if(departmentNo==0){
			BootstrapDialog.show({
				title:"部门操作提示",
				message:"<h4>选择要修改的部门</h4>",
				buttons: [{
	                label: '关闭',
	                action: function(dialog) {
	                    	dialog.close();
	                	}
	            	}
				]
			});
		}
		else{
			$("div#DepartmentMainContent").load("department/modify.html",function(){
				//取得选择的部门的信息
				$.getJSON("department/get.mvc",{no:departmentNo},function(resultData){
					$("input[name='code']").val(resultData.code);
					$("input[name='name']").val(resultData.name);
				});
				//点击修改提交按钮处理
				$("button#DepartmentModifyButton").on("click",function(){
					//取得输入的CODE和name值
					var code=$("input[name='code']").val();
					var name=$("input[name='name']").val();
					//请求REST API
					$.post("department/modify.mvc",{no:departmentNo,code:code,name:name},function(resultData){
						if(resultData=="ok"){
							BootstrapDialog.show({
								title:"部门操作提示",
								message:"<h4>修改部门成功</h4>",
								buttons: [{
					                label: '关闭',
					                action: function(dialog) {
					                    	dialog.close();
					                	}
					            	}
								]
							});
						}
						else{
							BootstrapDialog.show({
								title:"部门操作提示",
								message:"<h4>修改部门失败</h4>",
								buttons: [{
					                label: '关闭',
					                action: function(dialog) {
					                    	dialog.close();
					                	}
					            	}
								]
							});
						}
						
						showDepartmentList();
					});
				});
				//点击返回按钮事件处理	
				$("button#DepartmentCancelButton").on("click",function(){
					showDepartmentList();
				});
				
				
			});
		}
	});
	
	//点击删除按钮事件处理
	$("a#DepartmentDeleteLink").on("click",function(){
		if(departmentNo==0){
			BootstrapDialog.show({
				title:"部门操作提示",
				message:"<h4>请选择要删除的部门</h4>",
				buttons: [{
	                label: '关闭',
	                action: function(dialog) {
	                    	dialog.close();
	                	}
	            	}
				]
			});
		}
		else{
			//检查选择的部门是否可以被删除
			$.getJSON("department/checkcandelete.mvc",{departmentNo:departmentNo},function(checkResult){
				if(checkResult.status=="N"){
					BootstrapDialog.show({
						title:"部门操作提示",
						message:"<h4>"+checkResult.message+"</h4>",
						buttons: [{
			                label: '关闭',
			                action: function(dialog) {
			                    	dialog.close();
			                	}
			            	}
						]
					});
				}
				else{
					//可以删除后的确认
					BootstrapDialog.confirm({
			            title: '删除部门警告',
			            message: '确认要删除此部门么?',
			            type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
			            closable: true, // <-- Default value is false
			            btnCancelLabel: '取消', // <-- Default value is 'Cancel',
			            btnOKLabel: '确认', // <-- Default value is 'OK',
			            btnOKClass: 'btn-warning', // <-- If you didn't specify it, dialog type will be used,
			            callback: function(result) {
			                // result will be true if button was click, while it will be false if users close the dialog directly.
			                if(result) {
			                	//选择了确认删除
			                	$.post("department/delete.mvc",{no:departmentNo},function(resultData){
									if(resultData=="ok"){
										BootstrapDialog.show({
											title:"部门操作提示",
											message:"<h4>删除部门成功</h4>",
											buttons: [{
								                label: '关闭',
								                action: function(dialog) {
								                    	dialog.close();
								                	}
								            	}
											]
										});
									}
									else{
										BootstrapDialog.show({
											title:"部门操作提示",
											message:"<h4>删除部门失败</h4>",
											buttons: [{
								                label: '关闭',
								                action: function(dialog) {
								                    	dialog.close();
								                	}
								            	}
											]
										});
									}
									showDepartmentList();
								});
			                }
			            }
			        });
					/* 默认的没有定制的确认框
					BootstrapDialog.confirm('确认要删除此部门么?', function(result){
				            if(result) {
				            	$.post("department/delete.mvc",{no:departmentNo},function(resultData){
									if(resultData=="ok"){
										BootstrapDialog.show({
											title:"部门操作提示",
											message:"<h4>删除部门成功</h4>",
											buttons: [{
								                label: '关闭',
								                action: function(dialog) {
								                    	dialog.close();
								                	}
								            	}
											]
										});
									}
									else{
										BootstrapDialog.show({
											title:"部门操作提示",
											message:"<h4>删除部门失败</h4>",
											buttons: [{
								                label: '关闭',
								                action: function(dialog) {
								                    	dialog.close();
								                	}
								            	}
											]
										});
									}
									showDepartmentList();
								});
				            }
				    });
					*/
					
				}
			});
			
			
			
		}
	});
	
	//点击查看按钮事件处理
	$("a#DepartmentViewLink").on("click",function(){
		if(departmentNo==0){
			alert("请选择要查看的部门");
		}
		else{
			$("div#DepartmentMainContent").load("department/view.html",function(){
				//取得选择的部门的信息
				$.get("department/get.mvc",{no:departmentNo},function(resultData){
					//alert(resultData);
					/* JSON字符串与JavaScript对象的相互转换
					alert(resultData);
					var jsonstring=JSON.stringify(resultData);
					alert(JSON.stringify(resultData));
					alert(JSON.parse(jsonstring));
					*/
					$("span#departmentCode").html(resultData.code);
					$("span#departmentName").html(resultData.name);
					
				});
				
				//点击返回按钮事件处理	
				$("button#DepartmentCancelButton").on("click",function(){
					showDepartmentList();
				});
				
			});
		}
	});
	//点击部门统计信息按钮事件处理
	$("a#DepartmentSummaryInfoLink").on("click",function(){
		$("div#DepartmentMainContent").load("department/summaryinfo.html",function(){
			//
			// 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('summaryinfomain'));

	        // 指定图表的配置项和数据
	        var option = {
	            
	            tooltip: {},
	            legend: {
	                data:['员工人数']
	            },
	            xAxis: {
	                data: ["财务部","后勤部","人力部","开发部","市场部","物流部"]
	            },
	            yAxis: {},
	            series: [{
	                name: '员工人数',
	                type: 'bar',
	                data: [5, 20, 36, 10, 10, 20]
	            }]
	        };

	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
	        
			//点击返回按钮事件处理	
			$("button#DepartmentCancelButton").on("click",function(){
				showDepartmentList();
			});
		});
		
	});
	//初始载入部门列表显示页面并取得部门列表
	showDepartmentList();
	
});