package model;

import utils.Datos;

import java.util.Date;

public class Vivienda {
    // Atributos
    private int id;
    private int tipoVivienda;
    private String localidad;
    private String direccion;
    private int huesped;
    private double precioNoche;
    private Date fechaInicio;
    private Date fechaFin;
    private int numeroReservas = 0;

    private int idReserva1 = -1;
    private int idReserva2 = -1;
    private int idReserva3 = -1;
    private int idReserva4 = -1;

    // Constructores
    public Vivienda(int idVivienda, String localidad, String direccion, int huesped, double precioNoche, int tipoVivienda) {
        this.id = idVivienda;
        this.localidad = localidad.toUpperCase();
        this.direccion = direccion;
        this.huesped = huesped;
        this.precioNoche = precioNoche;
        this.tipoVivienda = tipoVivienda;
    }
    public Vivienda() {

    }

    //Getter and Setter
    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
    public int getTipoVivienda() {
        return tipoVivienda;
    }
    public void setTipoVivienda(int tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public int getHuesped() {
        return huesped;
    }
    public void setHuesped(int huesped) {
        this.huesped = huesped;
    }
    public double getPrecioNoche() {
        return precioNoche;
    }
    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public int getNumeroReservas() {
        return numeroReservas;
    }
    public void setNumeroReservas(int numeroReservas) {
        this.numeroReservas = numeroReservas;
    }

    public int getIdReserva1() {
        return idReserva1;
    }

    public int getIdReserva2() {
        return idReserva2;
    }

    public int getIdReserva3() {
        return idReserva3;
    }

    public int getIdReserva4() {
        return idReserva4;
    }

    // Métodos
    public static String tipoViviendaString(int tipoVivienda) {
        if (tipoVivienda == 1) return "Chalet";
        if (tipoVivienda == 2) return "Apartamento";
        if (tipoVivienda == 3) return "Bajo";
        else return "Estudio";
    }

    public boolean cumplirTodosRequisitos(String ciudad,Date fechaInicio, Date fechaFin, int huespedes, Datos datos){
        if (!cumplirRequisitoLocalidad(ciudad)) return false;
        if (!cumplirRequisitoFechaNoDisponible(fechaInicio,fechaFin)) return false;
        if (!cumplirRequisitoOtrasReservas(fechaInicio, fechaFin, datos)) return false;
        if (!cumplirRequisitoHuespedes(huespedes)) return false;
        return true;
    }

    private boolean cumplirRequisitoOtrasReservas(Date fechaInicio, Date fechaFin, Datos datos) {
        if(idReserva1 != -1){
            if (!cumplirRequisitoFechaInicio(fechaInicio, fechaFin, datos.buscarRerserva(idReserva1).getFechaEntrada()) &&
                    !cumplirRequisitoFechaFin(fechaInicio,fechaFin, datos.buscarRerserva(idReserva1).getFechaSalida())) return false;
        }
        if(idReserva2 != -1){
            if (!cumplirRequisitoFechaInicio(fechaInicio, fechaFin, datos.buscarRerserva(idReserva2).getFechaEntrada()) &&
                    !cumplirRequisitoFechaFin(fechaInicio,fechaFin, datos.buscarRerserva(idReserva2).getFechaSalida())) return false;
        }
        if(idReserva3 != -1){
            if (!cumplirRequisitoFechaInicio(fechaInicio, fechaFin, datos.buscarRerserva(idReserva3).getFechaEntrada()) &&
                    !cumplirRequisitoFechaFin(fechaInicio,fechaFin, datos.buscarRerserva(idReserva3).getFechaSalida())) return false;
        }
        if(idReserva4 != -1){
            if (!cumplirRequisitoFechaInicio(fechaInicio, fechaFin, datos.buscarRerserva(idReserva4).getFechaEntrada()) &&
                    !cumplirRequisitoFechaFin(fechaInicio,fechaFin, datos.buscarRerserva(idReserva4).getFechaSalida())) return false;
        }
        return true;
    }

    private boolean cumplirRequisitoLocalidad(String ciudad){
        if (this.localidad.equalsIgnoreCase(ciudad)) return true;
        return false;
    }

    private  boolean cumplirRequisitoFechaNoDisponible(Date fechaInicio, Date fechaFin){
        if (this.fechaInicio == null) return true;
        else{
            if (cumplirRequisitoFechaInicio(fechaInicio, fechaFin, this.fechaInicio) || cumplirRequisitoFechaFin(fechaInicio,fechaFin, this.fechaFin)) return true;
            return false;
        }
    }

    private boolean cumplirRequisitoFechaFin(Date fechaInicio, Date fechaFin, Date fechaFinCriterio){
        if(fechaInicio.after(fechaFinCriterio) && fechaFin.after(fechaFinCriterio)) return true;
        return false;
    }

    private boolean cumplirRequisitoFechaInicio(Date fechaInicio, Date fechaFin, Date fechaInicioCriterio){
        if(fechaInicio.before(fechaInicioCriterio) && fechaFin.before(fechaInicioCriterio)) return true;
        return false;
    }

    private boolean cumplirRequisitoHuespedes(int huespedes){
        if (this.huesped >= huespedes) return true;
        return false;
    }

    public void borrarFechaNoDisponibilidad() {
        this.fechaInicio = null;
        this.fechaFin = null;
    }

    public void guardarIdReserva(int idReserva){
        if(idReserva1 == -1) idReserva1 = idReserva;
        else if(idReserva2 == -1) idReserva2 = idReserva;
        else if(idReserva3 == -1) idReserva3 = idReserva;
        else if(idReserva4 == -1) idReserva4 = idReserva;
    }
    public boolean coincideIdReserva(int id){
        if (idReserva1 != -1) {
            if (idReserva1 == id) return true;
        } else if (idReserva2 != -1) {
            if (idReserva2 == id) return true;
        } else if (idReserva3 != -1) {
            if (idReserva3 == id) return true;
        } else if (idReserva4 != -1) {
            if (idReserva4 == id) return true;
        }
        return false;
    }

    public void borrarReserva(int id){
        if (idReserva1 != -1 && idReserva1 == id) {
            idReserva1 = -1;
        } else if (idReserva2 != -1 && idReserva2 == id) {
            idReserva2 = -1;
        } else if (idReserva3 != -1 && idReserva3 == id) {
            idReserva3 = -1;
        } else {
            if (idReserva4 == id) idReserva4 = -1;
        }
    }


    //Métodos
    @Override
    public String toString() {
        return "====== VIVIENDA CON ID: " + id + " ======" +
                "\nVivienda: " + tipoViviendaString(tipoVivienda) + " en " + localidad +
                "\nDireccion: " + direccion +
                "\nNúmero de huésped máximo: " + huesped +
                "\nPrecio por noche: " + precioNoche + " euros";
    }


}