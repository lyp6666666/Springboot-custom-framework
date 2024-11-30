package com.example.bookmanage.utils;

public class RequestContext {
    private static final ThreadLocal<String> userIdThreadLocal = new ThreadLocal<>();

    // 设置用户 ID
    public static void setAccount(String account) {
        userIdThreadLocal.set(account);
    }

    // 获取用户 ID
    public static String getAccount() {
        return userIdThreadLocal.get();
    }

    // 清除线程本地存储
    public static void clear() {
        userIdThreadLocal.remove();
    }
}
