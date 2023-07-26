package entity.item.items;

import entity.item.Item;
import gfx.ImageResource;

public class TestItem extends Item {

    public TestItem(String name, String description, ImageResource image) {
        
        super(name, description, image);

        this.name = "Test Item #1";
        this.description = "";

    }
    
}
