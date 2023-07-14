package map;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import main.Main;

public abstract class TMXReader {

    /**
     * Reads TMX file and adds attributes to and returns a TMXInfo instance
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static final TMXInfo getMapData(String fileName) throws ParserConfigurationException, SAXException, 
        IOException {

        // Open TMX file
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(Main.class.getResourceAsStream("../res/maps/" + fileName));
        document.getDocumentElement().normalize();

        // Get map, tileset, and data nodes
        NamedNodeMap mapData = document.getElementsByTagName("map").item(0).getAttributes();
        NamedNodeMap tilesetData = document.getElementsByTagName("tileset").item(0).getAttributes();
        String csvData = document.getElementsByTagName("data").item(0).getTextContent();

        // Return new TMXInfo instance with collected and parsed map data
        return new TMXInfo(
            
            mapData.getNamedItem("version").getNodeValue(), 
            mapData.getNamedItem("tiledversion").getNodeValue(), 
            mapData.getNamedItem("orientation").getNodeValue(), 
            mapData.getNamedItem("renderorder").getNodeValue(), 
            Integer.parseInt(mapData.getNamedItem("width").getNodeValue()), 
            Integer.parseInt(mapData.getNamedItem("height").getNodeValue()), 
            Integer.parseInt(mapData.getNamedItem("tilewidth").getNodeValue()), 
            Integer.parseInt(mapData.getNamedItem("tileheight").getNodeValue()), 
            Integer.parseInt(mapData.getNamedItem("infinite").getNodeValue()), 
            tilesetData.getNamedItem("source").getNodeValue(), 
            csvData
            
        );

    }

}
