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
	
	// AA toggle
	boolean AA = false;
	
	// display height and width
	float h;
	float w;
	
	// move speed multiplier
	static float speed = 0.4f;

	// home location
	Point3D home = new Point3D(0, 6, 20);
	// home rotations
	float rot_home = 0;
	
	// the exact location of the camera
	Point3D myLocation = new Point3D(home.x, home.y, home.z);
	// the rotation of the camera in radians
	float rot = rot_home;

	// This is a model of a house.
	// It has a single method that returns an iterator full of Line3Ds.
	// A "Line3D" is a wrapper class around two Point2Ds.
	// It should all be fairly intuitive if you look at those classes.
	// If not, I apologize.
	private WireFrame model = new HouseModel();
	
	public StudentLWJGLController(float height, float width) {
		h = height;
		w = width;
	}

	// This method is called to "resize" the viewport to match the screen.
	// When you first start, have it be in perspective mode.
	@Override
	public void resizeGL() {
		
		// AA
		if (AA) {
			glEnable(GL_LINE_SMOOTH);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glHint(GL_LINE_SMOOTH_HINT, GL_DONT_CARE);
		}
		
		// projection mode
		PerspectiveMode();
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
//		RELATIVE
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
//			System.out.println("You are pressing A!");
			myLocation.x -= 1*speed * Math.sin(rot+Math.PI/2);
			myLocation.z += 1*speed * Math.cos(rot+Math.PI/2);
		}
//		d	Move right
//		RELATIVE
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
//			System.out.println("You are pressing D!");
			myLocation.x += 1*speed * Math.sin(rot+Math.PI/2);
			myLocation.z -= 1*speed * Math.cos(rot+Math.PI/2);
		}
//		w	Move forward
//		RELATIVE
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
//			System.out.println("You are pressing W!");
			myLocation.x += 1*speed * Math.sin(rot);
			myLocation.z -= 1*speed * Math.cos(rot);
		}
//		s	Move backward
//		RELATIVE
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
//			System.out.println("You are pressing S!");
			myLocation.x -= 1*speed * Math.sin(rot);
			myLocation.z += 1*speed * Math.cos(rot);
		}
//		q	Turn left
//		decrement y-rot
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
//			System.out.println("You are pressing Q!");
			rot = (float) ((rot - 3 * Math.PI/180 * speed) % (2*Math.PI));
		}
//		e	Turn right
//		increment y-rot
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
//			System.out.println("You are pressing E!");
			rot = (float) ((rot + 3 * Math.PI/180 * speed) % (2*Math.PI));
		}
//		r	Move up
//		increment y-dir
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
//			System.out.println("You are pressing R!");
			myLocation.y += 1*speed;
		}
//		f	Move down
//		decrement y-dir
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
//			System.out.println("You are pressing F!");
			myLocation.y -= 1*speed;
		}
//		h	Return to the original “home” position and orientation
//		reset to zeros
		if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
//			System.out.println("You are pressing H!");
			myLocation = new Point3D(home.x, home.y, home.z);
			rot = rot_home;
		}
//		o	Switch to orthographic projection
		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
//			System.out.println("You are pressing O!");
			OrthoMode();
		}
//		p	Switch to perspective projection
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
//			System.out.println("You are pressing P!");
			PerspectiveMode();
		}
	}

	// This method is the one that actually draws to the screen.
	@Override
	public void render() {
		// This clears the screen.
		glClear(GL_COLOR_BUFFER_BIT);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

		// world to camera transformation
        glRotatef((float)Math.toDegrees(rot), 0, 1, 0);
        glTranslatef((float)-myLocation.x, (float)-myLocation.y, (float)-myLocation.z);
        
		// line properties
		glColor3f(0.0f, 1.0f, 1.0f);
//		glLineWidth(1.0f);
		
//		// draw testing
//		glPushMatrix();
//		glRotatef(0.0f, 0.0f, 0.0f, 0.1f);
//		glBegin(GL_LINES);
//			glVertex3d(0.0, 0.0, 0.0);
//			glVertex3d(-1.0, 1.0, 1.0);
//		glEnd();

		// Do your drawing here.
		glBegin(GL_LINES);
		Iterator<Line3D> it = model.getLines();
		while (it.hasNext()) {
			Line3D l = it.next();
			Point3D s = l.start;
			Point3D e = l.end;
			glVertex3d(s.x, s.y, s.z);
			glVertex3d(e.x, e.y, e.z);
		}
		glEnd();
	}
	
	private void PerspectiveMode() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(60f,		w/h,		1f,		100f);
        //             fov		aspect  	near  	far
	}
	
	private void OrthoMode() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glOrtho(-12, 12, -12, 12, -100, 100);
	}

}
