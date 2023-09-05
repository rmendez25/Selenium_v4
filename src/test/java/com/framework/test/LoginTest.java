package com.framework.test;

import com.framework.config.ConfigReader;
import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private ConfigReader reader;

    @BeforeMethod(description = "Navigate to login page")
    public void init() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        reader = new ConfigReader();
        loginPage.navigateToLoginPage();
    }

    @Test(description = "Login using valid credentials")
    public void loginHappyPath() {
        loginPage.setUsernameInput(reader.getPropertyValue("standard_user"));
        loginPage.setPasswordInput(reader.getPropertyValue("password"));
        loginPage.clickOnLoginButton();

        assertTrue(getDriver().getCurrentUrl().endsWith("inventory.html"));
        assertTrue(homePage.isHomePageHeaderDisplayed(), "Header should be displayed");
    }

    @Test(description = "Login with invalid credentials")
    public void loginWithInvalidCredentials() {
        loginPage.setUsernameInput(reader.getPropertyValue("invalid_user"));
        loginPage.setPasswordInput(reader.getPropertyValue("invalid_password"));
        loginPage.clickOnLoginButton();

        assertTrue(loginPage.loginErrorMessageIsDisplayed());
        assertEquals(loginPage.loginErrorMessageText(), reader.getPropertyValue("login_error_message"));
    }

    @Test(description = "Login with a locked user")
    public void loginWithLockedUser() {
        loginPage.setUsernameInput(reader.getPropertyValue("locked_out_user"));
        loginPage.setPasswordInput(reader.getPropertyValue("password"));
        loginPage.clickOnLoginButton();

        assertEquals(loginPage.loginErrorMessageText(), reader.getPropertyValue("login_error_locked_user_message"));
    }

    @Test(description = "Login with a glitched user")
    public void loginWithProblemUser() {
        loginPage.doLogin(reader.getPropertyValue("problem_user"), reader.getPropertyValue("password"));
        assertEquals(homePage.areImagesTheSame(), 6);
    }
}
