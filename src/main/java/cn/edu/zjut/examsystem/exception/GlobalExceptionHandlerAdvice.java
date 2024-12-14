package cn.edu.zjut.examsystem.exception;


import cn.edu.zjut.examsystem.Code;
import cn.edu.zjut.examsystem.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//统一异常处理类
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {
    @ExceptionHandler({Exception.class})
    public ResponseMessage handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
    {
        return new ResponseMessage(Code.FAIL,"后端发生未知错误",null);
    }
}
