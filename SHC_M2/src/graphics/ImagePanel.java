package graphics;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private Image image = null;

    public ImagePanel(String filename) {
        this.image = new ImageIcon(filename).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 180, 0, image.getWidth(null), image.getHeight(null), null);
    }
}
