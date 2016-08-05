package byob.enums;

/**
 * Created by Alessandro on 05/08/2016.
 */
public enum Versions {
    NewVersion ("3"),
    OldVersion ("2");

    private String name;

    Versions(String s) {
        this.name = s;
    }

    public String getName(){
        return this.name;
    }
}
