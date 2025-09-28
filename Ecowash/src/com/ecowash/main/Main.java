package com.ecowash.main;

import com.ecowash.dao.ProductoDAO;
import com.ecowash.model.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // URL de la base de datos y credenciales
        String url = "jdbc:mysql://localhost:3306/ecowash_db"; //
        String usuario = "root"; //
        String contrasena = "nueva_contraseña"; //

        // Conexión a la base de datos
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("Conexión exitosa a la base de datos.");

            // Crear el objeto ProductoDAO para interactuar con la base de datos
            ProductoDAO productoDAO = new ProductoDAO(con);  // Pasando la conexión al constructor

            // Crear productos para insertar
            Producto p1 = new Producto("Camiseta EcoWash", "Camiseta de algodón ecológico", 15.99, 50);
            Producto p2 = new Producto("Lavado Rápido", "Servicio de lavado rápido para vehículos", 9.99, 30);
            Producto p3 = new Producto("Detergente Eco", "Detergente ecológico para ropa", 4.5, 100);

            // Insertar los productos
            productoDAO.insertProducto(p1);
            productoDAO.insertProducto(p2);
            productoDAO.insertProducto(p3);

            // Obtener y mostrar todos los productos
            List<Producto> productos = productoDAO.getAllProductos();
            System.out.println("Lista de productos:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }

            // Actualizar un producto
            p1.setPrecio(16.99);
            productoDAO.updateProducto(p1);
            System.out.println("Producto actualizado: " + p1);

            // Eliminar un producto
            productoDAO.deleteProducto(p2.getId());
            System.out.println("Producto eliminado: " + p2);

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}











