public class EdadMaxima {
    
    public static void main(String[] args) {
        // Primer arreglo de edades
        int[] edades1 = {23, 45, 67, 34, 25, 19};
        
        // Segundo arreglo de edades
        int[] edades2 = {30, 40, 56, 62, 21};
        
        // Llamamos al método para obtener el mayor valor
        int edadMaxima = obtenerEdadMaxima(edades1, edades2);
        
        // Mostramos el resultado
        System.out.println("La edad máxima es: " + edadMaxima);
    }
    
    // Método que recibe dos arreglos y devuelve el mayor valor
    public static int obtenerEdadMaxima(int[] arr1, int[] arr2) {
        int max = arr1[0];  // Inicializamos el valor máximo con el primer valor del primer arreglo
        
        // Recorremos el primer arreglo para encontrar el mayor valor
        for (int i = 1; i < arr1.length; i++) {
            if (arr1[i] > max) {
                max = arr1[i];
            }
        }
        
        // Recorremos el segundo arreglo para encontrar el mayor valor
        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] > max) {
                max = arr2[i];
            }
        }
        
        // Retornamos el mayor valor encontrado
        return max;
    }
}
