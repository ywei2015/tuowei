var request = fastDev.Browser.getRequest();
var taskId = request['taskId'];
var taskstatus = request['c_status'];
var edit = request['edit'];

function onFormBeforeReady(){
	if (edit == "detail") {
		this.setOptions({
			dataSource : 'iitask_getTaskInfoByTaskId.action?c_task_id='+taskId
		});
	}
	if (edit == "confirm") {
		this.setOptions({
			dataSource : 'iitask_getTaskInfoByTaskId.action?c_task_id='+taskId
		});
	}
}

function setConfirmModel(){
	console.info("--1--");
	var iobj = fastDev.getInstance("taskinfo.c_exec_username");
	console.info(iobj.getValue());
	$("#taskinfo.c_exec_username").attr("readonly","false");
	$("#taskinfo.c_start_time").attr("readonly","false");
	
	//console.info($("#taskinfo.c_exec_username").value);
	/*$("#taskinfo.c_exec_username").value = "add";
	$("#taskinfo.c_exec_username").removeAttr("readonly");   //.attr("readonly","");
	$("taskinfo.c_start_time").attr("readonly",false);*/
	console.info("--2--");
}

function onFormAfterDataRender(){
	//var data = this.getItems();
	//console.info(data);
	var select_status=fastDev.getInstance("taskinfo.c_status1");
	select_status.setValue(fastDev.getInstance("taskinfo.c_status").getValue());
	
	if (edit == "confirm") {
		setConfirmModel(); //设置一些控件为可修改状态
	}
}

function slideToggle_StepTable(tableid){
	var tableid = "#StepTable"+tableid;
	//console.info(tableid);
	var ishidden = $(tableid).css("display"); //$(tableid).is(":hidden");
	//console.info(ishidden);
	if(ishidden!="none"){
		$(tableid).hide();
	}else{
		$(tableid).show();
	}
	//$("#StepTable"+tableid).slideToggle("slow");
}

var taskitemids = new Array();
fastDev.post("iitask_getTaskInfoItemListByMap.action?c_task_id="+taskId,{
	success : function(data){
		var StepDetailHtml = "";
		if(data!=undefined){
			for(var i=0;i<data.length;i++){
				taskitemids.push(data[i].c_actitem_index);
				StepDetailHtml +='<div class="toggler"><div id="effect" class="ui-widget-content ui-corner-all">';
				StepDetailHtml +='<h3 class="ui-widget-header ui-corner-all" onclick="slideToggle_StepTable('+i+');">第'+data[i].c_actitem_index+'步，----'+data[i].c_actitem_name+'" </h3>';
				StepDetailHtml +='<table id="StepTable'+i+'" class="ui-form-table">';
				//StepDetailHtml +='<tr valign="middle"><td class="ui-form-table-dt" style="width:100px;">结果收集要求：</td><td class="ui-form-table-dd">'+data[i].c_exec_getdatatype+'</td>';
				//StepDetailHtml +='<tr valign="middle"><td class="ui-form-table-dt" style="width:100px;">验证收集要求：</td><td class="ui-form-table-dd">'+data[i].c_check_getdatatype+'</td>';
				if(taskstatus==33){ 
					StepDetailHtml +='<tr valign="middle"><td class="ui-form-table-dd" style="width:100px;">收集结果：</td><td id="TaskStepResult'+data[i].c_actitem_index+'" class="ui-form-table-dd">'; 
				}
				//StepDetailHtml +='<tr valign="middle"><td class="ui-form-table-dt" style="width:100px;">收集结果：</td><td id="TaskStepResult" class="ui-form-table-dd">';
				StepDetailHtml +='</td></tr>';
				StepDetailHtml +='</table>';
				StepDetailHtml +='</div></div>';
			}
		}
		document.getElementById('divDetails').innerHTML = StepDetailHtml;  //$("divDetails").append(StepDetailHtml);
		if(taskstatus==33){
			showstepresultdetail();
		}
	}
});

function showStepResult(actitemindex)
{
	fastDev.post("iitask_getTaskStepResultListByMap.action?c_task_id="+taskId+"&c_actitem_index="+actitemindex, {
		success:function(resdata){
			if(resdata!=undefined){
				var ResultDetailHtml = "";
				ResultDetailHtml +='<table class="ui-form-table">';
				//console.info(resdata.length);
				for(var j=0;j<resdata.length;j++){
					ResultDetailHtml += '<tr valign="middle">';
					ResultDetailHtml += '<td class="ui-form-table-dd" style="width:5%;">'+resdata[j].c_step_index+'</td>';
					ResultDetailHtml += '<td class="ui-form-table-dd" style="width:15%;">'+resdata[j].c_tracefun_name+'</td>';
					ResultDetailHtml += '<td class="ui-form-table-dd" style="width:80%;">';
					if(resdata[j].c_isfile=="1"){
						if(resdata[j].c_tracefun_id=="1"){
							ResultDetailHtml += '<img height=200px width=300px src="'+(resdata[j].c_file_path==undefined?"":resdata[j].c_file_path)+'"/>';
						}else{
							ResultDetailHtml += '<a href="'+(resdata[j].c_file_path==undefined?"":resdata[j].c_file_path)+'">'+(resdata[j].c_file_title==undefined?"":resdata[j].c_file_title)+'</a>';
						}
					}else{
						ResultDetailHtml += (resdata[j].c_result==undefined?"":resdata[j].c_result);
					}
					ResultDetailHtml += '</td>';
					ResultDetailHtml += '</tr>';
				}
				ResultDetailHtml +='</table>';
				document.getElementById('TaskStepResult'+actitemindex).innerHTML = ResultDetailHtml; 
			}
		}
	});
}

function showstepresultdetail(){
	//console.info(taskitemids);
	var rs_count = taskitemids.length;
	for(var k=0;k<rs_count;k++){
		var tmpid = taskitemids.pop();
		showStepResult(tmpid);
	};
}

/*
fastDev.post("taskEntry_queryEntryList.action?action=1&taskMouldPojo.c_task_id="+taskId,{
	success : function(data){
		var innerHTml = "";
		for(var i=0;i<data.length;i++){
			innerHTml +='<table class="ui-form-table"><tr valign="middle"><td class="ui-form-table-dd" colspan=8>步骤【'+(i+1)+'】</td></tr>';
			innerHTml +='<tr valign="middle"><td class="ui-form-table-dt" style="width:100px;">步骤提示名称：</td><td class="ui-form-table-dd">'+data[i].c_step_prompt+'</td>';
			innerHTml +='<td class="ui-form-table-dt" style="width:100px;">动作收集方式：</td><td class="ui-form-table-dd">'+(data[i].c_tracefun_name==undefined?"":data[i].c_tracefun_name)+'</td>';
			innerHTml +='<td class="ui-form-table-dt" style="width:100px;">是否标准动作：</td><td class="ui-form-table-dd">'+data[i].c_isstd_name+'</td>';
			innerHTml +='<td class="ui-form-table-dt" style="width:100px;">作业区域名称：</td><td class="ui-form-table-dd">'+(data[i].areaname==undefined?"":data[i].areaname)+'</td></tr>';
			innerHTml +='<tr valign="middle"><td class="ui-form-table-dt" style="width:100px;">作业对象类别：</td><td class="ui-form-table-dd">'+(data[i].c_objtype_name==undefined?"":data[i].c_objtype_name)+'</td>';
			innerHTml +='<td class="ui-form-table-dt" style="width:100px;">作业对象名称：</td><td class="ui-form-table-dd">'+(data[i].c_obj_name==undefined?"":data[i].c_obj_name)+'</td>';
			innerHTml +='<td class="ui-form-table-dt" style="width:100px;">执行参数：</td><td class="ui-form-table-dd">'+(data[i].c_step_params==undefined?"":data[i].c_step_params)+'</td>';
			innerHTml +='<td class="ui-form-table-dt" style="width:100px;">是否异常：</td><td class="ui-form-table-dd">'+(data[i].c_iserror_name==undefined?"":data[i].c_iserror_name)+'</td></tr>';
			innerHTml +='<tr valign="middle"> <td class="ui-form-table-dt" style="width:100px;">异常批号：</td><td class="ui-form-table-dd">'+(data[i].c_err_sn==undefined?"":data[i].c_err_sn)+'</td>';
			innerHTml +='<td class="ui-form-table-dt" style="width:100px;">记录时间：</td><td class="ui-form-table-dd">'+(data[i].c_exec_time==undefined?"":data[i].c_exec_time)+'</td>';
			innerHTml +='<td class="ui-form-table-dt" style="width:100px;">状态：</td><td class="ui-form-table-dd">'+(data[i].c_status_name==undefined?"":data[i].c_status_name)+'</td>';
			innerHTml +='<td class="ui-form-table-dt" style="width:100px;">备注：</td><td class="ui-form-table-dd">'+(data[i].c_remark==undefined?"":data[i].c_remark)+'</td></tr>';
		
			if(data[i].c_tracefunid == "1"){ 
				innerHTml +='<tr valign="middle"><td class="ui-form-table-dt" style="width:100px;">执行结果：</td><td class="ui-form-table-dd" colspan=7><img height=300px width=200px src="'+data[i].c_file_path+'"/></td></tr></table>';					
			}else{
				innerHTml +='<tr valign="middle"><td class="ui-form-table-dt" style="width:100px;">执行结果：</td><td class="ui-form-table-dd" colspan=7>'+(data[i].c_result==undefined?"":data[i].c_result)+'</td></tr></table>';					
			}
		}
		document.getElementById('divDetails').innerHTML = innerHTml;
	}
});

function doSubmit() {
	var form = fastDev.getInstance('checkForm');
	form.setOptions({
		action : "taskEdit_editData.action?action=1&edit=" + edit
	});	 
	form.submit();
}
function doReset() {
	fastDev.getInstance('checkForm').resetData();
}

//表单提交后回调
function onSubmitSuccess(result) {
	fastDev.alert(result.msg, '信息提示', result.status, function() {
		if (result.status == 'ok') {
			parent.refreshDatagrid();
			parent.closeDialog();
		}
	});
}

*/