import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
        url.getItems().add("https://gilitransfers.com/");


        //website url to scrape
        url.getItems().addAll();

        // get routes -->NEEDS TO MAKE IT TIDY
        ComboBox routes = new ComboBox();
        String mainlandRoute = RoutesConstant.BL.getRoutes();


        routes.getItems().addAll(RoutesConstant.BL.getRoutes() + " to " + RoutesConstant.GA.getRoutes(),
                RoutesConstant.BL.getRoutes() + " to " + RoutesConstant.NC.getRoutes(),
                RoutesConstant.BL.getRoutes() + " to " + RoutesConstant.NP.getRoutes(),
                RoutesConstant.BL.getRoutes() + " to " + RoutesConstant.GM.getRoutes(),
                RoutesConstant.BL.getRoutes() + " to " + RoutesConstant.NL.getRoutes(),
                RoutesConstant.BL.getRoutes() + " to " + RoutesConstant.LM.getRoutes(),
                RoutesConstant.BL.getRoutes() + " to " + RoutesConstant.GT.getRoutes());



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
        passList.getItems().addAll("1", "2");


        //currency
        Label currencyLabel = new Label("Currency");
        ComboBox currencyList = new ComboBox();
        currencyList.getItems().addAll("IDR", "EUR", "GBP");




        //button to scrape
        Button searchButton = new Button("Scrape Now");




        hBox3.getChildren().addAll(passengerLabel, passList, currencyLabel, currencyList);
        vBox.getChildren().addAll(title, hBox1, hBox2,hBox3, searchButton);
        layoutGeneral.setTop(vBox);

        //result display
        TextArea result = new TextArea("Result will be shown here");
        layoutGeneral.setCenter(result);

        //add event listener for result to appear on display
        searchButton.setOnAction(actionEvent -> {

            List<FerrySchedule> ferryScheduleList = new ArrayList<>();
            String routeTo = GeneralMethods.checkRoute((String) routes.getValue());
            String passengerValue = (String) passList.getValue();
            String currencyValue = (String) currencyList.getValue();
            String urlValue = (String) url.getValue();
            String depart = departText.getText();
            String returnDates = returnText.getText();
            ConnectionScraper connectionScraper = new ConnectionScraper(newSearch);

            if (url.getValue().equals("https://www.lombokfastboats.com/")) {

                //enter to Search class need to improve later on to count on amount of letters and whether its all digit
                if (returnText.getText().equals("")) {
                    enterToClass(urlValue, mainlandRoute, routeTo, depart, passengerValue, currencyValue);
                } else {

                    enterToClassReturn(urlValue, mainlandRoute, routeTo, depart, returnDates, passengerValue, currencyValue);
                }


                ferryScheduleList = connectionScraper.connectLombokFb();

            } else if (url.getValue().equals("https://gilitransfers.com/")) {
                enterToClassReturn(urlValue, mainlandRoute, routeTo, depart, returnDates, passengerValue, currencyValue);
                ferryScheduleList = connectionScraper.connectToGiliTrf();
            }

            result.setText(connectionScraper.printResult(ferryScheduleList));
            connectionScraper.closeWebDriver();


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
