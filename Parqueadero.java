public class Parqueadero {

    // Arreglos para los puestos de motos
    private Moto[] puestosBajoCilindraje; // 20 puestos para motos de bajo cilindraje
    private Moto[] puestosAltoCilindraje; // 10 puestos para motos de alto cilindraje

    // Arreglos para registrar la hora de entrada de cada moto
    private Date[] horaEntradaBajoCilindraje;
    private Date[] horaEntradaAltoCilindraje;

    // Constructor
    public Parqueadero() {
        puestosBajoCilindraje = new Moto[20];
        puestosAltoCilindraje = new Moto[10];
        horaEntradaBajoCilindraje = new Date[20];
        horaEntradaAltoCilindraje = new Date[10];
    }

    // Método para registrar una moto en el parqueadero
    public boolean registrarMoto(Moto moto) {
        if (moto.esBajoCilindraje()) {
            for (int i = 0; i < puestosBajoCilindraje.length; i++) {
                if (puestosBajoCilindraje[i] == null) {
                    puestosBajoCilindraje[i] = moto;
                    horaEntradaBajoCilindraje[i] = new Date(); // Registrar hora de entrada
                    System.out.println("Moto de bajo cilindraje registrada en el puesto " + (i + 1));
                    return true;
                }
            }
            System.out.println("No hay puestos disponibles para motos de bajo cilindraje.");
        } else if (moto.esAltoCilindraje()) {
            for (int i = 0; i < puestosAltoCilindraje.length; i++) {
                if (puestosAltoCilindraje[i] == null) {
                    puestosAltoCilindraje[i] = moto;
                    horaEntradaAltoCilindraje[i] = new Date(); // Registrar hora de entrada
                    System.out.println("Moto de alto cilindraje registrada en el puesto " + (i + 1));
                    return true;
                }
            }
            System.out.println("No hay puestos disponibles para motos de alto cilindraje.");
        }
        return false;
    }

    // Método para calcular el costo de estacionamiento
    public double calcularCosto(Date horaEntrada, Date horaSalida) {
        long diferencia = horaSalida.getTime() - horaEntrada.getTime();
        long minutos = diferencia / (60 * 1000); // Convertir milisegundos a minutos

        if (minutos <= 30) {
            return 0; // Los primeros 30 minutos son gratis
        } else if (minutos <= 60) {
            return 800; // Entre 31 y 60 minutos cuesta $800
        } else {
            return 2000; // Más de 60 minutos cuesta $2000
        }
    }

    // Método para retirar una moto y calcular el pago
    public double retirarMoto(String placa) {
        // Buscar en los puestos de bajo cilindraje
        for (int i = 0; i < puestosBajoCilindraje.length; i++) {
            if (puestosBajoCilindraje[i] != null && puestosBajoCilindraje[i].getPlaca().equals(placa)) {
                double costo = calcularCosto(horaEntradaBajoCilindraje[i], new Date());
                puestosBajoCilindraje[i] = null; // Liberar el puesto
                horaEntradaBajoCilindraje[i] = null;
                System.out.println("Moto retirada. Costo: $" + costo);
                return costo;
            }
        }

        // Buscar en los puestos de alto cilindraje
        for (int i = 0; i < puestosAltoCilindraje.length; i++) {
            if (puestosAltoCilindraje[i] != null && puestosAltoCilindraje[i].getPlaca().equals(placa)) {
                double costo = calcularCosto(horaEntradaAltoCilindraje[i], new Date());
                puestosAltoCilindraje[i] = null; // Liberar el puesto
                horaEntradaAltoCilindraje[i] = null;
                System.out.println("Moto retirada. Costo: $" + costo);
                return costo;
            }
        }

        System.out.println("No se encontró ninguna moto con la placa " + placa);
        return 0;
    }

    // Método para mostrar los puestos disponibles
    public void mostrarPuestosDisponibles() {
        System.out.println("Puestos para motos de bajo cilindraje:");
        for (int i = 0; i < puestosBajoCilindraje.length; i++) {
            System.out.println("Puesto " + (i + 1) + ": " + (puestosBajoCilindraje[i] == null ? "Disponible" : "Ocupado"));
        }

        System.out.println("Puestos para motos de alto cilindraje:");
        for (int i = 0; i < puestosAltoCilindraje.length; i++) {
            System.out.println("Puesto " + (i + 1) + ": " + (puestosAltoCilindraje[i] == null ? "Disponible" : "Ocupado"));
        }
    }
}
public class Main {
    public static void main(String[] args) {
        // Crear 