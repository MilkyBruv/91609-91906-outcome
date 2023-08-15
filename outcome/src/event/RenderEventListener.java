package event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import game.GameEventManager;
import gfx.Renderer;

public final class RenderEventListener implements GLEventListener {

    private int windowWidth = 10;
    private int windowHeight = 10;
    private int shaderProgram;
    private GameEventManager game;

    public RenderEventListener(GameEventManager game) {

        this.game = game;

    }




    @Override
    public synchronized void init(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();

        // Set clear colour and enable images to be draw to the window
        gl.glClearColor(0f, 0f, 0f, 1f);
        gl.glEnable(GL2.GL_TEXTURE_2D);

        // Load and apply fragment shader

        // Read shader file
        String fragShaderSource = "";

        try (BufferedReader reader = new BufferedReader(new FileReader("src/shader/fragment.glsl"))) {

            String line = "";

            while ((line = reader.readLine()) != null) {

                fragShaderSource += line + "\n";

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        // Create and compile shader
        int fragShader = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);
        gl.glShaderSource(fragShader, 1, new String[] {fragShaderSource}, null);
        gl.glCompileShader(fragShader);

        // Load shader
        shaderProgram = gl.glCreateProgram();
        gl.glAttachShader(shaderProgram, fragShader);
        gl.glLinkProgram(shaderProgram);

        int[] status = new int[1];
        gl.glGetShaderiv(fragShader, GL2.GL_COMPILE_STATUS, status, 0);
        if (status[0] == GL2.GL_FALSE) {
            int[] infoLogLength = new int[1];
            gl.glGetShaderiv(fragShader, GL2.GL_INFO_LOG_LENGTH, infoLogLength, 0);

            if (infoLogLength[0] > 0) {
                byte[] infoLog = new byte[infoLogLength[0]];
                gl.glGetShaderInfoLog(fragShader, infoLogLength[0], null, 0, infoLog, 0);
                System.err.println("Shader compilation error: " + new String(infoLog));
            }
        }

        status = new int[1];
        gl.glGetProgramiv(shaderProgram, GL2.GL_LINK_STATUS, status, 0);
        if (status[0] == GL2.GL_FALSE) {
            int[] infoLogLength = new int[1];
            gl.glGetProgramiv(shaderProgram, GL2.GL_INFO_LOG_LENGTH, infoLogLength, 0);

            if (infoLogLength[0] > 0) {
                byte[] infoLog = new byte[infoLogLength[0]];
                gl.glGetProgramInfoLog(shaderProgram, infoLogLength[0], null, 0, infoLog, 0);
                System.err.println("Shader program linking error: " + new String(infoLog));
            }
        }


    }



    @Override
    public synchronized void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();

        // Clear display, set matrix mode, and set ortho viewport position to topleft (0, 0)
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, windowWidth, windowHeight, 0, -1, 1);

        // Draw onto framebuffer
        Renderer.createGraphics();

            this.game.draw();

        Renderer.disposeGraphics();

        // Bind OpenGL Texture
        gl.glBindTexture(GL2.GL_TEXTURE_2D, Renderer.getFramebufferGlTexture().getTextureObject());

        // Set interpolation parameters to prevent blurring when scaling
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);

        // Apply shader
        gl.glUseProgram(shaderProgram);

        // Draw framebuffer OpenGL Texture to window
        gl.glColor4f(1f, 1f, 1f, 1f);
        gl.glBegin(GL2.GL_QUADS);

            gl.glTexCoord2f(0, 0);
            gl.glVertex2f(Renderer.framebufferX, Renderer.framebufferY);

            gl.glTexCoord2f(1, 0);
            gl.glVertex2f(Renderer.framebufferX + Renderer.framebufferWidth, Renderer.framebufferY);

            gl.glTexCoord2f(1, 1);
            gl.glVertex2f(Renderer.framebufferX + Renderer.framebufferWidth,
                Renderer.framebufferY + Renderer.framebufferHeight);

            gl.glTexCoord2f(0, 1);
            gl.glVertex2f(Renderer.framebufferX, Renderer.framebufferY + Renderer.framebufferHeight);

        gl.glEnd();
        gl.glFlush();

        // Remove shader
        gl.glUseProgram(0);

        // Bind to 0 (No OpenGL Texture)
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);

    }



    @Override
    public synchronized void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        GL2 gl = drawable.getGL().getGL2();

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
