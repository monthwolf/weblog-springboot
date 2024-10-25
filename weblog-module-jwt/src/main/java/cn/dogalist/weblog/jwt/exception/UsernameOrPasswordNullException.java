package cn.dogalist.weblog.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 用户名或密码为空异常
 * 注意，需继承自 AuthenticationException，只有该类型异常，才能被后续自定义的认证失败处理器捕获到。
 */
public class UsernameOrPasswordNullException extends AuthenticationException {
    // 构造函数，传入异常信息
    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }

    // 构造函数，传入异常信息和异常
    public UsernameOrPasswordNullException(String msg, Throwable t) {
        super(msg, t);
    }
}
