package com.springboot.crm.business.txqk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author ld
 * @name 长时间未拜访的客户进行提醒和强制移入公海的时间设定类
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxqkModel implements Serializable {

    private String uuid;

    //    提醒时间
//    整数精度99，小数精度0
    @Min(value = 0, message = "提醒时间最小值为0")
    @Max(value = 99, message = "提醒时间最大值为99")
    @Digits(integer = 99, fraction = 0, message = "强制移入公海时间整数精度最大99，小数精度0")
    private int txsj;

    //    强制移入公海时间
    @Min(value = 0, message = "强制移入公海时间最小值为0")
    @Max(value = 99, message = "强制移入公海时间最大值为99")
    @Digits(integer = 99, fraction = 0, message = "强制移入公海时间整数精度最大99，小数精度0")
    private int qzyrghsj;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getTxsj() {
        return txsj;
    }

    public void setTxsj(int txsj) {
        this.txsj = txsj;
    }

    public int getQzyrghsj() {
        return qzyrghsj;
    }

    public void setQzyrghsj(int qzyrghsj) {
        this.qzyrghsj = qzyrghsj;
    }

    @Override
    public String toString() {
        return "TxqkModel{" +
                "uuid='" + uuid + '\'' +
                ", txsj=" + txsj +
                ", qzyrghsj=" + qzyrghsj +
                '}';
    }
}
