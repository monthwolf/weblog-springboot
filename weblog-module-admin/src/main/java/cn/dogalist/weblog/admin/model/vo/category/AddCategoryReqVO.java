package cn.dogalist.weblog.admin.model.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "添加分类 VO")
public class AddCategoryReqVO {
    @NotBlank(message = "分类名称不能为空")
    @Length(min = 1,max = 10, message = "分类名称长度为1-10")
    @ApiModelProperty(value = "分类名称")
    private String name;

    @NotNull(message = "优先级不能为空")
    @ApiModelProperty(value = "优先级")
    @Min(value = 1, message = "优先级不能小于1")
    @Max(value = 99, message = "优先级不能大于99")
    private Integer sort;


}
