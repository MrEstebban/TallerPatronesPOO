package FactoryMethodPattern;

import Implementation.AdaptadorMySQL;
import Utilities.UtilidadesAcceso;

import java.util.Properties;

public class FactoryBaseDatos {

    private static final String DB_FACTORY_PROPERTY_URL = "ConfigurationFactory.properties";
    private static final String DEFAULT_DB_CLASS_PROP = "defaultDBClass";
    public FactoryBaseDatos(){
    }

    public static IntAdaptador getDefaultDBAdapter() {
        try {
            Properties prop = UtilidadesAcceso.loadProperty(DB_FACTORY_PROPERTY_URL);
            String defaultDBClass = prop.getProperty(DEFAULT_DB_CLASS_PROP);
            System.out.println("DefaultDBClass ==> " + defaultDBClass);
            return (IntAdaptador) Class.forName(defaultDBClass).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
