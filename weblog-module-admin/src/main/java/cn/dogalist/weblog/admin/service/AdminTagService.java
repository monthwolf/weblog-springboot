package cn.dogalist.weblog.admin.service;


import cn.dogalist.weblog.admin.model.vo.tag.*;
import cn.dogalist.weblog.common.utils.PageResponse;
import cn.dogalist.weblog.common.utils.Response;

public interface AdminTagService {

    Response addTags(AddTagReqVO addTagReqVO);

    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);

    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    Response changeTagStatus(ChangeTagStatusReqVO changeTagStatusReqVO);

    Response findTagSelectList();

    Response updateTag(UpdateTagReqVO updateTagReqVO);
}
