import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario miInventario = new Inventario();
        int opcion = -1;

        do {
            System.out.println("\n=== GESTOR DE INVENTARIO Y VENTAS ===");
            System.out.println("1. Registrar nuevo producto");
            System.out.println("2. Mostrar inventario completo");
            System.out.println("3. Actualizar precio de un producto");
            System.out.println("4. Mostrar categorías disponibles"); // Agregado para T-15
            System.out.println("5. Buscar productos por categoría"); // Agregado para T-14
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            // T-11: Implementar bloque try-catch (NumberFormatException) (Zubieta)
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1:
                        System.out.print("Código: "); String cod = scanner.nextLine();
                        System.out.print("Nombre: "); String nom = scanner.nextLine();
                        System.out.print("Categoría: "); String cat = scanner.nextLine();
                        System.out.print("Precio: "); double pre = Double.parseDouble(scanner.nextLine());
                        System.out.print("Stock inicial: "); int stk = Integer.parseInt(scanner.nextLine());
                        if(miInventario.registrarProducto(cod, nom, cat, pre, stk)) {
                            System.out.println("Producto guardado con éxito.");
                        }
                        break;
                    case 2:
                        miInventario.consultarProductos();
                        break;
                    case 3:
                        System.out.println("\n--- ACTUALIZAR PRECIO ---");
                        System.out.print("Ingresa el código del producto: ");
                        String codActualizar = scanner.nextLine();
                        System.out.print("Ingresa el nuevo precio: ");
                        double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                        
                        miInventario.actualizarPrecio(codActualizar, nuevoPrecio);
                        break;
                    case 4:
                        // Lógica para T-15 en el menú
                        miInventario.mostrarCategoriasUnicas();
                        break;
                    case 5:
                        // Lógica para T-14 en el menú
                        System.out.print("\nIngresa el nombre de la categoría a buscar: ");
                        String catBuscar = scanner.nextLine();
                        miInventario.buscarPorCategoria(catBuscar);
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                // Atrapa el error si el usuario escribe letras donde van números
                System.out.println("Error de entrada: Por favor ingresa únicamente valores numéricos. No se permiten letras.");
                opcion = -1; // Mantiene el ciclo corriendo
            }
        } while (opcion != 0);
        
        scanner.close();
    }
}