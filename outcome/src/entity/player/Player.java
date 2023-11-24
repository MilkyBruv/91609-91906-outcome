package entity.player;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.newt.event.KeyEvent;

import common.Commons;
import entity.Entity;
import entity.player.hud.inventory.PlayerInventory;
import entity.rect.DebugRect;
import entity.tile.Tile;
import event.KeyInfo;
import game.GameEventManager;
import game.GameSettings;
import gfx.ImageResource;
import gfx.Renderer;
import tileset.Tileset;

public final class Player extends Entity {

    public Rectangle rect;
    public Rectangle groundRect;
    public DebugRect debugRect;
    public int width = 16;
    public int height = 32;
    public int speed = 2;
    public float velX = 0;
    public float velY = 0;
    public int collisionDistance = 2;

    public boolean collidedUp = false;
    public boolean collidedLeft = false;
    public boolean collidedDown = false;
    public boolean collidedRight = false;
    public boolean canJump = false;
    public boolean movingLeft = false;
    public boolean movingRight = false;
    public boolean movingUp = false;
    public boolean movingDown = false;
    public boolean facingLeft = false;
    public boolean facingRight = true;
    public boolean moving = false;

    public int frameDirection = PlayerAnimations.RIGHT;
    public int currentFrame = 0;

    private long lastIdleFrameUpdate = 0;

    public List<Boolean> groundRectCollisions = new ArrayList<>();

    public PlayerInventory inventory = new PlayerInventory(this);

    public Player(int x, int y, GameEventManager game) {
        
        super(x, y, game);
        
        this.rect = new Rectangle(this.x + 8, this.y, this.width, this.height);
        this.groundRect = new Rectangle(this.x + 8, this.y + this.height, this.width, 1);
        this.debugRect = new DebugRect(this.rect, this.drawX, this.drawY);
        this.image = Tileset.getImage("17");

        PlayerAnimations.init();
        
    }
    


    @Override
    public void update() {

        // Call update methods
        this.applyGravity();
        this.applyXVelocity();

        this.setDrawPosition();

        this.rect.x = this.x + 8;
        this.groundRect.x = this.x + 8;
        this.debugRect.x = this.rect.x + 8;
        this.debugRect.drawX = this.drawX + 8;
        this.detectTileCollisions("x");
        this.rect.y = this.y;
        this.groundRect.y = this.y + this.height;
        this.debugRect.y = this.rect.y;
        this.debugRect.drawY = this.drawY;
        this.detectTileCollisions("y");

        this.getKeyInput();
        this.setVelocityStates();
        this.setAnimationFrames();

    }



    /**
     * Detects if the supplied tile is within the bounds of the collision range
     * 
     * @param tile - Tile to detect
     * @return A Boolean, true if the tile is in collision range, and false if not.
     */
    public final boolean tileInCollisionRange(Tile tile) {

        // Detect if the supplied tile is within the collision range
        if ((Commons.inRange(tile.y, this.y - this.rect.height * this.collisionDistance, this.y + this.rect.height * this.collisionDistance + 1) || 
            Commons.inRange(tile.y + tile.rect.height, this.y - this.rect.height * this.collisionDistance, this.y + this.rect.height * this.collisionDistance + 1)) && 
            (Commons.inRange(tile.x, this.x + 8 - this.rect.width * this.collisionDistance, this.x + 8 + this.rect.width * this.collisionDistance + 1) || 
            Commons.inRange(tile.x + tile.rect.width, this.x + 8 - this.rect.width * this.collisionDistance, this.x + 8 + this.rect.width * this.collisionDistance + 1))) {

            // If the tile IS in range
            return true;

        }

        // If the tile IS NOT in range
        return false;

    }



    public final void detectTileCollisions(String dir) {

        // Detect collisions in determined direction (x or y)
        if (dir.equals("x")) {

            // Loop through each tile and check if it is solid
            for (Tile tile : this.game.tilemap.getTiles()) {

                // Check if the tile is close to avoid checking collisions for every tile on the map
                if (this.tileInCollisionRange(tile)) {

                    if (tile.type.equals("solid")) {

                        // If the tile is solid and the player is moving, set the position to align with the tile and 
                        // zero the player velocity
                        if (this.rect.intersects(tile.rect)) {

                            if (this.velX < 0) {

                                this.rect.x = tile.x + GameSettings.TILESIZE;
                                this.collidedLeft = true;

                            }

                            if (this.velX > 0) {

                                this.rect.x = tile.x - this.rect.width;
                                this.collidedRight = true;

                            }

                            this.x = this.rect.x - 8;
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

            this.groundRectCollisions.clear();

            // Loop through each tile and check if it is solid
            for (Tile tile : this.game.tilemap.getTiles()) {

                // Check if the tile is close to avoid checking collisions for every tile on the map
                if (this.tileInCollisionRange(tile)) {

                    if (tile.type.equals("solid")) {

                        // If the tile is solid and the player collides with it, set the position to align with the tile and 
                        // zero the player velocity
                        if (this.rect.intersects(tile.rect)) {

                            if (this.velY < 0) {

                                this.y = tile.y + GameSettings.TILESIZE;
                                this.collidedUp = true;

                            }

                            if (this.velY > 0) {

                                this.y = tile.y - this.height;
                                this.collidedDown = true;

                            }

                            this.rect.y = this.y;
                            this.velY = 0;

                        }
                        
                        // Check if the ground rect collides with any tiles
                        if (this.groundRect.intersects(tile.rect)) {

                            this.groundRectCollisions.add(true);

                        } else {

                            this.groundRectCollisions.add(false);

                        }

                    }

                }

            }

            // If the ground rect collides with any tiles then stop the player from moving
            if (!this.groundRectCollisions.contains(true)) {

                this.collidedDown = false;

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



    public final void setVelocityStates() {

        if (this.velX < 0) {

            this.moving = true;
            this.movingLeft = true;
            this.movingRight = false;
            this.movingUp = false;
            this.movingDown = false;

            this.facingLeft = true;
            this.facingRight = false;
            this.frameDirection = PlayerAnimations.LEFT;

        } else if (this.velX > 0) {

            this.moving = true;
            this.movingLeft = false;
            this.movingRight = true;
            this.movingUp = false;
            this.movingDown = false;

            this.facingLeft = false;
            this.facingRight = true;
            this.frameDirection = PlayerAnimations.RIGHT;

        } else if (this.velY < 0) {

            this.moving = true;
            this.movingLeft = false;
            this.movingRight = false;
            this.movingUp = true;
            this.movingDown = false;

        } else if (this.velY > 2) {

            this.moving = true;
            this.movingLeft = false;
            this.movingRight = false;
            this.movingUp = false;
            this.movingDown = true;

        } else if (this.velX == 0 && this.velY == 0) {

            this.moving = false;
            this.movingLeft = false;
            this.movingRight = false;
            this.movingUp = false;
            this.movingDown = false;

        }

    }



    public final void setAnimationFrames() {

        if (this.moving) {

            try {

                this.image = PlayerAnimations.runningFrames[this.frameDirection][(Math.round(this.x / PlayerAnimations.RUNNING_FRAME_INTERVAL)) % 
                    PlayerAnimations.runningFrames[this.frameDirection].length];

                System.out.println("moving");

            } catch (ArrayIndexOutOfBoundsException e) {

                e.printStackTrace();

            }

        }
        
        if (!this.moving) {

            long now = System.currentTimeMillis();

            if (now - this.lastIdleFrameUpdate >= PlayerAnimations.IDLE_FRAME_INTERVAL) {

                FrameInfo frameInfo = this.updateAnimationFrame(PlayerAnimations.idleFrames[this.frameDirection], this.currentFrame);
                this.image = frameInfo.image();
                this.currentFrame = frameInfo.frame();

                System.out.println("NOT MOVING");

                this.lastIdleFrameUpdate = now;

            }

        }

    }



    private final FrameInfo updateAnimationFrame(ImageResource[] frames, int currentFrame) {

        int frameCount = frames.length - 1;

        if (currentFrame == frameCount) {

            return new FrameInfo(frames[0], 0);

        }

        return new FrameInfo(frames[currentFrame + 1], currentFrame + 1);

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



    /**
     * Set current draw position
     */
    public final void setDrawPosition() {

        // Set draw position to centre of display
        this.drawX = (Renderer.FRAMEBUFFER_BASE_WIDTH / 2) - (this.width / 2);
        this.drawY = (Renderer.FRAMEBUFFER_BASE_HEIGHT / 2) - (this.height / 2);

    }



    public void draw() {

        Renderer.drawImage(this.image, this.drawX, this.drawY);
        Renderer.drawRect(new Rectangle(this.debugRect.drawX, this.debugRect.drawY, this.debugRect.width, this.debugRect.height), 0xffffff);

    }
    
}
