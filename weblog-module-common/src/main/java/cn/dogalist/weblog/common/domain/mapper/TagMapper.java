package cn.dogalist.weblog.common.domain.mapper;

import cn.dogalist.weblog.common.domain.dos.TagDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

public interface TagMapper extends BaseMapper<TagDO> {
    default Page<TagDO> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate) {
        Page<TagDO> page = new Page<>(current, size);

        // 构建查询
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name),TagDO::getName, name)
                .ge(Objects.nonNull(startDate),TagDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate),TagDO::getCreateTime, endDate)
                .orderByDesc(TagDO::getCreateTime);
        return selectPage(page, wrapper);
    }

    default void changeTagStatus(long id){
        // 查找并修改
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TagDO::getId, id);
        TagDO tagDO = selectOne(wrapper);
        tagDO.setStatus(!tagDO.getStatus());
        updateById(tagDO);
    };
}
