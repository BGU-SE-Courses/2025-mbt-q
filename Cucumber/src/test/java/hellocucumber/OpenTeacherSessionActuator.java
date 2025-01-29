package hellocucumber;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenTeacherSessionActuator extends OpenSessionActuator{
    private final String courseXpath = "/html/body/div[2]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/div/a/span[3]/span[2]";
    private final String courseSettingsXpath = "/html/body/div[2]/div[4]/div/div[2]/nav/ul/li[2]/a";
    private final String visibilityXpath = "/html/body/div[3]/div[4]/div/div[3]/div/section/div/form/fieldset[1]/div[2]/div[4]/div[2]/select";
    private final String hideXpath = visibilityXpath + "/option[1]";
    private final String saveButtonXpath = "/html/body/div[3]/div[4]/div/div[3]/div/section/div/form/div[3]/div[2]/div[1]/div/div[1]/span/input";
    private final String hiddenFromStudentsXpath = "/html/body/div[2]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/div/div[2]/span";

    public void goToSettings(String course){
        searchCourse(course);
        driver.findElement(By.xpath(courseXpath)).click();
        waitToLoad(2000);
        driver.findElement(By.xpath(courseSettingsXpath)).click();
        waitToLoad(2000);
    }

    public void deleteCourse(){
        ((JavascriptExecutor) driver).executeScript("window.scrollBy({ top: 500, behavior: 'smooth' });"); // scroll to hide / show
        waitToLoad(2000);
        driver.findElement(By.xpath(visibilityXpath)).click();
        waitToLoad(500);
        driver.findElement(By.xpath(hideXpath)).click();
        waitToLoad(500);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
        waitToLoad(2000);
        driver.findElement(By.xpath(saveButtonXpath)).click();
        waitToLoad(2000);
    }

    @Test
    public void CheckHiddenCourse(String course){
        searchCourse(course);
        boolean hidden = !driver.findElements(By.xpath(hiddenFromStudentsXpath)).isEmpty();
        assertTrue(hidden);
    }

}