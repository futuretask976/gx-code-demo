package com.gx.code.utils.office;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelUtils {
    public static void main(String args[]) {
        ExcelUtils.getInstance().parseLiveRefundOssByFstReason();
    }

    public void parseLiveRefundOssByCategory() {
        File xlsFile = new File("/Users/jiaqing.gjq/liverefund.xlsx");
        if (!xlsFile.exists()) {
            System.out.print("file doesnot exist");
            return;
        }

        InputStream is = null;
        try {
            is = new FileInputStream(xlsFile);
            Workbook workbook = this.parseWorkbook(xlsFile.getAbsolutePath(), is);
            List<List<String>> rowList = this.parseSheet(workbook, 0);

            Map<String, Long> cateMap = Maps.newHashMap();
            boolean skipedTitle = false;
            for (List<String> cellList : rowList) {
                if (!skipedTitle) {
                    skipedTitle = true;
                    continue;
                }

                String cate = cellList.get(0);
                if (cateMap.containsKey(cate)) {
                    cateMap.put(cate, cateMap.get(cate).longValue() + Long.valueOf(cellList.get(2)));
                } else {
                    cateMap.put(cate, Long.valueOf(cellList.get(2)));
                }
            }

            List<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>(cateMap.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String,Long>>() {
                public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
            for (Map.Entry<String, Long> e: list) {
                System.out.println(e.getKey() + ":" + e.getValue() + ", " + (df.format(Double.valueOf(Double.valueOf(e.getValue())/Double.valueOf(12094243L)))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(is);
        }
        return;
    }

    public void parseLiveRefundOssByFstReason() {
        File xlsFile = new File("/Users/jiaqing.gjq/liverefund.xlsx");
        if (!xlsFile.exists()) {
            System.out.print("file doesnot exist");
            return;
        }

        InputStream is = null;
        try {
            is = new FileInputStream(xlsFile);
            Workbook workbook = this.parseWorkbook(xlsFile.getAbsolutePath(), is);
            List<List<String>> rowList = this.parseSheet(workbook, 0);

            Map<String, Long> reasonMap = Maps.newHashMap();
            boolean skipedTitle = false;
            for (List<String> cellList : rowList) {
                if (!skipedTitle) {
                    skipedTitle = true;
                    continue;
                }

                String reason = cellList.get(1);
                if (reasonMap.containsKey(reason)) {
                    reasonMap.put(reason, reasonMap.get(reason).longValue() + Long.valueOf(cellList.get(2)));
                } else {
                    reasonMap.put(reason, Long.valueOf(cellList.get(2)));
                }
            }

            List<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>(reasonMap.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String,Long>>() {
                public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
            for (Map.Entry<String, Long> e: list) {
                System.out.println(e.getKey() + ":" + e.getValue() + ", " + (df.format(Double.valueOf(Double.valueOf(e.getValue())/Double.valueOf(12094243L)))));
            }



            Map<String, Long> zhuGuanWenTi = new HashMap<String, Long>() {{
                put("我不想要了", 0L);
                put("7天无理由退换货", 0L);
                put("不喜欢/不想要", 0L);
                put("协商一致退款", 0L);
                put("退货无忧", 0L);
                put("交易风险", 0L);
                put("买卖双方协商一致退款", 0L);
                put("不想要了", 0L);
                put("七天无理由退货", 0L);
                put("拍错/不喜欢", 0L);
                put("多拍/拍错/不想要了", 0L);
                put("七天放心退", 0L);
                put("拍错/不想去了", 0L);
                put("计划有变，无时间消费", 0L);
            }};

            Map<String, Long> xiaDanWenTi = new HashMap<String, Long>() {{
                put("地址/电话信息填写错误", 0L);
                put("没用/少用优惠", 0L);
                put("身份资料上传错误/地址错误", 0L);
                put("身地址填写错误", 0L);
                put("活动/优惠未生效", 0L);
                put("枯萎/死亡", 0L);
                put("已过/临近保质期", 0L);
                put("生产保质期不符", 0L);
                put("功能缺失", 0L);
                put("食物变质", 0L);
                put("充值未到账", 0L);
                put("功能不符", 0L);
                put("奶粉爆罐/扁罐", 0L);
            }};

            Map<String, Long> shangPinWenTi = new HashMap<String, Long>() {{
                put("质量问题", 0L);
                put("大小/尺寸/重量不符", 0L);
                put("空包裹/少货", 0L);
                put("大小尺寸不符", 0L);
                put("商品破损少件等", 0L);
                put("材质面料不符", 0L);
                put("商品信息描述不符", 0L);
                put("做工问题", 0L);
                put("包装/商品破损/污渍", 0L);
                put("少件/漏发", 0L);
                put("商品变质", 0L);
                put("颜色/图案/款式不符", 0L);
                put("空包裹", 0L);
                put("肤质不匹配", 0L);
                put("功能/效果不符", 0L);
                put("参数/规格/品种不符", 0L);
                put("商品无法使用", 0L);
                put("假货问题", 0L);
                put("品种不符", 0L);
                put("使用后过敏", 0L);
                put("安全隐患", 0L);
                put("日期/年份不符", 0L);
                put("开线/走丝", 0L);
                put("缩水/褪色", 0L);
                put("假冒品牌", 0L);
                put("效果问题", 0L);
                put("商品变形", 0L);
                put("宠物活体传染性疾病/死亡", 0L);
                put("配件问题", 0L);
                put("宠物食用不适", 0L);
                put("性能故障", 0L);
                put("卡券无法使用", 0L);
                put("屏幕问题", 0L);
                put("充值少到账", 0L);
                put("CPU问题", 0L);
                put("假冒商品", 0L);
                put("帐号期限/流量不符", 0L);
                put("朋友/网上评价不好", 0L);
                put("开关机问题", 0L);
                put("商品信息与实际信息不符", 0L);
                put("宽带速率不符", 0L);
                put("计划有变，来不及消费", 0L);
                put("信号问题", 0L);
                put("移动电源故障", 0L);
                put("硬盘问题", 0L);
                put("印刷问题", 0L);
                put("香味等与描述不符", 0L);
                put("保质期问题", 0L);
                put("脱胶/印刷问题", 0L);
                put("部件问题", 0L);
                put("褪色/缩水/起球", 0L);
                put("主商品破损", 0L);
                put("软件问题", 0L);
                put("屏幕碎屏/漏液", 0L);
                put("硬件问题", 0L);
                put("商品质量问题", 0L);
                put("噪音问题", 0L);
                put("兼容性问题", 0L);
                put("镜片度数不符", 0L);
                put("三无产品", 0L);
                put("配件破损", 0L);
                put("质量问题", 0L);
                put("安全隐患", 0L);
                put("商品降价了", 0L);
                put("包装/商品破损/污渍", 0L);
                put("货物破损已拒签", 0L);
                put("申请价保退款", 0L);
            }};

            Map<String, Long> wuLiuWenTi = new HashMap<String, Long>() {{
                put("物流一直未送到", 0L);
                put("卖家发错货", 0L);
                put("物流无跟踪记录", 0L);
                put("未收到货", 0L);
                put("未按约定时间送达", 0L);
                put("骑手送错订单", 0L);
                put("骑手提前点确认送达", 0L);
                put("地址不在服务范围", 0L);
                put("包裹丢失", 0L);
                put("未送货上门", 0L);
                put("地域发货限制", 0L);
                put("配送超时", 0L);
                put("未收到商品", 0L);
                put("虚假发货", 0L);
                put("快递问题", 0L);
                put("附近没有可配送的门店", 0L);
                put("配送时间太长", 0L);
                put("商家无法配送，联系我取消", 0L);
                put("发货问题", 0L);
                put("虚假签收", 0L);
            }};

            Map<String, Long> jiaGeWenTi = new HashMap<String, Long>() {{
                put("实际价格与描述不符", 0L);
                put("资费问题", 0L);
                put("店里活动更优惠", 0L);
                put("没用/少用优惠", 0L);
            }};

            Map<String, Long> fuWuWenTi = new HashMap<String, Long>() {{
                put("商家评价不好", 0L);
                put("未履行服务", 0L);
                put("卖家未完成服务", 0L);
                put("未履行送货上门并安装", 0L);
                put("卖家承诺问题", 0L);
                put("安装质量问题", 0L);
                put("卖家服务问题", 0L);
                put("宽带安装时间等待过长", 0L);
                put("商家营业但不接待", 0L);
                put("宽带安装条件限制", 0L);
                put("赠品未收到", 0L);
                put("预约不上", 0L);
                put("无人联系提供服务", 0L);
                put("联系不上商家/实际地址无门店", 0L);
                put("商家无法提供服务", 0L);
                put("服务时间协商不一致", 0L);
                put("未按约定时间提供服务", 0L);
            }};

            Map<String, Long> others = new HashMap<String, Long>() {{
                put("其他", 0L);
                put("退运费", 0L);
                put("未按约定时间发货", 0L);
                put("缺货", 0L);
                put("发票问题", 0L);
                put("商家缺货/打烊，联系我取消", 0L);
                put("商品数量不足", 0L);
                put("不愿意提供身份证信", 0L);
                put("双方协商一致退款", 0L);
                put("退货纠纷", 0L);
                put("商家关店/装修/转让", 0L);
                put("商家无货/缺货", 0L);
                put("退票款", 0L);
                put("商家要求用美团/点评/支付宝/云闪付/现金进行消费", 0L);
            }};

            for (Map.Entry<String, Long> e: list) {
                if (zhuGuanWenTi.containsKey(e.getKey())) {
                    zhuGuanWenTi.put(e.getKey(), zhuGuanWenTi.get(e.getKey()) + e.getValue().longValue());
                } else if (xiaDanWenTi.containsKey(e.getKey())) {
                    xiaDanWenTi.put(e.getKey(), xiaDanWenTi.get(e.getKey()) + e.getValue().longValue());
                } else if (shangPinWenTi.containsKey(e.getKey())) {
                    shangPinWenTi.put(e.getKey(), shangPinWenTi.get(e.getKey()) + e.getValue().longValue());
                } else if (wuLiuWenTi.containsKey(e.getKey())) {
                    wuLiuWenTi.put(e.getKey(), wuLiuWenTi.get(e.getKey()) + e.getValue().longValue());
                } else if (fuWuWenTi.containsKey(e.getKey())) {
                    fuWuWenTi.put(e.getKey(), fuWuWenTi.get(e.getKey()) + e.getValue().longValue());
                } else {
                    if (others.containsKey(e.getKey())) {
                        others.put(e.getKey(), others.get(e.getKey()) + e.getValue().longValue());
                    } else {
                        others.put(e.getKey(), e.getValue().longValue());
                    }
                }
            }

            long zhuGuanWenTiTotal = 0;
            for (Map.Entry<String, Long> entry : zhuGuanWenTi.entrySet()) {
                zhuGuanWenTiTotal += entry.getValue();
            }
            long xiaDanWenTiTotal = 0;
            for (Map.Entry<String, Long> entry : xiaDanWenTi.entrySet()) {
                xiaDanWenTiTotal += entry.getValue();
            }
            long shangPinWenTiTotal = 0;
            for (Map.Entry<String, Long> entry : shangPinWenTi.entrySet()) {
                shangPinWenTiTotal += entry.getValue();
            }
            long wuLiuWenTiTotal = 0;
            for (Map.Entry<String, Long> entry : wuLiuWenTi.entrySet()) {
                wuLiuWenTiTotal += entry.getValue();
            }
            long fuWuWenTiTotal = 0;
            for (Map.Entry<String, Long> entry : fuWuWenTi.entrySet()) {
                fuWuWenTiTotal += entry.getValue();
            }
            long othersTotal = 0;
            for (Map.Entry<String, Long> entry : others.entrySet()) {
                othersTotal += entry.getValue();
            }



            System.out.println("主观问题：" + (df.format(Double.valueOf(Double.valueOf(zhuGuanWenTiTotal)/Double.valueOf(12094243L)))));
            System.out.println("下单问题：" + (df.format(Double.valueOf(Double.valueOf(xiaDanWenTiTotal)/Double.valueOf(12094243L)))));
            System.out.println("商品问题：" + (df.format(Double.valueOf(Double.valueOf(shangPinWenTiTotal)/Double.valueOf(12094243L)))));
            System.out.println("物流问题：" + (df.format(Double.valueOf(Double.valueOf(wuLiuWenTiTotal)/Double.valueOf(12094243L)))));
            System.out.println("服务问题：" + (df.format(Double.valueOf(Double.valueOf(fuWuWenTiTotal)/Double.valueOf(12094243L)))));
            System.out.println("其它问题：" + (df.format(Double.valueOf(Double.valueOf(othersTotal)/Double.valueOf(12094243L)))));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(is);
        }
        return;
    }

    public void parseOss() {
        File xlsFile = new File("/Users/jiaqing.gjq/Downloads/OSS-淘系技术部.xlsx");
        if (!xlsFile.exists()) {
            System.out.print("file doesnot exist");
            return;
        }


        HashMap<String, List<String>> addedColumn = Maps.newHashMap();
        addedColumn.put("新增存储容量", Lists.<String>newArrayList());
        addedColumn.put("月度归档型存储总容量", Lists.<String>newArrayList());
        addedColumn.put("月度归档型新增存储容量", Lists.<String>newArrayList());
        addedColumn.put("平均每小时存储容量", Lists.<String>newArrayList());
        addedColumn.put("月度低频访问存储总容量", Lists.<String>newArrayList());
        addedColumn.put("CDN回源流量", Lists.<String>newArrayList());
        addedColumn.put("外网流出流量", Lists.<String>newArrayList());

        InputStream is = null;
        try {
            is = new FileInputStream(xlsFile);

            Workbook workbook = this.parseWorkbook(xlsFile.getAbsolutePath(), is);
            List<List<String>> rowList = this.parseSheet(workbook, 0);
            boolean skipedTitle = false;
            for (List<String> cellList : rowList) {
                if (!skipedTitle) {
                    skipedTitle = true;
                    continue;
                }

                String a = "0";
                String b = "0";
                String c = "0";
                String d = "0";
                String e = "0";
                String f = "0";
                String g = "0";

                JSONObject json = JSONObject.parseObject(cellList.get(17));
                JSONArray configs = json.getJSONArray("configs");
                for (int i = 0; i < configs.size(); i++) {
                    JSONObject configItem = configs.getJSONObject(i);
                    JSONArray propertyList = configItem.getJSONArray("propertyList");
                    for (int j = 0; j < propertyList.size(); j++) {
                        JSONObject propertyListItem = propertyList.getJSONObject(j);

                        String propertyName = propertyListItem.getString("propertyName");
                        String value = propertyListItem.getString("value");
                        if ("新增存储容量".equals(propertyName)) {
                            a = value;
                        } else if ("月度归档型存储总容量".equals(propertyName)) {
                            b = value;
                        } else if ("月度归档型新增存储容量".equals(propertyName)) {
                            c = value;
                        } else if ("平均每小时存储容量".equals(propertyName)) {
                            d = value;
                        } else if ("月度低频访问存储总容量".equals(propertyName)) {
                            e = value;
                        } else if ("CDN回源流量".equals(propertyName)) {
                            f = value;
                        } else if ("外网流出流量".equals(propertyName)) {
                            g = value;
                        }
                    }
                }

                addedColumn.get("新增存储容量").add(a);
                addedColumn.get("月度归档型存储总容量").add(b);
                addedColumn.get("月度归档型新增存储容量").add(c);
                addedColumn.get("平均每小时存储容量").add(d);
                addedColumn.get("月度低频访问存储总容量").add(e);
                addedColumn.get("CDN回源流量").add(f);
                addedColumn.get("外网流出流量").add(g);
            }

            System.out.println("新增存储容量, 月度归档型存储总容量, 月度归档型新增存储容量, 平均每小时存储容量, 月度低频访问存储总容量, CDN回源流量, 外网流出流量");
            for (int i = 0; i < addedColumn.get("新增存储容量").size(); i++) {
                System.out.println(addedColumn.get("新增存储容量").get(i)
                        + ", " + addedColumn.get("月度归档型存储总容量").get(i)
                        + ", " + addedColumn.get("月度归档型新增存储容量").get(i)
                        + ", " + addedColumn.get("平均每小时存储容量").get(i)
                        + ", " + addedColumn.get("月度低频访问存储总容量").get(i)
                        + ", " + addedColumn.get("CDN回源流量").get(i)
                        + ", " + addedColumn.get("外网流出流量").get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(is);
        }
        return;
    }

    public void parseCdn() {
        File xlsFile = new File("/Users/jiaqing.gjq/Downloads/CDN-淘系技术部.xlsx");
        if (!xlsFile.exists()) {
            System.out.print("file doesnot exist");
            return;
        }

        HashMap<String, List<String>> addedColumn = Maps.newHashMap();
        addedColumn.put("DayPeakBandwidthAvgChina", Lists.<String>newArrayList());
        addedColumn.put("DayPeakBandwidthAvgOverseas", Lists.<String>newArrayList());
        addedColumn.put("PcdnFlowP295", Lists.<String>newArrayList());

        InputStream is = null;
        try {
            is = new FileInputStream(xlsFile);

            Workbook workbook = this.parseWorkbook(xlsFile.getAbsolutePath(), is);
            List<List<String>> rowList = this.parseSheet(workbook, 0);
            boolean skipedTitle = false;
            for (List<String> cellList : rowList) {
                if (!skipedTitle) {
                    skipedTitle = true;
                    continue;
                }

                String addedGuoNeiDaiKuanFengZhiValue = null;
                String addedHaiWaiDaiKuanFengZhiValue = null;
                String addedErJiYueDuDaiKuanValue = null;

                JSONObject json = JSONObject.parseObject(cellList.get(17));
                JSONArray configs = json.getJSONArray("configs");
                for (int i = 0; i < configs.size(); i++) {
                    JSONObject configItem = configs.getJSONObject(i);
                    JSONArray propertyList = configItem.getJSONArray("propertyList");
                    for (int j = 0; j < propertyList.size(); j++) {
                        JSONObject propertyListItem = propertyList.getJSONObject(j);

                        String propertyName = propertyListItem.getString("propertyName");
                        String value = propertyListItem.getString("value");
                        if ("国内带宽峰值".equals(propertyName)) {
                            addedGuoNeiDaiKuanFengZhiValue = value;
                        } else if ("海外带宽峰值".equals(propertyName)) {
                            addedHaiWaiDaiKuanFengZhiValue = value;
                        } else if ("P2P 二级月度带宽".equals(propertyName)) {
                            addedErJiYueDuDaiKuanValue = value;
                        }
                    }
                }

                if (StringUtils.isNotBlank(addedGuoNeiDaiKuanFengZhiValue)) {
                    addedColumn.get("DayPeakBandwidthAvgChina").add(addedGuoNeiDaiKuanFengZhiValue);
                } else {
                    addedColumn.get("DayPeakBandwidthAvgChina").add("0");
                }
                if (StringUtils.isNotBlank(addedHaiWaiDaiKuanFengZhiValue)) {
                    addedColumn.get("DayPeakBandwidthAvgOverseas").add(addedHaiWaiDaiKuanFengZhiValue);
                } else {
                    addedColumn.get("DayPeakBandwidthAvgOverseas").add("0");
                }
                if (StringUtils.isNotBlank(addedErJiYueDuDaiKuanValue)) {
                    addedColumn.get("PcdnFlowP295").add(addedErJiYueDuDaiKuanValue);
                } else {
                    addedColumn.get("PcdnFlowP295").add("0");
                }
            }

            List<String> dayPeakBandwidthAvgChina = addedColumn.get("DayPeakBandwidthAvgChina");
            List<String> DayPeakBandwidthAvgOverseas = addedColumn.get("DayPeakBandwidthAvgOverseas");
            List<String> pcdnFlowP295 = addedColumn.get("PcdnFlowP295");

            System.out.println("DayPeakBandwidthAvgChina, DayPeakBandwidthAvgOverseas, PcdnFlowP295");
            for (int i = 0; i < dayPeakBandwidthAvgChina.size(); i++) {
                System.out.println(dayPeakBandwidthAvgChina.get(i) + ", " + DayPeakBandwidthAvgOverseas.get(i) + ", " + pcdnFlowP295.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(is);
        }
        return;
    }

    public Workbook parseWorkbook(String absolutePath, InputStream is) throws IOException {
        if (StringUtils.isBlank(absolutePath) || is == null) {
            return null;
        }
        Workbook workbook = null;
        if (absolutePath.endsWith(".xls")) {
            workbook = new HSSFWorkbook(is);
        } else if (absolutePath.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    public List<List<String>> parseSheet(Workbook workbook, int sheetIdx) throws IOException {
        if (workbook == null) {
            return null;
        }
        // 获取第一个sheet
        Sheet sheet = workbook.getSheetAt(sheetIdx);

        List<List<String>> rowList = Lists.newArrayList();
        // 获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rownum; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            List<String> cellList = Lists.newArrayList();
            int physicalNumberOfCells = row.getPhysicalNumberOfCells();
            for (int j = 0; j < physicalNumberOfCells; j++) {
                String cellFormatVal = String.valueOf(parseCellFormatValue(row.getCell(j)));
                cellList.add(cellFormatVal);
            }
            rowList.add(cellList);
        }
        return rowList;
    }

    /**
     * 获取单个单元格数据
     *
     * @param cell
     * @return
     */
    public Object parseCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            // 判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = Double.valueOf(cell.getNumericCellValue()).longValue();
                    // cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        // 数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

    public void close(InputStream is) {
        if (is == null) {
            return;
        }
        try {
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ExcelUtils excelUtils;

    public static ExcelUtils getInstance() {
        if (excelUtils == null) {
            excelUtils = new ExcelUtils();
        }
        return excelUtils;
    }
}
