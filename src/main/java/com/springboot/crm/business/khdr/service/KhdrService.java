package com.springboot.crm.business.khdr.service;

import com.springboot.crm.utils.result.ResponseResult;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface KhdrService {

    ResponseResult<Integer> jxXLS(String filePath, String fileName);
}
