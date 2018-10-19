package com.springboot.crm.business.accJur.service.impl;

import com.springboot.crm.business.accJur.mapper.AccJurMapper;
import com.springboot.crm.business.accJur.model.AccJurModel;
import com.springboot.crm.business.accJur.service.AccJurService;
import com.springboot.crm.business.account.mapper.AccountMapper;
import com.springboot.crm.business.account.model.AccountModel;
import com.springboot.crm.business.jurisdiction.mapper.JurisdictionMapper;
import com.springboot.crm.business.jurisdiction.model.JurisdictionModel;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class AccJurServiceImpl implements AccJurService {

    @Autowired
    private AccJurMapper mapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private JurisdictionMapper jurisdictionMapper;

    @Override
    public List<JurisdictionModel> findByAccId(String accid) {
        if ((accid == null || accid.isEmpty()) || accid.equals("0")) {
            Subject subject = SecurityUtils.getSubject();
            AccountModel model1 = (AccountModel) subject.getPrincipal();
            accid = model1.getUuid();
        }
        AccountModel model = accountMapper.getByUuid(accid);
        if (model == null)
            return null;
        else {
//            是否是管理员
            boolean b = model.getLx() == -1;
            if (b)
//                如果是管理员获取所有的权限
                return jurisdictionMapper.findAll();
            else
                return mapper.findByAccId(accid);
        }
    }

    @Override
    public int sq(String jurid, String accid) {
        List<AccJurModel> list = mapper.findByAccIdAndJurId(accid, jurid);
        if (list.size() > 0)
            return mapper.deleteByAccIdAndJurId(accid, jurid);
        else
            return mapper.add(new AccJurModel(GetUuid.getUUID(), accid, jurid));
    }
}
