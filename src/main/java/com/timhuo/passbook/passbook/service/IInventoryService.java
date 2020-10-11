package com.timhuo.passbook.passbook.service;

import com.timhuo.passbook.passbook.vo.Response;

/**
 * 获取库存信息，只返回用户没有领取的，即优惠券库存功能
 *
 * @auther: Tim_Huo
 * @date: 2020/10/11 6:39 上午
 */
public interface IInventoryService {

    /**
     * 获取库存信息
     * @param userId 用户id
     * @return {@link Response}
     */
    Response getInventoryInfo(Long userId) throws Exception;
}
