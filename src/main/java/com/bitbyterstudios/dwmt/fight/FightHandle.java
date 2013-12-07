package com.bitbyterstudios.dwmt.fight;

import com.bitbyterstudios.dwmt.DontWasteMyTime;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FightHandle {

    private DontWasteMyTime plugin;
    private BukkitRunnable runner;
    private String upperHand; //who is winning atm?
    private Player player1;
    private Player player2;
    private Conversation conv1;
    private Conversation conv2;
    private long loss_per_tick;

    private ConversationFactory factory;

    public FightHandle(DontWasteMyTime plugin, Player player1, Player player2, boolean death) {
        this.plugin = plugin;
        this.player1 = player1;
        this.player2 = player2;
        loss_per_tick = plugin.getConfig().getLong("fight_time_loss", 30);

        factory = new ConversationFactory(plugin)
                .withModality(false)
                .withPrefix(new FightConversationPrefix(plugin))
                .withFirstPrompt(new FightPrompt(this))
                .thatExcludesNonPlayersWithMessage("sorry, consoles doesn't have hands :(")
                .addConversationAbandonedListener(new FightAbandonendListener(this));
        if (!death) {
            factory.withEscapeSequence("abort");
        }
    }

    public void start() {
        runner = new BukkitRunnable() {
            @Override
            public void run() {
                if (upperHand == null) {
                    return;
                }
                if (upperHand.equals(player1.getName())) {
                    plugin.getTimeManager().transfer(player2.getName(), player1.getName(), (int) loss_per_tick);
                } else {
                    plugin.getTimeManager().transfer(player1.getName(), player2.getName(), (int) loss_per_tick);
                }

            }
        };
        runner.runTaskTimer(plugin, 0, 1);
        conv1 = factory.buildConversation(player1);
        conv1.begin();
        conv2 = factory.buildConversation(player2);
        conv2.begin();
    }

    public void end() {
        runner.cancel();
        conv1.abandon();
        conv2.abandon();
        System.out.println("ended");
    }


    public void setUpperHand(String upperHand) {
        this.upperHand = upperHand;
    }
}
