package com.bitbyterstudios.dwmt.fight;

import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationAbandonedListener;

public class FightAbandonendListener implements ConversationAbandonedListener {

    private FightHandle handle;

    public FightAbandonendListener(FightHandle handle) {
        this.handle = handle;
    }

    @Override
    public void conversationAbandoned(ConversationAbandonedEvent conversationAbandonedEvent) {
        System.out.println("abandoned");
        handle.end();
    }
}
