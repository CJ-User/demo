package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.example.demo.util.DemoData;
import com.example.demo.util.DemoDataListener;
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
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/fileHandle")
public class FileHandleController {

    // todo 文件上传：
    //      1.文件转存 2.文件校验
    @PostMapping("/uploadExcel")
    public void uploadExcel(@RequestParam("excelFile") MultipartFile excel) throws IOException {
        log.info("start upload excel");

        DemoDataListener listener = new DemoDataListener();
        // todo 1. 文件转存
        // todo 文件保存路径
        String filePath = "/Users/chenjie/Chen/Work/Dabai/demo/" + excel.getOriginalFilename();
        excel.transferTo(new File(filePath));

        log.info("uploadExcel to " + filePath);

        // todo 2. 文件校验
        EasyExcel.read(filePath, DemoData.class, listener).sheet().doRead();
        System.out.println(listener.getCorrectList().size());
        System.out.println(listener.getErrorList().size());

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
