package com.pvt.fasad;

import com.pvt.forms.UserForm;
import com.pvt.jar.exceptions.LogicException;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.datasource.url=jdbc:mysql://localhost:3306/test_diplom"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTestFasad {

    private final String NEGATIVE_USERNAME = "user1";
    private final String USER_EMAIL= "jvdk";
    private final String USERNAME = "test";
    private final String ROLE = "user";
    private final String PASSWORD = "123456Qqq!";
    private final String UPDATE_USERNAME = "test_update";
    @Autowired
    private UserFasad userFasad;

    @Test
    @Ignore
    public void getTest(){
        assertEquals("test",userFasad.get(1).getUsername());
    }

    @Test
    @Ignore
    public void addTest() throws LogicException {
        UserForm user = new UserForm();
        user.setEmail(USER_EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
        user.setUsername(USERNAME);
        userFasad.addUser(user);
    }

    @Test
    @Ignore
    public void updateTest() throws LogicException {
        UserForm user = new UserForm();
        user.setEmail(USER_EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
        user.setUsername(USERNAME);
        userFasad.addUser(user);
        user = userFasad.getByUsername(USERNAME);
        user.setUsername(UPDATE_USERNAME);
        userFasad.update(user);
        assertEquals(UPDATE_USERNAME,userFasad.getByUsername(UPDATE_USERNAME).getUsername());

    }

    @Test
    @Ignore
    public void deleteTest(){
        userFasad.delete(userFasad.getByUsername(USERNAME).getId());
    }


    @Test
    @Ignore
    public void getAllUserTest(){
        assertNotNull(userFasad.getAllUsers());
    }

    @Test
    @Ignore
    public void getByUsernameTest(){
        assertEquals("user1",userFasad.getByUsername("user1").getUsername());
    }


    @Test
    @Ignore
    public void addNegativeTest() {
        UserForm user = new UserForm();
        user.setEmail(USER_EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
        user.setUsername(NEGATIVE_USERNAME);
        assertThrows(LogicException.class,()->{
            userFasad.addUser(user);
        });
    }



    @Test
    @Ignore
    public void deleteNegativeTest(){
        assertThrows(EmptyResultDataAccessException.class,()->{
            userFasad.delete(875585);
        });
    }
}
