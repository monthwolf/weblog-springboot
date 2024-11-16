package cn.dogalist.weblog.admin.controller;

import cn.dogalist.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import cn.dogalist.weblog.admin.model.vo.category.AddCategoryReqVO;
import cn.dogalist.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import cn.dogalist.weblog.admin.model.vo.category.UpdateCategoryReqVO;
import cn.dogalist.weblog.admin.service.AdminCategoryService;
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
@Api(tags = "Admin 分类模块")
public class AdminCategoryController {
    @Autowired
    private AdminCategoryService adminCategoryService;
    @PostMapping("/category/add")
    @ApiOperation(value = "添加分类")
    @ApiOperationLog(description = "添加分类")
    public Response addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO) {
        return adminCategoryService.addCategory(addCategoryReqVO);
    }

    @PostMapping("/category/update")
    @ApiOperation(value = "修改分类")
    @ApiOperationLog(description = "修改分类")
    public Response updateCategory(@RequestBody @Validated UpdateCategoryReqVO updateCategoryReqVO) {
        return adminCategoryService.updateCategory(updateCategoryReqVO);
    }

    @PostMapping("/category/list")
    @ApiOperation(value = "分类分页列表获取")
    @ApiOperationLog(description = "分类分页列表获取")
    public PageResponse findCategoryPageList(@RequestBody @Validated FindCategoryPageListReqVO findCategoryPageListReqVO) {
        return adminCategoryService.findCategoryPageList(findCategoryPageListReqVO);
    }

    @PostMapping("/category/delete")
    @ApiOperation(value = "删除分类")
    @ApiOperationLog(description = "删除分类")
    public Response deleteCategory(@RequestBody @Validated DeleteCategoryReqVO deleteCategoryReqVO) {
        return adminCategoryService.deleteCategory(deleteCategoryReqVO);
    }

    @PostMapping("/category/select/list")
    @ApiOperation(value = "获取分类下拉列表")
    @ApiOperationLog(description = "获取分类下拉列表")
    public Response findCategorySelectList() {
        return adminCategoryService.findCategorySelectList();
    }
}
