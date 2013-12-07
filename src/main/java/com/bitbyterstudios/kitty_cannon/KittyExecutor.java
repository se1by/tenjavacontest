package com.bitbyterstudios.kitty_cannon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class KittyExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            return false;
        }
        Player p = (Player) commandSender;
        ItemMeta im = p.getItemInHand().getItemMeta();
        if ("make".equalsIgnoreCase(strings[0])) {
            im.setDisplayName("KittyCannon 3000");
        } else {
            im.setDisplayName("Boring stuff :(");
        }
        p.getItemInHand().setItemMeta(im);
        return true;
    }
}
