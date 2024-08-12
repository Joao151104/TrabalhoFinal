
# Sistema de Gestão Acadêmica

## Visão Geral

Este projeto é uma implementação de um sistema de gestão acadêmica, desenvolvido em Java, com o objetivo de aplicar conceitos fundamentais de Orientação por Objetos (OO), incluindo herança e polimorfismo. O sistema permite o cadastro de alunos, professores, disciplinas e turmas, além de associar professores e alunos às turmas. Também trata exceções específicas para garantir a consistência dos dados e oferece funcionalidades como impressão de listas de presença.

## Funcionalidades

- **Cadastro de Alunos, Professores, Disciplinas e Turmas**:
  - Adicionar novos registros.
  - Pesquisar registros existentes.
  - Alterar informações de registros existentes.
  - Remover registros.


- **Impressão de Lista de Presença**:
  - Impressão da lista de presença para uma turma específica, incluindo o nome do professor, código da turma, e a lista de alunos matriculados (matrícula e nome).


## Instruções de Execução

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/Joao151104/TrabalhoFinal.git
   cd TrabalhoFinal
   ```

2. **Compilar o projeto**:
   javac -d bin src/Principal.java

3. **Executar o projeto**
   cd src
   java Principal.java


## Exemplos de Uso

### Cadastro de Alunos
- O sistema permite cadastrar alunos com os atributos `nome`, `matrícula`, `email`, `curso`e `CPF`. 

### Cadastro de Turmas
- Ao criar uma nova turma, é necessário associar uma disciplina e um professor já cadastrados. 

### Impressão de Lista de Presença
- Para imprimir a lista de presença de uma turma, basta informar o código da turma ao sistema, que retornará a lista completa de alunos matriculados, junto com as informações da disciplina e do professor.

## Documentação

A documentação completa do projeto, incluindo diagramas de classes e exemplos detalhados de execução, pode ser encontrada no diretório `/docs` do repositório.

## Contribuidores

231035141 - João FIlipe de Oliveira Souza
231026563 - Rafael Barbosa de Melo
231038081 - Giovana Martins de Brito 
231034322 - Cecilia da Costa Beck
#
