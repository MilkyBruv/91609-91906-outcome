package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import event.RenderEventListener;

public final class Main implements Runnable {

    public static double delta = 0;

    private static final int FPS = 60;

    private static JFrame frame;
    private static GLCapabilities glCapabilities;
    private static GLProfile glProfile;
    private static GLCanvas glCanvas;
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
        // System.setProperty("sun.java2d.opengl", "true");

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
     * Initializes JFrame, GLProfile, GLCapabilities, and GLCanvas
     */
    private static final void initWindow() {

        // Create JFrame
        frame = new JFrame();
        frame.setTitle("Window Test");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup OpenGL profile, capabilities, basic properties, and GLEventListener
        glProfile = GLProfile.get(GLProfile.GL2);
        glCapabilities = new GLCapabilities(glProfile);
        glCanvas = new GLCanvas(glCapabilities);
        glCanvas.setSize(640, 480);
        glCanvas.setPreferredSize(new Dimension(640, 480));
        glCanvas.setBackground(new Color(0x000000));
        glCanvas.setFocusable(true);
        glCanvas.addGLEventListener(new RenderEventListener());

        // Finish setting up JFrame
        frame.add(glCanvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }



    /**
     * Calls all other update methods then gets called in main thread method
     */
    private static final void update() {

        // 

    }



    /**
     * Calls Renderer and RenderEventListener drawing methods through GLCanvas
     */
    private static final void draw() {
        
        glCanvas.display();

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
