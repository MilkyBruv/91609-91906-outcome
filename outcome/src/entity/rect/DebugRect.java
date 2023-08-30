package entity.rect;

import java.awt.Rectangle;

public class DebugRect {

    public int x;
    public int y;
    public int width;
    public int height;
    public int drawX;
    public int drawY;

    public DebugRect(Rectangle rect, int drawX, int drawY) {

        this.x = rect.x;
        this.y = rect.y;
        this.width = rect.width;
        this.height = rect.height;
        this.drawX = drawX;
        this.drawY = drawY;

    }

}
