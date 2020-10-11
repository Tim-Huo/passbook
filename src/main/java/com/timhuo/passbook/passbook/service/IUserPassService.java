package com.timhuo.passbook.passbook.service;

import com.timhuo.passbook.passbook.vo.Pass;
import com.timhuo.passbook.passbook.vo.Response;

/**
 * 获取用户个人优惠券信息
 *
 * @auther: Tim_Huo
 * @date: 2020/10/11 6:42 上午
 */
public interface IUserPassService {

    /**
     * 获取用户个人优惠券信息（用户已领取的优惠券）
     * @param userId 用户ID
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserPassInfo(Long userId) throws Exception;

    /**
     * 获取用户已经消费的优惠券 即已经使用的优惠券功能实现
     * @param userId 用户ID
     * @return {@link Response}
     * @throws Exception 
     */
    Response getUserUsedPassInfo(Long userId) throws Exception;

    /**
     * 获取用户所有的优惠券
     * @param userId 用户id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserAllPassInfo(Long userId) throws Exception;

    /**
     * 用户使用优惠券
     * @param pass {@link Pass}
     * @return {@link Response}
     */
    Response userUsePass(Pass pass);
}
