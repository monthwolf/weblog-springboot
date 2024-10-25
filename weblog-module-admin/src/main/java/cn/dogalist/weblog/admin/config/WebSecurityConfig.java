package cn.dogalist.weblog.admin.config;

import cn.dogalist.weblog.jwt.config.JwtAuthenticationSecurityConfig;
import cn.dogalist.weblog.jwt.filter.TokenAuthenticationFilter;
import cn.dogalist.weblog.jwt.handler.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @Description: Spring Security 配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;
    @Autowired
    private RestAuthenticationEntryPoint authEntryPoint;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用CSRF
        http.csrf().disable()
                .formLogin().disable() // 禁用form表单登录
                .apply(jwtAuthenticationSecurityConfig) // 添加JWT过滤器
                .and()
                .authorizeRequests()
                .mvcMatchers("/admin/**").authenticated() // 认证所有以/admin为前缀的URL资源
                .anyRequest().permitAll()// 其他页面放行
                .and()
                .httpBasic().authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 前后端分离，无需创建会话
                .and()
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;

    }
    /**
     * Token 校验过滤器
     * @return
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

}
