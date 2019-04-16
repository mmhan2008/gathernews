package com.cnstock.entity;

import java.io.Serializable;

/**
 * @author user01
 * @create 2019/1/28
 */
public class Response implements Serializable {


    private static final long serialVersionUID = 6078726412474998475L;

    private String code;

    private String msg;

    private Object info;


    public Response newSuccess(){
        this.code="200";
        this.msg="请求成功";
        return this;
    }

    public Response newError(){
        this.code="-1";
        this.msg="请求失败";
        return this;
    }

    public Response(String code, String msg, Object info) {
        this.code = code;
        this.msg = msg;
        this.info = info;
    }

    public Response() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", info=" + info +
                '}';
    }
}


