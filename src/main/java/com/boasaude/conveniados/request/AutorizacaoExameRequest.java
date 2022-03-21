package com.boasaude.conveniados.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AutorizacaoExameRequest {

    private Long numeroCarteira;

    private Long codigoProcedimento;
}
