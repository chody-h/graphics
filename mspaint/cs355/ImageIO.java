/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author talonos
 */
public class ImageIO 
{
    static JFileChooser fileChooser = new JFileChooser(".");
    
    public static BufferedImage openImage()
    {
        try
	{
            int val = fileChooser.showOpenDialog(CS355Frame.inst());
		
            if (val == JFileChooser.APPROVE_OPTION)
            {
		File file = fileChooser.getSelectedFile();
			
		BufferedImage img = javax.imageio.ImageIO.read(file);
				
		if (img == null) throw new Exception("unable to read image");
				
		return img;
            }
	}
	catch (Exception e)
	{
            e.printStackTrace();
	}
		
	return null;
    }
	
	public static void saveImage(BufferedImage img)
	{
		try
		{
			int val = fileChooser.showSaveDialog(CS355Frame.inst());
			
			if (val == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				int dot = file.getName().lastIndexOf('.');
				String suffix = file.getName().substring(dot + 1);
				ImageWriter writer = javax.imageio.ImageIO.getImageWritersBySuffix(suffix).next();
				ImageOutputStream out = javax.imageio.ImageIO.createImageOutputStream(file);
				writer.setOutput(out);
				writer.write(img);
				out.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
