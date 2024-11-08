package cn.dogalist.weblog.jwt.service;

import cn.dogalist.weblog.common.domain.dos.UserDO;
import cn.dogalist.weblog.common.domain.dos.UserRoleDO;
import cn.dogalist.weblog.common.domain.mapper.UserMapper;
import cn.dogalist.weblog.common.domain.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户信息获取类
 */
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 从数据库中查询
        UserDO userDO = userMapper.findByUsername(username);


        // 判断用户是否存在
        if (Objects.isNull(userDO)) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        // 查询权限
        List<UserRoleDO> userRoleDO = userRoleMapper.findByUsername(username);
        // 转数组
        List<String> roles = userRoleDO.stream().map(UserRoleDO::getRole).collect(Collectors.toList());
        String[] roleArr = roles.toArray(new String[roles.size()]);
//        log.info(Arrays.toString(roleArr));

        return User.withUsername(userDO.getUsername())
                .password(userDO.getPassword())
                .authorities(roleArr)
                .build();
    }

}
