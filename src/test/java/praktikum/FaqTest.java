package praktikum;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.pages.FagPage;
import praktikum.DriverRule;
import praktikum.pages.MainPage;

@RunWith(Parameterized.class)
public class FaqTest  {
    @Rule
    public DriverRule driverRule = new DriverRule();

    private String itemId;
    private String itemQuestion;
    private String itemAnswer;



    public FaqTest (String itemId, String itemQuestion, String itemAnswer){
        this.itemId = itemId;
        this.itemQuestion = itemQuestion;
        this.itemAnswer = itemAnswer;
    }


    @Before
    public void closeCookies(){
        WebDriver driver = driverRule.getDriver();
        var c = new CloseCookiesPage(driver);
        c.open();
        c.acceptCookies();
    }

    @Parameterized.Parameters
    public static Object[][] fagData(){
        return new Object[][]{
                {"0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }
    @Test
    public void clickOnFagItem(){
        WebDriver driver = driverRule.getDriver();
        var fag = new FagPage(driver);
                fag.checkAnswerIsInvesible(itemId);
                fag.clickOnQuestion(itemId);
                fag.waitForAnswer(itemId);
                fag.checkQuestionText(itemId, itemQuestion);
                fag.checkAnswerText(itemId, itemAnswer);
    }
}
