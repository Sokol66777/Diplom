import com.pvt.jar.configuration.SpringConfig;
import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.entity.User;
import com.pvt.jar.services.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserServiceTest {

    private final String NEGATIVE_USERNAME = "user1";
    private final String USER_EMAIL= "jvdk";
    private final String USERNAME = "test";
    private final String ROLE = "user";
    private final String PASSWORD = "123456Qqq!";
    private final String UPDATE_USERNAME = "test_update";
    @Autowired
    private UserService userService;



    @Test
    @Ignore
    public void getTest(){
        assertEquals(userService.get(1L).getUsername(),"user1");
    }

    @Test

    public void repoTest(){
        User user = userService.getByUsername("user4");
    }
    @Test

    public void addTest() throws LogicException {
        User user = new User();
        user.setEmail("user5@mail.com");
        user.setPassword(PASSWORD);
        user.setUsername("user5");
        userService.add(user);
    }

    @Test
    @Ignore
    public void updateTest() throws LogicException {
        User user = new User();
        user.setEmail(USER_EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
        user.setUsername(USERNAME);
        userService.add(user);
        user = userService.getByUsername(USERNAME);
        user.setUsername(UPDATE_USERNAME);
        userService.modify(user);

    }

    @Test
    @Ignore
    public void deleteTest(){
        userService.delete(userService.getByUsername(UPDATE_USERNAME).getID());
    }


    @Test
    @Ignore
    public void getAllUserTest(){
        assertNotNull(userService.getAllUsers());
    }

    @Test
    @Ignore
    public void getByUsernameTest(){
        assertEquals(userService.getByUsername("user1").getUsername(),"user1");
    }

    @Test
    @Ignore
    public void getByEmailTest(){
        assertEquals(userService.getByEmail("user4@mail.com").getEmail(),"user4@mail.com");
    }

    @Test
    @Ignore
    public void addNegativeTest() {
        User user = new User();
        user.setEmail(USER_EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
        user.setUsername(NEGATIVE_USERNAME);
        assertThrows(LogicException.class,()->{
            userService.add(user);
        });
    }

    @Test
    @Ignore
    public void modifyNegativeTest() throws LogicException {
        User user = new User();
        user.setEmail(USER_EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
        user.setUsername(USERNAME);
        userService.add(user);
        user = userService.getByUsername(USERNAME);
        user.setUsername(NEGATIVE_USERNAME);
        User finalUser = user;
        assertThrows(LogicException.class,()->{
            userService.modify(finalUser);
        });
    }

    @Test
    @Ignore
    public void deleteNegativeTest(){
        assertThrows(InvalidDataAccessApiUsageException.class,()->{
            userService.delete(875585L);
        });
    }


}
