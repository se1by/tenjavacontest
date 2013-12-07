package com.bitbyterstudios.dwmt;

import com.bitbyterstudios.dwmt.time.ITimeManager;
import com.bitbyterstudios.dwmt.time.MysqlTimeManager;
import com.bitbyterstudios.dwmt.time.YamlTimeManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public class DontWasteMyTime extends JavaPlugin {

    private ITimeManager timeManager;
    private BukkitRunnable counter;
    private String prefix;
    private OutOfTime behaviour;
    private String kickMsg;

    //This just loads on request
    private File timeFile;
    private FileConfiguration timeConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        prefix = ChatColor.GOLD + "[" + ChatColor.GREEN + getName() + ChatColor.GOLD + "] " + ChatColor.RESET;

        if ("yaml".equalsIgnoreCase(getConfig().getString("time_manager"))) {
            timeManager = new YamlTimeManager(this);
        } else if ("mysql".equalsIgnoreCase(getConfig().getString("time_manager"))) {
            timeManager = new MysqlTimeManager();
        }

        behaviour = OutOfTime.valueOf(getConfig().getString("out_of_time_behaviour").toUpperCase());
        kickMsg = getConfig().getString("kick_message", "You are dead...");

        long period = getConfig().getLong("counter_interval");
        if (period == 0) {
            period = 200;
        }

        try {
            startCounter(period);
        } catch (Exception e) {
            getLogger().severe(e.getMessage());
        }

        getCommand("intime").setExecutor(new TimeExecutor(this));

        getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }

    @Override
    public void onDisable() {
        counter.cancel();
        getTimeManager().saveCache();
    }

    public String getPrefix() {
        return prefix;
    }

    public ITimeManager getTimeManager() {
        return timeManager;
    }

    public FileConfiguration getTimeConfig() {
        if (timeConfig == null) {
            timeFile = new File(getDataFolder(), "times.yml");

            if (!timeFile.exists()) {
                saveResource("times.yml", false);
            }

            timeConfig = YamlConfiguration.loadConfiguration(timeFile);
        }
        return timeConfig;
    }

    public void saveTimeConfig() {
        try {
            timeConfig.save(timeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startCounter(final long period) throws Exception {
        if (counter != null) {
            throw new Exception("Counter is already running!");
        }
        counter = new BukkitRunnable() {
            @Override
            public void run() {
               for (Player p :getServer().getOnlinePlayers()) {
                    getTimeManager().subtract(p.getName(), (long) (period * 3.623188406));       //3,623188406 = ingame seconds per tick
                    if (getTimeManager().getTime(p.getName()).getSeconds() < 0) {
                        switch (behaviour){
                            case BAN:
                                p.setBanned(true);
                            case KICK:
                                p.kickPlayer(kickMsg);
                            case KILL:
                                p.setHealth(0);
                        }
                    }
               }
            }
        };
        counter.runTaskTimer(this, 0 , period);
    }

}
