package cn.dogalist.weblog.admin.service.impl;

import cn.dogalist.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import cn.dogalist.weblog.admin.service.AdminBlogSettingsService;
import cn.dogalist.weblog.common.domain.dos.BlogSettingsDO;
import cn.dogalist.weblog.common.domain.mapper.BlogSettingsMapper;
import cn.dogalist.weblog.common.utils.Response;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingsService {
    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        BlogSettingsDO blogSettingsDO = BlogSettingsDO.builder()
                .id(1L)
                .name(updateBlogSettingsReqVO.getName())
                .logo(updateBlogSettingsReqVO.getLogo())
                .author(updateBlogSettingsReqVO.getAuthor())
                .avatar(updateBlogSettingsReqVO.getAvatar())
                .githubHomepage(updateBlogSettingsReqVO.getGithubHomepage())
                .giteeHomepage(updateBlogSettingsReqVO.getGiteeHomepage())
                .csdnHomepage(updateBlogSettingsReqVO.getCsdnHomepage())
                .zhihuHomepage(updateBlogSettingsReqVO.getZhihuHomepage())
                .build();

        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }
}
