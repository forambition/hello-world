/**
 * 角色管理
 */
//页面载入成功事件
$(document).ready(function(){
	
	var roleNo=0; //选择的角色编号
	
	//显示角色列表表格
	$("table#roleGrid").jqGrid({
		 url: 'role/list/all/page.mvc',
         mtype: "POST",
		 styleUI : 'Bootstrap',
         datatype: "json",
         colModel: [
        	 { label: '编号', name: 'no',  width: 100 },
        	 { label: '名称', name: 'name', width: 100 },
        	 
         ],
         autowidth:true,
		 viewrecords: true,
         width: "100%",
		 height: 350,
         rowNum: 10,
         rowList:[2,10,15,20],
         pager: "#roleGridPager",
         jsonReader : {
             root: "rows",  //指定数据列表
             page: "page",
             total: "total",
             records: "records",
             repeatitems: true,
             id: "no"
         },
         multiselect:false,
         onSelectRow:function(id){
        	 roleNo=id;
         },
         ajaxGridOptions:{
        	 cache:false
         }
         
	});
	//点击增加角色处理
	$("a#RoleAddLink").on("click",function(){
		
		$("div#RoleDialog").load("role/add.html",function(){
			//取得系统功能列表，并生成功能复选框
			$.getJSON("function/list/all.mvc",function(functionList){
				if(functionList!=null){
					$.each(functionList,function(index,fm){
						$("span#functionCheckboxList").append("<label class='checkbox-inline'><input type='checkbox' name='rolefunctions' value='"+fm.no+"'>"+fm.name+"</label>");
					});
				}
			});
			
			$("form#RoleAddForm").validate({
				rules:{
					name:{
						required:true
					}
				},
				messages:{
					name:{
						required:"角色名称为空"
					}
				}
			});
			$("form#RoleAddForm").ajaxForm(function(resultData){
				BootstrapDialog.show({
					title:"角色操作提示",
					message:"<h4>"+resultData.message+"</h4>",
					buttons: [{
		                label: '关闭',
		                action: function(dialog) {
		                    	dialog.close();
		                    	$("table#roleGrid").trigger("reloadGrid");
		        				$("div#RoleDialog").html("");
		        				$("div#RoleDialog").dialog("close");
		        				$("div#RoleDialog").dialog("destroy");
		                	}
		            	}
					]
				});
				
			});
			//
			$("button#RoleCancelButton").on("click",function(){
				$("div#RoleDialog").html("");
				$("div#RoleDialog").dialog("close");
				$("div#RoleDialog").dialog("destroy");
			});
			$("div#RoleDialog").dialog({
				title:"增加新角色",
				width:500
				
			});
		});
	})
	
	//点击修改角色处理
	
	//点击删除角色处理
	
	//点击查看觉色处理
	$("a#RoleViewLink").on("click",function(){
		if(roleNo==0){
			BootstrapDialog.show({
				title:"角色操作提示",
				message:"<h4>请选择要查看的角色</h4>",
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
			$("div#RoleDialog").load("role/view.html",function(){
				//取得系统功能列表，并生成功能复选框
				$.getJSON("role/get.mvc",{roleNo:roleNo},function(functionResult){
					if(functionResult!=null){
						$("span#RoleName").html(functionResult.name);
					}
				});
				
				$.getJSON("role/functionListByRole.mvc",{roleNo:roleNo},function(functionList){
					if(functionList!=null){
						$.each(functionList,function(index,fm){
							$("span#functionNameList").append("<label class='checkbox-inline'>"+fm.name+"</label>");
						});
					}
				});
				
				//
				$("button#RoleCancelButton").on("click",function(){
					$("div#RoleDialog").html("");
					$("div#RoleDialog").dialog("close");
					$("div#RoleDialog").dialog("destroy");
				});
				$("div#RoleDialog").dialog({
					title:"查看角色",
					width:500
					
				});
			});
		}
	});
	
});