package hangman.ui;

import javax.sound.sampled.*;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.Duration;
import java.time.Instant;

import hangman.ui.components.Forca;
import hangman.ui.components.Teclado;
import hangman.logic.Jogo;
import hangman.util.PlayerSom;
import hangman.util.GerenciadorRecorde;

/**
 * Interface Gráfica Ultra-Moderna 2026.
 */
public class InterfaceGrafica {

    public static final int LARGURA = 600, ALTURA = 700;
    
    private static final Color COR_FUNDO = new Color(28, 28, 28);
    private static final Color COR_TEXTO_PRIMARIO = new Color(245, 245, 245);
    private static final Color COR_DESTAQUE = new Color(110, 190, 255);

    private String palavraOriginal;
    private static final PlayerSom player = new PlayerSom();
    private final Jogo jogo;

    private final JFrame tela = new JFrame("Jogo da Forca 2026");
    private final JLabel componenteCronometro = new JLabel("⏱️ Tempo: 00:00");
    private final JLabel componenteRecorde = new JLabel("🏆 Melhor: --:--");
    private final JLabel componenteDica = new JLabel();
    public final Forca componenteForca = new Forca();
    private final JLabel componentePalavra = new JLabel();
    private final Teclado componenteTeclado;

    private Timer timerSwing;
    private Instant tempoInicio;

    public InterfaceGrafica(Jogo jogo) {
        this.jogo = jogo;
        this.componenteTeclado = new Teclado(this.jogo);
        
        configurarComponentes();
        configurarJanela();
        configurarListeners();
        atualizarExibicaoRecorde();
    }

    private void configurarComponentes() {
        componenteCronometro.setAlignmentX(Component.CENTER_ALIGNMENT);
        componenteCronometro.setFont(new Font("Monospaced", Font.BOLD, 18));
        componenteCronometro.setForeground(new Color(180, 180, 180));

        componenteRecorde.setAlignmentX(Component.CENTER_ALIGNMENT);
        componenteRecorde.setFont(new Font("Monospaced", Font.BOLD, 14));
        componenteRecorde.setForeground(new Color(255, 215, 0));

        componenteDica.setAlignmentX(Component.CENTER_ALIGNMENT);
        componenteDica.setFont(new Font("SansSerif", Font.BOLD, 22));
        componenteDica.setForeground(COR_TEXTO_PRIMARIO);

        componenteForca.setAlignmentX(Component.CENTER_ALIGNMENT);
        componenteForca.setBackground(COR_FUNDO);

        componentePalavra.setAlignmentX(Component.CENTER_ALIGNMENT);
        componentePalavra.setFont(new Font("Monospaced", Font.BOLD, 45));
        componentePalavra.setForeground(COR_DESTAQUE);

        componenteTeclado.setAlignmentX(Component.CENTER_ALIGNMENT);
        componenteTeclado.setBackground(COR_FUNDO);

        timerSwing = new Timer(1000, e -> {
            if (tempoInicio != null) {
                long seg = Duration.between(tempoInicio, Instant.now()).toSeconds();
                componenteCronometro.setText(String.format("⏱️ Tempo: %02d:%02d", seg / 60, seg % 60));
            }
        });
    }

    private void configurarJanela() {
        tela.setSize(LARGURA, ALTURA);
        tela.setResizable(false);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = tela.getContentPane();
        c.setBackground(COR_FUNDO);
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

        tela.add(Box.createVerticalStrut(20));
        tela.add(componenteCronometro);
        tela.add(componenteRecorde);
        tela.add(Box.createVerticalStrut(10));
        tela.add(componenteDica);
        tela.add(Box.createVerticalStrut(15));
        tela.add(componenteForca);
        tela.add(Box.createVerticalStrut(15));
        tela.add(componentePalavra);
        tela.add(Box.createVerticalStrut(25));
        tela.add(componenteTeclado);
        tela.add(Box.createVerticalStrut(20));
    }

    private void configurarListeners() {
        tela.setFocusable(true);
        tela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String letra = String.valueOf(Character.toUpperCase(e.getKeyChar()));
                componenteTeclado.teclaArray.stream()
                    .filter(b -> b.getText().equals(letra) && b.isEnabled())
                    .findFirst().ifPresent(JButton::doClick);
            }
        });
    }

    public void atualizarExibicaoRecorde() {
        long recorde = GerenciadorRecorde.lerMelhorTempo();
        componenteRecorde.setText("🏆 Recorde: " + GerenciadorRecorde.formatarTempo(recorde));
    }

    public void setDadosIniciais(String pOrig, String pEsc, String dica) {
        this.palavraOriginal = pOrig;
        this.componentePalavra.setText(pEsc);
        this.componenteDica.setText("DICA: " + dica);
    }

    public void atualizarPalavra(String pEsc) {
        this.componentePalavra.setText(pEsc);
    }

    public void renderizar() {
        if (!tela.isVisible()) tela.setVisible(true);
        tela.requestFocusInWindow();
    }

    public void iniciarPartida() {
        tempoInicio = Instant.now();
        timerSwing.start();
        componenteTeclado.teclaArray.forEach(t -> t.setEnabled(true));
        componenteForca.atualizar(0);
        renderizar();
    }

    public void finalizarPartida(boolean venceu) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        timerSwing.stop();
        long tempoFinal = Duration.between(tempoInicio, Instant.now()).toSeconds();
        
        String som = venceu ? "sounds/vitoria.wav" : "sounds/derrota.wav";
        player.play(som, false);
        
        String msg = venceu ? "🌟 Venceu em " + GerenciadorRecorde.formatarTempo(tempoFinal) + "!" : "💀 Fim de jogo em " + GerenciadorRecorde.formatarTempo(tempoFinal) + ".\nA palavra era: " + palavraOriginal;

        if (venceu && tempoFinal < GerenciadorRecorde.lerMelhorTempo()) {
            GerenciadorRecorde.salvarNovoRecorde(tempoFinal);
            msg += "\n🏆 NOVO RECORDE MUNDIAL!";
            atualizarExibicaoRecorde();
        }

        final String msgFinal = msg + "\nDeseja jogar novamente?";
        SwingUtilities.invokeLater(() -> {
            if (JOptionPane.showConfirmDialog(tela, msgFinal, "Resultado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try { jogo.iniciar(); } catch (Exception e) { e.printStackTrace(); }
            } else System.exit(0);
        });
    }
}
