package com.sachet.webfluxdemo.service;

import com.sachet.webfluxdemo.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathService {

    public Response getSquare(int number){
        return new Response(number * number);
    }

    public List<Response> multiplicationTable(int number){
        return IntStream
                .rangeClosed(1, 10)
                .peek(i -> SleepUtil.sleepSeconds(1))
                .peek(i -> System.out.println("math-service processing: "+i))
                .mapToObj(i -> new Response(i * number))
                .collect(Collectors.toList());

    }

}
