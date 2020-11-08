import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConnectionScraper {

    private Search search;
    private List<FerrySchedule> results;
    private WebDriver webDriver;

    public ConnectionScraper(Search search) {
        this.search = search;
        this.results = new ArrayList<>();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        this.webDriver = new FirefoxDriver(options);
    }

    public List<FerrySchedule> connectLombokFb() {
        try {
            LombokFbMethods lombokFbMethods = new LombokFbMethods();
//
//
//            options.addArguments("-headless");
//            WebDriver webDriver = new FirefoxDriver(options);
            //check if URL is lombok                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             options.addArguments("-headless");
            webDriver.get(search.getUrl());
            search.setStatus("OK");
            webDriver.findElement(By.id("departure_region_id"));
            Select depRegion = new Select(webDriver.findElement(By.id("departure_region_id")));
            depRegion.selectByValue(lombokFbMethods.findValueLombokFb(search.getFrom()));
            webDriver.findElement(By.id("arrival_region_id"));
            Select arrivalRegion = new Select(webDriver.findElement(By.id("arrival_region_id")));
            arrivalRegion.selectByValue(lombokFbMethods.findValueLombokFb(search.getTo()));

            //setting departure date
            webDriver.findElement(By.id("departure_date"));
            WebElement dateDeparture = webDriver.findElement(By.id("departure_date"));
            dateDeparture.clear();
            dateDeparture.sendKeys(search.getDepDate());

            //if return date is not put, then its one way
            if (!search.getReturnDate().equals("")) {
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
            curr.selectByValue(lombokFbMethods.findCurrencyLombokFb(search.getCurrency()));

            //clicking search button
            webDriver.findElement(By.className("search-button")).click();
            webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

            //scrape content into list
            List<WebElement> price = webDriver.findElements(By.className("price"));
            List<WebElement> boatTitle = webDriver.findElements(By.className("boat-title"));
            List<WebElement> time = webDriver.findElements(By.className("time"));
            List<WebElement> route = webDriver.findElements(By.className("route"));

            this.results = putIntoClass(boatTitle, price, time, route);
            return this.results;
//            printResult(this.results);
//            webDriver.close();

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

    public String printResult(List<FerrySchedule> ferrySchedules) {
        StringBuilder sb = new StringBuilder();
        for (FerrySchedule ferrySchedule : ferrySchedules) {
            sb.append(ferrySchedule);
        }
        return sb.toString();
    }

    public void closeWebDriver() {
        this.webDriver.close();
    }




//    public static String findValueLombokFb(String departure) {
//        switch (departure) {
//            case "Nusa Ceningan":
//                return "8";
//            case "Nusa Penida":
//                return "7";
//            case "Gili Meno":
//                return "6";
//            case "Nusa Lembongan":
//                return "5";
//            case "Lombok":
//                return "4";
//            case "Bali" :
//            case "Padang Bai":
//                return "3";
//            case "Gili Air":
//                return "2";
//            case "Gili Trawangan":
//                return "1";
//        }
//        return null;
//    }

//    public String findCurrencyLombokFb(String currency) {
//        if (currency.contains("CHF")) {
//            return "10";
//        } else if (currency.contains("AUD")) {
//            return "9";
//        } else if (currency.contains("CAD")) {
//            return "8";
//        } else if (currency.contains("EUR")) {
//            return "7";
//        } else if (currency.contains("JPY")) {
//            return "5";
//        } else if (currency.contains("USD")) {
//            return "4";
//        } else if (currency.contains("GBP")) {
//            return "3";
//        } else if (currency.contains("SGD")) {
//            return "2";
//        } else if (currency.contains("IDR")) {
//            return "1";
//        }
//        return null;
//    }
}
