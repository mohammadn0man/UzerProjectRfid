package com.example.rfidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rfidtracker.DatabaseConnection.SqlConnector;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
//    private static final String dburl ="jdbc:mysql://85.10.205.173:3306/rfidproj?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
//    private static final String dbusername = "uzerjamal";
//    private static final String dbpassword = "zxcvb321";
    TextView name, rollno, cls, location;
    private static final String  TAG = "SqlConnector";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("Hello!");

        name = findViewById(R.id.nameField);
        cls = findViewById(R.id.classField);
        rollno = findViewById(R.id.rollnoField);
        location = findViewById(R.id.locationField);

        new ConnectMySql().execute("aa");
    }

    private class ConnectMySql extends AsyncTask<String, Void, String>{
        ResultSet rs;
        String s1, s2, s3;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(String... params){

            Connection conn = null;
            SqlConnector connection = new SqlConnector();

            conn = connection.getMySqlConnection();
                System.out.println("Connected!");

            try {
                Statement statement = conn.createStatement();
                String query = "SELECT * FROM `student`";
                Log.e(TAG, " Query == " + query);
                rs = statement.executeQuery(query);
                rs.next();
                s1 = rs.getString(1);
                s2 = rs.getString(2);
                s3 = rs.getString(3);
                Log.e(TAG, " s1 " + s1 + " s2 " + s2 + " s3 " + s3);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return "Success";
        }

        @Override
        protected void onPostExecute(String result){
            try{
                name.setText(s1);
                cls.setText(s2);
                rollno.setText(s3);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
}

