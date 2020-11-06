import javafx.application.Application;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static javafx.application.Application.launch;


public class Main {
    public static void main(String[] args) {
//        Search firstSearch = new Search("https://www.lombokfastboats.com/", "Bali", "Gili Air", "2020-11-20", "1", "IDR");
//        ConnectionScraper scraper = new ConnectionScraper(firstSearch);
//        scraper.connectLombokFb();

        launch(Gui.class);

    }
}
