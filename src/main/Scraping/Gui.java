import javafx.application.Application;
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

public class Gui extends Application {
    //class
    private Search newSearch;

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

        //website url to scrape
        url.getItems().addAll("https://www.lombokfastboats.com/");

        // get routes
        ComboBox routes = new ComboBox();
        String route1 = "Padang Bai";
        String route2 = "Gili Air";
        Label pbGa = new Label(route1 + " to " + route2);
        pbGa.setTextFill(Color.BLACK);


        routes.getItems().add(pbGa);


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
        Label departLabel = new Label("Departure date (yyyy mm dd): ");
        TextField departText = new TextField();

        //return date text
        Label returnLabel = new Label("Return date (if any) (yyyy mm dd): ");
        TextField returnText = new TextField();

        hBox2.getChildren().addAll(departLabel, departText, returnLabel, returnText);


        HBox hBox3 = new HBox();
        hBox3.setSpacing(30);
        hBox3.setAlignment(Pos.CENTER);

        //passenger amount
        Label passengerLabel = new Label("Passenger amount: ");
        ComboBox passList = new ComboBox();
        Label passItem = new Label("1");
        passItem.setTextFill(Color.BLACK);
        passList.getItems().add(passItem);

        //currency
        Label currencyLabel = new Label("Currency");
        ComboBox currencyList = new ComboBox();
        Label curr = new Label("IDR");
        curr.setTextFill(Color.BLACK);
        currencyList.getItems().add(curr);

        //enter to Search clas need to improve later on to count on amount of letters and whether its all digit
        if (returnLabel.getText().equals("")) {
            enterToClass(urlLabel, route1, route2, departText, passItem, curr);
        } else {
            enterToClassReturn(urlLabel, route1, route2, departText, returnText, passItem, curr);
        }

        //button to scrape
        Button searchButton = new Button("Scrape Now");

        hBox3.getChildren().addAll(passengerLabel, passList, currencyLabel, currencyList);
        vBox.getChildren().addAll(title, hBox1, hBox2,hBox3, searchButton);
        layoutGeneral.setTop(vBox);

        //result display
        TextArea result = new TextArea("Result will be shown here");
        result.setDisable(true);
        layoutGeneral.setCenter(result);

        return layoutGeneral;
    }

    //if one way
    private void enterToClass(Label urlLabel, String route1, String route2, TextField departDate, Label passenger, Label currency) {
        this.newSearch = new Search(urlLabel.getText(), route1, route2, departDate.getText(), passenger.getText(), currency.getText());
    }

    //if return

    private void enterToClassReturn(Label urlLabel, String route1, String route2, TextField departDate, TextField returnDate, Label passenger, Label currency) {
        this.newSearch = new Search(urlLabel.getText(), route1, route2, departDate.getText(), returnDate.getText(), passenger.getText(), currency.getText());
    }
}
