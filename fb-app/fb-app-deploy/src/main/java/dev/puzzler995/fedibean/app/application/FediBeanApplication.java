package dev.puzzler995.fedibean.app.application;

import dev.puzzler995.fedibean.beanconfig.FedibeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {FedibeanConfig.class})
public class FediBeanApplication {
  public static void main(String[] args) {

    SpringApplication.run(FediBeanApplication.class, args);
  }
}
