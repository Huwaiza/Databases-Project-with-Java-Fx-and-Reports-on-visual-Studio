package ProjectDatabases;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.lang.Class.forName;

public class myConnection {

    Connection conn;
    Statement stmt;


    public myConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost\\sql2018:1433;databaseName=SOFTWARE; user=sa; password=huwaiza";
            conn = DriverManager.getConnection(connectionUrl);
            stmt = conn.createStatement();

            System.out.println("Driver Loaded.");

        }catch (Exception e){
            System.out.println("Unable to Load Driver." +e);
        }
    }

    public Connection setConnection(){
        try{
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\sql2017:1433; databaseName=SOFTWARE; user=sa; password=huwaiza");
            System.out.println("DataBase Connected.");
            return conn;
        }catch (Exception e){
            System.out.println("Database not connected.");
            return null;
        }
    }
}
