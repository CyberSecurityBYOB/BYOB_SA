package byob.utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by Alessandro on 30/07/2016.
 */
public class StringUtils {

    public static String UNKNOWN = "UNKNOWN";

    public static String capitalize(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String fromTextFieldToOptionalString(TextField textField) {
        return Optional.ofNullable(textField).map(TextField::getText).orElse(UNKNOWN);
    }

    public static String fromDatePickerToOptionalString(DatePicker datePicker) {
        return Optional.ofNullable(datePicker).map(DatePicker::getValue).map(Object::toString).orElse(UNKNOWN);
    }

    public static String fromObjectToOptionalString(Object object) {
        return Optional.ofNullable(object).map(Object::toString).orElse(UNKNOWN);
    }

    public static GenericList fromComboBoxesToStrings(GenericList genericList) {
        GenericList genericStringList = new GenericList(String.class);

        for (Object object : genericList) {
            ComboBox comboBox = (ComboBox) object;
            genericStringList.add(fromObjectToOptionalString(comboBox.getValue()));
        }
        return genericStringList;
    }

    public static String fromTextToOptionalString(Text text) {
        return Optional.ofNullable(text).map(Text::getText).orElse(UNKNOWN);
    }

    public static boolean isPrintableString(Object obj) {
        return obj != null &&
               obj instanceof String &&
               ((String) obj).length() > 0 &&
               !(((String) obj).equals(UNKNOWN)) ;
    }

    public static boolean isWritableString(Object obj) {
        return obj != null &&
                obj instanceof String &&
                ((String) obj).length() > 0;
    }

    public static GenericList fromTextFieldsToStrings(GenericList<TextField> textFields){
        GenericList strings = new GenericList(String.class);
        for (TextField textField : textFields){
            strings.add(textField.getText().equals("") ? UNKNOWN : textField.getText());
        }
        return strings;
    }

    public static GenericList fromTextToStrings(GenericList<Text> texts){
        GenericList strings = new GenericList(String.class);
        for (Text text : texts){
            strings.add(text.getText().equals("") ? UNKNOWN : text.getText());
        }
        return strings;
    }

    public static GenericList fromDatePickersToStrings(GenericList<DatePicker> datePickers){
        GenericList strings = new GenericList(String.class);
        for (DatePicker datePicker : datePickers){
            strings.add(fromDatePickerToOptionalString(datePicker));
        }
        return strings;
    }

    //public static boolean isPrintableArrayList(ArrayList<Object> objects){
    //    return objects.size() > 0 && ((String)objects.get(0)).length() > 0;
    //}

    public static boolean isPrintableGenericList(Object genericList){
        return genericList != null &&
               genericList instanceof GenericList &&
               ((GenericList)genericList).size() > 0 &&
               ((String)((GenericList)genericList).get(0)).length() > 0;
    }
}
