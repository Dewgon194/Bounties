package com.duggan.bounty;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Bounties {

    Plugin plugin = Main.getPlugin(Main.class);

    public void saveBounties(String bounty, String placer, Double value) {

        plugin.getConfig().set("bounties." + bounty + "." + placer + ".value", value);
        plugin.saveConfig();
    }

    public Double getTotal(String bounty) {

        Double bountyTotal = 0.0;

        for (String id : plugin.getConfig().getConfigurationSection("bounties." + bounty).getKeys(false)) {

            Double valuepp = plugin.getConfig().getDouble("bounties." + bounty + "." + id + ".value");
            bountyTotal = bountyTotal + valuepp;

        }
        System.out.println(bountyTotal);
        return bountyTotal;
    }

    public void configDelete(String bounty) {

        plugin.getConfig().set("bounties." + bounty, null);
        plugin.saveConfig();

    }

    public ArrayList<String> bountyPlayers() {
        ArrayList<String> players = new ArrayList<>();
        for (String id : plugin.getConfig().getConfigurationSection("bounties.").getKeys(false)) {
            if (Bukkit.getPlayer(UUID.fromString(id)) != null) {
                players.add(Bukkit.getPlayer(UUID.fromString(id)).getName());
            }
        }
        return players;


    }

    public void bountyPlacers(String bounty) {
        for (String id : plugin.getConfig().getConfigurationSection("bounties." + bounty + ".").getKeys(false)) {
            if (Bukkit.getPlayer(UUID.fromString(id)) != null) {
                Bukkit.getPlayer(UUID.fromString(id)).sendMessage(ChatColor.AQUA + "Your bounty on " + ChatColor.GREEN + Bukkit.getPlayer(UUID.fromString(bounty)).getName() + "has been fulfilled." );
            }
        }
    }
}

