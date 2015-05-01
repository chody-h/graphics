/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 *
 * @author Talonos
 */
public interface CS355Controller 
{

    void colorButtonHit(Color c);
    void triangleButtonHit();
    void squareButtonHit();
    void rectangleButtonHit();
    void circleButtonHit();
    void ellipseButtonHit();

    public void lineButtonHit();

    public void selectButtonHit();

    public void zoomInButtonHit();

    public void zoomOutButtonHit();

    public void hScrollbarChanged(int value);

    public void vScrollbarChanged(int value);

    public void toggle3DModelDisplay();

    public void keyPressed(Iterator<Integer> iterator);

    public void doEdgeDetection();

    public void doSharpen();

    public void doMedianBlur();

    public void doUniformBlur();

    public void doChangeContrast(int contrastAmountNum);

    public void doChangeBrightness(int brightnessAmountNum);

    public void doLoadImage(BufferedImage openImage);

    public void toggleBackgroundDisplay();
}
