package cn.dogalist.weblog.admin.convert;

import cn.dogalist.weblog.admin.model.vo.blogsettings.FindeBlogSettingsRspVO;
import cn.dogalist.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import cn.dogalist.weblog.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSettingsConvert {

    /**
     * 初始化convert实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * VO 转 DO
     * @param bean
     * @return
     */
    BlogSettingsDO convertVO2DO(UpdateBlogSettingsReqVO bean);
    /**
     * DO 转 VO
     * @param bean
     * @return
     */
    FindeBlogSettingsRspVO convertDO2VO(BlogSettingsDO bean);
}
