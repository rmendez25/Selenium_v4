package com.framework.test;

import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

public class HomeTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    private static final Logger logger = LogManager.getLogger(HomeTest.class);


    @BeforeMethod(description = "Perform login")
    public void init() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        logger.info("Login into the WebApp");
        loginPage.doLogin();
    }

    @Test(description = "Validate add to the cart functionality works")
    public void validateAddToCartButtonFunctionality(Method method) {
        logger.info("Add item to cart");
        homePage.addItemToCart();
        logger.info("Validate Cart is updated");
        assertFalse(homePage.cartIconIsUpdated());
    }

    @Test(description = "Validate remove item from cart button works as expected")
    public void validateRemoveCartItemFunctionality(Method method) {
        homePage.addItemToCart();
        assertEquals(homePage.removeCartItemText(), "Remove");

        homePage.removeItemFromCart();
        assertEquals(homePage.addItemToCartText(), "Add to cart");
    }
}
