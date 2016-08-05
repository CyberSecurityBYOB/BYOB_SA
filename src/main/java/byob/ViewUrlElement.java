package byob;

import javafx.scene.control.Slider;
import lombok.Data;

/**
 * Created by Alessandro on 03/08/2016.
 */
@Data
public class ViewUrlElement extends GraphicUrlElement{
    private boolean checkToggleFixedFrequency;
    private boolean checkToggleFullDay;
    private boolean checkDisableComboBox;
    private Slider sliderMaxChangeFrequency;
    private Slider sliderChangeFrequency;
    private Slider sliderContacts;
    private Slider sliderMinChangeFrequency;
    private Slider sliderMaxHour;
    private Slider sliderMinHour;

    public ViewUrlElement() {
        super();
        checkToggleFixedFrequency = false;
        checkToggleFullDay = false;
        checkDisableComboBox = true;
        sliderChangeFrequency = new Slider(1,3600,1);
        sliderContacts = new Slider(1,100,1);
        sliderMaxChangeFrequency = new Slider(1,3600,1);
        sliderMaxHour = new Slider(0,23,1);
        sliderMinChangeFrequency = new Slider(1,3600,1);
        sliderMinHour = new Slider(0,23,1);
    }
}
