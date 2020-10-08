package com.timhuo.passbook.passbook.constant;

/**
 * 评论类型枚举(用户反馈）
 *
 * @auther: Tim_Huo
 * @date: 2020/10/8 5:57 下午
 */
public enum FeedbackType {

    PASS(1,"针对优惠券的评论"),
    APP(2,"针对卡包APP的评论");

    //评论类型编码
    private Integer code;

    //评论类型描述
    private String desc;

    FeedbackType(Integer code,String desc){
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
