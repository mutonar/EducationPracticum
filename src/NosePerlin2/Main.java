/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NosePerlin2;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Main {

    private JFrame frame;

    public static void main(String[] args) {
        String _hz = "1000";
        new Main().createFrame(500, 500, 2, Long.decode(_hz));
    }

    private void createFrame(int width, int height, int cellSize, long seed) {
        frame = new JFrame("Perlin noise 2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(new getJPanelMap(width, height, cellSize, seed)); // генерация панели с изображением
        //GenerationIMG _generationIMG = new GenerationIMG();
        //frame.getContentPane().add(_generationIMG.getJpanelWithIMG(width, height, cellSize, seed)); // 
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class getJPanelMap extends JPanel {

        private int[][] map;
        private int widthInCell;
        private int heightInCell;
        private int cellSize;
        private long seed;

        getJPanelMap(int width, int height, int cellSize, long seed) {
            setLayout(null);
            setPreferredSize(new Dimension(width, height));

            widthInCell = width / cellSize;
            heightInCell = height / cellSize;
            this.cellSize = cellSize;
            this.seed = seed;

            map = new int[widthInCell][heightInCell];

            Perlin2DPrimer perlin = new Perlin2DPrimer(seed); // главное это seed что за параметр
            for (int x = 0; x < widthInCell; x++) {
                for (int y = 0; y < heightInCell; y++) {
                    float value = perlin.getNoise(x / 100f, y / 100f, 8, 0.5f);
                    map[x][y] = (int) (value * 255) & 255;
                    map[x][y] = (int)(value * 255 + 128) & 255;
                }
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
           
            // тестовый вызов 
            GenerationIMG _generationIMG = new GenerationIMG();
            g.drawImage(_generationIMG.getIMG_Perlin(widthInCell, heightInCell, cellSize, seed), 0, 0, null);  

//            for (int x = 0; x < widthInCell; ++x) {
//                for (int y = 0; y < heightInCell; ++y) {
//                    Rectangle2D cell = new Rectangle2D.Float(x * cellSize, y * cellSize,
//                            cellSize, cellSize);
//                    g2d.setColor(new Color(map[x][y], map[x][y], map[x][y]));
//                    g2d.fill(cell);
//                }
//            }

        }

    }

}
