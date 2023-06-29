package gfx;

import java.io.IOException;

public abstract class ImageLoader {

    public static Image testImage;

    /**
     * Loads all the {@link Image}s
     */
    public static final void load() {

        // Load images here
        try {

            testImage = new Image("./res/image.png");

        } catch (IOException e) {
            
            e.printStackTrace();

        }

    }

}
