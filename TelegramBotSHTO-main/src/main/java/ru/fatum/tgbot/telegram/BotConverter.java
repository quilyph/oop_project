package ru.fatum.tgbot.telegram;
import ru.fatum.tgbot.logic.BotRequest;

import org.telegram.telegrambots.meta.api.objects.Update;


public class BotConverter {
    public BotRequest makeRequest(Update update){
        String message = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        return new BotRequest(message, chatId);
    }
}
