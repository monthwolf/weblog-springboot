package cn.dogalist.weblog.web.controller;

import cn.dogalist.weblog.common.aspect.ApiOperationLog;
import cn.dogalist.weblog.common.enums.ResponseCodeEnum;
import cn.dogalist.weblog.common.exception.BizException;
import cn.dogalist.weblog.common.utils.JsonUtil;
import cn.dogalist.weblog.common.utils.Response;
import cn.dogalist.weblog.web.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api(tags = "测试接口")
public class TestController {
    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "测试接口")
    public Response test(@RequestBody @Validated User user,BindingResult bindingResult) {
        // 是否存在校检错误
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return Response.fail(errorMsg);
        }

        return Response.success();
    }
    @PostMapping("/testerr")
    @ApiOperationLog(description = "测试业务异常")
    @ApiOperation(value = "测试业务异常")
    public Response testerr(@RequestBody @Validated User user, BindingResult bindingResult) throws BizException {
        // 手动抛异常，入参是前面定义好的异常码枚举，返参统一交给全局异常处理器搞定
        throw new BizException(ResponseCodeEnum.PRODUCT_NOT_FOUND);
    }
    @PostMapping("/testerr2")
    @ApiOperationLog(description = "测试运行时异常")
    @ApiOperation(value = "测试运行时异常")
    public Response testerr2(@RequestBody @Validated User user, BindingResult bindingResult) throws BizException {
        // 手动抛异常
        int a = 1 / 0;
        return Response.success();
    }
    @PostMapping("/test2")
    @ApiOperationLog(description = "测试参数校检抛出异常")
    @ApiOperation(value = "测试参数校检抛出异常")
    public Response test2(@RequestBody @Validated User user) {
        return Response.success();
    }

    @PostMapping("/test3")
    @ApiOperationLog(description = "测试")
    @ApiOperation(value = "测试Jackson自定义")
    public Response test3(@RequestBody @Validated User user) {
        // 打印入参
        log.info(JsonUtil.toJsonString(user));

        // 设置三种日期字段值
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());

        return Response.success(user);
    }


}
