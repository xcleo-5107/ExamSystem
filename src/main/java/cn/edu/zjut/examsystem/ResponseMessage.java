package cn.edu.zjut.examsystem;

import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;

@Getter
@Setter
public class ResponseMessage<T> {
    private int code;
    private String message;
    private T data;

    public ResponseMessage(Code code,String message,T data)
    {
        this.code = code.getValue();
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseMessage<T> success(String message,T data)
    {
        return new ResponseMessage<>(Code.SUCCESS,message,data);
    }

    public static <T> ResponseMessage<T> fail(String message,T data)
    {
        return new ResponseMessage<>(Code.FAIL,message,data);
    }
}
