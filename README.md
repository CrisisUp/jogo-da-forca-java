# 🪓 Jogo da Forca Java (Versão Ultra-Moderna 2026)

Bem-vindo à evolução definitiva do clássico **Jogo da Forca**. Este projeto foi completamente refatorado para atingir o estado da arte do desenvolvimento Java em 2026, combinando segurança, performance e uma experiência de usuário (UX) excepcional.

---

## 🚀 Destaques Tecnológicos (Stack 2026)

Este projeto não é apenas um jogo; é um exemplo de engenharia de software moderna:

- **Java Platform Module System (JPMS)**: 100% modularizado para encapsulamento forte e segurança.
- **Java 25 (LTS) Support**: Utiliza os recursos mais recentes como `Records`, `Switch Expressions`, `RandomGenerator API` e `Sequenced Collections`.
- **NIO.2 File System**: Manipulação de arquivos moderna e segura contra ataques de *Path Traversal*.
- **Swing Thread-Safety**: Interface gráfica robusta processada na *Event Dispatch Thread (EDT)* via `SwingUtilities.invokeLater`.
- **Zero Dependências Externas**: Código purista, utilizando apenas APIs nativas da JDK para máxima portabilidade.

---

## 🎨 Experiência do Usuário (UX)

- **🌑 Modo Escuro (Dark Mode)**: Interface visual moderna com paleta grafite e azul destaque, pensada para o conforto visual.
- **⌨️ Teclado Físico (Agilidade)**: Suporte total para digitação direta. O sistema identifica as teclas pressionadas e as sincroniza com o teclado virtual.
- **⏱️ Cronômetro em Tempo Real**: Desafie-se a resolver a forca no menor tempo possível.
- **🏆 Sistema de Recordes**: Persistência de dados para salvar e exibir o seu melhor tempo histórico.
- **🌍 Consciência de Localização**: O jogo identifica automaticamente o seu país, idioma e horário para saudações personalizadas.

---

## 🛡️ Segurança e Robustez

O código foi auditado para garantir:

1. **Proteção de I/O**: Verificação de integridade de caminhos absolutos no carregamento de palavras e dicas.
2. **Imutabilidade**: Uso de `List.copyOf` para evitar efeitos colaterais na lógica de jogo.
3. **Escaneamento de Erros**: Tratamento rigoroso de exceções de áudio e sistema de arquivos.

---

## 📂 Estrutura Modular do Projeto

```text
src/
└── hangman/
    ├── module-info.java        # Definição do módulo
    └── hangman/
        ├── Main.java           # Ponto de entrada e i18n
        ├── logic/              # Lógica core do jogo
        ├── ui/                 # Interface Gráfica e UX
        └── util/               # I/O, Som e Recordes
```

---

## 🎮 Como Jogar

Certifique-se de ter o **JDK 25** instalado no seu sistema.

### 🍎 No macOS / 🐧 No Linux:

1. Dê permissão de execução ao script:

   ```bash
   chmod +x jogar.sh
   ```

2. Inicie o jogo:

   ```bash
   ./jogar.sh
   ```

### 🪟 No Windows

Basta clicar duas vezes no arquivo ou rodar via terminal:

```cmd
jogar.bat
```

---

## 👥 Créditos

**Autores Originais:**

- Ana Alice Gomes Soares
- Arthur Alves de Souza Costa

**Refatoração e Modernização (2026):**

- Realizada via **Gemini CLI (Google)** para transformar um projeto educacional em uma aplicação modular de alta performance.

---

*© 2026 - Desenvolvido com foco em excelência técnica e diversão.*
