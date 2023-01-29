package utils;

import model.*;

import java.security.SecureRandom;
import java.util.Date;

public class Datos {
    // Administrador por defecto
    private  Administrador administrador = new Administrador("admin@gmail.com", "administra", "Juan");
    
    // Usuario por defecto
    private  Usuario user1;
    
    private  Usuario user2;

    private  Propietario propietario1;
    private  Propietario propietario2;
    private  Reserva reserva;
    public  int totalPropietarios = 0;
    private  int totalUsuarios = 0;
    private int contadorViviendas = 0;
    private  int contadorReservas = 0;
    private int contadorFacturas = 0;
    private  String idFacturas = null; //Como no podemos hacer arrasys podemos hacer nuevos id de facturas si no se repiten en este String
    private String idResultadoAlojamiento;
    private String numeroConfirmacion;

    //Reservas no pueden estar aquí pero lo pongo de prueba
    private int idUltimaReserva = -1;
    private Reserva reserva1 = null;
    private Reserva reserva2 = null;
    private Reserva reserva3 = null;
    private Reserva reserva4 = null;
    
    // Getters and Setters


    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Usuario getUser1() {
        return user1;
    }

    public void setUser1(Usuario user1) {
        this.user1 = user1;
    }

    public Usuario getUser2() {
        return user2;
    }

    public void setUser2(Usuario user2) {
        this.user2 = user2;
    }

    public Propietario getPropietario1() {
        return propietario1;
    }

    public void setPropietario1(Propietario propietario1) {
        this.propietario1 = propietario1;
    }

    public Propietario getPropietario2() {
        return propietario2;
    }

    public void setPropietario2(Propietario propietario2) {
        this.propietario2 = propietario2;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getContadorReservas() {
        return contadorReservas;
    }

    public void setContadorReservas(int contadorReservas) {
        this.contadorReservas = contadorReservas;
    }

    public int getContadorFacturas() {
        return contadorFacturas;
    }

    public void setContadorFacturas(int contadorFacturas) {
        this.contadorFacturas = contadorFacturas;
    }

    public String getIdFacturas() {
        return idFacturas;
    }

    public void setIdFacturas(String idFacturas) {
        this.idFacturas = idFacturas;
    }

    public String getIdResultadoAlojamiento() {
        return idResultadoAlojamiento;
    }

    public void setIdResultadoAlojamiento(String idResultadoAlojamiento) {
        this.idResultadoAlojamiento = idResultadoAlojamiento;
    }

    public String getNumeroConfirmacion() {
        return numeroConfirmacion;
    }

    public void setNumeroConfirmacion(String numeroConfirmacion) {
        this.numeroConfirmacion = numeroConfirmacion;
    }

    public int getIdUltimaReserva() {
        return idUltimaReserva;
    }

    public void setIdUltimaReserva(int idUltimaReserva) {
        this.idUltimaReserva = idUltimaReserva;
    }

    public Reserva getReserva1() {
        return reserva1;
    }

    public void setReserva1(Reserva reserva1) {
        this.reserva1 = reserva1;
    }

    public Reserva getReserva2() {
        return reserva2;
    }

    public void setReserva2(Reserva reserva2) {
        this.reserva2 = reserva2;
    }

    public Reserva getReserva3() {
        return reserva3;
    }

    public void setReserva3(Reserva reserva3) {
        this.reserva3 = reserva3;
    }

    public Reserva getReserva4() {
        return reserva4;
    }

    public void setReserva4(Reserva reserva4) {
        this.reserva4 = reserva4;
    }

    public String generarNombreFactura() {
        return "factura" + getContadorFacturas() + ".pdf";
    }

    public void aniadirRutaPropietario(int idUltimaReserva, String s) {
        if (propietario1 != null &&  propietario1.getVivienda() != null && propietario1.getVivienda().getId() == idUltimaReserva) {
            propietario1.setRutaUltimaFactura(s);
        }
        else if(propietario2 != null && propietario2.getVivienda() != null &&  propietario2.getVivienda().getId() == idUltimaReserva){
            propietario2.setRutaUltimaFactura(s);
        }
    }

    public int getTotalPropietarios() {
        return totalPropietarios;
    }

    public void setTotalPropietarios(int totalPropietarios) {
        this.totalPropietarios = totalPropietarios;
    }
    public int getTotalUsuarios() {
        return Usuario.getNumeroUsuarios();
    }
    public void setTotalUsuarios(int totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }
    public int getContadorViviendas() {
        return contadorViviendas;
    }
    public void setContadorViviendas(int contadorViviendas) {
        this.contadorViviendas = contadorViviendas;
    }

    // Otros métodos
    
    /*public int comprobarID(int opcion) {
        String opcionLetra = String.valueOf(opcion);
        for(int i = 0; i < idResultadoAlojamiento.length(); i ++){
            if(idResultadoAlojamiento.substring(i,i+1).equals(opcionLetra)) return opcion;
        }
        return -1;
    }*/

    public int comprobarID(int opcion) {
        if(propietario1 != null && propietario1.getVivienda() != null && propietario1.getVivienda().getId() == opcion) return opcion;
        if(propietario2 != null && propietario2.getVivienda() != null && propietario2.getVivienda().getId() == opcion) return opcion;
        return -1;
    }

    public Vivienda buscarVivienda(int id){
        if(propietario1.getVivienda().getId() == id) return propietario1.getVivienda();
        return propietario2.getVivienda();
    }

    public void crearReserva(Usuario usuario, int idVivienda, Date fechaInicio, Date fechaFin, int huespedesTeclado) {
        int idReserva = generarIdReserva();
        if(getReserva1() == null){
            reserva1 = new Reserva(idReserva,usuario,buscarVivienda(idVivienda),fechaInicio,fechaFin,huespedesTeclado);
            idUltimaReserva = reserva1.getId();
        }else if(getReserva2() == null){
            reserva2 = new Reserva(idReserva,usuario,buscarVivienda(idVivienda),fechaInicio,fechaFin,huespedesTeclado);
            idUltimaReserva = reserva2.getId();
        }else if(getReserva3() == null){
            reserva3 = new Reserva(idReserva,usuario,buscarVivienda(idVivienda),fechaInicio,fechaFin,huespedesTeclado);
            idUltimaReserva = reserva3.getId();
        }else if(getReserva4() == null){
            reserva4 = new Reserva(idReserva,usuario,buscarVivienda(idVivienda),fechaInicio,fechaFin,huespedesTeclado);
            idUltimaReserva = reserva4.getId();
        }
    }

    public int generarIdReserva(){
        boolean validar;
        int numeroAleatorio;
        do{
            numeroAleatorio = numeroAleatorio = (int)(Math.random() * 100000) - 1 ;
            validar = true;
            if(reserva1 != null && reserva1.getId() == numeroAleatorio) validar = false;
            if(reserva2 != null && reserva2.getId() == numeroAleatorio) validar = false;
            if(reserva3 != null && reserva3.getId() == numeroAleatorio) validar = false;
            if(reserva4 != null && reserva4.getId() == numeroAleatorio) validar = false;
        }while (!validar);
        return numeroAleatorio;
    }

    public void borrarReserva(int id){ //Borra en propietario
        if(propietario1 != null && propietario1.getVivienda() != null && propietario1.getVivienda().coincideIdReserva(id)) propietario1.getVivienda().borrarReserva(id);
        else if (propietario2.getVivienda() != null && propietario2.getVivienda().coincideIdReserva(id)) propietario2.getVivienda().borrarReserva(id);
    }

    public void guardarIdRerserva(int idVivienda,int idReserva) {
        if(propietario1 != null && propietario1.getVivienda().getId() == idVivienda) propietario1.getVivienda().guardarIdReserva(idReserva);
        else propietario2.getVivienda().guardarIdReserva(idReserva);
    }

    // Creamos una copia del propietario sin vivienda
    public void registrarPropietarioSinVivienda(Propietario nuevoPropietario, Datos datos){
        if(totalPropietarios == 0){
            propietario1 = new Propietario(nuevoPropietario.getEmail(), nuevoPropietario.getNombreUsuario(), nuevoPropietario.getPass(),nuevoPropietario.getNombre(),
                    nuevoPropietario.getApellidos(), nuevoPropietario.getTelefono(),nuevoPropietario.isVisible());
            irMenuPropietario(propietario1, datos);
        }else{
            propietario2 = new Propietario(nuevoPropietario.getEmail(), nuevoPropietario.getNombreUsuario(), nuevoPropietario.getPass(),nuevoPropietario.getNombre(),
                    nuevoPropietario.getApellidos(), nuevoPropietario.getTelefono(),nuevoPropietario.isVisible());
            irMenuPropietario(propietario2, datos);
        }
        totalPropietarios++;
    }

    // Creamos una copia del propietario con vivienda
    /*public void propietarioPorDefecto(Propietario nuevoPropietario, Vivienda nuevaVivienda, Datos datos){
        contadorViviendas++;
            propietario1 =  new Propietario(nuevoPropietario.getEmail(),nuevoPropietario.getNombreUsuario(),nuevoPropietario.getPass(),nuevoPropietario.getNombre(),
                nuevoPropietario.getApellidos(),nuevoPropietario.getTelefono(),nuevoPropietario.isVisible(),nuevaVivienda);
        irMenuPropietario(propietario1, datos);
        totalPropietarios++;
    }*/

    public void registrarPropietarioConVivienda(Propietario nuevoPropietario, Vivienda nuevaVivienda, Datos datos){
        contadorViviendas++;
        if(totalPropietarios == 0){
            propietario1 = new Propietario(nuevoPropietario.getEmail(),nuevoPropietario.getNombreUsuario(),nuevoPropietario.getPass(),nuevoPropietario.getNombre(),
                    nuevoPropietario.getApellidos(),nuevoPropietario.getTelefono(),nuevoPropietario.isVisible(),nuevaVivienda);
            irMenuPropietario(propietario1, datos);
        }else{
            propietario2 = new Propietario(nuevoPropietario.getEmail(),nuevoPropietario.getNombreUsuario(),nuevoPropietario.getPass(),nuevoPropietario.getNombre(),
                    nuevoPropietario.getApellidos(),nuevoPropietario.getTelefono(),nuevoPropietario.isVisible(),nuevaVivienda);
            irMenuPropietario(propietario2, datos);
        }
        totalPropietarios++;
    }

    public boolean maximoUsuariosPermitidos(){
        if( totalUsuarios < 2) return false;
        return true;
    }

    public boolean maximoPropietariosPermitidos(){
        if( totalPropietarios < 2) return false;
        return true;
    }

    // Si el total usuarios creados es menor que dos, comprobamos que usuario está libre y se guarda
    public void registrarUsuario(Usuario nuevoUsuario, Datos datos){
        if(totalUsuarios == 0){
            user1 = new Usuario(nuevoUsuario.getEmail(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getPass(),nuevoUsuario.getNombre(),
                    nuevoUsuario.getApellidos(), nuevoUsuario.getTelefono(), nuevoUsuario.isVisible());
            irMenuUsuario(user1, datos);
        }else {
            user2 = new Usuario(nuevoUsuario.getEmail(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getPass(),nuevoUsuario.getNombre(),
                    nuevoUsuario.getApellidos(), nuevoUsuario.getTelefono(), nuevoUsuario.isVisible());
            irMenuUsuario(user2, datos);
        }
        totalUsuarios++;
    }

    public boolean validarEmail(String email){
        if (email.equals(administrador.getEmail())) return false;
        if(user1 != null && user1.getEmail().equals(email)) return false;
        if(user2 != null && user2.getEmail().equals(email)) return false;
        if(propietario1 != null && propietario1.getEmail().equals(email)) return false;
        if(propietario2 != null && propietario2.getEmail().equals(email)) return false;
        return true;
    }

    public boolean validarNombreUsuario(String nombreUsuario){
        if (nombreUsuario.equals(administrador.getEmail())) return false;
        if(user1 != null && user1.getNombreUsuario().equals(nombreUsuario)) return false;
        if(user2 != null && user2.getNombreUsuario().equals(nombreUsuario)) return false;
        if(propietario1 != null && propietario1.getNombreUsuario().equals(nombreUsuario)) return false;
        if(propietario2 != null && propietario2.getNombreUsuario().equals(nombreUsuario)) return false;
        return true;
    }

    public String mostrarUsuarios(){
        String listaUsuarios = "";
        if(user1 != null) listaUsuarios += (user1.getNombre()+" "+user1.getApellidos());
        if(user2 != null) listaUsuarios += (user2.getNombre()+" "+user2.getApellidos());
        return listaUsuarios;
    }

    public String mostrarPropietarios(){
        String listaPropietarios = "";
        if(propietario1 != null) listaPropietarios += propietario1.getNombre() + " " + propietario1.getApellidos();
        if(propietario2 != null) listaPropietarios += propietario2.getNombre() + " " + propietario2.getApellidos();
        return listaPropietarios;
    }

    public String mostrarViviendas(){
        String lista = "";
        //Comprobamos la existencia de los propietarios y si existen comprobamos si tienen viviendas
        if(propietario1 != null && propietario1.existenciaVivienda()) lista += "\n" + propietario1.mostrarVivienda();
        if(propietario2 != null && propietario2.existenciaVivienda()) lista += "\n" + propietario2.mostrarVivienda();
        if (lista.equals("")) return "No hay viviendas creadas.";
        return lista;
    }

    public String mostrarTodasReservas(Administrador administrador) {
        String listaReservasTotales = "RESERVAS ENCONTRADAS: ";
        // Comprobamos que los propietarios no estén a null
        if(reserva1 != null) listaReservasTotales += "\n" + reserva1.toString();
        if(reserva2 != null) listaReservasTotales += "\n" + reserva2.toString();
        if(reserva3 != null) listaReservasTotales += "\n" + reserva3.toString();
        if(reserva4 != null) listaReservasTotales += "\n" + reserva1.toString();
        if (listaReservasTotales.equals("RESERVAS ENCONTRADAS: ")) return "No se han encontrado revervas";
        else return listaReservasTotales;
    }

    // Método que comprueba el email y contraseña con los datos del admin, usuario y propietario
    public boolean usuarioExistente(String email, String pass) {
        if(administrador != null && administrador.getEmail().equals(email) && administrador.getPass().equals(pass)) return true;
        if(user1 != null && user1.isVisible() && user1.getEmail().equals(email) && user1.getPass().equals(pass)) return true;
        if(user2 != null && user2.isVisible() && user2.getEmail().equals(email) && user2.getPass().equals(pass)) return true;
        if (propietario1 != null && propietario1.isVisible() && propietario1.getEmail().equals(email) && propietario1.getPass().equals(pass)) return true;
        return (propietario2 != null && propietario2.isVisible() && propietario2.getEmail().equals(email) && propietario2.getPass().equals(pass));
    }

    public void iniciarSesion(String email, String pass, Datos datos){
        if(administrador != null && administrador.getEmail().equals(email) && administrador.getPass().equals(pass)) irMenuAdmin(administrador,datos);
        if(user1 != null && user1.getEmail().equals(email) && user1.getPass().equals(pass) && user1.isVisible()) irMenuUsuario(user1, datos);
        if(user2 != null && user2.getEmail().equals(email) && user2.getPass().equals(pass) && user2.isVisible()) irMenuUsuario(user2, datos);
        if (propietario1 != null && propietario1.getEmail().equals(email) && propietario1.getPass().equals(pass) && propietario1.isVisible()) irMenuPropietario(propietario1, datos);
        if (propietario2 != null && propietario2.getEmail().equals(email) && propietario2.getPass().equals(pass) && propietario2.isVisible()) irMenuPropietario(propietario2, datos);
    }

    // Mostramos todas las viviendas que estén disponibles y cumplan con los requisitos
    public String buscarAlojamiento(String ciudad, Date fechaInicio, Date fechaFin, int huespedes, Datos datos) {
        String listaReservas = " ";
        idResultadoAlojamiento = " ";
        // Comprobamos que haya algún propietario creado
        if (totalPropietarios == 0) return "No hay viviendas en alquiler.";
        else {
            if (propietario1 != null && propietario1.getVivienda() != null && propietario1.getVivienda().cumplirTodosRequisitos(ciudad, fechaInicio, fechaFin, huespedes, datos)){
                idResultadoAlojamiento += propietario1.getVivienda().getId()+" ";
                listaReservas += propietario1.getVivienda().toString() + "\n";
            }
            if (propietario2 != null && propietario2.getVivienda() != null && propietario2.getVivienda().cumplirTodosRequisitos(ciudad, fechaInicio, fechaFin, huespedes, datos)){
                idResultadoAlojamiento += propietario2.getVivienda().getId()+" ";
                listaReservas += propietario2.getVivienda().toString() + "\n";
            }
        }
        if (listaReservas.equals(" ")) return "No se han encontrado viviendas con esos requisistos";
        return listaReservas;
    }

    private void irMenuAdmin(Administrador admin, Datos datos){ Menus.menuAdminstrador(admin, datos); }
    private void irMenuUsuario(Usuario user, Datos datos){ Menus.menuUsuario(user, datos); }
    private void irMenuPropietario(Propietario propietario, Datos datos){ Menus.menuPropietario(propietario, datos); }

    public String crearNumeroAleatorio(){
        int numeroAleatorio;
        do{
            numeroAleatorio = (int)(Math.random() * 100000) + 1 ;
            if(numeroAleatorio == 100000) numeroAleatorio --;
        }while (numeroAleatorio < 9999);
        return String.valueOf(numeroAleatorio);
    }

    public Reserva buscarRerserva(int idReserva){
        if(reserva1 != null && reserva1.getId() == idReserva) return reserva1;
        if(reserva2 != null && reserva2.getId() == idReserva) return reserva2;
        if(reserva3 != null && reserva3.getId() == idReserva) return reserva3;
        return reserva4;
    }

    public String generarIdFactura (){
        String idFactura;
        boolean validar;
        do {
            //final long onceDecimales = Long.parseLong("10000000000"); //Se parsea porque no nos deja poner 10^7 tal cual
            idFactura = String.valueOf((long) (Math.random() * Long.parseLong("10000000000")) - 1); //Va a salir numero con longitud maxima 10
            while (idFactura.length() < 10){
                String aux = idFactura;
                idFactura = "0" + aux;
            }
            validar = validarIdFactura(idFactura);
        }while (!validar);
        return idFactura;
    }

    private boolean validarIdFactura(String idFactura){
        if(getContadorFacturas() != 0){
            int contador = 0;
            for (int i = 0; i < contadorFacturas; i++){
                if(idFacturas.substring((contador * 10), ((contador * 10) + 9)).equals(idFactura)) return false;
                contador++;
            }
        }
        idFacturas += idFactura;
        contadorFacturas++;
        return true;
    }

    public Propietario buscarPropietario(int idVivienda){
        if (propietario1 != null && propietario1.getVivienda() != null && propietario1.getVivienda().getId() == idVivienda) return propietario1;
        else return propietario2;
    }

    // Método que realiza el inicio de sesión de un usuario en Telegram
    public boolean buscarUsuario(String contrasenia,int numeroTelefono) {
        if(user1 != null && user1.getTelefono() == numeroTelefono && user1.getPass().equals(contrasenia)) return true;
        return (user2 != null && user2.getTelefono() == numeroTelefono && user2.getPass().equals(contrasenia));
    }

    public Usuario aniadirIdChatTelegramUsuario(String contrasenia, int numeroTelefono, String idChatTelegram) {
        if(user1 != null && user1.getTelefono() == numeroTelefono && user1.getPass().equals(contrasenia)) {
            user1.setIdChatTelegram(idChatTelegram);
            return user1;
        }
        else {
            user2.setIdChatTelegram(idChatTelegram);
            return user2;
        }
    }

    // Método que realiza el inicio de sesión de un propietario en Telegram
    public boolean buscarPropietario(String contrasenia,int numeroTelefono) {
        if(propietario1 != null && propietario1.getTelefono() == numeroTelefono && propietario1.getPass().equals(contrasenia)) return true;
        return (propietario2 != null && propietario2.getTelefono() == numeroTelefono && propietario2.getPass().equals(contrasenia));
    }

    public Propietario aniadirIdChatTelegramPropietario(String contrasenia, int numeroTelefono, String idChatTelegram) {
        if(propietario1 != null && propietario1.getTelefono() == numeroTelefono && propietario1.getPass().equals(contrasenia)) {
            propietario1.setIdChatTelegram(idChatTelegram);
            return propietario1;
        }
        else {
            propietario2.setIdChatTelegram(idChatTelegram);
            return propietario2;
        }
    }

    public void registrarPropietarioConViviendaPorDefecto(Propietario propietario, Vivienda vivienda) {
        propietario1 = new Propietario(propietario.getEmail(),propietario.getNombreUsuario(),propietario.getPass(),propietario.getNombre(),
                propietario.getApellidos(),propietario.getTelefono(),propietario.isVisible(),vivienda);
        totalPropietarios++;
    }

    public void registrarUsuarioDefecto(Usuario usuario) {
        user1 = new Usuario(usuario.getEmail(), usuario.getNombreUsuario(), usuario.getPass(),usuario.getNombre(),
                usuario.getApellidos(), usuario.getTelefono(), usuario.isVisible());
        totalUsuarios++;
    }

    public boolean usuarioEncontrado(Long toString) {
        String chatId = String.valueOf(toString);
        if (user1 != null && chatId.equals(user1.getIdChatTelegram())) return true;
        return (user2 != null && chatId.equals(user2.getIdChatTelegram()));
    }

    public Usuario obtenerUsuario (Long toString){
        String chatId = String.valueOf(toString);
        if (user1 != null && chatId.equals(user1.getIdChatTelegram())) return user1;
        return user2;
    }

    public boolean propietarioEncontrado(Long toString) {
        String chatId = String.valueOf(toString);
        if (propietario1 != null && chatId.equals(propietario1.getIdChatTelegram())) return true;
        return (propietario2 != null && chatId.equals(propietario2.getIdChatTelegram()));
    }

    public Propietario obtenerPropietario (Long toString){
        String chatId = String.valueOf(toString);
        if (propietario1 != null && chatId.equals(propietario1.getIdChatTelegram())) return propietario1;
        return propietario2;
    }

    public int generaIdVivienda(){
        boolean validar;
        int numeroAleatorio;
        do{
            numeroAleatorio = numeroAleatorio = (int)(Math.random() * 100000) - 1 ;
            validar = true;
            if(propietario1 != null && propietario1.getVivienda() != null && propietario1.getVivienda().getId() == numeroAleatorio) validar = false;
            if(propietario2 != null && propietario2.getVivienda() != null && propietario2.getVivienda().getId() == numeroAleatorio) validar = false;
        }while (!validar);
        return numeroAleatorio;
    }

    public boolean existenciaUsuarioPropietarioNoConfirmado(String email, String pass) {
        if(user1 != null && !user1.isVisible() && user1.getEmail().equals(email) && user1.getPass().equals(pass)) return true;
        if(user2 != null && !user2.isVisible() && user2.getEmail().equals(email) && user2.getPass().equals(pass)) return true;
        if (propietario1 != null && !propietario1.isVisible() && propietario1.getEmail().equals(email) && propietario1.getPass().equals(pass)) return true;
        return (propietario2 != null && !propietario2.isVisible() && propietario2.getEmail().equals(email) && propietario2.getPass().equals(pass));
    }

    public void cambiarVisibilidad(String emailTeclado, String passTeclado,Datos datos) {
        if(user1 != null &&! user1.isVisible() && user1.getEmail().equals(emailTeclado) && user1.getPass().equals(passTeclado)){
            user1.setVisible(true);
        }
        if(user2 != null && !user2.isVisible() && user2.getEmail().equals(emailTeclado) && user2.getPass().equals(passTeclado)) {
            user2.setVisible(true);
        }
        if (propietario1 != null && !propietario1.isVisible() && propietario1.getEmail().equals(emailTeclado) && propietario1.getPass().equals(passTeclado)){
            propietario1.setVisible(true);
        }
        if(propietario2 != null && !propietario2.isVisible() && propietario2.getEmail().equals(emailTeclado) && propietario2.getPass().equals(passTeclado)){
            propietario2.setVisible(true);
        }
    }
}