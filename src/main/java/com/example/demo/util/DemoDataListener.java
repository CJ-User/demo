package com.example.demo.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoDataListener extends AnalysisEventListener<DemoData> {
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        log.info("get excel data:" + demoData.toString());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("finish handle excel");
    }
}
