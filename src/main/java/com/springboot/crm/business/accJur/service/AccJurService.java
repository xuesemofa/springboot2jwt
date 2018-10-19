package com.springboot.crm.business.accJur.service;

import com.springboot.crm.business.jurisdiction.model.JurisdictionModel;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AccJurService {

    List<JurisdictionModel> findByAccId(String accid);

    int sq(String jurid, String accid);
}
