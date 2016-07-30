package byob.controllers;

import byob.entities.ConfigurationFile;
import lombok.Data;

/**
 * Created by Alessandro on 29/07/2016.
 */

public class ConfigurationController {

    private ConfigurationFile configurationFile;
    private static ConfigurationController instance;

    private ConfigurationController (){

    }

    private ConfigurationController (ConfigurationFile configurationFile){
        this.configurationFile = configurationFile;
    }

    public static ConfigurationController getInstance(ConfigurationFile configurationFile){
        if (instance == null) {
            return new ConfigurationController(configurationFile);
        }
        else {
            instance.configurationFile = configurationFile;
            return instance;
        }
    }

    public void writeFile(){

    }
}
