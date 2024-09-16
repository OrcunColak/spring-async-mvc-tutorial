package com.colak.springtutorial.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CallableController {

    // http://localhost:8080/ruleTheWorldAsync
    // Callable will be executed by Spring MVC task executor
    @GetMapping(value = "/ruleTheWorldAsync")
    public Callable<String> rule() {
        // nio-8080-exec-5 : rule Start
        // nio-8080-exec-5 : rule End
        // MvcAsync1 : ruleTheWorldAsync Callable running
        log.info("ruleTheWorldAsync Start");
        Callable<String> callable = () -> {
            log.info("ruleTheWorldAsync Callable running ");
            return "Ruling...";
        };
        log.info("ruleTheWorldAsync End");
        return callable;
    }
}
