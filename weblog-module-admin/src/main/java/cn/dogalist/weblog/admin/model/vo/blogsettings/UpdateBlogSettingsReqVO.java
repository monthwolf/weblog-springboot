package cn.dogalist.weblog.admin.model.vo.blogsettings;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "修改博客设置请求 VO")
public class UpdateBlogSettingsReqVO {
    @NotBlank(message = "博客 LOGO 不能为空")
    private String logo;
    @NotBlank(message = "博客名称不能为空")
    private String name;
    @NotBlank(message = "博客作者不能为空")
    private String author;
    @NotBlank(message = "博客描述不能为空")
    private String introduction;
    @NotBlank(message = "博主头像不能为空")
    private String avatar;

    private String githubHomepage;
    private String giteeHomepage;
    private String csdnHomepage;
    private String zhihuHomepage;
}
