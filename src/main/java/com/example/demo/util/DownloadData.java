package com.example.demo.util;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DownloadData {
    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期考题")
    private Date date;

    @ExcelProperty("数字标题")
    private Double doubleNum;
}
