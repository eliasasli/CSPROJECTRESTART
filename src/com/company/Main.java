package com.company;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {


    public static void main(String[] args) throws Exception {






    Connection connection = DriverManager.getConnection("jdbc:ucanaccess://Databaseyesyes.accdb");

    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery("Select * from User");
           while (resultset.next())
                   System.out.println(resultset.getString(1) + "\t" + resultset.getString(2));

           connection.close();



    }}

    public User user;
    private User addUserToDatabase(String name, String email, String phone, String address, String password) throws Exception {
        User user = null;

        Connection connection = DriverManager.getConnection("jdbc:ucanaccess:/Databaseyesyes.accdb");

        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery("Select * from User");
        while (resultset.next())
            System.out.println(resultset.getString(1) + "\t" + resultset.getString(2));

        connection.close();



        try{
            Connection conn = DriverManager.getConnection(String.valueOf(con));

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO `User`(`fname`,`lname`, `email`, `password` , `phone`, `address`) VALUES (?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, address);
            preparedStatement.setString(4, password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = fname;
                user.name = lname;
                user.email = email;
                user.phone = phone;
                user.address = address;
                user.password = password;
            }

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }