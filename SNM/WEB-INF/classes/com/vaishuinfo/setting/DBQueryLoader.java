package com.vaishuinfo.setting;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author
 */
public class DBQueryLoader {

    private final static long serialVersionUID = 1L;
    private static DBQueryLoader _queryInstance;
    private org.slf4j.Logger logger = DBResourseLoader.getInstance().getLogger(getClass());
    /**
     * holds SQL statements
     */
    private static Properties queries;

    /**
     * return object instance. if object is not found, create new instance.
     * otherwise return existing object
     */
    public static synchronized DBQueryLoader getInstance() {
        if (_queryInstance == null) {
            _queryInstance = new DBQueryLoader();
        }
        return _queryInstance;
    }

    /**
     * Call loadQueryProperties() method
     */
    private DBQueryLoader() {
        loadQueryProperties();
    }

    /**
     * Read property file containing list of SQL statements and load into a
     * Properties object
     *
     */
    private void loadQueryProperties() {
        if (queries == null) {
            queries = new Properties();
        }
        try {
            InputStream in = DBQueryLoader.class.getResourceAsStream("/com/vaishuinfo/propertiesfiles/queries.properties");
            if (in != null) {
                queries.load(in);
            }
            in.close();
        } catch (IOException ioe) {
            logger.info("Exception loading queries:" + ioe.getMessage());
        }
    }

    /**
     * Call this method to get the SQL query statement
     *
     * @param queryId
     * @return SQL statement
     */
    public String getQueryStatement(String queryId) {
        return (String) queries.get(queryId);
    }
}
