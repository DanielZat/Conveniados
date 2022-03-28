package com.boasaude.conveniados.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.boasaude.conveniados.request.AutorizacaoExameRequest;
import com.boasaude.conveniados.response.AutorizacaoExameResponse;
import com.boasaude.conveniados.service.AutorizacaoExameService;

import reactor.core.publisher.Mono;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/autorizacaoexame")
@RequiredArgsConstructor
@RestController
public class AutorizacaoExameController {

    private final AutorizacaoExameService autorizacaoExameService;

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autorizado com sucesso", content = @Content(
                    schema = @Schema(
                            implementation = AutorizacaoExameResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ocorreu um problema ao verificar autorização para realização do exame")
    })
    @ResponseStatus(HttpStatus.OK)
    public Mono<AutorizacaoExameResponse> autorizarExecucaoExame(@RequestBody AutorizacaoExameRequest autorizacaoExameRequest) {
        return autorizacaoExameService.autorizarExecucaoExame(autorizacaoExameRequest);
    }
}
