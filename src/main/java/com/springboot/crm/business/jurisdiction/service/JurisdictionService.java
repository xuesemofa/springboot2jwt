package com.springboot.crm.business.jurisdiction.service;

import com.springboot.crm.business.jurisdiction.model.JurisdictionModel;
import com.springboot.crm.utils.result.ResponseResult;

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
