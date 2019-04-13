package com.duggan.bounty;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlaceBounty implements CommandExecutor {

    private Bounties bounties;

    public PlaceBounty(Bounties bounties) {
        this.bounties = bounties;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            String placer = ((Player) sender).getUniqueId().toString();

            if (args.length == 2) {
                Player victimPlayer = Bukkit.getPlayerExact(args[0]);
                if (victimPlayer != null) {
                    String bounty = victimPlayer.getUniqueId().toString();
                    if (NumberUtils.isNumber(args[1])) {
                        Double bountyValue = Double.valueOf(args[1]);
                        if (bountyValue > 0) {
                            if (Utils.hasEnoughMoney(player.getName(), bountyValue)) {
                                Double value = bountyValue;
                                bounties.saveBounties(bounty, placer, value);
                                bounties.getTotal(bounty);
                                sender.sendMessage(ChatColor.AQUA + "You set a bounty on " + ChatColor.GREEN + victimPlayer.getName());
                                victimPlayer.sendMessage(ChatColor.GREEN + "" + player.getName() + ChatColor.AQUA + " set a bounty on you!");
                                Utils.takeMoney(player.getName(), value);
                            } else {
                                sender.sendMessage(ChatColor.RED + "You don't have enough money!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "Please enter a positive value.");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Please use a positive number.");
                    }

                } else {
                    sender.sendMessage(ChatColor.RED + "This player is not online.");
                }

            } else if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "/placebounty (username) (monetary value)");
            }
        } else {
            System.out.println(ChatColor.RED + "This command cannot be ran from a console.");
        }
        return true;
    }
}

