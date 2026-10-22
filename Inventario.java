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

    public void realizarVenta(String idVenta, String codigoProducto, int cantidad) {
        // Recorremos la lista para buscar el producto
        for (Producto prod : productos) {
            if (prod.getCodigo().equals(codigoProducto)) {
                try {
                    // Intenta crear la venta
                    Venta nuevaVenta = new Venta(idVenta, prod, cantidad);

                    // Si pasa las validaciones sin error, descuenta el stock y guarda la venta
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
    }

    // T-09: Programar método para actualizar el precio (Zubieta)
    public void actualizarPrecio(String codigoProducto, double nuevoPrecio) {
        for (Producto prod : productos) {
            if (prod.getCodigo().equals(codigoProducto)) {
                try {
                    prod.setPrecio(nuevoPrecio); // Modificando el estado del objeto
                    System.out.println("Éxito: El precio de '" + prod.getNombre() + "' ha sido actualizado a $" + nuevoPrecio);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error al actualizar: " + e.getMessage());
                }
                return; 
            }
        }
        // NUEVO MENSAJE SI EL CÓDIGO NO EXISTE
        System.out.println("Error: Código no agregado. No se pudo actualizar el precio.");
    }    

    // T-14: Implementar método de búsqueda de productos filtrando por categoría (Zubieta)
    public void buscarPorCategoria(String categoriaBuscada) {
        boolean encontrado = false;
        System.out.println("\n=== PRODUCTOS EN LA CATEGORÍA: " + categoriaBuscada.toUpperCase() + " ===");
        
        for (Producto prod : productos) {
            if (prod.getCategoria().equalsIgnoreCase(categoriaBuscada)) {
                System.out.println(prod.toString());
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            System.out.println("No se encontraron productos en esta categoría.");
        }
    }

    // T-15: Implementar consulta de categorías únicas utilizando la colección HashSet (Zubieta)
    public void mostrarCategoriasUnicas() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados para extraer categorías.");
            return;
        }

        Set<String> categoriasUnicas = new HashSet<>();
        
        for (Producto prod : productos) {
            categoriasUnicas.add(prod.getCategoria());
        }

        System.out.println("\n=== CATEGORÍAS DISPONIBLES ===");
        for (String cat : categoriasUnicas) {
            System.out.println("- " + cat);
        }
    }
}