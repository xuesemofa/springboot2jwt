package com.hengbang.hbcrm.hb.jurisdiction.service.impl;

import com.hengbang.hbcrm.hb.jurisdiction.mapper.JurBmMapper;
import com.hengbang.hbcrm.hb.jurisdiction.model.JurBmModel;
import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;
import com.hengbang.hbcrm.hb.jurisdiction.service.JurBmService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import com.hengbang.hbcrm.utils.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class JurBmServiceImpl implements JurBmService {

    @Autowired
    private JurBmMapper mapper;

    @Transactional
    @Override
    public ResponseResult<JurBmModel> isQx(JurBmModel model) {
        List<JurBmModel> list = mapper.findByBmIdAndJurId(model.getBmId(), model.getJurId());
        if (list.size() > 0)
            mapper.deleteByBmIdAndJurId(model.getBmId(), model.getJurId());
        else {
            model.setUuid(GetUuid.getUUID());
            mapper.add(model);
        }
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<JurBmModel>> findByBmId(String bmid) {
        List<JurBmModel> list = mapper.findByBmId(bmid);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<List<JurisdictionModel>> findByBmId2(String bmid) {
        List<JurisdictionModel> list = mapper.findByBmId2(bmid);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录",new ArrayList<>());
    }

    @Override
    public ResponseResult<List<JurBmModel>> findByBmIdAndJurId(String bmid, String jurid) {
        List<JurBmModel> list = mapper.findByBmIdAndJurId(bmid, jurid);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
