package cn.dogalist.weblog.jwt.config;

import cn.dogalist.weblog.jwt.filter.JwtAuthenticationFilter;
import cn.dogalist.weblog.jwt.handler.RestAuthenticationFailureHandler;
import cn.dogalist.weblog.jwt.handler.RestAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JWT认证配置类
 */
@Configuration
public class JwtAuthenticationSecurityConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    // 用Autowired注解注入成功和失败的处理器、密码加密方式、用户信息获取类
    @Autowired
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 自定义用于JWT的过滤器
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        // 设置登录认证对应处理类
        filter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // 设置密码加密方式
        provider.setPasswordEncoder(passwordEncoder);

        // 设置用户信息获取类
        provider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(provider);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    }

}
