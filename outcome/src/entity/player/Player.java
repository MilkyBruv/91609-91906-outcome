package entity.player;

import java.awt.Rectangle;

import com.jogamp.newt.event.KeyEvent;

import entity.Entity;
import event.GameEventManager;
import event.KeyInfo;
import gfx.Renderer;
import tileset.Spritesheet;

public class Player extends Entity {

    public Rectangle rect;
    public int speed = 2;

    public Player(int x, int y, GameEventManager game) {
        
        super(x, y, game);
        
        this.rect = new Rectangle(0, 0, 8, 8);
        this.image = Spritesheet.getImage("1");
        
    }
    


    @Override
    public synchronized void update() {

        this.drawX = this.x;
        this.drawY = this.y;

        if (KeyInfo.isKeyPressed(KeyEvent.VK_W)) {

            this.y -= 1;

        }

        if (KeyInfo.isKeyPressed(KeyEvent.VK_A)) {

            this.x -= 1;

        }

        if (KeyInfo.isKeyPressed(KeyEvent.VK_S)) {

            this.y += 1;

        }

        if (KeyInfo.isKeyPressed(KeyEvent.VK_D)) {

            this.x += 1;

        }

    }



    public void draw() {

        Renderer.drawImage(this.image, this.drawX, this.drawY);

    }
    
}
