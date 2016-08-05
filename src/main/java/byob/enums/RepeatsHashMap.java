package byob.enums;

import java.util.HashMap;

/**
 * Created by Alessandro on 05/08/2016.
 */
public class RepeatsHashMap extends HashMap<String, String>{

    public RepeatsHashMap(){
        put(Repeats.EveryDay.getName(),"1");
        put(Repeats.EveryTwoDays.getName(),"2");
        put(Repeats.EveryThreeDays.getName(),"3");
        put(Repeats.EveryFourDays.getName(),"4");
        put(Repeats.EveryFiveDays.getName(),"5");
        put(Repeats.EverySixDays.getName(),"6");
        put(Repeats.EveryWeek.getName(),"7");
        put(Repeats.EveryMonth.getName(),"30");
        put(Repeats.EveryYear.getName(),"365");
    }
}
