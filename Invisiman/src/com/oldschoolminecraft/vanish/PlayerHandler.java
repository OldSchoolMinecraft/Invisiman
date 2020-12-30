package com.oldschoolminecraft.vanish;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerHandler extends PlayerListener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if (Invisiman.instance.statusMap.containsKey(event.getPlayer()))
            Invisiman.instance.setVanish(event.getPlayer(), Invisiman.instance.statusMap.get(event.getPlayer()));
        if (event.getPlayer().hasPermission("invisiman.see") || event.getPlayer().isOp())
            for (Player player : Bukkit.getServer().getOnlinePlayers())
                event.getPlayer().showPlayer(player);
    }

    @EventHandler
    public void onPlayerItemPickup(PlayerPickupItemEvent event)
    {
        if (Invisiman.instance.isVanished(event.getPlayer()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if (Invisiman.instance.isVanished(event.getPlayer()) && event.getAction() == Action.PHYSICAL && event.hasBlock() && (event.getMaterial() == Material.WOOD_PLATE || event.getMaterial() == Material.STONE_PLATE))
            event.setCancelled(true);
    }
}
