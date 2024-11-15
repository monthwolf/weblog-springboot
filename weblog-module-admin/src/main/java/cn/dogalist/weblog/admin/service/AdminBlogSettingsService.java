package cn.dogalist.weblog.admin.service;

import cn.dogalist.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import cn.dogalist.weblog.common.utils.Response;

public interface AdminBlogSettingsService {
    /**
     * 更新博客设置信息
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);
}
