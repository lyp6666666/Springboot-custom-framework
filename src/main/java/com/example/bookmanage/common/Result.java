package com.example.bookmanage.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    private static final String CODE_SUCCESS = "200";
    private static final String CODE_AUTH_ERROR = "401";
    private static final String CODE_ERROR = "500";

    private String code;
    private String msg;
    private Object data;

    // 用于存储附加数据
    private Map<String, Object> extraData;




    public static Result success() {
        return new Result(CODE_SUCCESS, "请求成功", null,new HashMap<>());
    }
    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, "请求成功", data,new HashMap<>());
    }

    public static Result error(String msg) {
        return new Result(CODE_ERROR, msg, null,new HashMap<>());
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg, null,new HashMap<>());
    }

    public static Result error() {
        return new Result(CODE_ERROR, "系统错误", null,new HashMap<>());
    }


    // 添加额外数据
    public Result addData(String key, Object value) {
        if (this.extraData == null) {
            this.extraData = new HashMap<>();
        }
        this.extraData.put(key, value);
        return this;
    }


}
