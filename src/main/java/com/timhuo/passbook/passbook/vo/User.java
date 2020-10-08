package com.timhuo.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Tim_Huo
 * @created: 2020/10/08 18:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    //用户id
    private Long id;

    //用户基本信息
    private BaseInfo baseInfo;

    //用户额外信息
    private OtherInfo otherInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BaseInfo{
        private String name;
        private Integer age;
        private String sex;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OtherInfo{
        private String phone;
        private String address;
    }

}