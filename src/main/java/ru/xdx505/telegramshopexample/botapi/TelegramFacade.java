package ru.xdx505.telegramshopexample.botapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import ru.xdx505.telegramshopexample.botapi.service.MessageService;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramFacade {
  private final MessageService messageService;

  public BotApiMethod<?> handleUpdate(Update update) {
    if (update.getMessage() != null) {
      Message message = update.getMessage();
      Long chatId = message.getChatId();
      User user = update.getMessage().getFrom();

      if (message.getEntities() != null && !message.getEntities().isEmpty()) {
        Optional<MessageEntity> messageEntityOptional =
            update.getMessage().getEntities().stream()
                .filter(messageEntity -> messageEntity.getType().equals("bot_command"))
                .findFirst();
        if (messageEntityOptional.isPresent()) {
          MessageEntity messageEntity = messageEntityOptional.get();
          log.info(
              "Command received {}, user: {} chatId: {}",
              messageEntity.getText(),
              user.getUserName(),
              chatId);
          return messageService.getCachedMessage(chatId);
        }
      }

      log.info("Message received, user: {} chatId: {}", user.getUserName(), chatId);
      return new SendMessage(
          update.getMessage().getChatId().toString(),
          "Введите команду /start для получения приветственного сообщения");
    }

    if (update.getCallbackQuery() != null) {
      Message message = update.getCallbackQuery().getMessage();
      Long chatId = message.getChatId();
      User user = update.getCallbackQuery().getFrom();
      log.info("Callback query received, user: {} chatId: {}", user.getUserName(), chatId);
      return new SendMessage(
          update.getCallbackQuery().getMessage().getChatId().toString(), "Это демо версия");
    }

    // событие заблокировать/разблокировать бота в приватном чате
    ChatMemberUpdated chatMemberUpdated = update.getMyChatMember();
    if (chatMemberUpdated != null) {
      User user = chatMemberUpdated.getFrom();
      ChatMember oldChatMember = chatMemberUpdated.getOldChatMember();
      ChatMember newChatMember = chatMemberUpdated.getNewChatMember();
      log.info(
          "Chat member update received, user: {} prevState: {} newState: {}",
          user.getUserName(),
          oldChatMember.getStatus(),
          newChatMember.getStatus());
    }

    return null;
  }
}
