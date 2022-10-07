/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package NosePerlin2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PopupMenu;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author nazarov
 */
public class GenerationIMG {
 
    public JPanel getJpanelWithIMG(int width, int height, int cellSize, long seed){
        JPanel _JPanel = new JPanel();
        _JPanel.setLayout(null);
        _JPanel.setPreferredSize(new Dimension(width, height));
        
        
        BufferedImage _Graphics2D = getIMG_Perlin(width, height, cellSize, seed);
        //_JPanel.paintComponent(_Graphics2D);
        return _JPanel;
    }
    
    
    private int[][] generationNozePerlin(int widthInCell, int heightInCell, long seed){
         int[][] map = new int[widthInCell][heightInCell];

            Perlin2DPrimer perlin = new Perlin2DPrimer(seed);
            for (int x = 0; x < widthInCell; x++) {
                for (int y = 0; y < heightInCell; y++) {
                    float value = perlin.getNoise(x / 100f, y / 100f, 8, 0.5f);
                    map[x][y] = (int) (value * 255) & 255;
                    map[x][y] = (int)(value * 255 + 128) & 255;
                }
            }
        return map;
    }
    
    public BufferedImage getIMG_Perlin(int width, int height, int cellSize, long seed) {
        int[][] map = generationNozePerlin(width, height, seed);
        
        int widthInCell = width / cellSize;
        int heightInCell = height / cellSize;

        BufferedImage g = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) g.createGraphics();

        for (int x = 0; x < widthInCell; ++x) {
            for (int y = 0; y < heightInCell; ++y) {
                Rectangle2D cell = new Rectangle2D.Float(x * cellSize, y * cellSize,
                        cellSize, cellSize);
                g2d.setColor(new Color(map[x][y], map[x][y], map[x][y]));
                g2d.fill(cell);
            }
        }

        g2d.dispose();
      
        // Сохранение 
//        File file = new File("myimageNoze.png");
//        try {
//            ImageIO.write(g, "png", file);
//        } catch (IOException ex) {
//            Logger.getLogger(GenerationIMG.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        return g;

        }

}
