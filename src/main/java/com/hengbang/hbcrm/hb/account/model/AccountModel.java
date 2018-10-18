package com.hengbang.hbcrm.hb.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountModel implements Serializable {

    private String uuid;
    //    账户
    @NotBlank(message = "账户不能为空")
    @Size(max = 200, message = "账户最大长度为200位")
    private String account;
    //    密码
    private String password;
    //    加密盐
    private String salt;
    //    账户类型( -1:管理员 1：普通用户)
    private int lx = 1;
    //    所属上级
    private String parents;
    //    所属部门
    private String bmid;
    //    是否首次登录-更改为是否允许登录 N:不允许 Y：允许
    private String isLogin;
    //    账户生成系统时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp systimes;
    //    账户生成人
    private String creatorAccId;
    //    姓名
    private String name;
    //    手机
    private String phone;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getLx() {
        return lx;
    }

    public void setLx(int lx) {
        this.lx = lx;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    public Timestamp getSystimes() {
        return systimes;
    }

    public void setSystimes(Timestamp systimes) {
        this.systimes = systimes;
    }

    public String getCreatorAccId() {
        return creatorAccId;
    }

    public void setCreatorAccId(String creatorAccId) {
        this.creatorAccId = creatorAccId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBmid() {
        return bmid;
    }

    public void setBmid(String bmid) {
        this.bmid = bmid;
    }

    public AccountModel() {
        super();
    }

    public AccountModel(String uuid, String account, String password, String salt, int lx, String parents, String bmid, String isLogin, Timestamp systimes, String creatorAccId, String name, String phone) {
        this.uuid = uuid;
        this.account = account;
        this.password = password;
        this.salt = salt;
        this.lx = lx;
        this.parents = parents;
        this.bmid = bmid;
        this.isLogin = isLogin;
        this.systimes = systimes;
        this.creatorAccId = creatorAccId;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "uuid='" + uuid + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", lx=" + lx +
                ", parents='" + parents + '\'' +
                ", isLogin=" + isLogin +
                ", systimes=" + systimes +
                ", creatorAccId='" + creatorAccId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
