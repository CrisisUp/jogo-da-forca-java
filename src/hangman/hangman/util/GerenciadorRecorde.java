package hangman.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Gerencia a persistência do melhor tempo do jogador.
 */
public final class GerenciadorRecorde {

    private static final Path RECORD_FILE = Path.of("data", "recorde.txt");

    private GerenciadorRecorde() {}

    /**
     * Retorna o recorde atual em segundos. Retorna Long.MAX_VALUE se não houver recorde.
     */
    public static long lerMelhorTempo() {
        try {
            if (Files.exists(RECORD_FILE)) {
                String conteudo = Files.readString(RECORD_FILE).trim();
                return Long.parseLong(conteudo);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("⚠️ Erro ao ler recorde: " + e.getMessage());
        }
        return Long.MAX_VALUE;
    }

    /**
     * Salva um novo recorde em segundos.
     */
    public static void salvarNovoRecorde(long segundos) {
        try {
            Files.writeString(RECORD_FILE, String.valueOf(segundos));
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar recorde: " + e.getMessage());
        }
    }

    /**
     * Formata segundos para exibição amigável.
     */
    public static String formatarTempo(long segundos) {
        if (segundos == Long.MAX_VALUE) return "Nenhum";
        return segundos < 60 ? segundos + "s" : (segundos / 60) + "m " + (segundos % 60) + "s";
    }
}
