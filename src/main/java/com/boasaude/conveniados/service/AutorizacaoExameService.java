package com.boasaude.conveniados.service;

import static com.boasaude.conveniados.mapper.AutorizacaoExameResponseMapper.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.boasaude.conveniados.entity.AssociadoEntity;
import com.boasaude.conveniados.exception.AutorizacaoExameException;
import com.boasaude.conveniados.exception.BadRequestException;
import com.boasaude.conveniados.repository.AssociadoRepository;
import com.boasaude.conveniados.request.AutorizacaoExameRequest;
import com.boasaude.conveniados.response.AutorizacaoExameResponse;

import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class AutorizacaoExameService {

    private final AssociadoRepository associadoRepository;

    public Mono<AutorizacaoExameResponse> autorizarExecucaoExame(AutorizacaoExameRequest autorizacaoExameRequest) {

        return associadoRepository.buscarAssociadoPorCarteira(autorizacaoExameRequest.getNumeroCarteira())
                .switchIfEmpty(Mono.error(new BadRequestException("Nenhum usuário encontrado para esta carteira.")))
                .filter(AssociadoEntity::getAtivo)
                .switchIfEmpty(Mono.error(new AutorizacaoExameException(autorizacaoExameRequest.getCodigoProcedimento(), "Usuário não está ativo.")))
                .filter(verificarCarenciaAssociado())
                .switchIfEmpty(Mono.error(new AutorizacaoExameException(autorizacaoExameRequest.getCodigoProcedimento(),
                        "Usuário está no período de carência e não pode realizar o procedimento.")))
                .filter(verificarProcedimentoInclusoNoPlano(autorizacaoExameRequest))
                .switchIfEmpty(Mono.error(new AutorizacaoExameException(autorizacaoExameRequest.getCodigoProcedimento(),
                        "Procedimento não está incluso no plano.")))
                .map(associadoEntity -> builderAutorizacaoExameResponse(autorizacaoExameRequest
                        .getCodigoProcedimento(), "Autorizado", StringUtils.EMPTY));
    }

    private Predicate<AssociadoEntity> verificarCarenciaAssociado() {
        return associadoEntity -> associadoEntity.getPlanoSaude().getCarencia().equals(0) ||
                !associadoEntity.getPlanoSaude().getCarencia().equals(0) &&
                        associadoEntity.getDataContratacaoPlano().until(LocalDateTime.now(), ChronoUnit.DAYS) > associadoEntity.getPlanoSaude()
                                .getCarencia();
    }

    private Predicate<AssociadoEntity> verificarProcedimentoInclusoNoPlano(AutorizacaoExameRequest autorizacaoExameRequest) {
        return associadoEntity -> associadoEntity.getPlanoSaude()
                .getProcedimentosInclusos()
                .stream()
                .anyMatch(procedimentoEntity -> autorizacaoExameRequest.getCodigoProcedimento()
                        .equals(procedimentoEntity.getCodigoProcedimento()));
    }
}
