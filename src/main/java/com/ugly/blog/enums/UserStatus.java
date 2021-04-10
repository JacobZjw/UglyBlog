package com.ugly.blog.enums;

/**
 * @author JwZheng
 * @date 2021/4/10 14:29
 */
public enum UserStatus {
    NORMAL("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
