package com.hengbang.hbcrm.hb.khdr.service;

import com.hengbang.hbcrm.utils.result.ResponseResult;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface KhdrService {

    ResponseResult<Integer> jxXLS(String filePath, String fileName);
}
