package map;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import main.Main;

public abstract class TMXMapReader {

    /**
     * Reads TMX file and adds attributes to maps
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static final Map<String, HashMap<String, String>> getMapData(String fileName) throws 
        ParserConfigurationException, SAXException, IOException {

        // Map of all TMX file elements and attributes
        Map<String, HashMap<String, String>> content = new HashMap<String, HashMap<String, String>>() {};
        String csvMap = null;

        // Read TMX file
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(Main.class.getResourceAsStream("../res/maps/" + fileName));
        document.getDocumentElement().normalize();

        // Array of nodes
        String[] nodes = {"map", "tileset", "layer", "data"};

        // Loop through each node name
        for (String node : nodes) {
            
            // Get list of attributes for each node
            NamedNodeMap nodeAttribList = document.getElementsByTagName(node).item(0).getAttributes();
            HashMap<String, String> map = new HashMap<>() {};

            // If getting <data> attributes get the csv map too
            if (node == "data" && csvMap == null) {

                csvMap = document.getElementsByTagName(node).item(0).getTextContent();

            }

            // Add attributes' name and value to map
            for (int i = 0; i < nodeAttribList.getLength(); i++) {
                
                map.put(nodeAttribList.item(i).getNodeName(), nodeAttribList.item(i).getNodeValue());

            }

            // Add map to main map
            content.put(node, map);

        }

        return content;

    }

}
