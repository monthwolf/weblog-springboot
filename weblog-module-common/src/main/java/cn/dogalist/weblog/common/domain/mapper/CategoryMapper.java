package cn.dogalist.weblog.common.domain.mapper;

import cn.dogalist.weblog.common.domain.dos.CategoryDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public interface CategoryMapper extends BaseMapper<CategoryDO> {
    /**
     * 根据分类名查询
     *
     * @param name 分类名
     * @return
     */
    default CategoryDO findByName(String name) {
        // 构建查询
        LambdaQueryWrapper<CategoryDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryDO::getName, name);

        return selectOne(queryWrapper);
    }

    default Page<CategoryDO> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate) {
        // 分页对象(查询在第几页、每页多少数据)
        Page<CategoryDO> page = new Page<>(current, size);

        // 构建查询
        LambdaQueryWrapper<CategoryDO> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.isNotBlank(name), CategoryDO::getName, name)
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)
                .orderByAsc(CategoryDO::getSort); // 排序
        return selectPage(page, queryWrapper);
    }

}
