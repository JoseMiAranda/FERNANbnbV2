package model;

import utils.Datos;

import java.util.Date;

public class Propietario {
    // Atributos
    private int id;
    private String rutaUltimaFactura = "";
    private  int contador = 0;
    private String email;
    private String pass;
    private String nombreUsuario;
    private String nombre;
    private String apellidos;
    private int telefono;
    private boolean visible;
    private Vivienda vivienda;
    private int totalViviendas = 0;
    private  int numeroPropietarios = 0;

    //Atributos de Telegram
    private String idChatTelegram = "";
    private boolean registradoTelegram = false;

    // Constructores
    public Propietario(String nuevoEmail,String nombreUsuario, String nuevoPass, String nuevoNombre, String nuevoApellidos, int telefono, boolean visible, Vivienda nuevaVivienda) {
        contador++;
        this.id = contador;
        this.email = nuevoEmail;
        this.nombreUsuario = nombreUsuario;
        this.pass = nuevoPass;
        this.nombre = nuevoNombre;
        this.apellidos = nuevoApellidos;
        this.telefono = telefono;
        this.vivienda = new Vivienda(nuevaVivienda.getId(), nuevaVivienda.getLocalidad(), nuevaVivienda.getDireccion(), nuevaVivienda.getHuesped(), nuevaVivienda.getPrecioNoche(), nuevaVivienda.getTipoVivienda());
        this.visible = visible;
        totalViviendas ++;
        numeroPropietarios ++;
    }

    public Propietario(String nuevoEmail, String nombreUsuario, String nuevoPass, String nuevoNombre, String nuevoApellidos, int telefono, boolean visible) {
        contador++;
        this.id = contador;
        this.email = nuevoEmail;
        this.nombreUsuario = nombreUsuario;
        this.pass = nuevoPass;
        this.nombre = nuevoNombre;
        this.apellidos = nuevoApellidos;
        this.telefono = telefono;
        this.visible = visible;
        numeroPropietarios ++;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }
    public String mostrarVivienda() { return vivienda.toString(); }
    public void cambiarHuespedes(int nuevoHuespedes){ vivienda.setHuesped(nuevoHuespedes); }
    public void cambiarPrecioNoche(double nuevoPrecio){ vivienda.setPrecioNoche(nuevoPrecio); }
    public int getTotalViviendas(){ return totalViviendas; }
    public void setVivienda(Vivienda vivienda) { this.vivienda = vivienda; }
    public Vivienda getVivienda() { return vivienda; }

    public String getRutaUltimaFactura() {
        return rutaUltimaFactura;
    }

    public boolean isRegistradoTelegram() {
        return registradoTelegram;
    }

    public void setRegistradoTelegram(boolean registradoTelegram) {
        this.registradoTelegram = registradoTelegram;
    }

    public void setRutaUltimaFactura(String rutaUltimaFactura) {
        this.rutaUltimaFactura = rutaUltimaFactura;
    }

    public int getNumeroPropietarios() {
        return numeroPropietarios;
    }

    public void setNumeroPropietarios(int numeroPropietarios) {
        this.numeroPropietarios = numeroPropietarios;
    }

    public String getIdChatTelegram() {
        return idChatTelegram;
    }

    public void setIdChatTelegram(String idChatTelegram) {
        this.idChatTelegram = idChatTelegram;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    // Otros métodos
    public boolean login(String email, String pass) {
        if (this.email.equals(email) && this.pass.equals(pass)) return true;
        return false;
    }

    // Mostramos las reservas de dicho propietario
    public String verReservas(Datos datos) {
        String listaReservas = "";
        if (getVivienda() != null){
            if(getVivienda().getIdReserva1() != -1) listaReservas += datos.buscarRerserva(vivienda.getIdReserva1()).toString() + "\n";
            if(getVivienda().getIdReserva2() != -1) listaReservas += datos.buscarRerserva(vivienda.getIdReserva2()).toString() + "\n";
            if(getVivienda().getIdReserva3() != -1) listaReservas += datos.buscarRerserva(vivienda.getIdReserva3()).toString() + "\n";
            if(getVivienda().getIdReserva4() != -1) listaReservas += datos.buscarRerserva(vivienda.getIdReserva4()).toString() + "\n";
            if(listaReservas.equals("")) return "No se han realizado reservas.";
            else return listaReservas;
        }
        return "No hay viviendas en alquiler.";
    }

    // Cambiar método para simular que en las fechas introducidas no esté disponible la vivienda
    public void cambiarEstadoVivienda(Date fechaInicio, Date fechaFin){
        vivienda.setFechaInicio(fechaInicio);
        vivienda.setFechaFin(fechaFin);
    }

    public boolean existenciaVivienda(){
        if (vivienda == null) return false;
        return true;
    }

    public void borrarVivienda(){
        if(totalViviendas == 0) System.out.println("No hay viviendas creadas para borrar");
        else {
            vivienda = null;
            totalViviendas--;
        }
    }

    public void crearVivienda (Vivienda vivienda,int idVivienda){
        this.vivienda = new Vivienda(idVivienda,vivienda.getLocalidad(),vivienda.getDireccion(), vivienda.getHuesped(), vivienda.getPrecioNoche(), vivienda.getTipoVivienda());
        totalViviendas++;
    }

    @Override
    public String toString() {
        return "========== PROPIETARIO ==========" +
                "\nEmail = " + email +
                "\nPass = " + pass +
                "\nNombre = " + nombre +
                "\nApellidos = " + apellidos +
                "\nTeléfono = " + telefono +
                "\n\n" + (vivienda != null ? vivienda.toString() : "No tienes viviendas en alquiler.");
    }
}