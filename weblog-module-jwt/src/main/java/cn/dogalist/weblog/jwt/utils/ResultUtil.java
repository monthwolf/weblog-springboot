package cn.dogalist.weblog.jwt.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 结果工具类
 */
public class ResultUtil {
    /**
     * 成功响应
     * 
     * @param response
     * @param result
     * @throws IOException
     */
    public static void ok(HttpServletResponse response, Object result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value()); // 200
        response.setContentType("application/json");

        // 输出响应
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        // 忽略为null的字段
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 将结果转换为json字符串
        writer.write(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }

    /**
     * 失败响应
     * 
     * @param response
     * @param result
     * @throws IOException
     */
    public static void fail(HttpServletResponse response, Object result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        writer.write(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }

    /**
     * 失败响应
     * 
     * @param response
     * @param status
     * @param result
     * @throws IOException
     */
    public static void fail(HttpServletResponse response, int status, Object result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        writer.write(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }

}
