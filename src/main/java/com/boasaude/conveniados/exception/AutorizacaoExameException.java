package com.boasaude.conveniados.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AutorizacaoExameException extends ResponseStatusException {

    private final Long codigoProcedimento;

    private final String situacao;

    public AutorizacaoExameException(Long codigoProcedimento, String reason) {
        super(HttpStatus.BAD_REQUEST, reason);

        this.codigoProcedimento = codigoProcedimento;
        situacao = "Não Autorizado";
    }
}
