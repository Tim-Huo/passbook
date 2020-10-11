package com.timhuo.passbook.passbook.vo;

import com.timhuo.passbook.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description: 优惠券模板信息
 * @author: Tim_Huo
 * @created: 2020/10/11 06:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplateInfo {

    //优惠券模板信息
    private PassTemplate passTemplate;

    //优惠券对应的商户
    private Merchants merchants;
}
