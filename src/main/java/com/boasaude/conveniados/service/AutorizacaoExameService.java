package com.boasaude.conveniados.service;

import com.boasaude.conveniados.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.boasaude.conveniados.request.AutorizacaoExameRequest;
import com.boasaude.conveniados.response.AutorizacaoExameResponse;

import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class AutorizacaoExameService {

    private final AssociadoRepository associadoRepository;

    public Mono<AutorizacaoExameResponse> autorizarExecucaoExame(AutorizacaoExameRequest autorizacaoExameRequest) {

        return Mono.just(null);
    }
}
