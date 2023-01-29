package utils;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;
import static jakarta.mail.Transport.send;

public class Email {
    private static final String host = "smtp.gmail.com";
    private static final String user = "mensajerofernanbnb@gmail.com";
    private static final String pass = "kwvekaurqcwvllsw";

    //La factura se mandará al usuario y al propietario. Por tanto necesitaremos 2 destinatarios y dos mensajes
    public static boolean enviarFactura(String destinoUsuario,String destinoPropietario, String asuntoUsuario, String asuntoPropietario, String mensajeUsuario,String mensajePropietario, String direccionDocumento) {
        // Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(user, pass);
            }
        });

        try {
            //Manadamos el mensaje al usuario
            //Creamos el texto del contenido Html
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensajeUsuario,"text/html");

            //Creamos el adjunto pdf
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile(direccionDocumento);

            //Creamos el contenido con el mensaje y el Pdf
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            multiparte.addBodyPart(pdfAttachment);


            // Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);

            // En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinoUsuario));

            // Establecemos el Asunto
            message.setSubject(asuntoUsuario);

            // Añadimos el contenido del mensaje (Texto e imagen)
            message.setContent(multiparte);

            // Intenamos mandar el mensaje
            Transport t = session.getTransport("smtp");
            t.connect(user,pass);
            t.sendMessage(message,message.getAllRecipients());
            t.close();

            //Mandamos el mensaje al propietario
            //Creamos el texto del contenido Html
            BodyPart textoPropietario = new MimeBodyPart();
            textoPropietario.setContent(mensajePropietario,"text/html");


            //Creamos el contenido con el mensaje y el Pdf
            multiparte = new MimeMultipart();
            multiparte.addBodyPart(textoPropietario);
            multiparte.addBodyPart(pdfAttachment);


            // Creamos un mensaje de correo por defecto
            Message messagePropietario = new MimeMessage(session);

            // En el mensaje, establecemos el receptor
            messagePropietario.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinoPropietario));

            // Establecemos el Asunto
            messagePropietario.setSubject(asuntoPropietario);

            // Añadimos el contenido del mensaje (Texto e imagen)
            messagePropietario.setContent(multiparte);

            // Intenamos mandar el mensaje
            t = session.getTransport("smtp");
            t.connect(user,pass);
            t.sendMessage(messagePropietario,messagePropietario.getAllRecipients());
            t.close();


        } catch (Exception e) { //Si entra aquí hemos tenido fallo
            System.out.println("Error al enviar el correo electrónico. Asegúrese de tener una conexión a internet");
            Mensajes.pausa("Pulse una tecla para continuar");
            return false;
        }
        return true;
    }

    public static boolean enviarAnulacion(String destinoUsuario,String destinoPropietario, String asuntoUsuario, String asuntoPropietario, String mensajeUsuario,String mensajePropietario) {// Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        //Mandamos la multiparte aunque sea solo el texto, en caso de mandar el pdf con la marca de agua de anulada, no tenemos que cambiar el método, solo añadir el pdf.
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(user, pass);
            }
        });

        try {
            //Manadamos el mensaje al usuario
            //Creamos el texto del contenido Html
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensajeUsuario,"text/html");


            //Creamos el contenido con el mensaje y el Pdf
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);


            // Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);

            // En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinoUsuario));

            // Establecemos el Asunto
            message.setSubject(asuntoUsuario);

            // Añadimos el contenido del mensaje (Texto e imagen)
            message.setContent(multiparte);

            // Intenamos mandar el mensaje
            Transport t = session.getTransport("smtp");
            t.connect(user,pass);
            t.sendMessage(message,message.getAllRecipients());
            t.close();

            //Mandamos el mensaje al propietario
            //Creamos el texto del contenido Html
            BodyPart textoPropietario = new MimeBodyPart();
            textoPropietario.setContent(mensajePropietario,"text/html");


            //Creamos el contenido con el mensaje y el Pdf
            multiparte = new MimeMultipart();
            multiparte.addBodyPart(textoPropietario);


            // Creamos un mensaje de correo por defecto
            Message messagePropietario = new MimeMessage(session);

            // En el mensaje, establecemos el receptor
            messagePropietario.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinoPropietario));

            // Establecemos el Asunto
            messagePropietario.setSubject(asuntoPropietario);

            // Añadimos el contenido del mensaje (Texto e imagen)
            messagePropietario.setContent(multiparte);

            // Intenamos mandar el mensaje
            t = session.getTransport("smtp");
            t.connect(user,pass);
            t.sendMessage(messagePropietario,messagePropietario.getAllRecipients());
            t.close();


        } catch (Exception e) { //Si entra aquí hemos tenido fallo
            System.out.println("Error al enviar el correo electrónico. Asegúrese de tener una conexión a internet");
            Mensajes.pausa("Pulse una tecla para continuar");
            return false;
        }
        return true;
    }

    public static boolean enviarConfirmacion(String destino, String asunto, String mensaje) {
        // Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(user, pass);
            }
        });


        try {
            //Creamos el texto del contenido del correo
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);


            // Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);

            // En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));

            // Establecemos el Asunto
            message.setSubject(asunto);

            //Hacemos que el mensaje se mande con formato html
            message.setContent(mensaje,"text/html; charset=utf-8");

            // Intenamos mandar el mensaje
            Transport t = session.getTransport("smtp");
            t.connect(user,pass);
            t.sendMessage(message,message.getAllRecipients());
            t.close();

        } catch (Exception e) { //Si entra aquí hemos tenido fallo
            System.out.println("Error al enviar el correo electrónico. Asegúrese de tener una conexión a internet o introduzca un corro electrónico existente");
            Mensajes.pausa("Pulse una tecla para continuar");
            return false;
        }
        return true;
    }
}

    /*public static boolean enviarFactura(String destino, String asunto, String mensaje, String direccionDocumento) {
        // Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(user, pass);
            }
        });

        try {
            //Creamos el texto del contenido del correo
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);


            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile(direccionDocumento);

            //Creamos el contenido con el mensaje y la foto
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            multiparte.addBodyPart(pdfAttachment);
            //multiparte.addBodyPart(adjunto);



            // Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);

            // En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));

            // Establecemos el Asunto
            message.setSubject(asunto);

            // Añadimos el contenido del mensaje (Texto e imagen)
            // message.setContent(multiparte);
            message.setContent(mensaje,"text/html; charset=utf-8");

            // Intenamos mandar el mensaje
            Transport t = session.getTransport("smtp");
            t.connect(user,pass);
            t.sendMessage(message,message.getAllRecipients());
            t.close();

        } catch (Exception e) { //Si entra aquí hemos tenido fallo
            System.out.println("Error al enviar el correo electrónico. Asegúrese de tener una conexión a internet");
            Mensajes.pausa("Pulse una tecla para continuar");
            return false;
        }
        return true;
    }
*/