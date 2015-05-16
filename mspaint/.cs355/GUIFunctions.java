/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Talonos
 */
public class GUIFunctions 
{
    /**
     * Refreshes the view by repainting the canvas. Call this any time the picture in the
     * main view should change.
     */
    public static void refresh()
    {
        RedrawRoutine.inst().refreshView();
    }
    
    /**
     * Changes the main view so that the given color is shown in the color selector box.
     * @param c the color to show in the color selector box.
     */
    public static void changeSelectedColor(Color c)
    {
        CS355Frame.inst().setSelectedColor(c);
    }
    
    /**
     * Changes the amount the vertical scroll bar returns when it is all the way at the top.
     * @param newMin the new value to return when the vertical scrollbar is all the way at the top.
     */
    public static void setVScrollBarMin(int newMin)
    {
        CS355Frame.inst().setScrollAttribute(CS355SScrollbarAttrConsts.V_SCROLL_BAR, CS355SScrollbarAttrConsts.MIN, newMin);
    }
    
    /**
     * Changes the amount the horizontal scroll bar returns when it is all the way at the right.
     * @param newMin the new value to return when the horizontal scrollbar is all the way at the right.
     */
    public static void setHScrollBarMin(int newMin)
    {
        CS355Frame.inst().setScrollAttribute(CS355SScrollbarAttrConsts.H_SCROLL_BAR, CS355SScrollbarAttrConsts.MIN, newMin);
    }
    
    /**
     * Changes the amount the vertical scroll bar returns when it is all the way at the bottom.
     * Important: See setVScrollBarKnob for further clarification!
     * @param newMin the new value to return when the horizontal scrollbar is all the way at the right.
     */
    public static void setVScrollBarMax(int newMax)
    {
        CS355Frame.inst().setScrollAttribute(CS355SScrollbarAttrConsts.V_SCROLL_BAR, CS355SScrollbarAttrConsts.MAX, newMax);
    }
    
    /**
     * Changes the amount the horizontal scroll bar returns when it is all the way at the left.
     * Important: See setHScrollBarKnob for further clarification!
     * @param newMin the new value to return when the horizontal scrollbar is all the way at the left.
     */
    public static void setHScrollBarMax(int newMax)
    {
        CS355Frame.inst().setScrollAttribute(CS355SScrollbarAttrConsts.H_SCROLL_BAR, CS355SScrollbarAttrConsts.MAX, newMax);
    }
    
    /**
     * Sets the width of the scroll bar's knob. This is not just purely visual! If you have, for example, a knob
     * width of 2 and a maximum value of 4, then the knob will "fill" the last half of the scroll bar and return
     * a value of 2 if pushed all the way to the left!
     * @param newKnob the new width of the knob.
     */
    public static void setVScrollBarKnob(int newKnob)
    {
        CS355Frame.inst().setScrollAttribute(CS355SScrollbarAttrConsts.V_SCROLL_BAR, CS355SScrollbarAttrConsts.KNOB, newKnob);
    }
    
    /**
     * Sets the width of the scroll bar's knob. This is not just purely visual! If you have, for example, a knob
     * width of 2 and a maximum value of 4, then the knob will "fill" the last half of the scroll bar and return
     * a value of 2 if pushed all the way to the left!
     * @param newKnob the new width of the knob.
     */
    public static void setHScrollBarKnob(int newKnob)
    {
        CS355Frame.inst().setScrollAttribute(CS355SScrollbarAttrConsts.H_SCROLL_BAR, CS355SScrollbarAttrConsts.KNOB, newKnob);
    }
    
    /**
     * Sets the position of the scroll bar's knob. I do not believe this will trigger a 
     * scrollbar changed event in your controller, but I could be wrong.
     * @param newKnob the new position of the knob.
     */
    public static void setHScrollBarPosit(int newPosit)
    {
        CS355Frame.inst().setScrollAttribute(CS355SScrollbarAttrConsts.H_SCROLL_BAR, CS355SScrollbarAttrConsts.POSIT, newPosit);
    }
    
    /**
     * Sets the position of the scroll bar's knob. I do not believe this will trigger a 
     * scrollbar changed event in your controller, but I could be wrong.
     * @param newKnob the new position of the knob.
     */
    public static void setVScrollBarPosit(int newPosit)
    {
        CS355Frame.inst().setScrollAttribute(CS355SScrollbarAttrConsts.V_SCROLL_BAR, CS355SScrollbarAttrConsts.POSIT, newPosit);
    }

    /**
     * Creates a new CS355Frame. This is probably about the first thing you should call.
     * @param inst the CS 355Controller you wish to receive button events.
     * @param viewRefresher The view refresher used to draw the canvas.
     * @param mouseListener The mouseListener that will be sent mouse events from the canvas.
     * @param mouseMotionListener The Mouse Motion Listener that will be sent events from the canvas.
     */
    public static void createCS355Frame(CS355Controller inst, ViewRefresher viewRefresher, 
            MouseListener mouseListener, MouseMotionListener mouseMotionListener) 
    {
        CS355Frame.createCS355Frame(inst, viewRefresher, mouseListener, mouseMotionListener);
    }
}
