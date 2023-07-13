package tileset;

import java.util.List;

public final class TSXInfo {
    
    public String version;
    public String tiledVersion;
    public String name;
    public int tileWidth;
    public int tileHeight;
    public int tileCount;
    public int columns;
    public String source;
    public int width;
    public int height;
    public List<TileInfo> tilesInfo;

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

}
