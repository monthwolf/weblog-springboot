package cn.dogalist.weblog.common.domain.mapper;

import cn.dogalist.weblog.common.domain.dos.UserDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UserMapper extends BaseMapper<UserDO> {
    default UserDO findByUsername(String username) {
        // 构建查询条件
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(UserDO::getUsername, username);
        return selectOne(queryWrapper);
    }
}
