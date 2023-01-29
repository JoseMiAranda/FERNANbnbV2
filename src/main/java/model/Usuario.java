package model;

import utils.Datos;
import utils.Email;

public class Usuario {
    // Atributos

    private String email;
    private String pass;

    private String rutaUltimaFactura = "";

    private String nombreUsuario;
    private String nombre;
    private String apellidos;
    private int telefono;
    private int  idReserva1 = -1;
    private int idReserva2 = -1;

    private boolean visible;

    private int numReservas = 0;
    private static int numeroUsuarios = 0;

    //Atributos de Telegram
    private String idChatTelegram = "";
    private boolean registradoTelegram = false;


    // Constructor
    public Usuario(String email, String nombreUsuario, String pass, String nombre, String apellidos, int telefono, boolean visible) {
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.pass = pass;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.visible = visible;
        numeroUsuarios++;
    }

    // Getter and Setter
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
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdReserva1() {return idReserva1;}

    public void setIdReserva1(int idReserva1) {this.idReserva1 = idReserva1;}

    public int getIdReserva2() {return idReserva2;}

    public void setIdReserva2(int idReserva2) {this.idReserva2 = idReserva2;}

    public boolean isRegistradoTelegram() {
        return registradoTelegram;
    }

    public void setRegistradoTelegram(boolean registradoTelegram) {
        this.registradoTelegram = registradoTelegram;
    }

    public String getRutaUltimaFactura() {
        return rutaUltimaFactura;
    }

    public void setRutaUltimaFactura(String rutaUltimaFactura) {
        this.rutaUltimaFactura = rutaUltimaFactura;
    }

    public  String getIdChatTelegram() {
        return idChatTelegram;
    }

    public void setIdChatTelegram(String idChatTelegram) {
        this.idChatTelegram = idChatTelegram;
    }

    public static int getNumeroUsuarios() {
        return numeroUsuarios;
    }
    public static void setNumeroUsuarios(int numeroUsuarios) {
        Usuario.numeroUsuarios = numeroUsuarios;
    }
    public int getNumReservas() {
        return numReservas;
    }
    public void setNumReservas(int numReservas) {
        this.numReservas = numReservas;
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

    // Métodos
    public void crearReserva( int idReserva){
        numReservas++;
        if(idReserva1 == -1) idReserva1 = idReserva;
        else idReserva2 = idReserva;
    }

    public String verReserva(Datos datos) {
        String listaReservas = "";
        if(idReserva1 != -1) listaReservas += datos.buscarRerserva(idReserva1).toString() + "\n";
        if(idReserva2 != -1) listaReservas += datos.buscarRerserva(idReserva1).toString() + "\n";
        return listaReservas;
    }

    public boolean comprobarReserva(int opcion) {
        if(idReserva1 != -1 && idReserva1 == opcion) return true;
        if(idReserva2 != -1 && idReserva2 == opcion) return true;
        return false;
    }
    public void borrarReserva(int opcion) {
        numReservas--;
        if(idReserva1 != -1 && idReserva1 == opcion) idReserva1 = -1;
        if(idReserva2 != -1 && idReserva2 == opcion) idReserva2 = -1;
    }



    @Override
    public String toString() {
        return "========== USUARIO ==========" +
                "\nEmail = " + email +
                "\nPass = " + pass +
                "\nNombre = " + nombre +
                "\nApellidos = " + apellidos +
                "\nTeléfono = " + telefono;
    }

}
