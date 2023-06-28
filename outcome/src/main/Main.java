package main;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

import event.KeyEventListener;
import event.MouseEventListener;
import event.RenderEventListener;
import event.WindowEventListener;

public final class Main implements Runnable {

    private static final int FPS = 60;
    public static double delta = 0;

    private static final GLProfile GL_PROFILE = GLProfile.get(GLProfile.GL2);
    private static final GLCapabilities GL_CAPABILITIES = new GLCapabilities(GL_PROFILE);
    private static final GLWindow GL_WINDOW = GLWindow.create(GL_CAPABILITIES);
    private static Thread thread;

    public static void main(String[] args) {

        // Init everything
        init();

    }



    /**
     * Sets system properties / compiler args
     */
    private static final void setSystemProperties() {

        // Make Java2D use OpenGL for faster rendering
        System.setProperty("sun.java2d.opengl", "true");

        // Prevent display from being erased to stop flickering when resizing and rendering
        System.setProperty("sun.awt.noerasebackground", "true");

    }



    /**
     * Initializes window and thread
     */
    private static final void init() {

        // Set system properties / compiler args
        setSystemProperties();

        // Create window
        initWindow();

        // Create and start thread
        initThread();

    }



    /**
     * Initializes main thread and starts it
     */
    private static final void initThread() {

        // Create and start thread to run game
        thread = new Thread(new Main());
        thread.start();

    }



    /**
     * Sets up GLWindow
     */
    private static final void initWindow() {

        // Setup basic properties
        GL_WINDOW.setSize(640, 480);
        GL_WINDOW.setTitle("Test GLWindow");
        GL_WINDOW.setUndecorated(false);

        // Add event listeners
        GL_WINDOW.addGLEventListener(new RenderEventListener());
        GL_WINDOW.addWindowListener(new WindowEventListener());
        GL_WINDOW.addKeyListener(new KeyEventListener());
        GL_WINDOW.addMouseListener(new MouseEventListener());

        GL_WINDOW.setVisible(true);

    }



    /**
     * Calls all other update methods then gets called in main thread method
     */
    private static final void update() {

        // 

    }



    /**
     * Calls {@link Renderer} and {@link RenderEventListener} drawing methods through GLCanvas
     */
    private static final void draw() {
        
        GL_WINDOW.display();

    }


    
    @Override
    public void run() {

        // 60 FPS game loop cap
        // Calculates time left on update and waits till time is right to update
        double drawInterval = 1000000000 / FPS;
        delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (thread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {

                // Update
                update();

                // Render
                draw();

                delta--;

            }

        }

    }

}
