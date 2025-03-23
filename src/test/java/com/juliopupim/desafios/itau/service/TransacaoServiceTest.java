package com.juliopupim.desafios.itau.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.juliopupim.desafios.itau.domain.Transacao;
import com.juliopupim.desafios.itau.domain.dto.TransacaoRequestDTO;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransacaoServiceTest {

  private TransacaoService transacaoService;

  private Transacao transacao;

  private TransacaoRequestDTO transacaoRequestDTO;

  @BeforeEach
  public void setup() {
    transacaoService = new TransacaoService();
    transacaoRequestDTO = new TransacaoRequestDTO(BigDecimal.TEN,
        OffsetDateTime.now().minusDays(1L));
    transacao = Transacao.of(transacaoRequestDTO.valor(), transacaoRequestDTO.dataHora());

  }

  @Test
  void save() {
    transacaoService.save(transacaoRequestDTO);
    List<Transacao> transacaoList = transacaoService.getAll();
    assertThat(transacaoList).hasSize(1);
    assertThat(transacaoList.getFirst()).isEqualTo(transacao);
  }

  @Test
  void deveTrazerEstraticaUltimoMinuto() {
    createListTransacao();
    DoubleSummaryStatistics doubleSummaryStatistics = transacaoService.calculaEstatistica(1L);
    assertThat(doubleSummaryStatistics.getAverage()).isEqualTo(37.0);
    assertThat(doubleSummaryStatistics.getCount()).isEqualTo(3L);
    assertThat(doubleSummaryStatistics.getMin()).isEqualTo(1.0);
    assertThat(doubleSummaryStatistics.getMax()).isEqualTo(100.0);
    assertThat(doubleSummaryStatistics.getSum()).isEqualTo(111.0);


  }


  @Test
  public void delete() {
    TransacaoRequestDTO transacao1 = new TransacaoRequestDTO(BigDecimal.TEN,
        OffsetDateTime.now().minusDays(1L));
    transacaoService.save(transacao1);
    transacaoService.save(transacaoRequestDTO);
    transacaoService.deleteAll();
    List<Transacao> transacaoList = transacaoService.getAll();
    assertThat(transacaoList).isEmpty();
  }

  private void createListTransacao() {
    TransacaoRequestDTO transacao1 = new TransacaoRequestDTO(BigDecimal.valueOf(100),
        OffsetDateTime.now().minusSeconds(15));
    TransacaoRequestDTO transacao2 = new TransacaoRequestDTO(BigDecimal.TEN,
        OffsetDateTime.now().minusSeconds(30));
    TransacaoRequestDTO transacao3 = new TransacaoRequestDTO(BigDecimal.ONE,
        OffsetDateTime.now().minusSeconds(45));
    transacaoService.save(transacao1);
    transacaoService.save(transacao2);
    transacaoService.save(transacao3);

  }
}