package com.springboot.crm.business.khdr.service.impl;

import com.springboot.crm.business.account.mapper.AccountMapper;
import com.springboot.crm.business.account.model.AccountModel;
import com.springboot.crm.business.customer.mapper.CustomerMapper;
import com.springboot.crm.business.customer.model.CustomerModel;
import com.springboot.crm.business.khdr.model.KhdrModel;
import com.springboot.crm.business.khdr.service.KhdrService;
import com.springboot.crm.business.lxfs.mapper.LxfsMapper;
import com.springboot.crm.business.lxfs.model.LxfsModel;
import com.springboot.crm.sys.webSocket.MyWebSocket;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
//抑制弃用警告
@SuppressWarnings("deprecation")
@Service
public class KhdrServiceImpl implements KhdrService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private LxfsMapper lxfsMapper;
    @Autowired
    private AccountMapper accountMapper;

    //springboot 特有的异步处理注解，此注解仅用于方法，且不能是controller，必须得是controller调用的
    // 而且controller必须加@EnableAsync注解
    @Async
    @Override
    public ResponseResult<Integer> jxXLS(String filePath, String fileName) {
        int a = 0;
        //        转换成buffer流
        BufferedInputStream bf = null;
        try {
            File file = new File(filePath + fileName);
            bf = new BufferedInputStream(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(bf);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
//        每行数据装入map
        Map<Long, Map<String, String>> map = new HashMap<>();
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            HSSFRow row = sheet.getRow(rowIndex);
            if (row == null || rowIndex == 0) {
                continue;
            }
//            每行一个map
            Map<String, String> rowMap = new HashMap<>();
            HSSFCell cell = null;
            for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                String value = "";
                cell = row.getCell(columnIndex);
                if (sheet.getRow(0).getCell(columnIndex) == null
                        || sheet.getRow(0).getCell(columnIndex).getStringCellValue().trim().equals(""))
                    continue;
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd")
                                            .format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                value = new DecimalFormat("0").format(cell
                                        .getNumericCellValue());
                            }
                            break;
                        case XSSFCell.CELL_TYPE_FORMULA:
                            // 导入时如果为公式生成的数据则无值
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            } else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            value = "";
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = (cell.getBooleanCellValue() ? "Y" : "N");
                            break;
                        default:
                            value = "";
                    }
                }
                if (!rowMap.containsKey(sheet.getRow(0).getCell(columnIndex).getStringCellValue()))
                    rowMap.put(sheet.getRow(0).getCell(columnIndex).getStringCellValue(), value);
            }
            if (rowMap.size() > 0)
                map.put(Integer.toUnsignedLong(rowIndex), rowMap);
            if (rowIndex < sheet.getLastRowNum()) {
                if (map.size() == 100) {
//                则执行保存
                    ResponseResult<Integer> zlsj = zlsj(map);
                    if (zlsj.isSuccess())
                        a = zlsj.getData();
                    map.clear();
                }
            } else {
                ResponseResult<Integer> zlsj = zlsj(map);
                if (zlsj.isSuccess())
                    a = zlsj.getData();
                map.clear();
            }
        }
        Subject subject = SecurityUtils.getSubject();
        AccountModel model1 = (AccountModel) subject.getPrincipal();
        try {
            MyWebSocket.sendInfo(model1.getName() + "导入的数据已完成处理");
        } catch (IOException e) {
            log.info(model1.getName() + "导入的数据完成通知错误");
        }
        return new ResponseResult<>(true, "导入完成", a);
    }

    //    整理数据为可以保存的格式
    @Transactional
    private ResponseResult<Integer> zlsj(Map<Long, Map<String, String>> map) {
        List<KhdrModel> list = new ArrayList<>();
        List<Integer> i = new ArrayList<>();
        if (map.size() > 0) {
            map.forEach((k, v) -> {
                String uuid = GetUuid.getUUID();
                KhdrModel model = new KhdrModel();
                model.setUuid(uuid);
                LxfsModel lxfsModel = new LxfsModel();
                lxfsModel.setUuid(GetUuid.getUUID());
                lxfsModel.setCusid(uuid);
                v.forEach((k1, v1) -> {
                    if (k1.equals("公司"))
                        model.setGsmc(v1);
                    if (k1.equals("地址"))
                        model.setXsdz(v1);
                    if (k1.equals("类型"))
                        model.setKhlx(v1);
                    if (k1.equals("状态")) {
                        String s = "1";
                        if (v1.equals("潜在"))
                            s = "1";
                        if (v1.equals("合作"))
                            s = "3";
                        model.setKhdj(s);
                    }
                    if (k1.equals("级别"))
                        model.setKhdj(v1);
                    if (k1.equals("负责人"))
                        model.setGsr(v1);
                    if (k1.equals("姓名"))
                        lxfsModel.setXm(v1);
                    if (k1.equals("电话"))
                        lxfsModel.setDh(v1);
                    if (k1.equals("职位"))
                        lxfsModel.setZw(v1);
                });
                CustomerModel model1 = new CustomerModel();
                model1.setGsmc(model.getGsmc());
                List<CustomerModel> page = customerMapper.findByGsmc(model1.getGsmc());
                if (page.size() <= 0) {
                    AccountModel one = accountMapper.getByName(model.getGsr());
                    if (one != null)
                        model.setGsr(one.getUuid());
                    else
                        model.setGsr("0");
                    customerMapper.add(model);
                    lxfsMapper.add(lxfsModel);
                    i.add(1);
                } else {
                    KhdrModel model2 = new KhdrModel();
                    model2.setGsmc(model1.getGsmc());
                    list.add(model2);
                }
            });
            StringJoiner sj = new StringJoiner("");
            list.forEach(k -> {
                sj.add(k.getGsmc() + ",");
            });
            log.info("成功" + i.size() + "条,失败的公司名称为:" + sj.toString());
            return new ResponseResult<>(true, "成功" + i.size() + "条,失败的公司名称为:" + sj.toString(), i.size());
        } else
            return new ResponseResult<>(false, "没有可以操作的数据");
    }
}
