package com.example.demo.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DemoDataListener extends AnalysisEventListener<DemoData> {

    private List correctList = new ArrayList<DemoData>();
    private List errorList = new ArrayList<DemoData>();
    private List existList = new ArrayList<DemoData>();
    private List snList = new ArrayList<String>();
    private int line = 1;

    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        log.info("get excel data:" + demoData.toString());
        // todo 这个每一条数据解析都会来调用，解析每一条数据，判断数据是否有问题

        if (demoData == null) {
            log.error("demoData == null");
            // todo throw "请确定文件是否为空"
        }
        demoData.setLine(line);

        if (demoData.getName() == null) {
            log.error("demoData.getName() == null");
            // todo throw "请确'姓名'列是否标题是否正确"
        } else if (demoData.getName().isEmpty()) {
            errorList.add(demoData);
        } else {
            correctList.add(demoData);
        }

        if (demoData.getSn() == null) {
            log.error("demoData.getName() == null");
            // todo throw "请确'姓名'列是否标题是否正确"
        } else if (demoData.getName().isEmpty()) {
            errorList.add(demoData);
        } else {
            correctList.add(demoData);
            snList.add(demoData.getSn().trim());
        }

        line++;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("finish handle excel");
        correctList.forEach(System.out::println);
        errorList.forEach(System.out::println);
        snList.forEach(System.out::println);

        // todo 查询已存在的数据
//        List<Conner> connerList = null;
//        if (!CollectionUtils.isEmpty(snList)) {
//            connerList = connerBiz.findBySnList(snList, adminId);
//        }
//        List<Conner> list = connerList.stream().filter(p -> p.getSn().equals(sn)).collect(Collectors.toList());


    }

    public List getCorrectList() {
        return correctList;
    }

    public List getErrorList() {
        return errorList;
    }

    private void setData(List correctList, List errorList, List existList) {
        this.correctList = correctList;
        this.errorList = errorList;
        this.existList = existList;
    }

}
