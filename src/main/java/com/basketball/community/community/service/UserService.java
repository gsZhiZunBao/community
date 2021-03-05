package com.basketball.community.community.service;

import com.basketball.community.community.mapper.UserMapper;
import com.basketball.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate (User user){
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser == null){
//            插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
//            更新
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }
    }
}
