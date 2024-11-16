package cn.dogalist.weblog.admin.service.impl;

import cn.dogalist.weblog.admin.convert.BlogSettingsConvert;
import cn.dogalist.weblog.admin.model.vo.blogsettings.FindeBlogSettingsRspVO;
import cn.dogalist.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import cn.dogalist.weblog.admin.service.AdminBlogSettingsService;
import cn.dogalist.weblog.common.domain.dos.BlogSettingsDO;
import cn.dogalist.weblog.common.domain.mapper.BlogSettingsMapper;
import cn.dogalist.weblog.common.utils.Response;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingsService {
    @Autowired
    private BlogSettingsMapper blogSettingsMapper;

    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        BlogSettingsDO blogSettingsDO = BlogSettingsConvert.INSTANCE.convertVO2DO(updateBlogSettingsReqVO);
        blogSettingsDO.setId(1L);

        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }

    @Override
    public Response findBlogSettings() {
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        FindeBlogSettingsRspVO vo = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);
        return Response.success(vo);
    }
}
