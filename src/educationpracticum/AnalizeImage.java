/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package educationpracticum;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.util.*;


import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.IplImage;

/**
 *
 * @author nazarov
 * 
 * тут использую еще библиотеки захвата изоброжния с Веб камеры
 * 
 */
public class AnalizeImage {
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                FrameIMG frame = new FrameIMG();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
 
}
class FrameIMG extends JFrame
{
    public FrameIMG()
    {
        setTitle("ImageAnalize");
        
         
        // Добавление компонента к фрейму.
        JComponentIMG component = new JComponentIMG();
        setSize(component.widthIMG(), component.heightIMG()); // вытаскиваем размер из считанного
        add(component);
    }
    
    
}


class JComponentIMG extends JComponent
{
    int imageWidth;
    int imageHeight;
                
    public JComponentIMG()
    {
        // Получаем изображения.
        try
        {
            BufferedImage imageTmp = ImageIO.read(new File("myimage.png"));
            image = analizeImg(imageTmp); // передаем на анализ и возращаем измененное
            
            if(image == null) return;
            imageWidth = image.getWidth(this);
            imageHeight = image.getHeight(this);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void paintComponent(Graphics g)
    {    
        // Отображение рисунка в левом верхнем углу.
        g.drawImage(image, 0, 0, null);
    }
    private Image image;
    
    
    // --- возвращаем размер изображения ---
    public int widthIMG(){
        return imageWidth;
    }
    public int heightIMG(){
        return imageHeight;
    }
    
      // --- анализ изображения(мешаю цвета) ---
     BufferedImage analizeImg(BufferedImage image) {
        //System.out.println("orig_size: " + image.getWidth() + "_" + image.getHeight() + "half_size: " + image.getWidth() / 2 + "_" + image.getHeight() / 2);
        BufferedImage buffImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        final int w = image.getWidth();
        final int h = image.getHeight();
        int[] strTmp = new int[w]; // временный массив
        
        // в 4 раза уменьшает а не в 2
        for (int y = 0; y < h; ++y) {
            
            for (int x = 0; x < w; ++x) {
                
                // сокращаем в два раза
                int c = image.getRGB(x, y);
                strTmp[x] = c;
                int red = (c & 0x00ff0000) >> 16;
                int green = (c & 0x0000ff00) >> 8;
                int blue = c & 0x000000ff;
                int hz = c & 0xff0000 >> 24; // прозрачности
                
                Color color = null;
                //color = new Color(red, green, blue); // оригинальный цвет
                //Color color = new Color(green, red, blue);
                //Color color = new Color(blue, green, red);
                color = new Color(red, blue, green);
                //System.out.println("red-" + red +  "green-" + green + "blue-"+ blue);
                buffImage.setRGB(x, y, color.getRGB());
                
            }
            Arrays.sort(strTmp);
            for (int x = 0; x < w; ++x) {
                buffImage.setRGB(x, y, strTmp[x]);
            }

        }
        return buffImage;
    }
     
      // --- Метод преобразовывания изображения ( размер , вырезать и добавить данные)---
    private BufferedImage BufferedImage(BufferedImage img, int newW, int newH) {

        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
