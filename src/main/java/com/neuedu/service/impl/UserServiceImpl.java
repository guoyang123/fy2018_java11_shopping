package com.neuedu.service.impl;

import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public UserInfo getUserInfoByToken(String token) {


        UserInfo userInfo=userInfoMapper.getUserInfoByToken(token);

        return userInfo;
    }
}
