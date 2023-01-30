package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//creating singleton class
public class DatabaseConnection {

    static Connection connection = null;
    public static Connection getConnection(){


        if(connection != null){
            //System.out.println("Siddhant");
            return connection;
        }

        //database name
        String db = "searchenginejava";
        //database user name
        String user = "root";
        String pwd = "siddhant";
        return getConnection(db,user,pwd);

    }

    private static Connection getConnection(String db , String user , String pwd){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+db+"?user="+user+"&password="+pwd);

        }catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return connection;



    }


}
