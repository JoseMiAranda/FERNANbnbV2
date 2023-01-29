package org.example;

import model.*;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import utils.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menuInicio();
        System.exit(0); //Forzamos a que acabe el bot de Telegram
    }

    public static void menuInicio(){
        Datos datos = new Datos();
        MiBotTelegram.setDatos(datos);
        // Propìetario por defecto
        //datos.propietarioPorDefecto(new Propietario("sara.t.e@hotmail.com", "LaJefaDeGuadalinfo", "1234567", "Sara", "Tejero", 1112312121, true), new Vivienda("Granada", "Real", 7, 34.78, 3));
        datos.registrarUsuarioDefecto(new Usuario("usuario1FERNANbnb@gmail.com","JoseMiUser","1234567","JoseMi","Aranda",111111111, true));
        datos.registrarPropietarioConViviendaPorDefecto(new Propietario("propietario1FERNANbnb@gmail.com", "SaraProp", "1234567","Sara", "Tejero", 222222222,
                true), new Vivienda(datos.generaIdVivienda(),"Granada", "Real", 7, 34.78, 3));
        //"sara.t.e@hotmail.com"

        //Inicio del programa
        boolean cerrarSesion = false; //Inicializamos en este punto para que en cada vuelta que se ejecute tenga el valor false

        iniciarBotTelegram();

        do {
            // Comenzamos iniciando sesión o registrando un usuario o propietario
            int opcionMenuPrincipal = PedirDatos.pedirOpcion(MensajesMenu.menuInicio());
            switch (opcionMenuPrincipal) {
                case 1: // Iniciar sesión
                    loggin(datos);
                    break;
                case 2: // Registrar nuevo perfil de usuario
                    menuRegistro(datos);
                    break;
                case 3: //si el usuario o el propietario no han validado el codigo del correo electrónico se le pedirá o mandará de nuevo
                    corfirmarUsuarioPropietario(datos);
                    break;
                case 4: // Apagar programa
                    Mensajes.delay("Apagando programa");
                    cerrarSesion = true;
                    break;
                default:
                    System.out.println("Error al introducir la opción.");
                    Mensajes.delay("Volviendo al menú principal");
            }
        } while (!cerrarSesion);
        // Forzamos la salida del programa para que el bot de telegram deje de estar activo
    }

    private static void corfirmarUsuarioPropietario(Datos datos) {
        String emailTeclado = PedirDatos.pedirEmailLogging();
        String passTeclado = PedirDatos.pedirConstrasenia();
        if(!datos.existenciaUsuarioPropietarioNoConfirmado(emailTeclado,passTeclado)) System.out.println("Usuario no encontrado");
        else {
            if(PedirDatos.pedirConfirmacion(emailTeclado,datos)) {
                datos.cambiarVisibilidad(emailTeclado,passTeclado,datos);
                datos.iniciarSesion(emailTeclado, passTeclado, datos);
            }
            else System.out.println("Has decidido no validar tu email. Recuerda de debes validarlo para acceder de nuevo a tu menú");
        }
    }

    public static  void loggin(Datos datos){
        String emailTeclado = PedirDatos.pedirEmailLogging();
        String passTeclado = PedirDatos.pedirConstrasenia();
        if (datos.usuarioExistente(emailTeclado,passTeclado)) datos.iniciarSesion(emailTeclado, passTeclado, datos);
         else {
            System.out.println("\nUsuario o contraseña incorrecta.");
            Mensajes.pausa("Pulse enter para continuar...");
        }
    }

    public static void menuRegistro(Datos datos){
        // Comprobar que no hemos alcanzado el máximo de usuarios y propietarios creados
        int opcion;
        if (datos.maximoUsuariosPermitidos() && datos.maximoPropietariosPermitidos())
            System.out.println("Se ha alcanzado el límite de registros.\n");
        else {
            boolean registrar = false;
            do {
                opcion = PedirDatos.pedirOpcion(MensajesMenu.menuRegistro());
                switch (opcion) {
                    case 1: //Registrar usuario
                        registrarUsuario(datos);
                        registrar = true;
                        break;
                    case 2: // Registrar nuevo perfil de propietario
                        registrarPropietario(datos);
                        registrar = true;
                        break;
                    case 3: // Salir
                        registrar = true;
                        break;
                    default:
                        registrar = false;
                        System.out.println("Error al introducir la opción.");
                        Mensajes.pausa("Pulse enter para continuar...");
                }
            } while (!registrar);
            //Una vez finalizado el registro volvemos al menú de inicio
            if (opcion == 3) Mensajes.delay("Volviendo al menú principal");
        }
    }

    public static void iniciarBotTelegram(){
        // Bot de Telegram
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MiBotTelegram());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("El bot está listo");
    }

    public static void registrarUsuario(Datos datos){
        if (datos.maximoUsuariosPermitidos())
            System.out.println("Se ha alcanzado el límite de usuarios registrados.");
        else {

            // Comprobamos que el email no esté registrado
            String emailTeclado = PedirDatos.pedirEmail(datos);
            //datos.registrarUsuario(new Usuario(emailTeclado, nombreUsuarioTeclado, passTeclado, nombreTeclado, apellidosTeclado, telefonoTeclado));
            datos.registrarUsuario(new Usuario(emailTeclado,PedirDatos.pedirNombreUsuario(datos),PedirDatos.pedirConstrasenia(),
                    PedirDatos.pedirNombre(), PedirDatos.pedirApellido(),PedirDatos.pedirNumTelefono(),PedirDatos.pedirConfirmacion(emailTeclado,datos)),datos);
        }
    }

    public static void registrarPropietario(Datos datos){
        if (datos.maximoPropietariosPermitidos())
            System.out.println("Se ha alcanzado el límite de propietarios registrados.");
        else {
            //Comprobamos que el email no esté registrado
            String emailTeclado = PedirDatos.pedirEmail(datos);
            String nombreUsuarioTeclado = PedirDatos.pedirNombreUsuario(datos);
            String passTeclado = PedirDatos.pedirConstrasenia();
            String nombreTeclado = PedirDatos.pedirNombre();
            String apellidosTeclado = PedirDatos.pedirApellido();
            int telefonoTeclado = PedirDatos.pedirNumTelefono();
            if (PedirDatos.pedirSiNo("¿Desea añadir alguna propiedad? (S/N): ")){
                // Pedir datos constructor vivienda
                // Crearla y después añadirla al contructor de Propietario
                String localidad = PedirDatos.pedirLocalidad();
                String direccion = PedirDatos.pedirDireccion();
                int numMaxHuespedes = PedirDatos.pedirSoloNumero("Introduce el número máximo de huéspedes: ");
                double precioNoche = PedirDatos.pedirPrecioNoche();
                int tipoVivienda = PedirDatos.pedirTipoTeclado();
                datos.registrarPropietarioConVivienda(new Propietario(emailTeclado, nombreUsuarioTeclado, passTeclado, nombreTeclado, apellidosTeclado,
                        telefonoTeclado,PedirDatos.pedirConfirmacion(emailTeclado,datos)), new Vivienda(datos.generaIdVivienda(),localidad,direccion,numMaxHuespedes,precioNoche,tipoVivienda),datos);
            }else {
                datos.registrarPropietarioSinVivienda(new Propietario(emailTeclado, nombreUsuarioTeclado, passTeclado, nombreTeclado, apellidosTeclado,
                        telefonoTeclado,PedirDatos.pedirConfirmacion(emailTeclado,datos)),datos);
            }
        }
    }
}