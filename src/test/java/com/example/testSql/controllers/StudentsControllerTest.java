package com.example.testSql.controllers;

import com.example.testSql.entities.Students;
import com.example.testSql.services.StudentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration(exclude = {
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class
//})
public class StudentsControllerTest {

    @MockBean
    private StudentsService studentsService;

    @Autowired
    private WebTestClient webTestClient;


//    @BeforeEach
//    public  void setup (){
//        Students student = new Students();
//        student.setName("Ernesto");
//        Mockito.when(studentsService.getStudentByName(ArgumentMatchers.anyString()))
//                .thenReturn(student);
//    }
//
//    @Test
//    public void getStudentByNameHPTest () {
//        webTestClient.get()
//                .uri("/read/name/Ernesto")
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$name").isEqualTo("Ernesto");
//    }
}
