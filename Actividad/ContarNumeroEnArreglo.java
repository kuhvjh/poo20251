import java.util.Scanner;

public class ContarNumeroEnArreglo {

    public static void main(String[] args) {
        // Crear el objeto Scanner para leer datos desde la entrada
        Scanner scanner = new Scanner(System.in);

        // Definir un arreglo de ejemplo
        int[] arreglo = {1, 3, 2, 5, 2, 33, 2, 9, 2, 6};  

        // Solicitar el número a buscar
        System.out.print("Introduce el número a buscar: ");
        int numeroBuscado = scanner.nextInt();

        // Llamar al método para contar las apariciones del número en el arreglo
        int cantidad = contarNumeroEnArreglo(arreglo, numeroBuscado);

        // Imprimir el resultado
        System.out.println("El número " + numeroBuscado + " aparece " + cantidad + " veces en el arreglo.");
    }

    // Método para contar las veces que un número aparece en el arreglo
    public static int contarNumeroEnArreglo(int[] arreglo, int numeroBuscado) {
        int contador = 0;  // Inicializamos un contador

        // Recorrer el arreglo y contar las apariciones del número
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == numeroBuscado) {
                contador++;
            }
        }

        return contador;  // Devolver el total de apariciones
    }
}
