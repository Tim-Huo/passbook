package com.timhuo.passbook.passbook.constant;

/**
 * @description: 优惠券状态
 * @author: Tim_Huo
 * @created: 2020/10/08 17:32
 */
public enum  PassStatus {

    UNUSED(1,"未被使用的"),
    USED(2,"已经被使用的"),
    ALL(3,"全部领取的");

    private Integer code;

    private String desc;

    PassStatus(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getDesc(){
        return this.desc;
    }
}
