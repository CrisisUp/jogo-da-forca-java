package hangman;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hangman.logic.Jogo;
import hangman.util.ManipuladorTXT;
import hangman.util.PlayerSom;

public class Main {
    public static void main(String[] args) {
        // 1. Identifica Localização e Horário (Padrão 2026)
        Locale localizacao = Locale.getDefault();
        ZonedDateTime agora = ZonedDateTime.now();
        
        // Formatação de data/hora respeitando o país do usuário
        DateTimeFormatter formatador = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                                                        .withLocale(localizacao);
        
        String saudacao = obterSaudacao(agora.getHour());
        
        System.out.printf("🌍 Sistema iniciado em: %s (%s)%n", localizacao.getDisplayLanguage(), localizacao.getDisplayCountry());
        System.out.printf("⏰ Horário local: %s%n", agora.format(formatador));
        System.out.println("💬 " + saudacao);

        try {
            PlayerSom player = new PlayerSom();

            List<String> listaDePalavras = ManipuladorTXT.lerArquivoDeTexto("palavras.txt");
            List<String> listaDeDicas = ManipuladorTXT.lerArquivoDeTexto("dicas.txt");

            if (listaDePalavras.isEmpty() || listaDeDicas.isEmpty()) {
                System.err.println("Erro: Arquivos de dados vazios ou não encontrados.");
                return;
            }

            player.play("sounds/musicaFundo.wav", true);

            Jogo jogo = new Jogo(listaDePalavras, listaDeDicas);
            jogo.iniciar();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro fatal ao iniciar o jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retorna uma saudação baseada na hora do dia.
     */
    private static String obterSaudacao(int hora) {
        return switch (hora) {
            case int h when h >= 5 && h < 12 -> "Bom dia! ☀️";
            case int h when h >= 12 && h < 18 -> "Boa tarde! ⛅";
            case int h when h >= 18 && h < 24 -> "Boa noite! 🌙";
            default -> "Boa madrugada! 🌌";
        };
    }
}
