package event;

import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.IntBuffer;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import game.GameEventManager;
import gfx.Renderer;
import shader.Shader;

public final class RenderEventListener implements GLEventListener {

    private int windowWidth = 10;
    private int windowHeight = 10;
    private int shaderProgram;
    private int programId;
    private int vao;
    private int vbo;
    private int framebuffer;
    private GameEventManager game;
    private float[] verts = { // Texture vertices

        // positions         // colors           // texture coords
        0.5f,  0.5f, 0.0f,   1.0f, 0.0f, 0.0f,   1.0f, 1.0f,    // top right
        0.5f, -0.5f, 0.0f,   0.0f, 1.0f, 0.0f,   1.0f, 0.0f,    // bottom right
        -0.5f, -0.5f, 0.0f,   0.0f, 0.0f, 1.0f,   0.0f, 0.0f,   // bottom left
        -0.5f,  0.5f, 0.0f,   1.0f, 1.0f, 0.0f,   0.0f, 1.0f    // top left 

    };;

    public RenderEventListener(GameEventManager game) {

        this.game = game;

    }




    @Override
    public synchronized void init(GLAutoDrawable drawable) {

        GL4 gl = drawable.getGL().getGL4();

        // Set clear colour and enable images to be draw to the window
        gl.glClearColor(0f, 0f, 0f, 1f);
        gl.glEnable(GL4.GL_TEXTURE_2D);

        // Create shaders
        Shader.loadAndCompileShaders(gl);
        Shader.createProgram(gl);

    }



    @Override
    public synchronized void display(GLAutoDrawable drawable) {

        GL4 gl = drawable.getGL().getGL4();

        // Clear display, set matrix mode, and set ortho viewport position to topleft (0, 0)
        gl.glClear(GL4.GL_COLOR_BUFFER_BIT);

        // Draw onto framebuffer
        Renderer.createGraphics();

            this.game.draw();

        Renderer.disposeGraphics();

        framebuffer = Renderer.getFramebufferGlTexture().getTextureObject();
        int[] framebufferPixelBuffer = ((DataBufferInt) Renderer.getFramebuffer().getRaster().getDataBuffer()).getData();

        // Generate and bind OpenGL Texture
        gl.glGenTextures(1, IntBuffer.wrap(framebufferPixelBuffer));
        gl.glBindTexture(GL4.GL_TEXTURE_2D, framebuffer);

        // Set interpolation parameters to prevent blurring when scaling
        gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MIN_FILTER, GL4.GL_LINEAR_MIPMAP_LINEAR);
        gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MAG_FILTER, GL4.GL_LINEAR);

        // Setup vertex map and texture
        gl.glTexImage2D(GL4.GL_TEXTURE_2D, 0, GL4.GL_RGB, Renderer.FRAMEBUFFER_BASE_WIDTH, 
            Renderer.FRAMEBUFFER_BASE_HEIGHT, 0, GL4.GL_RGB, GL4.GL_UNSIGNED_BYTE, framebuffer);
        gl.glGenerateMipmap(GL4.GL_TEXTURE_2D);
        gl.glVertexAttribPointer(2, 2, GL4.GL_FLOAT, false, 8 * Float.BYTES, 6 * Float.BYTES);
        gl.glEnableVertexAttribArray(2);

        // Apply shader
        Shader.useProgram(gl);

        // Draw framebuffer OpenGL Texture to window
        gl.glBindTexture(GL4.GL_TEXTURE_2D, framebuffer);
        gl.glBindVertexArray(vao);
        gl.glDrawElements(GL4.GL_TRIANGLES, 6, GL4.GL_UNSIGNED_INT, 0);

        // Remove shader
        gl.glUseProgram(0);

        // Bind to 0 (No OpenGL Texture)
        gl.glBindTexture(GL4.GL_TEXTURE_2D, 0);

    }



    @Override
    public synchronized void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        GL4 gl = drawable.getGL().getGL4();

        windowWidth = width;
        windowHeight = height;

        // Set display viewport to resized window
        gl.glViewport(x, y, width, height);

        // Scale framebuffer when
        Renderer.scaleFramebuffer(windowWidth, windowHeight);

    }


    
    @Override
    public synchronized void dispose(GLAutoDrawable drawable) {

        // 

    }

}
