import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Billetera miBilletera = new Billetera("Laura", 0.0); // Saldo inicial 0
        int opcion = -1;

        do {
            System.out.println("\n================================");
            System.out.println("       MI BILLETERA JAVA");
            System.out.println("================================");
            System.out.println("Titular: " + miBilletera.getTitular());
            System.out.println("Saldo disponible: $" + miBilletera.getSaldo());
            System.out.println("\n1. Registrar ingreso");
            System.out.println("2. Registrar gasto");
            System.out.println("3. Consultar saldo");
            System.out.println("4. Mostrar movimientos");
            System.out.println("5. Consultar movimientos por tipo");
            System.out.println("6. Consultar categorías utilizadas");
            System.out.println("7. Resumen financiero");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1:
                        System.out.print("Categoría: ");
                        String catIngreso = scanner.nextLine().trim();
                        System.out.print("Valor: ");
                        double valIngreso = Double.parseDouble(scanner.nextLine().trim());
                        if (miBilletera.registrarIngreso(valIngreso, catIngreso)) {
                            System.out.println("Ingreso registrado.");
                        } else {
                            System.out.println("Error: Ingreso rechazado (valor inválido o categoría vacía).");
                        }
                        break;
                    case 2:
                        System.out.print("Categoría: ");
                        String catGasto = scanner.nextLine().trim();
                        System.out.print("Valor: ");
                        double valGasto = Double.parseDouble(scanner.nextLine().trim());
                        if (miBilletera.registrarGasto(valGasto, catGasto)) {
                            System.out.println("Gasto registrado.");
                        } else {
                            System.out.println("Error: Gasto rechazado (saldo insuficiente o datos inválidos).");
                        }
                        break;
                    case 3:
                        System.out.println("Saldo actual: $" + miBilletera.getSaldo());
                        break;
                    case 4:
                        System.out.println("================================\n           HISTORIAL\n================================");
                        List<Movimiento> historial = miBilletera.getMovimientos();
                        if (historial.isEmpty()) {
                            System.out.println("El historial está vacío.");
                        } else {
                            for (int i = 0; i < historial.size(); i++) {
                                Movimiento m = historial.get(i);
                                System.out.println((i + 1) + ". " + m.getTipo() + " | " + m.getCategoria() + " | $" + m.getValor());
                            }
                        }
                        break;
                    case 5:
                        System.out.print("Escribe el tipo (INGRESO o GASTO): ");
                        String tipoBusqueda = scanner.nextLine().trim().toUpperCase();
                        List<Movimiento> filtrados = miBilletera.buscarPorTipo(tipoBusqueda);
                        if (filtrados.isEmpty()) {
                            System.out.println("No se encontraron movimientos de tipo " + tipoBusqueda);
                        } else {
                            for (Movimiento m : filtrados) {
                                System.out.println(m.getTipo() + " | " + m.getCategoria() + " | $" + m.getValor());
                            }
                        }
                        break;
                    case 6:
                        Set<String> categorias = miBilletera.categoriasUtilizadas();
                        if (categorias.isEmpty()) {
                            System.out.println("Aún no hay categorías registradas.");
                        } else {
                            System.out.println("Categorías utilizadas:");
                            for (String c : categorias) {
                                System.out.println("- " + c);
                            }
                        }
                        break;
                    case 7:
                        System.out.println("================================\n       RESUMEN FINANCIERO\n================================");
                        System.out.println("Titular: " + miBilletera.getTitular());
                        System.out.println("Saldo: $" + miBilletera.getSaldo());
                        System.out.println("Ingresos realizados: " + miBilletera.getCantidadIngresos());
                        System.out.println("Total ingresado: $" + miBilletera.getTotalIngresos());
                        System.out.println("Gastos realizados: " + miBilletera.getCantidadGastos());
                        System.out.println("Total gastado: $" + miBilletera.getTotalGastos());
                        System.out.println("\nGastos por categoría:");
                        Map<String, Double> reporteGastos = miBilletera.gastosPorCategoria();
                        if (reporteGastos.isEmpty()) {
                            System.out.println("No hay gastos registrados.");
                        } else {
                            for (Map.Entry<String, Double> entrada : reporteGastos.entrySet()) {
                                System.out.println(entrada.getKey() + ": $" + entrada.getValue());
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción desconocida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un valor numérico válido.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}