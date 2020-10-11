package com.timhuo.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 库存请求响应(商户在平台上投放的可用的优惠券)
 * @author: Tim_Huo
 * @created: 2020/10/11 06:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {

    //用户ID
    private Long userId;

    //优惠券模板信息（用户没有领取的优惠券信息）
    private List<PassTemplateInfo> passTemplateInfos;
}
