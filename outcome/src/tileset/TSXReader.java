package tileset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import main.Main;

public abstract class TSXReader {

    /**
     * Reads TSX file and adds attributes to a new TSXInfo instance
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static final TSXInfo getTilesetData(String fileName) throws ParserConfigurationException, SAXException, 
        IOException {

        // Tile array
        List<TileInfo> tiles = new ArrayList<>() {};

        // Open TSX file
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(Main.class.getResourceAsStream("../res/maps/" + fileName));
        document.getDocumentElement().normalize();

        // Get tileset, image, and tile nodes
        NamedNodeMap tilesetData = document.getElementsByTagName("tileset").item(0).getAttributes();
        NamedNodeMap imageData = document.getElementsByTagName("image").item(0).getAttributes();
        NodeList tilesData = document.getElementsByTagName("tile");

        // Loop through each tile element and create a new TileInfo instance and add it to tile array
        for (int i = 0; i < tilesData.getLength(); i++) {

            if (tilesData.item(i).getAttributes().getNamedItem("id").getNodeValue() != "0") {
            
                tiles.add(new TileInfo(tilesData.item(i).getAttributes().getNamedItem("id").getNodeValue(), 
                    tilesData.item(i).getAttributes().getNamedItem("type").getNodeValue()));

            }

        }

        // Collect, parse, and return all data from the TSX file and put into into a new TSXInfo instance
        return new TSXInfo(
            
            tilesetData.getNamedItem("version").getNodeValue(), 
            tilesetData.getNamedItem("tiledversion").getNodeValue(), 
            tilesetData.getNamedItem("name").getNodeValue(), 
            Integer.parseInt(tilesetData.getNamedItem("tilewidth").getNodeValue()), 
            Integer.parseInt(tilesetData.getNamedItem("tileheight").getNodeValue()), 
            Integer.parseInt(tilesetData.getNamedItem("tilecount").getNodeValue()), 
            Integer.parseInt(tilesetData.getNamedItem("columns").getNodeValue()), 
            imageData.getNamedItem("source").getNodeValue(), 
            Integer.parseInt(imageData.getNamedItem("width").getNodeValue()), 
            Integer.parseInt(imageData.getNamedItem("height").getNodeValue()),
            tiles
            
        );

    }

}
