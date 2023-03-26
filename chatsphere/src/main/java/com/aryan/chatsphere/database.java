package com.aryan.chatsphere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class database {
    private static Connection connection = null;
    private static boolean connect = false;
    private static Statement stmt = null;

    public static boolean connect() {

        boolean temp = false;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://10.0.0.118:5432/postgres", "root", "root");
            connection.setAutoCommit(false);
            if (connection != null) {
                temp = true;

            } else {
                temp = false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        connect = true;
        return temp;
    }

    private static void disconnect() throws SQLException {
        connection.close();
        connect = false;
    }

    public static boolean commit(String name, String time, String date, String message) throws SQLException {

        boolean done = false;
        database.connect();
        if (connect == true) {
            stmt = connection.createStatement();
            String sql = "INSERT INTO chatdata (NAME,TIME,DATE,MESSAGE)" + "VALUES('" + name
                    + "','" + time + "','" + date + "','" + message + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
            database.disconnect();
        done = true;

        } else {
            done = false;
        }
        return done;
    }

    public static boolean delete() throws SQLException {
        connect();
        if (connect == true) {
            stmt = connection.createStatement();
            String sql = "DELETE FROM chatdata";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
            database.disconnect();
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<chat> get() throws SQLException {
        ArrayList<chat> chat1 = new ArrayList<chat>();
        String time = "";
        String name = "";
        String date = "";
        String message = "";
        database.connect();
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM chatdata");
        while (rs.next()) {
            name = rs.getString("name");
            time = rs.getString("time");
            date = rs.getString("date");
            message = rs.getString("message");
            chat1.add(new chat(name, time, date, message));
        }
        disconnect();

        return chat1;
    }
}