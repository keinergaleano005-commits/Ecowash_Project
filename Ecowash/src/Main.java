import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // URL de la base de datos y credenciales
        String url = "jdbc:mysql://localhost:3306/ecowash_db"; // Cambia esto si el nombre de la base de datos es diferente
        String usuario = "root"; // Cambia esto si el usuario es diferente
        String contrasena = "nueva_contraseña"; // Cambia esto por la contraseña de tu base de datos

        // Conexión a la base de datos
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("Conexión exitosa a la base de datos.");

            // Crear una consulta SQL
            String query = "SELECT * FROM productos";
            try (PreparedStatement pst = con.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {

                // Procesar los resultados
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    double precio = rs.getDouble("precio");
                    int stock = rs.getInt("stock");

                    System.out.println("Producto ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Descripción: " + descripcion);
                    System.out.println("Precio: " + precio);
                    System.out.println("Stock: " + stock);
                    System.out.println("----------------------------------------");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}

