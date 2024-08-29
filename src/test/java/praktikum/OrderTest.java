package praktikum;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.FagPage;
import praktikum.pages.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    private String userName;
    private String userSurname;
    private String userAdress;
    private String stationId;
    private String userPhone;
    private String dayId;
    private String rentelPeriod;
    private String colorId;
    private String comment;

    public OrderTest(String userName,String userSurname, String userAdress, String stationId, String userPhone,
                     String dayId, String rentelPeriod, String colorId, String comment){
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAdress = userAdress;
        this.stationId = stationId;
        this.userPhone = userPhone;
        this.dayId = dayId;
        this.rentelPeriod = rentelPeriod;
        this.colorId = colorId;
        this.comment = comment;
    }
    @Before
    public void closeCookies(){
        WebDriver driver = driverRule.getDriver();
        var cloCookies = new CloseCookiesPage(driver);
        cloCookies.open();
        cloCookies.acceptCookies();
    }

    @Parameterized.Parameters
    public static Object[][] orderData(){
        return new Object[][]{
                {"Яна", "Ли", "ул. Мира, д.23", "Сокольники", "89001112233", "27.08.2024", "сутки", "black", "Стучите громче. Звонок не работает."},
                {"Константин", "Кузнецов", "пр. Мира, д. 24, к.3, кв. 223", "Преображенская площадь", "89004445522", "31.08.2024", "двое суток", "grey", "Позвоните за 1 час"},
        };
    }

    @Test
    public void clickOnUpperOderButtonAndOderPlaced(){
        WebDriver driver = driverRule.getDriver();
        var ord = new OrderPage(driver);
        ord.clickOnTheUpperOderButton();
        ord.whoIsTheScooterFor(userName, userSurname,userAdress,stationId, userPhone);
        ord.aboutRent(dayId, rentelPeriod, colorId, comment);
        ord.orderPlaced();
    }

    @Test
    public void clickOnLowerOderButtonAndOderPlaced(){
        WebDriver driver = driverRule.getDriver();
        var ord = new OrderPage(driver);
        ord.clickOnTheLowerOrderButton();
        ord.whoIsTheScooterFor(userName, userSurname,userAdress,stationId, userPhone);
        ord.aboutRent(dayId, rentelPeriod, colorId, comment);
        ord.orderPlaced();
    }


}
