package com.bitbyterstudios.kitty_cannon;

import org.bukkit.Material;

public class Utils {

    public static Material materialFromString(String s) {
        return Material.getMaterial(s.toUpperCase().replace(" ", "_"));
    }
}
