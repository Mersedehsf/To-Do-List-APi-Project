package com.example.ToDoList;

import com.example.ToDoList.model.entity.UserAuthenticationEntity;
import com.example.ToDoList.repository.UserRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(classes = ToDoListApplication.class)
@ComponentScan("com.example.ToDoList")
class UserRepositoryTest {

//    @MockBean // repository ro mock karde felan methodash too khalie
    @Autowired
    private UserRepository userRepository;

    @Test
    public void checkDuplicateInsert() {
        UserAuthenticationEntity nima = UserAuthenticationEntity.builder().name("nima").email("nima@gmail.com").build();
        Assertions.assertThrows(DataIntegrityViolationException.class,()-> this.userRepository.save(nima) );
    }

    @BeforeEach
    public void init() {
        UserAuthenticationEntity nima = UserAuthenticationEntity.builder().name("nima").email("nima@gmail.com").build();
        UserAuthenticationEntity reza = UserAuthenticationEntity.builder().name("reza").email("reza@gmail.com").build();
        this.userRepository.save(nima);
        this.userRepository.save(reza);
    }

    @Test
    public void testInsertionOfRepository() {
        List<UserAuthenticationEntity> findAll = this.userRepository.findAll();
        MatcherAssert.assertThat(findAll, IsCollectionWithSize.hasSize(2));

    }

}
