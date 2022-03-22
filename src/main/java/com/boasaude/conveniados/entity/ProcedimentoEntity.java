package com.boasaude.conveniados.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProcedimentoEntity {

    private Long codigoProcedimento;

    private String nomeProcedimento;
}
