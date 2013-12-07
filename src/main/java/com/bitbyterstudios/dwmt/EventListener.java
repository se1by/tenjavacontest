package com.bitbyterstudios.dwmt;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {

    private DontWasteMyTime plugin;

    public EventListener(DontWasteMyTime plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!plugin.getTimeManager().hasTime(event.getPlayer().getName())) {
            event.getPlayer().sendMessage(plugin.getPrefix() + "Happy birthday!\n" +
                    "25 years is a long time to be a kid, now it gets serious.\n" +
                    "You got one year, make the best out of it.");
            plugin.getTimeManager().setTime(event.getPlayer().getName(), 29030400);
        }
    }
}
