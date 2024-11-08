package cn.dogalist.weblog.common.domain.mapper;

import cn.dogalist.weblog.common.domain.dos.UserDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;

public interface UserMapper extends BaseMapper<UserDO> {
    default UserDO findByUsername(String username) {
        // 构建查询条件
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(UserDO::getUsername, username);
        return selectOne(queryWrapper);
    }

    default int updatePasswordByUsername(String username,  String password) {
        // 构建更新
        LambdaUpdateWrapper<UserDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserDO::getUsername, username)
                .set(UserDO::getPassword, password)
                .set(UserDO::getUpdateTime, LocalDateTime.now());
        return update(null, updateWrapper);
    }
}
