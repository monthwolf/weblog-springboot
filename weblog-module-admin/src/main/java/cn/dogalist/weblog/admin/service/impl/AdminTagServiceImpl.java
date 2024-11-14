package cn.dogalist.weblog.admin.service.impl;


import cn.dogalist.weblog.admin.model.vo.tag.*;
import cn.dogalist.weblog.admin.service.AdminTagService;
import cn.dogalist.weblog.common.domain.dos.TagDO;
import cn.dogalist.weblog.common.domain.mapper.TagMapper;
import cn.dogalist.weblog.common.enums.ResponseCodeEnum;
import cn.dogalist.weblog.common.exception.BizException;
import cn.dogalist.weblog.common.utils.PageResponse;
import cn.dogalist.weblog.common.utils.Response;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j

public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements AdminTagService {
    @Autowired
    private TagMapper tagMapper;

    /**
     * 添加标签集合
     *
     * @param addTagReqVO
     * @return
     */
    @Override
    public Response addTags(AddTagReqVO addTagReqVO) {
        List<TagDO> tagDOS = addTagReqVO.getTags()
                .stream()
                .map(tagDetailsVO -> TagDO.builder()
                        .name(tagDetailsVO.getName().trim())
                        .color(tagDetailsVO.getColor())
                        .build())
                .collect(Collectors.toList());
        // 批量保存
        try {
            saveBatch(tagDOS);
        } catch (Exception e) {
            log.warn("该标签已存在", e);
        }
        return Response.success();
    }

    @Override
    public PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO) {
        long current = findTagPageListReqVO.getCurrent();
        long size = findTagPageListReqVO.getSize();
        String name = findTagPageListReqVO.getName();
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();
        Page<TagDO> tagDOPage = tagMapper.selectPageList(current, size, name, startDate, endDate);
        List<TagDO> tagDOList = tagDOPage.getRecords();

        // DO 转 VO
        List<FindTagPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOList)) {
            vos = tagDOList.stream().map(TagDO -> FindTagPageListRspVO.builder()
                    .id(TagDO.getId())
                    .name(TagDO.getName())
                    .createTime(TagDO.getCreateTime())
                    .color(TagDO.getColor())
                    .status(TagDO.getStatus())
                    .build()
            ).collect(Collectors.toList());
        }
        return PageResponse.success(tagDOPage, vos);


    }

    @Override
    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
        long id = deleteTagReqVO.getId();
        tagMapper.deleteById(id);
        return Response.success();
    }

    @Override
    public Response changeTagStatus(ChangeTagStatusReqVO changeTagStatusReqVO) {
        long id = changeTagStatusReqVO.getId();
        tagMapper.changeTagStatus(id);
        return Response.success();
    }

    @Override
    public Response findTagSelectList() {
        // 查询所有标签
        List<TagDO> tagDOList = tagMapper.selectList(null);
        // 转换为SelectRspVO
        List<FindTagPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOList)){
            vos = tagDOList.stream().map(tagDO -> FindTagPageListRspVO.builder()
                    .id(tagDO.getId())
                    .name(tagDO.getName())
                    .build())
                    .collect(Collectors.toList());
        }
        return Response.success(vos);
    }

    @Override
    public Response updateTag(UpdateTagReqVO updateTagReqVO) {
        long id = updateTagReqVO.getId();
        TagDO tagDO = tagMapper.selectById(id);
        if (Objects.isNull(tagDO)){
            log.warn("标签不存在: {}", id);
            throw new BizException(ResponseCodeEnum.TAG_NOT_EXISTED);
        }
        tagDO.setName(updateTagReqVO.getName().trim());
        tagDO.setColor(updateTagReqVO.getColor());
        tagMapper.updateById(tagDO);
        return Response.success();
    }
}
