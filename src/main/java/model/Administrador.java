package model;

public class Administrador {
    // Atributos
    private String email;
    private String pass;
    private String nombre;

    // Constructor
    public Administrador(String email, String pass, String nombre) {
        this.email = email;
        this.pass = pass;
        this.nombre = nombre;
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

    // Otros métodos
    public boolean login(String email, String pass) {
        if (this.email.equals(email) && this.pass.equals(pass)) return true;
        return false;
    }

    // toString
    @Override
    public String toString() {
        return "======= ADMINISTRADOR =======" +
                "\nEmail = " + email +
                "\nPass = " + pass +
                "\nNombre = " + nombre;
    }
}