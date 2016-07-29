package byob;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

public class FrequencyController {
    @FXML private Text textChangeFrequency;
    @FXML private Slider sliderChangeFrequency;

    @FXML protected void handleChangeFrequency(ActionEvent event) {
        textChangeFrequency.setText(String.valueOf(sliderChangeFrequency.valueProperty().getValue().intValue()));
    }

}