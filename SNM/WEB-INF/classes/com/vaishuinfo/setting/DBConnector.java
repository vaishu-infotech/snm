package com.vaishuinfo.setting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raghav
 */
public class DBConnector implements java.io.Serializable {

    static final long serialVersionUID = 1L;
    private static Properties pwdProp;
    private static String url;
    private static String userId;
    private static String pwd;
    private static DBConnector _instance;
    private org.slf4j.Logger logger = DBResourseLoader.getInstance().getLogger(getClass());

    private DBConnector() {
        logger.info("Loading Driver");
    }

    public static synchronized DBConnector getInstance() {
        if (_instance == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                _instance = new DBConnector();
                pwdProp = DBResourseLoader.getInstance().JdbcDatasource;
                url = (String) pwdProp.getProperty("url");
                userId = (String) pwdProp.getProperty("userId");
                pwd = (String) pwdProp.getProperty("pwd");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return _instance;
    }

    public Connection getConnection(boolean boolautoCommit) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, userId, pwd);
            if (con != null) {
                if (boolautoCommit) {
                    con.setAutoCommit(true);
                } else {
                    con.setAutoCommit(false);
                }
            } else {
            }
        } catch (Exception err) {
            logger.info("Connection Error:" + err.getMessage());
        }
        return con;
    }
}
