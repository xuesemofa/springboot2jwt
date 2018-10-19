package com.springboot.crm.business.login.model;

import com.springboot.crm.business.account.model.AccountModel;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class LoginModel extends AccountModel implements Serializable {

    public LoginModel() {
        super();
    }

    public LoginModel(String uuid, String account, String password, String salt, int lx, String parents, String bmid, String isLogin, Timestamp systimes, String creatorAccId, String name, String phone) {
        super(uuid, account, password, salt, lx, parents, bmid, isLogin, systimes, creatorAccId, name, phone);
    }
}
