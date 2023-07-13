package map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import entity.tile.Tile;
import event.GameEventManager;

public final class Tilemap {
    
    private GameEventManager game;
    private List<Tile> tiles = new ArrayList<Tile>();
    private Map<String, HashMap<String, Object>> data;
    private String map = null;
    private int[] pos = new int[2];

    public Tilemap(GameEventManager game) {

        this.game = game;

        try {

            this.data = TMXReader.getMapData("test.tmx");
            this.map = (String) this.data.get("data").get("csvmap");

        } catch (ParserConfigurationException | SAXException | IOException e) {
            
            e.printStackTrace();
            
        }

    }



    public void createTiles() {

        for (int y = 0; y < (int) this.data.get("map").get("height"); y++) {
            


        }

    }

}
