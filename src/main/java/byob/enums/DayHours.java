package byob.enums;

/**
 * Created by Alessandro on 30/07/2016.
 */
public enum DayHours {

    MIDNIGHT_AM ("0"),
    ELEVEN_PM ("23");

    private String name;

    DayHours(String s) {
        this.name = s;
    }

    public String getName(){
        return this.name;
    }
}
