package byob.controllers;

import byob.GraphicUrlElement;
import byob.ViewAdapter;
import byob.ViewUrlElement;
import byob.enums.Repeats;
import byob.utils.StringUtils;
import byob.entities.ConfigurationFile;
import byob.enums.DayHours;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaneController implements Initializable {

    private Double MAX_URL_WIDTH = 265.0;

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

    //List of URLs
    //@FXML private ListView<TextField> urls;
    //private ObservableList<TextField> items;
    @FXML private ListView<GridPane> urls;
    private ObservableList<GridPane> items;

    @FXML private Button submitButton;

    @FXML private ComboBox comboBoxRepeat;

    private Stage stage;
    private String filePath;

    private ToggleGroup toggleGroupUrl;

    //private ArrayList<GraphicUrlElement> urlElements;
    private ArrayList<ViewUrlElement> urlElements;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        urlElements = new ArrayList<>();

        toggleGroupUrl = new ToggleGroup();
        items = FXCollections.observableArrayList ();
        //items.add(new TextField());
        items.add(getUrlGridPane(true));
        urls.setItems(items);

        //urlElements.add(new GraphicUrlElement());
        urlElements.add(new ViewUrlElement());

        ObservableList<String> options =
        FXCollections.observableArrayList(
                Repeats.EveryDay.getName(),
                Repeats.EveryWeek.getName(),
                Repeats.EveryMonth.getName(),
                Repeats.EveryYear.getName()
        );
        comboBoxRepeat.setItems(options);

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

        toggleGroupUrl.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                //RadioButton radioButton = (RadioButton) toggleGroupUrl.getSelectedToggle();
                RadioButton oldRadioButton = (RadioButton) old_toggle;
                RadioButton radioButton = (RadioButton) new_toggle;
                if (radioButton != null) {
                    GridPane parentGridPane = (GridPane)radioButton.getParent();
                    int index = items.indexOf(parentGridPane);
                    //System.out.println(index);
                    ViewUrlElement viewUrlElement = urlElements.get(index);
                    //System.out.println(viewUrlElement.getContacts().getText());
                    //store all the graphical components to the old graphic component
                    int oldIndex = getOldGraphicIndex(old_toggle);
                    //System.out.println(oldIndex);
                    urlElements.set(oldIndex,getElementFromGraphicComponents());
                    //System.out.println(urlElements.get(oldIndex).getContacts().getText());
                    //set all attributes to graphical components
                    setAllGraphicalAttributes(viewUrlElement);
                    //System.out.println("Graphic");
                    //System.out.println(textContacts.getText());
                }
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

    @FXML
    private void addItem(ActionEvent event){
        //items.add(new TextField());
        items.add(getUrlGridPane(false));
        urlElements.add(new ViewUrlElement());
    }

    @FXML
    private void showComboBox(ActionEvent event){
        comboBoxRepeat.setDisable(false);
    }

    private boolean checkToggleFullDay(){
        return this.toggleFullDay.isSelected();
    }

    private boolean checkToggleFixedFrequency(){
        return this.toggleFixedFrequency.isSelected();
    }

    private boolean checkDisableComboBox() { return this.comboBoxRepeat.isDisable(); }

    private GridPane getUrlGridPane(boolean enabled){
        GridPane gridPane = new GridPane();
        RadioButton radioButton = new RadioButton();
        radioButton.setToggleGroup(this.toggleGroupUrl);
        gridPane.add(radioButton,0,0);
        TextField textUrlField = new TextField();
        textUrlField.setPrefWidth(MAX_URL_WIDTH);
        gridPane.add(textUrlField,1,0);
        radioButton.setSelected(enabled);
        return gridPane;
    }

    private ViewUrlElement getElementFromGraphicComponents() {
        ViewUrlElement viewUrlElement = new ViewUrlElement();
        setAllElementAttributes(viewUrlElement);
        return viewUrlElement;
    }

    private void storeLastAttribute() {
            int storeIndex = getOldGraphicIndex(toggleGroupUrl.getSelectedToggle());
            urlElements.set(storeIndex,getElementFromGraphicComponents());
    }

    private void resetAllSliders() {
        sliderMaxChangeFrequency.setValue(sliderMaxChangeFrequency.getMin());
        sliderChangeFrequency.setValue(sliderChangeFrequency.getMin());
        sliderContacts.setValue(sliderContacts.getMin());
        sliderMinChangeFrequency.setValue(sliderMinChangeFrequency.getMin());
        sliderMaxHour.setValue(sliderMaxHour.getValue());
        sliderMinHour.setValue(sliderMinHour.getValue());

        textMaxChangeFrequency.setText("");
        textContacts.setText("");
        textChangeFrequency.setText("");
        textMinChangeFrequency.setText("");
        textMaxHour.setText("");
        textMinHour.setText("");
    }

    private void setAllElementAttributes(ViewUrlElement graphicUrlElement) {
        //GraphicUrlElement stuff
        graphicUrlElement.getContacts().setText(textContacts.getText());
        graphicUrlElement.getFixedFrequency().setText(textChangeFrequency.getText());
        graphicUrlElement.getMaxFrequency().setText(textMaxChangeFrequency.getText());
        graphicUrlElement.getMinFrequency().setText(textMinChangeFrequency.getText());
        graphicUrlElement.getProxy().setText(textProxy.getText());
        graphicUrlElement.getSleepModeDate().setValue(sleepModeDatePicker.getValue());
        graphicUrlElement.getUserAgent().setText(textUserAgent.getText());
        graphicUrlElement.getSleepModeMaxHour().setText(textMaxHour.getText());
        graphicUrlElement.getSleepModeMinHour().setText(textMinHour.getText());
        //System.out.println(graphicUrlElement.getMinFrequency().getText());
        //ViewUrlElement stuff
        graphicUrlElement.setCheckToggleFixedFrequency(checkToggleFixedFrequency());
        graphicUrlElement.setCheckToggleFullDay(checkToggleFullDay());
        graphicUrlElement.setCheckDisableComboBox(!checkDisableComboBox());
        graphicUrlElement.getSliderChangeFrequency().setValue(sliderChangeFrequency.getValue());
        graphicUrlElement.getSliderContacts().setValue(sliderContacts.getValue());
        graphicUrlElement.getSliderMaxHour().setValue(sliderMaxHour.getValue());
        //Double doubleValue = sliderMinChangeFrequency.valueProperty().getValue();
        //Integer integerValue = doubleValue.intValue();
        //graphicUrlElement.getSliderMinChangeFrequency().valueProperty().setValue(integerValue);
        graphicUrlElement.getSliderMinChangeFrequency().setValue(sliderMinChangeFrequency.getValue());
        graphicUrlElement.getSliderMaxChangeFrequency().setValue(sliderMaxChangeFrequency.getValue());
        graphicUrlElement.getSliderMinHour().setValue(sliderMinHour.getValue());

        graphicUrlElement.getComboBoxRepeat().setValue(comboBoxRepeat.getValue());
        //System.out.println("sliderMinChangeFrequency.getValue() in setAllElementAttributes");
        //System.out.println(sliderMinChangeFrequency.getValue());
        //System.out.println("graphicUrlElement.getSliderMinChangeFrequency().getValue() in setAllElementAttributes");
        //System.out.println(graphicUrlElement.getSliderMinChangeFrequency().getValue());
    }

    private void setAllGraphicalAttributes(ViewUrlElement graphicUrlElement) {
        //ViewUrlElement stuff
        toggleFixedFrequency.setSelected(graphicUrlElement.isCheckToggleFixedFrequency());
        handleToggleFixedFrequency(null);
        toggleFullDay.setSelected(graphicUrlElement.isCheckToggleFullDay());
        handleToggleFullDay(null);
        comboBoxRepeat.setDisable(graphicUrlElement.isCheckDisableComboBox());
        sliderChangeFrequency.setValue(graphicUrlElement.getSliderChangeFrequency().getValue());
        sliderContacts.setValue(graphicUrlElement.getSliderContacts().getValue());
        sliderMaxChangeFrequency.setValue(graphicUrlElement.getSliderMaxChangeFrequency().getValue());
        sliderMaxHour.setValue(graphicUrlElement.getSliderMaxHour().getValue());
        sliderMinChangeFrequency.setValue(graphicUrlElement.getSliderMinChangeFrequency().getValue());
        sliderMinHour.setValue(graphicUrlElement.getSliderMinHour().getValue());

        //System.out.println(sliderMinChangeFrequency.getValue());

        //GraphicUrlElement stuff
        textProxy.setText(graphicUrlElement.getProxy().getText());
        textUserAgent.setText(graphicUrlElement.getUserAgent().getText());
        textChangeFrequency.setText(graphicUrlElement.getFixedFrequency().getText());
        textContacts.setText(graphicUrlElement.getContacts().getText());
        textMaxChangeFrequency.setText(graphicUrlElement.getMaxFrequency().getText());
        textMaxHour.setText(graphicUrlElement.getSleepModeMaxHour().getText());
        textMinChangeFrequency.setText(graphicUrlElement.getMinFrequency().getText());
        textMinHour.setText(graphicUrlElement.getSleepModeMinHour().getText());
        sleepModeDatePicker.setValue(graphicUrlElement.getSleepModeDate().getValue());

        comboBoxRepeat.setValue(graphicUrlElement.getComboBoxRepeat().getValue());
        //System.out.println("textMinChangeFrequency.getText() in setAllGraphicalAttributes");
        //System.out.println(textMinChangeFrequency.getText());
    }

    private GraphicUrlElement getOldGraphicElement(Toggle old_toggle){
        RadioButton oldRadioButton = (RadioButton) old_toggle;
        GridPane oldParentGridPane = (GridPane)oldRadioButton.getParent();
        int oldIndex = items.indexOf(oldParentGridPane);

        if (oldIndex != -1) {
            return urlElements.get(oldIndex);
        }

        return null;
    }

    private int getOldGraphicIndex(Toggle old_toggle){
        RadioButton oldRadioButton = (RadioButton) old_toggle;
        GridPane oldParentGridPane = (GridPane)oldRadioButton.getParent();
        return items.indexOf(oldParentGridPane);
    }

    private ConfigurationFile packageConfiguration(){
        ConfigurationFile configurationFile = new ConfigurationFile();
        //Optional<TextField> optTextField = Optional.ofNullable(textProxy);
        ViewAdapter viewAdapter = new ViewAdapter(urlElements,items);
        viewAdapter.convert();

        //configurationFile.setProxy(StringUtils.fromTextFieldToOptionalString(textProxy));
        configurationFile.setProxys(StringUtils.fromTextFieldsToStrings(viewAdapter.getProxys()));
        //configurationFile.setUserAgent(StringUtils.fromTextFieldToOptionalString(textUserAgent));
        configurationFile.setUserAgents(StringUtils.fromTextFieldsToStrings(viewAdapter.getUserAgents()));
        //configurationFile.setContacts(StringUtils.fromTextToOptionalString(textContacts));
        configurationFile.setContacts(StringUtils.fromTextToStrings(viewAdapter.getContacts()));
        //configurationFile.setSleepModeDate(StringUtils.fromDatePickerToOptionalString(sleepModeDatePicker));
        configurationFile.setSleepModeDates(StringUtils.fromDatePickersToStrings(viewAdapter.getSleepModeDates()));
        //configurationFile.setUrls(StringUtils.fromTextFieldsToStrings(ur));
        configurationFile.setUrls(StringUtils.fromTextFieldsToStrings(viewAdapter.getUrls()));

        //Transferred to the ViewAdapter
        configurationFile.setSleepModeMinHours(StringUtils.fromTextToStrings(viewAdapter.getSleepModeMinHours()));
        configurationFile.setSleepModeMaxHours(StringUtils.fromTextToStrings(viewAdapter.getSleepModeMaxHours()));

        configurationFile.setMinFrequencys(StringUtils.fromTextToStrings(viewAdapter.getMinFrequencys()));
        configurationFile.setMaxFrequencys(StringUtils.fromTextToStrings(viewAdapter.getMaxFrequencys()));

        configurationFile.setRepeats(StringUtils.fromComboBoxesToStrings(viewAdapter.getComboBoxRepeats()));
        //configurationFile.setProxy(textProxy.getText());
        //configurationFile.setUserAgent(textUserAgent.getText());
        //configurationFile.setContacts(textContacts.getText());
        //configurationFile.setSleepModeDate(sleepModeDatePicker.getValue().toString());

        return configurationFile;
    }

    @FXML
    private void submitConfiguration(){
        storeLastAttribute();
        ConfigurationFile configurationFile = packageConfiguration();
        IntegrityCheckController integrityCheckController = IntegrityCheckController.getInstance(configurationFile);

        if (!integrityCheckController.fullIntegrityCheck()){
            showErrorAlertDialog();
            return;
        }

        ConfigurationController configurationController = ConfigurationController.getInstance(configurationFile, this.filePath);
        configurationController.writeFile();
    }

    @FXML
    private void showFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(this.stage);
        if (file != null){
            this.submitButton.setDisable(false);
            this.filePath = file.getAbsolutePath();
        }
        else {
            this.submitButton.setDisable(true);
        }
    }

    private void showErrorAlertDialog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ooops, there was an error!");
        alert.setContentText("Probably you have missed some fields or typed a wrong sleep mode date.");
        alert.showAndWait();
    }

}