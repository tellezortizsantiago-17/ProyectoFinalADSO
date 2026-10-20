public class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, String categoria, double precio, int stock) {
        if (precio <= 0) throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void actualizarPrecio(double nuevoPrecio) {
        if (nuevoPrecio > 0) this.precio = nuevoPrecio;
    }

    public void reducirStock(int cantidad) {
        if (cantidad > 0 && cantidad <= this.stock) {
            this.stock -= cantidad;
        }
    }
}