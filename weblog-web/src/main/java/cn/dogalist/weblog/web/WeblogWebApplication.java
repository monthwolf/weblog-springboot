package cn.dogalist.weblog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"cn.dogalist.weblog.*"}) //多模块必须手动指定扫描
public class WeblogWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(WeblogWebApplication.class, args);
    }

}
