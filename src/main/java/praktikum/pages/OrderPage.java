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
    private static final By upperOrderButton = By.cssSelector("div.Header_Nav__AGCXC button.Button_Button__ra12g");
    private static final By lowerOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");
    private static final By name = By.cssSelector("[placeholder='* Имя']");
    private static final By surname = By.cssSelector("[placeholder='* Фамилия']");
    private static final By adress = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    private static final By station = By.cssSelector("[placeholder='* Станция метро']");
    private static final By phone = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    private static final By nextButton = By.xpath(".//button[text()='Далее']");
    private static final By rent = By.cssSelector("[placeholder='* Когда привезти самокат']");
    private static final By removeFocus = By.className("Order_Header__BZXOb");
    private static final By periodOfRent = By.className("Dropdown-placeholder");
    private static final By comments = By.cssSelector("[placeholder='Комментарий для курьера']");
    private static final By finalOrderButton =By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    private static final By areYouSure = By.className("Order_Modal__YZ-d3");
    private static final By yesButton =By.xpath(".//button[text()='Да']");
    private static final By orderComplited = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickOnTheUpperOderButton(){
        driver.findElement(upperOrderButton).click();
    }

    public void clickOnTheLowerOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(lowerOrderButton));
        driver.findElement(lowerOrderButton).click();
    }

    public void whoIsTheScooterFor(String userName, String userSurname, String userAdress, String stationId, String userPhone){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(name));
        driver.findElement(name).sendKeys(userName);
        driver.findElement(surname).sendKeys(userSurname);
        driver.findElement(adress).sendKeys(userAdress);
        driver.findElement(station).click();
        driver.findElement(By.xpath(".//div[text()='" + stationId + "']")).click();
        driver.findElement(phone).sendKeys(userPhone);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }

    public void aboutRent(String dayId, String rentelPeriod, String colorId, String comment){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(rent));
        driver.findElement(rent).sendKeys(dayId);
        driver.findElement(removeFocus).click();
        driver.findElement(periodOfRent).click();
        driver.findElement(By.xpath(".//div[text()='" + rentelPeriod + "']")).click();
        driver.findElement(By.id(colorId)).click();
        driver.findElement(comments).sendKeys(comment);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.elementToBeClickable(finalOrderButton));
        driver.findElement(finalOrderButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(areYouSure));
        driver.findElement(yesButton).click();
    }
    public void orderPlaced(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderComplited));
        String actual = driver.findElement(orderComplited).getText();
        String success = "Заказ оформлен";
        MatcherAssert.assertThat("Нет подтверждения об оформлении заказа",actual, containsString(success));
    }

}
