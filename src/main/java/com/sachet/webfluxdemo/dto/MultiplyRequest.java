package com.sachet.webfluxdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class MultiplyRequest {

    private int first;
    private int second;

}
