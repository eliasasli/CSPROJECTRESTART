package com.company;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {


    public static void connection(String baseTableName, String MoveCodeTofind) {
        int WantedMoveID = 0;


        String DatabaseLocation = System.getProperty("use.dir") + "\\NEA_Hexapawn.accdb";
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess\\C:\\Users\\EA210847\\IdeaProjects\\CSPROJECTRESTART\\Databaseyesyes.accdb");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "Select * from Database" + baseTableName + ";";
            ResultSet rs = statement.executeQuery(String.valueOf(sql));
            while (rs.next()) {
                String MoveCode = rs.getString("MoveCode");
                if (MoveCode.equals(MoveCodeTofind)) {
                    WantedMoveID = rs.getInt("MoveID");
                }
            }
        } catch (Exception e) {
            dispose();

        }
    }

    private static void dispose() {

    }
    }
