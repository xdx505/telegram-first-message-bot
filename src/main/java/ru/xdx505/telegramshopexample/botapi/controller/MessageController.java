package ru.xdx505.telegramshopexample.botapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xdx505.telegramshopexample.botapi.service.MessageService;

@Slf4j
@RestController
@RequestMapping("messages")
@RequiredArgsConstructor
public class MessageController {
  private final MessageService messageService;

  @PostMapping("createMessage")
  public ResponseEntity<Void> createMessage(@RequestBody SendMessage sendMessage) {
    try {
      log.info("Create new message");
      messageService.cacheMessage(sendMessage);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error("Error creating new message");
      throw new RuntimeException(e);
    }
  }
}
