package com.boasaude.conveniados.service;

import org.springframework.stereotype.Service;

import com.boasaude.conveniados.request.AutorizacaoExameRequest;
import com.boasaude.conveniados.response.AutorizacaoExameResponse;

import reactor.core.publisher.Mono;

@Service

public class AutorizacaoExameService {

    public Mono<AutorizacaoExameResponse> autorizarExecucaoExame(AutorizacaoExameRequest autorizacaoExameRequest) {

        return Mono.just(null);
    }
}
