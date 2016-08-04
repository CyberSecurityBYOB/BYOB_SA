package byob;

import byob.enums.DayHours;
import byob.utils.GenericList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    public ViewAdapter(ArrayList<ViewUrlElement> viewUrlElements) {
        this.viewUrlElements = viewUrlElements;
    }

    public void convert() {
        urls = viewUrlElements.get(0).getUrls();
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
}
