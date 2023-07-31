package map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import entity.tile.Tile;
import game.GameEventManager;
import tileset.Spritesheet;

public final class Tilemap {
    
    private GameEventManager game;
    private final List<Tile> TILES = new ArrayList<Tile>();
    private String[][] mapIdData;
    private TMXInfo tmxInfo;
    private int[] pos = new int[2];

    public Tilemap(GameEventManager game, int[] pos) {

        this.game = game;
        this.pos = pos;

        // Map tiles to spritesheet
        Spritesheet.mapTiles();

        try {
            
            // Get data from TMX file
            this.tmxInfo = TMXReader.getMapData("test1.tmx");
            this.mapIdData = this.tmxInfo.getMapIdData();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            
            e.printStackTrace();

        }

    }



    /**
     * Loops through each id in the CSV map and creates a new Tile
     */
    public void createTiles() {

        for (int y = 0; y < this.tmxInfo.getHeight(); y++) {
            
            for (int x = 0; x < this.tmxInfo.getWidth(); x++) {
                
                if (!String.valueOf(this.mapIdData[y][x]).equals("0")) {

                    this.TILES.add(new Tile(x * this.tmxInfo.getTileWidth(), y * this.tmxInfo.getTileHeight(), 
                        String.valueOf(this.mapIdData[y][x]), this.game));
                
                }

            }

        }

    }



    public GameEventManager getGame() {

        return game;

    }



    public List<Tile> getTiles() {

        return TILES;

    }



    public String[][] getMapIdData() {

        return mapIdData;

    }



    public TMXInfo getTmxInfo() {

        return tmxInfo;

    }



    public int[] getPos() {

        return pos;

    }

}
