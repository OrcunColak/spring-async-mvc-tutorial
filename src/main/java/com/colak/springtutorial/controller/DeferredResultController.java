package com.colak.springtutorial.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeferredResultController {

    // http://localhost:8080/deferredRuleTheWorldAsync
    // DeferredResult will be executed by ForkJoinPool
    @GetMapping(value = "/deferredRuleTheWorldAsync")
    public DeferredResult<String> rule() {
        // nio-8080-exec-1 : deferredRuleTheWorldAsync Start
        // nio-8080-exec-1 : deferredRuleTheWorldAsync End
        // onPool-worker-1 : start processing
        // onPool-worker-1 : end processing
        log.info("deferredRuleTheWorldAsync Start");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        ForkJoinPool.commonPool().submit(() -> runSleepAndSetResultInDeferredResult(deferredResult));
        log.info("deferredRuleTheWorldAsync End");
        return deferredResult;
    }

    private void runSleepAndSetResultInDeferredResult(DeferredResult<String> deferredResult) {
        log.info("start processing ");
        deferredResult.setResult("Done sleeping");
        log.info("end processing ");
    }
}
