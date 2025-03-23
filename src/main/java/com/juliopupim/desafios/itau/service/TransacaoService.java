package com.juliopupim.desafios.itau.service;

import static com.juliopupim.desafios.itau.domain.Transacao.of;

import com.juliopupim.desafios.itau.domain.Transacao;
import com.juliopupim.desafios.itau.domain.dto.TransacaoRequestDTO;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

  private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);
  private final Map<Long, Transacao> transacaoMap = new HashMap<>();
  private Long id = 1L;


  public void save(TransacaoRequestDTO transacaoDTO) {
    log.info("Salvando entidade com id: {}", id);
    Transacao entidade = of(transacaoDTO.valor(), transacaoDTO.dataHora());
    transacaoMap.put(id, entidade);
    id++;
  }

  public DoubleSummaryStatistics calculaEstatistica() {
    OffsetDateTime ultimoMinuto = OffsetDateTime.now().minusMinutes(1);
    List<BigDecimal> allOnLastMinute = getAllOnLastMinute(ultimoMinuto);
    DoubleSummaryStatistics estatistica = new DoubleSummaryStatistics();
    allOnLastMinute.forEach(valor -> estatistica.accept(valor.doubleValue()));
    return estatistica;
  }

  public void deleteAll() {
    log.info("removendo todas as entidades");
    transacaoMap.clear();
  }

  public List<Transacao> getAll() {
    return transacaoMap.values().stream().toList();
  }

  private List<BigDecimal> getAllOnLastMinute(OffsetDateTime minuto) {
    return transacaoMap.values().stream()
        .filter(transacao -> transacao.getDataHora().isAfter(minuto)).map(Transacao::getValor)
        .toList();
  }


}
