package com.duggan.bounty.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void clickCancel(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Inventory open = e.getClickedInventory();
        if (e.getClickedInventory() != null)
            if (open.getName().equals("Wanted")) {
                e.setCancelled(true);
            }
    }

}
