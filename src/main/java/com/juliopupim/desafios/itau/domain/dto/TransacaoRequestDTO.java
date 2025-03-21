package com.juliopupim.desafios.itau.domain.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoRequestDTO(
    @NotNull BigDecimal valor,
    @NotNull OffsetDateTime dataHora
) {

}
