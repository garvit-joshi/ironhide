package org.garvit.IronHide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@EnableJpaRepositories
@SpringBootApplication
public class IronHideApplication {

  public static void main(String[] args) {
    SpringApplication.run(IronHideApplication.class, args);
  }
}
