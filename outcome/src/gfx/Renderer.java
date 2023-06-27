package gfx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public final class Renderer {
    
    private static final BufferedImage framebuffer = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
    private static Graphics2D framebufferGraphics2D = (Graphics2D) framebuffer.getGraphics();

    /**
     * Creates frameBuffer Graphics2D
     */
    public static final void createGraphics() {

        // Create graphics2d if it is null
        if (framebufferGraphics2D == null) {

            framebufferGraphics2D = (Graphics2D) framebuffer.getGraphics();

        }

    }



    /**
     * Clears frameBuffer with hex colour
     * @param hex (0x000000)
     */
    public static final void clear(int hex) {

        // Get current colour
        Color previousColour = framebufferGraphics2D.getColor();

        // Fill frameBuffer with colour
        framebufferGraphics2D.setColor(new Color(hex));
        framebufferGraphics2D.fillRect(0, 0, framebuffer.getWidth(), framebuffer.getHeight());

        // Set colour back to previous
        framebufferGraphics2D.setColor(previousColour);

    }



    /**
     * Draws image to frameBuffer
     * @param imageResource
     * @param x
     * @param y
     */
    public static final void drawImage(ImageResource imageResource, int x, int y) {

        // Draw bufferedImage to frameBuffer
        framebufferGraphics2D.drawImage(imageResource.getBufferedImage(), x, y, null);

    }



    /**
     * Disposes frameBuffer Graphics2D
     */
    public static final void disposeGraphics() {

        // Dispose frameBuffer Graphics2D
        framebufferGraphics2D.dispose();

    }

}
