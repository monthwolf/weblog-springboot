package cn.dogalist.weblog.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.dogalist.weblog.common.domain.mapper") //扫描mapper接口
public class MybatisPlusConfig {
}
