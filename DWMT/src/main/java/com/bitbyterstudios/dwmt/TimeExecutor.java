package main.java.com.bitbyterstudios.dwmt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class TimeExecutor implements CommandExecutor {

    private DontWasteMyTime plugin;
    private String kitten;

    public TimeExecutor(DontWasteMyTime plugin) {
        this.plugin = plugin;
        kitten = "  /\\\\_/\\\\ \n" +
                 "  >^.^<.---. \n" +
                 " _'-`-'     )\\\\ \n" +
                 "(6--\\\\ |--\\\\ (`.`-. \n" +
                 "    --'  --'  ``-'BP   cat";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("transfer".equalsIgnoreCase(args[0])) {
            handleTransfer(sender, args);
            return true;
        } else if ("dare".equalsIgnoreCase(args[0])) {
            handleDare(sender, args);
            return true;
        } else if ("settime".equalsIgnoreCase(args[0])) {
            handleSetTime(sender, args);
            return true;
        } else if ("addtime".equalsIgnoreCase(args[0])) {
            handleAddTime(sender, args);
            return true;
        } else if ("kitten".equalsIgnoreCase(args[0])) {
            sender.sendMessage(kitten);
            return true;
        } else {
            showHelp(sender);
        }


        return false;
    }

    private void handleTransfer(CommandSender sender, String[] args) {
        if (!sender.hasPermission(plugin.getName() + ".transfer")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission for that!");
            return;
        }
        if (args.length < 3 || !Utils.isInt(args[2])) {
            showTransferHelp(sender);
            return;
        }

        String name = args[1];
        int amount = Integer.parseInt(args[2]); //TODO: parse 1y 2mo 3w 4d 5h 6mi 7s

        if (Bukkit.getPlayerExact(name) == null) {
            sender.sendMessage(ChatColor.RED + name + " isn't online.");
            return;
        }
        if (amount < 1) {
            sender.sendMessage(ChatColor.RED + "Please enter an amount > 0!");
            return;
        }

        plugin.getTimeManager().transfer(sender.getName(), name, amount);
    }

    private void handleDare(CommandSender sender, String[] args) {
        if (!sender.hasPermission(plugin.getName() + ".dare")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission for that!");
            return;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Sorry, only for players :(");
            return;
        }
        if (args.length < 2) {
            showDareHelp(sender);
            return;
        }

        Player player = (Player) sender;

        if ("accept".equalsIgnoreCase(args[1]) || "decline".equalsIgnoreCase(args[1])) {
            if (!player.hasMetadata(plugin.getName() + ".dared")) {
                player.sendMessage(ChatColor.RED + "No one dared you.");
                return;
            }

            String otherName = Utils.getMetaData(player, plugin.getName() + ".dared", plugin).asString();
            Player otherPlayer = Bukkit.getPlayerExact(otherName);

            if ("decline".equalsIgnoreCase(args[1])) {
                player.sendMessage(plugin.getPrefix() + "Challenge declined.");

                if (otherPlayer != null) {
                    otherPlayer.sendMessage(plugin.getPrefix() + player.getName() + " declined your challenge.");
                }
                return;
            } else {
                if (otherPlayer == null) {
                    player.sendMessage(plugin.getPrefix() + otherName + " is offline...");
                    return;
                }

                boolean death = false;

                if ("death".equalsIgnoreCase(args[2])) {
                    death = true;
                }

                startFight(player, otherPlayer, death);
                return;
            }
        }

        Player toDare = Bukkit.getPlayerExact(args[1]);
        boolean death = false;
        if ("death".equalsIgnoreCase(args[2])) {
            death = true;
        }
        if (toDare == null) {
            player.sendMessage(plugin.getPrefix() + args[1] + " is currently offline.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(plugin.getPrefix());
        sb.append(player.getName());
        sb.append(" dared you");
        if (death) {
            sb.append(" to death");
        }
        sb.append("!\nEnter /intime dare [accept/decline]");

        toDare.sendMessage(sb.toString());
        toDare.setMetadata(plugin.getName() + ".dared", new FixedMetadataValue(plugin, player.getName()));

        player.sendMessage(plugin.getPrefix() + "You dared " + args[1] + "!");
    }

    private void handleSetTime(CommandSender sender, String[] args) {
        if (!sender.hasPermission(plugin.getName() + ".admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission for that!");
            return;
        }
        if (args.length < 4 || !Utils.isInt(args[2])) {
            showSetTimeHelp(sender);
            return;
        }

        String name = args[1];
        int amount = Integer.parseInt(args[2]);

        plugin.getTimeManager().setTime(name, amount);

        sender.sendMessage(plugin.getPrefix() + "Set time for " + name + " to " + amount + " seconds.");
    }

    private void handleAddTime(CommandSender sender, String[] args) {
        if (!sender.hasPermission(plugin.getName() + ".admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission for that!");
            return;
        }
        if (args.length < 4 || !Utils.isInt(args[2])) {
            showAddTimeHelp(sender);
            return;
        }

        String name = args[1];
        int amount = Integer.parseInt(args[2]);

        plugin.getTimeManager().subtract(name, amount * -1);

        sender.sendMessage(plugin.getPrefix() + "Added " + amount + " seconds to " + name + "s time.");
    }

    private void startFight(Player player, Player otherPlayer, boolean death) {
        //TODO: ADD TWO CONVERSATIONS FOR FIGHT
    }

    private void showHelp(CommandSender sender) {
        showTransferHelp(sender);
        showDareHelp(sender);
        showSetTimeHelp(sender);
        showAddTimeHelp(sender);
    }

    private void showTransferHelp(CommandSender sender) {
        sender.sendMessage("/intime transfer [name] [amount in seconds]");
    }

    private void showDareHelp(CommandSender sender) {
        sender.sendMessage("/intime dare [name] (optional)[death]");
    }

    private void showSetTimeHelp(CommandSender sender) {
        sender.sendMessage("/intime setTime [name] [amount in seconds]"); //TODO: add parse as seen above
    }

    private void showAddTimeHelp(CommandSender sender) {
        sender.sendMessage("/intime addTime [name] [amount in seconds]"); //TODO: add parse as seen above
    }
}
