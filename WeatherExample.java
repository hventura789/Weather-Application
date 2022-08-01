//Hunter Ventura, CIT 244, 7/25/22

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Hunter Ventura
 *
 * This is an example of how a user can easily use the methods from my weather app API to create their own weather application.
 *
 * NOTE: when you are testing your program, make sure to enter the city in the format of either...
 * "City Name" alone OR "City Name, Country Shorthand" eg. Pittsburgh, US; or London, UK; or just Seattle
 */

public class WeatherExample extends WeatherApp_API {


    @Override
    public void start(Stage primaryStage) {

        // To access methods of the weather app API
        WeatherApp_API weather = new WeatherApp_API();

        // Each item you add will be added to the borderPane (bp)
        BorderPane bp = new BorderPane();

        // Create scene
        Scene scene = weather.createScene(bp, 625, 625);

        // BACKGROUND (Weather Image chosen by user)
        weather.setBackgroundImage(bp, "https://www.metoffice.gov.uk/binaries/content/gallery/metofficegovuk/hero-images/weather/world/holiday-weather/chicago1.jpg");

        // TOP title
        Label topTitleText = weather.createLabel("World Weather Finder", 38, Color.WHITE);

        //create HBox for title setting equal to the setBox method in API passing the label
        HBox top = weather.setBox(topTitleText);
        //move down the top HBox slightly
        top.setTranslateY(20);

        // Add the HBox for the title of the app to the top of the borderpane
        bp.setTop(top);

        //create the Text Field for the user to input the City they want to search for
        //passing it into the new HBox for this TextField
        TextField userText = createTextField(bp);

        //call method to get the user input
        getUserInput(userText);

        //method call for the text field action
        textFieldAction(userText, bp);

        //creates the button that allows the user to search when clicked
        Button search = createButton(bp);

        //method call to allow for button action passing the button and the location variable
        buttonAction(search, userText, bp);

        //create the primary stage for the weather app
        primaryStage.setTitle("Weather App 2022");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
