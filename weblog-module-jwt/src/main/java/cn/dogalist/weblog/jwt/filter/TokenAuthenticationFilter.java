package cn.dogalist.weblog.jwt.filter;

import cn.dogalist.weblog.jwt.utils.JwtTokenHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 令牌认证过滤器
 */
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 认证过滤器
     * 
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 从请求头中获取Authorization字段
        String authorization = request.getHeader("Authorization");
        // 判断值是否以Bearer开头
        if (authorization != null && authorization.startsWith("Bearer ")) {
            // 获取token
            String token = authorization.substring(7);
            log.info("token: {}", token);
            // 判断token是否为空
            if (StringUtils.isNotBlank(token)) {
                // 校检token是否可用
                try {
                    jwtTokenHelper.validateToken(token);
                } catch (SignatureException | MalformedJwtException | UnsupportedJwtException
                        | IllegalArgumentException e) {
                    authenticationEntryPoint.commence(request, response,
                            new AuthenticationServiceException("token不可用"));
                    return;
                } catch (ExpiredJwtException e) {
                    authenticationEntryPoint.commence(request, response,
                            new AuthenticationServiceException("token已过期"));
                    return;
                }
                // 从token中获取用户名
                String username = jwtTokenHelper.getUsernameFromToken(token);
                // 判断用户名是否为空且用户是否认证
                if (StringUtils.isNotBlank(username)
                        && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    // 从数据库中获取用户信息
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // 将用户信息放入 authentication
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    // 将请求信息放入 authentication
                    authentication.setDetails(new WebAuthenticationDetails(request));
                    // 将 authentication 存入 ThreadLocal, 方便后续获取用户信息
                    log.info(authorization);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        // 放行请求
        filterChain.doFilter(request, response);
    }

}
