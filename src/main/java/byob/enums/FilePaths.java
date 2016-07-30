package byob.enums;

/**
 * Created by Alessandro on 30/07/2016.
 */
public enum FilePaths {

    BYOB_SA ("C:\\Users\\Alessandro\\Documents\\Sicurezza Informatica\\Configs\\byob_config.txt");

    private String path;

    FilePaths(String s) {
        this.path = s;
    }

    public String getPath(){
        return this.path;
    }
}
