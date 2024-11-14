package cn.dogalist.weblog.admin.model.vo.category;

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
@ApiModel(value = "分类数据 VO")
public class FindCategoryPageListRspVO {
    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Long id;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String name;
    /**
     * 优先级
     */
    @ApiModelProperty(value = "优先级")
    private Integer sort;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 文章数量
     */
    // todo 返回该分类下文章数量
    //    private Long articleCount;

}
