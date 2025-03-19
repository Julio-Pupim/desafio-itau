package com.juliopupim.desafios.itau.domain;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Transacao {

  @NotNull
  private final BigDecimal valor;
  @NotNull
  private final OffsetDateTime dataHora;

  private Transacao(BigDecimal valor, OffsetDateTime dataHora) {
    this.valor = valor;
    this.dataHora = dataHora;
  }

  public static Transacao of(BigDecimal valor, OffsetDateTime dataHora) {
    validaDataHora(dataHora);
    validaValor(valor);
    return new Transacao(valor, dataHora);
  }

  private static void validaValor(BigDecimal valor) {
    if (valor.signum() < 0) {
      throw new IllegalArgumentException("O valor não pode ser negativo");
    }
  }

  private static void validaDataHora(OffsetDateTime dataHora) {
    if (dataHora.isAfter(OffsetDateTime.now())) {
      throw new IllegalArgumentException("A data não pode estar no futuro");
    }
  }

}
