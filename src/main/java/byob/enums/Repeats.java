package byob.enums;

/**
 * Created by Alessandro on 05/08/2016.
 */
public enum Repeats {

    EveryDay ("Every Day"),
    EveryTwoDays ("Every 2 Days"),
    EveryThreeDays ("Every 3 Days"),
    EveryFourDays ("Every 4 Days"),
    EveryFiveDays ("Every 5 Days"),
    EverySixDays ("Every 6 Days"),
    EveryWeek ("Every Week"),
    EveryMonth ("Every Month"),
    EveryYear ("Every Year");

    private String name;

    Repeats(String s) {
        this.name = s;
    }

    public String getName(){
        return this.name;
    }

}
