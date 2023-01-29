package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import java.awt.Font;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.Reserva;
import model.Usuario;

public class Factura {
    public static void generarFactura(Usuario usuario, Reserva reserva, Date fechaReserva, String nombreFactura, String idFacturaGenerado){
        //Creamos el ducumento con formato A4
        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4,45,45,5,0);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(nombreFactura));
            document.open();
            URL url = new URL("https://i.ibb.co/5LzhXPz/logo-Reservas-Fernanbnb.png");
            //Image imagen = Image.getInstance("logoReservasFernanbnb.png");
            Image imagen = Image.getInstance(url);
            //Escogemos el ancho del logo con el ancho del formato A4
            //imagen.scaleAbsoluteWidth(PageSize.A4.getWidth());
            //imagen.scaleAbsoluteHeight(100);
            imagen.scalePercent(40f);
            imagen.setAlignment(Element.ALIGN_RIGHT);

            document.add(imagen);

            Paragraph texto = devuelveNegrita("\nHola " + usuario.getNombre());
            texto.getFont().setSize(20);
            texto.setAlignment(Element.ALIGN_CENTER);
            document.add(texto);
            Paragraph texto2 = new Paragraph(("""
                    Gracias por realizar una reserva. Aquí le mandamos los datos pertinentes: 
                    
                    """));
            texto2.setAlignment(Element.ALIGN_CENTER);
            document.add(texto2);

            texto.clear();
            String idFactura = "ID de la factura:\n" + "SJ" + idFacturaGenerado + "\n\n";
            /*texto = devuelveNegrita("""
                    ID de la factura:
                    123456789
                    
                    """);*/
            texto = devuelveNegrita(idFactura);
            texto.getFont().setSize(20);
            texto.setAlignment(Element.ALIGN_CENTER);
            document.add(texto);


            // Este codigo genera una tabla de 3 columnas
            PdfPTable tabla = new PdfPTable(2);

            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
            //Font font = new Font(Font.SERIF, Font.BOLD,10);

            tabla.setWidthPercentage(100);

            Paragraph parrafoTabla1 = new Paragraph("INFORMACIÓN GENERAL:");
            parrafoTabla1.getFont().setColor(BaseColor.RED);
            PdfPCell celdaIncialTabla1 = new PdfPCell(parrafoTabla1);
            celdaIncialTabla1.setColspan(2);
            //celdaIncialTabla1.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaIncialTabla1.setBorder(Rectangle.BOTTOM);
            tabla.addCell(celdaIncialTabla1);

            //Comprobar si las celdas son iguales al metodo crearContenido.EN caso afirmativo se deja como en la tabla 2

            //Creamos el contenido de las 4 celdas

            PdfPTable tablaCorreo  = new PdfPTable(1);
            //En cada celda que se cree se debe quitar el borde
            PdfPCell celda1Correo = devuelveCabeceraNegrita("Correo electrónico: ");
            celda1Correo.setPaddingTop(10);
            Paragraph correoElectronico = new Paragraph(usuario.getEmail());
            correoElectronico.getFont().setColor(BaseColor.BLUE);
            PdfPCell celda2Correo = new PdfPCell(correoElectronico);
            celda2Correo.setBorder(Rectangle.NO_BORDER);
            celda2Correo.setPaddingBottom(10);
            tablaCorreo.addCell(celda1Correo);
            tablaCorreo.addCell(celda2Correo);
            PdfPCell celdaCorreo = new PdfPCell(tablaCorreo);
            celdaCorreo.setBorder(Rectangle.NO_BORDER);

            tabla.addCell(crearContenidoTabla("Id de la reserva",String.valueOf(reserva.getId()))); // Todos funciona :D
            tabla.addCell(celdaCorreo);
            SimpleDateFormat formatoReserva = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            tabla.addCell(crearContenidoTabla("Fecha de la reserva",formatoReserva.format(fechaReserva)));
            tabla.addCell(crearContenidoTabla("Fuente","Consola"));

            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span

            // Agregamos la tabla al documento
            document.add(tabla);

            //Creamos la tabla de los datos del precio
            PdfPTable tabla2 = new PdfPTable(2);
            tabla2.setWidthPercentage(100);

            Paragraph parrafoTabla2 = new Paragraph("DATOS DE LA RESERVA:");
            parrafoTabla2.getFont().setColor(BaseColor.RED);
            PdfPCell celdaIncialTabla2 = new PdfPCell(parrafoTabla2);
            celdaIncialTabla2.setColspan(2);
            //celdaIncialTabla1.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaIncialTabla2.setBorder(Rectangle.BOTTOM);
            celdaIncialTabla2.setPaddingTop(10);
            tabla2.addCell(celdaIncialTabla2);

            //Describimos los encabezados
            Paragraph parrafoDescripcion = new Paragraph("Descripción");
            parrafoDescripcion.getFont().setColor(BaseColor.WHITE);
            parrafoDescripcion.getFont().setStyle(Font.BOLD);
            PdfPCell celdaDescripcion = new PdfPCell(parrafoDescripcion);
            celdaDescripcion.setBackgroundColor(BaseColor.RED);
            celdaDescripcion.setBorder(Rectangle.NO_BORDER);
            celdaDescripcion.setPaddingLeft(10);
            tabla2.addCell(celdaDescripcion);

            Paragraph parrafoPrecio = new Paragraph("Precio");
            parrafoPrecio.getFont().setColor(BaseColor.WHITE);
            parrafoPrecio.getFont().setStyle(Font.BOLD);
            PdfPCell celdaPrecio = new PdfPCell(parrafoPrecio);
            celdaPrecio.setBackgroundColor(BaseColor.RED);
            celdaPrecio.setBorder(Rectangle.NO_BORDER);
            celdaPrecio.setHorizontalAlignment(Element.ALIGN_RIGHT);
            celdaPrecio.setPaddingRight(10);
            tabla2.addCell(celdaPrecio);

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaInicio = formato.format(reserva.getFechaEntrada());
            String fechaSalida = formato.format(reserva.getFechaSalida());
            String totNoches = "x " + reserva.getNoches();

            tabla2.addCell(crearContenidoTabla("Localidad",reserva.getVivienda().getLocalidad()));
            tabla2.addCell(crearContenidoTabla("",""));
            tabla2.addCell(crearContenidoTabla("Dirección",reserva.getVivienda().getDireccion()));
            tabla2.addCell(crearContenidoTabla("",""));
            tabla2.addCell(crearContenidoTabla("Número de huéspedes",String.valueOf(reserva.getHuesped())));
            tabla2.addCell(crearContenidoTabla("",""));
            tabla2.addCell(crearContenidoTabla("Fecha Inicio",fechaInicio));
            tabla2.addCell(crearContenidoTabla("",""));
            tabla2.addCell(crearContenidoTabla("Fecha Fin",fechaSalida));
            tabla2.addCell(crearContenidoTabla("",""));
            tabla2.addCell(crearContenidoTabla("Precio por noche",String.valueOf(reserva.getVivienda().getPrecioNoche())));
            tabla2.addCell(crearContenidoTablaDer(String.valueOf(reserva.getVivienda().getPrecioNoche())));
            //Tenemos que sacar el total de noches
            tabla2.addCell(crearContenidoTabla("Total de noches",String.valueOf(reserva.getNoches())));
            PdfPCell cantidadNoches = crearContenidoTablaDer(totNoches);

            tabla2.addCell(cantidadNoches);

            String precioTotal = "Precio total: " + (reserva.getNoches() * reserva.getVivienda().getPrecioNoche()) + " €";
            PdfPCell celdaFinal = new PdfPCell(new Paragraph(precioTotal));

            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(3);
            celdaFinal.setBorder(Rectangle.TOP);
            celdaFinal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla2.addCell(celdaFinal);

            document.add(tabla2);

            document.close();

        } catch (Exception e) {
            System.err.println("Ocurrio un error al crear el archivo");
            System.exit(-1);
        }

    }

    private static Paragraph devuelveNegrita(String texto){
        Paragraph contenido = new Paragraph(texto);
        contenido.getFont().setStyle(Font.BOLD);
        return contenido;
    }

    private static PdfPCell devuelveCabeceraNegrita(String encabezado){
        PdfPCell encabezadoSinBorde = new PdfPCell(devuelveNegrita(encabezado));
        encabezadoSinBorde.setBorder(Rectangle.NO_BORDER);
        return encabezadoSinBorde;
    }

    private static PdfPCell crearContenidoTabla(String encabezado, String info){
        PdfPTable tabla  = new PdfPTable(1);
        //En cada celda que se cree se debe quitar el borde
        PdfPCell celda1 = devuelveCabeceraNegrita(encabezado);
        celda1.setPaddingTop(10);
        PdfPCell celda2 = new PdfPCell(new Phrase(info));
        celda2.setBorder(Rectangle.NO_BORDER);
        celda2.setPaddingBottom(10);
        tabla.addCell(celda1);
        tabla.addCell(celda2);
        PdfPCell celda = new PdfPCell(tabla);
        celda.setBorder(Rectangle.NO_BORDER);
        return celda;
    }

    private static PdfPCell crearContenidoTablaDer(String info){
        PdfPTable tabla  = new PdfPTable(1);
        //En cada celda que se cree se debe quitar el borde
        PdfPCell celda1 = devuelveCabeceraNegrita("\n");
        celda1.setPaddingTop(10);
        PdfPCell celda2 = new PdfPCell(new Phrase(info));
        celda2.setBorder(Rectangle.NO_BORDER);
        celda2.setPaddingBottom(10);
        celda2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(celda1);
        tabla.addCell(celda2);
        PdfPCell celda = new PdfPCell(tabla);
        celda.setBorder(Rectangle.NO_BORDER);
        return celda;
    }
}
