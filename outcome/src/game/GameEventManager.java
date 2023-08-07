package game;

import camera.Camera;
import entity.player.Player;
import entity.tile.Tile;
import gfx.Renderer;
import gfx.ui.elements.BoxElement;
import gfx.ui.elements.TextBoxElement;
import map.Tilemap;

public final class GameEventManager {

    public Tilemap tilemap = new Tilemap(this, new int[] {0, 0});
    public Player player = new Player(32, 32, this);
    public Camera camera = new Camera(this, this.player);

    public BoxElement boxElement = new BoxElement(0, 0, 9, 9);
    public TextBoxElement textBoxElement = new TextBoxElement(0, 64, "guys this is really crazy....");

    public GameEventManager() {
        
        this.tilemap.createTiles();

    }


    
    public final void init() {

        // 

    }



    public final void update() {

        this.player.update();

        for (Tile tile : this.tilemap.getTiles()) {
            
            tile.update();

        }

        this.camera.update();

    }



    public final void draw() {

        Renderer.clear(0x000000);
        
        for (Tile tile : this.tilemap.getTiles()) {
            
            Renderer.drawImage(tile.image, tile.drawX, tile.drawY);

        }

        this.boxElement.draw();
        this.textBoxElement.draw();

        this.player.draw();

    }
    
}
