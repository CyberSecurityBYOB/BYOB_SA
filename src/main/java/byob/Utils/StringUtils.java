package byob.Utils;

import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Alessandro on 30/07/2016.
 */
public class StringUtils {

    public static String capitalize(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String fromTextFieldToOptionalString(TextField textField) {
        return Optional.ofNullable(textField).map(TextField::getText).orElse(null);
    }

    public static String fromDatePickerToOptionalString(DatePicker datePicker) {
        return Optional.ofNullable(datePicker).map(DatePicker::getValue).map(Object::toString).orElse(null);
    }

    public static String fromTextToOptionalString(Text text) {
        return Optional.ofNullable(text).map(Text::getText).orElse(null);
    }

    public static boolean isPrintableString(Object obj) {
        return obj != null &&
               obj instanceof String &&
               ((String) obj).length() > 0;
    }

    public static ArrayList<String> fromTextFieldsToStrings(ObservableList<TextField> textFields){
        ArrayList<String> strings = new ArrayList<>();
        for (TextField textField : textFields){
            strings.add(textField.getText());
        }
        return strings;
    }

    public static boolean isPrintableArrayList(ArrayList<Object> objects){
        return objects.size() > 0 && ((String)objects.get(0)).length() > 0;
    }
}
