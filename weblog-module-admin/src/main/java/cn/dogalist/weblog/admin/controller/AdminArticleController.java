package cn.dogalist.weblog.admin.controller;

import cn.dogalist.weblog.admin.model.vo.article.PublishArticleReqVO;
import cn.dogalist.weblog.admin.service.AdminArticleService;
import cn.dogalist.weblog.common.aspect.ApiOperationLog;
import cn.dogalist.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/article")
@Api(tags = "Admin 文章模块")
public class AdminArticleController {
    @Autowired
    private AdminArticleService adminArticleService;

    @PostMapping("/publish")
    @ApiOperation(value = "发布文章")
    @ApiOperationLog(description = "发布文章")
    public Response publishArticle(@RequestBody @Validated PublishArticleReqVO publishArticleReqVO) {
        return adminArticleService.publishArticle(publishArticleReqVO);
    }
}
