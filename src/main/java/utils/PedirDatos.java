package utils;

import excepciones.DatosException;
import excepciones.MensajesExcepcion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PedirDatos {
    static MensajesExcepcion validacion = new MensajesExcepcion();

    private static  Scanner s = new Scanner(System.in);

    public PedirDatos(){ }

    //Método común para todos los menús
    public static int pedirOpcion(String mensaje){
        String opcion = null;
        boolean validar = false;
        do {
            try {
                System.out.println(mensaje);
                System.out.print("-> Seleccione una opcion: ");
                opcion = s.nextLine();
                validacion.validarOpcion(opcion);
                validar = true;

            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return Integer.parseInt(opcion);
    }

    //Método que pide el email en el logging
    public static String pedirEmailLogging(){
        String email = null;
        boolean validar = false;
        do {
            try {
                System.out.print("Introduce el email: ");
                email = s.nextLine();
                validacion.validarEmail(email);
                validar = true;
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return email;
    }


    //Métodos comunes para usuario y propietario
    public static String pedirEmail(Datos datos){
        String email = null;
        boolean validar = false;
        do {
            try {
                System.out.print("Introduce el email: ");
                email = s.nextLine();
                validacion.validarEmail(email);
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
            if(pedirSiNo("Correo suministrado " + email + "\n" +
                    "Deseas utilizarlo (S/N)?: ")){ //Pedimos si quiere usar el correo electrónico
                if(datos.validarEmail(email)) validar = true; //Si existe otro correo electrónico igual nos dirá que dicho email ya está registrado
                else System.out.println("Correo electrónico registrado");
            }
        }while (!validar);
        return email;
    }

    public static boolean pedirSiNo(String mensaje){
        String op;
        while (true){
            System.out.print(mensaje);
            op = s.nextLine().toUpperCase();
            switch (op){
                case "S" -> {
                    return true;
                }
                case "N" -> {
                    return false;
                }
                default -> System.out.println("Opción errónea");
            }
        }
    }

    public static String pedirNombreUsuario(Datos datos){
        String nombreUsuario = null;
        boolean validar = false;
        do{
            try {
                System.out.print("Introduce su nombre de usuario: ");
                nombreUsuario = s.nextLine();
                validacion.validarNombreUsuario(nombreUsuario);
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
            if(datos.validarNombreUsuario(nombreUsuario)) validar = true;
            else System.out.println("Nombre de usuario ya registrado");
        }while (!validar);
        return nombreUsuario;
    }

    public static String pedirConstrasenia(){
        String contrasenia = null;
        boolean validar = false;
        do{
            try {
                System.out.print("Introduce la contraseña: ");
                contrasenia = s.nextLine();
                validacion.validarContrasenia(contrasenia);
                validar = true;
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return contrasenia;
    }

    public static String pedirNombre(){
        String nombre = null;
        boolean validar = false;
        do {
            try {
                System.out.print("Introduce el nombre: ");
                nombre = s.nextLine();
                validacion.validarNombre(nombre);
                validar = true;
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return nombre;
    }

    public static String pedirApellido(){
        String apellido = null;
        boolean validar = false;
        do {
            try {
                System.out.print("Introduce el apellido: ");
                apellido = s.nextLine();
                validacion.validarApellido(apellido);
                validar = true;
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return apellido;
    }
    public static int pedirNumTelefono(){
        String numeroTelefono = null;
        boolean validar = false;
        do{
            try {
                System.out.print("Introduce el teléfono móvil: ");
                numeroTelefono = s.nextLine();
                validacion.validarNumeroTelefono(numeroTelefono);
                validar = true;
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return Integer.parseInt(numeroTelefono);
    }
    public static boolean pedirConfirmacion(String emailDestinatario, Datos datos){ //Preguntar a Carlos si cuando no hay internet y no se puede mandar no se crea el usuario/propietario
        String codigoConfirmacion = datos.crearNumeroAleatorio();
        String mensaje = Mensajes.contenidoEmailConfirmacion(codigoConfirmacion);
        System.out.println("Estamos enviando un correo hacia su correo electrónico. Por favor espere.");
        if(!Email.enviarConfirmacion(emailDestinatario,"Confirmación Fernanbnb", mensaje)) return false;
        else {
            System.out.print("Se ha enviado un código al correo designado.");
            while (true){
                //System.out.println("Por favor introduce el código enviado (Para omitir introduzca -1):");
                //String codigo = s.nextLine();
                int codigo = pedirSoloNumero("Por favor introduce el código enviado (Para omitir introduzca 0):");
                String codigoCadena = String.valueOf(codigo);
                if(!codigoConfirmacion.equals(codigoCadena)){
                    if(codigoCadena.equals("0")) return false;
                    else System.out.println("Error al introducir el código");
                }else {
                    System.out.println("Se ha confirmado el código");
                    return true;
                }
            }
        }
    }

    //Método específico del usuario
    public static int pedirIdReserva(){
        String idReserva = null;
        boolean validar = false;
        do {
            try {
                System.out.print("¿Qué vivienda deseas reservar? (Seleccione el ID de la vivienda deseada): ");
                idReserva = s.nextLine();
                validacion.validarOpcion(idReserva);
                validar = true;
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return Integer.parseInt(idReserva);
    }

    //Métodos específicos del propietario
    public static Double pedirPrecioNoche(){
        String precioNoche = null;
        boolean validar = false;
        do{
            try {
                System.out.print("Introduce el precio por noche (Se tomarán los primeros 2 decimales): ");
                precioNoche = s.nextLine();
                validacion.validarPrecioNoche(precioNoche);
                validar = true;
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return (double)((int)(Double.parseDouble(precioNoche) * 100)) / 100;
    }

    public static String pedirLocalidad(){
        String localidad = null;
        boolean validar = false;
        do{
            try {
                System.out.print("Introduce la localidad: ");
                localidad = s.nextLine();
                validacion.validarLocalidad(localidad);
                validar = true;
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return localidad;
    }

    public static String pedirDireccion(){
        String direccion = null;
        boolean validar = false;
        do {
            try {
                System.out.print("Introduce la dirección: ");
                direccion = s.nextLine();
                validacion.validarDireccion(direccion);
                validar = true;
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return direccion;
    }

    // Método para validar fecha de inicio
    public static Date pedirFecha(String mensaje) {
        String fechaInicio = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        boolean validar = false;
        do{
            try {
                System.out.print(mensaje);
                fechaInicio = s.nextLine();
                validacion.validarFecha(fechaInicio);
                validar = true;
            } catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        try {
            return  formato.parse(fechaInicio);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public  static Date pedirFechaFin(Date fechaInicio, Date fechaFin) {
        boolean validar = false;
        do{
            try {
                validacion.validarFecha(fechaInicio, fechaFin);
                validar = true;
            }catch (DatosException e){
                System.out.println("Error " + e.getMessage());
            }
        }while (!validar);
        return fechaFin;
    }
    public static int pedirSoloNumero(String mensaje) {
        String opcion = null;
        boolean validar = false;
        do{
            try {
                System.out.print(mensaje);
                opcion = s.nextLine();
                validacion.validarSoloNumero(opcion); //Comparten el mismo método
                validar = true;
            }catch (DatosException e){
                System.out.println("Error: " + e.getMessage());
            }
        }while (!validar);
        return Integer.parseInt(opcion);
    }

    public static int pedirTipoTeclado() {
        int numero;
        boolean validar = false;
        do{
            numero = pedirSoloNumero(MensajesMenu.menuTiposViviendas() + "\nSeleccione una opción: ");
            if(numero >= 1 && numero <= 4) validar = true;
            else System.out.println("Tipo de vivienda incorrecta");
        }while (!validar);
        return numero;
    }


}