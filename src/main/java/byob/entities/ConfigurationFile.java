package byob.entities;

import byob.enums.Versions;
import byob.utils.GenericList;
import lombok.Data;

/**
 * Created by Alessandro on 29/07/2016.
 */

@Data
public class ConfigurationFile {
    private String version;
    private GenericList urls;
    private GenericList proxys;
    private GenericList userAgents;
    private GenericList contacts;
    private GenericList minFrequencys;
    private GenericList maxFrequencys;
    //private GenericList fixedFrequencys;
    private GenericList sleepModeDates;
    private GenericList sleepModeMinHours;
    private GenericList sleepModeMaxHours;
    private GenericList repeats;
    private GenericList networkTimeServers;

    public ConfigurationFile(){
        this.version = Versions.NewVersion.getName();
        this.urls = new GenericList<>(String.class);
        this.proxys = new GenericList<>(String.class);
        this.userAgents = new GenericList<>(String.class);
        this.contacts = new GenericList<>(String.class);
        this.minFrequencys = new GenericList<>(String.class);
        this.maxFrequencys = new GenericList<>(String.class);
        this.sleepModeDates = new GenericList<>(String.class);
        this.sleepModeMinHours = new GenericList<>(String.class);
        this.sleepModeMaxHours = new GenericList<>(String.class);
        this.repeats = new GenericList<>(String.class);
        this.networkTimeServers = new GenericList<>(String.class);
    }
}
