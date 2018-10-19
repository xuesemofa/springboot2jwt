package com.springboot.crm.business.txqk.service;

import com.springboot.crm.business.txqk.model.TxqkModel;
import com.springboot.crm.utils.result.ResponseResult;
import org.apache.ibatis.annotations.Param;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface TxqkService {

    ResponseResult<TxqkModel> setTxqkSj(@Param("model") TxqkModel model);

    ResponseResult<TxqkModel> findAll();
}
