package CS355.LWJGL;

//import java.util.logging.Level;

/**
 *
 * @author Brennan Smith
 */
public class CS355LWJGL {

	public static void main(String[] args) {
		LWJGLSandbox main = null;
		try {
			main = new LWJGLSandbox();
			main.create(new StudentLWJGLController(LWJGLSandbox.DISPLAY_HEIGHT, LWJGLSandbox.DISPLAY_WIDTH));
			main.run();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (main != null) {
				main.destroy();
			}
		}
	}

}
