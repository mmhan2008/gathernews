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
        initTable();
    });
});

function initTable() {
    $("#bootstrapTable").bootstrapTable({
        method: 'get',
        striped: true,
        pagination: true,
        url: 'getFail',
        dataType: 'json',
        toolbar: '#toolbar',
        cache: false,
        showToggle: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 20,
        pageList: [10,15,20, 50, 100],
        sortable: true,
        sortName: 'createTime',
        sortOrder: "desc",
        showRefresh: true,
        showColumns: true,
        showToggle:false,
        queryParamsType:'undefined',
        queryParams: function queryParams(params){
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                jobName:$("#jobName").val(),
                jobUrl: $("#jobUrl").val()
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
                sortable: false,
            },{
                field: 'jobUrl',
                title: '任务连接',
                class: 'jobUrl',
                align: 'left',
                valign: 'middle',
                sortable: false
            },{
                field: 'jobModel',
                title: '模版',
                align: 'center',
                valign: 'middle',
                sortable: false
            },{
                field: 'isEnable',
                title: '开关',
                align: 'center',
                valign: 'middle',
                sortable: false,
                cellStyle:function(value,row,index){
                    if (value=="启用"){
                        return {css:{"color":"green"}}
                    }else{
                        return {css:{"color":"darkgrey"}}
                    }
                }
            },{
                field: 'jobOwner',
                title: '创建者',
                align: 'center',
                valign: 'middle',
                sortable: false,
            },{
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                valign: 'middle',
                sortable: true,
            },{
                field: 'updateTime',
                title: '最后一次更新时间',
                align: 'center',
                valign: 'middle',
                sortable: true,
            },{
                field: 'tbError.errorType',
                title: '错误类型',
                align: 'center',
                valign: 'middle',
                sortable: true,
            },{
                field: 'tbError.errorCount',
                title: '错误次数',
                align: 'center',
                valign: 'middle',
                sortable: true,
            },{
                field: 'tbError.createTime',
                title: '错误时间',
                align: 'center',
                valign: 'middle',
                sortable: true,
            },{
                title: '操作',
                align: 'center',
                valign: 'middle',
                formatter: actionFormatter,
            }]
    });
}
function actionFormatter(value, row, index) {
    var id = value;
    var result = "";
    // result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"EditViewByUrl('" + id + "', view='view')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
    result += "<a href=\"ruleconf?url="+row.jobUrl+"&name="+row.jobName+"\" class='btn btn-xs blue'title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
    // result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"DeleteByUrl(a)\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
    return result;
}

