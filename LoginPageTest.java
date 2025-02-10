package org.homeworksubmission.pages;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LoginPageTest {
    @Test
    void testValidLogin() {
        LoginPage loginPage = new LoginPage();
        boolean isLoggedIn = loginPage.login("username", "password");
        assertTrue(isLoggedIn, "Login should be successful");
    }

    @Test
    void testInvalidUsername() {
        LoginPage loginPage = new LoginPage();
        boolean isLoggedIn = loginPage.login("invalidUsername", "password");
        assertFalse(isLoggedIn, "Login should fail with invalid username");
    }

    @Test
    void testInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        boolean isLoggedIn = loginPage.login("username", "invalidPassword");
        assertFalse(isLoggedIn, "Login should fail with invalid password");
    }


}
