package hangman.ui.components;

import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import hangman.ui.InterfaceGrafica;
import hangman.logic.Jogo;

/**
 * Classe responsável por renderizar o teclado
 */
public class Teclado extends JPanel {

    private final Jogo jogo;
    public final List<Tecla> teclaArray = new ArrayList<>();

    /**
     * Classe reponsável por renderizar cada tecla do teclado
     */
    public static class Tecla extends JButton {
        public Tecla(String letra) {
            setSize(15, 15);
            setText(letra);
            setBackground(new Color(60, 60, 60)); // Cinza escuro
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        }
    }

    private static final List<String> ALFABETO = List.of(
            "A", "B", "C", "Ç", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    );

    /**
     * Constrói a classe
     * 
     * @param jogo instância do Jogo
     */
    public Teclado(Jogo jogo) {
        this.jogo = jogo;
        // Usa GridLayout para organizar as 27 teclas em 3 linhas de 9
        setLayout(new GridLayout(3, 9, 5, 5));
        // Aumenta a altura preferencial do teclado para melhor visibilidade
        setPreferredSize(new Dimension(InterfaceGrafica.LARGURA - 40, 160));
        setMaximumSize(new Dimension(InterfaceGrafica.LARGURA - 40, 160));

        ALFABETO.forEach(letra -> {
            Tecla tecla = new Tecla(letra);
            tecla.setFont(new Font("SansSerif", Font.BOLD, 16)); // Fonte um pouco maior
            tecla.setMargin(new Insets(5, 5, 5, 5));
            teclaArray.add(tecla);
            add(tecla);

            tecla.addActionListener(e -> {
                tecla.setEnabled(false);
                try {
                    jogo.novaTentativa(tecla.getText());
                    jogo.verificaStatus();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            });
        });
    }
}
