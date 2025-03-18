package com.juliopupim.desafios.itau.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Transacao {

  private final BigDecimal valor;
  private final OffsetDateTime dataHora;

  private Transacao(BigDecimal valor, OffsetDateTime dataHora) {
    this.valor = valor;
    this.dataHora = dataHora;
  }

  public static void of(BigDecimal valor, OffsetDateTime dataHora) {
    validaDataHora(dataHora);
    validaValor(valor);
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
