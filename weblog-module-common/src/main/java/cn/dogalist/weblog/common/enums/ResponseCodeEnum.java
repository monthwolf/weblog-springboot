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
    // ----------- 1-通用异常状态码 -----------
    SYSTEM_ERROR("10000", "出错啦，后台正在努力修复中..."),

    // ----------- 2-业务异常状态码 -----------
    PRODUCT_NOT_FOUND("20000", "该产品不存在（测试使用）"),
    CATEGORY_NAME_IS_EXISTED("20001","该分类已存在，请勿重复添加！" ),
    CATEGORY_NOT_EXISTED("20002","该分类不存在！"),
    TAG_NOT_EXISTED("20003","该标签不存在！" ),


    // ----------- 3-参数异常状态码 -----------
    PARAM_NOT_VALID("30000", "参数错误"),

    // ----------- 4-权限异常状态码 -----------
    FORBIDDEN("40000", "演示账号仅支持查询操作！"),

    // ----------- 5-用户异常状态码 -----------
    LOGIN_FAIL("50000", "登录失败"),
    USERNAME_OR_PWD_ERROR("50001", "用户名或密码错误"),
    UNAUTHORIZED("50002","无权限访问，请先登录！" ),
    USERNAME_NOT_FOUND("50003", "该用户不存在"),
    PASSWORD_ERROR("50005", "用户密码错误"),
    PASSWORD_NOT_CHANGE("50006","新旧密码不能相等" );
    private String errCode;
    private String errMsg;

}
