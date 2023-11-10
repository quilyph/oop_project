package ru.desmo.javatgbot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.desmo.javatgbot._interface.MessageHandler;
import ru.desmo.javatgbot.telegram.BotConverter;
import ru.desmo.javatgbot.logic.EchoMessageHandler;
import ru.desmo.javatgbot.telegram.TelegramBot;

@SpringBootApplication
public class Main {
	public static void main(String[] args) throws TelegramApiException {
		MessageHandler messageHandler = new EchoMessageHandler();
		BotConverter botConverter = new BotConverter();
		TelegramBot tgBot = new TelegramBot(botConverter, messageHandler);

		TelegramBotsApi tgApi = new TelegramBotsApi(DefaultBotSession.class);
		tgApi.registerBot(tgBot);
	}
}
