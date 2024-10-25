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

    // ----------- 用户异常状态码 -----------
    LOGIN_FAIL("20000", "登录失败"),
    USERNAME_OR_PWD_ERROR("20001", "用户名或密码错误"),
    UNAUTHORIZED("20002","无权限访问，请先登录！" );
    private String errCode;
    private String errMsg;

}
