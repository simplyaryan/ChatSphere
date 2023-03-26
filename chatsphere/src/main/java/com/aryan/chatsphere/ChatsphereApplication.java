package com.aryan.chatsphere;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatsphereApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ChatsphereApplication.class, args);
		database.delete();
	}

}
