package com.oldschoolminecraft.vanish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityHandler extends EntityListener
{
    @EventHandler
    public void onEntityTarget(EntityTargetEvent event)
    {
        if (event.getTarget() instanceof Player)
            if (Invisiman.instance.isVanished((Player) event.getTarget()))
                event.setCancelled(true);
    }
}
