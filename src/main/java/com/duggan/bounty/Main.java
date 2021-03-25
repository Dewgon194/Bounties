package com.duggan.bounty;

import com.duggan.bounty.listener.InventoryClickListener;
import com.duggan.bounty.listener.PlayerDeathListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("placebounty").setExecutor(new PlaceBounty(new Bounties()));
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(new Bounties()), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getCommand("wanted").setExecutor(new Gui(new Bounties()));
        System.out.println(ChatColor.AQUA + "Bounty is enabled");
        instance = this;
    }

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }
}