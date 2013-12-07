package com.bitbyterstudios.dwmt.fight;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

public class FightPrompt implements Prompt {

    private FightHandle handle;

    private static String[] randomWords = {"car", "rabbit", "kittens", "lemon", "chocolate", "box", "run", "forrest", "run", "lolcat"};
    private int index;

    public FightPrompt(FightHandle handle) {
        this.handle = handle;
    }

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        index = (int) (Math.random() * 10);
        return "Type: " + randomWords[index];
    }

    @Override
    public boolean blocksForInput(ConversationContext conversationContext) {
        return true;
    }

    @Override
    public Prompt acceptInput(ConversationContext conversationContext, String s) {
        if (s.equals(randomWords[index])) {
            System.out.println("got word");
            handle.setUpperHand(((Player)conversationContext.getForWhom()).getName());
        }
        System.out.println("will now return");
        return new FightPrompt(handle);
    }
}
