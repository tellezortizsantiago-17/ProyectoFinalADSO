import java.util.Date;

public class Venta {
    private String idVenta;
    private Producto producto;
    private int cantidadComprada;
    private double totalPagar;
    private Date fecha;

    public Venta(String idVenta, Producto producto, int cantidadComprada) {
        // Aquí va la lógica vital de tu tarea:
        if (cantidadComprada > producto.getStock()) {
            throw new IllegalArgumentException("No hay suficiente stock. Stock actual: " + producto.getStock());
        }
        if (cantidadComprada <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }
        
        this.idVenta = idVenta;
        this.producto = producto;
        this.cantidadComprada = cantidadComprada;
        this.totalPagar = producto.getPrecio() * cantidadComprada;
        this.fecha = new Date();
    }
public String getIdVenta() { return idVenta; }
    public Producto getProducto() { return producto; }
    public int getCantidadComprada() { return cantidadComprada; }
    public double getTotalPagar() { return totalPagar; }
    public Date getFecha() { return fecha; }
   
}