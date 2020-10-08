package com.timhuo.passbook.passbook.service;

import com.timhuo.passbook.passbook.vo.PassTemplate;

/**
 * Pass HBase 服务
 *
 * @auther: Tim_Huo
 * @date: 2020/10/8 9:40 下午
 */
public interface IHBasePassService {

    /**
     * 将优惠券 写入HBase中
     * @param passTemplate {@link PassTemplate}
     * @return true/false
     */
    boolean dropPassTemplateToHBase(PassTemplate passTemplate);
}
