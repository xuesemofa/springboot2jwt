package com.hengbang.hbcrm.hb.jurisdiction.service;

import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface JurisdictionService {

    ResponseResult<List<JurisdictionModel>> findAll();
}
