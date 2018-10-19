package com.springboot.crm.business.jurisdiction.service;

import com.springboot.crm.business.jurisdiction.model.JurBmModel;
import com.springboot.crm.business.jurisdiction.model.JurisdictionModel;
import com.springboot.crm.utils.result.ResponseResult;

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
