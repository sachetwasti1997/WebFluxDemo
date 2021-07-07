package com.sachet.webfluxdemo.controller;

import com.sachet.webfluxdemo.dto.Response;
import com.sachet.webfluxdemo.service.MathService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/math")
public class MathController {

    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("/square/{num}")
    public Response getSquare(@PathVariable Integer num){
        return mathService.getSquare(num);
    }

    @GetMapping("/multiplication/{num}")
    public List<Response> getMultiplicationTable(@PathVariable Integer num){
        return mathService.multiplicationTable(num);
    }

}
