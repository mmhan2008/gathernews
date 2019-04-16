$(function(){

    function getUrlParam(name) {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var URL =  decodeURI(window.location.search);
        var r = URL.substr(1).match(reg);
        if(r!=null){
            return  decodeURI(r[2]);
        };
        return null;
    }

    var id = getUrlParam('id');
    if (id != null) {
        $.ajax({
            url: "getJob?id=" + id,
            type: "get",
            async: true,
            dataType: "json",
            success: function (data) {
                if (data.result != null){
                    $(".job-detail").append("<ul class='list-group'>" +
                        "<li class='list-group-item'>ip："+data.result.droneId+"</li>" +
                        "<li class='list-group-item'>创建者：" +data.result.jobOwner+"</li>" +
                        "<li class='list-group-item'>关键词1：" +data.result.include1+"</li>" +
                        "<li class='list-group-item'>关键词2：" +data.result.include2+"</li>" +
                        "<li class='list-group-item'>关键词3：" +data.result.include3+"</li>" +
                        "<li class='list-group-item'>关键词4：" +data.result.isInclude1+"</li>" +
                        "<li class='list-group-item'>关键词5：" +data.result.isInclude2+"</li>" +
                        "<li class='list-group-item'>关键词6：" +data.result.isInclude3+"</li>" +
                        "</ul>");
                }
            },
            error: function (Error) {
                alert("error");
            }
        });
        $.ajax({
            url: "getCount?id=" + id,
            type: "get",
            async: true,
            dataType: "json",
            success: function (data) {
                if ((data.result).length != 0){
                    $.each(data.result,function (idx,obj) {
                        $(".job-count").append("<tr><td>"+obj.date+"</td><td>"+obj.dateCount+"</td></tr>");
                    });
                } else {
                    $(".count").show();
                }
            },
            error: function (Error) {
                alert("error");
            }
        });
        $.ajax({
            url: "getLog?id=" + id,
            type: "get",
            async: true,
            dataType: "json",
            success: function (data) {
                if ((data.result).length != 0){
                    $.each(data.result,function (idx,obj) {
                        $(".job-log").append("<tr>"+
                                                "<td>"+obj.updateTime+"</td>" +
                                                "<td>"+obj.newLink+"</td>" +
                                                "<td>"+obj.linkCount+"</td>" +
                                                "<td>"+obj.status+"</td>" +
                                            "</tr>");
                    });
                } else {
                    $(".log").show();
                }
            },
            error: function (Error) {
                alert("error");
            }
        });

        $.ajax({
            url: "getError?id=" + id,
            type: "get",
            async: true,
            dataType: "json",
            success: function (data) {
                if ((data.result).length != 0){
                    $.each(data.result,function (idx,obj) {
                        $(".job-error").append("<tr>"+
                            "<td>"+obj.droneId+"</td>" +
                            "<td>"+obj.errorType+"</td>" +
                            "<td>"+obj.errorCount+"</td>" +
                            "<td>"+obj.createTime+"</td>" +
                            "</tr>");
                    });
                } else {
                    $(".jobError").show();
                }
            },
            error: function (Error) {
                alert("error");
            }
        });
    }
});