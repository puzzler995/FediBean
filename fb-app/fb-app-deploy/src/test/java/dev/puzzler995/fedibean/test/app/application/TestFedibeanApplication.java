package dev.puzzler995.fedibean.test.app.application;

import dev.puzzler995.fedibean.test.beanconfig.TestFedibeanBeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {TestFedibeanBeanConfig.class})
public class TestFedibeanApplication {
  public static void main(String[] args) {
    SpringApplication.run(TestFedibeanApplication.class, args);
  }
}
