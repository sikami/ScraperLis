import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ScrapeLombokFb {
    private WebDriver webDriver;
    private List<FerrySchedule> ferrySchedules;
    private Search ferrySearch;

    public ScrapeLombokFb(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.ferrySchedules = new ArrayList<>();
        this.ferrySearch = new Search();
    }

    public void scrapeWeb() {
        try {
           List<WebElement> lists = webDriver.findElements(By.className("boat-item departure"));
            System.out.println(lists.size());
            webDriver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
