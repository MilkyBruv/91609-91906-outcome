package event;

import asset.AssetManager;
import gfx.Renderer;
import tileset.Spritesheet;

public final class GameEventManager {

    public GameEventManager() {
        
        Spritesheet.mapTiles();

    }


    
    public final void init() {

        // 

    }



    public final void update() {

        // 

    }



    public final void draw() {

        Renderer.clear(0x000000);
        Renderer.drawLine(0, 0, 20, 20, 0x00ff00);
        Renderer.drawImage(AssetManager.images.get("image1"), 0, 0);
        Renderer.drawImage(Spritesheet.getImage("2"), 40, 40);
        Renderer.drawText("this is a test... _!!!\"", 0, 32);

    }

}
