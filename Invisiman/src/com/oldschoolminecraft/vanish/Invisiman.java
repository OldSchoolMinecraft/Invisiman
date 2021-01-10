package com.oldschoolminecraft.vanish;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Invisiman extends JavaPlugin
{
    public static Invisiman instance;

    public HashMap<Player, Boolean> statusMap = new HashMap<Player, Boolean>();

    public void onEnable()
    {
        instance = this;

        getServer().getPluginManager().registerEvents(new PlayerHandler(), this);
        getServer().getPluginManager().registerEvents(new EntityHandler(), this);

        System.out.println("Invisiman enabled.");
    }

    public void onDisable()
    {
        System.out.println("Invisiman disabled.");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "You must be a player to do that!");
            return true;
        }

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("vanish"))
        {
            if (player.hasPermission("invisiman.vanish") || player.isOp())
                setVanish(player, statusMap.containsKey(player) ? !statusMap.get(player) : true);
            else {
                player.sendMessage(ChatColor.RED + "You aren't allowed to do that!");
                return true;
            }
        }

        return true;
    }

    public void setVanish(Player player, boolean vanished)
    {
        for (Player ply : getServer().getOnlinePlayers())
        {
            if (vanished && !ply.hasPermission("invisiman.see") && !ply.isOp())
                ply.hidePlayer(player);
            else
                ply.showPlayer(player);

            statusMap.put(player, vanished);
        }

        if (vanished)
            player.sendMessage(ChatColor.GREEN + "You are now INVISIBLE.");
        else
            player.sendMessage(ChatColor.GREEN + "You are now VISIBLE.");
    }

    public boolean isVanished(Player player)
    {
        return statusMap.containsKey(player) ? statusMap.get(player) : false;
    }
}
