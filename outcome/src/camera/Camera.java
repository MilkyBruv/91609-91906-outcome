package camera;

import game.GameEventManager;
import game.GameSettings;
import gfx.Renderer;
import map.Tilemap;

public class Camera {
    
    private static GameEventManager game;

    public static int x;
    public static int y;
    public static Tilemap currentTilemap = game.tilemap;
    public static int width = currentTilemap.getTmxInfo().getWidth() * GameSettings.TILESIZE;
    public static int height = currentTilemap.getTmxInfo().getHeight() * GameSettings.TILESIZE;

    public Camera(GameEventManager _game) {

        game = _game;

        x = (Renderer.FRAMEBUFFER_BASE_WIDTH / 2) - (width / 2);
        y = (Renderer.FRAMEBUFFER_BASE_HEIGHT / 2) - (height / 2);

    }

}
