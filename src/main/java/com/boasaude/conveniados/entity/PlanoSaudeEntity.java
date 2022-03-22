package com.boasaude.conveniados.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.boasaude.conveniados.enums.AbrangenciaEnum;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PlanoSaudeEntity {

    private Integer id;

    private String nome;

    private AbrangenciaEnum abrangenciaEnum;

    private Integer carencia;

    private Boolean possuiCoparticipacao;

    private Long valorCoparticipacao;

    private List<ProcedimentoEntity> procedimentosInclusos;
}
