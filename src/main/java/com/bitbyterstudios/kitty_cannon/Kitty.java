package com.bitbyterstudios.kitty_cannon;

import org.bukkit.entity.Ocelot;

public class Kitty {

    private Ocelot kitty;
    private KittyType type;

    public Kitty(Ocelot kitty, KittyType type) {
        this.kitty = kitty;
        this.type = type;
    }

    public KittyType getType() {
        return type;
    }

    public Ocelot getKitty() {
        return kitty;
    }
}
