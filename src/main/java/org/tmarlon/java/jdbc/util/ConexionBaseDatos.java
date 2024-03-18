package org.tmarlon.java.jdbc.util;

import java.sql.*;

public class ConexionBaseDatos {

    private static   String url = "jdbc:mysql://localhost/java_curso?serverTimezone=UTC";
    private static  String username = "root";
    private static  String password = "";

    private static Connection connection;

    public static Connection getInstance( ) throws SQLException {

        if (connection==null){
            connection = DriverManager.getConnection(url , username, password);
        }

        return connection;
    }








}
