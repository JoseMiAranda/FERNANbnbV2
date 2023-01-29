package utils;

import model.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Menus {
    private static Scanner s = new Scanner(System.in);
    //private static int opcion;
    //private static Vivienda plantillaVivienda;
    //private static boolean cerrarSesion;

    //private static String passTeclado;


    // Menú administrador
    public static Administrador menuAdminstrador(Administrador administrador, Datos datos) {
        String nombreAdministrador = administrador.getNombre();
        boolean cerrarSesion = false;
        while (!cerrarSesion) {
            // Mostramos el menú de administrador, pide una opción y la comprueba
            int opcion = PedirDatos.pedirOpcion(MensajesMenu.menuAdministrador(nombreAdministrador));
            switch (opcion) {
                case 1: // Ver todas las viviendas en alquiler
                    System.out.println(datos.mostrarViviendas());
                    break;
                case 2: // Ver todos los usuarios del sistema
                    mostrarUsuariosSistema(datos);
                    break;
                case 3: // Ver todas las reservas de viviendas
                    System.out.println(datos.mostrarTodasReservas(administrador));
                    break;
                case 4: // Ver mi perfil
                    System.out.println(administrador.toString());
                    break;
                case 5: // Modificar mi perfil
                    modificarPerfilAdministrador(administrador);
                    break;
                case 6: // Cerrar sesión
                    cerrarSesion = true;
                    break;
                default:
                    System.out.println("Error al elegir la opción.");
                    Mensajes.pausa("Pulse enter para continuar...");
            }
            Mensajes.pausa("Pulse enter para continuar...");
        }
        Mensajes.delay("Volviendo al menú principal");
        return administrador;
    }


    // Menú usuario
    public static Usuario menuUsuario(Usuario usuario, Datos datos) {
        boolean cerrarSesion = false;
        Mensajes.delay("Cargando menú usuario");
        while (!cerrarSesion) {
            String nombreUsuario = usuario.getNombreUsuario();
            int numeroReservas = usuario.getNumReservas();
            // Mostramos el menú de usuario, pide una opción y la comprueba
            int opcion = PedirDatos.pedirOpcion(MensajesMenu.menuUsuario(nombreUsuario, numeroReservas));
            switch (opcion) {
                case 1: // Búsqueda de alojamientos
                    busquedaDeAlojamientos(datos, usuario);
                    break;
                case 2: // Ver mis reservas
                    if (usuario.verReserva(datos).equals("")) System.out.println("No hay reservas creadas.");
                    else {
                        Mensajes.delay("Recopilando reservas");
                        System.out.println(usuario.verReserva(datos));
                    }
                    Mensajes.pausa("Pulse enter para continuar...");
                    break;
                case 3: // Borrar mis reservas
                    borrarReservas(datos, usuario);
                    break;
                case 4: // Ver mi perfil
                    System.out.println(usuario.toString());
                    Mensajes.pausa("Pulse enter para continuar...");
                    break;
                case 5: // Modificar mi perfil
                    modificarPerfilUsuario(usuario);
                    break;
                case 6: // Cerrar sesión
                    cerrarSesion = true;
                    break;
                default:
                    System.out.println("Error al elegir la opción.");
                    Mensajes.pausa("Pulse enter para continuar...");
            }
        }
        Mensajes.delay("Volviendo al menú principal");
        return usuario;
    }



    // Menú propietario
    public static Propietario menuPropietario(Propietario propietario, Datos datos) {
        boolean cerrarSesion = false;
        Mensajes.delay("Cargando menú propietario");
        while (!cerrarSesion) {
            String nombrePropietario = propietario.getNombreUsuario();
            int numeroViviendas = propietario.getTotalViviendas();
            int opcion = PedirDatos.pedirOpcion(MensajesMenu.menuPropietario(nombrePropietario, numeroViviendas));
            switch (opcion) {
                case 1: // Ver mis viviendas
                    Mensajes.delay("Recopilando propiedades");
                    if (propietario.existenciaVivienda()) System.out.println(propietario.mostrarVivienda());
                    else System.out.println("No se han registrado viviendas");
                    Mensajes.pausa("Pulse enter para continuar...");
                    break;
                case 2: // Editar mis viviendas
                    modificarViviendas(propietario, datos);
                    break;
                case 3: // Ver las reservas de mis viviendas
                    System.out.println(propietario.verReservas(datos));
                    Mensajes.pausa("Pulse enter para continuar...");
                    break;
                case 4: // Cambiar periodo de no disponibilidad para una vivienda
                    // Simular que en las fechas introducidas no esté disponible la vivienda
                    ponerPeriodoNoDisponibilidad(propietario);
                    break;
                case 5: // Ver mi perfil
                    System.out.println(propietario.toString());
                    Mensajes.pausa("Pulse enter para continuar...");
                    break;
                case 6: // Modificar mi perfil
                    modificarPerfilPropietario(propietario);
                    break;
                case 7: // Cerrar sesión
                    cerrarSesion = true;
                    break;
                default:
                    System.out.println("Error al elegir la opción.");
                    Mensajes.pausa("Pulse enter para continuar...");
            }
        }
        Mensajes.delay("Volviendo al menu principal");
        return propietario;
    }




    public static void crearPeriodoNoDisponible(Propietario propietario) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaI = PedirDatos.pedirFecha("Introduzca una fecha de inicio (dd/MM/yyyy): ");
        Date fechaF = PedirDatos.pedirFechaFin(fechaI,PedirDatos.pedirFecha("Introduzca una fecha de fin (dd/MM/yyyy): "));
        propietario.cambiarEstadoVivienda(fechaI, fechaF);
        System.out.println("Tu vivienda no estará disponible en estas fechas: Fecha Inicio: " + formato.format(fechaI) + "    Fecha Fin: " + formato.format(fechaF));
    }

    public static boolean enviarNotificacionTelegramUsuario(String idChatTelegramUsuario,String mensajeUsuario){
        if(idChatTelegramUsuario.equals("")) return false;
        return (MiBotTelegram.enviaMensajeTelegram(idChatTelegramUsuario, mensajeUsuario));
    }

    public static boolean enviarNotificacionTelegramPropietario(String idChatTelegramPropietario, String mensajePropietario){
        if(idChatTelegramPropietario.equals("")) return false;
        return (MiBotTelegram.enviaMensajeTelegram(idChatTelegramPropietario, mensajePropietario));
    }

    public static void enviarNotificacionTelegram(String idChatTelegramUsuario, String idChatTelegramPropietario, String mensajeUsuario, String mensajePropietario){
        enviarNotificacionTelegramUsuario(idChatTelegramUsuario, mensajeUsuario);
        enviarNotificacionTelegramPropietario(idChatTelegramPropietario, mensajePropietario);
    }

    //Funciones de Administrador
    private static void modificarPerfilAdministrador(Administrador administrador) {
        int opcion;
        do {
            // Mostramos el menú de modificar el perfil, pide una opción y la comprueba
            opcion = PedirDatos.pedirOpcion(MensajesMenu.modificarAdministrador());
            switch (opcion) {
                case 1 -> { // Modificar nombre
                    System.out.print("Introduce el nuevo nombre: ");
                    administrador.setNombre(PedirDatos.pedirNombre());
                    Mensajes.delay("Guardando cambios");
                }
                case 2 -> { // Modificar contraseña
                    System.out.print("Introduce la contraseña antigua: ");
                    if (!s.nextLine().equals(administrador.getPass()))
                        System.out.println("Contraseña incorrecta");
                    else {
                        // Pedir contraseña hasta que sea con longitud máxima de 7
                        String passTeclado = PedirDatos.pedirConstrasenia();
                        administrador.setPass(passTeclado);
                        Mensajes.delay("Guardando cambios");
                    }
                }
                case 3 -> { // Salir
                    Mensajes.delay("Volviendo al menú administrador");
                }
            }
        } while (opcion != 3);
    }

    private static void mostrarUsuariosSistema(Datos datos) {
        Mensajes.delay("Mostrando todos los usuarios");
        if (datos.getTotalUsuarios() == 0 && datos.getTotalPropietarios() == 0)
            System.out.println("No hay usuarios creados");
        if (datos.getTotalPropietarios() != 0 && datos.getTotalUsuarios() == 0) {
            System.out.println("--------- PROPIETARIOS ---------");
            System.out.println(datos.mostrarPropietarios());
        }
        if (datos.getTotalUsuarios() != 0 && datos.getTotalPropietarios() == 0) {
            System.out.println("--------- USUARIOS ---------");
            System.out.println(datos.mostrarUsuarios());
        }
        if (datos.getTotalUsuarios() != 0 && datos.getTotalPropietarios() != 0) {
            System.out.println("--------- PROPIETARIOS ---------");
            System.out.println(datos.mostrarPropietarios());
            System.out.println("\n--------- USUARIOS ---------");
            System.out.println(datos.mostrarUsuarios());
        }
    }



    //Funciones de Usuario

    private static boolean mandarFacturaReserva(Datos datos, Usuario usuario, Reserva reserva, String mensajeUsuario, Date fechaReserva, String rutaFactura) {
        //return Email.enviarFactura(usuario.getEmail(),"Factura de resera",mensaje,"C:/Users/Jose Aranda/Desktop/FERNANbnb/FERNANbnb-master/factura.pdf");
        String emailPropietario = datos.buscarPropietario(reserva.getVivienda().getId()).getEmail();
        String mensajePropietario = Mensajes.contenidoEmailFacturaPropietario(datos.buscarRerserva(datos.getIdUltimaReserva()));
        return Email.enviarFactura(usuario.getEmail(), emailPropietario, "Factura de resera", "Obtención de reserva", mensajeUsuario, mensajePropietario,rutaFactura);
    }

    private static boolean mandarAnulacionReserva(Datos datos, Usuario usuario, Reserva reserva) {
        String mensajeUsuario = Mensajes.mensajeAnulacionReservaUsuario(reserva);
        String emailPropietario = datos.buscarPropietario(reserva.getVivienda().getId()).getEmail();
        String mensajePropietario = Mensajes.mensajeAnulacionReservaPropietario(datos.buscarPropietario(reserva.getId()), reserva);
        return Email.enviarAnulacion(usuario.getEmail(), emailPropietario, "Anulación de resera", "Anulación de reserva", mensajeUsuario, mensajePropietario);
    }
    private static void modificarPerfilUsuario(Usuario usuario) {
        int opcion;
        do {
            opcion = PedirDatos.pedirOpcion(MensajesMenu.menuModificarPerfil());
            switch (opcion) {
                case 1 -> { // Modificar nombre
                    usuario.setNombre(PedirDatos.pedirNombre());
                    Mensajes.delay("Guardando cambios");
                }
                case 2 -> { // Modificar apellidos
                    usuario.setApellidos(PedirDatos.pedirApellido());
                    Mensajes.delay("Guardando cambios");
                }
                case 3 -> { // Modificar contraseña pidiendo la antigua
                    System.out.print("Introduce la contraseña antigua: ");
                    if (s.nextLine().equals(usuario.getPass())) {
                        // Pedir contraseña hasta que sea con longitud máxima de 12
                        String passTeclado = PedirDatos.pedirConstrasenia();
                        usuario.setPass(passTeclado);
                        Mensajes.delay("Guardando cambios");
                    } else System.out.println("Contraseña incorrecta.");
                }
                case 4 -> { // Modificar número de teléfono pidiendo la contraseña
                    System.out.print("Introduce la contraseña: ");
                    if (s.nextLine().equals(usuario.getPass())) {
                        System.out.print("Introduce el nuevo teléfono: ");
                        usuario.setTelefono(PedirDatos.pedirNumTelefono());
                    } else System.out.println("Contraseña incorrecta.");
                }
                case 5 -> { // Salir
                    Mensajes.delay("Volviendo al menú usuario");
                }
            }
        } while (opcion != 5);
    }

    private static void borrarReservas(Datos datos, Usuario usuario) {
        int opcion;
        if (usuario.verReserva(datos).equals("")) System.out.println("No hay reservas creadas.");
        else {
            System.out.println(usuario.verReserva(datos));
            Boolean salir = false;
            while (!salir) {
                do {
                    // Comprobamos el id de la reserva que quiere borrar
                    opcion = PedirDatos.pedirSoloNumero("Seleccione la reserva que desea borrar o pulse 0 (cero) para salir: ");
                    if (opcion == 0) salir = true;
                    else {
                        if (!usuario.comprobarReserva(opcion)) System.out.println("Opción incorrecta");
                        else {
                            String opcionBorrar;
                            do {
                                System.out.print("¿Deseas borrarla? (S/N): ");
                                opcionBorrar = s.nextLine().toUpperCase();
                                if (!opcionBorrar.equals("S") && !opcionBorrar.equals("N"))
                                    System.out.println("Error al introducir la opción.");
                            } while (!opcionBorrar.equals("S") && !opcionBorrar.equals("N"));

                            if (opcionBorrar.equals("S")) {
                                System.out.println("Estamos enviando la anulación. Por favor espere...");
                                while (!mandarAnulacionReserva(datos, usuario, datos.buscarRerserva(opcion))) {//Le comunicamos la anulación de la reserva tanto al usuario como al propietario
                                    //Si no se puede se vuelve a mandar
                                    System.out.println("""
                                                        No hemos podido enviar la anulación de la reserva.
                                                        Volviéndolo a enviar""");
                                }
                                usuario.borrarReserva(opcion); //Se borra en usuario
                                datos.borrarReserva(opcion); //Se borra en propietario
                                Mensajes.delay("Borrando reserva");
                            }
                            salir = true;
                        }
                    }
                } while (!salir);
            }
        }
        Mensajes.pausa("Pulse enter para continuar...");
    }




    private static void busquedaDeAlojamientos(Datos datos, Usuario usuario) {
        int opcion;
        System.out.print("Introduzca una ciudad para buscar: ");
        String ciudadTeclado = s.nextLine();
        Date fechaInicio = PedirDatos.pedirFecha("Introduzca una fecha de inicio (dd/MM/yyyy): ");
        Date fechaFin = PedirDatos.pedirFechaFin(fechaInicio,PedirDatos.pedirFecha("Introduzca una fecha de fin (dd/MM/yyyy): "));
        int huespedesTeclado = PedirDatos.pedirSoloNumero("Introduce el número máximo de huéspedes: ");
        Mensajes.delay("Buscando alojamientos");
        String resultadoAlojamoientos = datos.buscarAlojamiento(ciudadTeclado, fechaInicio, fechaFin, huespedesTeclado, datos);
        System.out.println(resultadoAlojamoientos);
        if (!resultadoAlojamoientos.equals("No hay viviendas en alquiler.") && !resultadoAlojamoientos.equals("No se han encontrado viviendas con esos requisistos")) {
            String opcionReservar;
            do {
                System.out.print("¿Deseas hacer una reserva (S/N)?: ");
                opcionReservar = s.nextLine().toUpperCase();
                if (!opcionReservar.equals("S") && !opcionReservar.equals("N")) System.out.println("Error al introducir la opción.");
            } while (!opcionReservar.equals("S") && !opcionReservar.equals("N"));
            if (opcionReservar.equals("N")) Mensajes.pausa("Pulse enter para continuar...");
            else {
                /*boolean reservar = false;
                do {
                    opcion = pedirDatos.pedirIdReserva();
                    switch (opcion) {
                        case 1, 2 -> {
                            if (datos.comprobarID(opcion) == -1) System.out.println("Opción incorrecta."); //Como el total de id son 1 o 2 si se introduce el id equivocado ( Ej: si la vivienda con id 2 no se ha creado) que se muestre como en default
                            else {
                                if (usuario.getNumReservas() >= 2) System.out.println("Límite de reversas alcanzado.");
                                else {
                                    datos.setIdUltimaReserva(opcion);
                                    guardarRerservaUsuarioPropietarioDatos(datos,usuario,fechaInicio,fechaFin,huespedesTeclado);
                                    mandarFacturaUsuarioPropietario(datos,usuario);
                                }
                                reservar = true;
                            }
                        }
                        default -> {
                            System.out.println("Error al elegir la opción. \n");
                        }
                    }

                } while (!reservar);*/
                boolean reservar = false;
                do {
                    opcion = PedirDatos.pedirIdReserva();
                    if (datos.comprobarID(opcion) == -1) System.out.println("Opción incorrecta.");
                    else {
                        if (usuario.getNumReservas() >= 2) System.out.println("Límite de reversas alcanzado.");
                        else {
                            datos.setIdUltimaReserva(opcion);
                            guardarRerservaUsuarioPropietarioDatos(datos,usuario,fechaInicio,fechaFin,huespedesTeclado,opcion);
                            mandarFacturaUsuarioPropietario(datos,usuario,opcion);
                        }
                        reservar = true;
                    }

                }while (!reservar);

            }
        } else Mensajes.pausa("Pulse enter para continuar...");
    }

    private static void guardarRerservaUsuarioPropietarioDatos(Datos datos, Usuario usuario, Date fechaInicio, Date fechaFin, int huespedesTeclado, int opcion) {
        datos.crearReserva(usuario, datos.comprobarID(opcion), fechaInicio, fechaFin, huespedesTeclado); //Se guarda en Datos
        usuario.crearReserva(datos.getIdUltimaReserva());//Se guarda en usuario
        datos.guardarIdRerserva(opcion, datos.getIdUltimaReserva());//Se guarda en propietario
    }

    private static void mandarFacturaUsuarioPropietario(Datos datos, Usuario usuario, int idVivienda) {
        Mensajes.delay("Generando reserva");
        System.out.println(datos.buscarRerserva(datos.getIdUltimaReserva()).toString());
        System.out.println("Mandando reserva al correo. Por favor espere");
        Date fechaReserva = Calendar.getInstance().getTime();
        String mensajeUsuario = Mensajes.contenidoEmailFacturaUsuario(datos.buscarRerserva(datos.getIdUltimaReserva()), fechaReserva);
        String nombreFactura = datos.generarNombreFactura();
        String idFacturaGenerado = datos.generarIdFactura();
        Factura.generarFactura(usuario, datos.buscarRerserva(datos.getIdUltimaReserva()), fechaReserva, nombreFactura,idFacturaGenerado);
        String ruta = "C:/FERNANbnb/" + nombreFactura;
        usuario.setRutaUltimaFactura(ruta); // se guarda la ruta del fichero en el usuario
        datos.aniadirRutaPropietario(idVivienda,ruta); // se guarda la ruta del fichero en el propietario
        //Comprobar método
        int idViviendaPropietario = datos.buscarRerserva(datos.getIdUltimaReserva()).getVivienda().getId();
        String chatIdTelegramPropietario = datos.buscarPropietario(idViviendaPropietario).getIdChatTelegram();
        enviarNotificacionTelegram(usuario.getIdChatTelegram(),chatIdTelegramPropietario,Mensajes.mensajeCreacionReservaTelegram(),
                Mensajes.mensajeRecibirReservaTelegram());
        if (!mandarFacturaReserva(datos, usuario, datos.buscarRerserva(datos.getIdUltimaReserva()), mensajeUsuario, fechaReserva,usuario.getRutaUltimaFactura())) { // En el caso del correo electrónico
            boolean validarOpcion = false;
            String opcionMensaje;
            do {
                if(!PedirDatos.pedirSiNo("""
                                                                Error al enviar en correo
                                                                ¿Desea enviarla de nuevo(S/N)?: """)) validarOpcion = true;
                else {
                    System.out.println("Volviendo a enviar el mensaje");
                    if (mandarFacturaReserva(datos, usuario, datos.buscarRerserva(datos.getIdUltimaReserva()), mensajeUsuario, fechaReserva,usuario.getRutaUltimaFactura())) {
                        validarOpcion = true;
                        System.out.println("Confirmación enviada");
                    }
                }
            } while (!validarOpcion);
        } else {
            System.out.println("Confirmación enviada");
        }
    }


    //Funciones de Propietario
    private static void ponerPeriodoNoDisponibilidad(Propietario propietario) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String op;
        if (propietario.getVivienda().getFechaInicio() != null) {
            do {
                System.out.print("Actualmente tienes un periodo de no disponibilidad en su vivienda en las siguientes fechas: " +
                        "\nInicio: " + formato.format(propietario.getVivienda().getFechaInicio()) +
                        "\nFin: " + formato.format(propietario.getVivienda().getFechaFin()) +
                        "\n¿Deseas borrarla o modificarla (B/M)?: ");
                op = s.nextLine().toUpperCase();
                switch (op) {
                    case "B" -> {
                        propietario.getVivienda().borrarFechaNoDisponibilidad();
                        Mensajes.delay("Borrando fecha de no disponibilidad");
                    }
                    case "M" -> {
                        crearPeriodoNoDisponible(propietario);
                        Mensajes.delay("Guardando cambios");
                    }
                    default -> {
                        System.out.println("Error al elegir la opción.");
                        Mensajes.pausa("Pulse enter para continuar...");
                    }
                }
            } while (!op.equals("B") && !op.equals("M"));

        } else {
            crearPeriodoNoDisponible(propietario);
            Mensajes.delay("Guardando cambios");
        }
    }

    private static void modificarViviendas(Propietario propietario,Datos datos) {
        int opcion;
        boolean salirModificacion = false;
        do {
            opcion = PedirDatos.pedirOpcion(MensajesMenu.menuEditarViviendas());
            switch (opcion) {
                case 1: // Editar el número de huéspedes
                    propietario.cambiarHuespedes(PedirDatos.pedirSoloNumero("Introduce el número máximo de huéspedes: "));
                    Mensajes.delay("Guardando cambios");
                    break;
                case 2: // Editar el precio por noche
                    propietario.cambiarPrecioNoche(PedirDatos.pedirPrecioNoche());
                    Mensajes.delay("Guardando cambios");
                    break;
                case 3: // Borrar vivienda
                    Mensajes.delay("Borrando vivienda");
                    propietario.borrarVivienda();
                    break;
                case 4: // Añadir vivienda
                    if (propietario.getTotalViviendas() != 0)
                        System.out.println("Se ha alcanzado el total de viviendas creadas.");
                    else {
                        Vivienda plantillaVivienda;
                        // Pedir datos constructor vivienda
                        // Crearla y después añadirla al contructor de Propietario
                        plantillaVivienda = new Vivienda();
                        plantillaVivienda.setLocalidad(PedirDatos.pedirLocalidad());
                        plantillaVivienda.setDireccion(PedirDatos.pedirDireccion());
                        plantillaVivienda.setHuesped(PedirDatos.pedirSoloNumero("Introduce el número máximo de huéspedes: "));
                        plantillaVivienda.setPrecioNoche(PedirDatos.pedirPrecioNoche());
                        do {
                            opcion = PedirDatos.pedirOpcion(MensajesMenu.menuTiposViviendas());
                            switch (opcion) {
                                case 1: // Chalet
                                    plantillaVivienda.setTipoVivienda(1);
                                    break;
                                case 2: // Apartamento
                                    plantillaVivienda.setTipoVivienda(2);
                                    break;
                                case 3: // Bajo
                                    plantillaVivienda.setTipoVivienda(3);
                                    break;
                                case 4: // Estudio
                                    plantillaVivienda.setTipoVivienda(4);
                                    break;
                                default:
                                    System.out.println("Tipo de vivienda incorrecto.");
                                    Mensajes.pausa("Pulse enter para continuar...");
                            }
                        } while (opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4);
                        propietario.crearVivienda(plantillaVivienda,datos.generaIdVivienda());
                        Mensajes.delay("Guardando cambios");
                        enviarNotificacionTelegramPropietario(propietario.getIdChatTelegram(), Mensajes.mensajeCreacionReserva());
                    }
                    break;
                case 5: //Salir
                    salirModificacion = true;
                    Mensajes.delay("Volviendo al menú propietario");
                    break;
                default:
                    System.out.println("Error al elegir la opción.");
                    Mensajes.pausa("Pulse enter para continuar...");
            }
        } while (!salirModificacion);
    }

    private static void modificarPerfilPropietario(Propietario propietario) {
        boolean salirMenuOpcion = false;
        int opcion;
        do {
            opcion = PedirDatos.pedirOpcion(MensajesMenu.menuModificarPerfil());
            switch (opcion) {
                case 1 -> { // Modificar nombre
                    propietario.setNombre(PedirDatos.pedirNombre());
                    Mensajes.delay("Guardando cambios");
                }
                case 2 -> { // Modificar apellidos
                    propietario.setApellidos(PedirDatos.pedirApellido());
                    Mensajes.delay("Guardando cambios");
                }
                case 3 -> { // Modificar contraseña pidiendo la antigua
                    System.out.print("Introduce la contraseña antigua: ");
                    if (s.nextLine().equals(propietario.getPass())) {
                        // Pedir contraseña hasta que sea con longitud máxima de 12
                        String passTeclado = PedirDatos.pedirConstrasenia();
                        propietario.setPass(passTeclado);
                        Mensajes.delay("Guardando cambios");
                    } else System.out.println("Contraseña incorrecta");
                }
                case 4 -> { // Modificar número de teléfono pidiendo la contraseña
                    System.out.print("Introduce la contraseña: ");
                    if (s.nextLine().equals(propietario.getPass())) {
                        propietario.setTelefono(PedirDatos.pedirNumTelefono());
                    } else System.out.println("Contraseña incorrecta");
                }
                case 5 -> { // Salir
                    salirMenuOpcion = true;
                    Mensajes.delay("Volviendo al menú propietario");
                }
            }
        } while (!salirMenuOpcion);
    }


}
