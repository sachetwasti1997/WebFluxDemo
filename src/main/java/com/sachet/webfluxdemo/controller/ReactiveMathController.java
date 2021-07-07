package com.sachet.webfluxdemo.controller;

import com.sachet.webfluxdemo.custom_exception.InvalidNumberException;
import com.sachet.webfluxdemo.dto.MultiplyRequest;
import com.sachet.webfluxdemo.dto.Response;
import com.sachet.webfluxdemo.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive")
public class ReactiveMathController {

    private final ReactiveMathService reactiveMathService;

    public ReactiveMathController(ReactiveMathService reactiveMathService) {
        this.reactiveMathService = reactiveMathService;
    }

    @GetMapping("/square/{number}")
    public Mono<Response> getSquare(@PathVariable Integer number){
        return reactiveMathService.findSquare(number);
    }

    @GetMapping(value = "/table/{number}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> getTable(@PathVariable Integer number){
        return reactiveMathService.multiplicationTable(number);
    }

    @PostMapping("/multiply")
    public Mono<Response> multiplyNumbers(@RequestBody Mono<MultiplyRequest> multiplyRequestMono){
        return reactiveMathService.multiplyRequest(multiplyRequestMono);
    }

    @GetMapping("/square/{number}/error-handler")
    public Mono<Response> squareErrorHandler(@PathVariable Integer number){
        return Mono.just(number)
                .handle((integer, sink) -> {
                    if (integer >= 10 && integer <= 20){
                        sink.next(integer);
                    }
                    else{
                        sink.error(new InvalidNumberException("Please enter number between 10 and 20!"));
                    }
                })
                .cast(Integer.class)
                .flatMap(reactiveMathService::findSquare);

    }

}





















