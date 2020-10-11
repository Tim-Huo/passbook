package com.timhuo.passbook.passbook.utils;

import com.timhuo.passbook.passbook.vo.GainPassTemplateRequest;
import com.timhuo.passbook.passbook.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import com.timhuo.passbook.passbook.vo.Feedback;

/**
 * @description: 生成器工具类
 * @author: Tim_Huo
 * @created: 2020/10/08 19:54
 */
@Slf4j
public class RowKeyGenUtil {

    /**
     * 根据提供的 PassTemplate 对象生成 Rowkey
     * Hbase是基于集群来存储，msd可以分散数据存储的服务器，用来实现负载均衡
     *
     * @param passTemplate {@link PassTemplate}
     * @return String RowKey
     */
    public static String genPassTemplateRowKey(PassTemplate passTemplate){

        String passInfo = String.valueOf(passTemplate.getId())+"_"+passTemplate.getTitle();
        String rowKey = DigestUtils.md5Hex(passInfo);
        log.info("GenPassTemplateRowKey:{},{}",passInfo,rowKey);

        return rowKey;
    }

    /**
     * 根据提供的领取优惠券请求生成 rowkey,只可以在领取优惠券的时候使用
     * Pass RowKey = reversed(userId)+ inverse(timeStamp)+ PAssTemplate RowKey
     * @param request {@link GainPassTemplateRequest}
     * @return String Rowkey
     */
    public static String genPassRowkey(GainPassTemplateRequest request){
        return new StringBuilder(String.valueOf(request.getUserId())).reverse().toString()
                + (Long.MAX_VALUE - System.currentTimeMillis())
                + genPassTemplateRowKey(request.getPassTemplate());
    }

    /**
     * 根据Feedback 构造 Rowkey
     * @param feedback {@link Feedback}
     * @return String RowKey
     */
    public static String genFeedbackRowKey(Feedback feedback){
        return new StringBuilder(String.valueOf(feedback.getUserId())).reverse().toString()+
                (Long.MAX_VALUE-System.currentTimeMillis());

    }

}
