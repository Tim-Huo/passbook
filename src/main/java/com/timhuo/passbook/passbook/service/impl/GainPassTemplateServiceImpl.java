package com.timhuo.passbook.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import com.timhuo.passbook.passbook.constant.Constants;
import com.timhuo.passbook.passbook.mapper.PassTemplateRowMapper;
import com.timhuo.passbook.passbook.service.IGainPassTemplateService;
import com.timhuo.passbook.passbook.utils.RowKeyGenUtil;
import com.timhuo.passbook.passbook.vo.GainPassTemplateRequest;
import com.timhuo.passbook.passbook.vo.PassTemplate;
import com.timhuo.passbook.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 用户领取优惠券功能实现
 * @author: Tim_Huo
 * @created: 2020/10/11 14:52
 */
@Slf4j
@Service
public class GainPassTemplateServiceImpl implements IGainPassTemplateService {

    //HBase 客户端
    private final HbaseTemplate hbaseTemplate;

    //redis 客户端
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public GainPassTemplateServiceImpl(HbaseTemplate hbaseTemplate,
                                       StringRedisTemplate redisTemplate) {
        this.hbaseTemplate = hbaseTemplate;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 用户领取优惠券
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response gainPassTemplate(GainPassTemplateRequest request) throws Exception {

        PassTemplate passTemplate;
        String passTemplateId = RowKeyGenUtil.genPassTemplateRowKey(request.getPassTemplate());

        try{
            passTemplate = hbaseTemplate.get(
                    Constants.PassTemplateTable.TABLE_NAME,
                    passTemplateId,
                    new PassTemplateRowMapper()
            );
        }catch (Exception ex){
            log.error("Gain PassTemplate Error: {}", JSON.toJSONString(request.getPassTemplate()));
            return Response.failure("Gain PassTemplate Error!");
        }

        if (passTemplate.getLimit()<= 1 && passTemplate.getLimit() != -1){
            log.error("PassTemplate Limit Max: {}",
                    JSON.toJSONString(request.getPassTemplate()));
            return Response.failure("PassTemplate Limit Max!");
        }

        Date cur = new Date();
        if (!(cur.getTime() >= passTemplate.getStartTime().getTime()&& cur.getTime() <= passTemplate.getEndTime().getTime())){
            log.error("PassTemplate ValidTime Error: {}", JSON.toJSONString(request.getPassTemplate()));
            return Response.failure("PassTemplate ValidTime Error!");
        }

        //减去优惠券的limit
        if (passTemplate.getLimit() != -1){
            List<Mutation> datas = new ArrayList<>();
            byte[] FAMILY_C = Constants.PassTemplateTable.FAMILY_C.getBytes();
            byte[] LIMIT = Constants.PassTemplateTable.LIMIT.getBytes();

            Put put = new Put(Bytes.toBytes(passTemplateId));
            put.addColumn(FAMILY_C,LIMIT,Bytes.toBytes(passTemplate.getLimit() -1 ));

            datas.add(put);

            hbaseTemplate.saveOrUpdates(Constants.PassTemplateTable.TABLE_NAME,datas);
        }

        // 将优惠券保存到用户优惠券表
        if (!addPassForUser(request, passTemplate.getId(), passTemplateId)) {
            return Response.failure("GainPassTemplate Failure!");
        }

        return Response.success();
    }

    /**
     * 给用户添加优惠券
     * @param request {@link GainPassTemplateRequest}
     * @param merchantsId 商户Id
     * @param passTemplateId 优惠券Id
     * @return true/false
     * @throws Exception
     */
    private boolean addPassForUser(GainPassTemplateRequest request,Integer merchantsId,String passTemplateId) throws Exception{

        byte[] FAMILY_I = Constants.PassTable.FAMILY_I.getBytes();
        byte[] USER_ID = Constants.PassTable.USER_ID.getBytes();
        byte[] TEMPLATE_ID = Constants.PassTable.TEMPLATE_ID.getBytes();
        byte[] TOKEN = Constants.PassTable.TOKEN.getBytes();
        byte[] ASSIGNED_DATE = Constants.PassTable.ASSIGNED_DATE.getBytes();
        byte[] CON_DATE = Constants.PassTable.CON_DATE.getBytes();

        List<Mutation> datas = new ArrayList<>();
        Put put = new Put(Bytes.toBytes(RowKeyGenUtil.genPassRowkey(request)));

        put.addColumn(FAMILY_I,USER_ID,Bytes.toBytes(request.getUserId()));
        put.addColumn(FAMILY_I,TEMPLATE_ID,Bytes.toBytes(passTemplateId));

        if (request.getPassTemplate().getHasToken()){
            String token = redisTemplate.opsForSet().pop(passTemplateId);
            if (null == token){
                log.error("Token not exist:{}",passTemplateId);
                return false;
            }
            recordTokenTofile(merchantsId,passTemplateId,token);
            put.addColumn(FAMILY_I,TOKEN, Bytes.toBytes(token));
        }else {
            put.addColumn(FAMILY_I,TOKEN,Bytes.toBytes("-1"));
        }

        put.addColumn(FAMILY_I,ASSIGNED_DATE,Bytes.toBytes(DateFormatUtils.ISO_DATE_FORMAT.format(new Date())));

        put.addColumn(FAMILY_I,CON_DATE,Bytes.toBytes("-1"));

        datas.add(put);

        hbaseTemplate.saveOrUpdates(Constants.PassTable.TABLE_NAME,datas);
        return true;
    }

    /**
     * 将已使用的 token 记录到文件中
     * @param merchantsId 商户 id
     * @param passTemplateId 优惠券 id
     * @param token 分配的优惠券 token
     * */
    private void recordTokenTofile(Integer merchantsId,String passTemplateId,String token) throws Exception{

        Files.write(
                Paths.get(Constants.TOKEN_DIR,String.valueOf(merchantsId),passTemplateId+Constants.USER_TOKEN_SHFFIX),
                (token +"\n").getBytes(),
                StandardOpenOption.APPEND
        );
    }

}
