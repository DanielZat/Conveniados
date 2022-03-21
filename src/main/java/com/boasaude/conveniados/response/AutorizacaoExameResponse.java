package com.boasaude.conveniados.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AutorizacaoExameResponse {

    private Long codigoProcedimento;

    private String situacao;
}
