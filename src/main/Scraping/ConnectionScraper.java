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
    private GeneralMethods generalMethods;

    public ConnectionScraper(Search search) {
        this.search = search;
        this.results = new ArrayList<>();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        this.webDriver = new FirefoxDriver(options);
        this.generalMethods = new GeneralMethods();
    }

    public List<FerrySchedule> connectLombokFb() {
        try {
            //check if URL is lombok                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             options.addArguments("-headless");
            webDriver.get(search.getUrl());
            search.setStatus("OK"); //unnecessary, need review later
            webDriver.findElement(By.id("departure_region_id"));
            Select depRegion = new Select(webDriver.findElement(By.id("departure_region_id")));
            String test = generalMethods.findValueLombokFb(search.getFrom());

            depRegion.selectByValue(generalMethods.findValueLombokFb(search.getFrom()));
            webDriver.findElement(By.id("arrival_region_id"));
            Select arrivalRegion = new Select(webDriver.findElement(By.id("arrival_region_id")));
            arrivalRegion.selectByValue(generalMethods.findValueLombokFb(search.getTo()));

            //setting departure date
            webDriver.findElement(By.id("departure_date"));
            WebElement dateDeparture = webDriver.findElement(By.id("departure_date"));
            dateDeparture.clear();
            dateDeparture.sendKeys(search.getDepDate());

            //if return date is not put, then its one way
            if (search.getReturnDate() != null) {
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
            curr.selectByValue(generalMethods.findCurrencyLombokFb(search.getCurrency()));

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

    public List<FerrySchedule> connectToGiliTrf() {


        String url = "/https://gilitransfers.com/fast-boats/get-availability?";
        String departure = "departure_port=" + generalMethods.checkRouteGiliTrf(search.getFrom()) + "&";
        String arrival = "arrival_port=" + generalMethods.checkRouteGiliTrf(search.getTo()) + "&";
        String departureDate = "departure_date=" + search.getDepDate() + "&";
        String returnDate = "return=" + search.getReturnDate() + "&";
        String adult = "adults=" + search.getPassenger() + "&childs=0&infants=0&";
        String currency = "currency=" + search.getCurrency();

        if (search.getFrom().equals("Bali")) {
            search.setFrom("BALI_ALL_PORT");
            departure = "departure_port=" + search.getFrom() + "&";
        }

        if (search.getReturnDate() == null) {
            search.setReturnDate("0");
        }

        String urlAddress = url + departure + arrival + departureDate + returnDate + adult + currency;

        try {
            webDriver.get(urlAddress);
            System.out.println("can get address");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to connect to url");
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

}
