package com.vaishuinfo.setting;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Raghav
 */
public class DBResourseLoader implements Serializable {

    private final static long serialVersionUID = 1L;
    private static DBResourseLoader _instance;
    private static final String PROPERTY_PATH = "/com/vaishuinfo/propertiesfiles";
    // DB2 properties
    public Properties JdbcDatasource;
    // Log4j properties
    private static Properties Logger;
    private final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(getClass().getName());

    public Properties getJdbcDatasource() {
        return JdbcDatasource;
    }

    public void setJdbcDatasource(Properties JdbcDatasource) {
        this.JdbcDatasource = JdbcDatasource;
    }
    

    public Properties getLogger() {
        return Logger;
    }

    public static synchronized DBResourseLoader getInstance() {
        if (_instance == null) {
            _instance = new DBResourseLoader();
            PropertyConfigurator.configure(Logger);
        }
        return _instance;
    }

    private DBResourseLoader() {
        JdbcDatasource = getDatasourceProp();
        Logger = getLoggerProp();
    }

    /**
     * Read DB2 property file via InputStream and load into JdbcDatasource
     * object
     *
     * @return
     */
    private Properties getDatasourceProp() {
        Properties ds = new Properties();
        try {
            InputStream in = DBResourseLoader.class.getResourceAsStream(PROPERTY_PATH + "/" + "database.properties");
            if (in != null) {
                ds.load(in);
            }
            in.close();
        } catch (IOException ioe) {
            logger.log(Level.INFO, "Exception setting Database :{0}", ioe.getMessage());
        }
        return ds;
    }

    /**
     * Read Log4j property file via InputStream and load into Logger object
     *
     * @return
     */
    private Properties getLoggerProp() {
        Properties loggerProp = new Properties();
        try {
            InputStream in = DBResourseLoader.class.getResourceAsStream(PROPERTY_PATH + "/" + "log4j.properties");
            if (in != null) {
                loggerProp.load(in);
            }
            in.close();
        } catch (IOException ioe) {
            this.logger.log(Level.INFO, "Exception setting Logger :{0}", ioe.getMessage());
        }
        return loggerProp;
    }

    public Logger getLogger(Class c) {
        return LoggerFactory.getLogger(getClass().getName());
    }
}
