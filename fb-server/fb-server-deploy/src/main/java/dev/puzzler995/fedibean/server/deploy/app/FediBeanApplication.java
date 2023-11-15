package dev.puzzler995.fedibean.server.deploy.app;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import dev.puzzler995.fedibean.beanconfig.ServerDeployBeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {ServerDeployBeanConfig.class})
@EnableVaadin(value = "dev.puzzler995.fedibean.ui.app.frontend.view")
public class FediBeanApplication {
  public static void main(String[] args) {
    SpringApplication.run(FediBeanApplication.class, args);
  }
}
