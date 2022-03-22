package com.boasaude.conveniados.entity;

import com.boasaude.conveniados.enums.AbrangenciaEnum;

import java.util.List;

public class PlanoSaudeEntity {

    private Long id;

    private String nome;

    private AbrangenciaEnum abrangenciaEnum;

    private Integer carencia;

    private Boolean possuiCoparticipacao;

    private Long valorCoparticipacao;

    private List<ProcedimentoEntity> procedimentosInclusos;
}
