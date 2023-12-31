package tileset;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import gfx.ImageResource;
import static game.GameSettings.*;

public final class Tileset {
    
    private static ImageResource image = null;
    private static final Map<String, ImageResource> IMAGE_ID_MAP = new HashMap<>();
    private static final Map<String, String> TYPE_ID_MAP = new HashMap<>();

    /**
     * Gets data from TMX file and maps all the tiles with their corresponding id
     */
    static {

        try {

            // Get data from TSX file
            TSXInfo tsxInfo = TSXReader.getTilesetData("tileset.tsx");
            image = new ImageResource(tsxInfo.getSource().split("/")[1]);

            // Tile id
            int id = 1;

            // Iterator for tilesInfo
            int tileCount = 0;

            // Loop through each tile and add the image and type to map with corresponding id
            for (int y = 0; y < tsxInfo.getHeight() / TILESIZE; y++) {
                
                for (int x = 0; x < tsxInfo.getWidth() / TILESIZE; x++) {

                    IMAGE_ID_MAP.put(Integer.toString(id), image.getSubImage(x * tsxInfo.getTileWidth(), 
                        y * tsxInfo.getTileHeight(), TILESIZE, TILESIZE));
                        
                    TYPE_ID_MAP.put(Integer.toString(id), tsxInfo.getTilesInfo().get(tileCount).type);

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
