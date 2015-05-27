package CS355.LWJGL;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.glClearColor;

/**
 * @author Brennan Smith, modified from code by jediTofu.
 * @see <a href="http://lwjgl.org/">LWJGL Home Page</a>
 */
public class LWJGLSandbox {
  public static final int DISPLAY_HEIGHT = 480;
  public static final int DISPLAY_WIDTH = 640;
  public static final Logger LOGGER = Logger.getLogger(LWJGLSandbox.class.getName());

  static {
    try {
      LOGGER.addHandler(new FileHandler("errors.log",true));
    }
    catch(IOException ex) {
      LOGGER.log(Level.WARNING,ex.toString(),ex);
    }
  }

  public LWJGLSandbox() 
  {
  }
  
  public CS355LWJGLController c;

  public void create(CS355LWJGLController c) throws LWJGLException 
  {       
    this.c=c;
    
    //Display
    Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH,DISPLAY_HEIGHT));
    Display.setFullscreen(false);
    Display.setTitle("CS355 LWJGL Framework - BYU");
    Display.create();

    //Keyboard
    Keyboard.create();

    //OpenGL
    initGL();
    c.resizeGL();
  }

  public void destroy() {
    //Methods already check if created before destroying.
    Mouse.destroy();
    Keyboard.destroy();
    Display.destroy();
  }

  public void initGL() {
    //2D Initialization
    glClearColor(0.0f,0.0f,0.0f,0.0f);
  }

  public void processKeyboard() 
  {
      c.updateKeyboard();
  }
  
  public void render() 
  {
      c.render();
  }

  public void run() {
    while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      if(Display.isVisible()) {
        processKeyboard();
        update();
        render();
      }
      else {
        if(Display.isDirty()) {
          render();
        }
        try {
          Thread.sleep(100);
        }
        catch(InterruptedException ex) {
        }
      }
      Display.update();
      Display.sync(60);
    }
  }

  public void update() 
  {
      c.update();
  }
}
