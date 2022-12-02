package ru.xdx505.telegramshopexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.xdx505.telegramshopexample.botapi.config.properties.BotProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {BotProperties.class})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
