package com.example.testSql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@ComponentScans( { @ComponentScan("com.example.testSql.controllers") })
@EnableJpaRepositories("com.example.testSql.repositories")
@EntityScan("com.example.testSql.entities")
public class TestSqlApplication {
	String user = "postgres";

	String pass = "Ke welta";

	Connection connection;
	{
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TestDB",
					user, pass);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		System.out.println("Connected successfully");
	}

	public static void main(String[] args) {
		SpringApplication.run(TestSqlApplication.class, args);
	}

}
