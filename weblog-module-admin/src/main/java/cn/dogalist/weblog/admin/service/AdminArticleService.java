package cn.dogalist.weblog.admin.service;

import cn.dogalist.weblog.admin.model.vo.article.PublishArticleReqVO;
import cn.dogalist.weblog.common.utils.Response;

public interface AdminArticleService {
    /**
     * 发布文章
     * @param publishArticleReqVO
     * @return
     */
    Response publishArticle(PublishArticleReqVO publishArticleReqVO);
}
