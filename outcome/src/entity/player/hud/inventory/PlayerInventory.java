package entity.player.hud.inventory;

import entity.item.Item;
import entity.item.ItemList;
import entity.player.Player;

public class PlayerInventory {
    
    private Player player;
    
    public ItemList items = new ItemList();

    public PlayerInventory(Player player) {

        this.player = player;

    }



    public void addItem(Item item) {



    }

}
