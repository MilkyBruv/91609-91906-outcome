package tileset;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import gfx.ImageResource;

public final class Spritesheet {
    
    private static ImageResource image = null;
    private static final Map<String, ImageResource> IMAGE_ID_MAP = new HashMap<>();
    private static final Map<String, String> TYPE_ID_MAP = new HashMap<>();

    /**
     * Gets data from TMX file and maps all the tiles with their corresponding id
     */
    public static void mapTiles() {

        try {

            // Get data from TSX file
            TSXInfo tsxInfo = TSXReader.getTilesetData("image.tsx");
            image = new ImageResource(tsxInfo.source.split("/")[1]);

            // Tile id
            int id = 1;

            // Iterator for tilesInfo
            int tileCount = 0;

            // Loop through each tile and add the image and type to map with corresponding id
            for (int y = 0; y < tsxInfo.height / tsxInfo.tileHeight; y++) {
                
                for (int x = 0; x < tsxInfo.width / tsxInfo.tileWidth; x++) {

                    IMAGE_ID_MAP.put(Integer.toString(id), image.getSubImage(x * tsxInfo.tileWidth, y * tsxInfo.tileHeight, 
                        tsxInfo.tileWidth, tsxInfo.tileHeight));

                    TYPE_ID_MAP.put(Integer.toString(id), tsxInfo.tilesInfo.get(tileCount).id);

                    id++;
                    tileCount++;

                }

            }

        } catch (IOException | ParserConfigurationException | SAXException e) {

            e.printStackTrace();

        }

    }



    public static final ImageResource getImage(String id) {

        return IMAGE_ID_MAP.get(id);

    }



    public static final String getType(String id) {

        return TYPE_ID_MAP.get(id);

    }

}
