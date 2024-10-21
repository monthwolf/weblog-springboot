package cn.dogalist.weblog.common.aspect;

import cn.dogalist.weblog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;


@Aspect // 声明这是一个切面
@Component // 声明这是一个Spring Bean
@Slf4j // 日志
public class ApiOperationLogAspect {
    /** 以自定义 @ApiOperationLog 注解为切点，凡是添加 @ApiOperationLog 的方法，都会执行环绕中的代码 */
    @Pointcut("@annotation(cn.dogalist.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog() {}

    /**
     * 环绕
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //请求开始实践
            long startTime = System.currentTimeMillis();

            //MDC
            MDC.put("traceId", UUID.randomUUID().toString());

            // 获取被请求的类和方法名
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();

            // 请求入参
            Object[] args = joinPoint.getArgs();
            // 入参转JSON
            String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(", "));

            // 功能描述
            String description = getApiOperationLogDescription(joinPoint);

            // 打印请求参数
            log.info("====== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} =================================== ",
                    description, argsJsonStr, className, methodName);

            // 执行切点方法
            Object result = joinPoint.proceed();

            // 请求耗时
            long executionTime = System.currentTimeMillis() - startTime;
            // 打印请求结果
            log.info("====== 请求结束: [{}], 出参: {}, 请求耗时: {} =================================== ",description, JsonUtil.toJsonString(result), executionTime);
            return result;
        } finally {
            // 清除日志上下文
            MDC.clear();
        }
    }

    /**
     * 获取注解的描述信息
     * @param joinPoint
     * @return
     */
    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {
        // ①从 ProceedingJoinPoint 获取 MethodSignature（方法签名）
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // ②从 MethodSignature 获取 Method（方法对象）
        Method method = methodSignature.getMethod();
        // ③从 Method 获取注解
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);
        // ④获取注解的描述信息
        return apiOperationLog.description();
    }


    /**
     * 转 JSON 字符串
     * @return
     */
    private Function<Object, String> toJsonStr() {
        return arg -> JsonUtil.toJsonString(arg);
    }

}
