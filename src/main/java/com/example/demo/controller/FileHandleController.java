package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.example.demo.util.DownloadData;
import com.example.demo.util.EasyExcelHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/fileHandle")
public class FileHandleController {

    @PostMapping("/uploadExcel")
    public void uploadExcel(@RequestParam("excelFile") MultipartFile excel) throws IOException {
        log.info("start upload excel");

        new EasyExcelHandler().uploadRead(excel);
    }

    @GetMapping("/downloadExcel")
    public void downExcel(HttpServletResponse response) throws IOException {
        log.info("start download excel");

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

//        String fileName = "/Users/chenjie/Chen/Work/Dabai/demoWrite.xls";
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        List<DownloadData> list = new ArrayList();
        for (int i = 0; i <= 10; i++) {
            DownloadData data = new DownloadData();
            data.setString("a" + i);
            data.setDate(new Date());
            data.setDoubleNum(0.02);
            list.add(data);
        }
        EasyExcel.write(response.getOutputStream(), DownloadData.class).sheet("测试数据").doWrite(list);
    }
}
