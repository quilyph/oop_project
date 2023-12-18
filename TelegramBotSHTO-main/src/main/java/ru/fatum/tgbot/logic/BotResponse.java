package ru.fatum.tgbot.logic;


public record BotResponse(String responseText, long chatId, int state) {
}