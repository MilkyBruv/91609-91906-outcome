package gfx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public abstract class Renderer {

    // Framebuffer scaling and positioning variable declarations
    public static final int FRAMEBUFFER_BASE_WIDTH = 16 * 20; // 16:9 ratio is the aspect ratio for most monitors
    public static final int FRAMEBUFFER_BASE_HEIGHT = 9 * 20;
    public static int framebufferWidth = FRAMEBUFFER_BASE_WIDTH;
    public static int framebufferHeight = FRAMEBUFFER_BASE_HEIGHT;
    public static int framebufferX = 0;
    public static int framebufferY = 0;

    private static final BufferedImage FRAMEBUFFER = new BufferedImage(FRAMEBUFFER_BASE_WIDTH, FRAMEBUFFER_BASE_HEIGHT,
            BufferedImage.TYPE_INT_ARGB);
    private static Graphics2D framebufferGraphics2D = (Graphics2D) FRAMEBUFFER.getGraphics();
    private static Texture glTexture = null;

    /**
     * Creates framebuffer Graphics2D
     */
    public static final void createGraphics() {

        // Create graphics2d if it is null
        if (framebufferGraphics2D == null) {

            framebufferGraphics2D = (Graphics2D) FRAMEBUFFER.getGraphics();

        }

    }



    /**
     * Clears framebuffer with hex colour
     * 
     * @param hex - (0x000000)
     */
    public static final void clear(int hex) {

        // Get current colour
        Color previousColour = framebufferGraphics2D.getColor();

        // Fill framebuffer with colour
        framebufferGraphics2D.setColor(new Color(hex));
        framebufferGraphics2D.fillRect(0, 0, FRAMEBUFFER_BASE_WIDTH, FRAMEBUFFER_BASE_HEIGHT);

        // Set colour back to previous
        framebufferGraphics2D.setColor(previousColour);

    }



    public static final void drawText(String text, int x, int y) {

        framebufferGraphics2D.drawImage(FontSheet.getTextImage(text).getBufferedImage(), x, y, null);

    }



    /**
     * Draws line onto frame buffer from (x1, y1) to (x2, y2) with the colour of the
     * supplied hex
     * 
     * @param x1 - start x
     * @param y1 - start y
     * @param x2 - end x
     * @param y2 - end y
     * @param hex - (0x000000)
     */
    public static final void drawLine(int x1, int y1, int x2, int y2, int hex) {

        // Get current colour
        Color previousColour = framebufferGraphics2D.getColor();

        // Draw line with colour
        framebufferGraphics2D.setColor(new Color(hex));
        framebufferGraphics2D.drawLine(x1, y1, x2, y2);

        // Set colour back to previous
        framebufferGraphics2D.setColor(previousColour);

    }



    /**
     * Draws line onto frame buffer from (x1, y1) to (x2, y2)
     * 
     * @param x1 - start x
     * @param y1 - start y
     * @param x2 - end x
     * @param y2 - end y
     */
    public static final void drawLine(int x1, int y1, int x2, int y2) {

        // Draw line
        framebufferGraphics2D.drawLine(x1, y1, x2, y2);

    }



    /**
     * Draws image to framebuffer at supplied position
     * 
     * @param image - image to draw
     * @param x - pos x
     * @param y - pos y
     */
    public static final void drawImage(ImageResource image, int x, int y) {

        // Draw bufferedImage to framebuffer if the image is properly loaded
        if (image != null) {

            framebufferGraphics2D.drawImage(image.getBufferedImage(), x, y, null);
            
        }

    }



    /**
     * Sets framebuffer graphics2d colour to supplied hex
     * 
     * @param hex - (0x000000)
     */
    public static final void setColour(int hex) {

        // Set framebuffer graphics2d colour to hex
        framebufferGraphics2D.setColor(new Color(hex));

    }



    /**
     * Disposes framebuffer Graphics2D and creates OpenGL Texture
     */
    public static final void disposeGraphics() {

        // Dispose framebuffer Graphics2D
        framebufferGraphics2D.dispose();

    }



    /**
     * Scales framebuffer to fit window perfectly, keeping the aspect ratio and resolution the same
     * 
     * @param windowWidth - current window width from {@link RenderEventListener}
     * @param windowHeight - current window height from {@link RenderEventListener}
     */
    public static final void scaleFramebuffer(int windowWidth, int windowHeight) {

        // Get framebuffer scale factor
        float scale = Math.min(

            (float) windowWidth / (float) FRAMEBUFFER_BASE_WIDTH,
            (float) windowHeight / (float) FRAMEBUFFER_BASE_HEIGHT

        );

        // Scale width to fit window
        framebufferWidth = Math.round((float) FRAMEBUFFER_BASE_WIDTH * scale);
        framebufferHeight = Math.round((float) FRAMEBUFFER_BASE_HEIGHT * scale);

        // Calculate centre-screen positions
        framebufferX = Math.round(((float) windowWidth / 2) - ((float) framebufferWidth / 2));
        framebufferY = Math.round(((float) windowHeight / 2) - ((float) framebufferHeight / 2));

    }



    public static final BufferedImage getFramebuffer() {

        return FRAMEBUFFER;

    }


    
    /**
     * Creates and returns OpenGL Texture from bufferedImage
     * 
     * @return OpenGL Texture
     */
    public static final Texture getFramebufferGlTexture() {

        // Create OpenGL Texture from framebuffer
        glTexture = AWTTextureIO.newTexture(GLProfile.get(GLProfile.GL2), FRAMEBUFFER, true);

        return glTexture;

    }

}
