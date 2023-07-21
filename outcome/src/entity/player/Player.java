package entity.player;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.newt.event.KeyEvent;

import common.Commons;
import entity.Entity;
import entity.tile.Tile;
import event.KeyInfo;
import game.GameEventManager;
import game.GameSettings;
import gfx.Renderer;
import tileset.Spritesheet;

public final class Player extends Entity {

    public Rectangle rect;
    public Rectangle groundRect;
    public int width = 8;
    public int height = 8;
    public int speed = 2;
    public float velX = 0;
    public float velY = 0;

    public boolean collidedUp = false;
    public boolean collidedLeft = false;
    public boolean collidedDown = false;
    public boolean collidedRight = false;
    public List<Boolean> collisions = new ArrayList<>() {};

    public boolean canJump = false;

    public Player(int x, int y, GameEventManager game) {
        
        super(x, y, 8, 8, game);
        
        this.rect = new Rectangle(this.x, this.y, 8, 8);
        this.groundRect = new Rectangle(this.x, this.y + 8, 8, 8);
        this.image = Spritesheet.getImage("2");
        
    }
    


    @Override
    public void update() {

        this.drawX = this.x;
        this.drawY = this.y;

        
        // this.drawX = (Renderer.FRAMEBUFFER_BASE_WIDTH / 2) - (this.width / 2);
        // this.drawY = (Renderer.FRAMEBUFFER_BASE_HEIGHT / 2) - (this.height / 2);

        // Call update methods
        this.applyGravity();
        this.applyXVelocity();

        

        this.getKeyInput();

    }



    /**
     * Applies gravitational calculations to y velocity, then in turn position
     */
    public final void applyGravity() {

        // Apply gravitational calculations
        this.velY += GameSettings.GRAVITY;
        this.velY += GameSettings.GRAVITY * 0.5f;
        this.y += this.velY;

    }



    /**
     * Applies x velocity to position
     */
    public void applyXVelocity() {

        this.x += this.velX;

    }



    /**
     * Gets input from keyboard
     */
    public final void getKeyInput() {

        // Zero x velocity to stop player if no x input is detected
        this.velX = 0;

        if (KeyInfo.isKeyPressed(KeyEvent.VK_LEFT) && !this.collidedLeft) {

            this.velX = -this.speed;

        }

        if (KeyInfo.isKeyPressed(KeyEvent.VK_RIGHT) && !this.collidedRight) {

            this.velX = this.speed;

        }

        if (KeyInfo.isKeyPressed(KeyEvent.VK_C)) {

            this.jump();

        }

        if (KeyInfo.isKeyPressed(KeyEvent.VK_0)) {

            this.x = 32;
            this.y = 32;
            this.velX = 0;
            this.velY = 0;

        }

    }



    /**
     * Causes player to jump
     */
    public final void jump() {

        if (this.collidedDown) {

            this.velY = -5;
            // this.collidedDown = false;

        }

    }



    public void draw() {

        Renderer.drawImage(this.image, this.drawX, this.drawY);

    }
    
}
