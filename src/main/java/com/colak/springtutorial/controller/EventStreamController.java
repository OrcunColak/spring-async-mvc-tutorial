package com.colak.springtutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class EventStreamController {

    // http://localhost:8080/stream
    @GetMapping("/stream")
    public ResponseBodyEmitter handle() {
        // Create a new ResponseBodyEmitter
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        // Use a separate thread or task to stream data asynchronously
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    // Send each message as a separate event
                    emitter.send("Update " + i + "\n");
                    // Simulate a delay
                    delay();
                }
                // Complete the streaming process
                emitter.complete();
            } catch (IOException exception) {
                emitter.completeWithError(exception);
            }
        });

        executor.shutdown();

        return emitter;
    }

    private void delay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}

