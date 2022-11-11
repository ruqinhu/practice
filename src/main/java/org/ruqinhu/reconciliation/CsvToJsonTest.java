package org.ruqinhu.reconciliation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class CsvToJsonTest {


//    public static JSONArray jiesuandb() throws IOException {
//
//        File f1 = new File("C:\\Users\\runze\\Desktop\\aaa");
//        File[] files = f1.listFiles();
//
//        JSONArray jsonArray = new JSONArray();
//        for (File file : files) {
//            String content= FileUtils.readFileToString(file,"UTF-8");
//            JSONObject jsonObject= JSON.parseObject(content);
//            jsonArray.add(jsonObject.getJSONArray("data"));
//
//        }
//        return jsonArray;
//    }


    public static JSONArray jiesuandb() throws IOException {

        File f1 = new File("C:\\Users\\runze\\Desktop\\aaa");
        File[] files = f1.listFiles();

        JSONArray jsonArray = new JSONArray();
        for (File file : files) {
            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            CsvMapper csvMapper = new CsvMapper();

            // Read data from CSV file
            List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(file).readAll();
            JSONArray objJson = new JSONArray();
            objJson = JSONArray.parseArray(objJson.toJSONString(readAll));
            jsonArray.fluentAddAll(objJson);

        }
        return jsonArray;
    }


    public static JSONArray ftp() throws IOException {

        //使用Java IO流操作打开/导出文件
        File f1 = new File("C:\\Users\\runze\\Desktop\\sss");
        File[] files = f1.listFiles();

        JSONArray jsonArray = new JSONArray();

        List<LinkedHashMap<Object, Object>> list = new ArrayList<>();

        for (File file : files) {
            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            CsvMapper csvMapper = new CsvMapper();

            // Read data from CSV file
            List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(file).readAll();
            JSONArray objJson = new JSONArray();
            objJson = JSONArray.parseArray(objJson.toJSONString(readAll));
            jsonArray.fluentAddAll(objJson);

        }

        return jsonArray;

    }


//    public static Map<String, BigDecimal> jiesuan2Map (JSONArray jsonArray) {
//        Map<String, BigDecimal> map = new HashMap<>();
//        for (Object o : jsonArray) {
//            JSONObject jsonObject = (JSONObject) o;
//            map.put(jsonObject.getString("biz_source_order_num"), jsonObject.getBigDecimal("amount"));
//        }
//        return map;
//    }

    public static Map<String, BigDecimal> jiesuan2zhengxiangMap (JSONArray jsonArray) {
        Map<String, BigDecimal> map = new HashMap<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            if (jsonObject.getBigDecimal("amount").compareTo(BigDecimal.ZERO) > 0){
                map.put(jsonObject.getString("biz_source_order_num"), jsonObject.getBigDecimal("amount"));
            }
        }
        return map;
    }

    public static Map<String, BigDecimal> jiesuan2nixiangMap (JSONArray jsonArray) {
        Map<String, BigDecimal> map = new HashMap<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            if (jsonObject.getBigDecimal("amount").compareTo(BigDecimal.ZERO) < 0) {
                map.put(jsonObject.getString("biz_source_order_num"), jsonObject.getBigDecimal("amount"));
            }
        }
        return map;
    }




   public static void main(String args[]) throws Exception {

       JSONArray jiesuan = jiesuandb();

//       System.out.println(jiesuan);

       Map<String, BigDecimal> orderIdZhengXiangMap = jiesuan2zhengxiangMap(jiesuan);

       Map<String, BigDecimal> orderIdNiXiangMap = jiesuan2nixiangMap(jiesuan);

       JSONArray ftp = ftp();

       BigDecimal ftpzhegnxiang = BigDecimal.ZERO;

       BigDecimal dbzhegnxiang = BigDecimal.ZERO;

       BigDecimal ftpnixiang = BigDecimal.ZERO;

       BigDecimal dbnixiang = BigDecimal.ZERO;

       // 正向
       for (Object o : ftp) {
           JSONObject jsonObject = (JSONObject) o;

           if (jsonObject.getString("结算类型").equals("退款")) {
               continue;
           }

           String ftpOrderId = jsonObject.getString("奈雪订单号");
           BigDecimal ftpMoney = jsonObject.getBigDecimal("实收清算金额（奈雪实收)");

           if (ftpMoney == null) {
               continue;
           }

           BigDecimal ftpBig = ftpMoney.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

           BigDecimal db = orderIdZhengXiangMap.get(ftpOrderId);

//           if (ftpBig.abs().compareTo(db.abs()) != 0) {
//               System.out.println("奈雪订单号：" + ftpOrderId + " ,业务发生时间：" + jsonObject.getDate("订单支付时间").toLocaleString() + " ，ftp金额 " + ftpBig.toPlainString() + "， 结算金额 " + db.toPlainString());
//           }

           if (ftpBig.abs().subtract(db.abs()).abs().compareTo(BigDecimal.valueOf(0.3)) > 0) {
               ftpzhegnxiang = ftpzhegnxiang.add(ftpBig);
               dbzhegnxiang = dbzhegnxiang.add(db);
               System.out.println("奈雪订单号：" + ftpOrderId + " ,业务发生时间：" + jsonObject.getDate("订单支付时间").toLocaleString() + " ，ftp金额 " + ftpBig.toPlainString() + "， 结算金额 " + db.toPlainString());
           }
       }


       System.out.println("----------------------------------  逆向 ------------------------------------");

       // 逆向
       for (Object o : ftp) {
           JSONObject jsonObject = (JSONObject) o;
           String ftpOrderId = jsonObject.getString("奈雪订单号");
           BigDecimal ftpMoney = jsonObject.getBigDecimal("实收清算金额（奈雪实收)");

           if (jsonObject.getString("结算类型").equals("支付")) {
               continue;
           }

           if (ftpMoney == null) {
               continue;
           }

           BigDecimal ftpBig = ftpMoney.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

           BigDecimal db = orderIdNiXiangMap.get(ftpOrderId);

//           if (ftpBig.abs().compareTo(db.abs()) != 0) {
//               System.out.println("奈雪订单号：" + ftpOrderId + " ,业务发生时间：" + jsonObject.getDate("订单支付时间").toLocaleString() + " ，ftp金额 " + ftpBig.toPlainString() + "， 结算金额 " + db.toPlainString());
//           }

           if (ftpBig.abs().subtract(db.abs()).abs().compareTo(BigDecimal.valueOf(0.3)) > 0) {
               ftpnixiang = ftpnixiang.add(ftpBig);
               dbnixiang = dbnixiang.add(db);
               System.out.println("奈雪订单号：" + ftpOrderId + " ,业务发生时间：" + jsonObject.getDate("订单支付时间").toLocaleString() + " ，ftp金额 " + ftpBig.toPlainString() + "， 结算金额 " + db.toPlainString());
           }

       }

       System.out.println("正向差额 " + ftpzhegnxiang.subtract(dbzhegnxiang).toPlainString() + "  ，逆向差额 " + ftpnixiang.subtract(dbnixiang.abs()).toPlainString());

//       System.out.println(ftp);

   }

}