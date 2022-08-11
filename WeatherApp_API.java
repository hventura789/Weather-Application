
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Weather App API -- CIT 244 Final Project
 * @author Hunter Ventura
 *
 * This class will allow the user to create their own simple weather application using JavaFX and an API from OpenWeatherMap.org
 * The user can use the methods in this class to easily create their own weather application
 */

public class WeatherApp_API extends Application {

    // To be overidden by demo class
    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    /**
     * Creates scene of the given width & height. If either value is less
     * than 100 then the window will default to a size of 500 X 500
     *
     * @param bp BorderPane used in your app
     * @param w User desired width of the GUI
     * @param h User desired height of the GUI
     * @return a Scene with desired Width & Height
     */
    public Scene createScene(BorderPane bp, int w, int h) {
        int[] sizeArr = {w, h};

        if (w < 100 || h < 100) {
            System.err.println("Invalid height or widht. Default size "
                    + "(500, 500) has been set");
            sizeArr[0] = 500;
            sizeArr[1] = 500;
        }
        Scene scene = new Scene(bp, sizeArr[0], sizeArr[1]);
        return scene;
    }

    /**
     * This method returns a map based on json data -- it requires gson imports to work and to convert data from json to a map!
     *
     * @param str - used to convert the data from json to a java map
     * @return Map<String, Object> - returns the data from the API in a format that can be used in a java program
     */
    public Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson (
                str, new TypeToken<HashMap<String, Object>>() {}.getType()
        );
        return map;
    }

    /**
     * Gets weather data from openWeather API -- requires the jsonToMap in order to work correctly!
     *
     * @param loc - the user entered location for wherever they want to return weather from openWeather API
     * @return String of the weather data - specifically the temperature and the humidity for whatever location searched for
     */
    public String getWeather(String loc) {
        
        //**Enter your own unique API key for the openweathermap.org API in the String below to make the app functional**
        String API_KEY = "";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" +
                loc + "&appid=" + API_KEY + "&units=imperial";

        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while((line = rd.readLine()) != null) {

                result.append(line);
            }
            rd.close();
            //below prints out ALL of the data in the map
           //System.out.println(result);

            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());

            //System.out.println("Current Temperature: " + mainMap.get("temp"));
            //System.out.println("Current Humidity: " + mainMap.get("humidity"));

            //below contains the unicode symbol for degrees fahrenheit
            String weatherData = "Current Temperature: " + mainMap.get("temp") + "\u2109" +
                    "\nCurrent Humidity: " + mainMap.get("humidity") + "%";

            return weatherData;

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);

        } catch(FileNotFoundException e) {
            //I'm trying to get the below to display on the GUI when an invalid City is entered and it's not displaying for some reason
            BorderPane bp = new BorderPane();
            System.out.println("Please re-enter a valid city name. We are too cheap for Spell Check lol.");
            Label weatherData = new Label("Please re-enter a valid city name. We are too cheap for Spell Check lol.");
            HBox badWeatherBox = new HBox(weatherData);
            badWeatherBox.setTranslateY(-75);
            weatherData.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
            weatherData.setTextFill(Color.WHITE);
            badWeatherBox.setAlignment(Pos.BOTTOM_CENTER);
            bp.setBottom(badWeatherBox);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return "";
    }

    /**
     * Sets the background of the window to your desired image
     * @param bp - The pane the image will be placed on
     * @param url - The link to the  image
     */
    public void setBackgroundImage(BorderPane bp, String url) {
        BackgroundImage myImage = new BackgroundImage(new Image(url, bp.getHeight(), bp.getWidth(), false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //then you set to your node
        bp.setBackground(new Background(myImage));
    }

    /**
     * Creates a label of defined text, size and color.
     *
     * @param txt The text of the label that will appear at the top of the scene
     * @param size size of the text
     * @param c Choice of color
     * @return label
     */

    public Label createLabel(String txt, int size, Color c) {

        Label title = new Label(txt);
        title.setFont(Font.font("Calibri", FontWeight.BOLD,
                FontPosture.REGULAR, size));
        title.setTextFill(c);

        return title;
    }

    /**
     * Creates the HBox for the top of the window passing in the label L1
     *
     * @param L1 user defined label
     * @return HBox
     */
    public HBox setBox(Label L1) {
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(L1);

        return box;
    }

    /**
     * This method creates a text field that the user can enter the city they wish to search for inside
     * @param bp - passes a borderpane to correctly place the bp within the scene
     * @return TextField with Event Handler
     */
    public TextField createTextField(BorderPane bp) {

        //Scanner scan = new Scanner(System.in);
        TextField userInputText = new TextField();
        userInputText.setPromptText("Enter a City to Get Weather Data");
        userInputText.setFocusTraversable(false);
        userInputText.setPrefSize(350, 40);
        //create the HBox that the textField will go into
        HBox boxUserInput = new HBox(userInputText);
        //center the HBox for user input in the middle of the scene
        boxUserInput.setAlignment(Pos.CENTER);
        boxUserInput.setTranslateY(-20);
        //add the HBox for userInput into the center of the borderpane
        bp.setCenter(boxUserInput);

        return userInputText;
    }

    /**
     * Creates the event handler that causes the text field action to occur
     * @param tf - passes the textfield that contains the user input
     * @param bp - passes the borderpane so that the weather data can be displayed
     */
    public void textFieldAction(TextField tf, BorderPane bp) {

        tf.setOnAction(e -> {

            String location1 = getUserInput(tf);
            String weatherData = getWeather(location1);

            //System.out.println("test");
            //create new Label and HBox to display the weather data
            Label labelData = new Label(weatherData);
            HBox weatherBox = new HBox(labelData);
            weatherBox.setTranslateY(-85);
            labelData.setFont(Font.font("Calibri", FontWeight.BOLD, 24));
            labelData.setTextFill(Color.WHITE);
            weatherBox.setAlignment(Pos.BOTTOM_CENTER);
            bp.setBottom(weatherBox);
        });
    }

    /**
     * Gets the user input from the text field and stores it in a String variable that can be accessed in other methods
     * @param tf
     * @return String - the user input
     */
    public String getUserInput(TextField tf) {

        String location1 = tf.getText();

        return location1;
    }

    /**
     * creates the button that is used to search for the city of the users choice
     * @param bp - passes a borderpane to create the borderpane that the button will be in
     * @return returns the button that the user can press to search
     */
    public Button createButton(BorderPane bp) {

        // Create Search button
        Button searchB = new Button("Go");
        TextField tf = new TextField();
        tf.setPrefWidth(55);
        searchB.setPrefWidth(65);
        VBox button = new VBox(searchB);
        button.setAlignment(Pos.CENTER_RIGHT);
        //the two below are to move the button to be in the right position
        button.setTranslateX(-75);
        button.setTranslateY(-20);
        bp.setRight(button);

        return searchB;
    }

    /**
     * This method is the button action handler, it prints out the weather data when the button is clicked
     * @param sB - passes the button
     * @param tf - passes a textfield that is used to get the user input
     * @param bp - passes a borderpane so that the weather data can be neatly displayed at the bottom of the scene
     */
    public void buttonAction(Button sB, TextField tf, BorderPane bp) {

        sB.setOnMouseClicked(e -> {

            String location1 = getUserInput(tf);
            String weatherData = getWeather(location1);
            //System.out.println("test");
            //create new Label and HBox to display the weather data
            Label labelData2 = new Label(weatherData);
            HBox weatherBox = new HBox(labelData2);
            weatherBox.setTranslateY(-85);
            labelData2.setFont(Font.font("Calibri", FontWeight.BOLD, 24));
            labelData2.setTextFill(Color.WHITE);
            weatherBox.setAlignment(Pos.BOTTOM_CENTER);
            bp.setBottom(weatherBox);
        });
    }

}






