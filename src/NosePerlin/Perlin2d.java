/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package NosePerlin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author nazarov
 */
public class Perlin2d
{
    private float[] topLeftGradient;
    private float[] topRightGradient;
    private float[] bottomLeftGradient;
    private float[] bottomRightGradient;
    private int sizeSideQuard;
    
    public Perlin2d(int sizeSideQuard){
        this.sizeSideQuard = sizeSideQuard;
        // извлекаем градиентные векторы для всех вершин квадрата:
        topLeftGradient     = GetPseudoRandomGradientVector( );
        topRightGradient    = GetPseudoRandomGradientVector( );
        bottomLeftGradient  = GetPseudoRandomGradientVector(  );
        bottomRightGradient = GetPseudoRandomGradientVector(  );
    }
        
  public float Noise(int x, int y)
  {     //рисунок размер с верху в низ
      
        // сразу находим координаты левой верхней вершины квадрата
        // округление
        /*
        int left = (int)Math.floor(x);
        int top  = (int)Math.floor(y); 
        */
        // а теперь локальные координаты точки внутри квадрата
        float pointInQuadX = x;
        float pointInQuadY = y;
      
        // вектора от вершин квадрата до точки внутри квадрата:
        float[] distanceToTopLeft     = new float[]{ pointInQuadX, pointInQuadY };
        float[] distanceToTopRight    = new float[]{ sizeSideQuard-pointInQuadX, pointInQuadY   };
        float[] distanceToBottomLeft  = new float[]{ pointInQuadX,   sizeSideQuard-pointInQuadY };
        float[] distanceToBottomRight = new float[]{ sizeSideQuard-pointInQuadX, sizeSideQuard-pointInQuadY };

        // считаем скалярные произведения между которыми будем интерполировать
        /*
        tx1--tx2
        |    |
        bx1--bx2
        */
        float tx1 = Dot(distanceToTopLeft,     topLeftGradient);
        float tx2 = Dot(distanceToTopRight,    topRightGradient);
        float bx1 = Dot(distanceToBottomLeft,  bottomLeftGradient);
        float bx2 = Dot(distanceToBottomRight, bottomRightGradient);

        // готовим параметры интерполяции, чтобы она не была линейной:(попробовать )
        pointInQuadX = QunticCurve(pointInQuadX);
        pointInQuadY = QunticCurve(pointInQuadY);

        // интерполяция :
        float tx = Lerp(tx1, tx2, pointInQuadX);
        float bx = Lerp(bx1, bx2, pointInQuadX);
        float tb = Lerp(tx, bx, pointInQuadY);

        // возвращаем результат:
        return tb;
  }
  
  
    public BufferedImage getIMG() {
        final int w = sizeSideQuard;
        final int h = sizeSideQuard;
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        int minNose = 0;
        int maxNoise = 0;
        int[][] colodDeep = new int[sizeSideQuard][sizeSideQuard];
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                int nose = (int)Noise(x, y);
                colodDeep[y][x] = nose;
                
                if (nose < 0) {
                    if(nose < minNose){
                        minNose = nose;
                    }
                } else {
                    if(nose > maxNoise){
                        maxNoise = nose;
                    }
                }
            }
        }
        
        System.out.println(minNose + " _ " + maxNoise);
        long deffenceColorAndNose;
        if(minNose < 0){
            minNose = -minNose; // что не так с этим числом блядь? почему он 
            minNose = ~minNose; // что не так с этим числом блядь? почему он 
            int minNoseTmp = minNose * -1;
            int minNoseTmp2 = minNose  >> 32;
            int m = -2147483648; //  на последних числах не работает эта операция
            System.out.println(Integer.toBinaryString(minNose));
            long m1 = -m; // 
            minNoseTmp = ~minNose;
            minNose = 0; //  а это то сбрасывает
            System.out.println(Integer.toBinaryString(minNoseTmp));
        }
        
        deffenceColorAndNose = (minNose + maxNoise) / 255; // в такое раз отличается от максимального градиента цвета
        System.out.println("deffenceColorAndNose = " + deffenceColorAndNose);
        
        long[][] colorArr = new long[sizeSideQuard][sizeSideQuard];
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                long colorNose = colodDeep[y][x];
                if(colorNose != 0){
                    colorNose = (colorNose + minNose )/deffenceColorAndNose;
                }
                colorArr[y][x] = colorNose;
            }
        }


        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                //int c = bufferedImage.getRGB(x, y);
                /*int red = (c &0x00ff0000) >> 16;
                int green = (c & 0x0000ff00) >> 8;
                int blue = c & 0x000000ff;
                        */
                int red = 0x00ff0000;
                int green = 0x0000ff00;
                //int blue = ThreadLocalRandom.current().nextInt(0, 255); 
                //int alpha = ThreadLocalRandom.current().nextInt(0, 255); // почему прозрачность не работет?
                
                if(colorArr[y][x] <= 255){
                    Color color = new Color(0, 0, colorArr[y][x]);                
                    bufferedImage.setRGB(x, y, color.getRGB());
                }else{
                    System.out.println("error err _ " + colorArr[y][x]);
                }
            }
        }
        
        return bufferedImage;
    }
    
    static float Lerp(float a, float b, float t) {
        /*
        линенйная интерполяция
        a - значение левой координаты
        b - значение правой координаты
        t - локальная координата
        */
        
//      return a * (t - 1) + b * t; можно переписать с одним умножением (раскрыть скобки, взять в другие скобки):
        return a + (b - a) * t;
    }
    
    static float QunticCurve(float t) {
        // модифицирование локальной координаты
        return t * t * t * (t * (t * 6 - 15) + 10);
    }
    
    static float CosinusCurve(float t) {
        // модифицирование локальной координаты
        return (float) ((1 - Math.cos(t * Math.PI)) / 2);
    }
    
    float[] GetPseudoRandomGradientVector()
    {
        // псевдо-случайное число от 0 до 3 которое всегда неизменно при данных x и y
        // используются только прямые углы
        int v = ThreadLocalRandom.current().nextInt(0, 3);

        switch (v)
        {
            case 0:  return new float[]{  1, 0 };
            case 1:  return new float[]{ -1, 0 };
            case 2:  return new float[]{  0, 1 };
            default: return new float[]{  0,-1 };
        }
    }
     
    
    static float Dot(float[] a, float[] b)
    {
        //скалярное произведение векторов в системе координат:
        return a[0] * b[0] + a[1] * b[1];
    } 
}