package com.revature.repositories;

import com.revature.models.User;
import com.revature.models.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository underTest;
    @Autowired
    UserRoleRepository roleRepo;

    @Test
    void checkIfFoundByEmailIgnoreCaseAndPassword() {

        // given
        UserRole role = roleRepo.save(new UserRole("Test"));
        User expected = new User(
                "Reyna",
                "Banks",
                "rdub@revature.com",
                "RDub123",
                role,
                new ArrayList<>(), // empty list to avoid null
                new ArrayList<>()
        );
        underTest.save(expected);

        // when
        User actual = underTest.findByEmailIgnoreCaseAndPassword("rdub@revature.com", "RDub123").orElse(null);


        // then
        assertThat(actual).isEqualTo(expected);
    }



    @Test
    void checkIfFoundById() {

        //given
        String email = "rdub@revature.com";
        String password = "RDub123";
        UserRole role = roleRepo.save(new UserRole("Test"));
        User expected = new User("Reyna", "Banks", "rdub@revature.com", "RDub123", role, null, null);
        System.out.println("*****************************************************************************************************************************************");
System.out.println(expected);
        System.out.println("*****************************************************************************************************************************************");

        underTest.save(expected);

        //when
        User actual = underTest.getById(1);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}