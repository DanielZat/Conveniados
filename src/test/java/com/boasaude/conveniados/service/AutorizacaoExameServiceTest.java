package com.boasaude.conveniados.service;

import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.boasaude.conveniados.exception.AutorizacaoExameException;
import com.boasaude.conveniados.exception.NotFoundException;
import com.boasaude.conveniados.helper.AssociadoHelper;
import com.boasaude.conveniados.repository.AssociadoRepository;
import com.boasaude.conveniados.request.AutorizacaoExameRequest;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.main.webApplicationType=reactive"})
public class AutorizacaoExameServiceTest {

    @Autowired
    private AutorizacaoExameService autorizacaoExameService;

    @MockBean
    private AssociadoRepository associadoRepository;

    @Test
    public void deveAutorizarExameComSucesso() {

        var autorizacaoExameRequest = AutorizacaoExameRequest.builder().codigoProcedimento(345644L).numeroCarteira(5542432034259090L).build();

        var associadoEntity = AssociadoHelper.criarAssociadoAtivo();

        when(associadoRepository.buscarAssociadoPorCarteira(autorizacaoExameRequest.getNumeroCarteira()))
                .thenReturn(Mono.just(associadoEntity));

        var autorizacaoExameResponse = autorizacaoExameService.autorizarExecucaoExame(autorizacaoExameRequest).block();

        Assertions.assertNotNull(autorizacaoExameResponse);
        Assertions.assertEquals(autorizacaoExameRequest.getCodigoProcedimento(), autorizacaoExameResponse.getCodigoProcedimento());
        Assertions.assertEquals("Autorizado", autorizacaoExameResponse.getSituacao());
        Assertions.assertEquals(StringUtils.EMPTY, autorizacaoExameResponse.getMensagem());
    }

    @Test
    public void naoDeveAutorizarExameUsuarioNaoEncontrado() {

        var autorizacaoExameRequest = AutorizacaoExameRequest.builder().codigoProcedimento(999999L).numeroCarteira(99999999999999L).build();

        when(associadoRepository.buscarAssociadoPorCarteira(autorizacaoExameRequest.getNumeroCarteira()))
                .thenReturn(Mono.empty());

        StepVerifier.create(autorizacaoExameService.autorizarExecucaoExame(autorizacaoExameRequest))
                .expectErrorMatches(ex -> ex instanceof NotFoundException
                        && ((NotFoundException) ex).getReason().equals("Nenhum usuário encontrado para esta carteira."))
                .verify();
    }

    @Test
    public void naoDeveAutorizarExameProcedimentoNaoIncluso() {

        var autorizacaoExameRequest = AutorizacaoExameRequest.builder().codigoProcedimento(999999L).numeroCarteira(5542432034259090L).build();

        var associadoEntity = AssociadoHelper.criarAssociadoAtivo();

        when(associadoRepository.buscarAssociadoPorCarteira(autorizacaoExameRequest.getNumeroCarteira()))
                .thenReturn(Mono.just(associadoEntity));

        StepVerifier.create(autorizacaoExameService.autorizarExecucaoExame(autorizacaoExameRequest))
                .expectErrorMatches(ex -> ex instanceof AutorizacaoExameException
                        && ((AutorizacaoExameException) ex).getReason().equals("Procedimento não está incluso no plano."))
                .verify();
    }

    @Test
    public void naoDeveAutorizarExameUsuarioNaoAtivo() {

        var autorizacaoExameRequest = AutorizacaoExameRequest.builder().codigoProcedimento(345644L).numeroCarteira(5542432034259090L).build();

        var associadoEntity = AssociadoHelper.criarAssociadoInativo();

        when(associadoRepository.buscarAssociadoPorCarteira(autorizacaoExameRequest.getNumeroCarteira()))
                .thenReturn(Mono.just(associadoEntity));

        StepVerifier.create(autorizacaoExameService.autorizarExecucaoExame(autorizacaoExameRequest))
                .expectErrorMatches(ex -> ex instanceof AutorizacaoExameException
                        && ((AutorizacaoExameException) ex).getReason().equals("Usuário não está ativo."))
                .verify();
    }

    @Test
    public void naoDeveAutorizarExameUsuarioPeriodoCarencia() {

        var autorizacaoExameRequest = AutorizacaoExameRequest.builder().codigoProcedimento(345644L).numeroCarteira(2345232225975477L).build();

        var associadoEntity = AssociadoHelper.criarAssociadoCumprindoCarencia();

        when(associadoRepository.buscarAssociadoPorCarteira(autorizacaoExameRequest.getNumeroCarteira()))
                .thenReturn(Mono.just(associadoEntity));

        StepVerifier.create(autorizacaoExameService.autorizarExecucaoExame(autorizacaoExameRequest))
                .expectErrorMatches(ex -> ex instanceof AutorizacaoExameException
                        && ((AutorizacaoExameException) ex).getReason()
                                .equals("Usuário está no período de carência e não pode realizar o procedimento."))
                .verify();
    }
}
