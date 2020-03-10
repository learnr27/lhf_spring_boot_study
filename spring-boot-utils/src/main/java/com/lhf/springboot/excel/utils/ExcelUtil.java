package com.lhf.springboot.excel.utils;


import com.lhf.springboot.json.JsonUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


/**
 * @ClassName: ExcelUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/11/22 15:01
 */

public class ExcelUtil {
    public ExcelUtil() {
    }

    public static File exportCSV(String fileName, List<Map> datas) {
        File file = new File(fileName);

        try {
            OutputStream out = new FileOutputStream(file);
            Throwable var4 = null;

            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
                Throwable var6 = null;

                try {
                    if (CollectionUtils.isNotEmpty(datas)) {
                        Map map = (Map)datas.get(0);
                        if (!(map instanceof LinkedHashMap)) {
                            throw new RuntimeException("CSV导出暂时只支持LinkedHashMap作为Map的实现");
                        }

                        Set<String> set = map.keySet();
                        String header = StringUtils.join(set, ",");
                        writer.append(header + '\n');
                        Iterator var10 = datas.iterator();

                        while(var10.hasNext()) {
                            Map data = (Map)var10.next();
                            Collection values = data.values();
                            String items = StringUtil.join(values, ",");
                            writer.append(items + '\n');
                        }
                    }
                } catch (Throwable var39) {
                    var6 = var39;
                    throw var39;
                } finally {
                    if (writer != null) {
                        if (var6 != null) {
                            try {
                                writer.close();
                            } catch (Throwable var38) {
                                var6.addSuppressed(var38);
                            }
                        } else {
                            writer.close();
                        }
                    }

                }
            } catch (Throwable var41) {
                var4 = var41;
                throw var41;
            } finally {
                if (out != null) {
                    if (var4 != null) {
                        try {
                            out.close();
                        } catch (Throwable var37) {
                            var4.addSuppressed(var37);
                        }
                    } else {
                        out.close();
                    }
                }

            }
        } catch (FileNotFoundException var43) {
            var43.printStackTrace();
        } catch (IOException var44) {
            var44.printStackTrace();
        }

        return file;
    }

    public static File exportDAT(String pathname, List<Map> datas) {
        File file = new File(pathname);

        try {
            OutputStream out = new FileOutputStream(file);
            Throwable var4 = null;

            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
                Throwable var6 = null;

                try {
                    if (CollectionUtils.isNotEmpty(datas)) {
                        Map map = (Map)datas.get(0);
                        if (!(map instanceof LinkedHashMap)) {
                            throw new RuntimeException("DAT导出暂时只支持LinkedHashMap作为Map的实现");
                        }

                        Set<String> set = map.keySet();
                        writer.append(JsonUtil.toJson(set) + '\n');
                        Iterator var9 = datas.iterator();

                        while(var9.hasNext()) {
                            Map data = (Map)var9.next();
                            Collection values = data.values();
                            List<String> strings = new ArrayList();
                            Iterator var13 = values.iterator();

                            while(var13.hasNext()) {
                                Object value = var13.next();
                                strings.add(value == null ? "" : value.toString());
                            }

                            writer.append(JsonUtil.toJson(strings) + '\n');
                        }
                    }
                } catch (Throwable var40) {
                    var6 = var40;
                    throw var40;
                } finally {
                    if (writer != null) {
                        if (var6 != null) {
                            try {
                                writer.close();
                            } catch (Throwable var39) {
                                var6.addSuppressed(var39);
                            }
                        } else {
                            writer.close();
                        }
                    }

                }
            } catch (Throwable var42) {
                var4 = var42;
                throw var42;
            } finally {
                if (out != null) {
                    if (var4 != null) {
                        try {
                            out.close();
                        } catch (Throwable var38) {
                            var4.addSuppressed(var38);
                        }
                    } else {
                        out.close();
                    }
                }

            }
        } catch (FileNotFoundException var44) {
            var44.printStackTrace();
        } catch (IOException var45) {
            var45.printStackTrace();
        }

        return file;
    }

    public static ByteArrayOutputStream exportTableDataExcel(List<Map> tableData) throws IOException {
        Workbook wb = new SXSSFWorkbook(100);
        Sheet sheet = wb.createSheet("默认单元");
        sheet.setDefaultColumnWidth(25);
        int colNum = 0;
        int rownum = 0;
        if (CollectionUtils.isNotEmpty(tableData)) {
            int var16 = rownum + 1;
            Row row = sheet.createRow(rownum);
            Map firstMap = (Map)tableData.get(0);
            Set keySet = firstMap.keySet();
            Iterator var8 = keySet.iterator();

            while(var8.hasNext()) {
                Object o = var8.next();
                String s = (String)o;
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(s);
            }

            var8 = tableData.iterator();

            while(var8.hasNext()) {
                Map tableDatum = (Map)var8.next();
                Row nextRow = sheet.createRow(var16++);
                int column = 0;
                Iterator var12 = keySet.iterator();

                while(var12.hasNext()) {
                    Object key = var12.next();
                    String string = MapUtils.getString(tableDatum, key);
                    Cell cell = nextRow.createCell(column++);
                    cell.setCellValue(string);
                }
            }
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wb.write(output);
        output.flush();
        return output;
    }

    public static List<Map> readExcel(InputStream inputStream) throws IOException, InvalidFormatException {
        List<Map> result = new ArrayList();
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        String tableCode = sheet.getSheetName();
        int lastRowNum = sheet.getLastRowNum();
        Row firstRow = sheet.getRow(0);
        short lastCellNum = firstRow.getLastCellNum();

        for(int i = 1; i <= lastRowNum; ++i) {
            Map map = new HashMap();
            Row row = sheet.getRow(i);
            if (row != null) {
                for(short j = 0; j < lastCellNum; ++j) {
                    Cell attrValueCell = row.getCell(j);
                    if (attrValueCell != null) {
                        Cell cell = firstRow.getCell(j);
                        String cellValue = cell.getStringCellValue();
                        if (cellValue.indexOf(40) > -1) {
                            cellValue = cellValue.substring(0, cellValue.indexOf(40));
                        }

                        CellType cellTypeEnum = attrValueCell.getCellTypeEnum();
                        if (cellTypeEnum == CellType.STRING) {
                            map.put(cellValue, attrValueCell.getStringCellValue());
                        } else if (cellTypeEnum == CellType.NUMERIC) {
                            if (DateUtil.isCellDateFormatted(attrValueCell)) {
                                map.put(cellValue, DateFormatUtils.format(attrValueCell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss"));
                            } else {
                                String value = String.valueOf(attrValueCell.getNumericCellValue());
                                if (value.indexOf(".") > -1 && value.indexOf("E") > -1) {
                                    DecimalFormat df = new DecimalFormat("#");
                                    String format = df.format(attrValueCell.getNumericCellValue());
                                    map.put(cellValue, format);
                                } else {
                                    map.put(cellValue, attrValueCell.getNumericCellValue());
                                }
                            }
                        }
                    }
                }

                if (!map.isEmpty()) {
                    result.add(map);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
    }
}
