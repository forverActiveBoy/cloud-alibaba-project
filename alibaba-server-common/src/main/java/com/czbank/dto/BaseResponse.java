package com.czbank.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *@author foreverActiveBoy
 *@date 2020/4/21 14:38
 *@param <T>
 */
@Data
@Getter
@Setter
public class BaseResponse<T> {
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    /**
     * 异常返回结果用的构造方法
     * @param code
     * @param msg
     */
    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
