@echo off
setlocal enabledelayedexpansion

:: Script para compilar e executar o Jogo da Forca no Windows (Java 25)

echo ------------------------------------------
echo   🔨 Compilando o Jogo da Forca (Moderno)
echo ------------------------------------------

:: 1. Limpa a pasta bin anterior se existir
if exist bin (
    rd /s /q bin
)
mkdir bin

:: 2. Compila o projeto habilitando preview features para o release 25
:: O ponto e virgula (;) e usado no Windows para separar caminhos, mas no javac modular e o mesmo padrao
javac --enable-preview --release 25 -d bin --module-source-path src -m hangman

:: 3. Verifica se a compilação teve sucesso
if %ERRORLEVEL% EQU 0 (
    echo ✅ Compilacao concluida com sucesso!
    echo 🚀 Iniciando o Jogo...
    echo.
    
    :: 4. Executa habilitando preview
    java --enable-preview --module-path bin -m hangman/hangman.Main
) else (
    echo ❌ Erro na compilacao. Certifique-se de ter o JDK 25 instalado e no PATH.
    pause
)

endlocal
