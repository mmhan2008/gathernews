$(function() {
    //ruleconf.html   js
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var URL =  decodeURI(window.location.search);
        var r = URL.substr(1).match(reg);
        if(r!=null){
            return  decodeURI(r[2]);
        };
        return null;
    }

    var url = getUrlParam('url');
    var jobName = getUrlParam('name');
    var format = $('.model select').val();
    var type = getUrlParam('type');

    var ys1,ys2,ys3,ns1,ns2,ns3;
    var result;

    if (url != null) {
        var rule = getRule(url,jobName);
        if (rule != null ) {
            $(".model select").val(rule.jobModel);
            format = $('.model select').val();
            getUrl(url,jobName,format,type);
            getKey(url,jobName);
            $(".ys1 select").val(rule.include1);
            $(".ys2 select").val(rule.include2);
            $('.ys3').val(rule.include3);
            $(".ns1 select").val(rule.isInclude1);
            $(".ns2 select").val(rule.isInclude2);
            $(".ns3 select").val(rule.isInclude3);
            if(typeof(result) != 'undefined'){
                ys1 =$(".ys1 select option:selected").val();
                ys2 =$(".ys2 select option:selected").val();
                ys3 =$('.ys3').val();
                ns1 =$(".ns1 select option:selected").val();
                ns2 =$(".ns2 select option:selected").val();
                ns3 =$(".ns3 select option:selected").val();
                var u = changeRule(ys1,ys2,ys3,ns1,ns2,ns3,result);
                show(u);
                initChange();
            }
        } else {
            getUrl(url,jobName,format,type);
            getKey(url,jobName);
            initChange();
        }
    }

    $('.model select').change(function() {
        format = $(this).val();
        getUrl(url,jobName,format,type);
        getKey(url,jobName);
        initChange();
    });

    function getKey(a,b){
        $.ajax({
            url : "getKey?url="+a,
            type : "post",
            contentType : false,
            processData : false,
            async : false,
            dataType : "html",
            success : function(rule) {
                $(".rule.y div").html("");
                $(".rule.y div").append(rule);
                $(".rule.n div").html("");
                $(".rule.n div").append(rule);
            },
        });
    }

    function getUrl(a,b,c,d){
        $.ajax({
            url : "getUrl?url="+a+"&jobName="+b+"&format="+c+"&type=" + d,
            type : "post",
            contentType : false,
            processData : false,
            async : false,
            dataType : "json",
            success : function(data) {
                result = data.resultData;
                $(".yulan ul").html("");
                if (typeof(data.msg) != 'undefined') {
                    alert(data.msg);
                }
                if (typeof(data.resultData) != 'undefined'){
                    $.each(data.resultData, function (idx, obj) {
                        // alert(obj.baseUrl + obj.title);
                        $(".yulan ul").append("<li><span style='color: red'>" + obj.baseUrl + "</span><span style='margin-left: 10px'>" + obj.title + "</span></li>");
                    });
                };
            },
        });
    }
    
    function getRule(a,b) {
        var rule;
        $.ajax({
            url:"getRule?url="+a+"&jobName="+b,
            type:"post",
            async:false,
            dataType:"json",
            success :function (data) {
                rule = data;
            },
        })
        return rule;
    }

    function initChange(){
        $(".ys1 select").bind('change',function () {
            ys1 =$(".ys1 select option:selected").val();
            var u = changeRule(ys1,ys2,ys3,ns1,ns2,ns3,result);
            show(u);
        });

        $(".ys2 select").bind('change',function () {
            ys2 =$(".ys2 select option:selected").val();
            var u = changeRule(ys1,ys2,ys3,ns1,ns2,ns3,result);
            show(u);
        });

        $('.ys3').each(function() {
            var default_value = this.value;

            $(this).focus(function() {
                if (this.value == default_value) {
                    this.value = '';
                }
            });
            $(this).blur(function() {
                ys3 = $('.ys3').val();
                var u = changeRule(ys1,ys2,ys3,ns1,ns2,ns3,result);
                show(u);
            });
        });

        $('.ns1 select').bind('change',function(){
            ns1 =$(".ns1 select option:selected").val();
            var u = changeRule(ys1,ys2,ys3,ns1,ns2,ns3,result);
            show(u);
        });

        $('.ns2 select').bind('change',function(){
            ns2 =$(".ns2 select option:selected").val();
            var u = changeRule(ys1,ys2,ys3,ns1,ns2,ns3,result);
            show(u);
        });

        $('.ns3 select').bind('change',function(){
            ns3 =$(".ns3 select option:selected").val();
            var u = changeRule(ys1,ys2,ys3,ns1,ns2,ns3,result);
            show(u);
        });
    }


    function show(array) {
        $(".yulan ul").html("");
        $(".yulan span").empty();
        if (array.length == 0){
            $(".yulan").append("<span class='jieguo'>无结果集</span>");
        } else {
            $.each(array,function (idx,obj) {
                $(".yulan ul").append("<li><span style='color: red'>" + obj.baseUrl + "</span><span style='margin-left: 10px'>" + obj.title + "</span></li>");
            });
        }
    }

    function changeRule(a,b,c,d,e,f,g){
        if (typeof(a) == 'undefined'){
            a ='';
        }
        if (typeof(b) == 'undefined'){
            b ='';
        }
        if (c == '这里可以输入规则' || typeof (c) == 'undefined') {
            c = '';
        }
        if (typeof(d) == 'undefined'){
            d ='';
        }
        if (typeof(e) == 'undefined'){
            e ='';
        }
        if (typeof(f) == 'undefined'){
            f ='';
        }
        var urls = [];
        try {
            $.each(g, function (idx, obj) {
                var trimUrl = obj.baseUrl.replace(/\d+/g, '');
                if (a == '' && b == '' && c == '' && d == '' && e == '' && f == '') {
                    urls = g;
                } else if (a != '' && b == '' && c == '') {
                    if (d == '' && e == '' && f == '') {
                        if (trimUrl.indexOf(a) != -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e == '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(d) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e == '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    }
                } else if (a != '' && b != '' && c == '') {
                    if (d == '' && e == '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e == '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && trimUrl.indexOf(d) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e == '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    }
                } else if (a != '' && b != '' && c != '') {
                    if (d == '' && e == '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e == '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e == '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    }
                } else if (a != '' && b == '' && c != '') {
                    if (d == '' && e == '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && obj.baseUrl.indexOf(c) != -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e == '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e == '' && f != '') {
                        if (trimUrl.indexOf(a) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f == '') {
                        if (trimUrl.indexOf(a) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    }
                } else if (a == '' && b != '' && c != '') {
                    if (d == '' && e == '' && f == '') {
                        if (trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e == '' && f == '') {
                        if (trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f == '') {
                        if (trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f != '') {
                        if (trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f != '') {
                        if (trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e == '' && f != '') {
                        if (trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f == '') {
                        if (trimUrl.indexOf(b) != -1 && obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    }
                } else if (a == '' && b == '' && c != '') {
                    if (d == '' && e == '' && f == '') {
                        if (trimUrl.indexOf(c) != -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e == '' && f == '') {
                        if (obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f == '') {
                        if (obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f != '') {
                        if (obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f != '') {
                        if (obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e == '' && f != '') {
                        if (obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f == '') {
                        if (obj.baseUrl.indexOf(c) != -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    }
                } else if (a == '' && b != '' && c == '') {
                    if (d == '' && e == '' && f == '') {
                        if (trimUrl.indexOf(b) != -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e == '' && f == '') {
                        if (trimUrl.indexOf(b) != -1 && trimUrl.indexOf(d) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f == '') {
                        if (trimUrl.indexOf(b) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f != '') {
                        if (trimUrl.indexOf(b) != -1 && trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f != '') {
                        if (trimUrl.indexOf(b) != -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e == '' && f != '') {
                        if (trimUrl.indexOf(b) != -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f == '') {
                        if (trimUrl.indexOf(b) != -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    }
                } else if (a == '' && b == '' && c == '') {
                    if (d != '' && e == '' && f == '') {
                        if (trimUrl.indexOf(d) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f == '') {
                        if (trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    } else if (d != '' && e != '' && f != '') {
                        if (trimUrl.indexOf(d) == -1 && trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f != '') {
                        if (trimUrl.indexOf(e) == -1 && trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e == '' && f != '') {
                        if (trimUrl.indexOf(f) == -1) {
                            urls.push(obj);
                        }
                    } else if (d == '' && e != '' && f == '') {
                        if (trimUrl.indexOf(e) == -1) {
                            urls.push(obj);
                        }
                    }
                }
            });
        } catch (err){
            console.log(err);
        }
        return urls;
    };

    //返回
    $("input[name = 'return']").click(function(){
        window.location.href="/index";
    });

    //重置
    $("input[name = 'reset']").click(function(){
        $(".rule select").val("");
        show(result);
    });

    //开关
    $("input[type = 'radio']").click(function() {
        $("input[type = 'radio']").not(this).removeAttr('checked');
        $(this).attr('checked', true);
    });
    //确认
    $("input[name = 'confirm']").click(function(){
        updateDB();
    });
    function createJob(a,b,c,d,e,f,g,h){
        var tbJob = {
            "jobUrl":a,
            "jobName": b,
            "include1":c,
            "include2":d,
            "include3":e,
            "isInclude1":f,
            "isInclude2":g,
            "isInclude3":h,
            "isEnable":$('input:radio:checked').val()
        };
        return tbJob;
    }

    function updateDB() {
        if (url != ''){
            if (ys3 == '这里可以输入规则') {
                ys3 = '';
            }
            var tb = createJob(url,jobName,ys1,ys2,ys3,ns1,ns2,ns3);
            $.ajax({
                url: "updateDB",
                type: "post",
                data:JSON.stringify(tb),
                contentType :'application/json',
                processData: false,
                async: false,
                dataType: "json",
                success: function (data) {
                    alert(data.msg);
                    if(data.msg == '创建任务成功'){
                        $.ajax({
                            url: "updateTaskList",
                            dataType:"json",
                            success:function (result) {
                                console.log("status:" +result.status + "\nmessage:" + result.message);
                                window.close();
                            }
                        })
                    }
                },
                error: function (Error) {
                    console.log(Error)
                    var msg = JSON.parse(Error.responseText).error_description;
                    alert(msg);
                }
            });
        };
    }
});