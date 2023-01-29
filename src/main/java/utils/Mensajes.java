package utils;

import model.Propietario;
import model.Reserva;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Mensajes {
    public static void delay(String mensaje) {
        System.out.print("\n" + mensaje);
        for (int i = 0; i < 4; i++) {
            System.out.print(".");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }

    public static void pausa (String mensaje) {
        Scanner s = new Scanner(System.in);
        System.out.println();
        System.out.print(mensaje);
        String pausa = s.nextLine();
    }

    public static String contenidoEmailFacturaUsuario(Reserva reserva, Date fechaReserva){
        SimpleDateFormat formatoReserva = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Mnesaje Factura Usuario</title>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            max-width: 600px;\n" +
                "            padding: 10px;\n" +
                "            margin: 0 auto;\n" +
                "            border-collapse: collapse;\n" +
                "            /*Indicamos que los bordes de las celdas no vam a estar separadas*/\n" +
                "        }\n" +
                "\n" +
                "        th {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            width: 30%;\n" +
                "            max-width: 150px;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            width: 30%;\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "\n" +
                "        .centrar{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        h3 {\n" +
                "            color: red;\n" +
                "        }\n" +
                "\n" +
                "        .totalPagar {\n" +
                "            background-color: red;\n" +
                "            color: white;\n" +
                "            display: inline-block;\n" +
                "            margin: 5px auto;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 20px;\n" +
                "        }\n" +
                "\n" +
                "        @media (min-width:1000px){\n" +
                "            h3{\n" +
                "                font-size: 20px;\n" +
                "            }\n" +
                "            p{\n" +
                "                font-size: 15px;\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "    <div class=\"contenedorGeneral\">\n" +
                "        <table>\n" +
                "\n" +
                "            <tr>\n" +
                "                <th>\n" +
                "                    <img src=\"https://i.ibb.co/pRhPLQc/logo-Fernanbnb-removebg-preview.png\" alt=\"logo\">\n" +
                "                </th>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td class = \"centrar\">\n" +
                "                    <h3>\n" +
                "                        ¡Buenas!\n" +
                "                    </h3>\n" +
                "                    <p>\n" +
                "                        Ya puedes ver tu factura sobre tu reserva realizada a las:\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                                        formatoReserva.format(fechaReserva) + " \n" +
                "                    </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td class=\"centrar\">\n" +
                "                    <div class = \"totalPagar\">\n" +
                "                        Total a pagar: " + (reserva.getVivienda().getPrecioNoche() * reserva.getNoches()) + "&euro;\n" +
                "                    </div>\n" +
                "                </td>\n" +
                "                \n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td class=\"centrar\">\n" +
                "                    <p>\n" +
                "                        Te enviamos un documento con la información detallada de su reserva.\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        Gracias por confiar en nosotros.\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        El equipo técnico de Fernanbnb.\n" +
                "                    </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "        </table>\n" +
                "    </div>\n" +
                "    \n" +
                "</body>\n" +
                "</html>";
    }
    public static String contenidoEmailFacturaPropietario(Reserva reserva){
        SimpleDateFormat formatoReserva = new SimpleDateFormat("dd/MM/yyyy");
        return  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Mensaje Factura Propietario</title>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            max-width: 600px;\n" +
                "            padding: 10px;\n" +
                "            margin: 0 auto;\n" +
                "            border-collapse: collapse;\n" +
                "            /*Indicamos que los bordes de las celdas no vam a estar separadas*/\n" +
                "        }\n" +
                "\n" +
                "        th {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            width: 30%;\n" +
                "            max-width: 150px;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            width: 30%;\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "\n" +
                "        .centrar{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        h3 {\n" +
                "            color: red;\n" +
                "        }\n" +
                "\n" +
                "        .totalPagar {\n" +
                "            background-color: red;\n" +
                "            color: white;\n" +
                "            display: inline-block;\n" +
                "            margin: 5px auto;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 20px;\n" +
                "        }\n" +
                "\n" +
                "        @media (min-width:1000px){\n" +
                "            h3{\n" +
                "                font-size: 20px;\n" +
                "            }\n" +
                "            p{\n" +
                "                font-size: 15px;\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "    <div class=\"contenedorGeneral\">\n" +
                "        <table>\n" +
                "\n" +
                "            <tr>\n" +
                "                <th>\n" +
                "                    <img src=\"https://i.ibb.co/pRhPLQc/logo-Fernanbnb-removebg-preview.png\" alt=\"logo\">\n" +
                "                </th>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td class = \"centrar\">\n" +
                "                    <h3>\n" +
                "                        ¡Enhorabuena!\n" +
                "                    </h3>\n" +
                "                    <p>\n" +
                "                        Su vivienda en " + reserva.getVivienda().getLocalidad() + " (Dirección: " + reserva.getVivienda().getDireccion()+") \n" +
                "                        acaba de ser reservado durante las siguientes fechas:\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                                         formatoReserva.format(reserva.getFechaEntrada()) + " al " + formatoReserva.format(reserva.getFechaSalida()) + " \n" +
                "                    </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td class=\"centrar\">\n" +
                "                    <p>\n" +
                "                        Puedes ver detalladamente la factura del interesado en el siguiente documento que hemos adjuntado.\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        Gracias por confiar en nosotros.\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        El equipo técnico de Fernanbnb.\n" +
                "                    </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "        </table>\n" +
                "    </div>\n" +
                "    \n" +
                "</body>\n" +
                "</html>";
    }

    public static String mensajeAnulacionReservaUsuario(Reserva reserva){
        SimpleDateFormat formatoReserva = new SimpleDateFormat("dd/MM/yyyy");
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Anulacion Reseserva</title>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            max-width: 600px;\n" +
                "            padding: 10px;\n" +
                "            margin: 0 auto;\n" +
                "            border-collapse: collapse;\n" +
                "            /*Indicamos que los bordes de las celdas no vam a estar separadas*/\n" +
                "        }\n" +
                "\n" +
                "        th {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            width: 30%;\n" +
                "            max-width: 150px;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            width: 30%;\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "\n" +
                "        .centrar{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        h3 {\n" +
                "            color: red;\n" +
                "        }\n" +
                "\n" +
                "        .totalPagar {\n" +
                "            background-color: red;\n" +
                "            color: white;\n" +
                "            display: inline-block;\n" +
                "            margin: 5px auto;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 20px;\n" +
                "        }\n" +
                "\n" +
                "        @media (min-width:1000px){\n" +
                "            h3{\n" +
                "                font-size: 20px;\n" +
                "            }\n" +
                "            p{\n" +
                "                font-size: 15px;\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "    <div class=\"contenedorGeneral\">\n" +
                "        <table>\n" +
                "\n" +
                "            <tr>\n" +
                "                <th>\n" +
                "                    <img src=\"https://i.ibb.co/pRhPLQc/logo-Fernanbnb-removebg-preview.png\" alt=\"logo\">\n" +
                "                </th>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td class = \"centrar\">\n" +
                "                    <h3>\n" +
                "                        ¡Buenas!\n" +
                "                    </h3>\n" +
                "                    <p>\n" +
                "                        Su reserva en " + reserva.getVivienda().getLocalidad() + " (Dirección: " + reserva.getVivienda().getLocalidad() + " ) acaba de ser anulada.\n" +
                "                    </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td class=\"centrar\">\n" +
                "                    <p>\n" +
                "                        Gracias por confiar en nosotros.\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        El equipo técnico de Fernanbnb.\n" +
                "                    </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "        </table>\n" +
                "    </div>\n" +
                "    \n" +
                "</body>\n" +
                "</html>";
    }

    public static String mensajeAnulacionReservaPropietario(Propietario propietario, Reserva reserva){
        SimpleDateFormat formatoReserva = new SimpleDateFormat("dd/MM/yyyy");
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Anulacion Reseserva</title>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            max-width: 600px;\n" +
                "            padding: 10px;\n" +
                "            margin: 0 auto;\n" +
                "            border-collapse: collapse;\n" +
                "            /*Indicamos que los bordes de las celdas no vam a estar separadas*/\n" +
                "        }\n" +
                "\n" +
                "        th {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            width: 30%;\n" +
                "            max-width: 150px;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            width: 30%;\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "\n" +
                "        .centrar{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        h3 {\n" +
                "            color: red;\n" +
                "        }\n" +
                "\n" +
                "        .totalPagar {\n" +
                "            background-color: red;\n" +
                "            color: white;\n" +
                "            display: inline-block;\n" +
                "            margin: 5px auto;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 20px;\n" +
                "        }\n" +
                "\n" +
                "        @media (min-width:1000px){\n" +
                "            h3{\n" +
                "                font-size: 20px;\n" +
                "            }\n" +
                "            p{\n" +
                "                font-size: 15px;\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "    <div class=\"contenedorGeneral\">\n" +
                "        <table>\n" +
                "\n" +
                "            <tr>\n" +
                "                <th>\n" +
                "                    <img src=\"https://i.ibb.co/pRhPLQc/logo-Fernanbnb-removebg-preview.png\" alt=\"logo\">\n" +
                "                </th>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td class = \"centrar\">\n" +
                "                    <h3>\n" +
                "                        ¡Ops!\n" +
                "                    </h3>\n" +
                "                    <p>\n" +
                "                        La reserva de su vivienda en " + reserva.getVivienda().getLocalidad() + " (Dirección: " + reserva.getVivienda().getDireccion()+") \n" +
                "                        acaba de ser anulada durante las siguientes fechas:\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                                         formatoReserva.format(reserva.getFechaEntrada()) + " al " + formatoReserva.format(reserva.getFechaSalida()) + " \n" +
                "                    </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td class=\"centrar\">\n" +
                "                    <p>\n" +
                "                        Esperemos que su vivienda sea reservada lo más pronto posible.\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        Gracias por confiar en nosotros.\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        El equipo técnico de Fernanbnb.\n" +
                "                    </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "        </table>\n" +
                "    </div>\n" +
                "    \n" +
                "</body>\n" +
                "</html>";
    }
    public static String contenidoEmailConfirmacion(String codigoConfirmacion){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            max-width: 600px;\n" +
                "            padding: 10px;\n" +
                "            margin: 0 auto;\n" +
                "            border-collapse: collapse;\n" +
                "            /*Indicamos que los bordes de las celdas no vam a estar separadas*/\n" +
                "        }\n" +
                "\n" +
                "        th {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            width: 30%;\n" +
                "            max-width: 150px;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            width: 30%;\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "\n" +
                "        .centrar{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        h3 {\n" +
                "            color: red;\n" +
                "        }\n" +
                "\n" +
                "        .codigoVerificacion {\n" +
                "            background-color: red;\n" +
                "            color: white;\n" +
                "            display: inline-block;\n" +
                "            margin: 5px auto;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 20px;\n" +
                "        }\n" +
                "       @media (min-width:1000px){\n" +
                "            h3{\n" +
                "                font-size: 20px;\n" +
                "            }\n" +
                "            p{\n" +
                "                font-size: 15px;\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "    <div class=\"conenedorGeneral\">\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "                <th>\n" +
                "                    <img src=\"https://i.ibb.co/pRhPLQc/logo-Fernanbnb-removebg-preview.png\" alt=\"logo\">\n" +
                "                </th>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <h3>¡Bienvenido!</h3>\n" +
                "                    <p>Gracias por registrarse a nuestro servicio. Te adjudicamos el siguiente código de verificación:</p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"centrar\">\n" +
                "                    <div class=\"codigoVerificacion\">\n" +
                                            codigoConfirmacion + "\n" +
                "                    </div>\n" +
                "                </td>\n" +
                "\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <p>"+ "" +
                "                          Este correo se ha generado automáticamente, si usted no esperaba este correo porfavor, ignóralo. En caso de \n" +
                "                          seguir recibiendo mas mensajes, por favor pongase en contacto con conostros\n"+
                "                    </p>\n" +
                "                </td>\n" +
                "\n" +
                "            </tr>\n" +
                "\n" +
                "        </table>\n" +
                "    </div>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static final String contenidoMenuUsuarioTelegram(){
        return """
                Seleccione una de las siguientes opciones:
                Para ver tus reservas seleccione /reservas
                Para ver la factura de tu última reserva /obtenerFactura
                """;
    }

    public static final String contenidoMenuPropietarioTelegram(){
        return """
                Seleccione una de las siguientes opciones:
                Para ver tus viviendas seleccione /casas
                Para ver las reservas de tu casa pulse /casaReser
                Para ver la última factura del interesado de su vivienda pulse /obtenerFactura
                """;
    }

    public static String mensajeCreacionReserva() {
        return """
                Ya hemos guardado su reserva a nuestra aplicación.
                Siempre puedes consultarla pulsando o escribiendo /casas""";
    }

    public static String mensajeCreacionReservaTelegram() {
        return  "Se ha realizado una reserva. Para ver la factura introduzca /obtenerFactura (experimental)";
    }

    public static String mensajeRecibirReservaTelegram() {
        return  "¡Enhorabuena!Acabas de recibir una reserva. Te mandamos el pdf de la factura del interesado .Para ver la factura introduzca /obtenerFactura (experimental)";
    }
}

/*
"<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>EnvioConfirmacion</title>\n" +
                "    <style>\n" +
                "        * {\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "\n" +
                "        #contenedorGeneral {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .rojo {\n" +
                "            color: red;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        h3 {\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        #imagen {\n" +
                "            width: 50%;\n" +
                "        }\n" +
                "\n" +
                "        .codigoVerificacion {\n" +
                "            background-color: red;\n" +
                "            color: white;\n" +
                "            display: inline-block;\n" +
                "            margin: 15px auto 0px auto;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div id=\"contenedorGeneral\">\n" +
                "        <img src=\"https://i.ibb.co/GR4JnPK/logo-Fernanbnb.png\" alt=\"logo\" id=\"imagen\">\n" +
                "        <h3 class=\"rojo\">\n" +
                "            ¡Bienvenido!\n" +
                "        </h3>\n" +
                "        <p>\n" +
                "            Gracias por registrarse a nuestro servicio. Te adjudicamos el siguiente código de verificación:\n" +
                "        </p>\n" +
                "        <div class=\"codigoVerificacion\">\n" +
                             codigoConfirmacion + "\n" +
                "        </div>\n" +
                "        <p>\n" +
                "            Este correo se ha generado automáticamente, si usted no esperaba este correo porfavor, ignóralo. En caso de\n" +
                "            seguir recibiendo mas mensajes, por favor pongase en contacto con conostros\n" +
                "        </p>\n" +
                "        <p>\n" +
                "            Atentamente\n" +
                "        </p>\n" +
                "        <p>\n" +
                "            El equipo técnico de Fernanbnb.\n" +
                "        </p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
*/

/*
"<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Envio Factura</title>\n" +
                "    <style>\n" +
                "        * {\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "\n" +
                "        #contenedorGeneral {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .rojo {\n" +
                "            color: red;\n" +
                "        }\n" +
                "\n" +
                "        .centrar{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        h3 {\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        #imagen {\n" +
                "            width: 50%;\n" +
                "        }\n" +
                "\n" +
                "        .precioPagar {\n" +
                "            background-color: red;\n" +
                "            color: white;\n" +
                "            display: inline-block;\n" +
                "            margin: 15px auto 15px auto;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div id=\"contenedorGeneral\">\n" +
                "        <img src=\"https://i.ibb.co/GR4JnPK/logo-Fernanbnb.png\" alt=\"logo\" id=\"imagen\">\n" +
                "        <h3 class=\"rojo\">\n" +
                "            ¡Buenas!\n" +
                "        </h3>\n" +
                "        <p>\n" +
                "            Ya puedes ver tu factura sobre tu reserva realizada a las:\n" +
                "        </p>\n" +
                "        <p class=\"centrar\">"+ formatoReserva.format(fechaReserva) +"</p>\n" +
                "        <div class=\"precioPagar\">\n" +
                "            Total a pagar: " + (reserva.getVivienda().getPrecioNoche() * reserva.getNoches()) +" &euro;\n" +
                "        </div>\n" +
                "        <p>\n" +
                "            Te enviamos un documento con la información detallada de su reserva.\n" +
                "        </p>\n" +
                "        <p>\n" +
                "            Gracias por confiar en nosotros.\n" +
                "        </p>\n" +
                "        <p>\n" +
                "            El equipo técnico de Fernanbnb.\n" +
                "        </p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
 */

// "https://i.ibb.co/GR4JnPK/logo-Fernanbnb.png"  logo con fondo