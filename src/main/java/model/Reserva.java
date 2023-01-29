package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
    // Atributos
    private int id;
    private Usuario usuario;
    private Vivienda vivienda;
    private int noches;
    private Date fechaEntrada;
    private Date fechaSalida;
    private int huesped;

    // Constructor
    public Reserva(int id,Usuario usuario, Vivienda vivienda, Date fechaEntrada, Date fechaSalida, int huesped) {
        this.id = id;
        this.usuario = usuario;
        this.vivienda = vivienda;
        this.noches = (int)((long) Math.floor(fechaSalida.getTime() / (1000 * 60 * 60 * 24)) - (long) Math.floor(fechaEntrada.getTime() / (1000 * 60 * 60 * 24)));
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.huesped = huesped;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Vivienda getVivienda() {
        return vivienda;
    }
    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }
    public int getNoches() {
        return noches;
    }
    public void setNoches(int noches) {
        this.noches = noches;
    }
    public Date getFechaEntrada() {
        return fechaEntrada;
    }
    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    public Date getFechaSalida() {
        return fechaSalida;
    }
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public int getHuesped() {
        return huesped;
    }
    public void setHuesped(int huesped) {
        this.huesped = huesped;
    }

    // Métodos
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return "=== RESERVA CON ID: " + id + " ====" +
                "\nUsuario: " + usuario.getNombre() + " " + usuario.getApellidos() +
                "\nVivienda: " + Vivienda.tipoViviendaString(vivienda.getTipoVivienda()) + " en " + vivienda.getLocalidad() + " (ID: " + vivienda.getId() + ")" +
                "\nNoches: " + noches +
                "\nEntrada: " + formato.format(fechaEntrada) +
                "\nSalida: " + formato.format(fechaSalida) +
                "\nHuesped: " + huesped +
                "\nPrecio por noche: " + vivienda.getPrecioNoche() + " euros; Precio total: " + (vivienda.getPrecioNoche() * noches) + " euros";
    }
}