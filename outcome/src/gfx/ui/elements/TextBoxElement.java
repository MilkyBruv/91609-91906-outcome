package gfx.ui.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static game.GameSettings.*;

import gfx.FontSheet;
import gfx.ImageResource;

public class TextBoxElement extends Element {

    public String text;

    public TextBoxElement(int x, int y, String text) {
        
        super(x, y);

        this.type = ElementType.BOX;

        this.imageSections = new ImageResource[9];
        this.image = new ImageResource(new BufferedImage((this.text.length() + 2) * TILESIZE, 3 * TILESIZE, 
            BufferedImage.TYPE_INT_ARGB));

        this.buildImage();
        
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

        for (int x = TILESIZE; x < this.image.getWidth() - TILESIZE; x++) {
            
            g2d.drawImage(this.imageSections[1].getBufferedImage(), x, 0, null);
            g2d.drawImage(this.imageSections[1].getBufferedImage(), x, this.image.getHeight() - TILESIZE, null);

        }

        for (int y = TILESIZE; y < this.image.getWidth() - TILESIZE; y++) {
            
            g2d.drawImage(this.imageSections[1].getBufferedImage(), 0, y, null);
            g2d.drawImage(this.imageSections[1].getBufferedImage(), this.image.getWidth() - TILESIZE, y, null);

        }

        g2d.drawImage(FontSheet.getTextImage(this.text).getBufferedImage(), TILESIZE, TILESIZE, null);

        g2d.dispose();

    }

}
