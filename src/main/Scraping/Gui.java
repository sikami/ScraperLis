import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gui extends Application {

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


        ComboBox routes = new ComboBox();

        // get routes
        routes.getItems().add("Padang Bai to Gili Air");


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
        result.setDisable(true);
        layoutGeneral.setCenter(result);


        return layoutGeneral;






    }
}
