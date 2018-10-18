package com.hengbang.hbcrm.hb.accJur.service.impl;

import com.hengbang.hbcrm.hb.accJur.mapper.AccJurMapper;
import com.hengbang.hbcrm.hb.accJur.model.AccJurModel;
import com.hengbang.hbcrm.hb.accJur.service.AccJurService;
import com.hengbang.hbcrm.hb.account.mapper.AccountMapper;
import com.hengbang.hbcrm.hb.account.model.AccountModel;
import com.hengbang.hbcrm.hb.jurisdiction.mapper.JurisdictionMapper;
import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;
import com.hengbang.hbcrm.utils.uuidUtil.GetUuid;
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
