package com.timhuo.passbook.passbook.dao;

import com.timhuo.passbook.passbook.entity.Merchants;

import java.util.List;

public interface MerchantsDao {

    /**
     * 根据id获取商户对象
     *
     * @auther: Tim_Huo
     * @param: id
     * @return: Merchants
     * @date: 2020/10/8 6:20 下午
     */
    Merchants findById(Integer id);

    /**
     * 根据商户名称获取商户对象
     *
     * @auther: Tim_Huo
     * @param: name
     * @return: Merchants
     * @date: 2020/10/8 6:20 下午
     */
    Merchants findByName(String name);

    /**
     * 根据商户 ids 获取商户对象
     *
     * @auther: Tim_Huo
     * @param: ids
     * @return: Merchants
     * @date: 2020/10/8 6:21 下午
     */
    List<Merchants> findByIdIn(List<Integer> ids);
}
