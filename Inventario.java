import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    // T-05: Registrar producto (Tellez)
    public boolean registrarProducto(String codigo, String nombre, String categoria, double precio, int stock) {
        try {
            productos.add(new Producto(codigo, nombre, categoria, precio, stock));
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }