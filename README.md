# Return CompletableFuture from Controller

The original idea is from  
https://howtodoinjava.com/spring-boot/spring-async-controller-responsebodyemitter/

While DeferredResult is used to produce a single result, a ResponseBodyEmitter can be used to send multiple objects
where each object is written with a compatible HttpMessageConverter.
