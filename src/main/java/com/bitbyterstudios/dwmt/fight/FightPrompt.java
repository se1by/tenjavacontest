package com.bitbyterstudios.dwmt.fight;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class FightPrompt implements Prompt {

    private static String[] randomWords = {"car", "rabbit", "kittens", "lemon", "chocolate", "box", "run", "forrest", "run", "lolcat"};
    private int index;

    public FightPrompt(FightHandle handle) {

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

        }
        return null;
    }
}
