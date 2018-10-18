package com.hengbang.hbcrm.hb.khdr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hengbang.hbcrm.hb.customer.model.CusImgModel;
import com.hengbang.hbcrm.hb.customer.model.CustomerModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author ld
 * @name 结构一至但数据库表用customer_dr
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KhdrModel extends CustomerModel implements Serializable {

    public KhdrModel() {
        super();
    }

    public KhdrModel(String uuid, String gsmc, String sf, String dq, String xsdz, String khlx, String khly, String khdj, Date clsj, String zycp1, String zycp2, String zycp3, String xsqy, String ygrs, String cs, String qylx, String pp, String wz, BigDecimal ncke, BigDecimal ndde, String sydjh, String xsqd, String cw_mc, String cw_nsrsbh, String cw_dz, String cw_dh, String cw_khh, String cw_zh, String flag, String gsr, Timestamp system, long bh, Date zhgjsj, List<CusImgModel> list, String fields,
                     String orderBys) {
        super(uuid, gsmc, sf, dq, xsdz, khlx, khly, khdj, clsj, zycp1, zycp2, zycp3, xsqy, ygrs, cs, qylx, pp, wz, ncke, ndde, sydjh, xsqd, cw_mc, cw_nsrsbh, cw_dz, cw_dh, cw_khh, cw_zh, flag, gsr, system, bh, zhgjsj, list, fields, orderBys);
    }
}
