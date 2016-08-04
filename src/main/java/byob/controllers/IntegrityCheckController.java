package byob.controllers;

import byob.entities.ConfigurationFile;
import byob.utils.GenericList;
import byob.utils.StringUtils;

import javax.swing.text.DateFormatter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = formatter.parse(dateInString);
            Date dateNow = new Date();
            return date.compareTo(dateNow)>=0;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
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
        return dateIntegrityChecks(configurationFile.getSleepModeDates()) &&
               minMaxIntegrityChecks(configurationFile.getMinFrequencys(),configurationFile.getMaxFrequencys()) &&
               minMaxIntegrityChecks(configurationFile.getSleepModeMinHours(),configurationFile.getSleepModeMaxHours()) &&
               urlsIntegrityCheck(configurationFile.getUrls()) &&
               spaceIntegrityChecks(configurationFile.getProxys()) &&
               spaceIntegrityChecks(configurationFile.getUserAgents());

    }
}
