package com.boasaude.conveniados.mapper;

import com.boasaude.conveniados.response.AutorizacaoExameResponse;

public class AutorizacaoExameResponseMapper {

    public static AutorizacaoExameResponse builderAutorizacaoExameResponse(Long codigoProcedimento, String situacao, String mensagem) {

        return AutorizacaoExameResponse
                .builder()
                .codigoProcedimento(codigoProcedimento)
                .situacao(situacao)
                .mensagem(mensagem)
                .build();
    }
}
