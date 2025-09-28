package com.ecowash.dao;

import com.ecowash.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection con;

    // Constructor que recibe la conexión
    public ProductoDAO(Connection con) {
        this.con = con;
    }

    // Insertar un producto en la base de datos
    public void insertProducto(Producto producto) {
        String query = "INSERT INTO productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getStock());
            pst.executeUpdate();

            // Obtener el ID generado automáticamente
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    producto.setId(rs.getInt(1));  // Establecer el ID del producto
                    System.out.println("Producto insertado correctamente con ID: " + producto.getId());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
        }
    }

    // Obtener todos los productos
    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";
        try (PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());
        }
        return productos;
    }

    // Actualizar un producto
    public void updateProducto(Producto producto) {
        String query = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, stock = ? WHERE id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getStock());
            pst.setInt(5, producto.getId());
            pst.executeUpdate();
            System.out.println("Producto actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // Eliminar un producto
    public void deleteProducto(int id) {
        String query = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Producto eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }
}


