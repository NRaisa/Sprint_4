package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.EnvConfig;

public class MainPage {
    private final WebDriver driver;

    private final By goButton = By.cssSelector(".Header_Button__28dPO");
    private final By orderField = By.className("Input_Input__1iN_Z");
    private final By statusButton = By.className("Header_Link__1TAG7");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }

    public StatusPage clickOnGo() {
        driver.findElement(goButton).click();

        return new StatusPage(driver);
    }

    public void enterOrderId(String orderNumber) {
        driver.findElement(orderField).sendKeys(orderNumber);
    }

    public void clickOnStatus() {
        driver.findElement(statusButton).click();
    }

}
