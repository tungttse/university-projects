package servicetests;

import io.github.akkhadka.webstore.model.User;
import io.github.akkhadka.webstore.service.UserService;
import io.github.akkhadka.webstore.service.UserServiceImpl;
import io.github.akkhadka.webstore.service.customexception.UserAlreadyExistsException;
import io.github.akkhadka.webstore.service.customexception.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;
    @AfterEach
    void tearDown() {
        userService =null;
    }
    @BeforeEach
    void setup() {
        userService = new UserServiceImpl();
    }
    @Test
    public void signUp_onaddingExistingUser_throwsException(){
        //arrange
        String userName = UUID.randomUUID().toString();
        User user = new User(userName,"123456a","arjun","lastname","arjun@gmail.com");

        //act and assert
        assertThrows(UserAlreadyExistsException.class,()->{
            userService.signUp(user);
            userService.signUp(user);
        });

    }
    @Test
    public void signUp_onaddingNewUser_addsSuccessfully(){
        //arrange
        String userName = UUID.randomUUID().toString();
        User newUser = new User(userName,"123456a","arjun","lastname","arjun@gmail.com");

        //act
        try {
            userService.signUp(newUser);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            userAlreadyExistsException.printStackTrace();
        }
        //asserts
        User user = userService.findbyUserName(userName);
        assertTrue(newUser.equals(user));


    }
    @Test
    public void signUp_onUpdatingUserWhichDonotExist_throwsException(){
        //arrange
        String userName = UUID.randomUUID().toString();
        User newUser = new User(userName,"123456a","arjun","lastname","arjun@gmail.com");

        //act & assert
        assertThrows(UserNotFoundException.class,()->{
            userService.updateProfile(newUser);
        });


    }
    @Test
    public void signUp_onUpdatingFirstName_updatesCorrectly() throws UserNotFoundException, UserAlreadyExistsException {
        //arrange
        String userName = UUID.randomUUID().toString();

        User newUser1 = new User(userName,"123456a","arjun","lastname","arjun@gmail.com");
        userService.signUp(newUser1);

        //act
        String newFirstName ="arjunnew";
        User newUser2 = new User(userName,"123456a",newFirstName,"lastname","arjun@gmail.com");
        userService.updateProfile(newUser2);

        //assert
        User user = userService.findbyUserName(userName);
        assertTrue(user.getFirstName().equals(newFirstName));

    }
    @Test
    public void signUp_onUpdatingLastName_updatesCorrectly() throws UserNotFoundException, UserAlreadyExistsException {
        //arrange
        String userName = UUID.randomUUID().toString();

        User newUser1 = new User(userName,"123456a","arjun","khadka","arjun@gmail.com");
        userService.signUp(newUser1);

        //act
        String newLastName ="khadkanew";
        User newUser2 = new User(userName,"123456a","arjun",newLastName,"arjun@gmail.com");
        userService.updateProfile(newUser2);

        //assert
        User user = userService.findbyUserName(userName);
        assertTrue(user.getLastName().equals(newLastName));


    }
    @Test
    public void signUp_onUpdatingEmail_updatesCorrectly() throws UserNotFoundException, UserAlreadyExistsException {
        //arrange
        String userName = UUID.randomUUID().toString();

        User newUser1 = new User(userName,"123456a","arjun","khadka","arjun@gmail.com");
        userService.signUp(newUser1);

        //act
        String email ="arjunnew@gmail.com";
        User newUser2 = new User(userName,"123456a","arjun","khadka",email);
        userService.updateProfile(newUser2);

        //assert
        User user = userService.findbyUserName(userName);
        assertTrue(user.getEmail().equals(email));


    }

}
