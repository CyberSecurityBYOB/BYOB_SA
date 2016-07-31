package byob.controllers;

import byob.entities.ConfigurationFile;
import byob.utils.GenericList;
import byob.utils.StringUtils;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public boolean minMaxIntegrityCheck(String minString, String maxString){
        if (!(StringUtils.isPrintableString(minString) && StringUtils.isPrintableString(maxString))) {
            return true;
        }
        if ( (!StringUtils.isPrintableString(minString) && StringUtils.isPrintableString(maxString)) ||
             (StringUtils.isPrintableString(minString) && !StringUtils.isPrintableString(maxString))){
            return false;
        }

        int min = Integer.valueOf(minString);
        int max = Integer.valueOf(maxString);
        return min < max;
    }

    public boolean urlsIntegrityCheck(GenericList genericList){

        if (genericList == null ||
            genericList.size() < 2){
            return false;
        }

        for (Object object : genericList){
            if (!StringUtils.isPrintableString(object) || !spaceIntegrityCheck(object)){
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

    public boolean fullIntegrityCheck() {
        return dateIntegrityCheck(configurationFile.getSleepModeDate()) &&
               minMaxIntegrityCheck(configurationFile.getMinFrequency(),configurationFile.getMaxFrequency()) &&
               minMaxIntegrityCheck(configurationFile.getSleepModeMinHour(),configurationFile.getSleepModeMaxHour()) &&
               urlsIntegrityCheck(configurationFile.getUrls()) &&
               spaceIntegrityCheck(configurationFile.getProxy()) &&
               spaceIntegrityCheck(configurationFile.getUserAgent());
    }
}
