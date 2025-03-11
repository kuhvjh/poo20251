import java.util.Date;

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

public class Moto {
    private Moto[] puestosBajoCilindraje;
    private Moto[] puestosAltoCilindraje;
    private Date[] horaEntradaBajoCilindraje;
    private Date[] horaEntradaAltoCilindraje;

    public Moto() {
        puestosBajoCilindraje = new Moto[20];
        puestosAltoCilindraje = new Moto[10];
        horaEntradaBajoCilindraje = new Date[20];
        horaEntradaAltoCilindraje = new Date[10];
    }

    public boolean registrarMoto(Moto moto) {
        if (moto == null) {
            System.out.println("Error: La moto es nula.");
            return false;
        }
        
        Moto[] puestos = moto.esBajoCilindraje() ? puestosBajoCilindraje : puestosAltoCilindraje;
        Date[] horasEntrada = moto.esBajoCilindraje() ? horaEntradaBajoCilindraje : horaEntradaAltoCilindraje;

        for (int i = 0; i < puestos.length; i++) {
            if (puestos[i] == null) {
                puestos[i] = moto;
                horasEntrada[i] = new Date();
                System.out.println("Moto registrada en el puesto " + (i + 1) + " (" + (moto.esBajoCilindraje() ? "Bajo" : "Alto") + " cilindraje)");
                return true;
            }
        }
        System.out.println("No hay puestos disponibles para motos de " + (moto.esBajoCilindraje() ? "bajo" : "alto") + " cilindraje.");
        return false;
    }

    private double calcularCosto(Date horaEntrada, Date horaSalida) {
        if (horaEntrada == null || horaSalida == null) {
            System.out.println("Error: Fechas no válidas.");
            return 0;
        }
        
        long minutos = (horaSalida.getTime() - horaEntrada.getTime()) / (60 * 1000);
        if (minutos <= 30) return 0;
        if (minutos <= 60) return 800;
        return 2000;
    }

    public double retirarMoto(String placa) {
        if (placa == null || placa.isEmpty()) {
            System.out.println("Error: Placa no válida.");
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
}
