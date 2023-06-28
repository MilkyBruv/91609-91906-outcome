package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import event.RenderEventListener;

public final class Main implements Runnable {

    private static final int FPS = 60;
    public static double delta = 0;

    private static final JFrame FRAME = new JFrame();
    private static final GLProfile GL_PROFILE = GLProfile.get(GLProfile.GL2);
    private static final GLCapabilities GL_CAPABILITIES = new GLCapabilities(GL_PROFILE);
    private static final GLCanvas GL_CANVAS = new GLCanvas(GL_CAPABILITIES);
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
     * Initializes JFrame and GLCanvas properties
     */
    private static final void initWindow() {

        // Setup JFrame
        FRAME.setTitle("Window Test");
        FRAME.getContentPane().setSize(640, 480);
        FRAME.getContentPane().setPreferredSize(new Dimension(640, 480));
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setResizable(true);

        // Setup OpenGL Swing canvas properties, and GLEventListener
        GL_CANVAS.setSize(640, 480);
        GL_CANVAS.setPreferredSize(new Dimension(640, 480));
        GL_CANVAS.setBackground(new Color(0x000000));
        GL_CANVAS.addGLEventListener(new RenderEventListener());
        GL_CANVAS.setFocusable(true);

        // Finish setting up JFrame
        FRAME.getContentPane().add(GL_CANVAS);
        FRAME.pack();
        FRAME.setLocationRelativeTo(null);
        FRAME.setVisible(true);

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
        
        GL_CANVAS.display();

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
