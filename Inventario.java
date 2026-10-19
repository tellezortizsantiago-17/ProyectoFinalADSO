import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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

    // T-06: Consultar y mostrar todos los productos registrados 
    public void consultarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados en el inventario.");
        } else {
            System.out.println("=== LISTA DE PRODUCTOS REGISTRADOS ===");
            for (Producto p : productos) {
                System.out.println(p.toString());
            }
        }
        // T-16: Informe financiero por categoría usando HashMap 
    public void generarInformeFinanciero() 
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados para generar el informe financiero.");
            return;
        }

        java.util.Map<String, Double> reporteFinanciero = new java.util.HashMap<>();

        for (Producto p : productos) {
            String categoria = p.getCategoria();
            double valorTotalProducto = p.getPrecio() * p.getStock();

            // Suma el valor al acumulador de la categoría correspondiente
            reporteFinanciero.put(categoria, reporteFinanciero.getOrDefault(categoria, 0.0) + valorTotalProducto);
        }

        System.out.println("=== INFORME FINANCIERO POR CATEGORÍA ===");
        for (java.util.Map.Entry<String, Double> entry : reporteFinanciero.entrySet()) {
            System.out.println("Categoría: " + entry.getKey() + " | Valor Total Acumulado: $" + entry.getValue());
        }