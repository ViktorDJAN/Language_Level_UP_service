package ru.kashtanov.graduation_work.telegram;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Objects;

@Configuration
@PropertySource("application.properties")
public class BotConfig {
    @Value("${bot.name}")
    String botName;
    @Value("${bot.token}")
    String token;

    public BotConfig() {
    }

    public BotConfig(String botName, String token) {
        this.botName = botName;
        this.token = token;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BotConfig botConfig = (BotConfig) o;
        return Objects.equals(botName, botConfig.botName) && Objects.equals(token, botConfig.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(botName, token);
    }

}
