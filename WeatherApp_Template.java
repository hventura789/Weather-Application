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
 * This file is designed to be used as a File, Save As for a beginner programmer to create their own
 * version of the program by following the API documentation.
 *
 * Using an instance of the class (not needed as we extend WeatherApp_API, but
 * easier to see what each method does, without referencing the API documentation PDF).
 *
 * NOTE: when you are testing your program, make sure to enter the city in the format of either
 * "City Name" alone OR "City Name, Country Shorthand" eg. Pittsburgh, US; or London, UK; or just Seattle
 *
 * Lines for programmer to change are labeled with TODO:
 * Uncomment code in the Start method following the API to create your own weather application!
 */
public class WeatherApp_Template extends WeatherApp_API {

    @Override
    public void start(Stage primaryStage) {

        // TODO: Un-comment the code below to access methods of the weather app API.
        //WeatherApp_API weather = new WeatherApp_API();

        // TODO: create the borderpane that each item you add will be added to
        //BorderPane bp = new BorderPane();

        // TODO: weather.createScene()
        //Scene scene =

        // TODO: BACKGROUND (Weather Image chosen by user)
        //weather.setBackgroundImage()

        // TODO: create top Title for the scene
        //Label topTitleText = weather.createLabel()

        // TODO: create HBox for title setting equal to the setBox method in API passing the label
        //HBox top = weather.setBox(); make sure to pass in your label
        //move down the top HBox slightly
        //top.setTranslateY()

        // TODO: Add the HBox for the title of the app to the top of the borderpane and pass the above HBox
        //bp.setTop()

        // TODO: create the Text Field for the user to input the City they want to search for
        //passing it into the new HBox for this TextField -- use the weather.createTextField() method
        //TextField userText =

        //TODO: call method to get the user input and pass the above text field
        //weather.getUserInput()

        //TODO: call method for the text field action passing the textfield and the borderpane
        //weather.textFieldAction()

        //TODO: create the button that allows the user to search when clicked using the createButton() method
        //Button search =

        //TODO: call the method to allow for button action passing the button and the textfield and the borderpane
        //weather.buttonAction()

        // TODO: create the primary stage for the weather app with the code below
        //primaryStage.setTitle("Weather App 2022");
        //primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        //primaryStage.sizeToScene();
        //primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
