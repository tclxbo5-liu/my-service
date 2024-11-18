package com.elong.hotel.hotelmy.utils.io.excel;

import com.alibaba.fastjson.JSON;
import com.elong.hotel.hotelmy.entity.Product;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelFileUtils {
    public static final String filePathXls="/Users/bobo/Downloads/150481/selecthotelidrateplanidfromrateplanpropertyvaluewherepropertyid46groupbyrateplanid_1.xls";
    public static final String filePath="/Users/bobo/Downloads/150481/selecthotelidrateplanidfromrateplanpropertyvaluewherepropertyid46groupbyrateplanid_";
    public static final String filePath2="/Users/bobo/Downloads/second/pro_";

    public static final String filePathXlsx="/Users/bobo/Downloads/insentitiveWords.xlsx";

    public static void readXlsx(String filePath){
        HashMap<String, Product> map1=new HashMap<>();
        int count =0;
        for (int j = 1; j <6; j++) {
            String filePathVar = filePath + j + ".xlsx";
            System.out.println("reading:"+filePathVar);
            try (InputStream inputStream = new FileInputStream(filePathVar);
                 Workbook Workbook = new XSSFWorkbook(inputStream)) {
                Sheet sheet = Workbook.getSheetAt(0);
                for (Row cells : sheet) {
                    count++;
                    Product product = new Product();
                    for (Cell cell : cells) {
                        if (cell.getColumnIndex() == 0) {
                            product.setHotelId(cell.getStringCellValue());
                        }
                        if (cell.getColumnIndex()==1){
                            product.setRatePalnId(cell.getStringCellValue());
                        }
                    }
                    if ("30812612".equals(product.getRatePalnId())||"31128012".equals(product.getRatePalnId())){
                        System.out.println(product);
                    }
                    map1.put(product.getRatePalnId(),product);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("read done:"+filePathVar);
        }

        HashSet<String> set=new HashSet<>();
        int count2 =0;
        for (int j = 1; j <17; j++) {
            String filePathVar = filePath2 + j + ".csv";
            System.out.println("reading:"+filePathVar);
            try (CSVReader reader = new CSVReader(new FileReader(filePathVar))) {
                List<String[]> records = reader.readAll();
                for (String[] record : records) {
                    Product product = new Product();
                    product.setHotelId(record[0]);
                    product.setRatePalnId(record[1]);
                    set.add(product.getRatePalnId());
                    if ("30812612".equals(product.getRatePalnId())||"31128012".equals(product.getRatePalnId())){
                        System.out.println(product);
                    }
                    count2++;
                }
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
            System.out.println("read done:"+filePathVar);
        }
        System.out.println("count2:" + count2);
        System.out.println("map1.size:" + map1.size());
        System.out.println("set.size:" + set.size());

        Workbook workbook = new XSSFWorkbook();
        // 创建一个新的工作表
        Sheet sheet = workbook.createSheet("Sheet1");

        // 创建行和单元格并插入数据
        Row headerRow = sheet.createRow(0); // 创建标题行
        headerRow.createCell(0).setCellValue("hotelId");
        headerRow.createCell(1).setCellValue("rpId");

        int i=1;
        for (Map.Entry<String, Product> stringProductEntry : map1.entrySet()) {
            String rpId = stringProductEntry.getKey();
            if (set.contains(rpId)){
                Product value = stringProductEntry.getValue();
                Row dataRow = sheet.createRow(i); // 创建数据行
                dataRow.createCell(0).setCellValue(value.getHotelId());
                dataRow.createCell(1).setCellValue(value.getRatePalnId());
                i++;
            }
        }

        // 将工作簿写入文件
        try (FileOutputStream fileOut = new FileOutputStream("/Users/bobo/Downloads/result.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭工作簿
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Excel 文件已创建并写入数据！");
    }

    public static  void readXlsxUtil(String filePathXlsx) {

        Set<String> hashSet =new HashSet<>();
        List<InsentitiveWord> insentitiveWords=new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(filePathXlsx);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {


            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 遍历行
            for (Row row : sheet) {
                Cell word = row.getCell(0);
                Cell reason = row.getCell(1);
                if (!hashSet.contains(word.toString())){
                    hashSet.add(word.toString());
                }else {
                    continue;
                }
                InsentitiveWord insentitiveWord = new InsentitiveWord();
                insentitiveWord.setWord(word.toString());
                insentitiveWord.setReason(reason.toString());
                insentitiveWords.add(insentitiveWord);
            }
            System.out.println(JSON.toJSONString(insentitiveWords));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void readXls(String filePathXls){
        int count =0;
        for (int j = 1; j <6; j++) {
            String filePathVar = filePath+j+".xls";
            try (InputStream inputStream = new FileInputStream(filePathVar);
                 Workbook workbook = new HSSFWorkbook(inputStream)) {
                // 获取第一个工作表
                Sheet sheet = workbook.getSheetAt(0);
                // 遍历行

                for (Row row : sheet) {
                    // 遍历单元格
                    count++;
                    for (Cell cell : row) {
                        // 获取单元格的值
                        switch (cell.getCellType()) {
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "\t");
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    System.out.print(cell.getDateCellValue() + "\t");
                                } else {
                                    System.out.print(cell.getNumericCellValue() + "\t");
                                }
                                break;
                            case BOOLEAN:
                                System.out.print(cell.getBooleanCellValue() + "\t");
                                break;
                            case FORMULA:
                                System.out.print(cell.getCellFormula() + "\t");
                                break;
                            default:
                                System.out.print("UNKNOWN\t");
                                break;
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(count);
    }

    @Data
    static  class InsentitiveWord{
        String word;
        String reason;
    }

    public static void main(String[] args) {
//        readXls(filePathXlsx);
        readXlsxUtil(filePathXlsx);
//        readXlsx(filePath);
//        Long.valueOf("");
    }
}
