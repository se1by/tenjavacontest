package com.bitbyterstudios.dwmt.time;

import com.bitbyterstudios.dwmt.DontWasteMyTime;

import java.util.HashMap;
import java.util.Map;

public class YamlTimeManager implements ITimeManager {

    private DontWasteMyTime plugin;
    private HashMap<String, Time> cachedTime;

    public YamlTimeManager(DontWasteMyTime plugin) {
        this.plugin = plugin;
        cachedTime = new HashMap<String, Time>();
    }

    @Override
    public Time getTime(String name) {
        if (cachedTime.containsKey(name)) {
            return cachedTime.get(name);
        }
        Time time = new Time(plugin.getTimeConfig().getLong(name));
        cachedTime.put(name, time);
        return time;
    }

    @Override
    public void setTime(String name, Time time) {
       cachedTime.put(name, time);
       plugin.getTimeConfig().set(name, time.getSeconds());
       plugin.saveTimeConfig();
    }

    @Override
    public void setTime(String name, long seconds) {
       setTime(name, new Time(seconds));
    }

    @Override
    public boolean hasTime(String name) {
        if (cachedTime.containsKey(name)) {
            return true;
        }
        if (plugin.getTimeConfig().contains(name)) {
            return true;
        }
        return false;
    }

    @Override
    public void subtract(String name, long seconds) {
       getTime(name).add(seconds * -1);
    }

    @Override
    public void transfer(String giver, String receiver, int seconds) {
        subtract(giver, seconds);
        save(giver);
        subtract(receiver, seconds * -1);
        save(receiver);
    }

    @Override
    public void save(String name) {
        if (!cachedTime.containsKey(name)) {
            return;
        }
        plugin.getTimeConfig().set(name, cachedTime.get(name).getSeconds());
        plugin.saveTimeConfig();
    }

    @Override
    public void saveCache() {
        for (Map.Entry<String, Time> e : cachedTime.entrySet()) {
            plugin.getTimeConfig().set(e.getKey(), e.getValue().getSeconds());
        }
        plugin.saveTimeConfig();
    }
}
