package cn.dogalist.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "标签数据 VO")
public class FindTagPageListRspVO {
    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id")
    private Long id;
    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String name;
    /**
     * 标签颜色
     */
    @ApiModelProperty(value = "标签颜色")
    private String color;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
