package com.lhf.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: ExcelHeaderInfo
 * @Author: liuhefei
 * @Description: Excel表头
 * @Date: 2019/10/8 14:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ExcelHeaderInfo {
    //标题的首行坐标
    private int firstRow;
    //标题的末行坐标
    private int lastRow;
    //标题的首列坐标
    private int firstCol;
    //标题的末列坐标
    private int lastCol;
    // 标题
    private String title;
}
