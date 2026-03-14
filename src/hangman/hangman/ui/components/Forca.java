package hangman.ui.components;

import java.util.List;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe responsável por renderizar a forca
 */
public class Forca extends JPanel {

    private static final List<String> CAMINHOS_IMAGENS = List.of(
        "img/forca1.png", "img/forca2.png", "img/forca3.png", "img/forca4.png",
        "img/forca5.png", "img/forca6.png", "img/forca7.png"
    );

    private final JLabel imgForca = new JLabel();
    private static final int LARGURA_IMG = 220;
    private static final int ALTURA_IMG = 220;
    
    /**
     * Constrói a classe
     */
    public Forca(){
        setPreferredSize(new Dimension(LARGURA_IMG, ALTURA_IMG));
        imgForca.setPreferredSize(new Dimension(LARGURA_IMG, ALTURA_IMG));
        add(imgForca);
        atualizar(0);
    }

    /**
     * Atualiza a imagem da forca conforme a quantidade de erros
     * 
     * @param erros quantidade de erros
     */
    public void atualizar(int erros) {
        if (erros >= 0 && erros < CAMINHOS_IMAGENS.size()) {
            ImageIcon icon = new ImageIcon(CAMINHOS_IMAGENS.get(erros));
            Image img = icon.getImage().getScaledInstance(LARGURA_IMG, ALTURA_IMG, Image.SCALE_SMOOTH);
            imgForca.setIcon(new ImageIcon(img));
        }
    }
}
