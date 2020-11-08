import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Search {
    private String url;
    private String from;
    private String to;
    private String depDate;
    private String returnDate;
    private String passenger;
    private String currency;
    private String status;


    public Search() {
    }

    public Search(String url, String from, String to, String depDate, String returnDate, String passenger, String currency) {
        this.url = url;
        this.from = from;
        this.to = to;
        this.depDate = depDate;
        this.returnDate = returnDate;
        this.passenger = passenger;
        this.currency = currency;
        this.status = "";

    }

    public Search(String url, String from, String to, String depDate, String passenger, String currency) {
        this.url = url;
        this.from = from;
        this.to = to;
        this.depDate = depDate;
        this.passenger = passenger;
        this.currency = currency;
        this.status = "";
        this.returnDate = "none";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private void takeSnapShot(WebDriver webDriver, String file) throws Exception {
        TakesScreenshot screenshot = ((TakesScreenshot)webDriver);
        File file1 = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(file);
        FileUtils.copyFile(file1, destFile);
    }

    public String getStatus() {
        return this.status;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDepDate() {
        return depDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getPassenger() {
        return passenger;
    }

    public String getCurrency() {
        return currency;
    }


}
