package com.timhuo.passbook.passbook.service.impl;

import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import com.timhuo.passbook.passbook.constant.Constants;
import com.timhuo.passbook.passbook.service.IHBasePassService;
import com.timhuo.passbook.passbook.utils.RowKeyGenUtil;
import com.timhuo.passbook.passbook.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.hadoop.hbase.client.Put;

/**
 * @description: 优惠券 HBase 服务
 * @author: Tim_Huo
 * @created: 2020/10/09 06:59
 */
@Slf4j
@Service
public class HBasePassServiceImpl implements IHBasePassService {

    //HBase 客户端
    private final HbaseTemplate hbaseTemplate;

    @Autowired
    public HBasePassServiceImpl(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    @Override
    public boolean dropPassTemplateToHBase(PassTemplate passTemplate) {
        String rowkey = RowKeyGenUtil.genPassTemplateRowKey(passTemplate);
        try{
            if (hbaseTemplate.getConnection().getTable(TableName.valueOf(Constants.PassTemplateTable.TABLE_NAME))
                    .exists(new Get(Bytes.toBytes(rowkey)))){
                log.warn("RowKey {} is already exist!",rowkey);
                return false;
            }
        } catch (Exception e) {
            log.error("DropPassTemplateToHBase Error:{}",e.getMessage());
            return false;
        }

        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.ID),
                Bytes.toBytes(passTemplate.getId()));

        put.addColumn(Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.TITLE),
                Bytes.toBytes(passTemplate.getTitle()));

        put.addColumn(Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.SUMMARY),
                Bytes.toBytes(passTemplate.getSummary()));

        put.addColumn(Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.DESC),
                Bytes.toBytes(passTemplate.getDesc()));

        put.addColumn(Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.HAS_TOKEN),
                Bytes.toBytes(passTemplate.getHasToken()));

        put.addColumn(Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.BACKGROUND),
                Bytes.toBytes(passTemplate.getBackground()));

        put.addColumn(Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C),
                Bytes.toBytes(Constants.PassTemplateTable.LIMIT),
                Bytes.toBytes(passTemplate.getLimit()));

        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C),
                Bytes.toBytes(Constants.PassTemplateTable.START),
                Bytes.toBytes(DateFormatUtils.ISO_DATE_FORMAT.format(passTemplate.getStartTime())));

        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C),
                Bytes.toBytes(Constants.PassTemplateTable.END),
                Bytes.toBytes(DateFormatUtils.ISO_DATE_FORMAT.format(passTemplate.getEndTime())));

        hbaseTemplate.saveOrUpdate(Constants.PassTemplateTable.TABLE_NAME,put);

        return true;


    }
}
