package event;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import gfx.Renderer;

public final class RenderEventListener implements GLEventListener {

    private int windowWidth = 640;
    private int windowHeight = 480;

    @Override
    public synchronized void init(GLAutoDrawable drawable) {
        
        GL2 gl = drawable.getGL().getGL2();

        // Set clear colour
        gl.glClearColor(0f, 0f, 0f, 1f);
        gl.glEnable(GL2.GL_TEXTURE_2D);

    }



    @Override
    public synchronized void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        
        // Draw onto framebuffer
        Renderer.createGraphics();

            Renderer.clear(0xff00ff);
            Renderer.drawLine(0, 0, 10, 10, 0xffffff);

        Renderer.disposeGraphics();
        Renderer.scaleFramebuffer(windowWidth, windowHeight);

        // Clear display, set matrix mode, and set ortho viewport position to topleft (0, 0)
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, windowWidth, windowHeight, 0, -1, 1);

        // Bind OpenGL Texture
        gl.glBindTexture(GL2.GL_TEXTURE_2D, Renderer.getFramebufferGlTexture().getTextureObject());

        // Set interpolation parameters to prevent blurring when scaling
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);

        // Draw framebuffer OpenGL Texture
        gl.glColor4f(1f, 1f, 1f, 1f);
        gl.glBegin(GL2.GL_QUADS);

            gl.glTexCoord2f(0f, 0f);
            gl.glVertex2f(Renderer.framebufferX, Renderer.framebufferY);

            gl.glTexCoord2f(1f, 0f);
            gl.glVertex2f(Renderer.framebufferX + Renderer.framebufferWidth, Renderer.framebufferY);

            gl.glTexCoord2f(1f, 1f);
            gl.glVertex2f(Renderer.framebufferX + Renderer.framebufferWidth, 
                Renderer.framebufferY + Renderer.framebufferHeight);

            gl.glTexCoord2f(0f, 1f);
            gl.glVertex2f(Renderer.framebufferX, Renderer.framebufferY + Renderer.framebufferHeight);

        gl.glEnd();
        gl.glFlush();

        // Bind to 0 (No OpenGL Texture)
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
    
    }



    @Override
    public synchronized void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        
        GL2 gl = drawable.getGL().getGL2();

        windowWidth = width;
        windowHeight = height;
        gl.glViewport(x, y, width, height);

    }



    @Override
    public synchronized void dispose(GLAutoDrawable drawable) {
        
        // 

    }

}
