package com.juliopupim.desafios.itau.controller;

import com.juliopupim.desafios.itau.domain.Transacao;
import com.juliopupim.desafios.itau.domain.dto.TransacaoRequestDTO;
import com.juliopupim.desafios.itau.service.TransacaoService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.validation.Valid;
import java.util.DoubleSummaryStatistics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class TransacaoController {

  private final TransacaoService transacaoService;
  private final MeterRegistry meterRegistry;


  @GetMapping("/estatistica")
  public ResponseEntity<DoubleSummaryStatistics> getEstatistica(
      @RequestParam(defaultValue = "1") Long tempo) {
    return Timer.builder("estatistica")
        .description("Tempo Gasto para calcular estatística")
        .register(meterRegistry).record(() -> ResponseEntity.ok(
            transacaoService.calculaEstatistica(tempo)));
  }

  @PostMapping("/transacao")
  public ResponseEntity<Transacao> salvarTransacao(
      @RequestBody @Valid TransacaoRequestDTO transacaoDTO) {
    transacaoService.save(transacaoDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/transacao")
  public ResponseEntity<Void> deleteTransacao() {
    transacaoService.deleteAll();
    return ResponseEntity.ok().build();
  }

}
