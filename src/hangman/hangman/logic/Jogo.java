package hangman.logic;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.List;
import java.util.Arrays;
import java.util.random.RandomGenerator;
import hangman.ui.InterfaceGrafica;

/**
 * Lógica do Jogo da Forca - Refatorado para Java 2026.
 */
public class Jogo {

    private String palavraOriginal;
    private char[] palavraEscondida;
    private int erros;
    private final List<String> listaPalavras, listaDicas;
    private final InterfaceGrafica graficos;
    private static final RandomGenerator RANDOM = RandomGenerator.of("L64X128MixRandom");

    public Jogo(List<String> listaPalavras, List<String> listaDicas) {
        this.listaPalavras = List.copyOf(listaPalavras);
        this.listaDicas = List.copyOf(listaDicas);
        this.graficos = new InterfaceGrafica(this);
    }

    public void iniciar() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.erros = 0;
        int idx = RANDOM.nextInt(listaPalavras.size());
        this.palavraOriginal = listaPalavras.get(idx);
        
        this.palavraEscondida = new char[palavraOriginal.length()];
        Arrays.fill(this.palavraEscondida, '*');

        graficos.setDadosIniciais(palavraOriginal, new String(palavraEscondida), listaDicas.get(idx));
        graficos.iniciarPartida();
    }

    public void novaTentativa(String letra) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (letra == null || letra.isEmpty()) return;
        
        char letraChar = Character.toLowerCase(letra.charAt(0));
        boolean existe = false;

        for (int i = 0; i < palavraOriginal.length(); i++) {
            if (palavraOriginal.charAt(i) == letraChar) {
                palavraEscondida[i] = letra.charAt(0);
                existe = true;
            }
        }

        if (!existe) {
            erros++;
            graficos.componenteForca.atualizar(erros);
        }

        graficos.atualizarPalavra(new String(palavraEscondida));
    }

    public void verificaStatus() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        boolean venceu = palavraOriginal.equals(new String(palavraEscondida).toLowerCase());
        if (venceu || erros >= 6) {
            graficos.finalizarPartida(venceu);
        }
    }
}
