$(function(){
    initTable();
    $("#bootstrapTable").bootstrapTable('refresh');
    $(document).on('click', ".queryButton",function(){
        $('#bootstrapTable').bootstrapTable('selectPage',1);
    });
    $(document).on('click', ".refreshButton",function(){
        $("#bootstrapTable").bootstrapTable('destroy');
        $("#jobName").val('');
        $("#jobUrl").val('');
        $("#isEnable").val('');
        $("#startData").val('');
        $("#endData").val('');
        initTable();
    });
});

function initTable() {
    //先销毁表格
    $("#bootstrapTable").bootstrapTable({
        method: 'get',
        striped: true,
        pagination: true,
        url: 'getJobAndCount',
        dataType: 'json',
        toolbar: '#toolbar',
        cache: false,
        showToggle: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 20,
        pageList: [10,15,20, 50, 100],
        // search: true,
        sortable: false,
        // sortName: 'createTime',
        // sortOrder: "desc",
        showRefresh: true,
        showColumns: true,
        showToggle:false,
        queryParamsType:'undefined',
        queryParams: function queryParams(params){
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                jobName: $("#jobName").val(),
                jobUrl: $("#jobUrl").val(),
                isEnable: $("#isEnable").val(),
                startTime:$("#startData").val(),
                endTime:$("#endData").val()
            };
            return param;
        },
        onLoadSuccess: function(data){
            console.log("加载成功");
        },
        onLoadError: function(status){
            console.log("加载数据失败"+status);
        },
        formatNoMatches: function () {
            return '无符合条件的记录';
        },
        formatLoadingMessage: function () {
            return "请稍等，正在加载中...";
        },
        columns: [
            {
                title: '编号',
                align: 'center',
                valign: 'middle',
                sortable: false,
                // width:'50',
                formatter: function (value, row, index) {
                    return index+1;
                }
            },{
                field: 'jobId',
                title: '任务编号',
                visible: false,
            },{
                field: 'jobName',
                title: '任务来源',
                class: 'jobName',
                align: 'center',
                valign: 'middle',
                // width:'200',
                sortable: false,
            },{
                field: 'type',
                title: '网站类型',
                align: 'center',
                valign: 'middle',
                sortable: false,
                visible: false
            },{
                field: 'jobUrl',
                title: '任务连接',
                class: 'jobUrl',
                align: 'left',
                valign: 'middle',
                // width:'300',
                sortable: false
            },{
                field: 'jobModel',
                title: '模版',
                align: 'center',
                valign: 'middle',
                width:'70',
                sortable: false
            },{
                field: 'isEnable',
                title: '开关',
                align: 'center',
                valign: 'middle',
                width:'100',
                sortable: false,
                cellStyle:function(value,row,index){
                    if (value=="启用"){
                        return {css:{"color":"green"}}
                    }else if(value=="暂停"){
                        return {css:{"color":"darkgrey"}}
                    }else{
                        return {css:{"color":"red"}}
                    }
                }
            },{
                field: 'tbJobCount.date',
                align: 'center',
                visible: false,
            },{
                field: 'tbJobCount.dateCount',
                title: '昨天更新条数',
                align: 'center',
                valign: 'middle',
                // width:'150',
                sortable: true,
                formatter: function (value,row,index) {
                    var time = new Date();
                    var formatDate = time.format("yyyyMMdd",time.getDate() - 1);
                    if (value == null) {
                        return 0;
                    } else if(formatDate != row.tbJobCount.date) {
                        return 0;
                    } else {
                        return value;
                    }
                }
            },{
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                valign: 'middle',
                // width:'150',
                sortable: true,
            },{
                field: 'updateTime',
                title: '更新时间',
                align: 'center',
                valign: 'middle',
                // width:'150',
                sortable: true,
            },{
                title: '操作',
                align: 'center',
                valign: 'middle',
                width:'100',
                events: operateEvents,
                formatter: actionFormatter,
            }]
    });
}
function actionFormatter(value, row, index) {
    var id = value;
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs green detail' title='详情'><span class='glyphicon glyphicon-search'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs blue edit' title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs red del' title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
    return result;
};
function timeFormatter(value,row,index){
    return new Date(value).format("yyyy-MM-dd hh:mm:ss");
}
window.operateEvents = {
    'click .del' : function(e, value, row, index) {
        //删除操作
        console.log(row.jobId);
        $.confirm({
            title:"删除",
            content:"是否确认删除！",
            buttons:{
                confirm:{
                    text:"确定",
                    action:function(){
                        $.ajax({
                            url:"del?id="+row.jobId,
                            type:"post",
                            async:true,
                            dataType:"json",
                            success :function (data) {
                                if ( data == true){
                                    alert("删除成功！");
                                } else {
                                    alert("删除失败！");
                                }
                                $("#bootstrapTable").bootstrapTable('refresh');
                            },
                            error : function(Error) {
                                alert("error");
                            }
                        })
                    }
                },
                cancle:{
                    text:"取消",
                }
            }
        })
    },
    'click .edit' : function(e, value, row, index) {
        window.open("preview?jobUrl="+row.jobUrl+"&jobName="+row.jobName+"&type=" + row.type +"","_blank");
    },
    'click .detail' : function(e, value, row, index) {
        window.open("details?id="+row.jobId+"","_blank");
    },
}
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}