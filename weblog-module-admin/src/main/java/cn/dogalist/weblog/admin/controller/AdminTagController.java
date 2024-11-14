package cn.dogalist.weblog.admin.controller;

import cn.dogalist.weblog.admin.model.vo.tag.*;
import cn.dogalist.weblog.admin.service.AdminTagService;
import cn.dogalist.weblog.common.aspect.ApiOperationLog;
import cn.dogalist.weblog.common.utils.PageResponse;
import cn.dogalist.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 标签模块")
public class AdminTagController {
    @Autowired
    private AdminTagService adminTagService;
    @PostMapping("/tag/add")
    @ApiOperation(value = "添加标签")
    @ApiOperationLog(description = "添加标签")
    public Response addTag(@RequestBody @Validated AddTagReqVO addTagReqVO) {
        return adminTagService.addTags(addTagReqVO);
    }

    @PostMapping("/tag/update")
    @ApiOperation(value = "修改标签")
    @ApiOperationLog(description = "修改标签")
    public Response updateTag(@RequestBody @Validated UpdateTagReqVO updateTagReqVO) {
        return adminTagService.updateTag(updateTagReqVO);
    }

    @PostMapping("/tag/list")
    @ApiOperation(value = "标签分页列表获取")
    @ApiOperationLog(description = "标签分页列表获取")
    public PageResponse findTagPageList(@RequestBody @Validated FindTagPageListReqVO findTagPageListReqVO) {
        return adminTagService.findTagPageList(findTagPageListReqVO);
    }
//
    @PostMapping("/tag/delete")
    @ApiOperation(value = "删除标签")
    @ApiOperationLog(description = "删除标签")
    public Response deleteTag(@RequestBody @Validated DeleteTagReqVO deleteTagReqVO) {
        return adminTagService.deleteTag(deleteTagReqVO);
    }
    @PostMapping("/tag/change/status")
    @ApiOperation(value = "修改标签状态")
    @ApiOperationLog(description = "修改标签状态")
    public Response changeTagStatus(@RequestBody @Validated ChangeTagStatusReqVO changeTagStatusReqVO) {
        return adminTagService.changeTagStatus(changeTagStatusReqVO);
    }
//
    @GetMapping("/tag/select/list")
    @ApiOperation(value = "获取标签下拉列表")
    @ApiOperationLog(description = "获取标签下拉列表")
    public Response findTagSelectList() {
        return adminTagService.findTagSelectList();
    }
}
