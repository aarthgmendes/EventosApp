package br.com.EventosApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.com.EventosApp.controllers"})
@EntityScan(basePackages = {"br.com.EventosApp.models"})
public class EventosAppApplication {

 public static void main(String[] args) {

  SpringApplication.run(EventosAppApplication.class, args);
 }

}
