package camera;

import entity.Entity;
import game.GameEventManager;
import game.GameSettings;
import gfx.Renderer;

public class Camera {
    
    private GameEventManager game;

    public int x;
    public int y;
    public Entity target;
    public int mapBoundsTop;
    public int mapBoundsBottom;
    public int mapBoundsLeft;
    public int mapBoundsRight;
    public boolean lockedTop = false;
    public boolean lockedBottom = false;
    public boolean lockedLeft = false;
    public boolean lockedRight = false;
    public int width = Renderer.FRAMEBUFFER_BASE_WIDTH;
    public int height = Renderer.FRAMEBUFFER_BASE_HEIGHT;

    public Camera(GameEventManager game, Entity target) {

        this.game = game;
        this.target = target;

        this.mapBoundsTop = 0;
        this.mapBoundsBottom = this.game.tilemap.getTmxInfo().getTotalHeight() - Renderer.FRAMEBUFFER_BASE_HEIGHT;
        this.mapBoundsLeft = 0;
        this.mapBoundsRight = this.game.tilemap.getTmxInfo().getTotalWidth() - Renderer.FRAMEBUFFER_BASE_WIDTH;

    }



    public final void update() {

        this.checkBounds();

        this.x = (this.target.x + (this.target.width / 2)) - (this.width / 2);
        this.y = (this.target.y + (this.target.height / 2)) - (this.height / 2);

    }



    public void checkBounds() {

        if (this.x <= this.mapBoundsLeft) {

            this.lockedLeft = true;

        } else {

            this.lockedLeft = false;

        }

        if (this.x >= this.mapBoundsRight) {

            this.lockedRight = true;

        } else {

            this.lockedRight = false;

        }


        if (this.y <= this.mapBoundsTop) {

            this.lockedTop = true;

        } else {

            this.lockedTop = false;

        }

        if (this.y >= this.mapBoundsBottom) {

            this.lockedBottom = true;

        } else {

            this.lockedBottom = false;

        }

    }

}
