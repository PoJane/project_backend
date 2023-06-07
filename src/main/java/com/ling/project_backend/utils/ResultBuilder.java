package com.ling.project_backend.utils;

public class ResultBuilder {

    private static final int DEFAULT_SUCCESS_CODE=200;
    private static final int DEFAULT_FAIL_CODE=400;
    private static final String DEFAULT_SUCCESS_MESSAGE="SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE="FAIL";

    public static Result successResult(Object data) {
        return buildResult(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, data);
    }

    public static Result successResult(String message,Object data){
        return buildResult(DEFAULT_SUCCESS_CODE, message, data);
    }

    public static Result failResult(Object data) {
        return buildResult(DEFAULT_FAIL_CODE, DEFAULT_FAIL_MESSAGE, data);
    }

    public static Result failResult(String message,Object data){
        return buildResult(DEFAULT_FAIL_CODE,message,data);
    }

    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }

}
