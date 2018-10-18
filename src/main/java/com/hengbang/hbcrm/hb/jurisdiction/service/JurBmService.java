package com.hengbang.hbcrm.hb.jurisdiction.service;

import com.hengbang.hbcrm.hb.jurisdiction.model.JurBmModel;
import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface JurBmService {

    ResponseResult<JurBmModel> isQx(JurBmModel model);

    ResponseResult<List<JurBmModel>> findByBmId(String bmid);

    ResponseResult<List<JurisdictionModel>> findByBmId2(String bmid);

    ResponseResult<List<JurBmModel>> findByBmIdAndJurId(String bmid, String jurid);
}
