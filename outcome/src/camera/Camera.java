package camera;

import entity.Entity;
import game.GameEventManager;
import game.GameSettings;
import gfx.Renderer;
import map.Tilemap;

public class Camera {
    
    private GameEventManager game;

    public int x;
    public int y;
    public Entity target = null;

    public Camera(GameEventManager _game, Entity target) {

        this.game = _game;
        this.target = target;
        
        this.x = (this.target.width / 2) - (Renderer.FRAMEBUFFER_BASE_WIDTH / 2);
        this.y = (this.target.height / 2) - (Renderer.FRAMEBUFFER_BASE_HEIGHT / 2);

    }



    public void update() {
        
        // Set position to centre the target
        this.x = (this.target.width / 2) - (Renderer.FRAMEBUFFER_BASE_WIDTH / 2);
        this.y = (this.target.height / 2) - (Renderer.FRAMEBUFFER_BASE_HEIGHT / 2);
            
    }

}
