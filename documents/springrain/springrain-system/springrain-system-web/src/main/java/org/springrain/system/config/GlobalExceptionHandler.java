package org.springrain.system.config;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springrain.frame.util.ReturnDatas;

/**
 * 全局异常处理
 * 按照 ReturnDatas.statusCode 判断是否成功.异常 1, 成功 0,默认成功0
 * 其他异常的信息 通过 http状态码进行判断.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Http Status Code 500
    public ReturnDatas<?> handleException(Exception e) {
        ReturnDatas<?> returnDatas = ReturnDatas.getErrorReturnDatas(e.getMessage());
        return returnDatas;
    }

}
