package org.launchcode.myInventory.models.forms;

import org.launchcode.myInventory.models.Item;
import org.launchcode.myInventory.models.Player;

import javax.validation.constraints.NotNull;

public class AddPlayerItemForm {

    @NotNull
    private int playerId;

    @NotNull
    private int itemId;

    private Iterable<Item> items;

    private Player player;

    public AddPlayerItemForm(Iterable<Item> items, Player player){
        this.items = items;
        this.player = player;
    }

    public AddPlayerItemForm() {}

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Iterable<Item> getItems() {
        return items;
    }

    public Player getPlayer() {
        return player;
    }
}
