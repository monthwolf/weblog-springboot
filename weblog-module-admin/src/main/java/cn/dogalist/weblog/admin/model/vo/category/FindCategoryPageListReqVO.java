package cn.dogalist.weblog.admin.model.vo.category;

import cn.dogalist.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "查询分类分页数据入参 VO")
public class FindCategoryPageListReqVO extends BasePageQuery {
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String name;
    /**
     * 创建起始日期
     */
    @ApiModelProperty(value = "创建起始日期")
    private LocalDate startDate;
    /**
     * 创建结束日期
     */
    @ApiModelProperty(value = "创建结束日期")
    private LocalDate endDate;
}
