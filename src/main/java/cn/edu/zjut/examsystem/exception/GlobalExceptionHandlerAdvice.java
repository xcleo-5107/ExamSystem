package cn.edu.zjut.examsystem.exception;


import cn.edu.zjut.examsystem.Code;
import cn.edu.zjut.examsystem.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

//统一异常处理类
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {
    //其它未定义的异常处理
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseMessage handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
    {
        return new ResponseMessage(Code.FAIL,"后端发生未知错误",null);
    }

    //数据验证异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseMessage handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 获取所有的FieldError并提取默认消息，然后使用逗号拼接成一个字符串
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return new ResponseMessage(Code.FAIL,errorMessage,null);
    }
}
