package map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import entity.tile.Tile;
import event.GameEventManager;

public final class Tilemap {
    
    private GameEventManager game;
    private List<Tile> tiles = new ArrayList<Tile>();

    public Tilemap(GameEventManager game) {

        this.game = game;

        try {
            System.out.println(TMXMapReader.getMapData("test.tmx").get("map").get("width"));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    public void createTiles() {

        // 

    }

}
