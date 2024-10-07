package praktikum.pages;

import static org.hamcrest.CoreMatchers.containsString;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private static final By UPPER_ORDER_BUTTON = By.cssSelector("div.Header_Nav__AGCXC button.Button_Button__ra12g");
    private static final By LOWER_ORDER_BUTTON = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");
    private static final By NAME = By.cssSelector("[placeholder='* Имя']");
    private static final By SURNAME = By.cssSelector("[placeholder='* Фамилия']");
    private static final By ADRESS = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    private static final By STATION = By.cssSelector("[placeholder='* Станция метро']");
    private static final By PHONE = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    private static final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");
    private static final By RENT = By.cssSelector("[placeholder='* Когда привезти самокат']");
    private static final By REMOVE_FOCUS = By.className("Order_Header__BZXOb");
    private static final By PERIOD_OF_RENT = By.className("Dropdown-placeholder");
    private static final By COMMENTS = By.cssSelector("[placeholder='Комментарий для курьера']");
    private static final By FINAL_ORDER_BUTTON =By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    private static final By ARE_YOU_SURE = By.className("Order_Modal__YZ-d3");
    private static final By YES_BUTTON =By.xpath(".//button[text()='Да']");
    private static final By ORDER_COMPLITED = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickOnTheUpperOderButton(){
        driver.findElement(UPPER_ORDER_BUTTON).click();
    }

    public void clickOnTheLowerOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(LOWER_ORDER_BUTTON));
        driver.findElement(LOWER_ORDER_BUTTON).click();
    }

    public void whoIsTheScooterFor(String userName, String userSurname, String userAdress, String stationId, String userPhone){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(NAME));
        driver.findElement(NAME).sendKeys(userName);
        driver.findElement(SURNAME).sendKeys(userSurname);
        driver.findElement(ADRESS).sendKeys(userAdress);
        driver.findElement(STATION).click();
        driver.findElement(By.xpath(".//div[text()='" + stationId + "']")).click();
        driver.findElement(PHONE).sendKeys(userPhone);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.elementToBeClickable(NEXT_BUTTON));
        driver.findElement(NEXT_BUTTON).click();
    }

    public void aboutRent(String dayId, String rentelPeriod, String colorId, String comment){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(RENT));
        driver.findElement(RENT).sendKeys(dayId);
        driver.findElement(REMOVE_FOCUS).click();
        driver.findElement(PERIOD_OF_RENT).click();
        driver.findElement(By.xpath(".//div[text()='" + rentelPeriod + "']")).click();
        driver.findElement(By.id(colorId)).click();
        driver.findElement(COMMENTS).sendKeys(comment);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.elementToBeClickable(FINAL_ORDER_BUTTON));
        driver.findElement(FINAL_ORDER_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(ARE_YOU_SURE));
        driver.findElement(YES_BUTTON).click();
    }
    public void orderPlaced(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_COMPLITED));
        String actual = driver.findElement(ORDER_COMPLITED).getText();
        String success = "Заказ оформлен";
        MatcherAssert.assertThat("Нет подтверждения об оформлении заказа",actual, containsString(success));
    }

}
