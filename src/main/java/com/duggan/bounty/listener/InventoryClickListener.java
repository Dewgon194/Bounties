package com.duggan.bounty.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void clickCancel(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {
            if (e.getView().getTitle().equals("Wanted")) {
                e.setCancelled(true);
            }
        }
    }
}
