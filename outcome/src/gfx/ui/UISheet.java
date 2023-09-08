package gfx.ui;

import java.io.IOException;

import gfx.ImageResource;

public class UISheet {

    private static ImageResource image;

    public static final ImageResource[] BOX_ELEMENT_IMAGE_SECTIONS = new ImageResource[9];
    // 0, 1, 2
    // 3, 4, 5
    // 6, 7, 8

    public static final ImageResource[] SLIDER_BOX_ELEMENT_IMAGE_SECTIONS = new ImageResource[11];
    // 0, 1, 2      9, 10
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
            BOX_ELEMENT_IMAGE_SECTIONS[4] = image.getSubImageByDimensions(1, 1, 8, 8);
            BOX_ELEMENT_IMAGE_SECTIONS[5] = image.getSubImageByDimensions(2, 1, 8, 8);

            BOX_ELEMENT_IMAGE_SECTIONS[6] = image.getSubImageByDimensions(0, 2, 8, 8);
            BOX_ELEMENT_IMAGE_SECTIONS[7] = image.getSubImageByDimensions(1, 2, 8, 8);
            BOX_ELEMENT_IMAGE_SECTIONS[8] = image.getSubImageByDimensions(2, 2, 8, 8);

            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[0] = BOX_ELEMENT_IMAGE_SECTIONS[0];
            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[1] = BOX_ELEMENT_IMAGE_SECTIONS[1];
            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[2] = BOX_ELEMENT_IMAGE_SECTIONS[2];

            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[3] = BOX_ELEMENT_IMAGE_SECTIONS[3];
            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[4] = BOX_ELEMENT_IMAGE_SECTIONS[4];
            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[5] = BOX_ELEMENT_IMAGE_SECTIONS[5];

            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[6] = BOX_ELEMENT_IMAGE_SECTIONS[6];
            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[7] = BOX_ELEMENT_IMAGE_SECTIONS[7];
            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[8] = BOX_ELEMENT_IMAGE_SECTIONS[8];

            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[9] = image.getSubImageByDimensions(3, 0, 8, 8);
            SLIDER_BOX_ELEMENT_IMAGE_SECTIONS[10] = image.getSubImageByDimensions(4, 0, 8, 8);

        } catch (IOException e) {
            
            e.printStackTrace();

        }
        
    }

}
