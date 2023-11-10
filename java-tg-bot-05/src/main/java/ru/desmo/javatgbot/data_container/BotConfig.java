package ru.desmo.javatgbot.data_container;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@Data
@PropertySource("app.properties")
public class BotConfig {
    public static final String botName = "hdugisdusiubot";
    public static final String token = "6657296133:AAF34wB05yZAYt7h39YlnQK2uuDw4Xk8mkI";
}