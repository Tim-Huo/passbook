package com.timhuo.passbook.passbook.vo;

import com.timhuo.passbook.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户领取的优惠券信息
 * @author: Tim_Huo
 * @created: 2020/10/11 06:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassInfo {

    //优惠券
    private Pass pass;

    //优惠券模板
    private PassTemplate passTemplate;

    //优惠券对应的商户信息
    private Merchants merchants;
}
