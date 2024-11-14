package cn.dogalist.weblog.admin.service.impl;

import cn.dogalist.weblog.admin.model.vo.UpdateAdminUserPasswordReqVO;
import cn.dogalist.weblog.admin.model.vo.user.FindUserInfoRspVO;
import cn.dogalist.weblog.admin.service.AdminUserService;
import cn.dogalist.weblog.common.domain.dos.UserDO;
import cn.dogalist.weblog.common.domain.dos.UserRoleDO;
import cn.dogalist.weblog.common.domain.mapper.UserMapper;
import cn.dogalist.weblog.common.domain.mapper.UserRoleMapper;
import cn.dogalist.weblog.common.enums.ResponseCodeEnum;
import cn.dogalist.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        // 获取用户、旧密码、密码
        String userName = updateAdminUserPasswordReqVO.getUsername();
        String oldPassword = updateAdminUserPasswordReqVO.getOldPassword();
        String password = updateAdminUserPasswordReqVO.getPassword();


        UserDO userDO = userMapper.findByUsername(userName);
        // 用户是否存在
        if (userDO == null) {
            return Response.fail(ResponseCodeEnum.USERNAME_NOT_FOUND);
        }
        // 校验旧密码是否正确
        if (!passwordEncoder.matches(oldPassword, userDO.getPassword())) {
            return Response.fail(ResponseCodeEnum.PASSWORD_ERROR);
        }
        // 新旧密码一致
        if (oldPassword.equals(password)) {
            return Response.fail(ResponseCodeEnum.PASSWORD_NOT_CHANGE);
        }

        // 加密
        String encryptPassword = passwordEncoder.encode(password);
        // 更新密码
        int count = userMapper.updatePasswordByUsername(userName, encryptPassword);
        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.SYSTEM_ERROR);

    }

    @Override
    public Response findUserInfo() {
        // 获取存储在ThreadLocal中的用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 获取用户权限角色
        List<UserRoleDO> userRoleDO = userRoleMapper.findByUsername(username);
        // 封装成字符串数组
        String[] roleArr = userRoleDO.stream().map(UserRoleDO::getRole).collect(Collectors.toList()).toArray(new String[userRoleDO.size()]);
        // 获取用户
        UserDO userDO = userMapper.findByUsername(username);
        String email = userDO.getEmail();
        Boolean active = userDO.getIsActive();
        // 格式化日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("Asia/Shanghai"));
        String createTimeStr = formatter.format(userDO.getCreateTime().toInstant());



        return Response.success(FindUserInfoRspVO.builder().username(username).roles(roleArr).email(email).isActive(active).createTime(createTimeStr).build());
    }

}
