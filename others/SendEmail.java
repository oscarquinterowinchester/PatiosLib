/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import basic.Config;
//import basic.SendEmail;
import basic.WiderCombo;
import basic.global;
import basic.utils;
import basic.ComboBoxCellEditor;
import basic.FillCombo;

/**
 *
 * @author Alex
 */
public class SendEmail {

    public static String normalEmail(String correo, ArrayList<String> attach, String body, String subject, int idfrom, ArrayList<String[]> images) {
        try {
            String mailto = "", mailbody = "";
            // Recipient's email ID needs to be mentioned.   
            String from = utils.dbConsult("SELECT Correo from mailinfo_tbl where MailID = '" + idfrom + "'");//"reservaciones@haciendaguadalupe.com.mx";
            // Sender's email ID needs to be mentioned
            String passw = utils.dbConsult("SELECT CCorreo from mailinfo_tbl where MailID = '" + idfrom + "'");//utils.dbConsult("SELECT pc from utils",1);
            // Assuming you are sending email from localhost
            String host = utils.dbConsult("SELECT HostC from mailinfo_tbl where MailID = '" + idfrom + "'");//sacramento.servershost.net
            String port = utils.dbConsult("SELECT Port from mailinfo_tbl where MailID = '" + idfrom + "'");
            String sign = utils.dbConsult("SELECT Signature from mailinfo_tbl where MailID = '" + idfrom + "'");
            // Get system properties
            Properties properties = System.getProperties();
            // Setup mail server
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.ssl.trust", "*");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            // Get the default Session object.
            Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, passw);
                }
            });

            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            if (!correo.contains(";")) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
                mailto = mailto + correo;
            } else {
                for (int i = 0; i < correo.split(";").length; i++) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo.split(";")[i]));
                    mailto = mailto + correo.split(";")[i];
                }
            }
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
            //entre mas adds tenga ^ esta linea se van sumando los correos
            // Set Subject: header field
            message.setSubject(subject);

            // Create the message part
            MimeBodyPart text = new MimeBodyPart();
            mailbody = "<font size=\"3\" color=\"black\">" + body + "</font>";
            text.setContent(global.cssmail + mailbody, "text/html; charset=utf-8");
            Multipart multiPart = new MimeMultipart("Attachment");
            multiPart.addBodyPart(text);

            //file part
            if (attach != null) {
                for (int i = 0; i < attach.size(); i++) {
                    multiPart.addBodyPart(addAttach(attach.get(i)));
                }
            }

            //images on body: signature
            //ArrayList<String[]> imgsig = addSignatureImages();
            MimeBodyPart imagespart;

            /*imagespart = new MimeBodyPart();
            imagespart.setDataHandler(new DataHandler(new FileDataSource(sign)));
            imagespart.setHeader("Content-ID", "<signature" + global.usuario + ">");
            multiPart.addBodyPart(imagespart);*/
 /*for (int i = 0; i < imgsig.size(); i++) {
            imagespart = new MimeBodyPart();
            imagespart.setDataHandler(new DataHandler(new FileDataSource(imgsig.get(i)[1])));
            imagespart.setHeader("Content-ID", "<" + imgsig.get(i)[0] + ">");
            multiPart.addBodyPart(imagespart);
            }*/
            //images on body: user
            if (images != null) {
                for (int i = 0; i < images.size(); i++) {
                    imagespart = new MimeBodyPart();
                    imagespart.setDataHandler(new DataHandler(new FileDataSource(images.get(i)[1])));
                    imagespart.setHeader("Content-ID", "<signature" + images.get(i)[0] + ">");
                    multiPart.addBodyPart(imagespart);
                }
            }
            //final
            message.setContent(multiPart);

//            session.getProperties().put("mail.smtp.ssl.trust", "*");
//            session.getProperties().put("mail.smtp.starttls.enable", "true");
//            session.getProperties().put("mail.smtp.ssl.protocols", "TLSv1.2");

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, passw);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            String folio = utils.dbConsult("SELECT IFNULL(SentID,0)+1 from sentmail_tbl order by SentID desc limit 1");
            //System.out.println(global.path+"bodys\\mail"+folio+".txt");
            String result = GenTxt.Generate(global.path + "bodys\\mail" + folio + ".txt", mailbody);
            if (result.isEmpty()) {
                utils.dbInsert("INSERT INTO sentmail_tbl (MailFrom,MailTo, Subject, Fecha, Body, TipoMail) VALUES('" + from + "','" + mailto + "','" + subject + "',(now()),'mail" + folio + ".txt','1')");
            }

            return "";
        } catch (MessagingException mex) {
            JOptionPane.showMessageDialog(new Frame(), "Error " + mex + "\n" + mex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
            if (global.usuario == 1) {
                mex.printStackTrace();
            }
            utils.errorGenerado("SendEmail / mex = " + mex.toString());
            return "Error " + mex.toString();
        } catch (Exception e) {
            if (global.usuario == 1) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(new Frame(), "Error " + e + "\n" + e.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
            utils.errorGenerado("SendEmail / ex = " + e.toString());
            return "Error ex = " + e.toString();
        }

    }

    public static String hiddenEmail(String id, String correo, ArrayList<String> attach, String body, String subject, int idfrom, ArrayList<String[]> images) {
        String mailto = "", mailbody = "";
        // Recipient's email ID needs to be mentioned.   
        String from = utils.dbConsult("SELECT Correo from mailinfo_tbl where MailID = '" + idfrom + "'");//"reservaciones@haciendaguadalupe.com.mx";
        // Sender's email ID needs to be mentioned

        String passw = utils.dbConsult("SELECT CCorreo from mailinfo_tbl where MailID = '" + idfrom + "'");//utils.dbConsult("SELECT pc from utils",1);
        // Assuming you are sending email from localhost
        String host = utils.dbConsult("SELECT HostC from mailinfo_tbl where MailID = '" + idfrom + "'");//sacramento.servershost.net
        String port = utils.dbConsult("SELECT Port from mailinfo_tbl where MailID = '" + idfrom + "'");
        String sign = utils.dbConsult("SELECT Signature from mailinfo_tbl where MailID = '" + idfrom + "'");
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, passw);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            if (!correo.contains(";")) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
                mailto = mailto + correo;
            } else {
                for (int i = 0; i < correo.split(";").length; i++) {
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(correo.split(";")[i]));
                    mailto = mailto + correo.split(";")[i];
                }
            }
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
            //entre mas adds tenga ^ esta linea se van sumando los correos
            // Set Subject: header field
            message.setSubject(subject);

            // Create the message part
            MimeBodyPart text = new MimeBodyPart();
            mailbody = "<font size=\"3\" color=\"black\">" + body + "</font>";
            text.setContent(global.cssmail + mailbody, "text/html; charset=utf-8");
            Multipart multiPart = new MimeMultipart("Attachment");
            multiPart.addBodyPart(text);

            //file part
            for (int i = 0; i < attach.size(); i++) {
                multiPart.addBodyPart(addAttach(attach.get(i)));
            }

            //images on body: signature
            //ArrayList<String[]> imgsig = addSignatureImages();
            MimeBodyPart imagespart;
            //for (int i = 0; i < imgsig.size(); i++) {
            imagespart = new MimeBodyPart();
            imagespart.setDataHandler(new DataHandler(new FileDataSource(sign)));
            imagespart.setHeader("Content-ID", "<signature" + global.usuario + ">");
            multiPart.addBodyPart(imagespart);
            //}
            //images on body: user
            for (int i = 0; i < images.size(); i++) {
                imagespart = new MimeBodyPart();
                imagespart.setDataHandler(new DataHandler(new FileDataSource(images.get(i)[1])));
                imagespart.setHeader("Content-ID", "<" + images.get(i)[0] + ">");
                multiPart.addBodyPart(imagespart);
            }

            //final
            message.setContent(multiPart);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, passw);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            String folio = utils.dbConsult("SELECT IFNULL(SentID,0)+1 from sentmail_tbl order by SentID desc limit 1");
            //System.out.println(global.path+"bodys\\mail"+folio+".txt");
            String result = GenTxt.Generate(global.path + "bodys\\mail" + folio + ".txt", mailbody);
            if (result.isEmpty()) {
                utils.dbInsert("INSERT INTO sentmail_tbl (MailFrom,MailTo, Subject, Fecha, Body, TipoMail) VALUES('" + from + "','" + mailto + "','" + subject + "',(now()),'mail" + folio + ".txt','1')");
            }
            return "";
        } catch (MessagingException mex) {
            JOptionPane.showMessageDialog(new Frame(), "Error " + mex + "\n" + mex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
            mex.printStackTrace();
            return "Error " + mex + "\n" + mex.getStackTrace()[0];
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new Frame(), "Error " + e + "\n" + e.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
            return "Error " + e + "\n" + e.getStackTrace()[0];
        }

    }

    
    
    private static ArrayList<String[]> addSignatureImages() {
        ArrayList<String[]> temp = new ArrayList<>();
        String query = "SELECT ContentID,Ruta from imgsignature_tbl";
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                temp.add(new String[]{rs.getString(1), rs.getString(2)});
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return temp;
    }

    private static MimeBodyPart addAttach(String file) throws MessagingException, IOException {
        File archivo = new File(file);
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(archivo);
        return attachment;
    }

    public static String makingTravelInfo(String itinerario, String tramite) {
        String body = "";
        String query = "SELECT ItinerarioID,"
                + "(SELECT Nombre from choferes_tbl where choferes_tbl.ChoferID = itinerarios_tbl.ChoferID) as nombrechofer,"
                + "IFNULL((SELECT DATE_FORMAT(Nacimiento,'%d/%m/%Y') from choferes_tbl where choferes_tbl.ChoferID = itinerarios_tbl.ChoferID),'') as fnac,"
                + "(SELECT Licencia from choferes_tbl where choferes_tbl.ChoferID = itinerarios_tbl.ChoferID) as licencia, "
                + "(SELECT NoEconomico from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID) as noeco, "
                + "CONCAT(IF(CHAR_LENGTH((SELECT PlacasUSA from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID))>=5, (SELECT PlacasUSA from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID), ''),' ' ,(SELECT EstadoPlacasUSA from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID)) as placasu, "
                + "CONCAT(IF(CHAR_LENGTH((SELECT Placas from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID))>=5, (SELECT Placas from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID), ''), ' ' , (SELECT EstadoPlacas from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID)) as placasm, "
                + "(SELECT Marca from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID) as marcacam, "
                + "(SELECT Modelo from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID) as modelocam, "
                + "(SELECT Visa from choferes_tbl where choferes_tbl.ChoferID = itinerarios_tbl.ChoferID) as visac, "
                + "(SELECT Fast from choferes_tbl where choferes_tbl.ChoferID = itinerarios_tbl.ChoferID) as fastc, "
                + "(SELECT Transponder from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID) as transp, "
                + "(SELECT Serie from camiones_tbl where camiones_tbl.CamionID = itinerarios_tbl.CamionID) as serievin, "
                + "(IFNULL((SELECT tipomovimientocont_tbl.Nombre from tipomovimientocont_tbl "
                + "where MovID = (SELECT tipoMov from workcontenedores_tbl "
                + "inner join icont_tbl on icont_tbl.WContID = workcontenedores_tbl.WContenedorID "
                + "where icont_tbl.ItinerarioID = itinerarios_tbl.ItinerarioID)), '')) as mov, "
                + "(IF((SELECT tipoMov from workcontenedores_tbl "
                + "inner join icont_tbl on icont_tbl.WContID = workcontenedores_tbl.WContenedorID "
                + "where icont_tbl.ItinerarioID = itinerarios_tbl.ItinerarioID) = 2,(SELECT empresas_tbl.SCAC from empresas_tbl where EmpresaID = 1),(SELECT empresas_tbl.SCAC from empresas_tbl "
                + "inner join camiones_tbl on camiones_tbl.empresaID = empresas_tbl.EmpresaID "
                + "where camiones_tbl.CamionID = itinerarios_tbl.CamionID))) as scac,"
                + "(IF((SELECT tipoMov from workcontenedores_tbl "
                + "inner join icont_tbl on icont_tbl.WContID = workcontenedores_tbl.WContenedorID "
                + "where icont_tbl.ItinerarioID = itinerarios_tbl.ItinerarioID) = 2,(SELECT empresas_tbl.CAAT from empresas_tbl where EmpresaID = 1),(SELECT empresas_tbl.CAAT from empresas_tbl "
                + "inner join camiones_tbl on camiones_tbl.empresaID = empresas_tbl.EmpresaID "
                + "where camiones_tbl.CamionID = itinerarios_tbl.CamionID))) as caat,"
                + "(IF((SELECT tipoMov from workcontenedores_tbl "
                + "inner join icont_tbl on icont_tbl.WContID = workcontenedores_tbl.WContenedorID "
                + "where icont_tbl.ItinerarioID = itinerarios_tbl.ItinerarioID) = 2,(SELECT empresas_tbl.DOT from empresas_tbl where EmpresaID = 1),(SELECT empresas_tbl.DOT from empresas_tbl "
                + "inner join camiones_tbl on camiones_tbl.empresaID = empresas_tbl.EmpresaID "
                + "where camiones_tbl.CamionID = itinerarios_tbl.CamionID))) as dotnum "
                + "from itinerarios_tbl "
                + "left join choferes_tbl on itinerarios_tbl.ChoferID = choferes_tbl.ChoferID "
                + "left join camiones_tbl on itinerarios_tbl.CamionID = camiones_tbl.CamionID "
                + "where ItinerarioID = '" + itinerario + "'";

        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            body = body + "<h3>Buen día</h3><br>"
                    + "<h4>Se comparte información:</h4><br>";
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                
                if(!tramite.equals("")){
                    body = body 
                        + "<table id=\"t01\" cellspacing = 0 cellpadding = 0 border = 1 style=\"border:1px solid #ccc;\">"
                        + "<col style=\"width:20%\">"
                        + "<col style=\"width:50%\">"
                        + "  <tr>"
                        + "    <td><b>Tipo de Tramite:</b></td>"
                        + "    <td>" + tramite + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Tipo de Movimiento:</b></td>"
                        + "    <td>" + rs.getString("mov") + "</td>"
                        + "  </tr>"
                        + "</table><br>";
                }
                  
                body = body
//                        + "<h5>Los datos de su embarque son: </h5>"
                        + "<table id=\"t01\" cellspacing = 0 cellpadding = 0 border = 1 style=\"border:1px solid #ccc;\">"
                        + "<col style=\"width:20%\">"
                        + "<col style=\"width:50%\">"                       
                        + "  <tr>"
                        + "    <th colspan=\"2\">Truck</th>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Tractor:</b></td>"
                        + "    <td>" + rs.getString("noeco") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Placas:</b></td>"
                        + "    <td>" + rs.getString("placasm") + " / " + rs.getString("placasu") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Serie:</b></td>"
                        + "    <td>" + rs.getString("serievin") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>DOT:</b></td>"
                        + "    <td>" + rs.getString("dotnum") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>CAAT:</b></td>"
                        + "    <td>" + rs.getString("caat") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>SCAC:</b></td>"
                        + "    <td>" + rs.getString("scac") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Marca:</b></td>"
                        + "    <td>" + rs.getString("marcacam") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Modelo:</b></td>"
                        + "    <td>" + rs.getString("modelocam") + "</td>"
                        + "  </tr>"
                        + "</table><br>"
                        + "<table id=\"t01\" cellspacing = 0 cellpadding = 0 border = 1 style=\"border:1px solid #ccc;\">"
                        + "<col style=\"width:20%\">"
                        + "<col style=\"width:50%\">"   
                        + "  <tr>"
                        + "    <th colspan=\"2\">Driver</th>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Operador:</b></td>"
                        + "    <td>" + rs.getString("nombrechofer") + "</td>"
                        + "  </tr>" 
                        + "  <tr>"
                        + "    <td><b>Licencia:</b></td>"
                        + "    <td>" + rs.getString("licencia") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Fast:</b></td>"
                        + "    <td>" + rs.getString("fastc") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Visa:</b></td>"
                        + "    <td>" + rs.getString("visac") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Gafete:</b></td>"
                        + "    <td>" + rs.getString("Licencia") + "</td>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>D. Birth:</b></td>"
                        + "    <td>" + rs.getString("fnac") + "</td>"
                        + "  </tr>"
                        + "</table><br>";
            }

            rs = state.executeQuery("SELECT "
                    + "Contenedor, "
                    + "wc.CajaID as idcaja,"
                    + "PlacasCaja,"
                    + "CONCAT(IF(CHAR_LENGTH((SELECT PlacasUSA from cajas_tbl where cajas_tbl.CajaID = wc.CajaID))>=5, (SELECT PlacasUSA from cajas_tbl where cajas_tbl.CajaID = wc.CajaID), ''),' ' ,(SELECT EstadoPlacasUSA from cajas_tbl where cajas_tbl.CajaID = wc.CajaID)) as placasus,"
                    + "CONCAT(IF(CHAR_LENGTH((SELECT Placas from cajas_tbl where cajas_tbl.CajaID = wc.CajaID))>=5, (SELECT Placas from cajas_tbl where cajas_tbl.CajaID = wc.CajaID), ''),' ' ,(SELECT EstadoPlacas from cajas_tbl where cajas_tbl.CajaID = wc.CajaID)) as placasmx,"
                    + "IFNULL((SELECT Serie from cajas_tbl where cajas_tbl.CajaID = wc.CajaID),'') as serie "
                    + "from icont_tbl as icont "
                    + "left join workcontenedores_tbl as wc on WContenedorID = WContID "
                    + "where ItinerarioID = '" + itinerario + "' and icont.Status = true");
            int i = 1;
            while (rs.next()) {
                body = body + "<table id=\"t01\" cellspacing = 0 cellpadding = 0 border = 1 style=\"border:1px solid #ccc;\">"
                        + "<col style=\"width:20%\">"
                        + "<col style=\"width:50%\">"
                        + "  <tr>"
                        + "    <th colspan=\"2\">Trailer " + i + "</th>"
                        + "  </tr>"
                        + "  <tr>"
                        + "    <td><b>Remolque:</b></td>"
                        + "    <td>" + rs.getString("contenedor") + "</td>"
                        + "  </tr>";
                if (rs.getInt("idcaja") == 0) {
                    body = body + "  <tr>"
                            + "    <td><b>Placas:</b></td>"
                            + "    <td>" + rs.getString("PlacasCaja") + "</td>"
                            + "  </tr>";
                } else {
                    body = body + "  <tr>"
                            + "    <td><b>Placas:</b></td>"
                            + "    <td>" + rs.getString("placasmx") + " / " + rs.getString("placasus") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td><b>#VIN:</b></td>"
                            + "    <td>" + rs.getString("serie") + "</td>"
                            + "  </tr>";
                }

                body = body + "</table><br>";
                i++;
            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

        return body;
    }

}

/*
 MimeBodyPart image = new MimeBodyPart();
 image.setDataHandler(new DataHandler(new FileDataSource("images\\imagemail.jpg")));
 image.setHeader("Content-ID", "<image>");
 multiPart.addBodyPart(image);

 MimeBodyPart image2 = new MimeBodyPart();
 DataSource fds2 = new FileDataSource("images\\logosmail.jpg");
 image2.setDataHandler(new DataHandler(fds2));
 image2.setHeader("Content-ID", "<image2>");
 multiPart.addBodyPart(image2);



 */
