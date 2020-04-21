package com.czbank.bo;

/**
 * @author foreverActiveBoy
 * @date 2020/4/21 14:54
 * 备注：每一个枚举值code都用七位数（四位端口号+三位状态码,状态码参考HttpStatus这个枚举）
 */
public enum HttpStatusEnum {
    /**
     * 请求成功
     */
    OK(8081200, "OK"),
    /**
     * 参数格式错误
     */
    BAD_REQUEST(8081400, "Bad Request"),
    /**
     * 服务端错误
     */
    SERVER_ERROR(8081500, "Server Error");

    private Integer code;
    private String msg;

    HttpStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
