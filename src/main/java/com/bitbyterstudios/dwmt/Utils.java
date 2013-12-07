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

    public static long parseToSeconds(String[] args) {
        long seconds = 0;

        for (String s : args) {
            int numberlength = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                if (Character.isAlphabetic(chars[i])) {
                    numberlength = i;
                    break;
                }
            }

            if (numberlength == 0 && Utils.isInt(s)) {
                seconds += Integer.parseInt(s);
                continue;
            }
            String[] split = s.split(s.charAt(numberlength) + "");
            int value = Integer.parseInt(split[0]);
            int multiplier = 0;

            if (split[1].equalsIgnoreCase("y")) {
                multiplier = 29030400;
            } else if (split[1].equalsIgnoreCase("mo")) {
                multiplier = 2419200;
            } else if (split[1].equalsIgnoreCase("w")) {
                multiplier = 604800;
            } else if (split[1].equalsIgnoreCase("d")) {
                multiplier = 86400;
            } else if (split[1].equalsIgnoreCase("h")) {
                multiplier = 3600;
            } else if (split[1].equalsIgnoreCase("mi")) {
                multiplier = 60;
            } else if (split[1].equalsIgnoreCase("s")) {
                multiplier = 1;
            } else {
                throw new IllegalArgumentException("Unsupported shortcut!");
            }

            seconds += value * multiplier;
        }

        return seconds;
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
