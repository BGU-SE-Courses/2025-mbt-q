package hellocucumber;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class OpenStudentSessionActuator extends OpenSessionActuator{
    private final String course3dotsXpath = "/html/body/div[2]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div/div[2]/div/div/button";
    private final String starThisCourseXpath = "/html/body/div[2]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div/div[2]/div/div/div/a[1]";
    private final String starXpath = "/html/body/div[2]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/div/a/span[1]/span/i";
    private final String unstarXpath = "/html/body/div[2]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div/div[2]/div/div/div/a[2]";

    public void DefineStaredCourse(String course){
        searchCourse(course);
        driver.findElement(By.xpath(course3dotsXpath)).click();
        waitToLoad(500);
        driver.findElement(By.xpath(starThisCourseXpath)).click();
        waitToLoad(500);
    }

    @Test
    public void CheckStaredCourse(){
        boolean starred = !driver.findElements(By.xpath(starXpath)).isEmpty();
        assertTrue(starred);
    }

}