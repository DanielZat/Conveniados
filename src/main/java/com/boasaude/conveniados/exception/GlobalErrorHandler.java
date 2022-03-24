package com.boasaude.conveniados.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import com.boasaude.conveniados.response.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Configuration
@Order(-1)
@Slf4j
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        var bufferFactory = exchange.getResponse().bufferFactory();
        DataBuffer dataBuffer = null;

        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        if (ex instanceof ResponseStatusException) {
            var cast = (ResponseStatusException) ex;
            exchange.getResponse().setStatusCode(cast.getStatus());
            dataBuffer = prepareResponse(bufferFactory, responseFor(cast.getReason(), cast.getStatus()));
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        }

        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        dataBuffer = prepareResponse(bufferFactory, genericResponse());

        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    private <T> DataBuffer prepareResponse(DataBufferFactory bufferFactory, BaseResponse response) {
        try {
            return bufferFactory.wrap(objectMapper.writeValueAsBytes(response));
        } catch (JsonProcessingException e) {
            return bufferFactory.wrap("".getBytes());
        }
    }

    private BaseResponse responseFor(String message, HttpStatus status) {
        return BaseResponse.builder().message(message).status_code(status.value()).build();
    }

    private BaseResponse genericResponse() {
        return BaseResponse.builder().status_code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Erro desconhecido").build();
    }
}
