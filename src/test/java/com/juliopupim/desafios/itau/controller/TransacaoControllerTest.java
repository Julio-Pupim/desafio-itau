package com.juliopupim.desafios.itau.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliopupim.desafios.itau.domain.Transacao;
import com.juliopupim.desafios.itau.service.TransacaoService;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TransacaoControllerTest {

  private MockMvc mockMvc;

  @MockitoBean
  private TransacaoService transacaoService;

  @Autowired
  private TransacaoController transacaoController;

  private JacksonTester<Transacao> jackson;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    JacksonTester.initFields(this, objectMapper);
    mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
  }


  @Test
  public void deveSalvarTransacao() throws Exception {
    Transacao transacao = Transacao.of(BigDecimal.TEN, OffsetDateTime.now().minusDays(1));
    MockHttpServletResponse response = mockMvc.perform(
            MockMvcRequestBuilders.post("/transacao")
                .contentType(MediaType.APPLICATION_JSON).content(jackson.write(transacao)
                    .getJson()))
        .andReturn()
        .getResponse();

    Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    Assertions.assertThat(response.getContentAsString()).isEmpty();

  }

  @Test
  public void deveLancarErro400QuandoJsonInvalido() throws Exception {
    String jsonInvalido = "{ \"valor\": 10.5, \"dataHora\": \"2024-03-20T10:00:00Z\"";
    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonInvalido))
        .andReturn()
        .getResponse();

    Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    Assertions.assertThat(response.getContentAsString()).isEmpty();

  }

  @Test
  public void deveLancarErro422() throws Exception {
    String jsonInvalido = "{ \"valor\": -10.0, \"dataHora\": \"2024-03-20T10:00:00Z\"}";

    MockHttpServletResponse response = mockMvc.perform(
            MockMvcRequestBuilders.post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInvalido))
        .andReturn()
        .getResponse();

    Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
    Assertions.assertThat(response.getContentAsString()).isEmpty();

  }

  @Test
  public void deveTrazerEstatisca() throws Exception {
    DoubleSummaryStatistics estatisticaMock = new DoubleSummaryStatistics();
    estatisticaMock.accept(10.0);
    estatisticaMock.accept(20.0);
    estatisticaMock.accept(30.0);

    when(transacaoService.calculaEstatistica(anyLong())).thenReturn(estatisticaMock);

    MockHttpServletResponse response = mockMvc.perform(
            MockMvcRequestBuilders.get("/estatistica")
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn()
        .getResponse();

    Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    Assertions.assertThat(response.getContentAsString()).contains("\"count\":3");
    Assertions.assertThat(response.getContentAsString()).contains("\"sum\":60.0");
    Assertions.assertThat(response.getContentAsString()).contains("\"average\":20.0");
    Assertions.assertThat(response.getContentAsString()).contains("\"min\":10.0");
    Assertions.assertThat(response.getContentAsString()).contains("\"max\":30.0");

  }


  @Test
  public void deleteTransacao() throws Exception {
    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/transacao"))
        .andReturn()
        .getResponse();

    verify(transacaoService).deleteAll();

    Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    Assertions.assertThat(response.getContentAsString()).isEmpty();

  }
}