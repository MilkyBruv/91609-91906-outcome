package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.Main;
import map.TSXTilesetReader;

public final class Spritesheet {
    
    // Using BufferedImage for main image as ImageResource has OpenGL functions that are not required, and using a
    // regular un-optimized BufferedImage will be faster and easier to work with due to there being no rendering
    private BufferedImage bufferedImage = null;
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private TSXTilesetReader tsxTileset;
    private final Map<String, ImageResource> tiles = new HashMap<String, ImageResource>() {};

    /**
     * Instantiates Spritesheet and loads supplied bufferedImage
     * 
     * @param bufferedImage - Pre-loaded image
     */
    public Spritesheet() {
        
        // 

    }



    /**
     * Sets width and height to bufferedImage dimensions
     */
    private final void setImageDimensions() {

        this.width = this.bufferedImage.getWidth();
        this.height = this.bufferedImage.getHeight();

    }



    /**
     * Adds all sub-images to map with corresponding id from TMX CSV data
     */
    private final void mapTiles() {

        // 

    }



    /**
     * Gets and returns image from supplied position
     * 
     * @param x
     * @param y
     * @return Image from map
     */
    public final ImageResource getImage(int x, int y) {

        return new ImageResource(this.bufferedImage.getSubimage(x * this.tileWidth, y * this.tileHeight, this.tileWidth, 
            this.tileHeight));

    }



    public BufferedImage getBufferedImage() {
        
        return bufferedImage;

    }



    public int getWidth() {
        
        return width;

    }



    public int getHeight() {
        
        return height;

    }



    public int getTileWidth() {
        
        return tileWidth;

    }



    public int getTileHeight() {
        
        return tileHeight;

    }

}
