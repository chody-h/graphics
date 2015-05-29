package CS355.LWJGL;

//You might notice a lot of imports here.
//You are probably wondering why I didn't just import org.lwjgl.opengl.GL11.*
//Well, I did it as a hint to you.
//OpenGL has a lot of commands, and it can be kind of intimidating.
//This is a list of all the commands I used when I implemented my project.
//Therefore, if a command appears in this list, you probably need it.
//If it doesn't appear in this list, you probably don't.
//Of course, your milage may vary. Don't feel restricted by this list of imports.
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.util.glu.GLU.gluPerspective;

// AA
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glHint;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.GL_LINE_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_LINE_SMOOTH_HINT;
import static org.lwjgl.opengl.GL11.GL_DONT_CARE;

/**
 *
 * @author Brennan Smith
 */
public class StudentLWJGLController implements CS355LWJGLController {
	
	// the exact location of the camera
	Point3D myLocation = new Point3D(0, 0, 0);
	// relative to the camera, this is a unit vector that represents the direction the camera is facing (as if the camera was always at (0, 0, 0))
	Point3D myFacing = new Point3D(1, 0, 0);
	// the model
	HouseModel m = new HouseModel();

	// This is a model of a house.
	// It has a single method that returns an iterator full of Line3Ds.
	// A "Line3D" is a wrapper class around two Point2Ds.
	// It should all be fairly intuitive if you look at those classes.
	// If not, I apologize.
	private WireFrame model = new HouseModel();

	// This method is called to "resize" the viewport to match the screen.
	// When you first start, have it be in perspective mode.
	@Override
	public void resizeGL() {

	}

	@Override
	public void update() {

	}

	// This is called every frame, and should be responsible for keyboard
	// updates.
	// An example keyboard event is captured below.
	// The "Keyboard" static class should contain everything you need to finish
	// this up.	
	@Override
	public void updateKeyboard() {
//		a	Move left
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			System.out.println("You are pressing A!");
		}
//		d	Move right
		else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			System.out.println("You are pressing D!");
		}
//		w	Move forward
		else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			System.out.println("You are pressing W!");
		}
//		s	Move backward
		else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			System.out.println("You are pressing S!");
		}
//		q	Turn left
		else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			System.out.println("You are pressing Q!");
		}
//		e	Turn right
		else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			System.out.println("You are pressing E!");
		}
//		r	Move up
		else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			System.out.println("You are pressing R!");
		}
//		f	Move down
		else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			System.out.println("You are pressing F!");
		}
//		h	Return to the original “home” position and orientation
		else if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
			System.out.println("You are pressing H!");
		}
//		o	Switch to orthographic projection
		else if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			System.out.println("You are pressing O!");
		}
//		p	Switch to perspective projection
		else if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			System.out.println("You are pressing P!");
		}
	}

	// This method is the one that actually draws to the screen.
	@Override
	public void render() {
		// This clears the screen.
		glClear(GL_COLOR_BUFFER_BIT);

		// line color
		glColor3f(0.0f, 0.5f, 0.5f);
		glLineWidth(1.5f);
		
		// AA
		glEnable(GL_LINE_SMOOTH);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glHint(GL_LINE_SMOOTH_HINT, GL_DONT_CARE);
		
		// draw testing
		glPushMatrix();
		glRotatef(0.0f, 0.0f, 0.0f, 0.1f);
		glBegin(GL_LINES);
			glVertex3d(0.0, 0.0, 0.0);
			glVertex3d(-1.0, 1.0, 1.0);
		glEnd();

		// Do your drawing here.
		Iterator<Line3D> it = m.getLines();
		while (it.hasNext()) {
			Line3D l = it.next();
//			System.out.println(l);
		}
	}

}
