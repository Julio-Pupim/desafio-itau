package com.juliopupim.desafios.itau.service;

import com.juliopupim.desafios.itau.domain.Transacao;
import com.juliopupim.desafios.itau.domain.dto.TransacaoRequestDTO;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
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
    Assertions.assertThat(transacaoList).hasSize(1);
    Assertions.assertThat(transacaoList.getFirst()).isEqualTo(transacao);
  }

  @Test
  void get() {
    transacaoService.save(transacaoRequestDTO);
    Transacao transacao1 = transacaoService.get(1L);
    Assertions.assertThat(transacao1).isNotNull();
    Assertions.assertThat(transacao1).isEqualTo(transacao);
  }

  @Test
  void getAll() {
    transacaoService.save(transacaoRequestDTO);
    TransacaoRequestDTO transacao1 = new TransacaoRequestDTO(BigDecimal.TEN,
        OffsetDateTime.now().minusDays(1L));
    transacaoService.save(transacao1);
    List<Transacao> transacaoList = transacaoService.getAll();
    Assertions.assertThat(transacaoList).hasSize(2);
  }

  @Test
  public void delete() {
    TransacaoRequestDTO transacao1 = new TransacaoRequestDTO(BigDecimal.TEN,
        OffsetDateTime.now().minusDays(1L));
    transacaoService.save(transacao1);
    transacaoService.save(transacaoRequestDTO);
    transacaoService.deleteAll();
    List<Transacao> transacaoList = transacaoService.getAll();
    Assertions.assertThat(transacaoList).isEmpty();
  }
}