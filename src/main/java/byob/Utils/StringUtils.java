package byob.Utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by Alessandro on 30/07/2016.
 */
public class StringUtils {

    public static String capitalize(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String fromTextFieldToOptionalString(TextField textField) {
        return Optional.ofNullable(textField).map(TextField::getText).get();
    }

    public static String fromDatePickerToOptionalString(DatePicker datePicker) {
        return Optional.ofNullable(datePicker).map(DatePicker::getPromptText).get();
    }

    public static String fromTextToOptionalString(Text text) {
        return Optional.ofNullable(text).map(Text::getText).get();
    }
}