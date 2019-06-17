package com.wxy.common.tool;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author wangxy
 * @version 1.0
 * @ClassName: ExcelUtils
 * @Description: 报表工具类(这里用一句话描述这个类的作用)
 * @date 2019/3/19 15:08
 */
public class ExcelUtils {

    /**
     * 创建 HSSFWorkbook
     *
     * @param sheetName
     * @param title
     * @param values
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,
                                               String[] title, String[][] values) {
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 第二步，在workbook中添加一个sheet，对应Excel文件中的sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行，注意老版本poi对Excel行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置表头 设置表头居中
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

        // 声明列对象
        HSSFCell cell = null;

        // 创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        // 创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                // 将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return workbook;
    }

    /**
     * 导出excel
     *
     * @param fileName
     * @param sheetName
     * @param title
     * @param values
     * @param response
     * @throws Exception
     */
    public static void downloadExcel(String fileName, String sheetName,
                                     String[] title, String[][] values, HttpServletResponse response) throws IOException {
        // 创建 HSSFWorkbook
        HSSFWorkbook workbook = getHSSFWorkbook(sheetName, title, values);
        if (response != null) {// 下载
            // 设置响应头
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        } else {// 写入本地
            FileOutputStream outputStream = new FileOutputStream("/" + fileName);
            workbook.write(outputStream);
            outputStream.close();
        }
    }
}
