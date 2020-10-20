package com.example.demo.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DemoDataListener extends AnalysisEventListener<DemoData> {

    private List correctList = new ArrayList<DemoData>();
    private List errorList = new ArrayList<DemoData>();
    private List existList = new ArrayList<DemoData>();

    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        log.info("get excel data:" + demoData.toString());
        // todo 这个每一条数据解析都会来调用，解析每一条数据，判断数据是否有问题

        if (demoData == null) {
            log.error("demoData == null");
            // todo throw "请确定文件是否为空"
        }
        int line = 1;
        demoData.setLine(line);

        if (demoData.getName() == null) {
            log.error("demoData.getName() == null");
            // todo throw "请确'姓名'列是否标题是否正确"
        } else if (demoData.getName().isEmpty()) {
            errorList.add(demoData);
        } else {
            correctList.add(demoData);
        }

        line++;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("finish handle excel");
        System.out.println(correctList.toArray());
        System.out.println(errorList.toArray());
//        connerList = connerBiz.findBySnList(snList, adminId);


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
