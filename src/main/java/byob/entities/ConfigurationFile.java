package byob.entities;

import lombok.Data;

import java.util.ArrayList;

/**
 * Created by Alessandro on 29/07/2016.
 */

@Data
public class ConfigurationFile {

    private ArrayList<String> urls;
    private String proxy;
    private String userAgent;
    private String contacts;
    private String minFrequency;
    private String maxFrequency;
    private String fixedFrequency;
    private String sleepModeDate;
    private String sleepModeMinHour;
    private String sleepModeMaxHour;

    public ConfigurationFile(){
        this.urls = new ArrayList<String>();
    }
}
