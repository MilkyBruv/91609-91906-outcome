package gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class FontSheet {
    
    private static Map<Character, ImageResource> charMap = new HashMap<Character, ImageResource>() {};
    public static ImageResource spritesheet = null;
    private static final char[] chars = "abcdefghijklmnopqrstuvwxyz |0123456789.,\"|!?#_♠♦◘☻+=-♣○*%$:;()[]{}||||"
        .toCharArray();

    static {

        try {

            spritesheet = new ImageResource("fontsheet.png");

            int charCount = 0;

            // Loop through each image in spritesheet and add them to charMap with the key of chars[charCount]
            for (int y = 0; y < spritesheet.getHeight() / 8; y++) {
                
                for (int x = 0; x < spritesheet.getWidth() / 8; x++) {
                
                    charMap.put(chars[charCount], spritesheet.getSubImage(x, y, 8, 8));
                    charCount++;

                }

            }

        } catch (IOException e) {
            
            e.printStackTrace();

        }

    }



    /**
     * Creates and returns an ImageResource with text from supplied string
     * 
     * @param text - Text to be drawn onto ImageResource
     * @return An ImageResource with the text from the supplied string
     */
    public static final ImageResource getTextImage(String text) {

        // Create new ImageResource with the dimensions relative to the supplied text
        ImageResource textImage = new ImageResource(new BufferedImage(text.length() * 8, 8, 
            BufferedImage.TYPE_INT_ARGB));
        
        // Get graphics of textImage BufferedImage and draw each character from charMap onto it
        Graphics2D g2d = (Graphics2D) textImage.getBufferedImage().getGraphics();
        int x = 0;

        for (Character c : text.toCharArray()) {
            
            g2d.drawImage(charMap.get(c).getBufferedImage(), x, 0, null);
            x += 8;

        }

        g2d.dispose();

        return textImage;

    }

}
