package byob.controllers;

import byob.entities.ConfigurationFile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PaneController implements Initializable {

    //Frequency
    @FXML private Text textChangeFrequency;
    @FXML private Text textMinChangeFrequency;
    @FXML private Text textMaxChangeFrequency;

    @FXML private Slider sliderChangeFrequency;
    @FXML private Slider sliderMinChangeFrequency;
    @FXML private Slider sliderMaxChangeFrequency;

    @FXML private ToggleButton toggleFixedFrequency;

    @FXML private GridPane minVariableFrequencyGridPane;
    @FXML private GridPane maxVariableFrequencyGridPane;

    //Sleep Mode
    @FXML private GridPane minHourGridPane;
    @FXML private GridPane maxHourGridPane;

    @FXML private ToggleButton toggleFullDay;

    @FXML private Text textMinHour;
    @FXML private Text textMaxHour;

    @FXML private Slider sliderMinHour;
    @FXML private Slider sliderMaxHour;

    @FXML private DatePicker sleepModeDatePicker;

    //Contacts
    @FXML private Slider sliderContacts;

    @FXML private Text textContacts;

    //Proxy
    @FXML private TextField textProxy;

    //UserAgent
    @FXML private TextField textUserAgent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sliderChangeFrequency.valueProperty().addListener( new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textChangeFrequency.setText(String.format("%.0f", newValue));
            }
        });

        sliderMinChangeFrequency.valueProperty().addListener( new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textMinChangeFrequency.setText(String.format("%.0f", newValue));
            }
        });

        sliderMaxChangeFrequency.valueProperty().addListener( new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textMaxChangeFrequency.setText(String.format("%.0f", newValue));
            }
        });

        sliderMinHour.valueProperty().addListener( new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textMinHour.setText(String.format("%.0f", newValue));
            }
        });

        sliderMaxHour.valueProperty().addListener( new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textMaxHour.setText(String.format("%.0f", newValue));
            }
        });

        sliderContacts.valueProperty().addListener( new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textContacts.setText(String.format("%.0f", newValue));
            }
        });
    }

    @FXML
    private void handleToggleFixedFrequency(ActionEvent event) {

        if (toggleFixedFrequency.isSelected()) {

            textChangeFrequency.setVisible(true);
            sliderChangeFrequency.setVisible(true);
            minVariableFrequencyGridPane.setVisible(false);
            maxVariableFrequencyGridPane.setVisible(false);
            sliderMinChangeFrequency.setVisible(false);
            sliderMaxChangeFrequency.setVisible(false);
            textChangeFrequency.setManaged(true);
            sliderChangeFrequency.setManaged(true);
            minVariableFrequencyGridPane.setManaged(false);
            maxVariableFrequencyGridPane.setManaged(false);
            sliderMinChangeFrequency.setManaged(false);
            sliderMaxChangeFrequency.setManaged(false);
        }
        else{

            textChangeFrequency.setVisible(false);
            sliderChangeFrequency.setVisible(false);
            minVariableFrequencyGridPane.setVisible(true);
            maxVariableFrequencyGridPane.setVisible(true);
            sliderMinChangeFrequency.setVisible(true);
            sliderMaxChangeFrequency.setVisible(true);
            textChangeFrequency.setManaged(false);
            sliderChangeFrequency.setManaged(false);
            minVariableFrequencyGridPane.setManaged(true);
            maxVariableFrequencyGridPane.setManaged(true);
            sliderMinChangeFrequency.setManaged(true);
            sliderMaxChangeFrequency.setManaged(true);
        }
    }

    @FXML
    private void handleToggleFullDay(ActionEvent event) {

        if (toggleFullDay.isSelected()) {
            minHourGridPane.setVisible(false);
            sliderMinHour.setVisible(false);
            sliderMaxHour.setVisible(false);
            maxHourGridPane.setVisible(false);
            //sleepModeDatePicker.setVisible(true);
            minHourGridPane.setManaged(false);
            maxHourGridPane.setManaged(false);
            sliderMaxHour.setManaged(false);
            sliderMinHour.setManaged(false);
            //sleepModeDatePicker.setManaged(true);
        }
        else{
            minHourGridPane.setVisible(true);
            sliderMinHour.setVisible(true);
            sliderMaxHour.setVisible(true);
            //sleepModeDatePicker.setVisible(false);
            maxHourGridPane.setVisible(true);
            minHourGridPane.setManaged(true);
            maxHourGridPane.setManaged(true);
            sliderMaxHour.setManaged(true);
            sliderMinHour.setManaged(true);
            //sleepModeDatePicker.setManaged(false);
        }
    }

    private void packageConfiguration(){
        ConfigurationFile configurationFile = new ConfigurationFile();

    }

    private void submitConfiguration(){
        packageConfiguration();
    }

}