package gfx.ui.elements;

import java.awt.image.BufferedImage;

import entity.item.ElementType;
import game.GameSettings;
import gfx.ImageResource;

public class Element {
    
    public int x;
    public int y;
    public int width;
    public int height;
    public ElementType type;
    public ImageResource[] imageSections = new ImageResource[9];
    public ImageResource builtImage = new ImageResource(new BufferedImage(this.width * GameSettings.TILESIZE, 
        this.height * GameSettings.TILESIZE, BufferedImage.TYPE_INT_ARGB));
    // 0, 0, 0
    // 0, 0, 0
    // 0, 0, 0

    public Element(int x, int y, int width, int height, ElementType type) {
    
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    
    }



    public final void buildImage() {

        

    }

}
