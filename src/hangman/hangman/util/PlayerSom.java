package hangman.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe responsável por gerenciar a reprodução de áudio
 */
public class PlayerSom {
    private Clip clip;

    /**
     * Reproduzir áudios
     * 
     * @param diretorio diretório do arquivo de áudio
     * @param loop define se o áudio será reproduzido em loop
     */
    public void play(String diretorio, boolean loop) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        if (clip != null) {
            clip.close();
        }

        File arquivoSom = new File(diretorio);
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivoSom.getAbsoluteFile())) {
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.start();
        }
    }
}
