# Test API Dog

Este repositório apresenta uma prova técnica desenvolvida para um processo seletivo, com o objetivo de demonstrar uma estrutura simples de automação de testes de API utilizando Java e Maven.

O projeto executa cenários automatizados, valida os resultados esperados e gera um relatório de execução integrado ao fluxo de CI/CD.

## Tecnologias utilizadas

- Java
- Maven
- TestNG
- Rest Assured
- Extent Reports

## Como executar o projeto

Execute o comando abaixo na raiz do projeto:
```bash
mvn test
```

## Relatório de execução

Após a execução dos testes, o relatório HTML será gerado no diretório:

```bash
target/reports
```

Abra o arquivo .html gerado para visualizar os resultados detalhados da execução dos testes, incluindo cenários aprovados, falhas e evidências da execução.

## Execução dos testes via GitHub Actions

Os testes automatizados deste projeto são executados por meio do **GitHub Actions**, utilizando o workflow localizado em:

```bash
.github/workflows/test-api-dog.yml
```

Esse workflow define um pipeline de CI/CD responsável por baixar o código, preparar o ambiente, executar os testes de API e disponibilizar o relatório de execução para download.

---

## Visão geral do workflow

O workflow é acionado manualmente através do evento:

```yaml
on:
  workflow_dispatch:
```
Isso permite que a execução dos testes seja iniciada sob demanda diretamente pela interface do GitHub.

### Sequência de execução do CI/CD

A seguir está o fluxo completo de execução do pipeline:

1. Baixar o código do repositório
```yaml
- name: Baixar código
  uses: actions/checkout@v4
```

Realiza o clone do repositório para o ambiente do GitHub Actions, garantindo acesso ao código-fonte e às configurações do projeto.

2. Configurar o ambiente Java (versão 21)
```yaml
- name: Configurar Java 21
  uses: actions/setup-java@v4
  with:
    java-version: '21'
    distribution: 'temurin'
```

Configura a JVM necessária para a execução do projeto, assegurando compatibilidade com a versão definida no build.

3. Instalar dependências do projeto
```yaml
- name: Instalar dependencias
  run: mvn install -DskipTests
```
Executa o Maven para baixar e configurar todas as dependências, acelerando a etapa seguinte ao pular a execução dos testes neste momento.

4. Executar os testes automatizados
```yaml
- name: Rodar os testes
  run: mvn test
  continue-on-error: true
```
Inicia a execução dos testes de API.
A opção continue-on-error: true garante que o pipeline continue mesmo que algum teste falhe, permitindo a geração e publicação do relatório.

5. Gerar e disponibilizar o relatório para download
```yaml
- name: Gerar o relatório para baixar.
  uses: actions/upload-artifact@v4
  if: always()
  with:
    name: extent-report
    path: target/reports/
```
Publica o relatório de testes (Extent Report) como um artefato do workflow, disponível para download diretamente no GitHub Actions, independentemente do resultado da execução.

