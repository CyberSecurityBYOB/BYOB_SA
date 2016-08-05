package byob.enums;

/**
 * Created by Alessandro on 05/08/2016.
 */
public enum Repeats {

    EveryDay ("EveryDay"),
    EveryWeek ("EveryWeek"),
    EveryMonth ("EveryMonth"),
    EveryYear ("EveryYear");

    private String name;

    Repeats(String s) {
        this.name = s;
    }

    public String getName(){
        return this.name;
    }
}
