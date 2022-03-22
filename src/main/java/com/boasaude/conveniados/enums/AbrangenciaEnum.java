package com.boasaude.conveniados.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public enum AbrangenciaEnum {

    REGIONAL("R", "Regional"), ESTADUAL("E", "Estadual"), NACIONAL("N",
            "Nacional");

    private final @Getter String value;

    private final @Getter String externalValue;
}
