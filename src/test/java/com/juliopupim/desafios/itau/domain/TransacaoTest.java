package com.juliopupim.desafios.itau.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransacaoTest {

  @Test
  public void naoDeveAceitarDataFutura() {
    Assertions.assertThatThrownBy(
        () -> Transacao.of(BigDecimal.TEN, OffsetDateTime.now().plusDays(1))).isInstanceOf(
        IllegalArgumentException.class).hasMessage("A data não pode estar no futuro");
  }

  @Test
  public void naoDeveAceitarValorNegativo() {
    Assertions.assertThatThrownBy(
            () -> Transacao.of(BigDecimal.TEN.negate(), OffsetDateTime.now().minusDays(1)))
        .isInstanceOf(
            IllegalArgumentException.class).hasMessage("O valor não pode ser negativo");
  }

  @Test
  public void naoDeveAceitarValoresNulos() {
    Assertions.assertThatThrownBy(
            () -> Transacao.of(null, OffsetDateTime.now().minusDays(1)))
        .isInstanceOf(
            IllegalArgumentException.class).hasMessage("O valor não pode ser nulo");

    Assertions.assertThatThrownBy(
            () -> Transacao.of(BigDecimal.TEN, null))
        .isInstanceOf(
            IllegalArgumentException.class).hasMessage("A data da transação não pode ser nula");
  }

}