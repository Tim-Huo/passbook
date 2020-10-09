package com.timhuo.passbook.passbook.service;

import com.timhuo.passbook.passbook.vo.Response;
import com.timhuo.passbook.passbook.vo.User;

/**
 * 用户服务：创建USer服务
 *
 * @auther: Tim_Huo
 * @date: 2020/10/9 9:31 下午
 */
public interface IUserService {

    /**
     * 创建用户
     * @param user {@link User}
     * @return     {@link Response}
     * @throws Exception
     */
    Response createUser(User user) throws Exception;

}
