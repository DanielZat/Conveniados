package com.boasaude.conveniados.helper;

import java.time.LocalDateTime;
import java.util.Arrays;

import com.boasaude.conveniados.entity.AssociadoEntity;
import com.boasaude.conveniados.entity.PlanoSaudeEntity;
import com.boasaude.conveniados.entity.ProcedimentoEntity;
import com.boasaude.conveniados.enums.AbrangenciaEnum;

public class AssociadoHelper {

    public static AssociadoEntity criarAssociadoAtivo() {

        return AssociadoEntity.builder()
                .id("123456")
                .ativo(Boolean.TRUE)
                .numeroCarteira(5542432034259090L)
                .planoSaude(PlanoSaudeEntity.builder()
                        .id(3)
                        .nome("Plano Boa Saúde VIP")
                        .abrangenciaEnum(AbrangenciaEnum.NACIONAL)
                        .carencia(0)
                        .possuiCoparticipacao(Boolean.FALSE)
                        .valorCoparticipacao(0L)
                        .procedimentosInclusos(Arrays.asList(ProcedimentoEntity
                                .builder()
                                .codigoProcedimento(345644L)
                                .nomeProcedimento("Ecocardiograma Doppler")
                                .build(), ProcedimentoEntity
                                        .builder()
                                        .codigoProcedimento(365233L)
                                        .nomeProcedimento("Ecografia Lombar")
                                        .build(), ProcedimentoEntity
                                                .builder()
                                                .codigoProcedimento(504432L)
                                                .nomeProcedimento("Eletrocardiograma")
                                                .build()))
                        .build())
                .dataContratacaoPlano(LocalDateTime.of(2021, 03, 20, 15, 30))
                .build();
    }

    public static AssociadoEntity criarAssociadoInativo() {

        return AssociadoEntity.builder()
                .id("123456")
                .ativo(Boolean.FALSE)
                .numeroCarteira(5542432034259090L)
                .planoSaude(PlanoSaudeEntity.builder()
                        .id(3)
                        .nome("Plano Boa Saúde VIP")
                        .abrangenciaEnum(AbrangenciaEnum.NACIONAL)
                        .carencia(0)
                        .possuiCoparticipacao(Boolean.FALSE)
                        .valorCoparticipacao(0L)
                        .procedimentosInclusos(Arrays.asList(ProcedimentoEntity
                                .builder()
                                .codigoProcedimento(345644L)
                                .nomeProcedimento("Ecocardiograma Doppler")
                                .build(), ProcedimentoEntity
                                        .builder()
                                        .codigoProcedimento(365233L)
                                        .nomeProcedimento("Ecografia Lombar")
                                        .build(), ProcedimentoEntity
                                                .builder()
                                                .codigoProcedimento(504432L)
                                                .nomeProcedimento("Eletrocardiograma")
                                                .build()))
                        .build())
                .dataContratacaoPlano(LocalDateTime.of(2021, 03, 20, 15, 30))
                .build();
    }

    public static AssociadoEntity criarAssociadoCumprindoCarencia() {

        return AssociadoEntity.builder()
                .id("987654")
                .ativo(Boolean.TRUE)
                .numeroCarteira(2345232225975477L)
                .planoSaude(PlanoSaudeEntity.builder()
                        .id(1)
                        .nome("Plano Boa Saúde ENFERMARIA")
                        .abrangenciaEnum(AbrangenciaEnum.ESTADUAL)
                        .carencia(180)
                        .possuiCoparticipacao(Boolean.TRUE)
                        .valorCoparticipacao(30L)
                        .procedimentosInclusos(Arrays.asList(ProcedimentoEntity
                                .builder()
                                .codigoProcedimento(345644L)
                                .nomeProcedimento("Ecocardiograma Doppler")
                                .build(), ProcedimentoEntity
                                        .builder()
                                        .codigoProcedimento(365233L)
                                        .nomeProcedimento("Ecografia Lombar")
                                        .build()))
                        .build())
                .dataContratacaoPlano(LocalDateTime.of(2022, 02, 17, 12, 21))
                .build();
    }
}
