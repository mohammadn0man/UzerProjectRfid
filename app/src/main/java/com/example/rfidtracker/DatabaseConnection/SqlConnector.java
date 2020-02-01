package com.example.rfidtracker.DatabaseConnection;

import android.util.Log;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class SqlConnector {

    private static final String  TAG = "SqlConnector";

    public static Connection getMySqlConnection()    {
        /* Declare and initialize a sql Connection variable. */
        Connection ret = null;

        try
        {

            Log.d(TAG, "before com.mysql.jdbc.Driver");
            /* Register for jdbc driver class. */
            Class.forName("com.mysql.jdbc.Driver");

            Log.d(TAG, "after com.....");

            /* Create connection url. */

            /*connection url for remotemysql.com database free database host service*/

            String mysqlConnUrl = "jdbc:mysql://db4free.net:3306/rfidproj";       //mobile
            String mysqlUserName = "uzerjamal";
            String mysqlPassword = "zxcvb321";


            /* Get the Connection object. */
            ret = DriverManager.getConnection(mysqlConnUrl + "?autoReconnect=true&useSSL=false", mysqlUserName , mysqlPassword);

            Log.d(TAG, "after getConnection");

            /* Get related meta data for this mysql server to verify db connect successfully.. */
            DatabaseMetaData dbmd = ret.getMetaData();

            String dbName = dbmd.getDatabaseProductName();

            String dbVersion = dbmd.getDatabaseProductVersion();

            String dbUrl = dbmd.getURL();

            String userName = dbmd.getUserName();

            String driverName = dbmd.getDriverName();

            Log.d(TAG, "Database Name is " + dbName);

            Log.d(TAG, "Database Version is " + dbVersion);

            Log.d(TAG, "Database Connection Url is " + dbUrl);

            Log.d(TAG, "Database User Name is " + userName);

            Log.d(TAG, "Database Driver Name is " + driverName);

        }catch(Exception ex)
        {
            ex.printStackTrace();
        }finally
        {
            Log.d(TAG, "inside Finally block");
            return ret;
        }
    }
}
