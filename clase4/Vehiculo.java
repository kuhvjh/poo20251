public class Vehiculo {

    //atributos de la clase
    private String marca;
    private String color;
    private double cilindraje;
    private String chasis;
    private double peso;
    private String potencia;

    /* 
     * constructor de la clase>- permite  inicializar el objeto
     *el constructor de la clase se reconoce porque tiene el mismo nombre de la clase 
     */
    public Vehiculo(String marca, String color, double cilindraje, String chasis, double peso, String potencia){
        this.marca=marca;
        this.color=color;
        this.cilindraje=cilindraje;
        this.chasis=chasis;
        this.peso=peso;
        this.potencia= potencia;
    }

    //metodos getter / setter 
    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca=marca;
    }

    public String toString(){
        return "Vehiculo{marca:"+ marca + "color:"+ color + "cilindraje:"+cilindraje+"chasis:"+ chasis+ "peso:"+ peso +"potencia:"+potencia+"}";
    }


    


    
}
