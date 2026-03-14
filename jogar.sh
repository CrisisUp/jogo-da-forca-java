#!/bin/bash

# Script para compilar e executar o Jogo da Forca Modularizado (Compatível com Java 25)

# 1. Limpa a pasta bin anterior
rm -rf bin
mkdir -p bin

# 2. Compila o projeto habilitando preview features para o release 25
echo "🔨 Compilando o projeto com recursos modernos (Java 25)..."
javac --enable-preview --release 25 -d bin --module-source-path src -m hangman

# 3. Verifica se a compilação teve sucesso
if [ $? -eq 0 ]; then
    echo "✅ Compilação concluída!"
    echo "🚀 Iniciando o Jogo..."
    # Executa habilitando preview
    java --enable-preview --module-path bin -m hangman/hangman.Main
else
    echo "❌ Erro na compilação. Certifique-se de estar usando o JDK 25."
    exit 1
fi
