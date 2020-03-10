package com.lhf.springboot.utils;

import com.lhf.springboot.constant.ExcelFormat;
import com.lhf.springboot.entity.ExcelHeaderInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * @ClassName: ExcelUtils
 * @Author: liuhefei
 * @Description: 生成excel工具
 * @Date: 2019/10/8 15:24
 */
public class ExcelUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    public static final int ROW_ACCESS_WINDOW_SIZE = 100;
    public static final int SHEET_MAX_ROW = 100000;

    private List list;    //保存待导出的数据
    private List<ExcelHeaderInfo> excelHeaderInfos;   //保存表头信息
    private Map<String, ExcelFormat> formatInfo;  //格式化字段

    public ExcelUtils(List list, List<ExcelHeaderInfo> excelHeaderInfos) {
        this.list = list;
        this.excelHeaderInfos = excelHeaderInfos;
    }

    public ExcelUtils(List list, List<ExcelHeaderInfo> excelHeaderInfos, Map<String, ExcelFormat> formatInfo) {
        this.list = list;
        this.excelHeaderInfos = excelHeaderInfos;
        this.formatInfo = formatInfo;
    }

    public Workbook getWorkbook() {
        Workbook workbook = new SXSSFWorkbook(ROW_ACCESS_WINDOW_SIZE);
        String[][] datas = transformData();
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        CellStyle style = setCellStyle(workbook);
        int pageRowNum = 0;
        Sheet sheet = null;

        long startTime = System.currentTimeMillis();
        LOGGER.info("开始处理excel文件。。。");

        for (int i = 0; i < datas.length; i++) {
            // 如果是每个sheet的首行
            if (i % SHEET_MAX_ROW == 0) {
                // 创建新的sheet
                sheet = createSheet(workbook, i);
                pageRowNum = 0; // 行号重置为0
                for (int j = 0; j < getHeaderRowNum(excelHeaderInfos); j++) {
                    sheet.createRow(pageRowNum++);
                }
                createHeader(sheet, style);
            }
            // 创建内容
            Row row = sheet.createRow(pageRowNum++);
            createContent(row, style, datas, i, fields);
        }
        LOGGER.info("处理文本耗时" + (System.currentTimeMillis() - startTime) + "ms");
        return workbook;
    }


    /**
     * 创建表头
     * 该方法用来初始化表头，而创建表头最关键的就是poi中Sheet类的addMergedRegion(CellRangeAddress var1)方法，该方法用于单元格融合。
     * 我们会遍历ExcelHeaderInfo列表，按照每个ExcelHeaderInfo的坐标信息进行单元格融合，然后在融合之后的每个单元首行和首列的位置创建单元格，
     * 然后为单元格赋值即可，通过上面的步骤就完成了任意类型的表头设置。
     * @param sheet
     * @param style
     */
    private void createHeader(Sheet sheet, CellStyle style) {
        for (ExcelHeaderInfo excelHeaderInfo : excelHeaderInfos) {
            Integer lastRow = excelHeaderInfo.getLastRow();
            Integer firstRow = excelHeaderInfo.getFirstRow();
            Integer lastCol = excelHeaderInfo.getLastCol();
            Integer firstCol = excelHeaderInfo.getFirstCol();

            // 行距或者列距大于0才进行单元格融合
            if ((lastRow - firstRow) != 0 || (lastCol - firstCol) != 0) {
                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
            }
            // 获取当前表头的首行位置
            Row row = sheet.getRow(firstRow);
            // 在表头的首行与首列位置创建一个新的单元格
            Cell cell = row.createCell(firstCol);
            // 赋值单元格
            cell.setCellValue(excelHeaderInfo.getTitle());
            cell.setCellStyle(style);
            sheet.setColumnWidth(firstCol, sheet.getColumnWidth(firstCol) * 17 / 12);
        }
    }

    /**
     * 创建正文
     * 这里的正文指定是正式的表格数据内容，其实这一些没有太多的奇淫技巧，主要的功能在上面已经实现了，这里主要是进行单元格的赋值与导出格式的处理（主要是为了导出excel后可以进行方便的运算）
     *
     * @param row
     * @param style
     * @param content
     * @param i
     * @param fields
     */
    private void createContent(Row row, CellStyle style, String[][] content, int i, Field[] fields) {
        List<String> columnNames = getBeanProperty(fields);
        for (int j = 0; j < columnNames.size(); j++) {
            // 如果格式化Map为空，默认为字符串格式
            if (formatInfo == null) {
                row.createCell(j).setCellValue(content[i][j]);
                continue;
            }
            if (formatInfo.containsKey(columnNames.get(j))) {
                switch (formatInfo.get(columnNames.get(j)).getValue()) {
                    case "DOUBLE":
                        row.createCell(j).setCellValue(Double.parseDouble(content[i][j]));
                        break;
                    case "INTEGER":
                        row.createCell(j).setCellValue(Integer.parseInt(content[i][j]));
                        break;
                    case "PERCENT":
                        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
                        Cell cell = row.createCell(j);
                        cell.setCellStyle(style);
                        cell.setCellValue(Double.parseDouble(content[i][j]));
                        break;
                    case "DATE":
                        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd HH:mm:ss"));
                        Cell cell1 = row.createCell(j);
                        cell1.setCellStyle(style);
                        row.createCell(j).setCellValue(this.parseDate(content[i][j]));
                }
            } else {
                row.createCell(j).setCellValue(content[i][j]);
            }
        }
    }

    /**
     * 将原始数据转成二维数组
     * 在进行正文赋值之前，我们先要对原始数据列表转换成字符串的二维数组，之所以转成字符串格式是因为可以统一的处理各种类型，之后有需要我们再转换回来即可。
     * 这个方法中我们通过使用反射技术，很巧妙的实现了任意类型的数据导出（这里的任意类型指的是任意的报表类型，不同的报表，导出的数据肯定是不一样的，那么在Java实现中的实体类肯定也是不一样的）。要想将一个List转换成相应的二维数组，我们得知道如下的信息；
     *
     * 二维数组的列数
     * 二维数组的行数
     * 二维数组每个元素的值
     * @return
     */
    private String[][] transformData() {
        int dataSize = this.list.size();
        String[][] datas = new String[dataSize][];
        // 获取报表的列数
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        // 获取实体类的字段名称数组
        List<String> columnNames = this.getBeanProperty(fields);
        for (int i = 0; i < dataSize; i++) {
            datas[i] = new String[fields.length];
            for (int j = 0; j < fields.length; j++) {
                try {
                    // 赋值
                    datas[i][j] = BeanUtils.getProperty(list.get(i), columnNames.get(j));
                } catch (Exception e) {
                    LOGGER.error("获取对象属性值失败");
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }

    // 获取实体类的字段名称数组
    private List<String> getBeanProperty(Field[] fields) {
        List<String> columnNames = new ArrayList<>();
        for (Field field : fields) {
            String[] strings = field.toString().split("\\.");
            String columnName = strings[strings.length - 1];
            columnNames.add(columnName);
        }
        return columnNames;
    }

    // 新建表格
    private static Sheet createSheet(Workbook workbook, int i) {
        Integer sheetNum = i / SHEET_MAX_ROW;
        workbook.createSheet("sheet" + sheetNum);
        //动态指定当前的工作表
        return workbook.getSheetAt(sheetNum);
    }

    // 获取标题总行数
    private static Integer getHeaderRowNum(List<ExcelHeaderInfo> headerInfos) {
        Integer maxRowNum = 0;
        for (ExcelHeaderInfo excelHeaderInfo : headerInfos) {
            Integer tmpRowNum = excelHeaderInfo.getLastRow();
            if (tmpRowNum > maxRowNum) maxRowNum = tmpRowNum;
        }
        return maxRowNum + 1;
    }

    // 设置总体表格样式
    private static CellStyle setCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        return style;
    }

    // 字符串转日期
    private Date parseDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (Exception e) {
            LOGGER.error("字符串转日期错误");
            e.printStackTrace();
        }
        return date;
    }

    // 发送响应结果
    public void sendHttpResponse(HttpServletResponse response, String fileName, Workbook workbook) {
        try {
            fileName += System.currentTimeMillis() + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
