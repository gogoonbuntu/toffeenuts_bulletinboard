package com.toffee.nuts.bulletinboard;

import com.toffee.nuts.bulletinboard.controller.TistoryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BulletinboardApplication {

	public static void main(String[] args) throws Exception {
		TistoryController.postNew();
		SpringApplication.run(BulletinboardApplication.class, args);
	}
}
