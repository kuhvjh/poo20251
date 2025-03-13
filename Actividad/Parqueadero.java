import java.util.Scanner;

public class Parqueadero {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Arreglos para controlar la disponibilidad de puestos
        boolean[] bajosCC = new boolean[20]; // 20 puestos para las motos de baja cc (Falso es = libre)
        boolean[] altosCC = new boolean[10]; // 10 puestos para las motos de alto cc
        String[] horasEntrada = new String[30]; // Almacena la hora en la que entra (índices 0-19 bajos)

        // Menú interactivo
        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Registra tu moto");
            System.out.println("2. Valor a pagar");
            System.out.println("3. Salir");
            System.out.print("Elige: ");
            int opcion = scanner.nextInt();

            // Registrar moto
            if (opcion == 1) {
                int tipo;
                do {
                    System.out.print("Tipo (1=Bajo CC / 2=Alto CC): ");
                    tipo = scanner.nextInt();
                    if (tipo < 1 || tipo > 2) {
                        System.out.println("Error: Opcion no valida");
                    }
                } while (tipo < 1 || tipo > 2);

                // Mostrar puestos disponibles
                if (tipo == 1) {
                    System.out.println("Puestos Bajos CC [D=Libre, O=Ocupado]: ");
                    for (int i = 0; i < 20; i++) {
                        System.out.print((i + 1) + (bajosCC[i] ? "O " : "D "));
                    }
                } else {
                    System.out.println("Puestos Altos CC [D=Libre, O=Ocupado]");
                    for (int i = 0; i < 10; i++) {
                        System.out.print((i + 1) + (altosCC[i] ? "O " : "D "));
                    }
                }

                System.out.print("Elige el puesto que desees: ");
                int puesto = scanner.nextInt();

                // Generar una hora aleatoria entre las 07:00 y 01:00 AM
                int horaEntradaMin = 420 + (int) (Math.random() * 1081); // Rango de 420 a 1500 (25:00)
                String horaEntrada = String.format("%02d:%02d",
                        horaEntradaMin / 60,
                        horaEntradaMin % 60);
                System.out.println("Hora entrada generada: " + horaEntrada);

                // Guardar registro
                boolean exito = false;
                if (tipo == 1 && puesto <= 20 && !bajosCC[puesto - 1]) {
                    bajosCC[puesto - 1] = true;
                    horasEntrada[puesto - 1] = horaEntrada;
                    exito = true;
                } else if (tipo == 2 && puesto <= 10 && !altosCC[puesto - 1]) {
                    altosCC[puesto - 1] = true;
                    horasEntrada[20 + (puesto - 1)] = horaEntrada;
                    exito = true;
                }

                System.out.println(exito ? "Éxito" : "Error: Este puesto no existe");

            // Valor a pagar
            } else if (opcion == 2) {

                System.out.print("Tipo (1=BajoCC / 2=AltoCC): ");
                int tipo = scanner.nextInt();

                // Validar tipo de moto
                if (tipo != 1 && tipo != 2) {
                    System.out.println("Error: Opción no valida.");
                    continue;
                }

                System.out.print("Número de puesto: ");
                int puesto = scanner.nextInt();

                // Valor a pagar si el puesto existe o no
                if ((tipo == 1 && (puesto < 1 || puesto > 20))) {
                    System.out.println("Error: Puesto no válido para motos de baja CC.");
                    continue; // Volver al menú principal
                } else if (tipo == 2 && (puesto < 1 || puesto > 10)) {
                    System.out.println("Error: Puesto no válido para motos de alta CC.");
                    continue;
                }

                // Obtener hora almacenada (Entrada)
                int index = (tipo == 1) ? puesto - 1 : 20 + (puesto - 1);
                String entrada = horasEntrada[index];
                if (entrada == null) {
                    System.out.println("Puesto no ocupado");
                    continue;
                }

                // Generar la hora de salida aleatoria, después de la entrada
                int entradaMin = convertirHoraAMinutos(entrada);
                int salidaMin = entradaMin + 1 + (int) (Math.random() * (1500 - entradaMin)); // Hasta las 25:00
                if (salidaMin > 1500) salidaMin = 1500;
                String salida = String.format("%02d:%02d", salidaMin / 60, salidaMin % 60);
                System.out.println("Hora salida generada: " + salida);

                // Calcular tarifa
                int duracion = salidaMin - entradaMin;
                int pago = (duracion > 60) ? 2000 : (duracion >
