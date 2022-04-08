/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package educationpracticumAdditionally;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author nazarov
 */
public class ResizerDataIMG {
        public BufferedImage getDataResisengScreen(Image image, int N_WIDTH, int N_HEIGHT){
        
        int afterResizeWIDTH = image.getWidth(null) / (image.getWidth(null) / N_WIDTH);
        int afterResizeHEIGHT = image.getHeight(null) / (image.getHeight(null) / N_HEIGHT);
        
        image = image.getScaledInstance(afterResizeWIDTH, afterResizeHEIGHT, Image.SCALE_SMOOTH);
        
        BufferedImage bi = new BufferedImage(afterResizeWIDTH, afterResizeHEIGHT, BufferedImage.TYPE_INT_ARGB);
        
        bi.getGraphics().drawImage(image, 0, 0, afterResizeWIDTH, afterResizeHEIGHT, null);
        
        return bi;
    }
}
