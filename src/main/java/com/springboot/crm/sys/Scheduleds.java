package com.springboot.crm.sys;

import com.springboot.crm.business.customer.mapper.YrghjlMapper;
import com.springboot.crm.business.customer.model.CustomerModel;
import com.springboot.crm.business.customer.model.YrghJlModel;
import com.springboot.crm.business.customer.service.CustomerService;
import com.springboot.crm.business.txqk.mapper.TxQkMapper;
import com.springboot.crm.business.txqk.model.TxqkModel;
import com.springboot.crm.utils.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@Component
public class Scheduleds {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TxQkMapper txQkMapper;
    @Autowired
    private YrghjlMapper yrghjlMapper;

    @Scheduled(cron = "0 0/10 0 * * ?") //每天凌晨0点10分执行
    public void statusCheck() {
        List<TxqkModel> list = txQkMapper.findAll();
        if (list.size() > 0) {
            int i = list.get(0).getQzyrghsj();
            if (i > 0) {
                ResponseResult<List<CustomerModel>> result = customerService.findByZhgjsj(i);
                if (result.isSuccess()) {
                    result.getData().forEach(k -> {
                        customerService.updateByZhgjsj(k.getUuid());
                        YrghJlModel model = new YrghJlModel();
                        model.setBdddkh(k.getUuid());
                        model.setCzr("0");
                        model.setSystimes(new Timestamp(System.currentTimeMillis()));
                        model.setYgsr(k.getGsr());
                        model.setDdyy("因长时间未拜访，系统自动处理");
                        model.setDdhgsr("0");
                        yrghjlMapper.add(model);
                    });
                }
            }
        }
        log.info("强制执行长期未拜访的客户至公海");
    }

}
