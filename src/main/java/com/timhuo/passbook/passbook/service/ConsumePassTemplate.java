package com.timhuo.passbook.passbook.service;

import com.timhuo.passbook.passbook.service.IHBasePassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 消费kafka中的消费券
 * @author: Tim_Huo
 * @created: 2020/10/08 21:42
 */
@Slf4j
@Service
public class ConsumePassTemplate {

    /** pass 相关的 HBase 服务 */
    private final IHBasePassService passService;

    @Autowired
    public ConsumePassTemplate(IHBasePassService passService) {
        this.passService = passService;
    }

}
