package com.timhuo.passbook.passbook.service;

import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import com.timhuo.passbook.passbook.constant.Constants;
import com.timhuo.passbook.passbook.vo.Response;
import com.timhuo.passbook.passbook.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 创建用户服务实现
 * @author: Tim_Huo
 * @created: 2020/10/09 21:37
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService{

    //HBase 客户端
    @Autowired
    private HbaseTemplate hbaseTemplate;

    //redis 客户端
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Response createUser(User user) throws Exception {
        byte[] FAMILY_B = Constants.UserTable.FAMILY_B.getBytes();
        byte[] NAME = Constants.UserTable.NAME.getBytes();
        byte[] AGE = Constants.UserTable.AGE.getBytes();
        byte[] SEX = Constants.UserTable.SEX.getBytes();

        byte[] FANILY_O = Constants.UserTable.FAMILY_O.getBytes();
        byte[] PHONE = Constants.UserTable.PHONE.getBytes();
        byte[] ADDRESS = Constants.UserTable.ADDRESS.getBytes();

        //userID前缀， 当前用户总数
        Long curCount = stringRedisTemplate.opsForValue().increment(Constants.USE_COUNT_REDIS_KEY,1);
        //构造用户id
        Long userId = getUserId(curCount);
        List<Mutation> datas = new ArrayList<Mutation>();
        Put put = new Put(Bytes.toBytes(userId));

        put.addColumn(FAMILY_B,NAME,Bytes.toBytes(user.getBaseInfo().getName()));
        put.addColumn(FAMILY_B,AGE,Bytes.toBytes(user.getBaseInfo().getAge()));
        put.addColumn(FAMILY_B,SEX,Bytes.toBytes(user.getBaseInfo().getAge()));

        put.addColumn(FANILY_O,PHONE,Bytes.toBytes(user.getOtherInfo().getPhone()));
        put.addColumn(FANILY_O,ADDRESS,Bytes.toBytes(user.getOtherInfo().getAddress()));

        datas.add(put);

        hbaseTemplate.saveOrUpdates(Constants.UserTable.TABLE_NAME, datas);

        user.setId(userId);
        return new Response(user);

    }

    /**
     * 生成UserId
     * @param prefix 当前用户数
     * @return
     */
    private long getUserId(Long prefix) {
        String suffix = RandomStringUtils.randomNumeric(5);
        return Long.valueOf(prefix + suffix);
    }

}
