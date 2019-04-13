package com.duggan.bounty.listener;

import com.duggan.bounty.Bounties;
import com.duggan.bounty.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private Bounties bounties;
    public PlayerDeathListener(Bounties bounties){
        this.bounties = bounties;
    }
    @EventHandler
    public void bountyKill(PlayerDeathEvent e){
        Player player = e.getEntity();
        String bounty = e.getEntity().getUniqueId().toString();
        Player killer = e.getEntity().getKiller();
        if (e.getEntity().getKiller() != null && player != killer && bounties.getTotal(bounty) != null) {
            Player bountyKiller = killer;
            if (bounties.getTotal(bounty) > 0) {
                Utils.giveMoney(bountyKiller.getName(), bounties.getTotal(bounty));
                killer.sendMessage(ChatColor.AQUA + "You fulfilled a bounty and earned "+ChatColor.GREEN + "$" + bounties.getTotal(bounty));
                bounties.bountyPlacers(bounty);
                bounties.configDelete(bounty);

            }



        }

    }
}
