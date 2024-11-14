package cn.dogalist.weblog.admin.service.impl;

import cn.dogalist.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import cn.dogalist.weblog.admin.model.vo.category.FindCategoryPageListRspVO;
import cn.dogalist.weblog.admin.model.vo.category.AddCategoryReqVO;
import cn.dogalist.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import cn.dogalist.weblog.admin.model.vo.category.UpdateCategoryReqVO;
import cn.dogalist.weblog.admin.service.AdminCategoryService;
import cn.dogalist.weblog.common.domain.dos.CategoryDO;
import cn.dogalist.weblog.common.domain.mapper.CategoryMapper;
import cn.dogalist.weblog.common.enums.ResponseCodeEnum;
import cn.dogalist.weblog.common.exception.BizException;
import cn.dogalist.weblog.common.model.vo.SelectRspVO;
import cn.dogalist.weblog.common.utils.PageResponse;
import cn.dogalist.weblog.common.utils.Response;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j

public class AdminCategoryServiceImpl implements AdminCategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    public Response addCategory(AddCategoryReqVO addCategoryReqVO) {
        String name = addCategoryReqVO.getName();
        CategoryDO categoryDO = categoryMapper.findByName(name);
        if (Objects.nonNull(categoryDO)) {
            log.warn("分类名称重复: {}", name);
            throw new BizException(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }

        // 构建分类
        CategoryDO category = CategoryDO.builder()
                .name(name.trim())
                .sort(addCategoryReqVO.getSort())
                .build();
        categoryMapper.insert(category);
        log.info("添加分类: {}", category);
        return Response.success();
    }

    @Override
    public Response updateCategory(UpdateCategoryReqVO updateCategoryReqVO) {
        Long id = updateCategoryReqVO.getId();
        CategoryDO categoryDO = categoryMapper.selectById(updateCategoryReqVO.getId());
        if (Objects.isNull(categoryDO)) {
            log.warn("分类不存在: {}", id);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }
        // 构建分类
        CategoryDO category = CategoryDO.builder()
                .id(id)
                .name(updateCategoryReqVO.getName().trim())
                .sort(updateCategoryReqVO.getSort())
                .build();
        categoryMapper.updateById(category);
        log.info("更新分类: {}", category);
        return Response.success();
    }

    @Override
    public PageResponse findCategoryPageList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
        long current = findCategoryPageListReqVO.getCurrent();
        long size = findCategoryPageListReqVO.getSize();
        String name = findCategoryPageListReqVO.getName();
        LocalDate startDate = findCategoryPageListReqVO.getStartDate();
        LocalDate endDate = findCategoryPageListReqVO.getEndDate();
        Page<CategoryDO> categoryDOPage = categoryMapper.selectPageList(current, size, name, startDate, endDate);
        List<CategoryDO> categoryDOList = categoryDOPage.getRecords();
        // DO 转 VO
        List<FindCategoryPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOList)){
            vos = categoryDOList.stream().map(categoryDO -> FindCategoryPageListRspVO.builder()
                    .id(categoryDO.getId())
                    .name(categoryDO.getName())
                    .sort(categoryDO.getSort())
                    .createTime(categoryDO.getCreateTime())
                    .build())
                    .collect(Collectors.toList());
        }

        return PageResponse.success(categoryDOPage, vos);
    }

    @Override
    public Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO) {
        // 分类id
        long id = deleteCategoryReqVO.getId();
        categoryMapper.deleteById(id);
        return Response.success();
    }

    @Override
    public Response findCategorySelectList() {
        // 查询所有分类
        List<CategoryDO> categoryDOList = categoryMapper.selectList(null);
        List<SelectRspVO> selectRspVOList = null;
        if (!CollectionUtils.isEmpty(categoryDOList)){
            // 转换为SelectRspVO
            selectRspVOList = categoryDOList.stream().map(categoryDO -> SelectRspVO.builder()
                    .id(categoryDO.getId())
                    .label(categoryDO.getName())
                    .build())
                    .collect(Collectors.toList());
        }
        return Response.success(selectRspVOList);
    }
}
