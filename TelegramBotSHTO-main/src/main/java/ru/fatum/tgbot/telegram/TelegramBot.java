package ru.fatum.tgbot.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.fatum.tgbot._interface.AnswerWriter;
import ru.fatum.tgbot.config.BotConfig;
//import ru.fatum.tgbot.model.User;
//import ru.fatum.tgbot.model.UserRepository;
import java.util.ArrayList;
import java.util.List;

import ru.fatum.tgbot.logic.BotResponse;
import ru.fatum.tgbot.logic.MessageHandler;
import ru.fatum.tgbot.tglogic.KeyboardUtils;

@Component
public class TelegramBot extends TelegramLongPollingBot implements AnswerWriter {

    //@Autowired
    //private UserRepository userRepository;

    private enum BotState {
        REGULAR, W8START, MEMORGET, ASKTOTAKETEST, GIVEMEMSTORAGE, qFIRST;
    }
    private BotState currentState = BotState.W8START;
    private boolean isBotEnabled = false;
    final BotConfig config;
    static final String HELP_TEXT = "help text lol";
    private final MessageHandler messageHandler;

    public TelegramBot(BotConfig config) throws TelegramApiException {
        this.messageHandler = new MessageHandler(this);
        this.config = config;
        List <BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "launch the bot"));
        listofCommands.add(new BotCommand("/savememory", "save your memories"));
        listofCommands.add(new BotCommand("/memories", "get your saved memories"));
        this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
    }

    @Override
    public void onUpdateReceived(Update update) {
        messageHandler.handleUpdate(update, this);

    }
    @Override
    public void writeAnswer(BotResponse response) {
        int state = response.state();
        long chatId = response.chatId();
        switch (state){
            case 1:
                KeyboardUtils.sendMessage(this,chatId, HELP_TEXT);
                break;
            case 2:
                KeyboardUtils.sendResponseWithKeyboard(this,chatId, "To Be or Not To Be?", KeyboardUtils.getKeyboardFor1q());
                break;
            case 3:
                KeyboardUtils.sendResponseWithKeyboard(this,chatId, "Would you like to take a test to determine your current mood?", KeyboardUtils.getKeyboardForAskTest());
                break;
            case 4:
                KeyboardUtils.sendMessage(this,chatId,"This bot can help you to save your beautiful memories \n" +
                        "To control this bot you can use these commands:\n" +
                        "/savememory - to remember something important for you\n" +
                        "/memories - get your saved memories" );
                break;
            case 5:
                KeyboardUtils.sendMessage(this,chatId, "Please choose one of the options");
                break;
            default:
                break;
        }
    }
    @Override
    public String getBotUsername() {
        return BotConfig.botName;
    }
    @Override
    public String getBotToken(){
        return BotConfig.token;
    }
}
