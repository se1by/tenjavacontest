package com.bitbyterstudios.kitty_cannon;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventListener implements Listener {

    private KittyCannon plugin;
    private int speed;

    public EventListener(KittyCannon plugin) {
        this.plugin = plugin;
        speed = plugin.getConfig().getInt("kitty_speed");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("KittyCannon")) {
            return;
        }

        if (!event.getPlayer().hasPermission("KittyCannon.Shoot")) {
            return;
        }

        Player player = event.getPlayer();
        Ocelot kitty = (Ocelot) player.getLocation().getWorld().spawnEntity(player.getEyeLocation(), EntityType.OCELOT);

        kitty.setVelocity(player.getEyeLocation().getDirection().clone().normalize().multiply(speed));
        new KittyRunner(new Kitty(kitty, KittyType.NUKE)).runTaskTimer(plugin, 20, 5);
    }
}
