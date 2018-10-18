package com.hengbang.hbcrm.hb.txqk.service;

import com.hengbang.hbcrm.hb.txqk.model.TxqkModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;
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
