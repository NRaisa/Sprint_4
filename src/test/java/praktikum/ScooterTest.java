package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;
import praktikum.pages.StatusPage;
import praktikum.DriverRule;

public class ScooterTest {
    @Rule
    public DriverRule factory = new DriverRule();

    private String invalidOrderId = "123";

    @Test
    public void openMainPage() throws Exception {
        WebDriver driver = factory.getDriver();
        var mainPain = new MainPage(driver);

        mainPain.open();

        mainPain.clickOnStatus();
        mainPain.enterOrderId(invalidOrderId);

        StatusPage statusPage = mainPain.clickOnGo();
        statusPage.checkErrorMessage();
    }
}
