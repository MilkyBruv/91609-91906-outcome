package entity;

import java.awt.Rectangle;

import game.GameEventManager;
import gfx.ImageResource;

public class Entity {
    
    protected GameEventManager game;

    public int x;
    public int y;
    public int drawX;
    public int drawY;
    public int width;
    public int height;
    public ImageResource image;
    public Rectangle rect;

    public Entity(int x, int y, int width, int height, GameEventManager game) {

        this.game = game;
        this.x = x;
        this.y = y;
        this.drawX = this.x;
        this.drawX = this.y;
        this.width = width;
        this.height = height;

    }



    public void update() {

        // 

    }

}
