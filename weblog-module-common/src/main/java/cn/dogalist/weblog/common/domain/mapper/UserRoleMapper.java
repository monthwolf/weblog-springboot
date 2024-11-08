package cn.dogalist.weblog.common.domain.mapper;


import cn.dogalist.weblog.common.domain.dos.UserRoleDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRoleDO> {
    default List<UserRoleDO> findByUsername(String username)
    {
        LambdaQueryWrapper<UserRoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRoleDO::getUsername, username);
        return selectList(queryWrapper);
    }

}
