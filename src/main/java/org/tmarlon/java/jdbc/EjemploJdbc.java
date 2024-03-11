package org.tmarlon.java.jdbc;

import java.sql.*;

public class EjemploJdbc {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/java_curso?serverTimezone=UTC";
        String usernmae = "root";
        String password = "";

        try (
                //ya tenemos la coenexion
                Connection conn = DriverManager.getConnection(url, usernmae, password);
                //creamos una sentencia
                Statement stmt = conn.createStatement();
                //contiene los registros despues de la sentencia
                ResultSet resultado = stmt.executeQuery("select * from productos")

        ) {
            //extrayendo con el ciclo while
            while (resultado.next()) {
                System.out.print(resultado.getInt("id"));
                System.out.print(" | ");
                System.out.print(resultado.getString("nombre"));
                System.out.print(" | ");
                System.out.print(resultado.getInt("precio"));
                System.out.print(" | ");
                System.out.println(resultado.getDate("fecha_registro"));

            }

        } catch (SQLException e) {

            System.out.println("error : "+e);

        }










    }
}
