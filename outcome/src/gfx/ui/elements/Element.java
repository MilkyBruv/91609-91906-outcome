package gfx.ui.elements;

import gfx.ImageResource;
import gfx.Renderer;

public class Element {
    
    public int x;
    public int y;
    public int width;
    public int height;
    public ElementType type;
    public ImageResource[] imageSections;
    public ImageResource image;

    public Element(int x, int y, int width, int height) {
    
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    
    }



    public Element(int x, int y) {

        this.x = x;
        this.y = y;

    }



    public void buildImage() {}



    public void draw() {

        Renderer.drawImage(this.image, this.x, this.x);

    }

}
