public class SepararParesImpares {

    public static void main(String[] args) {
        // Arreglo de 10 elementos (pueden ser números al azar)
        int[] arregloA = {23, 4, 56, 12, 33, 45, 67, 78, 90, 21};
        
        // Arreglos para almacenar los números pares e impares
        int[] pares = new int[arregloA.length];  // Suponemos que hay hasta 10 pares
        int[] impares = new int[arregloA.length]; // Suponemos que hay hasta 10 impares
        
        // Variables para contar los pares e impares
        int contadorPares = 0;
        int contadorImpares = 0;
        
        // Recorrer el arreglo A para separar pares e impares
        for (int i = 0; i < arregloA.length; i++) {
            if (arregloA[i] % 2 == 0) {  // Si el número es par
                pares[contadorPares] = arregloA[i];
                contadorPares++;
            } else {  // Si el número es impar
                impares[contadorImpares] = arregloA[i];
                contadorImpares++;
            }
        }
        
        // Imprimir los resultados
        System.out.print("Números Pares: ");
        for (int i = 0; i < contadorPares; i++) {
            System.out.print(pares[i] + " ");
        }
        System.out.println();
        
        System.out.print("Números Impares: ");
        for (int i = 0; i < contadorImpares; i++) {
            System.out.print(impares[i] + " ");
        }
        System.out.println();
    }
}
