package game;

import camera.Camera;
import entity.player.Player;
import entity.tile.Tile;
import event.KeyInfo;
import gfx.ImageResource;
import gfx.Renderer;
import gfx.ui.elements.BoxElement;
import gfx.ui.elements.SliderBoxElement;
import gfx.ui.elements.TextBoxElement;
import map.Tilemap;
import static game.GameSettings.*;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.newt.event.KeyEvent;

import asset.AssetManager;

public final class GameEventManager {

    public Tilemap tilemap = new Tilemap(this, new int[] {0, 0});
    public Player player = new Player(0, 0, this);
    public Camera camera = new Camera(this, this.player);

    public BoxElement boxElement = new BoxElement(0, 0, 9, 9);
    public TextBoxElement textBoxElement = new TextBoxElement(0, Renderer.FRAMEBUFFER_BASE_HEIGHT - (TILESIZE * 3), 
        "this is some test text!");
    public SliderBoxElement sliderBoxElement = new SliderBoxElement(0, 
        Renderer.FRAMEBUFFER_BASE_HEIGHT - (TILESIZE * 5), "test", 5, 1);

    private List<ImageResource> lightImages = new ArrayList<ImageResource>() {};
    private List<Integer[]> lightImagesPosition = new ArrayList<Integer[]>() {};

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

        if (KeyInfo.isKeyPressed(KeyEvent.VK_A)) {

            this.sliderBoxElement.decrementValue(1);

        } else if (KeyInfo.isKeyPressed(KeyEvent.VK_D)) {

            this.sliderBoxElement.incrementValue(1);

        }

    }



    public final void draw() {

        Renderer.clear(0x000000);
        
        for (Tile tile : this.tilemap.getTiles()) {
            
            Renderer.drawImage(tile.image, tile.drawX, tile.drawY);

            if (tile.id.equals("16")) {
    
                lightImages.add(AssetManager.images.get("light"));
                lightImagesPosition.add(new Integer[] {tile.drawX - TILESIZE, tile.drawY + 3});
    
            }

        }

        // this.boxElement.draw();
        // this.textBoxElement.draw();
        // this.sliderBoxElement.draw();

        this.player.draw();

        for (int i = 0; i < lightImages.size(); i++) {
            
            Renderer.drawImage(lightImages.get(i), lightImagesPosition.get(i)[0], lightImagesPosition.get(i)[1]);

        }

        lightImages.clear();
        lightImagesPosition.clear();

    }
    
}
