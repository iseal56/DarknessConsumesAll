package dev.iseal.darknessconsumesall.Listeners;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.text.TranslatableTextContent;

public class MuteDeathMessageListener {

    public void init() {
        ServerMessageEvents.ALLOW_GAME_MESSAGE.register((server, message, overlay) -> {
            if (message.getContent() instanceof TranslatableTextContent translatableText) {
                String key = translatableText.getKey();

                // Replace "death.attack.example" with your target translation key
                return !key.startsWith("death.attack.consumed_by_darkness"); // Block the message from chat
            }
            return true; // Allow other messages
        });
    }

}
