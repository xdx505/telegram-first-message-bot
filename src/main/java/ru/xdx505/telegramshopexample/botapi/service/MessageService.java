package ru.xdx505.telegramshopexample.botapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {
  private SendMessage sendMessage = null;

  public SendMessage getCachedMessage(Long chatId) {
    if (sendMessage == null) {
      sendMessage = SendMessage.builder().chatId(chatId).text("Демо сообщение").build();
    } else {
      sendMessage.setChatId(chatId);
    }
    return sendMessage;
  }

  public void cacheMessage(SendMessage message) {
    sendMessage = message;
  }
}
