package com.hengbang.hbcrm.hb.accJur.service;

import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;

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
