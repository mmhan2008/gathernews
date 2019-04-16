$(function() {
    //  http://www.baidu.com
    $('#url').each(function() {
        var default_value = this.value;

        $(this).focus(function() {
            if (this.value == default_value) {
                this.value = '';
            }
        });
        $(this).blur(function() {
            if (this.value != '') {
                url = $('#url').val();
                query(url);
            } else {
                $('#url').val(default_value);
            }
        });
    });

    function getUrlParam(name) {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var URL =  decodeURI(window.location.search);
        var r = URL.substr(1).match(reg);
        if(r!=null){
            return  decodeURI(r[2]);
        };
        return null;
    }
    var jobUrl = getUrlParam('jobUrl');
    $('iframe').attr('src', jobUrl);
    $("#url").val(jobUrl);
    var jobName = getUrlParam('jobName');
    $(".laiyuan input").val(jobName);

    var type = getUrlParam('type');
    $(".type input").val(type);

    $(".button").click(function() {
        url = $('#url').val();
        name = $('.laiyuan input').val();
        type = $(".type input").val();
        window.location.href = "ruleconf?url="+url+"&name="+name+"&type=" + type;
    });

    $("input[type = 'radio']").click(function() {
        $("input[type = 'radio']").not(this).removeAttr('checked');
        $(this).attr('checked', true);
    });

    function query(a){
        $.ajax({
            url:"queryExist?url="+a,
            type:"post",
            async:false,
            dataType:"json",
            success :function (data) {
                $('iframe').attr('src', data.jobUrl);
                if (typeof(data.jobId)!='undefined'){
                    $.confirm({
                        title:"修改",
                        content:"当前url任务已存在，是否修改！",
                        buttons:{
                            confirm:{
                                text:"确定",
                                action:function(){
                                    window.location.href = "ruleconf?url="+data.jobUrl+"&name="+data.jobName+"&type="+data.type;
                                }
                            },
                            cancle:{
                                text:"取消",
                            }
                        }
                    })
                } else {
                    $('.laiyuan input').val(data.jobName);
                }
            },
            error : function(Error) {
                alert("error");
            }
        })
    }

    $(".btn").click(function() {
        var formdata = new FormData(document.getElementById("formdata"));
        /* 发送ajax */
        $.ajax({
            url : "/upload",
            type : "post",
            data : formdata,
            contentType : false,
            processData : false,
            async : false,
            dataType : "json",
            beforeSend : function() {

            },
            success : function(data) {
                if ("success" == data) {
                    alert("上传成功");
                } else {
                    alert("上传失败！");
                }
            },
            complete : function() {

            },
            error : function(response) {
                alert("error");
            }
        });
    });
});