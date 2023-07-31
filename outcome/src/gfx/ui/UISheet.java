package gfx.ui;

import java.io.IOException;

import gfx.ImageResource;

public class UISheet extends ImageResource {

    public final static ImageResource[] boxElementImageSections = new ImageResource[9];
    public final static ImageResource[] textBoxElementImageSections = new ImageResource[9];

    public UISheet() throws IOException {

        super("uisheet.png");

        this.setBufferedImage("uisheet.png");
        
    }

}
