package com.example.demo;

import com.example.demo.main.Principal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {



	public static void main(String[] args) {
		var context = SpringApplication.run(DemoApplication.class, args);

		// pegar o bean do Spring
		Principal principal = context.getBean(Principal.class);

		// chama o menu
		principal.menu();
	}


}
