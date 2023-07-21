package entity.player;

import java.awt.Rectangle;

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

    public boolean canJump = false;

    public Player(int x, int y, GameEventManager game) {
        
        super(x, y, 8, 8, game);
        
        this.rect = new Rectangle(this.x, this.y, this.width, this.height);
        this.groundRect = new Rectangle(this.x, this.y + this.height, this.width, 1);
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

        this.rect.x = this.x;
        this.groundRect.x = this.x;
        this.detectTileCollisions("x");
        this.rect.y = this.y;
        this.groundRect.y = this.y + 8;
        this.detectTileCollisions("y");

        this.getKeyInput();

    }



    public final void detectTileCollisions(String dir) {

        // Detect collisions in determined direction (x or y)
        if (dir.equals("x")) {

            // Loop through each tile and check if it is solid
            for (Tile tile : this.game.tilemap.getTiles()) {

                // Check if the tile is close to avoid checking collisions for every tile on the map
                if ((Commons.inRange(tile.y, this.y - this.height * 2, this.y + this.height * 3) || 
                    Commons.inRange(tile.y + tile.height, this.y - this.height * 2, this.y + this.height * 3)) && 
                    (Commons.inRange(tile.x, this.x - this.width * 2, this.x + this.width * 3) || 
                    Commons.inRange(tile.x + tile.width, this.x - this.width * 2, this.x + this.width * 3))) {
                
                    if (tile.type.equals("solid")) {

                        // If the tile is solid and the player is moving, set the position to align with the tile and zero
                        // the player velocity
                        if (this.rect.intersects(tile.rect)) {

                            if (this.velX < 0) {

                                this.x = tile.x + GameSettings.TILESIZE;
                                this.collidedLeft = true;

                            }

                            if (this.velX > 0) {

                                this.x = tile.x - GameSettings.TILESIZE;
                                this.collidedRight = true;

                            }

                            this.rect.x = this.x;
                            this.velX = 0;

                        } else {

                            // Set x collision properties to false if no collisions happen
                            this.collidedLeft = false;
                            this.collidedRight = false;

                        }

                    }

                }

            }

        }

        if (dir.equals("y")) {

            // Loop through each tile and check if it is solid
            for (Tile tile : this.game.tilemap.getTiles()) {

                // Check if the tile is close to avoid checking collisions for every tile on the map
                if ((Commons.inRange(tile.y, this.y - this.height * 2, this.y + this.height * 3) || 
                    Commons.inRange(tile.y + tile.height, this.y - this.height * 2, this.y + this.height * 3)) && 
                    (Commons.inRange(tile.x, this.x - this.width * 2, this.x + this.width * 3) || 
                    Commons.inRange(tile.x + tile.width, this.x - this.width * 2, this.x + this.width * 3))) {

                    if (tile.type.equals("solid")) {

                        // If the tile is solid and the player collides with it, set the position to align with the tile and 
                        // zero the player velocity
                        if (this.rect.intersects(tile.rect)) {

                            if (this.velY < 0) {

                                this.y = tile.y + GameSettings.TILESIZE;
                                this.collidedUp = true;

                            }

                            if (this.velY > 0) {

                                this.y = tile.y - GameSettings.TILESIZE;
                                this.collidedDown = true;

                            }

                            this.rect.y = this.y;
                            this.velY = 0;

                        }
                        
                        // if (!this.rect.intersects(tile.rect) && this.velY > 0) {

                        //     this.collidedDown = false;

                        // }

                    }

                }

            }

        }

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

            this.velY = -3;
            this.collidedDown = false;

        }

    }



    public void draw() {

        Renderer.drawImage(this.image, this.drawX, this.drawY);

    }
    
}
