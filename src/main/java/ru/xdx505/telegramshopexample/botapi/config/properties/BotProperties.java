package ru.xdx505.telegramshopexample.botapi.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("bot")
public class BotProperties {
  private String username;
  private String token;
  private String path;
  private Boolean dropUpdates;
}
