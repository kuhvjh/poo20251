public class Agenciainmuebles {

    public static void main(String[] args) {
        // Arreglo de alquileres mensuales (en cualquier moneda)
        double[] alquileres = {500, 600, 450, 700, 800};  // Ejemplo de alquileres mensuales de N viviendas
        
        // Arreglo de porcentajes de ganancia correspondientes a cada vivienda
        double[] porcentajesGanancia = {10, 12, 15, 8, 10};  // Ejemplo de porcentajes de ganancia
        
        // Llamamos al método para calcular las ganancias netas
        double[] gananciasNetas = calcularGananciasNetas(alquileres, porcentajesGanancia);
        
        // Imprimimos las ganancias netas
        for (int i = 0; i < gananciasNetas.length; i++) {
            System.out.println("Ganancia neta de la vivienda " + (i+1) + ": " + gananciasNetas[i]);
        }
    }

    // Método que recibe dos arreglos (alquileres y porcentajes de ganancia) y devuelve las ganancias netas
    public static double[] calcularGananciasNetas(double[] alquileres, double[] porcentajes) {
        double[] ganancias = new double[alquileres.length]; // Arreglo para guardar las ganancias netas
        
        // Calculamos la ganancia neta por cada vivienda
        for (int i = 0; i < alquileres.length; i++) {
            ganancias[i] = alquileres[i] * (porcentajes[i] / 100);
        }
        
        return ganancias; // Retornamos el arreglo con las ganancias netas
    }
}
