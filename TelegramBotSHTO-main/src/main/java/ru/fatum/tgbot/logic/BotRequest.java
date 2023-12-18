package ru.fatum.tgbot.logic;

public record BotRequest(String requestText, long chatId) {
}