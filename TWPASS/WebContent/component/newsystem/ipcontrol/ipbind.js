var popupObj;
talkweb.Bus.ready({
    items: [{
        classPath: "BaseControl.Button",
        options: {
            value: "增加",//按钮文本
            type: "button",//按钮类型
            className: "btn_add",
            width: "80px",
            name:"add",
            hide:true,
            container: "operationDiv",//指定容器
            click: addIpbind
        }
    }, {
        classPath: "BaseControl.Button",
        options: {
            value: "批量删除",//按钮文本
            type: "button",//按钮类型
            width: "80px",
            name:"batchdelete",
            hide:true,
            className: "btn_delete",
            container: "operationDiv",//指定容器
            click: deleteIpbindByIds
        }
    }, {
        classPath: "Components.DataGrid",
        options: {
            initSource: "ipcontrol_queryIpControlByPage.action?type=2",//初始化数据来源
            container: "datagridDiv",//指定容器
            id: "datagrid",
            rows: 10, //指定展示的行数
            multiple: true, //是否有复选框
            seqNum: false, //行序号
            saveInstance: true,//保存实例
            fields: [{
                name: "userid",
                text: "登录账号",
                width: "10%"
            }, {
                name: "username",
                text: "姓名",
                width: "15%"
            }, {
                name: "ipaddress",
                text: "ip地址",
                width: "10%"
            }, {
                name: "maskcode",
                text: "子网掩码",
                width: "10%"
            }, {
                name: "controldesc",
                text: "是否绑定",
                width: "10%"
            }, {
                name: "startdate",
                text: "开始时间",
                width: "10%",
                filter: [{
                    type: "date",
                    dateMode: "YYYY-MM-DD hh:mm:ss:xxx",
                    format: "YYYY-MM-DD",
                    cond: "none"
                }]
            }, {
                name: "enddate",
                text: "结束时间",
                width: "10%",
                filter: [{
                    type: "date",
                    dateMode: "YYYY-MM-DD hh:mm:ss:xxx",
                    format: "YYYY-MM-DD",
                    cond: "none"
                }]
            }, {
                name: "createtime",
                text: "创建时间",
                width: "10%",
                filter: [{
                    type: "date",
                    dateMode: "YYYY-MM-DD hh:mm:ss:xxx",
                    format: "YYYY-MM-DD",
                    cond: "none"
                }]
            }, {
                text: "常见操作",
                width: "15%",
                filter: [{
                    type: "control",
                    cond: "none",
                    controlList: [{
                        calssName: "BaseControl.Button",
                        options: {
                            value: "修改",
                            name:"modify",
                            hide:true,
                            click: getIpbindById
                        }
                    }, {
                        calssName: "BaseControl.Button",
                        options: {
                            value: "删除",
                            name:"delete",
                            hide:true,
                            click: deleteIpbindById
                        }
                    }]
                }]
            }, {
                name: "controlid",
                hide: true
            }],
            dataIsReady:function(){//增加datagrid加载完成后执行按钮权限的方法
				showResourceModes("ip");//此方法写在pagecommon.js公共JS中,如果页面没有引用需要引用该JS
			}
        }
    }]
})

/**
 * 增加ip绑定
 */
function addIpbind(){
    popupObj = talkweb.Components.Dialog({
        title: "新增规则",
        iscenter: true,
        width: "720",
        height: "360",
        src: "addIpcontrol.html?controltype=2&opt=add&id=-1"
    });
}

/**
 * 根据主键得到ip绑定的信息
 */
function getIpbindById(){
    var datagridValue = talkweb.ControlBus.getInstance("datagrid").getRowValue(this);//得到一行记录
    popupObj = talkweb.Components.Dialog({
        title: "岗位信息",
        iscenter: true,
        width: "720",
        height: "360",
        src: "addIpcontrol.html?id=" + datagridValue.controlid + "&controltype=2&opt=modify"
    });
}

/**
 * 根据主键删除ip绑定的信息
 */
function deleteIpbindById(){
    if (confirm("删除所选记录？")) {
        var datagrid = talkweb.ControlBus.getInstance("datagrid");
        var datagridValue = datagrid.getRowValue(this);//得到一行记录
        $.ajax({
            type: "POST",
            url: "ipcontrol_deleteIpControl.action",
            data: "id=" + datagridValue.controlid +"&type=2",
            dataType: "json",
            success: function(data){
        		alert(data.msg);
                datagrid.refreshData(true); //刷新datagrid
            }
        });
    }
}

/**
 * 根据主键批量删除ip绑定的信息
 */
function deleteIpbindByIds(){
    var datagrid = talkweb.ControlBus.getInstance("datagrid");
    var ipbinds = datagrid.getValue();//得到所有选中对象的集合
    if (ipbinds.length <= 0) {
        alert("请至少选择一条记录进行操作");
        return;
    }
    if (confirm("删除所选记录？")) {
        var ids = "";
        for (var i = 0; i < ipbinds.length; i++) {
            var obj = ipbinds[i];
            if (i == ipbinds.length - 1) 
                ids += obj.controlid;
            else 
                ids += obj.controlid + ",";
        }
        $.ajax({
            type: "POST",
            url: "ipcontrol_deleteIpControl.action",
            data: "id=" + ids +"&type=2",
            dataType: "json",
            success: function(data){
        		alert(data.msg);
                datagrid.refreshData(true); //刷新datagrid
            }
        });
    }
}
