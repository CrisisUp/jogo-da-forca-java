package hangman.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Classe utilitária para manipulação de arquivos.
 */
public final class ManipuladorTXT {

    private static final Pattern DIACRITICS = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    private static final Path DATA_DIRECTORY = Path.of("data").toAbsolutePath().normalize();

    private ManipuladorTXT() {}

    public static List<String> lerArquivoDeTexto(String nomeArquivo) {
        try {
            Path caminhoAlvo = DATA_DIRECTORY.resolve(nomeArquivo).normalize();
            if (!caminhoAlvo.startsWith(DATA_DIRECTORY) || !Files.exists(caminhoAlvo)) return List.of();

            try (var linhas = Files.lines(caminhoAlvo)) {
                return linhas.map(ManipuladorTXT::sanitizarLinha)
                             .filter(s -> !s.isBlank())
                             .toList(); // Retorna lista imutável eficiente
            }
        } catch (IOException | SecurityException e) {
            return List.of();
        }
    }

    public static String sanitizarLinha(String linha) {
        if (linha == null) return "";
        return DIACRITICS.matcher(Normalizer.normalize(linha, Normalizer.Form.NFD))
                         .replaceAll("")
                         .toLowerCase()
                         .trim();
    }
}
