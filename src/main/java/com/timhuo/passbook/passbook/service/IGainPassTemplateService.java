package com.timhuo.passbook.passbook.service;

import com.timhuo.passbook.passbook.vo.GainPassTemplateRequest;
import com.timhuo.passbook.passbook.vo.Response;

/**
 * 用户领取优惠券功能实现
 *
 * @auther: Tim_Huo
 * @date: 2020/10/11 6:41 上午
 */
public interface IGainPassTemplateService {

    /**
     * 用户领取优惠券
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     * @throws Exception
     */
    Response gainPassTemplate(GainPassTemplateRequest request) throws Exception;
}
