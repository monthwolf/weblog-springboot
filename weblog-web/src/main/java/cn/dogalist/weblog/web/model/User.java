package cn.dogalist.weblog.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@ApiModel(value = "用户实体类", description = "用户信息")
public class User {
    // 创建时间
    private LocalDateTime createTime;
    // 更新日期
    private LocalDate updateDate;
    // 时间
    private LocalTime time;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @NotNull(message = "性别不能为空")
    @ApiModelProperty(value = "性别",required = true)
    private Integer sex;
    @NotNull(message = "年龄不能为空")
    @ApiModelProperty(value = "年龄",required = true)
    @Min(value = 18,message = "年龄必须大于等于18")
    @Max(value = 100,message = "年龄必须小于等于100")
    private Integer age;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱",required = true)
    private String email;
}
