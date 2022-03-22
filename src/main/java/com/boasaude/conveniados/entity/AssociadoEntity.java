package com.boasaude.conveniados.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AssociadoEntity {

    private String id;

    private Boolean ativo;

    private Long numeroCarteira;

    private PlanoSaudeEntity planoSaude;

    private LocalDateTime dataContratacaoPlano;
}
