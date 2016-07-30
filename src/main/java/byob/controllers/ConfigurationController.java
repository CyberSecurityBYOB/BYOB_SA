package byob.controllers;

import byob.entities.ConfigurationFile;
import byob.enums.FilePaths;
import lombok.Data;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alessandro on 29/07/2016.
 */

public class ConfigurationController {

    private ConfigurationFile configurationFile;
    private static ConfigurationController instance;

    private ConfigurationController (){

    }

    private ConfigurationController (ConfigurationFile configurationFile){
        this.configurationFile = configurationFile;
    }

    public static ConfigurationController getInstance(ConfigurationFile configurationFile){
        if (instance == null) {
            return new ConfigurationController(configurationFile);
        }
        else {
            instance.configurationFile = configurationFile;
            return instance;
        }
    }

    public void writeFile(){
        String path = FilePaths.BYOB_SA.getPath();

        List<String> lines = new ArrayList<String>();
        try {
            //for (Object fieldValue : configurationFile) {
            //    lines.add("print('foobar');");
            //}

            for (Field field : ConfigurationFile.class.getDeclaredFields()) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), ConfigurationFile.class);
                Method getter = pd.getReadMethod();
                Object f = getter.invoke(this.configurationFile);
                if (f instanceof String) {
                    System.out.println(field.getName());
                    System.out.println(f);
                }
                //lines.add(field.getName());
                //lines.add(f);
            }
            //Files.write(Paths.get(path), lines);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
