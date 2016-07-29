package byob;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class FrequencyController implements Initializable {
    @FXML private Text textChangeFrequency;
    @FXML private Text textMinChangeFrequency;
    @FXML private Text textMaxChangeFrequency;

    @FXML private Slider sliderChangeFrequency;
    @FXML private Slider sliderMinChangeFrequency;
    @FXML private Slider sliderMaxChangeFrequency;


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
    }

}