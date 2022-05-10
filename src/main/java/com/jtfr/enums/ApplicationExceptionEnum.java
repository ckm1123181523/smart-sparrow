package com.jtfr.enums;

/**
 * 异常枚举类
 */
public enum ApplicationExceptionEnum {

    // 成功 0
    SUCCESS(0, "成功"),

    // 普通异常20开头
    PARAMS_NULL(20001, "参数为空"),
    PARAMS_ERROR(20002, "参数错误"),
    REQUEST_REPEAT(20003, "重复请求"),
    SIGNATURE_ILLEGAL(20004, "非法请求"), //验签失败

    // 加解密相关的30开头
    DECRYPT_ERROR(30001, "解密失败"),
    ENCRYPT_ERROR(30002, "加密失败"),

    // 查询类异常40开头
    QUERY_NO_RESULT(40001, "查询无结果"),

    // http异常50开头
    HTTP_CALL_ERROR(50001, "http请求异常"),

    // 未知错误
    UNKNOWN_ERROR(99999, "未知错误"),
    ;


    private final int code;
    private final String desc;

    ApplicationExceptionEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return code;
    }

    public String codeAsString(){
        return Integer.toString(code);
    }

    public String desc() {
        return desc;
    }

}
