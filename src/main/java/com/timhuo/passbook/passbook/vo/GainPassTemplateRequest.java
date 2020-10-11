package com.timhuo.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户领取优惠券的请求对象
 * @author: Tim_Huo
 * @created: 2020/10/11 06:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GainPassTemplateRequest {

    private Long userId;

    //PassTemplate 对象
    private PassTemplate passTemplate;
}
