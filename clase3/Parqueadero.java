import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

class Moto {
    private String placa;
    private boolean bajoCilindraje;

    public Moto(String placa, boolean bajoCilindraje) {
        this.placa = placa;
        this.bajoCilindraje = bajoCilindraje;
    }

    public String getPlaca() {
        return placa;
    }

    public boolean esBajoCilindraje() {
        return bajoCilindraje;
    }
}

public class Parqueadero {
    private Moto[] puestosBajoCilindraje;
    private Moto[] puestosAltoCilindraje;
    private Date[] horaEntradaBajoCilindraje;
    private Date[] horaEntradaAltoCilindraje;

    public Parqueadero() {
        puestosBajoCilindraje = new Moto[20];
        puestosAltoCilindraje = new Moto[10];
        horaEntradaBajoCilindraje = new Date[20];
        horaEntradaAltoCilindraje = new Date[10];
    }

    private boolean dentroDeHorario() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
        Calendar now = Calendar.getInstance();
        int hora = now.get(Calendar.HOUR_OF_DAY);
        int minuto = now.get(Calendar.MINUTE);
        System.out.println("Depuración: Hora actual " + hora + ":" + minuto);
        return (hora >= 7 && hora < 24) || (hora >= 0 && hora < 3);
    }

    public boolean registrarMoto(Moto moto, int puesto) {
        if (!dentroDeHorario()) {
            System.out.println("Error: El parqueadero solo opera entre 07:00 y 03:00 HRS.");
            return false;
        }

        Moto[] puestos = moto.esBajoCilindraje() ? puestosBajoCilindraje : puestosAltoCilindraje;
        Date[] horasEntrada = moto.esBajoCilindraje() ? horaEntradaBajoCilindraje : horaEntradaAltoCilindraje;

        if (puesto < 1 || puesto > puestos.length || puestos[puesto - 1] != null) {
            System.out.println("Error: Puesto no válido o ya ocupado.");
            return false;
        }

        puestos[puesto - 1] = moto;
        horasEntrada[puesto - 1] = new Date();
        System.out.println("Moto registrada en el puesto " + puesto + " (" + (moto.esBajoCilindraje() ? "Bajo" : "Alto") + " cilindraje)");
        return true;
    }

    private double calcularCosto(Date horaEntrada, Date horaSalida) {
        long minutos = (horaSalida.getTime() - horaEntrada.getTime()) / (60 * 1000);
        if (minutos <= 30) return 0;
        if (minutos <= 60) return 800;
        return 2000;
    }

    public double retirarMoto(String placa) {
        if (!dentroDeHorario()) {
            System.out.println("Error: El parqueadero solo opera entre 07:00 y 03:00 HRS.");
            return 0;
        }

        Moto[][] puestos = {puestosBajoCilindraje, puestosAltoCilindraje};
        Date[][] horasEntrada = {horaEntradaBajoCilindraje, horaEntradaAltoCilindraje};

        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                if (puestos[i][j] != null && puestos[i][j].getPlaca().equals(placa)) {
                    double costo = calcularCosto(horasEntrada[i][j], new Date());
                    puestos[i][j] = null;
                    horasEntrada[i][j] = null;
                    System.out.println("Moto retirada. Costo: $" + costo);
                    return costo;
                }
            }
        }
        System.out.println("No se encontró ninguna moto con la placa " + placa);
        return 0;
    }

    public void mostrarPuestosDisponibles() {
        String[] tipos = {"Bajo cilindraje", "Alto cilindraje"};
        Moto[][] puestos = {puestosBajoCilindraje, puestosAltoCilindraje};

        for (int i = 0; i < tipos.length; i++) {
            System.out.println("Puestos para motos de " + tipos[i] + ":");
            for (int j = 0; j < puestos[i].length; j++) {
                System.out.println("Puesto " + (j + 1) + ": " + (puestos[i][j] == null ? "Disponible" : "Ocupado"));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parqueadero parqueadero = new Parqueadero();

        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Registrar moto");
            System.out.println("2. Retirar moto");
            System.out.println("3. Mostrar puestos disponibles");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la placa de la moto: ");
                    String placa = scanner.nextLine();
                    System.out.print("¿Es de bajo cilindraje? (true/false): ");
                    boolean bajoCilindraje = scanner.nextBoolean();
                    scanner.nextLine();
                    
                    parqueadero.mostrarPuestosDisponibles();
                    System.out.print("Seleccione un puesto disponible: ");
                    int puesto = scanner.nextInt();
                    scanner.nextLine();
                    
                    parqueadero.registrarMoto(new Moto(placa, bajoCilindraje), puesto);
                    break;
                case 2:
                    System.out.print("Ingrese la placa de la moto a retirar: ");
                    String placaRetirar = scanner.nextLine();
                    parqueadero.retirarMoto(placaRetirar);
                    break;
                case 3:
                    parqueadero.mostrarPuestosDisponibles();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
