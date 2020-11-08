import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

public class Gui extends Application {
    //class
    private Search newSearch = new Search();

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(borderPane());
        stage.setScene(scene);
        stage.show();

    }

    public BorderPane borderPane() {
        //Title
        BorderPane layoutGeneral = new BorderPane();

        Label title = new Label("Lis's Scraper");
        title.setStyle("-fx-font-weight: bold");
        title.setFont(new Font("Arial", 16));

        // Form
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(30);

        ComboBox url = new ComboBox();
        url.getItems().add("https://www.lombokfastboats.com/");


        //website url to scrape
        url.getItems().addAll();

        // get routes -->NEEDS TO MAKE IT TIDY
        ComboBox routes = new ComboBox();
        String route1 = "Bali";
        String route2 = "Gili Air";
        String route3 = "Nusa Ceningan";
        String route4 = "Nusa Penida";
        String route5 = "Gili Meno";
        String route6 = "Nusa Lembongan";
        String route7 = "Lombok";
        String route8 = "Gili Trawangan";
//        Label pbGa = new Label(route1 + " to " + route2);
//        Label pbNc = new Label(route1 + " to " + route3);
//        Label pbNp = new Label(route1 + " to " + route4);
//        Label pbGm = new Label(route1 + " to " + route5);
//        Label pbNl = new Label(route1 + " to " + route6);
//        Label pbL = new Label(route1 + " to " + route7);
//        Label pbGt = new Label(route1 + " to " + route8);
//        pbGa.setTextFill(Color.BLACK);
//        pbNc.setTextFill(Color.BLACK);
//        pbNp.setTextFill(Color.BLACK);
//        pbGm.setTextFill(Color.BLACK);
//        pbNl.setTextFill(Color.BLACK);
//        pbL.setTextFill(Color.BLACK);
//        pbGt.setTextFill(Color.BLACK);

        routes.getItems().addAll(route1 + " to " + route2,
                route1 + " to " + route3,
                route1 + " to " + route4,
                route1 + " to " + route5,
                route1 + " to " + route6,
                route1 + " to " + route7,
                route1 + " to " + route8);



        Label urlLabel = new Label("URL: ");
        Label routeLabel = new Label("Route: ");

        hBox1.getChildren().addAll(urlLabel, url, routeLabel, routes);


        HBox hBox2 = new HBox();
        hBox2.setSpacing(30);
        hBox2.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20,20,20,20));

        //departure date text
        Label departLabel = new Label("Departure date (yyyy-mm-dd): ");
        TextField departText = new TextField();

        //return date text
        Label returnLabel = new Label("Return date (if any) (yyyy-mm-dd): ");
        TextField returnText = new TextField();

        hBox2.getChildren().addAll(departLabel, departText, returnLabel, returnText);


        HBox hBox3 = new HBox();
        hBox3.setSpacing(30);
        hBox3.setAlignment(Pos.CENTER);

        //passenger amount
        Label passengerLabel = new Label("Passenger amount: ");
        ComboBox passList = new ComboBox();
        passList.getItems().add("1");


        //currency
        Label currencyLabel = new Label("Currency");
        ComboBox currencyList = new ComboBox();
        currencyList.getItems().add("IDR");




        //button to scrape
        Button searchButton = new Button("Scrape Now");




        hBox3.getChildren().addAll(passengerLabel, passList, currencyLabel, currencyList);
        vBox.getChildren().addAll(title, hBox1, hBox2,hBox3, searchButton);
        layoutGeneral.setTop(vBox);

        //result display
        TextArea result = new TextArea("Result will be shown here");
        layoutGeneral.setCenter(result);

        //add event listener for result to appear on display
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                List<FerrySchedule> ferryScheduleList;
                if (url.getValue().equals("https://www.lombokfastboats.com/")) {

                    //enter to Search class need to improve later on to count on amount of letters and whether its all digit
                    if (returnLabel.getText().equals("")) {
                        String urlValue = (String) url.getValue();
                        String depart = departText.getText();
                        String passengerValue = (String) passList.getValue();
                        String currencyValue = (String) currencyList.getValue();

                        enterToClass(urlValue, route1, route2, depart, passengerValue, currencyValue);
                    } else {
                        String urlValue = (String) url.getValue();
                        String depart = departText.getText();
                        String returnDates = returnText.getText();
                        String passengerValue = (String) passList.getValue();
                        String currencyValue = (String) currencyList.getValue();

                        enterToClassReturn(urlValue, route1, route2, depart, returnDates, passengerValue, currencyValue);
                    }

                    ConnectionScraper connectionScraper = new ConnectionScraper(newSearch);
                    ferryScheduleList = connectionScraper.connectLombokFb();
                    result.setText(connectionScraper.printResult(ferryScheduleList));
                    connectionScraper.closeWebDriver();
                }
            }
        });

        return layoutGeneral;
    }

    //if one way
    private void enterToClass(String urlLabel, String route1, String route2, String departDate, String passenger, String currency) {
        newSearch.setUrl(urlLabel);
        newSearch.setFrom(route1);
        newSearch.setTo(route2);
        newSearch.setDepDate(departDate);
        newSearch.setPassenger(passenger);
        newSearch.setCurrency(currency);
    }

    //if return

    private void enterToClassReturn(String urlLabel, String route1, String route2, String departDate, String returnDate, String passenger, String currency) {
        newSearch.setUrl(urlLabel);
        newSearch.setFrom(route1);
        newSearch.setTo(route2);
        newSearch.setDepDate(departDate);
        newSearch.setReturnDate(returnDate);
        newSearch.setPassenger(passenger);
        newSearch.setCurrency(currency);
    }




}
