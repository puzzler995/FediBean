package dev.puzzler995.fedibean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestFediBeanApplication {

  public static void main(String[] args) {
    SpringApplication.from(FediBeanApplication::main).with(TestFediBeanApplication.class).run(args);
  }

}
