package com.boasaude.conveniados.entity;

import java.time.LocalDateTime;

public class AssociadoEntity {

    private String id;

    private Boolean ativo;

    private Long numeroCarteira;

    private PlanoSaudeEntity planoSaude;

    private LocalDateTime dataContratacaoPlano;
}
