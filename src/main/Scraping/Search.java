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
    private WebDriver webDriver;


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
        this.webDriver = new FirefoxDriver();

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
        this.webDriver = new FirefoxDriver();
    }

    public void connect() {
        try {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
     //                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    options.addArguments("-headless");
            this.webDriver = new FirefoxDriver();
            webDriver.get(url);

            this.status = "OK";

            webDriver.findElement(By.id("departure_region_id"));
            Select depRegion = new Select(webDriver.findElement(By.id("departure_region_id")));
            depRegion.selectByValue(findValue(getFrom()));
            webDriver.findElement(By.id("arrival_region_id"));
            Select arrivalRegion = new Select(webDriver.findElement(By.id("arrival_region_id")));
            arrivalRegion.selectByValue(findValue(getTo()));

            //setting departure date
            webDriver.findElement(By.id("departure_date"));
            WebElement dateDeparture = webDriver.findElement(By.id("departure_date"));
            dateDeparture.clear();
            dateDeparture.sendKeys(getDepDate());

            //if return date is not put, then its one way
            if (!getReturnDate().equals("none")) {
                webDriver.findElement(By.id("departure_date"));
                WebElement dateReturn = webDriver.findElement(By.id("return_date"));
                dateReturn.clear();
                dateReturn.sendKeys(getReturnDate());
            } else {
                webDriver.findElement(By.id("oneway")).click();
            }

            //putting adult passenger
            webDriver.findElement(By.id("number_of_adult"));
            Select adult = new Select(webDriver.findElement(By.id("number_of_adult")));
            adult.selectByValue(getPassenger());

            //clicking currency
            webDriver.findElement(By.id("currency_id"));
            Select curr = new Select(webDriver.findElement(By.id("currency_id")));
            curr.selectByValue(findCurrency(getCurrency()));

            //clicking search button
            webDriver.findElement(By.className("search-button")).click();
            webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);

            //to take snapshot
           // this.takeSnapShot(webDriver, "/home/listya/Pictures/selenium.png");

            List<WebElement> price = webDriver.findElements(By.className("price"));

            List<WebElement> boatTitle = webDriver.findElements(By.className("boat-title"));

            List<WebElement> time = webDriver.findElements(By.className("time"));

            List<WebElement> route = webDriver.findElements(By.className("route"));
            putIntoClass(boatTitle, price, time, route);
            webDriver.close();






        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void close(WebDriver driver) {
        driver.close();
    }

    private String findValue(String departure) {
        switch (departure) {
            case "Nusa Ceningan":
                return "8";
            case "Nusa Penida":
                return "7";
            case "Gili Meno":
                return "6";
            case "Nusa Lembongan":
                return "5";
            case "Lombok":
                return "4";
            case "Bali" :
                return "3";
            case "Gili Air":
                return "2";
            case "Gili Trawangan":
                return "1";
        }
        return null;
    }

    private String findCurrency(String currency) {
        if (currency.contains("CHF")) {
            return "10";
        } else if (currency.contains("AUD")) {
            return "9";
        } else if (currency.contains("CAD")) {
            return "8";
        } else if (currency.contains("EUR")) {
            return "7";
        } else if (currency.contains("JPY")) {
            return "5";
        } else if (currency.contains("USD")) {
            return "4";
        } else if (currency.contains("GBP")) {
            return "3";
        } else if (currency.contains("SGD")) {
            return "2";
        } else if (currency.contains("IDR")) {
            return "1";
        }
        return null;
    }

    private void putIntoClass(List<WebElement> boatName, List<WebElement> price, List<WebElement> time, List<WebElement> routes) {
        List<FerrySchedule> ferrySchedules = new ArrayList<>();

        int size = boatName.size();
        for (int i = 0; i < size; i++) {
            ferrySchedules.add(new FerrySchedule(boatName.get(i).getText(), price.get(i).getText(), time.get(i).getText(), routes.get(i).getText()));
        }

        for (FerrySchedule ferrySchedule : ferrySchedules) {
            System.out.println(ferrySchedule);
        }

    }
}
