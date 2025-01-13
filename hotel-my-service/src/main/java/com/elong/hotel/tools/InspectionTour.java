package com.elong.hotel.tools;

import org.apache.http.client.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 成单错误巡检sql
 * @author bobo
 * @date 2025/01/13 10:26
 **/
public class InspectionTour {

    public static final String tablePrefix = " order_product_log_";
    public static void main(String[] args) {
        String templateStr = "use hotel_product_second ;\n" +
                "\n" +
                "select t1.errorstr as '查询商品库基础维度失败' ,t1.c as 'dateToday',t2.c as 'dateYesterday',t3.c  as 'dateLastWeek' \n" +
                "from (select tt.errorstr,count(1) c  from (\n" +
                "  select id,substring_index(substring_index(str1,'error_',-1),'\",\"middlePriceList',1) errorstr from tableToday \n" +
                "  where base_data_source ='good-db' \n" +
                "  and method_type=1 and (business_system_id!=31 and business_system_id!=32)\n" +
                "  and _timestamp < 'todayStr'\n" +
                ") tt group by tt.errorstr) as t1 \n" +
                "left join \n" +
                "(select tt.errorstr ,count(1) c  from (\n" +
                "  select id,substring_index(substring_index(str1,'error_',-1),'\",\"middlePriceList',1) errorstr from tableYesterday\n" +
                "  where base_data_source ='good-db' \n" +
                "  and method_type=1 and (business_system_id!=31 and business_system_id!=32\n" +
                "  and _timestamp < 'yesterdayStr'\n" +
                "\t\t\t\t\t\t)\n" +
                ") tt group by tt.errorstr) as t2 on t1.errorstr = t2.errorstr\n" +
                "left join \n" +
                "(select tt.errorstr ,count(1) c from (\n" +
                "  select id,substring_index(substring_index(str1,'error_',-1),'\",\"middlePriceList',1) errorstr from tableLastWeek\n" +
                "  where base_data_source ='good-db' \n" +
                "  and method_type=1 and (business_system_id!=31 and business_system_id!=32)\n" +
                "  and _timestamp < 'lastWeekStr'\n" +
                ") tt group by tt.errorstr) as t3 on t1.errorstr = t3.errorstr";
        Date today = new Date();
        Date yesterday  = org.apache.commons.lang3.time.DateUtils.addDays(today,-1);
        Date lastWeek  = org.apache.commons.lang3.time.DateUtils.addDays(today,-7);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        String todayStr = sdf.format(today);
        String yesterdayStr =sdf.format(yesterday);
        String lastWeekStr = sdf.format(lastWeek);


        String dateToday = DateUtils.formatDate(today, "MM-dd");
        String dateYesterday = DateUtils.formatDate(yesterday, "MM-dd");
        String dateLastWeek = DateUtils.formatDate(lastWeek, "MM-dd");

        String tableToday = tablePrefix+ DateUtils.formatDate(today,"yyyyMMdd");
        String tableYesterday = tablePrefix+ DateUtils.formatDate(yesterday,"yyyyMMdd");
        String tableLastWeek = tablePrefix+ DateUtils.formatDate(lastWeek,"yyyyMMdd");

        String replace = templateStr.replace("todayStr", todayStr).replace("yesterdayStr", yesterdayStr).replace("lastWeekStr", lastWeekStr).
                replace("dateToday",dateToday).replace("dateYesterday",dateYesterday).replace("dateLastWeek",dateLastWeek).
                replace("tableToday",tableToday).replace("tableYesterday",tableYesterday).replace("tableLastWeek",tableLastWeek);
        System.out.println(replace);
    }
}
