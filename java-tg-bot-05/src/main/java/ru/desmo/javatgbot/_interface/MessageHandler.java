package ru.desmo.javatgbot._interface;

import ru.desmo.javatgbot.logic.BotRequest;

public interface MessageHandler {
    void handle(BotRequest request, AnswerWriter writer);
}