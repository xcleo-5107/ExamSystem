package cn.edu.zjut.examsystem.exception;


import cn.edu.zjut.examsystem.Enum.Code;
import cn.edu.zjut.examsystem.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

//统一异常处理类
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {
    Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    //其它未定义的异常处理
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseMessage handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
    {
        log.error("统一异常:"+e.toString());
        e.printStackTrace();
        return new ResponseMessage(Code.ERROR,"后端发生未知错误",null);
    }

    //数据验证异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseMessage handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("数据校验异常:"+ex);

        // 获取所有的FieldError并提取默认消息，然后使用逗号拼接成一个字符串
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return new ResponseMessage(Code.ERROR,errorMessage,null);
    }

    @ExceptionHandler(jakarta.persistence.EntityExistsException.class)
    @ResponseBody
    public ResponseMessage entityExistsException(jakarta.persistence.EntityExistsException ex)
    {
        log.error("数据校验异常:"+ex);

        return new ResponseMessage(Code.ERROR,"尝试修改一个已经存在的实体类,疑似攻击:",null);
    }
}
