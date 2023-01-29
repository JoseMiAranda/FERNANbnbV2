package utils;

public class MensajesMenu {
    public static String menuInicio() {
        return """                                        
                                
                ================== FERNANbnb =================
                |                                            |
                |                    ___                     |
                |                   /   \\                    |
                |                  /  _  \\                   |
                |                 /  \\ /  \\                  |
                |                /    /    \\                 |
                |                \\___/ \\___/                 |
                |                                            |
                ================== FERNANbnb =================
                                    
                =============== MENÚ PRINCIPAL ===============
                |                                            |
                |  1. Iniciar sesión.                        |
                |  2. Registrar nuevo usuario o propietario. |
                |  3. Confirmar usuario / propietario        |
                |  4. Salir.                                 |
                |                                            |
                ==============================================
                """;
    }

    public static String menuRegistro() {
        return """
                                                
                ================== REGISTRO ==================
                |                                            |
                |         ¿Cómo desea registrarse?           |
                |                                            |
                |    1. Usuario.                             |
                |    2. Propietario.                         |
                |    3. Salir.                               |
                |                                            |
                ==============================================
                """;
    }

    public static String menuAdministrador(String nombreAdministrador) {
        return "\n" +
                "==================== PERFIL DE ADMINISTRADOR ====================\n" +
                "\n" +
                " Bienvenio/a " + nombreAdministrador + ", perfil de administración\n" +
                "\n" +
                "========================= MENÚ PRINCIPAL ========================\n" +
                "\n" +
                "  1.- Ver todas las viviendas en alquiler.\n" +
                "  2.- Ver todos los usuarios del sistema.\n" +
                "  3.- Ver todas las reservas de viviendas.\n" +
                "  4.- Ver mi perfil.\n" +
                "  5.- Modificar mi perfil.\n" +
                "  6.- Cerrar sesión.\n" +
                "\n" +
                "=================================================================\n";
    }

    public static String modificarAdministrador() {
        return """
                                
                =========== MODIFICAR PERFIL ===========
                |                                      |
                |    ¿Qué apartado desea modificar?    |
                |                                      |
                |  1.- Nombre                          |
                |  2.- Contraseña                      |
                |  3.- Salir                           |
                |                                      |
                ========================================
                """;
    }

    public static String menuUsuario(String nombreUsuario, int numeroReservas) {
        return "\n" +
                "============================== PERFIL DE USUARIO ==============================\n" +
                "\n" +
                " Bienvenio/a " + nombreUsuario + ", busque un alojamiento para sus próximas vacaciones\n" +
                "                               Tienes " + numeroReservas + " reservas\n" +
                "\n" +
                "================================ MENÚ PRINCIPAL ===============================\n" +
                "\n" +
                "    1.- Búsqueda de alojamientos.\n" +
                "    2.- Ver mis reservas.\n" +
                "    3.- Borrar mis reservas.\n" +
                "    4.- Ver mi perfil.\n" +
                "    5.- Modificar mi perfil.\n" +
                "    6.- Cerrar sesión.\n" +
                "\n" +
                "===============================================================================\n";
    }

    public static String menuTiposViviendas() {

        return """
                                                        
                ======== TIPO DE VIVIENDA ========
                |                                |
                |  1.- Chalet                    |
                |  2.- Apartamento               |
                |  3.- Bajo                      |
                |  4.- Estudio                   |
                |                                |
                ==================================
                """;
    }

    public static String menuModificarPerfil() {
        return """
                                            
                =========== MODIFICAR PERFIL ===========
                |                                      |
                |    ¿Qué apartado desea modificar?    |
                |                                      |
                |  1.- Nombre                          |
                |  2.- Apellidos                       |
                |  3.- Contraseña                      |
                |  4.- Teléfono                        |
                |  5.- Salir                           |
                |                                      |
                ========================================
                """;
    }

    public static String menuPropietario(String nombrePropietario, int numeroViviendas) {
        return "\n" +
                "============================ PERFIL DE PROPIETARIO ============================\n" +
                "\n" +
                "  Bienvenio/a " + nombrePropietario + ", gestione sus viviendas en alquiler.\n" +
                "                       Tienes " + numeroViviendas + " viviendas en alquiler.\n" +
                "\n" +
                "=============================== MENÚ PRINCIPAL ================================\n" +
                "\n" +
                "   1.- Ver mis viviendas en alquiler.\n" +
                "   2.- Editar mis viviendas.\n" +
                "   3.- Ver las reservas de mis viviendas.\n" +
                "   4.- Cambiar periodo de disponibilidad para una vivienda.\n" +
                "   5.- Ver mi perfil.\n" +
                "   6.- Modificar mi perfil.\n" +
                "   7.- Cerrar sesión.\n" +
                "\n" +
                "===============================================================================\n";
    }

    public static String menuEditarViviendas() {
        return """
                
                ========= EDITAR MIS VIVIENDAS =========
                |                                      |
                |    ¿Qué apartado desea modificar?    |
                |                                      |
                |  1.- Número de huéspedes             |
                |  2.- Precio por noche                |
                |  3.- Borrar Vivienda                 |
                |  4.- Añadir vivienda (Max 1)         |
                |  5.- Salir                           |
                |                                      |
                ========================================
                """;
    }
}
