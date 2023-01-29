package excepciones;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MensajesExcepcion {
    //Constructor
    public MensajesExcepcion() {

    }

    //Método para la opción del menú
    public void validarOpcion(String opcion) throws DatosException{
        if (opcion.equals("")) throw new DatosException("Opción vacía."); //Si el email está vacío mandaremos una excepción
        for (int i = 0; i < opcion.length(); i++){
            if(!Character.isDigit(opcion.charAt(0))) throw new DatosException("Sólo se puede introducir valores numéricos."); //Si la opción no contiene dígito manda una excepcion
        }
    }

    //Métodos del usuario y propietario
    public void validarEmail(String email) throws DatosException {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);

        if(!mather.find()) throw new DatosException("El email ingresado es incorrecto.");

    }

    public void validarNombreUsuario(String nombreUsuario) throws DatosException{
        if (nombreUsuario.equals("")) throw new DatosException("Nombre de usuario vacía.");
        if (nombreUsuario.length() < 6 || nombreUsuario.length() > 30) throw new DatosException("El nombre de usuario tiene que tener una longitud entre 6 y 30 caracteres.");
        if (Character.isWhitespace(nombreUsuario.charAt(0)) || Character.isWhitespace(nombreUsuario.charAt(nombreUsuario.length() - 1))) throw new DatosException("No puede incluir espacios en blanco al principio y al final.");
        for (int i = 0; i < nombreUsuario.length(); i++) {
            if (Character.isWhitespace(nombreUsuario.charAt(i))) throw new DatosException("Espacio detectado.");
            if (!Character.isLetter(nombreUsuario.charAt(i)) && !Character.isDigit(nombreUsuario.charAt(i))){
                if(nombreUsuario.charAt(i) == '&' || nombreUsuario.charAt(i) == '=' || nombreUsuario.charAt(i) == '_' ||
                        nombreUsuario.charAt(i) == '\'' || nombreUsuario.charAt(i) == '~' || nombreUsuario.charAt(i) == '<' || nombreUsuario.charAt(i) == '>')
                    throw new DatosException("No se pueden incluir los siguienetes caracteres: & = _ \' ~ < > .");
            }
            if(i != nombreUsuario.length() - 1) {
                if (nombreUsuario.charAt(i) == '.' && nombreUsuario.charAt(i + 1) == '.') throw new DatosException("No se pueden incluir los siguienetes caracteres: & = _ \' ~ < >.");
            }
        }
    }
    public void validarContrasenia(String contrasenia) throws DatosException {
        if (contrasenia.equals("")) throw new DatosException("Contraseña vacía.");
        if (contrasenia.length() < 6) throw new DatosException("Se pide una contraseña mínima de longitud 6.");
        if (contrasenia.length() > 12) throw new DatosException("Se ha superado la longitud de 12 caracteres.");
        for (int i = 0; i < contrasenia.length(); i++) {
            if (Character.isWhitespace(contrasenia.charAt(i))) throw new DatosException("Espacio detectado.");
        }
    }

    public void validarNombre(String nombre) throws DatosException {
        if (nombre.equals("")) throw new DatosException("Apellido vacio.");
        for (int i = 0; i < nombre.length(); i++) {
            if(!Character.isLetter(nombre.charAt(i)) || Character.isDigit(nombre.charAt(i)))
                if(!Character.isWhitespace(nombre.charAt(i))) throw new DatosException("Has introducido un valor que no es letra.");
        }
    }

    public void validarApellido(String apellido) throws DatosException {
        if (apellido.equals("")) throw new DatosException("Apellido vacio.");
        for (int i = 0; i < apellido.length(); i++) {
            if(!Character.isLetter(apellido.charAt(i)) || Character.isDigit(apellido.charAt(i)))
                if(!Character.isWhitespace(apellido.charAt(i))) throw new DatosException("Has introducido un valor que no es letra.");
        }
    }

    public void validarNumeroTelefono(String numeroTelefono) throws DatosException {
        boolean noNumeroDetectado = false;
        if (numeroTelefono.equals(""))
            throw new DatosException("Numero  vacio."); //Si el número está vacío mandaremos una excepción
        for (int i = 0; i < numeroTelefono.length(); i++) {
            if (!Character.isDigit(numeroTelefono.charAt(i))) noNumeroDetectado = true;
        }
        if (noNumeroDetectado) throw new DatosException("Se ha introducido un carácter no numérico.");
        if (numeroTelefono.length() != 9) throw new DatosException("Debes introducir 9 dígitos.");
    }

    //Métodos específicos del propietario

    public void validarSoloNumero(String cadena) throws DatosException {
        boolean noNumeroDetectado = false;
        if (cadena.equals("")) throw new DatosException("Numero vacío."); //Si el número está vacío mandaremos una excepción
        if(cadena.length() > 8) throw new DatosException("No se permite introducir más de 8 dígitos"); //Si el número es mayor a 99999999 mandamos una excepción
        for (int i = 0; i < cadena.length(); i++) {
            if (!Character.isDigit(cadena.charAt(i))) noNumeroDetectado = true;
        }
        if (noNumeroDetectado) throw new DatosException("Se ha introducido un carácter no numérico.");
    }

    public void validarPrecioNoche(String precioNoche) throws DatosException {
        boolean noNumeroDetectado = false;
        int totalPuntos = 0;
        if (precioNoche.equals(""))
            throw new DatosException("Numero vacio."); //Si el número está vacío mandaremos una excepción
        for (int i = 0; i < precioNoche.length(); i++) {
            if (!Character.isDigit(precioNoche.charAt(i))) {
                if (precioNoche.charAt(i) == '.') totalPuntos++;
                else noNumeroDetectado = true;
            }
            if (Character.isWhitespace(precioNoche.charAt(i))) throw new DatosException("Espacio detectado.");
        }
        if (noNumeroDetectado) throw new DatosException("Se ha introducido un carácter no numérico.");
        if (totalPuntos != 0 && totalPuntos != 1) throw new DatosException("No puedes poner más de un punto.");
    }

    public void validarLocalidad(String localidad) throws DatosException {
        if (localidad.equals("")) throw new DatosException("Localidad vacía.");
        for (int i = 0; i < localidad.length(); i++) {
            if(!Character.isLetter(localidad.charAt(i)) || Character.isDigit(localidad.charAt(i))) System.out.println("Has introducido un valor que no es letra.");
        }
    }

    public void validarDireccion(String direccion) throws DatosException {
        if (direccion.equals("")) throw new DatosException("Dirección vacía.");
    }

    public void validarFecha(String fecha) throws DatosException {
        int valorDato = 0;
        int incremento = 0;
        int cifra = 0;
        int contadorBarras = 0;
        int dias = 0, mes = 0, anio = 0;
        if (fecha.length() < 8) throw new DatosException("La fecha es demasiado corta.");
        if (fecha.length() > 10) throw new DatosException("La fecha es demasiado larga.");
        for (int i = fecha.length() - 1; i >= 0; i--) {
            if (Character.isDigit(fecha.charAt(i))) {
                cifra = Character.getNumericValue(fecha.charAt(i));
                valorDato += cifra * Math.pow(10, incremento);
                incremento++;
                if (i == 0) {
                    if (valorDato < 1 || valorDato > 31) throw new DatosException("El día debe estar comprendido entre 1 y 31.");
                    else dias = valorDato;
                }
            } else {
                if(fecha.charAt(0) == '/') throw new DatosException("Se ha omitido un dato o introducido un valor no válido.");
                if (fecha.charAt(i) == '/') {
                    contadorBarras++;
                    if (valorDato == 0) throw new DatosException("Se ha omitido un dato o introducido un valor no válido.");
                    if (contadorBarras == 1 || contadorBarras == 2) {
                        if (contadorBarras == 1) {
                            if (valorDato < 1000 || valorDato > 9999) throw new DatosException("El año debe estar comprendido entre 999 y 9999.");
                            else  anio = valorDato;
                        }
                        if (contadorBarras == 2) {
                            if (valorDato < 1 || valorDato > 12) throw new DatosException("El mes debe estar comprendido entre 1 y 12.");
                            else mes = valorDato;
                        }
                        valorDato = 0;
                        incremento = 0;
                    } else throw new DatosException("Número de / incorrecto.");
                } else throw new DatosException("Se ha introducido un valor no numérico.");
            }
        }
        switch (mes){
            case 1, 3, 5, 7, 8, 10, 12 ->{
                if (dias == 31) throw new DatosException("El mes no se corresonde con los días y viceversa.");
            }
            case 4, 6, 9, 11 ->{
                if (dias > 30) throw new DatosException("El mes no se corresonde con los días y viceversa.");
            }
            case 2 ->{
                if(anio % 400 == 0 || ((anio % 4 == 0) && (anio % 100 != 0))){ //Calculamos si el año es bisiesto
                    if(dias > 29) throw new DatosException("El mes de Febrero como máximo tiene 29 días en años bisiestos.");
                }else
                if(dias > 28) throw new DatosException("El mes de Febrero como máximo tiene 28 días en años no bisiestos.");
            }
        }
    }
    public void validarFecha(Date fechaInicio, Date fechaFin) throws DatosException { //Comprobamos que la fecha fin sea posterior a fecha inicio
        if (fechaFin.before(fechaInicio)) throw new DatosException("Fecha Fin es anterior a fecha Inicio.");
    }

}

