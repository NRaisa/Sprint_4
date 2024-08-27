package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FagPage {
    private  WebDriver driver;
    public FagPage(WebDriver driver){
        this.driver = driver;
    }

    public void checkAnswerIsInvesible(String itemId){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("accordion__heading-" + itemId)));

        String tab_status = driver.findElement(By.id("accordion__heading-" + itemId)).getAttribute("aria-disabled");
        assertEquals("Ответ виден до клика по вопросу", "false", tab_status);
    }
    public void clickOnQuestion(String itemId){
        driver.findElement(By.id("accordion__heading-" + itemId)).click();
    }
    public void waitForAnswer(String itemId){
                new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + itemId)));
    }
    public void checkQuestionText(String itemId, String expectedQuestion){
        String actualQuestion = driver.findElement(By.id("accordion__heading-" + itemId)).getText();
        assertEquals("Текст вопроса не совпадает с заданным", expectedQuestion, actualQuestion);
    }
    public void checkAnswerText(String itemId, String expectedAnswer){
        String actualAnswer = driver.findElement(By.id("accordion__panel-" + itemId)).getText();
        assertEquals("Текст ответа не совпадает с заданным", expectedAnswer, actualAnswer);
    }
}
