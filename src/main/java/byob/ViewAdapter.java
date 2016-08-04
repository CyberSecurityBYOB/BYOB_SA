package byob;

import byob.enums.DayHours;
import byob.utils.GenericList;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import lombok.Data;

import java.util.ArrayList;

/**
 * Created by Alessandro on 04/08/2016.
 */
@Data
public class ViewAdapter {

    private GenericList<TextField> urls;
    private GenericList<TextField> proxys;
    private GenericList<TextField> userAgents;
    private GenericList<Text> contacts;
    private GenericList<Text> minFrequencys;
    private GenericList<Text> maxFrequencys;
    //private GenericList<Text> fixedFrequencys;
    private GenericList<DatePicker> sleepModeDates;
    private GenericList<Text> sleepModeMinHours;
    private GenericList<Text> sleepModeMaxHours;
    private ArrayList<ViewUrlElement> viewUrlElements;

    public ViewAdapter(ArrayList<ViewUrlElement> viewUrlElements, ObservableList<GridPane> gridPanes) {
        this.viewUrlElements = viewUrlElements;
        this.urls = new GenericList<>(TextField.class);
        proxys = new GenericList<>(TextField.class);
        userAgents = new GenericList<>(TextField.class);
        contacts = new GenericList<>(Text.class);
        minFrequencys = new GenericList<>(Text.class);
        maxFrequencys = new GenericList<>(Text.class);
        sleepModeDates = new GenericList<>(DatePicker.class);
        sleepModeMinHours = new GenericList<>(Text.class);
        sleepModeMaxHours = new GenericList<>(Text.class);
        fromGridPanesToTextFields(gridPanes);
    }

    public void convert() {
        for (ViewUrlElement viewUrlElement : viewUrlElements) {
            proxys.add(viewUrlElement.getProxy());
            userAgents.add(viewUrlElement.getUserAgent());
            contacts.add(viewUrlElement.getContacts());
            sleepModeDates.add(viewUrlElement.getSleepModeDate());

            //Check for Fixed Frequency
            if (viewUrlElement.isCheckToggleFixedFrequency()){
                minFrequencys.add(viewUrlElement.getFixedFrequency());
                maxFrequencys.add(viewUrlElement.getFixedFrequency());
            }
            else {
                minFrequencys.add(viewUrlElement.getMinFrequency());
                maxFrequencys.add(viewUrlElement.getMaxFrequency());
            }

            //Check for Full Day
            if (viewUrlElement.isCheckToggleFullDay()){
                sleepModeMinHours.add(new Text(DayHours.MIDNIGHT_AM.getName()));
                sleepModeMaxHours.add(new Text(DayHours.ELEVEN_PM.getName()));
            }
            else {
                sleepModeMinHours.add(viewUrlElement.getSleepModeMinHour());
                sleepModeMaxHours.add(viewUrlElement.getSleepModeMaxHour());
            }
        }
    }

    private TextField fromGridPaneToTextField(GridPane gridPane) {
        TextField textField = (TextField) gridPane.getChildren().get(1);
        return textField;
    }

    private void fromGridPanesToTextFields(ObservableList<GridPane> gridPanes) {
        for (GridPane gridPane : gridPanes) {
            urls.add(fromGridPaneToTextField(gridPane));
        }
    }

}
