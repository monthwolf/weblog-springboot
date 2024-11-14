package cn.dogalist.weblog.admin.model.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "删除分类 VO")
public class DeleteCategoryReqVO {
    @NotNull(message = "分类id不能为空")
    @ApiModelProperty(value = "分类id")
    private long id;
}
