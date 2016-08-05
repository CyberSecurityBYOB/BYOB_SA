package byob.controllers;

import byob.utils.GenericList;
import byob.utils.StringUtils;
import byob.entities.ConfigurationFile;
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
    private String filePath;

    private ConfigurationController (){

    }

    private ConfigurationController (ConfigurationFile configurationFile, String filePath){
        this.configurationFile = configurationFile;
        this.filePath = filePath;
    }

    public static ConfigurationController getInstance(ConfigurationFile configurationFile, String filePath){
        if (instance == null) {
            return new ConfigurationController(configurationFile, filePath);
        }
        else {
            instance.configurationFile = configurationFile;
            return instance;
        }
    }

    public void writeFile(){
        String path = this.filePath;

        List<String> lines = new ArrayList<String>();
        try {
            for (Field field : ConfigurationFile.class.getDeclaredFields()) {
                String rowString = "";

                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), ConfigurationFile.class);
                Method getter = pd.getReadMethod();
                Object obj = getter.invoke(this.configurationFile);
                if (StringUtils.isWritableString(obj)){
                    System.out.println(StringUtils.capitalize(field.getName()));
                    System.out.println(obj);
                    String objInString = (String) obj;
                    rowString = StringUtils.capitalize(field.getName());
                    rowString = rowString + " " + objInString;
                    lines.add(rowString);
                }
                else {
                    if (StringUtils.isPrintableGenericList(obj)) {
                        GenericList strings = (GenericList) obj;
                        System.out.println(StringUtils.capitalize(field.getName()));
                        rowString = StringUtils.capitalize(field.getName());
                        for (Object string : strings) {
                            if (StringUtils.isWritableString(string)) {
                                System.out.println(string);
                                String objInString = (String) string;
                                rowString = rowString + " " + objInString;
                            }
                        }
                        lines.add(rowString);
                    }
                }
            }
            Files.write(Paths.get(path), lines);
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException | IOException e) {
            e.printStackTrace();
        }
    }
}
