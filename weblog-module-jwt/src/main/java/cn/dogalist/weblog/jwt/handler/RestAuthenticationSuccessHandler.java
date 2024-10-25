package cn.dogalist.weblog.jwt.handler;

import cn.dogalist.weblog.common.utils.Response;
import cn.dogalist.weblog.jwt.model.LoginRspVO;
import cn.dogalist.weblog.jwt.utils.JwtTokenHelper;
import cn.dogalist.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 */
@Component
@Slf4j
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // 从 authentication 对象中获取用户的 UserDetails 实例，这里是获取用户的用户名
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // 生成token
        String username = userDetails.getUsername();
        String token = jwtTokenHelper.generateToken(username);
        // 从token获取过期时间
        Long expireTime = jwtTokenHelper.parseToken(token).getBody().getExpiration().getTime();

        // 返回token
        LoginRspVO loginRspVO = LoginRspVO.builder().token(token).expireTime(expireTime).username(username).build();
        log.info("用户{}登录成功，token:{}", username, token);
        ResultUtil.ok(response, Response.success(loginRspVO));
    }
}
