package cn.dogalist.weblog.common.enums;

import cn.dogalist.weblog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 响应码枚举
 */

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("10000", "出错啦，后台正在努力修复中..."),

    // ----------- 业务异常状态码 -----------
    PRODUCT_NOT_FOUND("20000", "该产品不存在（测试使用）"),

    // ----------- 参数异常状态码 -----------
    PARAM_NOT_VALID("10001", "参数错误"),
    ;
    private String errCode;
    private String errMsg;

}
