package gfx.ui.elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static game.GameSettings.*;
import gfx.ImageResource;
import gfx.ui.UISheet;

public class BoxElement extends Element {

    public BoxElement(int x, int y, int width, int height) {
        
        super(x, y, width, height);

        this.type = ElementType.BOX;

        this.imageSections = UISheet.BOX_ELEMENT_IMAGE_SECTIONS;
        this.image = new ImageResource(new BufferedImage(this.width * TILESIZE, this.height * TILESIZE, 
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

        g2d.dispose();

    }

}
