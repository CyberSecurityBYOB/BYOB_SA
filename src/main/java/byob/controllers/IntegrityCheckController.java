package byob.controllers;

import byob.entities.ConfigurationFile;
import byob.utils.GenericList;
import byob.utils.StringUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Alessandro on 31/07/2016.
 */
public class IntegrityCheckController {

    private ConfigurationFile configurationFile;
    private static IntegrityCheckController instance;

    private IntegrityCheckController (){

    }

    private IntegrityCheckController (ConfigurationFile configurationFile){
        this.configurationFile = configurationFile;
    }

    public static IntegrityCheckController getInstance(ConfigurationFile configurationFile){
        if (instance == null) {
            return new IntegrityCheckController(configurationFile);
        }
        else {
            instance.configurationFile = configurationFile;
            return instance;
        }
    }

    public boolean dateIntegrityCheck(String dateInString){
        if (!StringUtils.isPrintableString(dateInString)){
            return true;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate oldDate = LocalDate.parse(dateInString,formatter);
        if (oldDate == null) {
            return false;
        }
        LocalDate newDate = LocalDate.now();
        int oldDay = oldDate.getDayOfYear();
        int newDay = newDate.getDayOfYear();
        return oldDay>=newDay;
    }

    public boolean dateIntegrityChecks(GenericList genericList) {
        for (Object object : genericList) {
            String string = (String) object;
            if (!dateIntegrityCheck(string)) {
                System.out.println("Date Error");
                return false;
            }
        }
        return true;
    }

    public boolean comboIntegrityCheck(String comboInString){
        return StringUtils.isWritableString(comboInString);
    }

    public boolean comboIntegrityChecks(GenericList genericComboList, GenericList genericDateList) {
        for (int i=0; i<genericComboList.size(); i++){
            String stringCombo = (String) genericComboList.get(i);
            String stringDate = (String) genericDateList.get(i);
            if (!comboIntegrityCheck(stringCombo)) {
                System.out.println("Combo Error");
                return false;
            }
            if (!stringCombo.equals(StringUtils.UNKNOWN) && !StringUtils.isPrintableString(stringDate)) {
                System.out.println("Date Combo Error");
                return false;
            }
        }
        return true;
    }

    public boolean minMaxIntegrityCheck(String minString, String maxString){
        if (!(StringUtils.isPrintableString(minString) && StringUtils.isPrintableString(maxString))) {
            return true;
        }
        if ( (!StringUtils.isPrintableString(minString) && StringUtils.isPrintableString(maxString)) ||
             (StringUtils.isPrintableString(minString) && !StringUtils.isPrintableString(maxString))){
            System.out.println("Min Max Error");
            return false;
        }


        int min = Integer.valueOf(minString);
        int max = Integer.valueOf(maxString);
        return min <= max;
    }

    public boolean minMaxIntegrityChecks(GenericList genericListMin, GenericList genericListMax) {

        for (int i = 0; i<genericListMin.size(); i++){
            String stringMin = (String) genericListMin.get(i);
            String stringMax = (String) genericListMax.get(i);
            if (!minMaxIntegrityCheck(stringMin,stringMax)) {
                System.out.println("Min Max Error");
                return false;
            }
        }
        return true;
    }

    public boolean urlsIntegrityCheck(GenericList genericList){

        if (genericList == null ||
            genericList.size() < 2){
            System.out.println("Url Error, too small Generic List");
            return false;
        }

        for (Object object : genericList){
            if (!StringUtils.isPrintableString(object) || !spaceIntegrityCheck(object)){
                System.out.println("Url Error");
                return false;
            }
        }

        return true;
    }

    public boolean ntpsIntegrityCheck(GenericList genericList){

        for (Object object : genericList){
            if (!StringUtils.isWritableString(object) || !spaceIntegrityCheck(object)){
                System.out.println("NTP Error");
                return false;
            }
        }

        return true;
    }

    private boolean spaceIntegrityCheck(Object object){
        if (!StringUtils.isPrintableString(object)){
            return true;
        }
        String string = (String) object;
        return !string.contains(" ");
    }

    private boolean spaceIntegrityChecks(GenericList genericList) {
        for (Object object : genericList) {
            if (!spaceIntegrityCheck(object)){
                System.out.println("Space Error");
                return false;
            }
        }
        return true;
    }

    public boolean fullIntegrityCheck() {
        return comboIntegrityChecks(configurationFile.getRepeats(), configurationFile.getSleepModeDates()) &&
               dateIntegrityChecks(configurationFile.getSleepModeDates()) &&
               minMaxIntegrityChecks(configurationFile.getMinFrequencys(),configurationFile.getMaxFrequencys()) &&
               minMaxIntegrityChecks(configurationFile.getSleepModeMinHours(),configurationFile.getSleepModeMaxHours()) &&
               urlsIntegrityCheck(configurationFile.getUrls()) &&
               spaceIntegrityChecks(configurationFile.getProxys()) &&
               spaceIntegrityChecks(configurationFile.getUserAgents()) &&
               ntpsIntegrityCheck(configurationFile.getNetworkTimeServers());

    }
}
