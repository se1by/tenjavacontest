package com.bitbyterstudios.dwmt;

import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class Utils {

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static MetadataValue getMetaData(LivingEntity entity, String data, JavaPlugin plugin) {
        for (MetadataValue mdv : entity.getMetadata(data)) {
            if (mdv.getOwningPlugin().equals(plugin)) {
                return mdv;
            }
        }
        return null;
    }
}
