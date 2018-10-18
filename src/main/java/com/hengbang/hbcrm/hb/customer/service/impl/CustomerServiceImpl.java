package com.hengbang.hbcrm.hb.customer.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengbang.hbcrm.hb.account.mapper.AccountMapper;
import com.hengbang.hbcrm.hb.account.model.AccountModel;
import com.hengbang.hbcrm.hb.customer.mapper.CusImgMapper;
import com.hengbang.hbcrm.hb.customer.mapper.CustomerMapper;
import com.hengbang.hbcrm.hb.customer.mapper.YrghjlMapper;
import com.hengbang.hbcrm.hb.customer.model.Customer2Model;
import com.hengbang.hbcrm.hb.customer.model.Customer3Model;
import com.hengbang.hbcrm.hb.customer.model.CustomerModel;
import com.hengbang.hbcrm.hb.customer.model.YrghJlModel;
import com.hengbang.hbcrm.hb.customer.service.CustomerService;
import com.hengbang.hbcrm.hb.gjpl.mapper.GjplMapper;
import com.hengbang.hbcrm.hb.gjpl.model.GjplModel;
import com.hengbang.hbcrm.hb.khslxz.mapper.KhslxzMapper;
import com.hengbang.hbcrm.hb.khslxz.model.KhslxzModel;
import com.hengbang.hbcrm.hb.txqk.mapper.TxQkMapper;
import com.hengbang.hbcrm.hb.txqk.model.TxqkModel;
import com.hengbang.hbcrm.sys.shiro.JWTUtils;
import com.hengbang.hbcrm.utils.date.Dates2;
import com.hengbang.hbcrm.utils.poi.CreateSimpleExcelToDisk;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import com.hengbang.hbcrm.utils.uuidUtil.GetUuid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper mapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private YrghjlMapper yrghjlMapper;
    @Autowired
    private TxQkMapper txQkMapper;
    @Autowired
    private CusImgMapper cusImgMapper;
    @Autowired
    private GjplMapper gjplMapper;
    @Autowired
    private KhslxzMapper khslxzMapper;

    @Transactional
    @Override
    public ResponseResult<CustomerModel> add(CustomerModel model, HttpServletRequest request) {
        CustomerModel model1 = new CustomerModel();
        model1.setGsmc(model.getGsmc());
        List<CustomerModel> list1 = mapper.findByGsmc(model1.getGsmc());
        if (list1.size() > 0)
            return new ResponseResult<>(false, "该公司已存在", null);
        else {
            String accId = JWTUtils.getAccId(request);

            List<KhslxzModel> list2 = khslxzMapper.findAll();
            if (list2.size() > 0) {
                int i = 0;
                for (KhslxzModel m : list2) {
                    if (m.getLx().equals("1"))
                        i = m.getSl();
                }
                if (i >= 0) {
                    //            非合作客户数量限制100
                    List<CustomerModel> list = mapper.findByGsrAndKhlx(accId);
                    if (list.size() >= i)
                        return new ResponseResult<>(false, "非合作客户数量已达到最高值，不在允许添加非合作客户", null);
                }
            }

            model.setGsr(accId);
            model.setSystem(new Timestamp(System.currentTimeMillis()));
            model.setUuid(GetUuid.getUUID());
            CustomerModel bh = mapper.getMaxBh();
            if (bh != null)
                model.setBh(bh.getBh() + 1);
            else
                model.setBh(1L);
            model.setZhgjsj(new Date(System.currentTimeMillis()));
            mapper.add(model);
            if (model.getList().size() > 0) {
                cusImgMapper.adds(model.getList(), model.getUuid(), accId);
            }
            return new ResponseResult<>(true, "成功", null);
        }
    }

    @Transactional
    @Override
    public ResponseResult<CustomerModel> updateGsrById(CustomerModel model, String bz,
                                                       HttpServletRequest request) {
        CustomerModel one = mapper.getById(model.getUuid());
        if (one == null)
            return new ResponseResult<>(false, "该客户已不存在", null);
        if (one.getFlag().equals("-1"))
            return new ResponseResult<>(false, "该客户已被标记为删除", null);
        String accId = JWTUtils.getAccId(request);
        if (model.getGsr().equals("1"))
            model.setGsr(accId);
        if (one.getGsr().equals("0") || one.getGsr().equals(accId)) {
//            判断目标归属人是不是原归属人
            YrghJlModel ygs = yrghjlMapper.getByBdddkh(model.getUuid(), model.getGsr());
            if (ygs != null) {
                long time = ygs.getSystimes().getTime();
                long l = System.currentTimeMillis() - time;
                long l1 = l / (1000 * 60 * 60 * 24);
                if (l1 < 5)
                    return new ResponseResult<>(false, "5天内调入公海的客户不能调回");
            }
//            历史记录
            mapper.addBack(model.getUuid(), accId);
            int i = mapper.updateById(model);
            if (i > 0) {
                if (model.getGsr().equals("0")) {
                    YrghJlModel yrghJlModel = new YrghJlModel();
                    yrghJlModel.setUuid(GetUuid.getUUID());
                    yrghJlModel.setBdddkh(model.getUuid());
                    yrghJlModel.setCzr(accId);
                    yrghJlModel.setDdhgsr(model.getGsr());
                    yrghJlModel.setDdyy(bz);
                    yrghJlModel.setSystimes(new Timestamp(System.currentTimeMillis()));
                    yrghJlModel.setYgsr(one.getGsr());
                    yrghjlMapper.add(yrghJlModel);
                }
                return new ResponseResult<>(true, "成功");
            } else
                return new ResponseResult<>(false, "失败");
        } else {
            List<AccountModel> list = accountMapper.findByParents(accId);
            int i = 0;
            for (AccountModel m : list) {
                if (m.getUuid().equals(one.getGsr()))
                    i = 1;
            }
            if (i > 0 || one.getGsr().equals("0") || one.getGsr().equals(accId)) {
                model.setGsr(model.getGsr());
                //            历史记录
                mapper.addBack(model.getUuid(), accId);
                int j = mapper.updateById(model);
                if (j > 0)
                    return new ResponseResult<>(true, "成功", null);
                else
                    return new ResponseResult<>(false, "失败", null);
            } else
                return new ResponseResult<>(false, "该客户不属于公海也不属于当前登陆人", null);
        }
    }

    @Transactional
    @Override
    public ResponseResult<CustomerModel> updateGsrById2(CustomerModel model) {
//        CustomerModel one = mapper.getById(model.getUuid());
//        if (one == null)
//            return new ResponseResult<>(false, "该客户已不存在", null);
//        if (one.getFlag().equals("-1"))
//            return new ResponseResult<>(false, "该客户已被标记为删除", null);
//        Subject subject = SecurityUtils.getSubject();
//        AccountModel model2 = (AccountModel) subject.getPrincipal();
//        List<AccountModel> list = accountMapper.findByParents2(model2.getUuid());
//        int i = 0;
//        for (AccountModel m : list) {
//            if (m.getUuid().equals(one.getGsr()))
//                i = 1;
//        }
//        if (i > 0 || one.getGsr().equals("0") || one.getGsr().equals(model2.getUuid())) {
//            model.setGsr(model.getGsr());
//            //            历史记录
//            mapper.addBack(model.getUuid(), model2.getUuid());
//            int j = mapper.updateById(model);
//            if (j > 0)
//                return new ResponseResult<>(true, "成功", null);
//            else
//                return new ResponseResult<>(false, "失败", null);
//        } else
//            return new ResponseResult<>(false, "该客户不属于公海也不属于当前登陆人", null);
        return null;
    }

    @Transactional
    @Override
    public ResponseResult<CustomerModel> updateGsrById3(CustomerModel model) {
        CustomerModel one = mapper.getById(model.getUuid());
        if (one == null)
            return new ResponseResult<>(false, "该客户已不存在", null);
        Subject subject = SecurityUtils.getSubject();
        AccountModel model2 = (AccountModel) subject.getPrincipal();
        //            历史记录
        mapper.addBack(model.getUuid(), model2.getUuid());
        int j = mapper.updateById(model);
        if (j > 0)
            return new ResponseResult<>(true, "成功", null);
        else
            return new ResponseResult<>(false, "失败", null);
    }

    @Transactional
    @Override
    public ResponseResult<CustomerModel> updateGsrById4(CustomerModel model, HttpServletRequest request) {
        CustomerModel one = mapper.getById(model.getUuid());
        if (one == null)
            return new ResponseResult<>(false, "该客户已不存在", null);

        CustomerModel one2 = mapper.getById(model.getUuid());
        String lTokenD = request.getHeader("LTokenD");
        Claim sub = JWTUtils.getApp(lTokenD, "sub");
        if (one2.getGsr().equals(sub.asString())) {
            //            历史记录
//            mapper.addBack(model.getUuid(), model2.getUuid());
            List<Customer3Model> list = backUpdateById(model,sub.asString());
            list.forEach(k -> {
                mapper.addBack3(k);
            });
            int j = mapper.updateById(model);
            if (j > 0) {
                if (model.getList().size() > 0) {
                    cusImgMapper.deleteByCusid(model.getUuid());
                    cusImgMapper.adds(model.getList(), model.getUuid(), sub.asString());
                }
                return new ResponseResult<>(true, "成功", null);
            } else
                return new ResponseResult<>(false, "失败", null);
        } else
            return new ResponseResult<>(false, "当前客户已不属于您", null);
    }

    @Override
    public ResponseResult<CustomerModel> getById(String uuid) {
        CustomerModel model = mapper.getById(uuid);
        if (model != null)
            return new ResponseResult<>(true, "成功", model);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Override
    public ResponseResult<CustomerModel> getById2(String uuid) {
        CustomerModel model = mapper.getById2(uuid);
        if (model != null) {
            return new ResponseResult<>(true, "成功", model);
        } else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Override
    public ResponseResult<CustomerModel> getById3(String uuid) {
        CustomerModel model = mapper.getById2(uuid);
        if (model != null) {
            Subject subject = SecurityUtils.getSubject();
            AccountModel model2 = (AccountModel) subject.getPrincipal();
            if (model.getGsr().equals(model2.getUuid()))
                return new ResponseResult<>(true, "成功", model);
            else
                return new ResponseResult<>(false, "当前客户已不属于当前登陆人");
        } else
            return new ResponseResult<>(false, "未查询到数据");
    }

    @Override
    public ResponseResult<PageInfo<CustomerModel>> findAll(int pageNow, int pageSize, CustomerModel model) {
        String px = "";
        if (model.isPx())
            px = getFields(Integer.parseInt(model.getFields())) + " " + model.getOrderBys();
        PageHelper.startPage(pageNow, pageSize, px);
        Page<CustomerModel> page = mapper.findAll(model);
        PageInfo<CustomerModel> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0 && page.get(0).getUuid() != null && !page.get(0).getUuid().isEmpty())
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Override
    public ResponseResult<PageInfo<CustomerModel>> findAll2(int pageNow, int pageSize, CustomerModel model) {
        String px = "";
        if (model.isPx())
            px = getFields(Integer.parseInt(model.getFields())) + " " + model.getOrderBys();
        PageHelper.startPage(pageNow, pageSize, px);
        Page<CustomerModel> page = mapper.findAll2(model);
        PageInfo<CustomerModel> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0)
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Override
    public ResponseResult<PageInfo<CustomerModel>> findAll3(int pageNow, int pageSize, CustomerModel model) {
        String px = "";
        if (model.isPx())
            px = getFields(Integer.parseInt(model.getFields())) + " " + model.getOrderBys();
        PageHelper.startPage(pageNow, pageSize, px);
        Page<CustomerModel> page = mapper.findAll3(model);
        PageInfo<CustomerModel> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0)
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Override
    public ResponseResult<PageInfo<CustomerModel>> findAll4(int pageNow, int pageSize, CustomerModel model) {
        List<TxqkModel> list = txQkMapper.findAll();
        int i = 0;
        if (list.size() > 0)
            i = list.get(0).getTxsj();
        if (i < 1)
            return new ResponseResult<>(false, "未查询到数据", null);
        String px = "";
        if (model.isPx())
            px = getFields(Integer.parseInt(model.getFields())) + " " + model.getOrderBys();
        PageHelper.startPage(pageNow, pageSize, px);
        Page<CustomerModel> page = mapper.findAll4(model, i);
        PageInfo<CustomerModel> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0)
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Override
    public ResponseResult<PageInfo<CustomerModel>> findAll4Hz(int pageNow, int pageSize, CustomerModel model) {
        GjplModel one = gjplMapper.getByLx(3);
        if (one == null)
            return new ResponseResult<>(false, "未查询到数据", null);
        String px = "";
        if (model.isPx())
            px = getFields(Integer.parseInt(model.getFields())) + " " + model.getOrderBys();
        PageHelper.startPage(pageNow, pageSize, px);
        Page<CustomerModel> page = mapper.findAll4Hz(model, one.getZq());
        PageInfo<CustomerModel> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0)
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Override
    public ResponseResult<CustomerModel> gggsr(CustomerModel model) {
//        确认当前客户所属是否是当前登陆人或下级的
        String gsr = model.getGsr();
        Subject subject = SecurityUtils.getSubject();
        AccountModel model2 = (AccountModel) subject.getPrincipal();
        model.setGsr(model2.getUuid());
        Page<CustomerModel> page = mapper.findAll2(model);
        if (page.size() > 0) {
            model.setGsr(gsr);
            int i = mapper.updateById(model);
            if (i > 0)
                return new ResponseResult<>(true, "成功", null);
            else
                return new ResponseResult<>(false, "失败", null);
        } else
            return new ResponseResult<>(false, "当前客户不属于当前操作人或者当前操作人的下级", null);
    }

    @Override
    public ResponseResult<PageInfo<Customer2Model>> xgjl(int pageNow, int pageSize, String cusid) {
        PageHelper.startPage(pageNow, pageSize);
        Page<Customer2Model> page = mapper.findAllXgjl(cusid);
        PageInfo<Customer2Model> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0)
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Override
    public ResponseResult<Customer2Model> findXgjlByUuid(String uuid) {
        Customer2Model one = mapper.findXgjlByUuid(uuid);
        if (one != null)
            return new ResponseResult<>(true, "成功", one);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Transactional
    @Override
    public ResponseResult<Customer2Model> updateByZhgjsj(String uuid) {
        mapper.updateByZhgjsj(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<CustomerModel>> findByZhgjsj(int qzsj) {
        List<CustomerModel> list = mapper.findByZhgjsj(qzsj);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    @Override
    public List<Customer3Model> backUpdateById(CustomerModel model,String account) {
        List<Customer3Model> list = new ArrayList<>();
        CustomerModel one = mapper.getById(model.getUuid());
//        反射获取所有的属性
        Field[] fields = CustomerModel.class.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
//            设置私有属性允许访问
                fields[i].setAccessible(true);
                if (fields[i].getName().equals("gsr") || fields[i].getName().equals("zhgjsj"))
                    continue;
                Object o = fields[i].get(one);
                Object o3 = fields[i].get(model);
                if (o == null && o3 == null)
                    continue;
                if (String.valueOf(o).equals("null") && String.valueOf(o3).isEmpty())
                    continue;
                if (!(String.valueOf(o).equals(String.valueOf(o3)))) {
                    Customer3Model c3 = new Customer3Model();
                    c3.setUuid(GetUuid.getUUID());
                    c3.setBacksystimes(new Timestamp(System.currentTimeMillis()));
                    c3.setXgr(account);
                    c3.setYuuid(one.getUuid());
                    c3.setYz(String.valueOf(o));
                    c3.setXz(String.valueOf(o3));
                    list.add(c3);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ResponseResult<PageInfo<Customer3Model>> xgjl3(int pageNow, int pageSize, String cusid) {
        PageHelper.startPage(pageNow, pageSize);
        Page<Customer3Model> page = mapper.findAllXgjl3(cusid);
        PageInfo<Customer3Model> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0)
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    private String getFields(int i) {
        Field[] fields = CustomerModel.class.getDeclaredFields();
//            设置私有属性允许访问
        fields[i].setAccessible(true);
        String name = fields[i].getName();
        if (name.equals("gsr"))
            name = "name";
        return "convert(" + name + " using gbk)";
    }

    @Override
    public ResponseResult<String> exp(CustomerModel model, String filePath) {
        Page<CustomerModel> page = mapper.findAll(model);
        if (page.size() > 0) {
            try {
                return CreateSimpleExcelToDisk.excels(titles(),
                        Dates2.getDateTimeString2(),
                        getDatas(page.getResult()),
                        filePath);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseResult<>(false, "导出数据错误");
            }
        }
        return new ResponseResult<>(false, "未查询到数据");
    }

    //    生成标题
    private static List<String> titles() {
        List<String> list = new ArrayList<>();
        list.add("标识");
        list.add("公司名称");
        list.add("省");
        list.add("市");
        list.add("县");
        list.add("详细地址");
        list.add("客户来源");
        list.add("客户类型");
        list.add("客户等级");
        list.add("归属人");
        list.add("编号");
        list.add("最后跟进时间");
        return list;
    }

    private static String[][] getDatas(List<CustomerModel> list) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datas[][] = new String[list.size()][36];
        for (int i = 0; i < list.size(); i++) {
            String[] data = datas[i];
            data[0] = list.get(i).getUuid();
            data[1] = list.get(i).getGsmc();
            data[2] = list.get(i).getSf();
            data[3] = list.get(i).getDq();
            data[4] = list.get(i).getCs();
            data[5] = list.get(i).getXsdz();
            data[6] = list.get(i).getKhly();
            data[7] = list.get(i).getKhlx();
            data[8] = list.get(i).getKhdj();
            data[9] = list.get(i).getGsr();
            data[10] = list.get(i).getBh() + "";
            data[11] = list.get(i).getZhgjsj() != null ? format.format(list.get(i).getZhgjsj()) : "";
        }
        return datas;
    }
}
