package com.sachet.webfluxdemo.config;

import com.sachet.webfluxdemo.custom_exception.InvalidNumberException;
import com.sachet.webfluxdemo.dto.MultiplyRequest;
import com.sachet.webfluxdemo.dto.Response;
import com.sachet.webfluxdemo.error_to_return.ApiError;
import com.sachet.webfluxdemo.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@Service
public class RequestHandler {

    private final ReactiveMathService mathService;

    public RequestHandler(ReactiveMathService mathService) {
        this.mathService = mathService;
    }

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
        int number = Integer.parseInt(serverRequest.pathVariable("number"));
        Mono<Response> responseMono = mathService.findSquare(number);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest){
        int number = Integer.parseInt(serverRequest.pathVariable("number"));
        Flux<Response> responseFlux = mathService.multiplicationTable(number);
        return ServerResponse.ok().body(responseFlux, Response.class);
    }

    public Mono<ServerResponse> tableHandlerStream(ServerRequest serverRequest){
        int number = Integer.parseInt(serverRequest.pathVariable("number"));
        Flux<Response> responseFlux = mathService.multiplicationTable(number);
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, Response.class);
    }

    public Mono<ServerResponse> multiplicationHandler(ServerRequest serverRequest){
        Mono<MultiplyRequest> multiplyRequestMono = serverRequest.bodyToMono(MultiplyRequest.class);
        Mono<Response> responseMono = this.mathService.multiplyRequest(multiplyRequestMono);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest){
        int number = Integer.parseInt(serverRequest.pathVariable("number"));

        if (number < 10 || number > 20){
            ApiError apiError = new ApiError("Numbers between 10 to 20 are only allowed!", 400);
            return ServerResponse.badRequest().bodyValue(apiError);
        }

        Mono<Response> responseMono = mathService.findSquare(number);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidationThrowError(ServerRequest serverRequest){
        int number = Integer.parseInt(serverRequest.pathVariable("number"));

        if (number < 10 || number > 20){
            return Mono.error(new InvalidNumberException("Numbers between 10 to 20 are only allowed!"));
        }

        Mono<Response> responseMono = mathService.findSquare(number);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

}

















