package com.bitbyterstudios.kitty_cannon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class KittyRunner extends BukkitRunnable {

    private Kitty kitty;

    public KittyRunner(Kitty kitty) {
        this.kitty = kitty;
    }

    @Override
    public void run() {
        Location kittyLoc = kitty.getKitty().getLocation();
        if (!kittyLoc.getBlock().getType().equals(Material.AIR) ||
                containsPlayer(kitty.getKitty().getNearbyEntities(2, 2, 2)) ||
                !kittyLoc.clone().add(0, -1, 0).getBlock().getType().equals(Material.AIR)) {
            kitty.getKitty().remove();

            if (kitty.getType().equals(KittyType.SINGLE)) {
                kittyLoc.getWorld().createExplosion(kittyLoc.getX(), kittyLoc.getY(), kittyLoc.getZ(), 4f);
            } else if (kitty.getType().equals(KittyType.RING)) {

            } else {
                kittyLoc.getWorld().createExplosion(kittyLoc.getX(), kittyLoc.getY(), kittyLoc.getZ(), 50f);
            }
            cancel();
        }
    }

    private boolean containsPlayer(List<Entity> entityList) {
        for (Entity e : entityList) {
            if (e instanceof Player) {
                return true;
            }
        }
        return false;
    }
}
