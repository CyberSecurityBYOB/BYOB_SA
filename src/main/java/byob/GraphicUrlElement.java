package byob;

import byob.utils.GenericList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Data;

/**
 * Created by Alessandro on 02/08/2016.
 */
@Data
public class GraphicUrlElement {

    private GenericList urls;
    private TextField proxy;
    private TextField userAgent;
    private Text contacts;
    private Text minFrequency;
    private Text maxFrequency;
    private Text fixedFrequency;
    private DatePicker sleepModeDate;
    private Text sleepModeMinHour;
    private Text sleepModeMaxHour;
    private boolean checkToggleFixedFrequency;
    private boolean checkToggleFullDay;
    private ComboBox comboBoxRepeat;
    private TextField ntpServerUrl;


    public GraphicUrlElement(){
        urls = new GenericList(TextField.class);
        proxy = new TextField("");
        userAgent = new TextField("");
        contacts = new Text("");
        minFrequency = new Text("");
        maxFrequency = new Text("");
        fixedFrequency = new Text("");
        sleepModeDate = new DatePicker();
        sleepModeMinHour = new Text("");
        sleepModeMaxHour = new Text("");
        comboBoxRepeat = new ComboBox();
        ntpServerUrl = new TextField("");
    }

}
