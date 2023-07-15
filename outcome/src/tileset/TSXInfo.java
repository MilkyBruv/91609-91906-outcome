package tileset;

import java.util.List;

public final class TSXInfo {
    
    private String version;
    private String tiledVersion;
    private String name;
    private int tileWidth;
    private int tileHeight;
    private int tileCount;
    private int columns;
    private String source;
    private int width;
    private int height;
    private List<TileInfo> tilesInfo;

    public TSXInfo(String version, String tiledVersion, String name, int tileWidth, int tileHeight, int tileCount,
        int columns, String source, int width, int height, List<TileInfo> tilesInfo) {

        this.version = version;
        this.tiledVersion = tiledVersion;
        this.name = name;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileCount = tileCount;
        this.columns = columns;
        this.source = source;
        this.width = width;
        this.height = height;
        this.tilesInfo = tilesInfo;

    }



    public String getVersion() {
        
        return version;

    }



    public String getTiledVersion() {
        
        return tiledVersion;

    }



    public String getName() {
        
        return name;

    }



    public int getTileWidth() {
        
        return tileWidth;

    }



    public int getTileHeight() {
        
        return tileHeight;

    }



    public int getTileCount() {
        
        return tileCount;

    }



    public int getColumns() {
        
        return columns;

    }



    public String getSource() {
        
        return source;

    }



    public int getWidth() {
        
        return width;

    }



    public int getHeight() {
        
        return height;

    }



    public List<TileInfo> getTilesInfo() {
        
        return tilesInfo;

    }

}
