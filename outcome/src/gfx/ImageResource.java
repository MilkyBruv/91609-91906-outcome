package gfx;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ImageResource {
    
    private BufferedImage bufferedImage = null;
    private int width, height;
    private final GraphicsConfiguration graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment()
        .getDefaultScreenDevice().getDefaultConfiguration();

    /**
     * Creates ImageResource, and loads and optimizes supplied bufferedImage
     * @param bufferedImage 
     */
    public ImageResource(BufferedImage bufferedImage) {

        // Set bufferedImage, then get dimensions and optimize it
        this.bufferedImage = bufferedImage;
        this.setImageProperties();

    }



    /**
     * Creates ImageResource, and loads and optimizes image from path
     * @param filePath
     * @throws IOException
     */
    public ImageResource(String filePath) throws IOException {

        // Read image data from File and get dimensions
        this.bufferedImage = ImageIO.read(new File(filePath));
        this.setImageProperties();

    }



    /**
     * Optimizes and gets dimensions of bufferedImage
     */
    private final void setImageProperties() {

        this.optimizeBufferedImage();
        this.setImageDimensions();

    }



    /**
     * Sets width and height to bufferedImage dimensions
     */
    private final void setImageDimensions() {

        this.width = this.bufferedImage.getWidth();
        this.height = this.bufferedImage.getHeight();

    }



    /**
     * Optimizes bufferedImage using current GraphicsConfiguration
     */
    private final void optimizeBufferedImage() {

        // Creates optimized / more compatible bufferedImage for faster rendering
        this.bufferedImage = graphicsConfig.createCompatibleImage(this.width, this.height, 
            this.bufferedImage.getTransparency());
        this.bufferedImage.flush();

    }



    public final BufferedImage getBufferedImage() {

        return this.bufferedImage;

    }



    public final void setBufferedImage(BufferedImage bufferedImage) {

        this.bufferedImage = bufferedImage;

    }



    /**
     * Loads and optimizes and gets dimensions of bufferedImage from file path
     * @param filePath
     * @throws IOException
     */
    public final void setBufferedImage(String filePath) throws IOException {

        // Read image data from file path and optimize and get dimensions
        this.bufferedImage = ImageIO.read(new File(filePath));
        this.setImageProperties();

    }



    public final int getWidth() {

        return this.width;

    }



    public final int getHeight() {

        return this.height;

    }

}
