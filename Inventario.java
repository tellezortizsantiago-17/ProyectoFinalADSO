import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Inventario {
    private List<Producto> productos;
    private List<Venta> historialVentas = new ArrayList<>();

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
    } 

    // T-10: Realizar venta
    public void realizarVenta(String idVenta, String codigoProducto, int cantidad) {
        for (Producto prod : productos) {
            if (prod.getCodigo().equals(codigoProducto)) {
                try {
                    Venta nuevaVenta = new Venta(idVenta, prod, cantidad);
                    prod.reducirStock(cantidad);
                    historialVentas.add(nuevaVenta);
                    System.out.println("Venta exitosa. Total a pagar: $" + nuevaVenta.getTotalPagar());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error en la venta: " + e.getMessage());
                }
                return; 
            }
        }
        System.out.println("Error: Producto no encontrado en el inventario.");
    }
    
    // T-16: Informe financiero por categoría usando HashMap 
    public void generarInformeFinanciero() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados para generar el informe financiero.");
            return;
        }

        Map<String, Double> reporteFinanciero = new HashMap<>();

        for (Producto p : productos) {
            String categoria = p.getCategoria();
            double valorTotalProducto = p.getPrecio() * p.getStock();
            reporteFinanciero.put(categoria, reporteFinanciero.getOrDefault(categoria, 0.0) + valorTotalProducto);
        }

        System.out.println("=== INFORME FINANCIERO POR CATEGORÍA ===");
        for (Map.Entry<String, Double> entry : reporteFinanciero.entrySet()) {
            System.out.println("Categoría: " + entry.getKey() + " | Valor Total Acumulado: $" + entry.getValue());
        }   
    }

    // T-09: Actualizar precio
    public void actualizarPrecio(String codigo, double nuevoPrecio) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                p.setPrecio(nuevoPrecio);
                System.out.println("Precio actualizado correctamente a: $" + nuevoPrecio);
                return;
            }
        }
        System.out.println("Error: Producto no encontrado.");
    }

    // T-15: Mostrar categorías únicas con HashSet
    public void mostrarCategoriasUnicas() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos para mostrar categorías.");
            return;
        }
        Set<String> categoriasUnicas = new HashSet<>();
        for (Producto p : productos) {
            categoriasUnicas.add(p.getCategoria());
        }
        System.out.println("=== CATEGORÍAS DISPONIBLES ===");
        for (String cat : categoriasUnicas) {
            System.out.println("- " + cat);
        }
    }

    // T-14: Buscar por categoría
    public void buscarPorCategoria(String categoriaABuscar) {
        boolean encontrado = false;
        System.out.println("=== PRODUCTOS EN LA CATEGORÍA: " + categoriaABuscar.toUpperCase() + " ===");
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoriaABuscar)) {
                System.out.println(p.toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron productos en esa categoría.");
        }
    }
}