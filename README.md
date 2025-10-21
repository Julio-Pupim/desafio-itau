[![Build Status](https://github.com/Julio-Pupim/desafio-itau/actions/workflows/maven.yml/badge.svg)](https://github.com/Julio-Pupim/desafio-itau/actions)
![Java](https://img.shields.io/badge/Java-21-blue)
[![Codecov](https://img.shields.io/codecov/c/github/Julio-Pupim/desafio-itau)](https://codecov.io/gh/Julio-Pupim/desafio-itau)
# README - Desafio Técnico Itaú Backend

Este projeto é uma solução para o [Desafio Técnico Itaú Backend](https://github.com/feltex/desafio-itau-backend?tab=readme-ov-file), desenvolvido em Java 21 com Spring Boot 3.4.3. A aplicação foi projetada para ser configurável, escalável e de fácil manutenção, seguindo as melhores práticas de desenvolvimento de software.

---

## Sumário
- [Proposta do Projeto](#proposta-do-projeto)
- [Formas de Rodar a Aplicação](#formas-de-rodar-a-aplicação)
  - [Com Docker](#com-docker)
  - [Sem Docker](#sem-docker)
- [Endpoints da Aplicação](#endpoints-da-aplicação)
  - [Spring Actuator](#spring-actuator)
  - [Swagger](#swagger)
- [Vídeo Explicativo](#vídeo-explicativo)
- [Aprendizados](#Aprendizados)

---
## Proposta do Projeto

Este projeto tem como objetivo resolver 100% do [Desafio Técnico Itaú Backend](https://github.com/feltex/desafio-itau-backend?tab=readme-ov-file). O desafio propõe a criação de uma API RESTful para gerenciar transações financeiras e calcular estatísticas em tempo real, atendendo aos critérios de aceite especificados.

### Principais Funcionalidades Implementadas:
- **Registro de Transações:** Permite cadastrar transações com valor e data/hora.
- **Estatísticas em Tempo Real:** Calcula soma, mínimo, máximo, média e contagem das transações dos últimos 60 segundos (tempo configurável).
- **Validação de Dados:** Impede o cadastro de transações com valores negativos ou datas futuras.
- **Gerenciamento de Dados:** Remove transações antigas automaticamente, garantindo performance e integridade.
- 
---

## Formas de Rodar a Aplicação

A aplicação pode ser executada de duas maneiras: com Docker ou sem Docker. Abaixo, você encontrará instruções detalhadas para cada opção.

### Com Docker

Para executar a aplicação utilizando Docker, siga os passos abaixo:

1. **Pré-requisitos:**
   - Certifique-se de ter o Docker instalado em sua máquina. Você pode baixá-lo em [docker.com/get-started](https://www.docker.com/get-started).

2. **Construir a Imagem Docker:**
   - Navegue até o diretório raiz do projeto, onde está o arquivo `Dockerfile`.
   - Execute o comando:
     ```bash
     docker build -t desafio-itau-backend .
     ```

3. **Executar o Contêiner:**
   - Após construir a imagem, execute:
     ```bash
     docker run -p 8080:8080 desafio-itau-backend
     ```
   - A aplicação estará disponível em `http://localhost:8080`.

### Sem Docker

Para rodar a aplicação sem Docker, siga os passos abaixo:

1. **Pré-requisitos:**
   - Certifique-se de ter o Java 21 instalado. Faça o download em [oracle.com/java](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html).
   - Certifique-se de ter o Maven instalado. Baixe-o em [maven.apache.org](https://maven.apache.org/download.cgi).

2. **Compilar e Executar a Aplicação:**
   - Navegue até o diretório raiz do projeto.
   - Compile e empacote a aplicação com:
     ```bash
     mvn clean package
     ```
   - Execute a aplicação com:
     ```bash
     java -jar target/desafio-itau-backend-0.0.1-SNAPSHOT.jar
     ```
   - A aplicação estará disponível em `http://localhost:8080`.

---

## Endpoints da Aplicação

A aplicação disponibiliza os seguintes endpoints principais:

- **POST /transacao:** Registra uma nova transação.
- **GET /estatistica:** Retorna as estatísticas das transações dos últimos 60 segundos (configurável).

Além disso, foram integrados o Spring Actuator e o Swagger para monitoramento e documentação da API.

### Spring Actuator

O Spring Actuator oferece endpoints para monitoramento e gerenciamento da aplicação. Os principais são:

- **GET /actuator/health:** Verifica o status da aplicação.
- **GET /actuator/info:** Exibe informações gerais da aplicação.
- **GET /actuator/metrics:** Fornece métricas detalhadas.

Acesse todos os endpoints do Actuator em `http://localhost:8080/actuator`.

### Swagger

O Swagger documenta e permite testar os endpoints da API de forma interativa. A interface está disponível em `http://localhost:8080/swagger-ui.html`.

Veja abaixo um print da interface do Swagger:

![swagger](https://github.com/user-attachments/assets/25f78549-0218-4b82-8203-33c00ddde222)

---

## Vídeo Explicativo

Para uma explicação detalhada sobre o projeto, incluindo a arquitetura, decisões de design e uma demonstração prática, assista ao vídeo abaixo:

[em breve](https://www.youtube.com/link-do-video)

---

## Aprendizados
  Durante o desafio tive que aprender sobre novas bibliotecas e ferramentas, para isso pesquisei bastante, reuni alguns dos links que acessei para que possam ajudar você a estudar esses assuntos também:
- swagger: O que é? Como configurar e como usar. [Link para estudo](https://medium.com/@f.s.a.kuzman/using-swagger-3-in-spring-boot-3-c11a483ea6dc)
- Logs: como funciona? Quais bibliotecas usar? [video explicado bibliotecas de log](https://www.youtube.com/watch?v=SWHYrCXIL38&t=420s&ab_channel=JavaBrains), [Artigo explicando](https://springframework.guru/using-logback-spring-boot/)
- Health check endpoints: o que é? Como implementar? [Artigo](https://devkico.itexto.com.br/?p=3205) 
- performance. Como fazer medidas no endpoint? [Estude sobre micrometer](https://www.youtube.com/watch?v=RAqG4XaTgv4&ab_channel=LinhVu)
- Docker CLI. [Containerize a Java application](https://docs.docker.com/guides/java/containerize/)

Outros links também importante:
- [Documentação Oficial do Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [Swagger com Spring Boot](https://springdoc.org/)
- [Docker para Desenvolvedores Java](https://www.docker.com/blog/java-developers-guide-to-docker/)

---
