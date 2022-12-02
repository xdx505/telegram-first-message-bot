package ru.xdx505.telegramshopexample.botapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xdx505.telegramshopexample.botapi.Bot;
import ru.xdx505.telegramshopexample.botapi.service.MessageService;

@Slf4j
@RestController
@RequestMapping("${bot.token}")
@RequiredArgsConstructor
public class WebhookController {
  private final Bot bot;

  @PostMapping("callback")
  public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    return bot.onWebhookUpdateReceived(update);
  }
}
