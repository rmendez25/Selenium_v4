package com.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(xpath = "//div[contains(text(), 'Swag Labs')]")
    private WebElement homePageHeader;
    @FindAll({@FindBy(xpath = "//img[@src='/static/media/sl-404.168b1cce.jpg']")})
    private List<WebElement> cardImages;
    @FindBy(css = "[data-test=add-to-cart-sauce-labs-backpack]")
    private WebElement addToCartBackPack;

    @FindBy(css = "[data-test=remove-sauce-labs-backpack]")
    private WebElement removeCartItem;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartIcon;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Validate the header is visible")
    public boolean isHomePageHeaderDisplayed() {
        waitForElementToBeVisible(homePageHeader);
        return homePageHeader.isDisplayed();
    }

    @Step("Validate that all images are the same")
    public int areImagesTheSame() {
        return cardImages.size();
    }

    @Step("Add item to cart")
    public void addItemToCart() {
        clickOnElement(addToCartBackPack);
    }

    @Step("Validate text for add to cart button")
    public String addItemToCartText() {
        return addToCartBackPack.getText();
    }

    @Step("Validate text for remove button")
    public void removeItemFromCart() {
        clickOnElement(removeCartItem);
    }

    @Step("Remove item from the cart")
    public String removeCartItemText() {
        return removeCartItem.getText();
    }

    @Step("Validate the cart icon is updated")
    public boolean cartIconIsUpdated() {
        return shoppingCartIcon.isDisplayed();
    }
}
