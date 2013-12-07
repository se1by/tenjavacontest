package com.bitbyterstudios.kitty_cannon;

import org.bukkit.plugin.java.JavaPlugin;

public class KittyCannon extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getCommand("kitty").setExecutor(new KittyExecutor());
    }
}
