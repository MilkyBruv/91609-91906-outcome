package gfx.ui;

import java.io.IOException;

import gfx.ImageResource;
import gfx.Renderer;

public class UISheet {

    private static ImageResource image;

    public final static ImageResource[] BOX_ELEMENT_IMAGE_SECTIONS = new ImageResource[9];
    // 0, 1, 2
    // 3, 4, 5
    // 6, 7, 8

    public final static ImageResource[] TEXT_BOX_ELEMENT_IMAGE_SECTIONS = new ImageResource[9];
    // 0, 1, 2
    // 3, 4, 5
    // 6, 7, 8

    static {

        // Load UI sheet image
        try {

            image = new ImageResource("uisheet.png");

            // Box Element
            BOX_ELEMENT_IMAGE_SECTIONS[0] = image.getSubImageByDimensions(0, 0, 8, 8);
            BOX_ELEMENT_IMAGE_SECTIONS[1] = image.getSubImageByDimensions(1, 0, 8, 8);
            BOX_ELEMENT_IMAGE_SECTIONS[2] = image.getSubImageByDimensions(2, 0, 8, 8);

            BOX_ELEMENT_IMAGE_SECTIONS[3] = image.getSubImageByDimensions(0, 1, 8, 8);
            // [4] is blank and therefore is not loaded
            BOX_ELEMENT_IMAGE_SECTIONS[5] = image.getSubImageByDimensions(2, 1, 8, 8);

            BOX_ELEMENT_IMAGE_SECTIONS[6] = image.getSubImageByDimensions(0, 2, 8, 8);
            BOX_ELEMENT_IMAGE_SECTIONS[7] = image.getSubImageByDimensions(1, 2, 8, 8);
            BOX_ELEMENT_IMAGE_SECTIONS[8] = image.getSubImageByDimensions(2, 2, 8, 8);

            // Text Box Element
            TEXT_BOX_ELEMENT_IMAGE_SECTIONS[0] = BOX_ELEMENT_IMAGE_SECTIONS[0];
            TEXT_BOX_ELEMENT_IMAGE_SECTIONS[1] = BOX_ELEMENT_IMAGE_SECTIONS[1];
            TEXT_BOX_ELEMENT_IMAGE_SECTIONS[2] = BOX_ELEMENT_IMAGE_SECTIONS[2];

            TEXT_BOX_ELEMENT_IMAGE_SECTIONS[3] = BOX_ELEMENT_IMAGE_SECTIONS[3];
            // [4] is blank and therefore is not loaded
            TEXT_BOX_ELEMENT_IMAGE_SECTIONS[5] = BOX_ELEMENT_IMAGE_SECTIONS[5];

            TEXT_BOX_ELEMENT_IMAGE_SECTIONS[6] = BOX_ELEMENT_IMAGE_SECTIONS[6];
            TEXT_BOX_ELEMENT_IMAGE_SECTIONS[7] = BOX_ELEMENT_IMAGE_SECTIONS[7];
            TEXT_BOX_ELEMENT_IMAGE_SECTIONS[8] = BOX_ELEMENT_IMAGE_SECTIONS[8];

        } catch (IOException e) {
            
            e.printStackTrace();

        }
        
    }

}
