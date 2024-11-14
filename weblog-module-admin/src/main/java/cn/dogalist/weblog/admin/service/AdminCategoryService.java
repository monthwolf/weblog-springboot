package cn.dogalist.weblog.admin.service;

import cn.dogalist.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import cn.dogalist.weblog.admin.model.vo.category.AddCategoryReqVO;
import cn.dogalist.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import cn.dogalist.weblog.admin.model.vo.category.UpdateCategoryReqVO;
import cn.dogalist.weblog.common.utils.PageResponse;
import cn.dogalist.weblog.common.utils.Response;

public interface AdminCategoryService {
    /**
     * 新增分类
     * @param addCategoryReqVO
     * @return
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);

    /**
     * 更新分类
     */
    Response updateCategory(UpdateCategoryReqVO updateCategoryReqVO);

    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    PageResponse findCategoryPageList(FindCategoryPageListReqVO findCategoryPageListReqVO);

    /**
     * 删除分类
     * @param deleteCategoryReqVO
     * @return
     */
    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);

    /**
     * 获取下拉菜单分类选项
     */
    Response findCategorySelectList();
}
