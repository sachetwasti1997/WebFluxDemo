package com.sachet.webfluxdemo.service;

import com.sachet.webfluxdemo.dto.MultiplyRequest;
import com.sachet.webfluxdemo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Collectors;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int num){
        /**
         * return Mono.fromSupplier(() -> num * num) this builds the pipeline so all the calculations
         * happen in the reactive style
         *                 .map(integer -> new Response(integer));
         */
        return Mono.fromSupplier(() -> num * num)
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input){

        return Flux
                .range(1, 10)
                .delayElements(Duration.ofSeconds(1))
//                .doOnNext(i -> SleepUtil.sleepSeconds(1)) non delay sleep
                .doOnNext(i -> System.out.println("Processing : "+i))
                .map(i -> new Response(i * input));

    }

    public Mono<Response> multiplyRequest(Mono<MultiplyRequest> multiplyRequestMono){
        return multiplyRequestMono
                .map(dto -> dto.getFirst() * dto.getSecond())
                .map(Response::new);
    }

}
