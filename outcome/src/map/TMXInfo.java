package map;

public final class TMXInfo {
    
    public String version;
    public String tiledVersion;
    public String orientation;
    public String renderOrder;
    public int width;
    public int height;
    public int tileWidth;
    public int tileHeight;
    public int infinite;
    public String source;
    public String data;

    public TMXInfo(String version, String tiledVersion, String orientation, String renderOrder, int width, int height,
        int tileWidth, int tileHeight, int infinite, String source, String data) {
        
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
        this.data = data;

    }

}
