package org.tmarlon.java.jdbc;

import org.tmarlon.java.jdbc.model.Producto;
import org.tmarlon.java.jdbc.repository.ProductoRepositoryImpl;
import org.tmarlon.java.jdbc.repository.Repository;
import org.tmarlon.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;

public class EjemploJdbc {

    public static void main(String[] args) {


        try ( Connection conn = ConexionBaseDatos.getInstance() ) {

            Repository<Producto> repository = new ProductoRepositoryImpl();
            repository.findAll().forEach(System.out::println);

            System.out.println("Buscando por id :");
            System.out.println(repository.finById(2L));


        } catch (SQLException e) {

            System.out.println("error : "+e);

        }










    }
}
