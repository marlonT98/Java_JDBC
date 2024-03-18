package org.tmarlon.java.jdbc.repository;

import org.tmarlon.java.jdbc.model.Producto;
import org.tmarlon.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements Repository<Producto> {

    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }

    @Override
    public List<Producto> findAll() {

        List<Producto> productos = new ArrayList<>();

        try (
                Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("select * from productos")
        ) {

            while (rs.next()) {

                Producto p = crearProducto(rs);
                productos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }


    @Override
    public Producto finById(Long id) {

        Producto producto = null;
        try (PreparedStatement stmt = getConnection()
                .prepareStatement("select * from productos where id = ?")) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = crearProducto(rs);
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return producto;
    }

    @Override
    public void save(Producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            //seria un update
            sql = "update producto set nombre=?, precio=? where id= ?";
        } else {
            //insert

            sql = "insert into producto (nombre, precio , fecha_registro) values(?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());

            if (producto.getId() != null && producto.getId() > 0) {

                stmt.setLong(3, producto.getId());

            } else {
                stmt.setDate(3, new Date(producto.getFechaRegistro().getTime()));
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(Long id) {


        try (PreparedStatement stmt = getConnection().prepareStatement("delete from productos where id =?")) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Producto crearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha_registro"));
        return p;
    }


}
