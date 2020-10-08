package com.timhuo.passbook.passbook.mapper;

import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import com.timhuo.passbook.passbook.constant.Constants;
import com.timhuo.passbook.passbook.vo.Pass;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @description: HBase Pass Row To Pass Object
 * @author: Tim_Huo
 * @created: 2020/10/08 19:33
 */
public class PassRowMapper implements RowMapper<Pass> {

    private static byte[] FAMILY_I = Constants.PassTable.FAMILY_I.getBytes();
    private static byte[] USER_ID = Constants.PassTable.USER_ID.getBytes();
    private static byte[] TEMPLATE_ID =Constants.PassTable.TEMPLATE_ID.getBytes();
    private static byte[] TOKEN = Constants.PassTable.TOKEN.getBytes();
    private static byte[] ASSIGNED_DATE = Constants.PassTable.ASSIGNED_DATE.getBytes();
    private static byte[] CON_DATE = Constants.PassTable.CON_DATE.getBytes();

    @Override
    public Pass mapRow(Result result, int i) throws Exception {
        Pass pass = new Pass();

        pass.setUserId(Bytes.toLong(result.getValue(FAMILY_I, USER_ID)));
        pass.setTemplateId(Bytes.toString(result.getValue(FAMILY_I, TEMPLATE_ID)));
        pass.setToken(Bytes.toString(result.getValue(FAMILY_I, TOKEN)));

        String[] patterns = new String[] {"yyyy-DD-dd"};

        //领取日期时间
        pass.setAssignedDate(DateUtils.parseDate(Bytes.toString(result.getValue(FAMILY_I, ASSIGNED_DATE)), patterns));
        String conDateStr = Bytes.toString(result.getValue(FAMILY_I, CON_DATE));

        if (conDateStr.equals("-1")){
            //没有被消费
            pass.setConDate(null);
        }else {
            pass.setConDate(DateUtils.parseDate(conDateStr,patterns));
        }

        pass.setRowKey(Bytes.toString(result.getRow()));
        return pass;
    }

}
