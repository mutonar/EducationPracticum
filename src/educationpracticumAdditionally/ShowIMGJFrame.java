/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package educationpracticumAdditionally;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 *
 * @author nazarov
 */

// =============  Просто вывод изображения на акран в нескольких итерациях ==================
public class ShowIMGJFrame {
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ImageFrame frame = new ImageFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
 
}
class ImageFrame extends JFrame
{
    public ImageFrame()
    {
        setTitle("ImageTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
         
        // Добавление компонента к фрейму.
         
        ImageComponent component = new ImageComponent();
        add(component);
    }
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
}
class ImageComponent extends JComponent
{
    public ImageComponent()
    {
        // Получаем изображения.
        try
        {
            image = ImageIO.read(new File("myimage_7.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void paintComponent(Graphics g)
    {
        if(image == null) return;
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
         
        // Отображение рисунка в левом верхнем углу.
        g.drawImage(image, 0, 0, null);
         
        // Многократный вывод изображения в панели.
         
        for(int i = 0; i * imageWidth <= getWidth(); i++)
         for(int j = 0; j * imageHeight <= getHeight(); j++)
             if(i + j > 0)
       g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
    }
    private Image image;
}
