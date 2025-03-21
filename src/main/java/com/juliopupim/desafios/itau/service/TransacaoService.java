package com.juliopupim.desafios.itau.service;

import static com.juliopupim.desafios.itau.domain.Transacao.of;

import com.juliopupim.desafios.itau.domain.Transacao;
import com.juliopupim.desafios.itau.domain.dto.TransacaoRequestDTO;
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

  public Transacao get(Long id) {
    return transacaoMap.getOrDefault(id, null);
  }

  public List<Transacao> getAll() {
    return transacaoMap.values().stream().toList();
  }

  public void deleteAll() {
    log.info("removendo todas as entidades");
    transacaoMap.clear();
  }

}
