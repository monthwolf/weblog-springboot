package cn.dogalist.weblog.admin.service.impl;

import cn.dogalist.weblog.admin.model.vo.article.PublishArticleReqVO;
import cn.dogalist.weblog.admin.service.AdminArticleService;
import cn.dogalist.weblog.common.domain.dos.ArticleCategoryRelDO;
import cn.dogalist.weblog.common.domain.dos.ArticleContentDO;
import cn.dogalist.weblog.common.domain.dos.ArticleDO;
import cn.dogalist.weblog.common.domain.dos.CategoryDO;
import cn.dogalist.weblog.common.domain.mapper.*;
import cn.dogalist.weblog.common.enums.ResponseCodeEnum;
import cn.dogalist.weblog.common.exception.BizException;
import cn.dogalist.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AdminArticleServiceImpl implements AdminArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response publishArticle(PublishArticleReqVO publishArticleReqVO) {
        // VO 转 ArticleDO
        ArticleDO articleDO = ArticleDO.builder()
                .title(publishArticleReqVO.getTitle())
                .cover(publishArticleReqVO.getCover())
                .summary(publishArticleReqVO.getSummary())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        articleMapper.insert(articleDO);

        // 获取文章ID
        Long articleId = articleDO.getId();

        // VO 转 ArticleContentDO
        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .articleId(articleId)
                .content(publishArticleReqVO.getContent())
                .build();
        articleContentMapper.insert(articleContentDO);

        // 处理分类关联
        Long categoryId = publishArticleReqVO.getCategoryId();
        // 校检分类是否存在
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 分类不存在, categoryId: {}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }

        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);

        // 保存关联标签集合
        List<String> tags = publishArticleReqVO.getTags();
        insertTags(tags);

        return Response.success();
    }

    private void insertTags(List<String> tags) {
        // TODO
    }
}
