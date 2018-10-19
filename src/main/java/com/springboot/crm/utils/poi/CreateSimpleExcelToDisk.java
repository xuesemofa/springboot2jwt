package com.springboot.crm.utils.poi;

import com.springboot.crm.business.account.model.AccountModel;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.FileOutputStream;
import java.util.List;

//抑制弃用警告
@SuppressWarnings("deprecation")
public class CreateSimpleExcelToDisk {

    /**
     * 自动生成excel
     *
     * @param list     标题
     * @param fileName 文件名称
     * @param datas    数据
     * @param filePath 存放位置
     * @return
     * @throws Exception
     */
    public static ResponseResult<String> excels(List<String> list,
                                                String fileName,
                                                String[][] datas,
                                                String filePath) throws Exception {
        if (list == null || list.size() <= 0)
            return new ResponseResult<>(false, "没有标题");
        if (fileName == null || fileName.isEmpty())
            return new ResponseResult<>(false, "没有文件名称");
        if (datas == null || datas.length <= 0)
            return new ResponseResult<>(false, "没有数据");
        if (filePath == null || filePath.isEmpty())
            return new ResponseResult<>(false, "没有文件存放地址");
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(fileName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
//            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//创建标题行
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);
        for (int i = 0; i < list.size(); i++) {
            cell = row.createCell((short) i + 1);
            cell.setCellValue(list.get(i));
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据
        for (int i = 0; i < datas.length; i++) {
            String[] data = datas[i];
            row = sheet.createRow(i + 1);
            row.createCell((short) 0).setCellValue(i + 1);
            for (int j = 0; j < data.length; j++) {
                // 第四步，创建单元格，并设置值
                if (data[j] != null)
                    row.createCell((short) j + 1).setCellValue(data[j]);
            }
        }
        // 第六步，将文件存到指定位置
        Subject subject = SecurityUtils.getSubject();
        AccountModel model2 = (AccountModel) subject.getPrincipal();
        fileName = model2.getName() + fileName + GetUuid.getUUID() + ".xls";
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(filePath + fileName);
            wb.write(fout);
            return new ResponseResult<>(true, "成功", fileName);
        } catch (Exception e) {
            return new ResponseResult<>(false, "输出错误");
        } finally {
            if (fout != null)
                fout.close();
        }
    }
//
//    public static String excels4(List<String[]> list, String na) throws Exception {
//        if (list != null && list.size() > 0) {
//            // 第一步，创建一个webbook，对应一个Excel文件
//            HSSFWorkbook wb = new HSSFWorkbook();
//            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//            HSSFSheet sheet = wb.createSheet("na");
//            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//            HSSFRow row = sheet.createRow((int) 0);
//            // 第四步，创建单元格，并设置值表头 设置表头居中
//            HSSFCellStyle style = wb.createCellStyle();
////            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//
//            HSSFCell cell = row.createCell((short) 0);
//            cell.setCellValue("序号");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 1);
//            cell.setCellValue("机组名称");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 2);
//            cell.setCellValue("所属分公司");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 3);
//            cell.setCellValue("建筑类型");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 4);
//            cell.setCellValue("通讯状态");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 5);
//            cell.setCellValue("一网供水温度/℃");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 6);
//            cell.setCellValue("一网回水温度/℃");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 7);
//            cell.setCellValue("一网供水压力/Mpa");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 8);
//            cell.setCellValue("一网回水压力/Mpa");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 9);
//            cell.setCellValue("二网供水温度/℃");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 10);
//            cell.setCellValue("二网回水温度/℃");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 11);
//            cell.setCellValue("二网供水压力/Mpa");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 12);
//            cell.setCellValue("二网回水压力/Mpa");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 13);
//            cell.setCellValue("1#循环泵频率反馈/Hz");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 14);
//            cell.setCellValue("1#循环泵电流反馈/Hz");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 15);
//            cell.setCellValue("2#循环泵频率反馈/Hz");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 16);
//            cell.setCellValue("2#循环泵电流反馈/Hz");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 17);
//            cell.setCellValue("1#补水泵频率反馈/Hz");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 18);
//            cell.setCellValue("采集时间");
//            cell.setCellStyle(style);
//
//            // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
////        List list = CreateSimpleExcelToDisk.getStudent();
//
//            for (int i = 0; i < list.size(); i++) {
//                row = sheet.createRow((int) i + 1);
//                // 第四步，创建单元格，并设置值
//                row.createCell((short) 0).setCellValue(i + 1);
//                row.createCell((short) 1).setCellValue(list.get(i)[1].toString());
//                row.createCell((short) 2).setCellValue(list.get(i)[2].toString());
//                row.createCell((short) 3).setCellValue(list.get(i)[3].toString());
//                row.createCell((short) 4).setCellValue(list.get(i)[4].toString());
//                row.createCell((short) 5).setCellValue(list.get(i)[5].toString());
//                row.createCell((short) 6).setCellValue(list.get(i)[6].toString());
//                row.createCell((short) 7).setCellValue(list.get(i)[7].toString());
//                row.createCell((short) 8).setCellValue(list.get(i)[8].toString());
//                row.createCell((short) 9).setCellValue(list.get(i)[9].toString());
//                row.createCell((short) 10).setCellValue(list.get(i)[10].toString());
//                row.createCell((short) 11).setCellValue(list.get(i)[11].toString());
//                row.createCell((short) 12).setCellValue(list.get(i)[12].toString());
//                row.createCell((short) 13).setCellValue(list.get(i)[13].toString());
//                row.createCell((short) 14).setCellValue(list.get(i)[14].toString());
//                row.createCell((short) 15).setCellValue(list.get(i)[15].toString());
//                row.createCell((short) 16).setCellValue(list.get(i)[16].toString());
//                row.createCell((short) 17).setCellValue(list.get(i)[17].toString());
//                row.createCell((short) 18).setCellValue(list.get(i)[18].toString());
//            }
//
//            Double d1 = 0.0;
//            Double d2 = 0.0;
//            Double d3 = 0.0;
//            Double d4 = 0.0;
//            Double d5 = 0.0;
//            Double d6 = 0.0;
//            Double d7 = 0.0;
//            Double d8 = 0.0;
//            Double d9 = 0.0;
//            Double d10 = 0.0;
//            Double d11 = 0.0;
//            Double d12 = 0.0;
//            Double d13 = 0.0;
//
////            for (int i = 0; i < list.size(); i++) {
////                d1 += Double.valueOf(list.get(i)[5].toString());
////                d2 += Double.valueOf(list.get(i)[6].toString());
////                d3 += Double.valueOf(list.get(i)[7].toString());
////                d4 += Double.valueOf(list.get(i)[8].toString());
////                d5 += Double.valueOf(list.get(i)[9].toString());
////                d6 += Double.valueOf(list.get(i)[10].toString());
////                d7 += Double.valueOf(list.get(i)[11].toString());
////                d9 += Double.valueOf(list.get(i)[12].toString());
////                d10 += Double.valueOf(list.get(i)[13].toString());
////                d11 += Double.valueOf(list.get(i)[14].toString());
////                d12 += Double.valueOf(list.get(i)[15].toString());
////                d13 += Double.valueOf(list.get(i)[16].toString());
////            }
////
////            d1 = d1 < 0.01 ? 0 : d1 / list.size();
////            d2 = d2 < 0.01 ? 0 : d2 / list.size();
////            d3 = d3 < 0.01 ? 0 : d3 / list.size();
////            d4 = d4 < 0.01 ? 0 : d4 / list.size();
////            d5 = d5 < 0.01 ? 0 : d5 / list.size();
////            d6 = d6 < 0.01 ? 0 : d6 / list.size();
////            d7 = d7 < 0.01 ? 0 : d7 / list.size();
////            d8 = d8 < 0.01 ? 0 : d8 / list.size();
////            d9 = d9 < 0.01 ? 0 : d9 / list.size();
////            d10 = d10 < 0.01 ? 0 : d10 / list.size();
////            d11 = d11 < 0.01 ? 0 : d11 / list.size();
////            d12 = d12 < 0.01 ? 0 : d12 / list.size();
////            d13 = d13 < 0.01 ? 0 : d13 / list.size();
////
////            row = sheet.createRow((int) list.size() + 1);
////            // 第四步，创建单元格，并设置值
////            row.createCell((short) 0);
////            row.createCell((short) 1);
////            row.createCell((short) 2);
////            row.createCell((short) 3);
////            row.createCell((short) 4);
////            row.createCell((short) 5).setCellValue(d1);
////            row.createCell((short) 6).setCellValue(d2);
////            row.createCell((short) 7).setCellValue(d3);
////            row.createCell((short) 8).setCellValue(d4);
////            row.createCell((short) 9).setCellValue(d5);
////            row.createCell((short) 10).setCellValue(d6);
////            row.createCell((short) 11).setCellValue(d7);
////            row.createCell((short) 12).setCellValue(d8);
////            row.createCell((short) 13).setCellValue(d9);
////            row.createCell((short) 14).setCellValue(d10);
////            row.createCell((short) 15).setCellValue(d11);
////            row.createCell((short) 16).setCellValue(d12);
////            row.createCell((short) 17).setCellValue(d13);
////            row.createCell((short) 18);
//
//
//            // 第六步，将文件存到指定位置
//            String fileName = na + GetUuid.getUUID() + ".xls";
//            FileOutputStream fout = null;
//            try {
//                fout = new FileOutputStream(fileName);
//                wb.write(fout);
//                return fileName;
//            }catch (Exception e){
//                return null;
//            }finally {
//                if (fout != null)
//                    fout.close();
//            }
//        }
//        return null;
//    }
//
//    public static String excels2(String names, List<HrzrlFxModel> list) throws Exception {
//        if (list != null && list.size() > 0) {
//            // 第一步，创建一个webbook，对应一个Excel文件
//            HSSFWorkbook wb = new HSSFWorkbook();
//            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//            HSSFSheet sheet = wb.createSheet(names);
//            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//            HSSFRow row = sheet.createRow((int) 0);
//            // 第四步，创建单元格，并设置值表头 设置表头居中
//            HSSFCellStyle style = wb.createCellStyle();
////            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//
//            HSSFCell cell = row.createCell((short) 0);
//            cell.setCellValue("序号");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 1);
//            cell.setCellValue("换热站名称");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 2);
//            cell.setCellValue("换热站面积");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 3);
//            cell.setCellValue("换热站热单耗");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 4);
//            cell.setCellValue("换热站热指标");
//            cell.setCellStyle(style);
//
//            // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
////        List list = CreateSimpleExcelToDisk.getStudent();
//
//            for (int i = 0; i < list.size(); i++) {
//                row = sheet.createRow((int) i + 1);
//                // 第四步，创建单元格，并设置值
//                row.createCell((short) 0).setCellValue(i + 1);
//                row.createCell((short) 1).setCellValue(list.get(i).getHrzmc());
//                row.createCell((short) 2).setCellValue(list.get(i).getHrzmj());
//                row.createCell((short) 3).setCellValue(list.get(i).getHrzrdh());
//                row.createCell((short) 4).setCellValue(list.get(i).getHrzrzb());
//            }
//
//            // 第六步，将文件存到指定位置
//            String fileName = names + "站点热.xls";
//            FileOutputStream fout = null;
//            try {
//                fout = new FileOutputStream(fileName);
//                wb.write(fout);
//                return fileName;
//            }catch (Exception e){
//                return null;
//            } finally {
//                if (fout != null)
//                    fout.close();
//            }
//        }
//        return null;
//    }
//
//    public static String excels3(String names, List<HrzrlFxModel> list) throws Exception {
//        if (list != null && list.size() > 0) {
//            // 第一步，创建一个webbook，对应一个Excel文件
//            HSSFWorkbook wb = new HSSFWorkbook();
//            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//            HSSFSheet sheet = wb.createSheet(names);
//            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//            HSSFRow row = sheet.createRow((int) 0);
//            // 第四步，创建单元格，并设置值表头 设置表头居中
//            HSSFCellStyle style = wb.createCellStyle();
////            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//
//            HSSFCell cell = row.createCell((short) 0);
//            cell.setCellValue("序号");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 1);
//            cell.setCellValue("机组名称");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 2);
//            cell.setCellValue("建筑物面积");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 3);
//            cell.setCellValue("一网累计热量");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 4);
//            cell.setCellValue("机组热单耗");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 5);
//            cell.setCellValue("机组热指标");
//            cell.setCellStyle(style);
//            cell = row.createCell((short) 6);
//            cell.setCellValue("机组热指标");
//            cell.setCellStyle(style);
//            // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
////        List list = CreateSimpleExcelToDisk.getStudent();
//
//            for (int i = 0; i < list.size(); i++) {
//                row = sheet.createRow((int) i + 1);
//                // 第四步，创建单元格，并设置值
//                row.createCell((short) 0).setCellValue(i + 1);
//                row.createCell((short) 1).setCellValue(list.get(i).getHrzmc());
//                row.createCell((short) 2).setCellValue(list.get(i).getHrzmj());
//                row.createCell((short) 3).setCellValue(list.get(i).getYwljrl());
//                row.createCell((short) 4).setCellValue(list.get(i).getHrzrdh());
//                row.createCell((short) 5).setCellValue(list.get(i).getHrzrzb());
//            }
//
//            // 第六步，将文件存到指定位置
//            String fileName = names + "机组热.xls";
//            FileOutputStream fout = null;
//            try {
//                fout = new FileOutputStream(fileName);
//                wb.write(fout);
//                return fileName;
//            }catch (Exception e){
//                return null;
//            } finally {
//                if (fout != null)
//                    fout.close();
//            }
//        }
//        return null;
//    }
/***********************************************************************************************************************/
/**
 * 以下方法使用范围，标准的table格式，可带平均值
 */
//    /**
//     * @param name     String 文件名称
//     * @param title    String 表格头部信息
//     * @param data     String 表格数据
//     * @param lastData String 底部平均值，也可以在确保相同格式的情况下传入其它值
//     * @param response HttpServletResponse
//     * @throws Exception
//     */
//    public static String strToJs(String name, String title, String data, String lastData, HttpServletResponse response) throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("name", name);
//        JSONArray jsonArray = new JSONArray();
//
////        标题
//        String[] titles = title.split(",");
//        int i = 0;
//        JSONObject js = new JSONObject();
//        do {
//            js.put(i + "", titles[i]);
//            i++;
//        } while (i < titles.length);
//        jsonArray.put(0, js);
//
////        数据
//        String[] datas = data.split("]");
//        for (int j = 0; j < datas.length; j++) {
//            int i2 = 0;
//            JSONObject js2 = new JSONObject();
//            String[] s = datas[j].split(",");
//            do {
//                js2.put(i2 + "", s[i2]);
//                i2++;
//            } while (i2 < s.length);
//            jsonArray.put(j + 1, js2);
//        }
//
//        //        数据
//        JSONObject js3 = new JSONObject();
//        if (lastData != null && !lastData.isEmpty()) {
//            String[] ldatas = lastData.split(",");
//            int i3 = 0;
//            do {
//                js3.put(i3 + "", ldatas[i3]);
//                i3++;
//            } while (i3 < ldatas.length);
//
//        }
//
//        jsonObject.put("data", jsonArray);
////生成excel
//        return excels_ajax(jsonObject, js3);
//    }


//    /**
//     * @param json JSONObject 除最底部的平均值以外的所有数据
//     * @param js3  JSONObject 平均值数据
//     * @return String 生成的excel的文件名称
//     * @throws Exception
//     */
//    public static String excels_ajax(JSONObject json, JSONObject js3) throws Exception {
//        if (json != null) {
//            // 第一步，创建一个webbook，对应一个Excel文件
//            HSSFWorkbook wb = new HSSFWorkbook();
//            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//            HSSFSheet sheet = wb.createSheet("sheet1");
//            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//            HSSFRow row = sheet.createRow(0);
//            // 第四步，创建单元格，并设置值表头 设置表头居中
//            HSSFCellStyle style = wb.createCellStyle();
//
//            HSSFCell cell = row.createCell((short) 0);
//            cell.setCellValue("序号");
//            cell.setCellStyle(style);
//
//            JSONArray jsonArray = json.getJSONArray("data");
//            JSONObject jsonObject = jsonArray.getJSONObject(0);
//            int i = 0;
//            do {
//                String s = jsonObject.getString(i + "");
//                i++;
//                cell = row.createCell((short) i);
//                cell.setCellValue(s);
//                cell.setCellStyle(style);
//            } while (jsonObject.has(i + ""));
//
////            不带平均值
//            for (int j = 1; j < jsonArray.length(); j++) {
//                HSSFRow row2 = sheet.createRow(j);
//                int i2 = 0;
//                if (jsonArray.length() >= 2) {
//                    do {
//                        if (i2 == 0) {
//                            row2.createCell((short) i2).setCellValue(j);
//                        } else {
//                            String s = jsonArray.getJSONObject(j).getString(i2 - 1 + "");
//                            HSSFCell cell2 = row2.createCell((short) i2);
//                            cell2.setCellValue(s.contains("[") ? "" : s);
//                            cell2.setCellStyle(style);
//                        }
//                        i2++;
//                    } while (jsonArray.getJSONObject(j).has((i2 - 1) + ""));
//                }
//            }
//
////            平均值
//            HSSFRow row2 = sheet.createRow(jsonArray.length());
//            int i2 = 0;
//            do {
//                if (i2 == 0) {
//                    row2.createCell((short) i2).setCellValue("平均值");
//                } else {
//                    String s = js3.getString(i2 - 1 + "");
//                    HSSFCell cell2 = row2.createCell((short) i2);
//                    cell2.setCellValue(s.contains("[") ? "" : s);
//                    cell2.setCellStyle(style);
//                }
//                i2++;
//            } while (js3.has((i2 - 1) + ""));
//
//            // 第六步，将文件存到指定位置
//            String fileName = json.getString("name") + ".xls";
//            try {
//                FileOutputStream fout = new FileOutputStream(fileName);
//                wb.write(fout);
//                fout.close();
//                logger.info("[" + fileName + "]---文件导出完成");
//                return fileName;
//            } catch (Exception e) {
//                logger.info("[" + fileName + "]---文件导出失败", e);
//                return null;
//            }
//        }
//        return null;
//    }
}
