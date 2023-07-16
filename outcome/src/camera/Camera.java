package camera;

import game.GameEventManager;
import game.GameSettings;
import gfx.Renderer;
import map.Tilemap;

public class Camera {
    
    private GameEventManager game;

    public int x;
    public int y;
    public Tilemap currentTilemap = null;
    public int width = this.currentTilemap.getTmxInfo().getWidth() * GameSettings.TILESIZE;
    public int height = this.currentTilemap.getTmxInfo().getHeight() * GameSettings.TILESIZE;

    public Camera(GameEventManager game) {

        this.game = game;

        this.x = (Renderer.FRAMEBUFFER_BASE_WIDTH / 2) - (this.width / 2);
        this.y = (Renderer.FRAMEBUFFER_BASE_HEIGHT / 2) - (this.height / 2);

    }

}
