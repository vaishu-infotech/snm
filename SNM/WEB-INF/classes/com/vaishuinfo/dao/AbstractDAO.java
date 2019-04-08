package com.vaishuinfo.dao;

import com.vaishuinfo.setting.DBResourseLoader;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.slf4j.Logger;

/**
 *
 * @author Raghav
 */
public class AbstractDAO implements Serializable {

    protected Logger logger = DBResourseLoader.getInstance().getLogger(getClass());

    protected String getString(ResultSet resInfo, String columnName, String defaultValue) {
        String value = defaultValue;
        try {
            value = resInfo.getString(columnName);
            value = value.trim();
        } catch (Exception e) {
        }
        return value;
    }

    protected void setString(PreparedStatement prepStmnt, int parameterIndex, String value, int columnWidth) throws Exception {
        if (value != null && value.length() > columnWidth) {
            prepStmnt.setString(parameterIndex, value.substring(0, columnWidth));
        } else {
            prepStmnt.setString(parameterIndex, value);
        }
    }

    protected void close(PreparedStatement prepStmnt) {
        try {
            if (prepStmnt != null) {
                prepStmnt.close();
            }
        } catch (Exception e) {
        }
        prepStmnt = null;
    }

    protected void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
        }
        con = null;
    }

    protected void close(ResultSet res) {
        try {
            if (res != null) {
                res.close();
            }
        } catch (Exception e) {
        }
        res = null;
    }
}
