package com.bitbyterstudios.dwmt.fight;

import com.bitbyterstudios.dwmt.DontWasteMyTime;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationPrefix;

public class FightConversationPrefix implements ConversationPrefix{

    private String prefix;

    public FightConversationPrefix(DontWasteMyTime plugin) {
        prefix = plugin.getPrefix();
    }
    @Override
    public String getPrefix(ConversationContext conversationContext) {
        return prefix;
    }
}
