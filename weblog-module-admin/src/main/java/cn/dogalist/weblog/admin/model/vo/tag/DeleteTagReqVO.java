package cn.dogalist.weblog.admin.model.vo.tag;

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
@ApiModel(value = "删除标签 VO")
public class DeleteTagReqVO {
    @NotNull(message = "标签id不能为空")
    @ApiModelProperty(value = "标签id")
    private long id;
}
