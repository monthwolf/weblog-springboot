package cn.dogalist.weblog.admin.model.vo.blogsettings;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "博客设置信息响应 VO")
public class FindeBlogSettingsRspVO {
    private String logo;
    private String name;
    private String author;
    private String introduction;
    private String avatar;
    private String githubHomepage;
    private String giteeHomepage;
    private String csdnHomepage;
    private String zhihuHomepage;
}
