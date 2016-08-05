package byob.enums;

/**
 * Created by Alessandro on 05/08/2016.
 */
public enum Versions {
    NewVersion ("4"),
    OldVersion ("3");

    private String name;

    Versions(String s) {
        this.name = s;
    }

    public String getName(){
        return this.name;
    }
}
