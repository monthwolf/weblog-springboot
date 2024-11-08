package cn.dogalist.weblog.admin.service;

import cn.dogalist.weblog.admin.model.vo.UpdateAdminUserPasswordReqVO;
import cn.dogalist.weblog.common.utils.Response;

public interface AdminUserService {
    /**
     * 修改密码
     * @param updateAdminUserPasswordReqVO
     * @return
     */
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);
    Response findUserInfo();
}
