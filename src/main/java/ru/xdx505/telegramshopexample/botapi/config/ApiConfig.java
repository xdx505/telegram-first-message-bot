package ru.xdx505.telegramshopexample.botapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.xdx505.telegramshopexample.botapi.Bot;
import ru.xdx505.telegramshopexample.botapi.TelegramFacade;
import ru.xdx505.telegramshopexample.botapi.config.properties.BotProperties;

@Configuration
@RequiredArgsConstructor
public class ApiConfig {
  private final BotProperties botProperties;

  @Bean
  public SetWebhook setWebhook() {
    return SetWebhook.builder()
        .url(botProperties.getPath())
        .dropPendingUpdates(botProperties.getDropUpdates())
        .build();
  }

  @Bean
  public Bot bot(SetWebhook setWebhook, TelegramFacade telegramFacade, BotProperties botProperties)
      throws TelegramApiException {
    Bot bot = new Bot(setWebhook, telegramFacade);
    bot.setBotToken(botProperties.getToken());
    bot.setBotUsername(botProperties.getUsername());
    bot.setWebhook(setWebhook);
    return bot;
  }
}
