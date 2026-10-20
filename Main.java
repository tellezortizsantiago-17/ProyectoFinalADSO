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
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

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
                    miInventario.mostrarInventario();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        
        scanner.close();
    }
}