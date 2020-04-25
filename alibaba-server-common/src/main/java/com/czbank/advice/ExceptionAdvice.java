package com.czbank.advice;

import com.czbank.bo.HttpStatusEnum;
import com.czbank.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author foreverActiveBoy
 * @date 2020/4/21 14:42
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     *处理服务器异常
     * @param e 异常对象
     * @return
     * 开发阶段不要打开，上线的时候再打开
     */
/*    @ExceptionHandler(value = {Exception.class})
    public BaseResponse exceptionHandle(Exception e){
        log.error("服务错误！错误信息:[{}]",e.getMessage());
        return new BaseResponse(HttpStatusEnum.SERVER_ERROR.getCode(),HttpStatusEnum.SERVER_ERROR.getMsg(),"服务器错误！");
    }*/

    /**
     * 处理参数校验异常
     * @param e 方法参数异常异常对象
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public BaseResponse exceptionHandle(MethodArgumentNotValidException e){
        log.error("参数校验错误！错误信息：[{}]",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new BaseResponse(HttpStatusEnum.BAD_REQUEST.getCode(),HttpStatusEnum.BAD_REQUEST.getMsg(),e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    //  自定义异常在下面扩展
}
