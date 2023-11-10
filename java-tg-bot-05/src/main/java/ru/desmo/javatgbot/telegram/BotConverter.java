package ru.desmo.javatgbot.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.desmo.javatgbot.logic.BotRequest;

public class BotConverter {
    public BotRequest makeRequest(Update update){
        String message = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();

        return new BotRequest(message, chatId);
    }
}
