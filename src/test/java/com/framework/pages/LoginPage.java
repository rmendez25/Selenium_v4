package com.framework.pages;

import com.framework.config.ConfigReader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    private ConfigReader reader;

    @FindBy(css = "[data-test=username]")
    private WebElement username;

    @FindBy(css = "[data-test=password]")
    private WebElement password;

    @FindBy(css = "[data-test=login-button]")
    private WebElement loginButton;

    @FindBy(css = "[data-test=error]")
    private WebElement loginErrorMessage;

    @FindBy(css = "[data-icon=times]")
    private WebElement loginErrorButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        reader = new ConfigReader();
    }

    @Step("Navigate to Login Page.")
    public void navigateToLoginPage() {
        String baseUrl = reader.getPropertyValue("base_url");
        driver.get(baseUrl);
    }

    @Step("Set the username input field.")
    public void setUsernameInput(String value) {
        typeInputField(username, value);
    }

    @Step("Set the password input field.")
    public void setPasswordInput(String value) {
        typeInputField(password, value);
    }

    @Step("Click into the login button.")
    public void clickOnLoginButton() {
        clickOnElement(loginButton);
    }

    @Step("Validate login error message text.")
    public String loginErrorMessageText() {
        waitForElementToBeVisible(loginErrorMessage);
        return loginErrorMessage.getText();
    }

    @Step("Validate login error message is displayed.")
    public Boolean loginErrorMessageIsDisplayed() {
        waitForElementToBeVisible(loginErrorMessage);
        return loginErrorMessage.isDisplayed();
    }

    public void closeErrorMessage() {
        clickOnElement(loginErrorButton);
    }

    public boolean isErrorButtonNotVisible() {
        return isElementNotVisible(loginErrorButton);
    }

    public HomePage doLogin(String username, String password) {
        navigateToLoginPage();
        setUsernameInput(username);
        setPasswordInput(password);
        clickOnLoginButton();

        return new HomePage(driver);
    }

    public HomePage doLogin() {
        navigateToLoginPage();
        setUsernameInput(reader.getPropertyValue("standard_user"));
        setPasswordInput(reader.getPropertyValue("password"));
        clickOnLoginButton();

        return new HomePage(driver);
    }

}
