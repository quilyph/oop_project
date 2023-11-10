package ru.desmo.javatgbot.logic;

import ru.desmo.javatgbot._interface.AnswerWriter;
import ru.desmo.javatgbot._interface.MessageHandler;

public class EchoMessageHandler implements MessageHandler {
    @Override
    public void handle(BotRequest request, AnswerWriter writer) {
        String userMessage = request.getRequestText();
        String chatId = request.getChatId();
        BotResponse response = new BotResponse(userMessage, chatId);
        writer.writeAnswer(response);
    }
}