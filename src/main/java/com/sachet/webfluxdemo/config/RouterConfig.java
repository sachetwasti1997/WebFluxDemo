package com.sachet.webfluxdemo.config;

import com.sachet.webfluxdemo.custom_exception.InvalidNumberException;
import com.sachet.webfluxdemo.error_to_return.ApiError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
public class RouterConfig {

    private final RequestHandler requestHandler;

    public RouterConfig(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> highLevelRouter(){
        return RouterFunctions.route()
                .path("/router", this::serverResponseRouterFunctionFindSquare)
                .build();
    }

    private RouterFunction<ServerResponse> serverResponseRouterFunctionFindSquare(){
        return RouterFunctions
                .route()
                .GET("/square/{number}", requestHandler::squareHandler)
                .GET("/square/{number}/validation", requestHandler::squareHandlerWithValidation)
                .GET("/square/{number}/validation/throw", requestHandler::squareHandlerWithValidationThrowError)
                .GET("/table/{number}", requestHandler::tableHandler)
                .GET("/table/{number}/stream", requestHandler::tableHandlerStream)
                .POST("/multiply", requestHandler::multiplicationHandler)
                .onError(InvalidNumberException.class, invalidNumberExceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> invalidNumberExceptionHandler(){
        return (err, req) -> {
            System.out.println(req.uri());
            InvalidNumberException ex = (InvalidNumberException) err;
            ApiError apiError = new ApiError(ex.getMessage(), 400, req.uri().toString());
            return ServerResponse.badRequest().bodyValue(apiError);
        };
    }

}
















