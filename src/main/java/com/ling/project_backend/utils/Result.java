package com.ling.project_backend.utils;

public class Result {

    private int code;
    private String name;
    private Object data;

    public Result() {
    }

    public Result(int code, String name, Object data) {
        this.code = code;
        this.name = name;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
