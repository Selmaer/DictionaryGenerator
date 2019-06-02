package dictionarygenerator;

import java.io.*;
import java.util.Properties;

public class PropertiesFile {
    private static final File cfg = new File("properties.cfg");

    private static void propertiesFileCreate() {

        Properties prop = new Properties();

        prop.put("DEFAULT_FILE_NAME", "New Dictionary");
        prop.put("DEFAULT_FILE_EXTENSION", ".txt");
        prop.put("DEFAULT_DIRECTORY_PATH", "C:\\"/*"shell:UsersFilesFolder\\Desktop"*/);

        prop.put("FILE_NAME", "");
        prop.put("FILE_EXTENSION", "");
        prop.put("DIRECTORY_PATH", "");

        try {
            cfg.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveProperties(cfg, prop);
    }

    private static Properties getProperties (File cfg) {
        Properties pref = new Properties();
        try {
            InputStream is = new FileInputStream(cfg);
            pref.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pref;
    }

    private static void saveProperties (File cfg, Properties prop) {
        try {
            OutputStream os = new FileOutputStream(cfg);
            prop.store(os, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getValue(String key) {
        if(!cfg.exists()) {
            propertiesFileCreate();
        }
        Properties prop = getProperties(cfg);
        String value = prop.getProperty(key);
        if (value.isEmpty()) {
            value = prop.getProperty("DEFAULT_" + key);
        }
        return value;
    }

    private static void setValue(String key, String value) {
        if(!cfg.exists()) {
            propertiesFileCreate();
        }
        if (!value.isEmpty()) {
            Properties prop = getProperties(cfg);
            prop.replace(key, value);
            saveProperties(cfg, prop);
        }
    }

    public static String getFileName() {
        return getValue("FILE_NAME");
    }

    public static String getFileExtension() {
        return getValue("FILE_EXTENSION");
    }

    public static String getDirectoryPath() {
        return getValue("DIRECTORY_PATH");
    }

    public static void setFileName(String value) {
        setValue("FILE_NAME", value);
    }

    public static void setFileExtension(String value) {
        setValue("FILE_EXTENSION", value);
    }

    public static void setDirectoryPath(String value) {
        setValue("DIRECTORY_PATH", value);
    }


}