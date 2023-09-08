package gfx.ui.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static game.GameSettings.*;

import gfx.FontSheet;
import gfx.ImageResource;
import gfx.Renderer;
import gfx.ui.UISheet;

public class SliderBoxElement extends Element {

    public String text;
    public int sliderSize;
    public int sliderValue;

    public SliderBoxElement(int x, int y, String text, int sliderSize, int sliderValue) {
        
        super(x, y);

        this.text = text;
        this.sliderSize = sliderSize;
        this.sliderValue = sliderValue;

        this.type = ElementType.TEXT_BOX;

        this.imageSections = UISheet.SLIDER_BOX_ELEMENT_IMAGE_SECTIONS;
        this.image = new ImageResource(new BufferedImage((this.text.length() + this.sliderSize + 3) * TILESIZE, 3 * TILESIZE, 
            BufferedImage.TYPE_INT_ARGB));

        this.buildImage();
        
    }



    public final void incrementValue(int amount) {

        if (this.sliderValue + amount <= this.sliderSize - 1) {

            this.sliderValue += amount;
            this.buildImage();

        }

    }



    public final void decrementValue(int amount) {

        if (this.sliderValue - amount >= 0) {

            this.sliderValue -= amount;
            this.buildImage();

        }

    }



    @Override
    public void buildImage() {

        Graphics2D g2d = (Graphics2D) this.image.getBufferedImage().getGraphics();

        g2d.drawImage(this.imageSections[0].getBufferedImage(), 0, 0, null);
        g2d.drawImage(this.imageSections[2].getBufferedImage(), this.image.getWidth() - TILESIZE, 0, null);
        g2d.drawImage(this.imageSections[6].getBufferedImage(), 0, 
            this.image.getHeight() - TILESIZE, null);
        g2d.drawImage(this.imageSections[8].getBufferedImage(), this.image.getWidth() - TILESIZE, 
            this.image.getHeight() - TILESIZE, null);

        for (int x = TILESIZE; x < this.image.getWidth() - TILESIZE; x += TILESIZE) {
            
            g2d.drawImage(this.imageSections[1].getBufferedImage(), x, 0, null);
            g2d.drawImage(this.imageSections[7].getBufferedImage(), x, this.image.getHeight() - TILESIZE, null);

        }

        g2d.drawImage(this.imageSections[3].getBufferedImage(), 0, TILESIZE, null);
        g2d.drawImage(this.imageSections[5].getBufferedImage(), this.image.getWidth() - TILESIZE, TILESIZE, null);

        g2d.drawImage(FontSheet.getTextImage(this.text).getBufferedImage(), TILESIZE, TILESIZE, null);

        for (int x = (TILESIZE * (this.text.length() + 2)); x < (TILESIZE * (this.text.length() + 2) + ((this.sliderSize) * TILESIZE)); x += TILESIZE) {
            
            g2d.drawImage(this.imageSections[9].getBufferedImage(), x, TILESIZE, null);

        }

        g2d.drawImage(this.imageSections[10].getBufferedImage(), (TILESIZE * (this.text.length() + 2 + this.sliderValue)), TILESIZE, null);

        g2d.dispose();

    }



    @Override
    public void draw() {

        Renderer.drawImage(this.image, this.x, this.y);

    }

}
