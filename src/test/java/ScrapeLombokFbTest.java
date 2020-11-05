import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.*;

class ScrapeLombokFbTest {
    @Test
    public void siteOnHomePage() {
        WebDriver browser;
        System.setProperty("webdriver.gecko.driver", "/home/listya/Lib/JAVA/JavaLibrary/FirefoxDriver/geckodriver");


        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        browser = new FirefoxDriver(options);
        browser.get("http://saucelabs.com");
        WebElement header = browser.findElement(By.id("headerMainNav"));
        assertTrue((header.isDisplayed()));
        browser.close();
    }
}