package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.Main;

public final class ConstantSpritesheet {
    
    // Using bufferedImage for main image as ImageResource has OpenGL functions that are not required, and using a
    // regular, un-optimized BufferedImage will be faster and easier to work with
    private BufferedImage bufferedImage = null;
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private final Map<Integer[], ImageResource> images = new HashMap<Integer[], ImageResource>() {};

    /**
     * Instantiates Spritesheet and loads supplied bufferedImage
     * 
     * @param bufferedImage - Pre-loaded image
     */
    public ConstantSpritesheet(BufferedImage bufferedImage, int tileWidth, int tileHeight) {

        // Set bufferedImage, then get dimensions
        this.bufferedImage = bufferedImage;
        this.setImageDimensions();
        
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.mapTiles();

    }



    /**
     * Instantiates Spritesheet and loads image from path
     * 
     * @param fileName - Name of file
     * @throws IOException If cannot find specified image
     */
    public ConstantSpritesheet(String fileName, int tileWidth, int tileHeight) throws IOException {

        // Read image data from File and get dimensions
        this.bufferedImage = ImageIO.read(Main.class.getResourceAsStream("../res/" + fileName));
        this.setImageDimensions();

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.mapTiles();

    }



    /**
     * Loops through each tile interval and creates new ImageResources and puts them into the image map
     */
    public final void mapTiles() {

        // Loop through each tile
        for (int y = 0; y < (int) (this.height / this.tileHeight); y++) {
            
            for (int x = 0; x < (int) (this.width / this.tileWidth); x++) {
            
                // Put the current sub image at (x, y) with the dimensions of (tileWidth, tileHeight) into the image map
                // with the current position
                this.images.put(new Integer[] {x, y}, new ImageResource(this.bufferedImage.getSubimage(
                    x * this.tileWidth, y * this.tileHeight, this.tileWidth, this.tileHeight)));

            }

        }

    }



    /**
     * Sets width and height to bufferedImage dimensions
     */
    private final void setImageDimensions() {

        this.width = this.bufferedImage.getWidth();
        this.height = this.bufferedImage.getHeight();

    }

}
