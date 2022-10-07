/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package NosePerlin2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

class ImageComponent extends JComponent {
    private Image image;
    
    public ImageComponent(BufferedImage img) {
        if (img != null) {
            image = img;
        } else {
            // Получаем изображения.
            try {
                image = ImageIO.read(new File("noIMG.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        if (image == null) {
            return;
        }
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        // Отображение рисунка в левом верхнем углу.
        g.drawImage(image, 0, 0, null);
    }
    
}
