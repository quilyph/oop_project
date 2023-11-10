package ru.desmo.javatgbot.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.desmo.javatgbot._interface.AnswerWriter;
import ru.desmo.javatgbot._interface.MessageHandler;
import ru.desmo.javatgbot.data_container.BotConfig;
import ru.desmo.javatgbot.logic.BotRequest;
import ru.desmo.javatgbot.logic.BotResponse;

@Component
public class TelegramBot extends TelegramLongPollingBot implements AnswerWriter {
    private final BotConverter botConverter;
    private final MessageHandler messageHandler;

    public TelegramBot(BotConverter botConverter, MessageHandler messageHandler) {
        this.botConverter = botConverter;
        this.messageHandler = messageHandler;
    }

    @Override
    public String getBotUsername() {
        return BotConfig.botName;
    }
    @Override
    public String getBotToken(){
        return BotConfig.token;
    }
    @Override
    public void onUpdateReceived(Update update) {
        BotRequest request = botConverter.makeRequest(update);
        messageHandler.handle(request, this);
    }

    @Override
    public void writeAnswer(BotResponse response) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(response.getResponseText());
        sendMessage.setChatId(response.getChatId());

        try {
            execute(sendMessage);
        }
        catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
