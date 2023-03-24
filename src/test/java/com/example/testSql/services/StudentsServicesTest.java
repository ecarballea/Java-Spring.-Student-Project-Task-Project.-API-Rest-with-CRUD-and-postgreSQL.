package com.example.testSql.services;

import com.example.testSql.exceptions.StudentIDException;
import com.example.testSql.repositories.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import java.util.UUID;


public class StudentsServicesTest {

    @Autowired
    private StudentsService studentsService;

    @MockBean
    private StudentsRepository studentsRepositoryMock;


    @BeforeEach
    public void setup(){
        studentsService = new StudentsService();

        Mockito.when(studentsRepositoryMock.existsByName(ArgumentMatchers.anyString()))
                .thenReturn(true);
    }

//    @Test
//    public void getStudentByIDHPTest(){
//
////        Assertions.assertEquals(
////                studentsService.getStudentByID(UUID.fromString("e408d5e3-06fc-4cad-8400-c442737f7641")).get().getName(),
////                "Ernesto");
//        Assertions.assertEquals(
//                studentsService.getStudentByName("Ernesto").getName(),
//                "Ernesto");
//    }

//    @Test
//    public void getStudentByNameSPTest(){
//        Assertions.assertThrows(StudentIDException.class, () -> {
//            studentsService.getStudentByName("Manuel");
//        });
//    }



}
