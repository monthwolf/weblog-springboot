package cn.dogalist.weblog.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录响应VO（视图对象）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRspVO {
    /**
     * token
     */
    private String token;
    /**
     * 用户名
     */
    private String username;

    /**
     * 过期时间
     */
    private Long expireTime;
}
