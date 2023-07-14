package main;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

import asset.AssetManager;
import event.GameEventManager;
import event.KeyEventListener;
import event.MouseEventListener;
import event.RenderEventListener;
import event.WindowEventListener;
import gfx.Renderer;

public final class Main implements Runnable {

    private static final int FPS = 60;
    public static double delta = 0;

    private static final GLProfile GL_PROFILE = GLProfile.get(GLProfile.GL2);
    private static final GLCapabilities GL_CAPABILITIES = new GLCapabilities(GL_PROFILE);
    private static final GLWindow GL_WINDOW = GLWindow.create(GL_CAPABILITIES);
    private static Thread thread;
    private static GameEventManager game;

    public static void main(String[] args) {

        // Init everything
        init();

    }



    /**
     * Initializes window, game manager, and thread, and loads all the assets
     */
    private static final void init() {

        // Set system properties / compiler args
        setSystemProperties();

        // Load all content before creating window as RenderEventListener needs all content to be fully initialized
        loadAssets();

        // Instantiate and initialize main game manager
        game = new GameEventManager();
        game.init();

        // Create window
        initWindow();

        // Create and start thread
        initThread();

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
        // GL_WINDOW.setFullscreen(true);
        GL_WINDOW.setTitle("Test GLWindow");
        GL_WINDOW.setUndecorated(false);

        // Create main GameEventManager and add event listeners
        GL_WINDOW.addGLEventListener(new RenderEventListener(game));
        GL_WINDOW.addWindowListener(new WindowEventListener(game));
        GL_WINDOW.addKeyListener(new KeyEventListener(game));
        GL_WINDOW.addMouseListener(new MouseEventListener(game));

        // Show window
        GL_WINDOW.setVisible(true);

    }


    
    /**
     * Load images and sounds from {@link AssetLoader}
     */
    private static final void loadAssets() {

        try {

            AssetManager.loadAssets();

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {

            e.printStackTrace();

        }

    }


    
    /**
     * Calls all other update methods then gets called in main thread method
     */
    private static final void update() {

        game.update();

    }


    
    /**
     * Calls {@link Renderer} and {@link RenderEventListener} drawing methods through GLWindow
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
