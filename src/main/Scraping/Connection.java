import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Connection {

    private Search search;
    private ScrapeLombokFb lombokFb;
    private List<FerrySchedule> results = new ArrayList<>();


    public List<FerrySchedule> connectLombokFb() {
        try {
            FirefoxOptions options = new FirefoxOptions();
            WebDriver webDriver = new FirefoxDriver();
            options.addArguments("-headless");

            //check if URL is lombok                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             options.addArguments("-headless");
            webDriver.get(search.getUrl());
            webDriver.get(search.getUrl());
            search.setStatus("OK");
            webDriver.findElement(By.id("departure_region_id"));
            Select depRegion = new Select(webDriver.findElement(By.id("departure_region_id")));
            depRegion.selectByValue(this.lombokFb.findValueLombokFb(search.getFrom()));
            webDriver.findElement(By.id("arrival_region_id"));
            Select arrivalRegion = new Select(webDriver.findElement(By.id("arrival_region_id")));
            arrivalRegion.selectByValue(this.lombokFb.findValueLombokFb(search.getTo()));

            //setting departure date
            webDriver.findElement(By.id("departure_date"));
            WebElement dateDeparture = webDriver.findElement(By.id("departure_date"));
            dateDeparture.clear();
            dateDeparture.sendKeys(search.getDepDate());

            //if return date is not put, then its one way
            if (!search.getReturnDate().equals("none")) {
                webDriver.findElement(By.id("departure_date"));
                WebElement dateReturn = webDriver.findElement(By.id("return_date"));
                dateReturn.clear();
                dateReturn.sendKeys(search.getReturnDate());
            } else {
                webDriver.findElement(By.id("oneway")).click();
            }

            //putting adult passenger
            webDriver.findElement(By.id("number_of_adult"));
            Select adult = new Select(webDriver.findElement(By.id("number_of_adult")));
            adult.selectByValue(search.getPassenger());

            //clicking currency
            webDriver.findElement(By.id("currency_id"));
            Select curr = new Select(webDriver.findElement(By.id("currency_id")));
            curr.selectByValue(this.lombokFb.findCurrencyLombokFb(search.getCurrency()));

            //clicking search button
            webDriver.findElement(By.className("search-button")).click();
            webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);

            //scrape content into list
            List<WebElement> price = webDriver.findElements(By.className("price"));
            List<WebElement> boatTitle = webDriver.findElements(By.className("boat-title"));
            List<WebElement> time = webDriver.findElements(By.className("time"));
            List<WebElement> route = webDriver.findElements(By.className("route"));

            this.results = putIntoClass(boatTitle, price, time, route);
            webDriver.close();
            return results;

        } catch (Exception e) {
            System.out.println("Failed to fetch url for Lombok Fast Boat");
        }

        return null;
    }




    private List<FerrySchedule> putIntoClass(List<WebElement> boatName, List<WebElement> price, List<WebElement> time, List<WebElement> routes) {
        List<FerrySchedule> ferrySchedules = new ArrayList<>();

        int size = boatName.size();
        for (int i = 0; i < size; i++) {
            ferrySchedules.add(new FerrySchedule(boatName.get(i).getText(), price.get(i).getText(), time.get(i).getText(), routes.get(i).getText()));
        }
        return ferrySchedules;
    }

    public void printResult(List<FerrySchedule> ferrySchedules) {
        for (FerrySchedule ferrySchedule : ferrySchedules) {
            System.out.println(ferrySchedule);
        }
    }
}
