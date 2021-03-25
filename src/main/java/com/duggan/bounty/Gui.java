package com.duggan.bounty;

import com.deanveloper.skullcreator.SkullCreator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;

public class Gui implements CommandExecutor {

    private Bounties bounties;

    public Gui(Bounties bounties) {
        this.bounties = bounties;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Inventory inv = Bukkit.createInventory(null, 54, "Wanted");
        Integer check = 0;
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        for (String p : bounties.bountyPlayers()) {
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "$" + bounties.getTotal(Bukkit.getPlayerExact(p).getUniqueId().toString()));
            ItemStack skull = SkullCreator.withName(item, p);
            ItemMeta skullMeta = skull.getItemMeta();
            skullMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + p);
            skullMeta.setLore(lore);
            skull.setItemMeta(skullMeta);
            inv.setItem(check, skull);
            check++;
            Player player = (Player) sender;
            player.openInventory(inv);
        }
        SkullCreator.withName(item, "Bob");

        return true;
    }

}
