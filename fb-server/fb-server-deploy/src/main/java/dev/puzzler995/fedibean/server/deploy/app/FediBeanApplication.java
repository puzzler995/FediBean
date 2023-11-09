package dev.puzzler995.fedibean.server.deploy.app;

import dev.puzzler995.fedibean.server.deploy.beanconfig.ServerDeployBeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {ServerDeployBeanConfig.class})
public class FediBeanApplication {
  public static void main(String[] args) {
    SpringApplication.run(FediBeanApplication.class, args);
  }
}
