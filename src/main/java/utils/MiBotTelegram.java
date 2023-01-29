package utils;


import model.Propietario;
import model.Usuario;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.vdurmont.emoji.EmojiParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MiBotTelegram extends TelegramLongPollingBot {
    private Usuario usuarioTelegram;

    private String ruta = "C:/Users/Jose Aranda/Desktop/FENANbnb/FENANbnb/factura.pdf";

    Propietario propietarioTelegram;
    String numeroTelefonoPrefijo;
    int numeroTelefono;
    boolean pedirConfirmacion = false;
    boolean accederMenuUsuario;
    boolean accederMenuPropietario;

    //Para mandar el pdf
    boolean mandarFacturaUsuario = false;
    boolean mandarFacturaPropietario = false;
    static Datos datos;

    String chatId;

    //Metodos



    @Override
    public void onUpdateReceived(Update update) {
        // TODO
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if(datos.usuarioEncontrado(update.getMessage().getChatId())){
                accederMenuUsuario = true;
                accederMenuPropietario = false;
                pedirConfirmacion = false;
                usuarioTelegram = datos.obtenerUsuario(update.getMessage().getChatId());
            }
            if(datos.propietarioEncontrado(update.getMessage().getChatId())){
                accederMenuPropietario = true;
                accederMenuUsuario = false;
                pedirConfirmacion = false;
                propietarioTelegram = datos.obtenerPropietario(update.getMessage().getChatId());
            }

            if (message.hasText()) {

                String texto = message.getText();

                if (texto.equals("/start")) {
                    SendMessage sendMessage = new SendMessage(); // Create a SendMessage object with mandatory fields
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    sendMessage.setText(EmojiParser.parseToUnicode("Para acceder necesito que me digas tu número de téfono"));
                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    replyKeyboardMarkup.setSelective(true);
                    List<KeyboardRow> keyboardRowList = new ArrayList<>();
                    KeyboardRow keyboardRow1 = new KeyboardRow();
                    KeyboardButton keyboardButton1 = new KeyboardButton();
                    keyboardButton1.setText("Compartir número de teléfono");
                    keyboardButton1.setRequestContact(true);
                    keyboardRow1.add(keyboardButton1);
                    keyboardRowList.add(keyboardRow1);
                    replyKeyboardMarkup.setKeyboard(keyboardRowList);
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                    try {
                        execute(sendMessage); // Call method to send the sendMessage
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (pedirConfirmacion) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    if (!datos.buscarUsuario(texto, numeroTelefono) && !datos.buscarPropietario(texto, numeroTelefono))
                        sendMessage.setText(EmojiParser.parseToUnicode("Contacto no encontrado o contraseña " +
                                "errónea :fearful: . Por favor vuelva a intentarlo"));
                    if (datos.buscarUsuario(texto, numeroTelefono)) {
                        sendMessage.setText("Contacto encontrado, pulse /user para acceder al menú. Por favor borre la contraseña para evitar problemas");
                        chatId = update.getMessage().getChatId().toString();
                        usuarioTelegram = datos.aniadirIdChatTelegramUsuario(texto, numeroTelefono,chatId);
                        //usuarioTelegram = datos.obtenerUsuario(chatId);
                        //accederMenuUsuario = true;
                        //accederMenuPropietario = false;
                        pedirConfirmacion = false;
                    }
                    if (datos.buscarPropietario(texto, numeroTelefono)) {
                        sendMessage.setText("Contacto encontrado, pulse /prop para acceder al menú. Por favor borre la contraseña para evitar problemas");
                        chatId = update.getMessage().getChatId().toString();
                        propietarioTelegram = datos.aniadirIdChatTelegramPropietario(texto, numeroTelefono, chatId);
                        //accederMenuPropietario = true;
                        //accederMenuUsuario = false;
                        pedirConfirmacion = false;
                    }
                    try {
                        execute(sendMessage); // Call method to send the sendMessage
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (accederMenuUsuario) {
                    if (texto.equals("/user")) { //Cuando accedemos al menu
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(update.getMessage().getChatId().toString());
                        String menu = "Hola " + usuarioTelegram.getNombre() + "\n";
                        sendMessage.setText(menu + Mensajes.contenidoMenuUsuarioTelegram());

                        try {
                            execute(sendMessage); // Call method to send the sendMessage
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else { //si dentro del menu hemos escrito en vez de darle o si quieremos introducir datos(cambiar datos desde Telegram)
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(update.getMessage().getChatId().toString());
                        switch (texto) {
                            case "/reservas" -> {
                                if (usuarioTelegram.verReserva(datos).equals(""))
                                    sendMessage.setText("No hay reservas creadas.");
                                else sendMessage.setText((usuarioTelegram.verReserva(datos)));
                                // sendMessage.setText();
                            }
                            case "/obtenerFactura" -> {
                                if (!usuarioTelegram.getRutaUltimaFactura().equals("")) {
                                    java.io.File pdf = new File(usuarioTelegram.getRutaUltimaFactura()); //ruta
                                    InputFile nuevoPdf = new InputFile(pdf);

                                    SendDocument sendDocumentRequest = new SendDocument();
                                    sendDocumentRequest.setChatId(update.getMessage().getChatId());
                                    sendDocumentRequest.setDocument(nuevoPdf);
                                    try {
                                        execute(sendDocumentRequest);
                                    } catch (TelegramApiException e) {
                                        e.printStackTrace();
                                    }

                                } else sendMessage.setText("No se han realizado reservas");
                            }
                            default -> {
                                sendMessage.setText("Opcion incorrecta");
                            }
                        }
                        try {
                            execute(sendMessage); // Call method to send the sendMessage
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    }

                } else if (accederMenuPropietario) {
                    if (texto.equals("/prop")) { //Cuando accedemos al menu
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(update.getMessage().getChatId().toString());
                        String menu = "Hola " + propietarioTelegram.getNombre() + "\n";
                        sendMessage.setText(menu + Mensajes.contenidoMenuPropietarioTelegram());

                        try {
                            execute(sendMessage); // Call method to send the sendMessage
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else { //si dentro del menu hemos escrito en vez de darle o si quieremos introducir datos(cambiar datos desde Telegram)
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(update.getMessage().getChatId().toString());
                        switch (texto) {
                            case "/casas" -> {
                                if (propietarioTelegram.existenciaVivienda()) sendMessage.setText(propietarioTelegram.mostrarVivienda());
                                else sendMessage.setText("No se han registrado viviendas");
                            }
                            case "/casaReser" -> {
                                sendMessage.setText(propietarioTelegram.verReservas(datos));
                            }
                            case "/obtenerFactura" -> {
                                if (!propietarioTelegram.getRutaUltimaFactura().equals("")) {
                                    java.io.File pdf = new File(propietarioTelegram.getRutaUltimaFactura()); //ruta
                                    InputFile nuevoPdf = new InputFile(pdf);

                                    SendDocument sendDocumentRequest = new SendDocument();
                                    sendDocumentRequest.setChatId(update.getMessage().getChatId());
                                    sendDocumentRequest.setDocument(nuevoPdf);
                                    try {
                                        execute(sendDocumentRequest);
                                    } catch (TelegramApiException e) {
                                        e.printStackTrace();
                                    }

                                } else sendMessage.setText("No has recibido ninguna reserva");
                            }
                            default -> {
                                sendMessage.setText("Opcion incorrecta");
                            }
                        }
                        try {
                            execute(sendMessage); // Call method to send the sendMessage
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    }
                }
            } else if (message.hasContact()) {
                Contact contact = message.getContact();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Tu numero de telefono es " + contact.getPhoneNumber() + "\n");
                numeroTelefonoPrefijo = contact.getPhoneNumber();
                numeroTelefono = Integer.parseInt(numeroTelefonoPrefijo.substring(2, numeroTelefonoPrefijo.length())); //Como solo es el ámbito español y no hemos pedido el prefijo al registrar los usuarios y propietarios nos decidimos a descartarlo
                //if(!Datos.buscarUsuarioPropietario(numeroTelefono)) sendMessage.setText("Lamentablemente no estás registrado a Fernanbnb. Por favor regístrate para disfrutar de nuestros servicios");
                //else sendMessage.setText("Por favor introduzca la contraseña de la aplicación Fernanbnb");
                sendMessage.setText("Por favor introduzca la contraseña de la aplicación Fernanbnb");
                pedirConfirmacion = true;
                try {
                    execute(sendMessage); // Call method to send the sendMessage
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public String getBotUsername() {
        // TODO
        return "MensajeroFernanbnbBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5658872500:AAHxhNLPENRbPOESIB9VJ7Ha-ijjLIpGhOM";
    }

    public static boolean enviaMensajeTelegram(String idChat, String mensaje) {
        boolean dev = false;
        if (!idChat.equals("")) {
            String direccion;
            String fijo = "https://api.telegram.org/bot5658872500:AAHxhNLPENRbPOESIB9VJ7Ha-ijjLIpGhOM/sendMessage?chat_id=" + idChat + "&text=";
            direccion = fijo + mensaje;
            URL url;
            try {
                url = new URL(direccion);
                URLConnection con = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                dev = true;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return dev;
    }

    public static void setDatos(Datos datosObjeto) {
        datos = datosObjeto;
    }
}

/*case "/ultReserva" -> {
                                java.io.File pdf = new File(ruta); //ruta
                                InputFile nuevoPdf = new InputFile(pdf);
                                SendDocument sendDocumentRequest = new SendDocument();
                                sendDocumentRequest.setChatId(usuarioTelegram.getIdChatTelegram());
                                sendDocumentRequest.setDocument(nuevoPdf);
                                try {
                                    execute(sendDocumentRequest);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }*/