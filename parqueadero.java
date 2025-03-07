import java.util.Date;

public class parqueadero {

    //atributos de clase
    private double[] puestosBajoCilindraje = new moto[20];
    private double[] puestosAltoCilindraje = new Moto[10];
    private String[] horaEntradaBajoCilindraje = new Date[20];
    private String[] horaEntradaAltoCilindraje = new Date[10];
//  constructor de la clase
    public  registrarMoto(moto moto) {
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
        long minutos = (horaSalida.getTime() - horaEntrada.getTime()) / (60 * 1000);
        return minutos <= 30 ? 0 : (minutos <= 60 ? 800 : 2000);
    }

    public double retirarMoto(String placa) {
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
