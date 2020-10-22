package com.example.demo.util.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DemoData {

    @ExcelIgnore
    private int line;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("学号")
    private String sn;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("身份证号")
    private String id;

    @ExcelProperty("院系")
    private String college;

    @ExcelProperty("年级")
    private String grade;

    @ExcelProperty("班级")
    private String clazz;
}
