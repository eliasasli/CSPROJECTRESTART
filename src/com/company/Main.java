package com.company;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {


    public static void main(String[] args) throws Exception {


            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//EA210847//OneDrive - Reigate College//Computer Science NEA//DatabaseforCS//Database.accdb");
            Statement statement = connection.createStatement();
            ResultSet resultset =  statement.executeQuery("Select * from Database");
            while (resultset.next())
                System.out.println(resultset.getString(1) + "\t" + resultset.getString(2));

            connection.close();

}
}
