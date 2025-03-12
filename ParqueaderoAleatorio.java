// Archivo: ParqueaderoAleatorio.java
import java.util.Scanner;

public class ParqueaderoAleatorio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean[] bajosCC = new boolean[20];  // Puestos para motos <400cc
        boolean[] altosCC = new boolean[10];  // Puestos para motos >=400cc
        String[] horasEntrada = new String[30]; // Almacena horas de entrada

        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Registrar moto");
            System.out.println("2. Cobrar");
            System.out.println("3. Salir");
            System.out.print("Elige: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("⚠ Error: Debes ingresar un número válido.");
                scanner.next();
                continue;
            }
            
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.print("Tipo (1=Bajo CC / 2=Alto CC): ");
                
                if (!scanner.hasNextInt()) {
                    System.out.println("⚠ Error: Debes ingresar un número válido.");
                    scanner.next();
                    continue;
                }

                int tipo = scanner.nextInt();

                // Mostrar puestos disponibles
                if (tipo == 1) {
                    System.out.println("Puestos Bajos CC [D=Libre, O=Ocupado]: ");
                    for (int i = 0; i < 20; i++) {
                        System.out.print((i + 1) + (bajosCC[i] ? "O " : "D "));
                    }
                } else if (tipo == 2) {
                    System.out.println("Puestos Altos CC [D=Libre, O=Ocupado]: ");
                    for (int i = 0; i < 10; i++) {
                        System.out.print((i + 1) + (altosCC[i] ? "O " : "D "));
                    }
                } else {
                    System.out.println("⚠ Error: Tipo de moto no válido.");
                    continue;
                }

                System.out.print("\nElige puesto: ");
                
                if (!scanner.hasNextInt()) {
                    System.out.println("⚠ Error: Debes ingresar un número válido.");
                    scanner.next();
                    continue;
                }

                int puesto = scanner.nextInt();

                // Generar hora de entrada aleatoria (07:00 - 22:00)
                int horaEntradaMin = 420 + (int) (Math.random() * 901); // 420 = 7*60, 1320 = 22*60
                String horaEntrada = String.format("%02d:%02d", horaEntradaMin / 60, horaEntradaMin % 60);
                System.out.println("Hora entrada generada: " + horaEntrada);

                // Guardar registro
                boolean exito = false;
                if (tipo == 1 && puesto > 0 && puesto <= 20 && !bajosCC[puesto - 1]) {
                    bajosCC[puesto - 1] = true;
                    horasEntrada[puesto - 1] = horaEntrada;
                    exito = true;
                } else if (tipo == 2 && puesto > 0 && puesto <= 10 && !altosCC[puesto - 1]) {
                    altosCC[puesto - 1] = true;
                    horasEntrada[20 + (puesto - 1)] = horaEntrada;
                    exito = true;
                } else {
                    System.out.println("⚠ Error: Puesto no válido o ya ocupado.");
                }

                System.out.println(exito ? "✅ ¡Moto registrada exitosamente!" : "❌ Error al registrar la moto.");

            } else if (opcion == 2) {
                System.out.print("Tipo (1=Bajo CC / 2=Alto CC): ");
                
                if (!scanner.hasNextInt()) {
                    System.out.println("⚠ Error: Debes ingresar un número válido.");
                    scanner.next();
                    continue;
                }

                int tipo = scanner.nextInt();
                System.out.print("Número de puesto: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("⚠ Error: Debes ingresar un número válido.");
                    scanner.next();
                    continue;
                }

                int puesto = scanner.nextInt();

                // Validación de índice
                if ((tipo == 1 && (puesto < 1 || puesto > 20)) || (tipo == 2 && (puesto < 1 || puesto > 10))) {
                    System.out.println("⚠ Error: Número de puesto fuera de rango.");
                    continue;
                }

                // Obtener hora de entrada almacenada
                int index = (tipo == 1) ? puesto - 1 : 20 + (puesto - 1);
                String entrada = horasEntrada[index];

                if (entrada == null) {
                    System.out.println("⚠ Puesto no ocupado.");
                    continue;
                }

                // Generar hora de salida aleatoria (después de la entrada)
                int entradaMin = convertirHoraAMinutos(entrada);
                int salidaMin = entradaMin + 1 + (int) (Math.random() * (1320 - entradaMin));
                if (salidaMin > 1320) salidaMin = 1320; // Máximo 22:00
                String salida = String.format("%02d:%02d", salidaMin / 60, salidaMin % 60);
                System.out.println("Hora salida generada: " + salida);

                // Calcular tarifa
                int duracion = salidaMin - entradaMin;
                int pago = (duracion > 60) ? 2000 : (duracion > 30) ? 800 : 0;
                System.out.println("💲 Total a pagar: $" + pago);

                // Liberar puesto
                if (tipo == 1) bajosCC[puesto - 1] = false;
                else altosCC[puesto - 1] = false;
                horasEntrada[index] = null;

            } else if (opcion == 3) {
                System.out.println("👋 ¡Hasta luego!");
                break;
            } else {
                System.out.println("⚠ Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    // Convierte "HH:mm" a minutos totales
    private static int convertirHoraAMinutos(String hora) {
        String[] partes = hora.split(":");
        return Integer.parseInt(partes[0]) * 60 + Integer.parseInt(partes[1]);
    }
}
