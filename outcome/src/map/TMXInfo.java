package map;

public final class TMXInfo {
    
    private String version;
    private String tiledVersion;
    private String orientation;
    private String renderOrder;
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private int infinite;
    private String source;
    private String csvData;
    private String[][] mapIdData;

    public TMXInfo(String version, String tiledVersion, String orientation, String renderOrder, int width, int height,
        int tileWidth, int tileHeight, int infinite, String source, String csvData) {
        
        this.version = version;
        this.tiledVersion = tiledVersion;
        this.orientation = orientation;
        this.renderOrder = renderOrder;
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.infinite = infinite;
        this.source = source;
        this.csvData = csvData;
        this.mapIdData = new String[this.height][this.width];

        // Remove first blank line of CSV data then split it into a 2D string array for individual ids
        String[] rows = new String[this.height];

        // Add rows to new array
        for (int i = 1; i < this.height + 1; i++) {
            
            rows[i - 1] = this.csvData.split("\n")[i];

        }

        // Add ids to mapIdData

        int i = 0;

        for (String row : rows) {
            
            this.mapIdData[i] = row.split(",");
            i++;

        }

    }



    public String getVersion() {
        
        return version;

    }



    public String getTiledVersion() {
        
        return tiledVersion;

    }



    public String getOrientation() {
        
        return orientation;

    }



    public String getRenderOrder() {
        
        return renderOrder;

    }



    public int getWidth() {
        
        return width;

    }



    public int getHeight() {
        
        return height;

    }



    public int getTileWidth() {
        
        return tileWidth;

    }



    public int getTileHeight() {
        
        return tileHeight;

    }



    public int getInfinite() {
        
        return infinite;

    }



    public String getSource() {
        
        return source;

    }



    public String getCsvData() {
        
        return csvData;

    }



    public String[][] getMapIdData() {

        return mapIdData;

    }

}
