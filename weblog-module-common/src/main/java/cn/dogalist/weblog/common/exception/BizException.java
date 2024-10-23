package cn.dogalist.weblog.common.exception;

import lombok.Getter;
import lombok.Setter;


/**
 * 业务异常
 */
@Getter
@Setter
public class BizException extends RuntimeException{
    private String errCode;
    private String errMsg;
    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errCode = baseExceptionInterface.getErrCode();
        this.errMsg = baseExceptionInterface.getErrMsg();
    }
}
