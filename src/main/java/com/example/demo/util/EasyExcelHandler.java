package com.example.demo.util;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class EasyExcelHandler {

    public static void simpleRead() {
        String fileName = "/Users/chenjie/Chen/Work/Dabai/demo.xls";
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }

    public void uploadRead(MultipartFile excel) throws IOException {
        log.info("get excel, start upload");
        EasyExcel.read(excel.getInputStream(), DemoData.class, new DemoDataListener()).sheet().doRead();
    }

    public static void simpleWrite() {
        List<DownloadData> list = new ArrayList();
        for (int i = 0; i <= 10; i++) {
            DownloadData data = new DownloadData();
            data.setString("a" + i);
            data.setDate(new Date());
            data.setDoubleNum(0.02);
            list.add(data);
        }
        String fileName = "/Users/chenjie/Chen/Work/Dabai/demoWrite.xls";
        EasyExcel.write(fileName, DownloadData.class).sheet("模版名称").doWrite(list);
    }

    public static void main(String[] args) {
//        simpleRead();
//        simpleWrite();
    }
}
