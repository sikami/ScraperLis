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
        browser.get("https://gilitransfers.com/fast-boats/get-availability?departure_port=BALI_ALL_PORT&arrival_port=NL&departure_date=2020-12-20&return=0&return_date=Invalid%20date&adults=1&childs=0&infants=0&currency=IDR");
        WebElement header = browser.findElement(By.xpath("//*[@class='mat-card-header-text']"));

        System.out.println(header.getText());
        browser.close();
    }
}