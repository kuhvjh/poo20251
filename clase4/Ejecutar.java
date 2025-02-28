public class Ejecutar {
    public static void main (String []args){

        //creacion de los objetos de la clase vehiculo

        //Formula1

        Vehiculo objVehiculo1= new Vehiculo ("toyota", "blanca",  4500.0,  "ts3456we", 2.3,"150hp");

        //forma2
        Vehiculo objVehiculo2;
        objVehiculo2= new Vehiculo("ferrari", "rojo",5000.0, "SER567Y", 1.8, "450HP");

        //mostrar la marca del objeto vehiculo
        System.out.println(objVehiculo1.getMarca());

        //se va a modificar la marca del vehiculo del objeto objVehiculo1
        objVehiculo1.setMarca("chevrlet");

        //mostrar todo el objeto
        System.out.println(objVehiculo1.toString());
        System.out.println(objVehiculo2);

    }

    
}
