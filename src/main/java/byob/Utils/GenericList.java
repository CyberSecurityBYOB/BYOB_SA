package byob.Utils;

import java.util.ArrayList;

/**
 * Created by Alessandro on 30/07/2016.
 */
public class GenericList <T> extends ArrayList<T> {
    private Class<T> genericType;

    public GenericList(Class<T> c) {
        this.genericType = c;
    }

    public Class<T> getGenericType() {
        return genericType;
    }
}