package cn.dogalist.weblog.web;

import cn.dogalist.weblog.common.domain.dos.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import cn.dogalist.weblog.common.domain.mapper.UserMapper;

import java.util.Date;

@SpringBootTest
@Slf4j
class WeblogWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testLog() {
        log.info("这是一行 Info 级别日志");
        log.warn("这是一行 Warn 级别日志");
        log.error("这是一行 Error 级别日志");

        // 占位符
        String author = "monthwolf";
        log.info("这是一行带有占位符日志，作者：{}", author);
    }
    @Autowired
    private UserMapper userMapper;
    @Test
    void insertTest() {
        UserDO userDO = UserDO.builder()
                .username("monthwolf")
                .password("123456")
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(false)
                .build();
        userMapper.insert(userDO);
    }

}
