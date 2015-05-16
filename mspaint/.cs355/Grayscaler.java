/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 *
 * @author talonos
 */
public class Grayscaler
{

    public static BufferedImage grayScale(BufferedImage inputImage)
    {
         int width = inputImage.getWidth();
         int height = inputImage.getHeight();
         
         //Create an output image.
         BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         
         //Get rasters for the input and output image.
         WritableRaster in = inputImage.getRaster();
         WritableRaster out = outputImage.getRaster();
         
         //For each pixel:
         for (int y = 0; y < height; y++)
         {
         for (int x = 0; x < width; x++)
         {
         //Take the red, green, and blue.
         double red = in.getSample(x, y, 0);
         double green = in.getSample(x, y, 1);
         double blue = in.getSample(x, y, 2);
         
        //Create a brightness value according to the formula you gave us.
         int gray = (int)(0.299*red) + (int)(0.587*green) + (int)(0.114*blue);
         
         //Write to the output.
         out.setSample(x, y, 0, gray);
         out.setSample(x, y, 1, gray);
         out.setSample(x, y, 2, gray);
         }
         }
         
         //Display the output image.        
         return outputImage;
         
         //Done.
    }     
}
