package cn.dogalist.weblog.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码加密配置类
 */
@Component
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用 BCrypt 加密
        return new BCryptPasswordEncoder();
    }

    // 测试BCrypt加密密码
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("test"));
        // 解密
        System.out.println(encoder.matches("dog123", "$2a$10$ouEDxKnsqGexOI8lByF5I.Y9urOznf2OsiS1xzYX3xjxmskZGZ7Ya"));
    }

}
