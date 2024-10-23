package cn.dogalist.weblog.common.utils;

import java.io.Serializable;

import cn.dogalist.weblog.common.exception.BaseExceptionInterface;
import cn.dogalist.weblog.common.exception.BizException;
import lombok.Data;

@Data
public class Response<T> implements Serializable {
    private boolean success = true;
    private String message;
    private String errCode;
    private T data;

    // =================== 成功 =====================
    public static <T> Response<T> success(){
        Response<T> response = new Response<>();
        return response;
    }
    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    // =================== 失败 =====================
    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        return response;
    }
    public static <T> Response<T> fail(String errMsg) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(errMsg);
        return response;
    }
    public static <T> Response<T> fail(String errCode, String errMsg) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(errMsg);
        response.setErrCode(errCode);
        return response;
    }

    public static <T> Response<T> fail(BizException bizException) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(bizException.getErrMsg());
        response.setErrCode(bizException.getErrCode());
        return response;
    }

    public static <T> Response<T> fail(BaseExceptionInterface baseExceptionInterface) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(baseExceptionInterface.getErrMsg());
        response.setErrCode(baseExceptionInterface.getErrCode());
        return response;
    }
}
