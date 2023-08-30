package com.example.springreactiveexceptionalhandler.route;

import com.example.springreactiveexceptionalhandler.handler.BookHandler;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Configuration
public class AppConfig {

    @Autowired
    private BookHandler bookHandler;

    @Bean
    public WebProperties.Resources resources(){
        return new WebProperties.Resources();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("route/books",bookHandler::getAllBooks)
                .GET("route/books/{bookId}",bookHandler::getBookById)
                .build();
    }

}
