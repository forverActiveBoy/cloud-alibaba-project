package com.czbank.advice;

import com.alibaba.fastjson.JSON;
import com.czbank.bo.HttpStatusEnum;
import com.czbank.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author foreverActiveBoy
 * @date 2020/4/21 15:23
 */
@Slf4j
@RestControllerAdvice(basePackages = {"com.czbank.controller"})
public class ControllerAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //  如果接口返回的类型本身就是ResponseVO那就没有必要进行额外的操作，返回false
        //  supports方法要返回为true才会执行beforeBodyWrite方法
        return !methodParameter.getGenericParameterType().equals(BaseResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
            return JSON.toJSONString(new BaseResponse<>(HttpStatusEnum.OK.getCode(),HttpStatusEnum.OK.getMsg(),data));
        }
        return new BaseResponse<>(HttpStatusEnum.OK.getCode(),HttpStatusEnum.OK.getMsg(),data);
    }
}
