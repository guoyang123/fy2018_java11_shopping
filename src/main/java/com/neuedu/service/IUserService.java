package com.neuedu.service;

import com.neuedu.pojo.UserInfo;

public interface IUserService {
    UserInfo getUserInfoByToken(String token);
}
