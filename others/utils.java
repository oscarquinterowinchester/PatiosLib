/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import com.alee.utils.FileUtils;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.ss.usermodel.DateUtil;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/*
 *
 * @author alejandro
 */
public class utils {

    public static String agregarAddenda(String file, String addedtext, String facturaid, int tipo) {
        try {
            String carpetainterna = "CFDIaddendas", carpetausuario = "Facturas", nodoc = utils.dbConsult("SELECT NoFactura from facturas_tbl where FacturaID = '" + facturaid + "'");
            if (tipo == 1) {
                carpetainterna = "CFDIcpaddendas";
                carpetausuario = "Cartas Porte";
                nodoc = utils.dbConsult("SELECT NoCartaPorte from cartasporte_tbl where CartaPorteID = '" + facturaid + "'");
            }
            String filepath = file;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            String xml = convertXMLDocumentToString(doc);

            String replaced = xml.replace("</cfdi:Comprobante>", addedtext + "\n</cfdi:Comprobante>");
            System.out.println("xml = " + replaced);
            doc = convertStringToXMLDocument(replaced);
            // write the content into xml file

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult resultcfdi = new StreamResult(new File(global.rectim + carpetainterna + "\\" + facturaid + ".xml"));
            StreamResult resultfac = new StreamResult(new File(global.rectim + carpetausuario + "\\" + nodoc + ".xml"));
            transformer.transform(source, resultcfdi);
            transformer.transform(source, resultfac);

//            System.out.println("Done");
        } catch (ParserConfigurationException | TransformerException | IOException | SAXException pce) {
            System.out.println("pce = " + pce);
            return pce.toString();
        }
        return "";
    }

    public static void iniciaHiloCierre(JFrame frame, boolean principal) {
        try {
            Thread t = new Thread(new utils.startChecking(frame, principal));
            t.start();
        } catch (Exception e) {
            utils.errorGenerado("utils / cierre thread ex  " + e.toString());
        }
    }

    /*static void generaInvoice(String id, dNuevoDoc aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    public static class startChecking implements Runnable {

        JFrame frame;
        boolean principal;

        public startChecking(JFrame frame, boolean principal) {
            this.frame = frame;
            this.principal = principal;
        }

        @Override
        public void run() {
            int tiempoespera = 20000;
            while (true) {// && global.usuario > 1
//                System.out.println("running");
                if (frame.isEnabled() && !principal) {
//                    System.out.println("reading");
                    if (utils.dbConsult("SELECT IF(CerrarTodos, 1, 0) from utils_tbl where UtilID = 1").equals("1")) {
                        String ordencierre = utils.dbConsult("SELECT IFNULL((SELECT cierreid from cierretotal where status = true order by cierreid desc limit 1),0)");
                        if (!ordencierre.equals("0")) {
                            if (utils.dbConsult("SELECT IFNULL((SELECT id from usermacad_tbl where fecha >= (SELECT cit.fecha from cierretotal as cit where cit.cierreid = '" + ordencierre + "') and usuariofk = '" + global.usuario + "' limit 1),0)").equals("0")) {
                                tiempoespera = 1000;
                                System.out.println("exit");
                                frame.setEnabled(false);
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(tiempoespera);
                } catch (InterruptedException ex) {
                    //JOptionPane.showMessageDialog(null, "Error " + ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    public static boolean revisionDMCompleto(String itiid) {
//        !model1.getValueAt(row, t1choferid).equals("0") && !model1.getValueAt(row, t1camionid).equals("0") && !model1.getValueAt(row, t1rutaid).equals("0") && !utils.dbConsult("SELECT IFNULL(Carga,'') from itinerarios_tbl where ItinerarioID = '" + model1.getValueAt(row, t1itiid) + "'").equals("")
        if (utils.dbConsult("SELECT IFNULL((SELECT ItinerarioID from itinerarios_tbl where ItinerarioID = '" + itiid + "' and ChoferID > 0 and (CamionID > 0 or (SELECT Externo from choferes_tbl where choferes_tbl.ChoferID = itinerarios_tbl.ChoferID) = true) and RutaID > 0 and Carga is not null limit 1),0)").equals("0")) {
            return false;
        }
        return true;
    }

    public static void reporteOT(String id, String origenid, String destinoid) throws JRException, SQLException {
        Connection con = utils.startConnection();
        JasperReport reporte = null;
        reporte = (JasperReport) JRLoader.loadObject(new File("ReporteViaje.jasper"));
        Map parametros = new HashMap();
        parametros.put("itinerarioid", id);
        parametros.put("origenid", origenid);
        parametros.put("destinoid", destinoid);
        parametros.put("path", global.path);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
        utils.windowJasper(jasperPrint);
        con.close();

    }

    public static void reporteRemision(String facid) {
        Connection con = utils.startConnection();
        JasperReport reporte = null;
        Map parametros = new HashMap();
        try {
            reporte = (JasperReport) JRLoader.loadObject(new File("ReporteRemision.jasper"));
            parametros.put("facturaid", facid);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void reporteRemision(String facid, JDialog dialog) {
        Connection con = utils.startConnection();
        JasperReport reporte = null;
        Map parametros = new HashMap();
        try {
            reporte = (JasperReport) JRLoader.loadObject(new File("ReporteRemision.jasper"));
            parametros.put("facturaid", facid);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint, dialog);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String[] getMac() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            //System.out.println("Current IP address : " + ip.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            //System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }

            return new String[]{sb.toString(), network.getName(), network.getDisplayName()};
            //System.out.println(sb.toString());
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Host desconocido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SocketException e) {
            JOptionPane.showMessageDialog(null, "Socket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ex: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return new String[]{"", "", ""};
    }

    public static String exportTabletoExcel(Component window, JTable table) {

        String ret = "", path = "";
        JFileChooser fcxls = new JFileChooser();
        fcxls.setDialogTitle("Ingrese el destino");
        fcxls.setMultiSelectionEnabled(false);
        fcxls.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (fcxls.showOpenDialog(window) == JFileChooser.APPROVE_OPTION) {
            File file = fcxls.getSelectedFile();
            path = file.getAbsolutePath().replace(".xls", "");
            ArrayList<String[]> data = new ArrayList<>();
            try {
                String[] headers = new String[table.getColumnCount()];
                String val = "";
                for (int i = 0; i < table.getColumnCount(); i++) {
                    if (table.getColumnName(i) != null) {
                        val = table.getColumnName(i);
                    } else {
                        val = "";
//                        System.out.println("header" + val + "i:" + i);
                    }
                    headers[i] = val;
                }
                data.add(getArrayTable(headers, table));
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        if (table.getValueAt(i, j) != null) {
                            val = table.getValueAt(i, j).toString();
                        } else {
                            val = "";
//                            System.out.println(val + "i:" + i + " j:" + j);
                        }
                        headers[j] = val;

                    }
//                    System.out.println(i);
                    data.add(getArrayTable(headers, table));
                }
                FileUtils.copyFile(new File("itiexport.xls"), new File(path + ".xls"));
                ExcelMod.modificarExcel(data, new File(path + ".xls"), 0);
            } catch (Exception ex) {
                ret = ex.toString();
            }
        }
        return ret;
    }

    private static String[] getArrayTable(String[] data, JTable table) {
        String temp[] = new String[table.getColumnCount()];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
//            System.out.println(data[i]);
        }

        return temp;
    }

    public static String dateFromFieldtoDB(String string) {
        String stringDate = "";
        String[] split = new String[3];
        split = string.split("\\.");
        stringDate = "" + split[2] + "-" + split[1] + "-" + split[0] + " " + dbConsult("SELECT CURTIME()"); // aqui habia un 20, nose para qe...
        return stringDate;
    }

    public static String rellenarFecha(String string) {
        if (string.length() < 10) {
            if (string.contains(".")) {// 01.12.2019
                String split[] = string.split("\\.");
                if (split[0].length() == 1) {
                    split[0] = "0" + split[0];
                }
                if (split[1].length() == 1) {
                    split[1] = "0" + split[1];
                }
                string = split[0] + "." + split[1] + "." + split[2];
            }
            if (string.contains("/")) {// 01/12/2019
                String split[] = string.split("/");
                if (split[0].length() == 1) {
                    split[0] = "0" + split[0];
                }
                if (split[1].length() == 1) {
                    split[1] = "0" + split[1];
                }
                string = split[0] + "/" + split[1] + "/" + split[2];
            }
            if (string.contains("-")) {
                String split[] = string.split("-");
                if (split[0].length() == 1) {
                    split[0] = "0" + split[0];
                }
                if (split[1].length() == 1) {
                    split[1] = "0" + split[1];
                }
                if (split[2].length() == 1) {
                    split[2] = "0" + split[2];
                }
                string = split[0] + "-" + split[1] + "-" + split[2];
            }
        }
        return string;
    }

    public static String rellenarHora(String string) {
        String splith[] = string.split(":");
        if (splith[0].length() == 1) {
            splith[0] = "0" + splith[0];
        }
        if (splith[1].length() == 1) {
            splith[1] = "0" + splith[1];
        }
        if (splith[1].equals("59")) {
            splith[1] = splith[1] + ":59";
        } else {
            splith[1] = splith[1] + ":00";
        }
        string = splith[0] + ":" + splith[1];

        return string;
    }

    public static String validarDatetimeFormat(String fecha, String hora) {
        fecha = rellenarFecha(fecha);
        hora = rellenarHora(hora);
        if (validarDATE(fecha) && validarTIME(hora)) {
            return "'" + utils.dateFromFieldtoDBwoH(fecha) + " " + hora + "'";
        }

        return null;
    }

    /**
     * Valida fechas con anio de 4 digitos, puede tener separadores de puntos
     * (.), diagonal (/) o guiones (-)
     *
     * @param fecha string con formato dd.mm.yyyy
     * @return string con DATE listo para BD (con todo y comillas sencillas),
     * null en caso de que NO tenga formato adecuado.
     * @since 1.5
     */
    public static String validarDateFormat(String fecha) {
        if (validarDATE(fecha)) {
            return "'" + utils.dateFromFieldtoDBwoH(fecha) + "'";
        }
        return null;
    }

    /**
     * Valida horas con horas(HH) y minutos (mm) (formato24hrs)
     *
     * @param hora string con formato HH:mm
     * @return string con DATETIME listo para BD (con todo y comillas
     * sencillas), null en caso de que NO tenga formato adecuado.
     * @since 1.5
     */
    public static String validarTimeFormat(String hora) {
        if (validarTIME(hora)) {

            return "'" + hora + "'";
        }
        return null;
    }

    /**
     * Valida fechas con anio de 4 digitos, puede tener separadores de puntos
     * (.), diagonal (/) o guiones (-)
     *
     * @param date string con formato dd.mm.yyyy
     * @return booleano con verdadero de coincidir
     * @since 1.5
     */
    public static boolean validarDATE(String date) {
        if (date != null) {
            int totalchars = date.replace(" ", "").replace(".", "").replace("/", "").replace("-", "").length();
            if (totalchars == 8) {
                return true;
            } else {
                if (totalchars >= 4 && totalchars <= 6) {
                    if (date.contains("/")) {
                        String splitted[] = date.split("/");
                        if (splitted.length == 3) {
                            return true;
                        }
                    }
                    if (date.contains("-")) {
                        String splitted[] = date.split("-");
                        if (splitted.length == 3) {
                            return true;
                        }
                    }
                    if (date.contains(".")) {
                        String splitted[] = date.split(".");
                        if (splitted.length == 3) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Valida horas con horas(HH) y minutos (mm) (formato24hrs)
     *
     * @param time string con formato HH:mm
     * @return booleano con verdadero de coincidir
     * @since 1.5
     */
    public static boolean validarTIME(String time) {
        if (time != null) {
            int totalchars = time.replace(" ", "").replace("-", "").replace(":", "").length();
            if (totalchars == 4 || totalchars == 6) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida horas con formato dd.mm.yyyy HH:mm
     *
     * @param datetime string con formato ddmmyyyy HHmm
     * @return booleano con verdadero de coincidir
     * @since 1.5
     */
    public static boolean validarDATETIMEYYYYHHmm(String datetime) {
        if (datetime != null) {
            String[] temp = datetime.split(" ");
            if (temp.length == 2) {
                if (validarDATE(temp[0]) && validarTIME(temp[1])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String executeFile(String file) {
        try {
            Runtime.getRuntime().exec("cmd /c start " + file);
        } catch (IOException ex) {
            return ex.toString();
        }
        return "";
    }

    public static void validarSoloPDFs() {
        String query = "SELECT FileID, "
                + "Nombre, "
                + "Extension,"
                + "APath,"
                + "Archivo,"
                + "UsuarioID,"
                + "IF('" + global.imageneslinux + "' = 1 and Fuente = 1, true, false) as fromlinux "
                + "from workcfiles_tbl "
                + "where Status = true and (Extension = 'jpg' or Extension = 'png') ";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        String respond = "", newfile = "", pathorigen = "";
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            int i = 1;
            while (rs.next()) {

                if (rs.getBoolean("fromlinux")) {
                    pathorigen = Receive(rs.getString("APath"), rs.getString("Nombre"), rs.getString("FileID"));

                } else {
                    pathorigen = rs.getString("APath");
                }
                newfile = new File(pathorigen).getParent() + "\\" + rs.getString("Archivo") + ".pdf";
                if (new File(pathorigen).exists()) {
                    respond = utils.createPDFFromImage(pathorigen, newfile);
                    if (respond.isEmpty()) {
                        utils.dbUpdate("UPDATE workcfiles_tbl SET Nombre = '" + rs.getString("Archivo") + ".pdf', Extension = 'pdf', APath = '" + utils.FiletoDB(newfile) + "' where FileID = '" + rs.getString("FileID") + "'");
                    } else {
                        utils.errorGenerado("documentoswo / generarpdf / response " + respond);
                    }
                } else {
                    utils.dbUpdate("UPDATE workcfiles_tbl SET Status = false, UsuarioEliminaID = 0, FechaElimina = now() where FileID = '" + rs.getString("FileID") + "'");
                }
            }

        } catch (SQLException e) {
            utils.errorGenerado("documentoswo / validarsolopdfs / sqlex " + e.toString());
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static String Receive(String fileR, String nombrefile, String fileid) {
        String SFTPHOST = "192.168.10.3"; //
        int SFTPPORT = 22;
        String SFTPUSER = "wwwuser";
        String SFTPPASS = "Soluciones01"; //
        String SFTPWORKINGDIR = global.xampdir;//"/var/www/html/sanchotenaApi/"
        String pathdestino = global.carpetaevidencias + nombrefile;
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
//        System.out.println("preparing the host information for sftp.");
        boolean entro = false;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
//            System.out.println("Host connected.");
            channel = session.openChannel("sftp");
            channel.connect();
//            System.out.println("sftp channel opened and connected.");
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);

            byte[] buffer = new byte[4096];
            int readCount;
            BufferedInputStream bis = new BufferedInputStream(channelSftp.get(fileR));
//            System.out.println("fileorigen = " + fileR);

            File newFile = new File(pathdestino);//
//            System.out.println("filedestino = " + newFile.getAbsolutePath());
            OutputStream os = new FileOutputStream(newFile);

            while ((readCount = bis.read(buffer)) > 0) {
//                System.out.println("Writing: " + buffer);
                os.write(buffer, 0, readCount);
                entro = true;
            }
            os.close();
            bis.close();

        } catch (Exception ex) {
            System.out.println("Receive origen: '" + fileR + "', destino: '" + pathdestino + "' ex = " + ex.toString());
        } finally {
            if (channelSftp != null) {
                channelSftp.exit();
            }
//            System.out.println("sftp Channel exited.");
            if (channel != null) {
                channel.disconnect();
            }
//            System.out.println("Channel disconnected.");
            if (session != null) {
                session.disconnect();
            }
//            System.out.println("Host Session disconnected.");
        }
        if (entro) {
            utils.dbUpdate("UPDATE workcfiles_tbl SET APath = '" + utils.FiletoDB(pathdestino) + "', Fuente = 0 where FileID = '" + fileid + "'");
            return pathdestino;
        } else {
            return "";
        }
    }

    public static String dbInsertID(String string) {
        String idgen = "";
        Connection con = null;
        try {
            String host = "jdbc:mysql://" + global.host + ":" + global.puerto + "/" + global.db;
            String uName = "root";
            String uPass = "Soluciones01";
            con = DriverManager.getConnection(host, uName, uPass);
            String query = string;
            Statement statement = con.createStatement();

            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();

            while (rs.next()) {
                idgen = rs.getString(1);
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("Error " + e + "\nquery = " + string + "\n\n\n");

            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la coneccion " + ex);
            }
            return e.toString() + "\n" + string;
        }
        return idgen;
    }

    public static String dateFromFieldtoDB2(String string) {
        String stringDate = "";
        String[] split = new String[3];
        split = string.split("\\.");
        stringDate = split[2] + "-" + split[1] + "-" + split[0] + " " + dbConsult("SELECT CURTIME()");
        return stringDate;
    }

    public static String dateFromFieldFormattoDB(String string, String Format) {
        String stringDate = "";
        String[] split = new String[3];
        split = string.split("/");
        if (split[2].length() < 3) //MM/DD/yy
        {
            stringDate = split[2] + "-" + split[1] + "-" + split[0] + " " + dbConsult("SELECT CURTIME()");
        } else {
            stringDate = split[2] + "-" + split[1] + "-" + split[0] + " " + dbConsult("SELECT CURTIME()");
        }
        return stringDate;
    }

    public static String dateFromFieldDiagonaltoDB(String string) {
        String stringDate = "";
        String[] split = new String[3];
        split = string.split("/");
        if (split[2].length() < 3) //MM/DD/yy
        {
            stringDate = split[2] + "-" + split[1] + "-" + split[0] + " " + dbConsult("SELECT CURTIME()");
        } else {
            stringDate = split[2] + "-" + split[1] + "-" + split[0] + " " + dbConsult("SELECT CURTIME()");
        }
        return stringDate;
    }

    public static String createPDFFromImage(String imagePath, String outputFile) {
        PDDocument doc = new PDDocument();
        try {

            InputStream in = new FileInputStream(imagePath);

            BufferedImage bimg = ImageIO.read(in);
            float width = bimg.getWidth();
            float height = bimg.getHeight();

            System.out.println("height = " + height);
            System.out.println("width = " + width);

            //we will add the image to the first page.
            PDPage page = new PDPage(PDRectangle.LETTER);
            doc.addPage(page);
            PDRectangle pageSize = page.getMediaBox();
            float pageancho = pageSize.getWidth();
            float pagealto = pageSize.getHeight();

            // createFromFile is the easiest way with an image file
            // if you already have the image in a BufferedImage, 
            // call LosslessFactory.createFromImage() instead
            PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, doc);

            try (PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
                // contentStream.drawImage(ximage, 20, 20 );
                // better method inspired by http://stackoverflow.com/a/22318681/535646
                // reduce this value if the image is too large
                float scale = 1f;
                if (width < height) {
                    if (height > pagealto) {
                        scale = pagealto / height;
                    }
                } else {
                    if (width > pageancho) {
                        scale = pageancho / width;
                    }
                }
                System.out.println("height*scale = " + height * scale);
                contentStream.drawImage(pdImage, 0, 0, width * scale, height * scale);
            }
            doc.save(outputFile);
            doc.close();
            in.close();

            return "";
        } catch (IOException e) {
            return e.toString();
        } finally {

        }
    }

    public static String dateFromFieldtoDBwoH2(String string) {
        String stringDate = "";
        String[] split = new String[3];
        split = string.split("\\.");
        stringDate = split[2] + "-" + split[1] + "-" + split[0];
        return stringDate;
    }

    public static ArrayList<File> splitPDF(File file) throws IOException {
        //File file = new File("C:/pdfBox/splitpdf_IP.pdf");
        ArrayList<File> splitdocs = new ArrayList<>();
        String dirname = file.getParent();
        String filename = FileUtils.getFileNamePart(file);

        PDDocument doc = PDDocument.load(file);

        //Instantiating Splitter class 
        Splitter splitter = new Splitter();

        //splitting the pages of a PDF document 
        List<PDDocument> Pages = splitter.split(doc);
        if (Pages.size() > 1) {
            //Creating an iterator 
            Iterator<PDDocument> iterator = Pages.listIterator();

            //Saving each page as an individual document 
            int i = 1;
            String filepath = "";
            while (iterator.hasNext()) {
                PDDocument pd = iterator.next();
                filepath = dirname + "\\" + filename + "_" + i + ".pdf";
                pd.save(filepath);
                splitdocs.add(new File(filepath));
                i++;
            }
            //file.delete();
            System.out.println("PDF splitted");
        } else {
            splitdocs.add(file);
        }
        doc.close();
        return splitdocs;
    }

    public static void windowJasper(JasperPrint jasperPrint, JDialog ventanap) {

        JasperViewer jv = null;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("Reporte");
            ImageIcon icon = new ImageIcon("images\\icon.png");
            jv.setIconImage(icon.getImage());
            jv.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jv.setLocationRelativeTo(ventanap);
            jv.setVisible(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void windowJasper(JasperPrint jasperPrint) {

        JasperViewer jv = null;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("Reporte");
            ImageIcon icon = new ImageIcon("images\\icon.png");
            jv.setIconImage(icon.getImage());
            jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jv.setLocationRelativeTo(null);
            jv.setVisible(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarFechas(String itianterior, String wcontid) {
        String upfields = "", ups = "";
        if (!wcontid.equals("0")) {
            String query = "SELECT IFNULL((SELECT (SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = workcontenedores_tbl.RutaID) from workcontenedores_tbl where WContenedorID = '" + wcontid + "' ),0) as destinowc, "
                    + "IFNULL((SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID),0) as destinoiti, "
                    + "IFNULL((SELECT LocacionPUID from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID),0) as origeniti, "
                    + "IF(IFNULL((SELECT (SELECT Pais from locaciones_tbl where LocacionID = LocacionPUID) from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID),0) like '%US%' and IFNULL((SELECT (SELECT Pais from locaciones_tbl where LocacionID = LocacionTOID) from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID),0) like '%MX%', 1, 0)  as crucemx, "
                    + "IF(IFNULL((SELECT (SELECT Pais from locaciones_tbl where LocacionID = LocacionPUID) from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID),0) like '%MX%' and IFNULL((SELECT (SELECT Pais from locaciones_tbl where LocacionID = LocacionTOID) from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID),0) like '%US%', 1, 0)  as cruceus,"
                    + "IFNULL((SELECT (SELECT TipoPatio from patios_tbl where patios_tbl.PatioID = locaciones_tbl.PatioID) from rutas_tbl left join locaciones_tbl on LocacionTOID = LocacionID where rutas_tbl.RutaID = itinerarios_tbl.RutaID),'') as tipopatio  "
                    + "from itinerarios_tbl "
                    + "where ItinerarioID = '" + itianterior + "' and (SELECT IcontID from icont_tbl as icont where icont.ItinerarioID = itinerarios_tbl.ItinerarioID and WcontID = '" + wcontid + "' and icont.Estado = 2 limit 1) is null ";
            Connection con = null;
            Statement state = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                state = con.createStatement();
                rs = state.executeQuery(query);

                while (rs.next()) {

                    if (rs.getString("destinowc").equals(rs.getString("destinoiti"))) {
                        upfields = upfields + ", DeliveryDate = (SELECT IF(FechaLlegada is null, Carga, FechaLlegada) from itinerarios_tbl where ItinerarioID = '" + itianterior + "')";
                        ups = ups + "UPDATE fechaswo SET fecha = (SELECT DATE(IF(FechaLlegada is null, Carga, FechaLlegada)) from itinerarios_tbl where ItinerarioID = '" + itianterior + "'), "
                                + "hora = (SELECT TIME(IF(FechaLlegada is null, Carga, FechaLlegada)) from itinerarios_tbl where ItinerarioID = '" + itianterior + "'), "
                                + "fechahora = (SELECT IF(FechaLlegada is null, Carga, FechaLlegada) from itinerarios_tbl where ItinerarioID = '" + itianterior + "') "
                                + "where idfecha in (SELECT deff.id from deffechaswo as deff where fechareq = 101) and wcontfk = '" + wcontid + "' and status = true;";
                    }

                    if (rs.getString("destinowc").equals(rs.getString("origeniti"))) {
                        upfields = upfields + ", PUCliente = (SELECT Carga from itinerarios_tbl where ItinerarioID = '" + itianterior + "')";
                        ups = ups + "UPDATE fechaswo SET fecha = (SELECT DATE(IF(FechaLlegada is null, Carga, FechaLlegada)) from itinerarios_tbl where ItinerarioID = '" + itianterior + "'), "
                                + "hora = (SELECT TIME(IF(FechaLlegada is null, Carga, FechaLlegada)) from itinerarios_tbl where ItinerarioID = '" + itianterior + "'), "
                                + "fechahora = (SELECT IF(FechaLlegada is null, Carga, FechaLlegada) from itinerarios_tbl where ItinerarioID = '" + itianterior + "') "
                                + "where idfecha in (SELECT deff.id from deffechaswo as deff where fechareq = 102) and wcontfk = '" + wcontid + "' and status = true;";
                    }

//                    if (rs.getString("tipopatio").equals("SD") && rs.getInt("cruceus") != 1) {
//                        upfields = upfields + ", USArrDate =(SELECT IF(FechaLlegada is null, Carga, FechaLlegada) from itinerarios_tbl where ItinerarioID = '" + itianterior + "')";
//                    }
                    if (rs.getInt("crucemx") == 1 || rs.getInt("cruceus") == 1) {
                        ups = ups + "UPDATE fechaswo SET fecha = (SELECT DATE(IF(FechaLlegada is null, Carga, FechaLlegada)) from itinerarios_tbl where ItinerarioID = '" + itianterior + "'), "
                                + "hora = (SELECT TIME(IF(FechaLlegada is null, Carga, FechaLlegada)) from itinerarios_tbl where ItinerarioID = '" + itianterior + "'), "
                                + "fechahora = (SELECT IF(FechaLlegada is null, Carga, FechaLlegada) from itinerarios_tbl where ItinerarioID = '" + itianterior + "') "
                                + "where idfecha in (SELECT deff.id from deffechaswo as deff where fechareq = 103) and wcontfk = '" + wcontid + "' and status = true;";
//                        upfields = upfields + ", XtoMX =(SELECT IF(FechaLlegada is null, Carga, FechaLlegada) from itinerarios_tbl where ItinerarioID = '" + itianterior + "')";
                    }

//                    if (rs.getInt("cruceus") == 1) {
//                        upfields = upfields + ", XtoUS =(SELECT IF(FechaLlegada is null, Carga, FechaLlegada) from itinerarios_tbl where ItinerarioID = '" + itianterior + "')";
//                    }
                }
                if (!upfields.isEmpty()) {
                    utils.dbUpdate("UPDATE workcontenedores_tbl SET Status = Status" + upfields + " where WContenedorID = '" + wcontid + "'");
                    utils.dbUpdate(ups);
                }

            } catch (SQLException e) {
                utils.errorGenerado("utils / actualizarFechas / sql ex " + e.toString());
            } catch (Exception e) {
                utils.errorGenerado("utils / actualizarFechas / ex " + e.toString());
            } finally {
                utils.closeAllConnections(con, state, rs);
            }
        }
    }

    public static String crearSubject(String clienteid, String facturaid) {
        String subject = utils.dbConsult("SELECT Subjectt from clientes_tbl where ClienteID = '" + clienteid + "'");
        subject = subject.replace("{#factura}", utils.dbConsult("SELECT NoFactura from facturas_tbl where FacturaID = '" + facturaid + "'"));
        subject = subject.replace("{nomcliente}", utils.dbConsult("SELECT RazonSocial from clientes_tbl where ClienteID = '" + clienteid + "'"));
        subject = subject.replace("{referencia}", utils.dbConsult("SELECT Referencia from conceptosf_tbl where FacturaID = '" + facturaid + "'"));
        subject = subject.replace("{#remolque}", utils.dbConsult("SELECT Contenedorc from conceptosf_tbl where FacturaID = '" + facturaid + "'"));
        subject = subject.replace("{#po}", utils.dbConsult("SELECT PONumber from workcontenedores_tbl "
                + "inner join conceptosf_tbl on conceptosf_tbl.WCID = workcontenedores_tbl.WContenedorID "
                + "where conceptosf_tbl.FacturaID = '" + facturaid + "'"));
        return subject;
    }

    public static String descargaMasivaInvoice(String filedir, String clienteid, String folioi, String foliof, boolean enviocorreo, boolean moverxml, boolean body) throws IOException {
        String err = "";

        ArrayList<String> paths = new ArrayList<>();
        ArrayList<String[]> facturasid = new ArrayList<>();
        facturasid = obtenerFacturas(clienteid, folioi, foliof);

        for (int it = 0; it < facturasid.size(); it++) {
//            System.out.println("uso = "+facturasid.get(it)[0]);
            String fileuserpdf = facturasid.get(it)[1] + ".pdf";
            String fileuserxml = facturasid.get(it)[1] + ".xml";
            String filesyspdf = facturasid.get(it)[0] + ".pdf";
            String filesysxml = facturasid.get(it)[0] + ".xml";
            String esfac = facturasid.get(it)[2];

            PDFMergerUtility ut = new PDFMergerUtility();
            if (!new File(global.rectim + "PDF\\" + filesyspdf).exists() && esfac.equals("0")) {
                utils.generaPDFInvoice(facturasid.get(it)[0]);
            }
            if (moverxml) {
                if (esfac.equals("1")) {
                    FileUtils.copyFile(global.rectim + "CFDI\\" + filesysxml, filedir + "\\" + fileuserxml);
                }
            }
            if (new File(global.rectim + "PDF\\" + filesyspdf).exists()) {
                try {
                    ut.addSource(global.rectim + "PDF\\" + filesyspdf);
                } catch (FileNotFoundException ex) {
                    err = err + "\nFileNotFoundException " + ex.toString();
                }
            }
            String query = "SELECT "
                    + "FileID, "
                    + "IFNULL((SELECT Contenedor from workcontenedores_tbl where WcontenedorID = WContID), FileID) as contenedor, "
                    + "Nombre, "
                    + "'pdf', "
                    + "CONCAT(FileID, '.pdf') as docnam, "
                    + "IFNULL(FileID,0) as idfile,"
                    + "APath "
                    + "from workcfiles_tbl as wcf "
                    + "where wcf.Status = true and wcf.FacturaID = '" + facturasid.get(it)[0] + "' and wcf.EvidenciaID > 0 "
                    + "order by EvidenciaID ";

            Connection con = null;
            Statement state = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                state = con.createStatement();
                rs = state.executeQuery(query);

                String fac = "", archivosistema = "", archivousuario = "";
                int i = 0;
                boolean islast = false;
                boolean entro = false;
                while (rs.next()) {
//                    System.out.println("entro");
                    try {
                        ut.addSource(rs.getString("APath"));
                    } catch (FileNotFoundException ex) {
                        err = err + "\nFileNotFoundException2 " + ex.toString();
                    }
                    i++;
                    if (i % 10 == 0 && !rs.isLast()) {
                        ut.setDestinationFileName(filedir + "\\" + fileuserpdf);
                        ut.mergeDocuments(null);
                        ut = new PDFMergerUtility();
                        ut.addSource(filedir + "\\" + fileuserpdf);
                    }
                    entro = true;
                }

                if (!entro) {
//                    System.out.println("post no entro");
                    try {
                        if (new File(global.rectim + "PDF\\" + filesyspdf).exists()) {//debe tener pdf para que pueda continuar.
//                            ut.addSource(global.rectim + "PDF\\" + filesyspdf);
                            ut.setDestinationFileName(filedir + "\\" + fileuserpdf);
                            ut.mergeDocuments(null);
                            paths.add(filedir + "\\" + fileuserpdf);
                            if (esfac.equals("1")) {
                                try {
                                    paths.add(filedir + "\\" + fileuserxml);
                                } catch (Exception ex) {
                                    err = err + "\nFileNotFoundException " + ex.toString();
                                }
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        err = err + "\nFileNotFoundException " + ex.toString();
                    }
                } else {
//                    System.out.println("post entro");
                    ut.setDestinationFileName(filedir + "\\" + fileuserpdf);
                    ut.mergeDocuments(null);
                    paths.add(filedir + "\\" + fileuserpdf);
                    if (esfac.equals("1")) {
                        try {
                            paths.add(filedir + "\\" + fileuserxml);
                        } catch (Exception ex) {
                            err = err + "\nFileNotFoundException " + ex.toString();
                        }
                    }
                }

            } catch (SQLException e) {
                err = err + "\nsqlex" + e.toString();
                //utils.errorGenerado("utils / descargamasivainvoice / sqlex "+err);
            } catch (Exception e) {
                err = err + "\nex" + e.toString();
                //utils.errorGenerado("utils / descargamasivainvoice / sqlex "+err);
            } finally {
                utils.closeAllConnections(con, state, rs);
            }
        }
        if (enviocorreo) {

            String facs = "";
            if (folioi.equals(foliof)) {
                facs = utils.dbConsult("SELECT NoFactura from facturas_tbl where FacturaID = '" + folioi + "'");
            } else {
                facs = utils.dbConsult("SELECT CONCAT((SELECT NoFactura from facturas_tbl where FacturaID = '" + folioi + "'), ' - ', (SELECT NoFactura from facturas_tbl where FacturaID = '" + foliof + "'))");
            }
            if (body) {
                System.out.println("Utils");
                SendEmail.normalEmail(utils.dbConsult("SELECT CorreoF from clientes_tbl where ClienteID = '" + clienteid + "'"), paths, utils.txtToMail(utils.openTxt("bmail.txt")), crearSubject(clienteid, folioi), Integer.parseInt(utils.dbConsult("SELECT CorreoFacturacion from utils_tbl where UtilID = 1")), null);
            } else {
                SendEmail.normalEmail(utils.dbConsult("SELECT CorreoF from clientes_tbl where ClienteID = '" + clienteid + "'"), paths, "", crearSubject(clienteid, folioi), Integer.parseInt(utils.dbConsult("SELECT CorreoFacturacion from utils_tbl where UtilID = 1")), null);
            }
        }

        return err;
    }

    private static ArrayList<String[]> obtenerFacturas(String clienteid, String folioi, String foliof) {
        ArrayList<String[]> temp = new ArrayList<>();
        String filtroc = "";
        if (!clienteid.equals("0")) {
            filtroc = " and IF(ClienteFacturadoID = 0, ClienteID, ClienteFacturadoID) = '" + clienteid + "' ";
        }
        String query = "SELECT facturas_tbl.FacturaID as facid,"
                + "IF(NoFactura = '', FacturaID, NoFactura) as numfac,"
                + "IF(Factura, 1, 0) as esfactura "
                + "from facturas_tbl "
                + "where facturas_tbl.Status > 0 and facturas_tbl.FacturaID >= '" + folioi + "' and facturas_tbl.FacturaID <= '" + foliof + "' " + filtroc
                + "order by facid";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                temp.add(new String[]{rs.getString("facid"), rs.getString("numfac"), rs.getString("esfactura")});
//                System.out.println("carga = "+rs.getString("facid"));
            }
        } catch (SQLException e) {
            utils.errorGenerado("utils / obtenerFacturas / sql ex " + e.toString());
        } catch (Exception e) {
            utils.errorGenerado("utils / obtenerFacturas / ex " + e.toString());
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
        return temp;
    }
//
//    public static void eliminarPost(String itiid) {
//        if (true) {
//            //46 - 29 - 34
//            //!utils.dbConsult("SELECT IFNULL((SELECT ItinerarioID from itinerarios_tbl where (ChoferID = 46 or ChoferID = 29 or ChoferID = 34) and ItinerarioID = '" + itiid + "'),0)").equals("0")
//            if (true) {//empezamos con estos choferes
//                utils.dbUpdate("UPDATE itinerarios_tbl SET EnvioPost = 1, TipoPost = 3 where ItinerarioID = '" + itiid + "'");//insercion
//
//            }
//        }
//    }

    //dataPost[]:{"option":"Programar","selecUnineg":"<UNIDAD DE NEGOCIO>","track":"{"<UNIDAD>":"Test 678"}","horacita":"<HORA DE CITA>","operador":"<NOMBRE DE OPERADOR>","tipoCarga":"{"tipoCarga":"<CARGA (B,C,V)>","caja":<NUMERO DE CAJA>,"sello1":<NUMERO DE SELLO 1>,"sello2":<NUMERO DE SELLO 2>,"sello3":<NUMERO DE SELLO 3>}","billto":"{"nom_billto":"<BILLTO>","nom_blClt":"<CLIENTE> "}","tp":"<TIPO DE VIAJE>","from":"{"desc_des":"<ORIGEN>"}","to":"{"desc_des":"<DESTINO>"}","user":"<USUARIO>","WorkOrder":"<NUMERO DE WORKORDER>"}
    public static void definirTipoItinerario(String itiid) {
        ArrayList<String> tipos = new ArrayList<>();
        utils.dbInsert("DELETE from itinerariostm_tbl where ItinerarioID = '" + itiid + "'");
        String destinowc = utils.dbConsult("SELECT IFNULL((SELECT (SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = workcontenedores_tbl.RutaID) from workcontenedores_tbl where WContenedorID = (SELECT WContFK from itinerarios_tbl where ItinerarioID = '" + itiid + "') ),0)");
        String destinoiti = utils.dbConsult("SELECT IFNULL((SELECT (SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) from itinerarios_tbl where ItinerarioID = '" + itiid + "' ),0)");
        String origeniti = utils.dbConsult("SELECT IFNULL((SELECT (SELECT LocacionPUID from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) from itinerarios_tbl where ItinerarioID = '" + itiid + "' ),0)");
        if (!utils.dbConsult("SELECT IFNULL((SELECT LocacionID from locaciones_tbl where LocacionID = '" + origeniti + "' and (Puerto = true or Tren = true) ),0)").equals("0")) {//reviso si es bajada

            utils.dbInsert("INSERT INTO  itinerariostm_tbl (ItinerarioID, TipoMovimientoID) VALUES('" + itiid + "', 1) ");
            tipos.add("1");//tiposmovimiento_tbl
        }
        if (!utils.dbConsult("SELECT IFNULL((SELECT LocacionID from locaciones_tbl where LocacionID = '" + destinoiti + "' and (Puerto = true or Tren = true) ),0)").equals("0")) {//reviso si es subida
            utils.dbInsert("INSERT INTO  itinerariostm_tbl (ItinerarioID, TipoMovimientoID) VALUES('" + itiid + "', 2) ");
            tipos.add("2");//tiposmovimiento_tbl
        }
        if (destinoiti.equals(destinowc) && utils.dbConsult("SELECT IFNULL((SELECT LocacionID from locaciones_tbl where LocacionID = '" + destinoiti + "' and (Puerto = true or Tren = true) ),0)").equals("0") && utils.dbConsult("SELECT IFNULL((SELECT LocacionID from locaciones_tbl where LocacionID = '" + origeniti + "' and (Puerto = true or Tren = true) ),0)").equals("0")) {//entrega con el cliente
            utils.dbInsert("INSERT INTO  itinerariostm_tbl (ItinerarioID, TipoMovimientoID) VALUES('" + itiid + "', 3) ");
            tipos.add("3");
        }
        if (!utils.dbConsult("SELECT IFNULL((SELECT LocacionID from locaciones_tbl where LocacionID = '" + origeniti + "' and Pais like '%US%'),0)").equals("0")) {//cruce mx
            if (!utils.dbConsult("SELECT IFNULL((SELECT LocacionID from locaciones_tbl where LocacionID = '" + destinoiti + "' and Pais like '%MX%'),0)").equals("0")) {
                utils.dbInsert("INSERT INTO  itinerariostm_tbl (ItinerarioID, TipoMovimientoID) VALUES('" + itiid + "', 4) ");
                tipos.add("4");
            }
        }
        if (!utils.dbConsult("SELECT IFNULL((SELECT LocacionID from locaciones_tbl where LocacionID = '" + origeniti + "' and Pais like '%MX%'),0)").equals("0")) {// cruce us
            if (!utils.dbConsult("SELECT IFNULL((SELECT LocacionID from locaciones_tbl where LocacionID = '" + destinoiti + "' and Pais like '%US%'),0)").equals("0")) {
                utils.dbInsert("INSERT INTO  itinerariostm_tbl (ItinerarioID, TipoMovimientoID) VALUES('" + itiid + "', 5) ");
                tipos.add("5");
            }
        }
        if (!utils.dbConsult("SELECT IFNULL((SELECT LocacionID from locaciones_tbl where LocacionID = '" + origeniti + "' and Pais like '%US%'),0)").equals("0")) {//MovUS
            if (!utils.dbConsult("SELECT IFNULL((SELECT LocacionID from locaciones_tbl where LocacionID = '" + destinoiti + "' and Pais like '%US%'),0)").equals("0")) {
                utils.dbInsert("INSERT INTO  itinerariostm_tbl (ItinerarioID, TipoMovimientoID) VALUES('" + itiid + "', 7) ");
                tipos.add("7");
            }
        }

        if (tipos.size() == 0) {// local
            utils.dbInsert("INSERT INTO  itinerariostm_tbl (ItinerarioID, TipoMovimientoID) VALUES('" + itiid + "', 6) ");
            tipos.add("6");
        }
        if (global.cambiodocs == 0) {
//            documentosObligatorios(itiid);
        } else {

        }
    }

    public static void documentosCliente(String wcid) {
        if (!utils.dbConsult("SELECT IFNULL((SELECT ItinerarioID from itinerarios_tbl where WContFK = '" + wcid + "' and Status > 0),0)").equals("0")) {
            utils.dbInsert("DELETE FROM evidenciaswc_tbl where WContFK = '" + wcid + "'");
            String clienteid = utils.dbConsult("SELECT clientefk from workcontenedores_tbl where WContenedorID = '" + wcid + "'");
            String rutaid = utils.dbConsult("SELECT RutaID from workcontenedores_tbl where WContenedorID = '" + wcid + "'");
            String query = "SELECT EDesgloseID,"
                    + "evc.EvidenciaCID, "
                    + "evc.EvidenciaID as evid,"
                    + "EstadoC,"
                    + "RutaID  "
                    + "from evidenciasclientes_tbl as evc "
                    + "left join evidenciasdesglose_tbl as evd on evc.EvidenciaCID = evd.EvidenciaCID "
                    + "where evc.Status = true and evc.ClienteID = '" + clienteid + "'  and evd.Status = true ";
            Connection con = null;
            Statement state = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                state = con.createStatement();
                rs = state.executeQuery(query);
                while (rs.next()) {
                    if (rutaid.equals(rs.getString("RutaID")) || rs.getString("RutaID").equals("0")) {
                        utils.dbInsert("INSERT INTO evidenciaswc_tbl(EvidenciaID, WContFK, Status,EDesgloseFK) "
                                + "VALUES('" + rs.getString("evid") + "', '" + wcid + "', true, '" + rs.getString("EDesgloseID") + "')");
                    }
                }

            } catch (SQLException e) {
                utils.errorGenerado("utils / documentosObligatorios / sql ex " + e.toString());
            } catch (Exception e) {
                utils.errorGenerado("utils / documentosObligatorios / ex " + e.toString());
            } finally {
                utils.closeAllConnections(con, state, rs);
            }
        }
    }
//
//    public static String[] validarMicroDocs(String rutaiti, String estadoc, String wcid, String itiid) {
//
//        String mensaje[] = {"0", ""}, insertiti = "", valueiti = "";//uso mensaje para transportar el resultado, texto normal = 'error y no insertar', numero = 'ok y regreso evidenciasiti_tbl.EvidenciaitiID', vacio = 'ok pero no tenia documento'
//        if (global.usuario > 1) {
//            if (!rutaiti.equals("0")) {
//                mensaje[1] = utils.dbConsult("SELECT IFNULL((SELECT GROUP_CONCAT(CONCAT((SELECT Nombre from locaciones_tbl where LocacionID = LocacionPUFK),' - ',(SELECT Nombre from locaciones_tbl where LocacionID = LocacionTOFK),' ',IF(EstadoCarga = 0,'Empty','Load')) SEPARATOR '\n') from destinosrutas_tbl where RutaFK = (SELECT RutaID from workcontenedores_tbl where WContenedorID = '" + wcid + "') and Status = true),0)");
//                if (!mensaje[1].equals("0")) {//
//                    if (estadoc.isEmpty() && !itiid.equals("0")) {
//                        estadoc = utils.dbConsult("SELECT IF(Bobtail = true, 2, Estado) from itinerarios_tbl where ItinerarioID = '" + itiid + "'");
//                    }
//                    if (!estadoc.equals("2") && !estadoc.isEmpty()) {
//                        String microev[] = utils.dbConsult("SELECT IFNULL((SELECT CONCAT(DestinoCID,';',EvidenciaOperadorID) from destinosrutas_tbl where RutaFK = (SELECT RutaID from workcontenedores_tbl where WContenedorID = '" + wcid + "') and Status = true and LocacionPUFK = (SELECT LocacionPUID from rutas_tbl where rutas_tbl.RutaID = '" + rutaiti + "') and LocacionTOFK = (SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = '" + rutaiti + "') and EstadoCarga = " + estadoc + "),'0;0') ").split(";");
//                        if (!microev[0].equals("0")) {
//                            if (!microev[1].equals("0")) {
//                                if (!itiid.equals("0")) {
//                                    utils.dbInsert("DELETE from evidenciasiti_tbl where ItinerarioID = '" + itiid + "'");
//                                    insertiti = ", ItinerarioID";
//                                    valueiti = ", '" + itiid + "' ";
//                                }
//                                mensaje[0] = "1";
//                                mensaje[1] = utils.dbInsert("INSERT INTO evidenciasiti_tbl(EDesgloseFK, EvidenciaID, WcontFK, Status" + insertiti + ") "
//                                        + "VALUES('" + microev[0] + "','" + microev[1] + "', '" + wcid + "', true" + valueiti + ")");
//                            } else {
//                                mensaje[0] = "1";
//                                mensaje[1] = "";
//                            }
//                        } else {
//                            mensaje[0] = "0";
//                            mensaje[1] = "El movimiento ingresado no se encuentra entre los movimientos permitidos";
//                        }
//                    } else {
//                        mensaje[0] = "1";
//                        mensaje[1] = "movimiento botando";
//                    }
//                } else {
//                    mensaje[0] = "1";
//                }
//            }
//        } else {
//            mensaje[0] = "1";
//        }
//        return mensaje;
//    }
//
//    public static void documentosObligatorios(String itiid) {
//        String clienteid = utils.dbConsult("SELECT clientefk from workcontenedores_tbl left join itinerarios_tbl on WcontFK = WContenedorID where ItinerarioID = '" + itiid + "'");
//        String estadoc = utils.dbConsult("SELECT Estado from itinerarios_tbl where ItinerarioID = '" + itiid + "'");
//        String rutaid = utils.dbConsult("SELECT wc.RutaID from itinerarios_tbl left join workcontenedores_tbl as wc on WContenedorID = WContFK where ItinerarioID = '" + itiid + "'");
//        String query = "SELECT EDesgloseID,"
//                + "evc.EvidenciaCID, "
//                + "evc.EvidenciaID as evid,"
//                + "EstadoC,"
//                + "RutaID  "
//                + "from evidenciasclientes_tbl as evc "
//                + "left join evidenciasdesglose_tbl as evd on evc.EvidenciaCID = evd.EvidenciaCID "
//                + "where evc.Status = true and evc.ClienteID = '" + clienteid + "'  and evd.Status = true and TipoMovimientoID in (SELECT iti.TipoMovimientoID from itinerariostm_tbl as iti where iti.ItinerarioID = '" + itiid + "')";
//        Connection con = null;
//        Statement state = null;
//        ResultSet rs = null;
//        try {
//            con = utils.startConnection();
//            state = con.createStatement();
//            rs = state.executeQuery(query);
//            utils.dbInsert("DELETE FROM evidenciasiti_tbl where ItinerarioID = '" + itiid + "'");
//            while (rs.next()) {
//                if (estadoc.equals(rs.getString("EstadoC")) || rs.getString("EstadoC").equals("2")) {
//                    if (rutaid.equals(rs.getString("RutaID")) || rs.getString("RutaID").equals("0")) {
//                        utils.dbInsert("INSERT INTO evidenciasiti_tbl(EvidenciaID, ItinerarioID, Status,EDesgloseFK) VALUES('" + rs.getString("evid") + "', '" + itiid + "', true, '" + rs.getString("EDesgloseID") + "')");
//                    }
//                }
//            }
//
//        } catch (SQLException e) {
//            utils.errorGenerado("utils / documentosObligatorios / sql ex " + e.toString());
//        } catch (Exception e) {
//            utils.errorGenerado("utils / documentosObligatorios / ex " + e.toString());
//        } finally {
//            utils.closeAllConnections(con, state, rs);
//        }
//    }

    public static void errorGenerado(String error) {
        if (!new File(global.path + "Errores").exists()) {
            new File(global.path + "Errores").mkdir();
        }
        String fechaHoraFormateada = "";
        try {
            Date fechaHoraActual = new Date();
            // Definir el formato deseado (yyymmddHHmmss)
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
            // Formatear la fecha y hora actual
            fechaHoraFormateada = formato.format(fechaHoraActual);

        } catch (Exception e) {
            System.out.println("Error al mostrar el error e = " + e);
        }
        GenTxt.Generate(global.path + "Errores\\" + global.usuario + "-" + fechaHoraFormateada + ".txt", error);

    }
//    SELECT IFNULL((SELECT WContenedorID from workcontenedores_tbl where Contenedor = filtrarAlfaNum('intentoingreso') and Status = true and WContenedorID != 'actual' and StatusAdmin = 1),0);
//
//--SELECT IFNULL((SELECT prog.ProgramacionID from programacion_tbl as prog left join progcont_tbl on prog.ProgramacionID = progcont_tbl.ProgramacionID where WContenedorID = 'actual' and prog.Status = 1),0);
//
//--SELECT IFNULL((SELECT ItinerarioID from itinerarios_tbl where WContFK = 'actual' and Status = 1),0)

    public static String revisarActivo(String contenedor, String wcont) {
        String regreso = "";
        if (utils.dbConsult("SELECT IF(validarF('remorepetido'), 1, 0)").equals("1")) {
            String contrev = utils.dbConsult("SELECT IFNULL((SELECT GROUP_CONCAT(WContenedorID) from workcontenedores_tbl where Contenedor = filtrarAlfaNum('" + contenedor + "') and Status = true and WContenedorID != '" + wcont + "' and StatusAdmin = 1 limit 1),0)");
            if (!contrev.equals("0") && contrev.contains(",")) {

                regreso = "Contenedor activo #" + contrev + " tipo " + utils.dbConsult("SELECT GROUP_CONCAT(DISTINCT (SELECT Nombre from tipooperacion_tbl where tipooperacion_tbl.TipoID = TipoOperacion)) from workcontenedores_tbl where WContenedorID in (" + contrev + ")") + ":\n";

                String progrev = utils.dbConsult("SELECT IFNULL((SELECT GROUP_CONCAT(DISTINCT prog.ProgramacionID) from programacion_tbl as prog left join progcont_tbl on prog.ProgramacionID = progcont_tbl.ProgramacionID where WContenedorID in (" + contrev + ") and prog.Status = 1 limit 1),0)");
                if (!progrev.equals("0")) {
                    regreso = regreso + "Revisar programacion #" + progrev + "\n";
                }
                String itirev = utils.dbConsult("SELECT IFNULL((SELECT GROUP_CONCAT(DISTINCT ItinerarioID) from itinerarios_tbl where ItinerarioID in (SELECT icont_tbl.ItinerarioID from icont_tbl where icont_tbl.WContID in (" + contrev + ") and icont_tbl.Status = true) and Status = 1),0)");
                if (!itirev.equals("0")) {
                    if (regreso.isEmpty()) {
                        regreso = "Contenedor activo #" + contrev + " tipo " + utils.dbConsult("SELECT GROUP_CONCAT(DISTINCT (SELECT Nombre from tipooperacion_tbl where tipooperacion_tbl.TipoID = TipoOperacion)) from workcontenedores_tbl where WContenedorID in (" + contrev + ")") + ":\n";
                    }

                    regreso = regreso + "Revisar Itinerario(Driver Move) #" + itirev + "\n";
                }

                if (!regreso.isEmpty()) {
                    regreso = "Ya existe un contenedor activo con esta numeracion, es necesario cerrar el contenedor anterior para darlo de alta nuevamente.\n" + regreso;
                }

            }
        }
        return regreso;
    }

    public static String revisionReferencia(String wcont, String ref) {
        if (global.nivel > 1) {
            String worepetido = dbConsult("SELECT IF(IFNULL((SELECT WContenedorID from workcontenedores_tbl where CusRef = TRIM('" + ref + "') and WContenedorID != '" + wcont + "' and EstadoCarga = 1 and Status = true and StatusAdmin = 1 limit 1),0) = 0 or TRIM('" + ref + "') = 'pending', 1, 0)");
            if (worepetido.equals("1")) {
                return "";
            }
            return "Referencia repetida: " + utils.dbConsult("SELECT CONCAT('WO: ',WContenedorID, ', Customer:', (SELECT NComercial from clientes_tbl where clientes_tbl.ClienteID = clientefk), ', Container: ',Contenedor ) from workcontenedores_tbl where CusRef = TRIM('" + ref + "') and Status = true limit 1");
        }
        return "";
    }

    public static boolean revisionStatusWO(String wcont) {
        boolean resp = true;

        if (ordenCerrada(wcont)) {
            resp = false;
        }

// para la ccp tiene variaciones diferentes, depende que quieran modificar, si esta cerrado y que tipo de facturacion sea el cliente.
        if (woFacturada(wcont)) {
            resp = false;
        }

        return resp;
    }

    public static boolean tieneCCP(String wcont) {// true = si tiene, false = no tiene
        String statwo = dbConsult("SELECT IFNULL((SELECT id from wconticp_tbl where WContID = '" + wcont + "' and Status = true and (SELECT FolioFiscalcp from cartasporte_tbl as ccp where ccp.CartaPorteID = wconticp_tbl.CartaPorteID)!= '' limit 1),0)");
        if (!statwo.equals("0")) {//
            return true;
        }
        return false;
    }

    public static boolean tieneCCPencabezado(String wo) {// true = si tiene, false = no tiene
        String statwo = dbConsult("SELECT IFNULL((SELECT id from wconticp_tbl where WContID in (SELECT WContenedorID from workcontenedores_tbl where WorkOrderID = " + wo + " and workcontenedores_tbl.Status = true) and Status = true and (SELECT FolioFiscalcp from cartasporte_tbl as ccp where ccp.CartaPorteID = wconticp_tbl.CartaPorteID)!= '' limit 1),0)");
        if (!statwo.equals("0")) {//
            return true;
        }
        return false;
    }

    public static int facturacionCliente(String wcont) {//0 = mexicana, 1 = americana
        return Integer.parseInt(utils.dbConsult("SELECT IFNULL((SELECT TipoFac from clientes_tbl where ClienteID = (SELECT wc.clientefk from workcontenedores_tbl as wc where WContenedorID =  '" + wcont + "')),1)"));
    }

    public static int facturacionClienteEncabezado(String wo) {//0 = mexicana, 1 = americana
        return Integer.parseInt(utils.dbConsult("SELECT IFNULL((SELECT TipoFac from clientes_tbl left join workorder_tbl on clientes_tbl.ClienteID = workorder_tbl.ClienteID where WorkID = '" + wo + "'),1)"));
    }

    public static boolean ordenCerrada(String wcont) {
        if (global.nivel > 1) {
            String statwo = dbConsult("SELECT IF(StatusAdmin >= 2, 1, 0) from workcontenedores_tbl where WContenedorID = '" + wcont + "'");
            if (statwo.equals("1")) {//si esta cerrada la orden regresa falso (no se puede modificar), si es nivel admin ignora esta validacion
                return true;
            }
        }
        return false;
    }

    public static boolean woFacturada(String wcont) {
//        String facturacion = utils.dbConsult("SELECT TipoFac from clientes_tbl left join rutas_tbl on clientes_tbl.ClienteID = rutas_tbl.ClienteID where rutas_tbl.RutaID = (SELECT wc.RutaID from workcontenedores_tbl as wc where WContenedorID =  '" + wcont + "')");
        if (global.usuario != 1) {
            String statwo = dbConsult("SELECT IF(StatusAdmin >= 3, 1, 0 ) from workcontenedores_tbl where WContenedorID = '" + wcont + "'");
            if (statwo.equals("1")) {//si es mayor igual a 3, la orden esta cerrada (no se puede modificar), solo usuario desarrollo puede mover esta orden
                return true;
            }
        }
        return false;
    }

    public static boolean woFacturada(String wcont, String rentaid) {
//        String facturacion = utils.dbConsult("SELECT TipoFac from clientes_tbl left join rutas_tbl on clientes_tbl.ClienteID = rutas_tbl.ClienteID where rutas_tbl.RutaID = (SELECT wc.RutaID from workcontenedores_tbl as wc where WContenedorID =  '" + wcont + "')");
        if (global.usuario != 1) {
            if (!wcont.equals("0")) {
                String statwo = dbConsult("SELECT IF(StatusAdmin >= 3, 1, 0 ) from workcontenedores_tbl where WContenedorID = '" + wcont + "'");
                if (statwo.equals("1")) {//si es mayor igual a 3, la orden esta cerrada (no se puede modificar), solo usuario desarrollo puede mover esta orden
                    return true;
                }
            } else {
                String statwo = dbConsult("SELECT IF(status >= 3, 1, 0 ) from rentaunidades_tbl where id = '" + rentaid + "'");
                if (statwo.equals("1")) {//si es mayor igual a 3, la orden esta cerrada (no se puede modificar), solo usuario desarrollo puede mover esta orden
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean encabezadoFacturado(String wo) {
//        String facturacion = utils.dbConsult("SELECT TipoFac from clientes_tbl left join rutas_tbl on clientes_tbl.ClienteID = rutas_tbl.ClienteID where rutas_tbl.RutaID = (SELECT wc.RutaID from workcontenedores_tbl as wc where WContenedorID =  '" + wcont + "')");
        if (global.usuario != 1) {
            String statwo = dbConsult("SELECT IFNULL((SELECT WContenedorID from workcontenedores_tbl where WorkOrderID = '" + wo + "' and StatusAdmin >=3 and Status = true limit 1),0)");
            if (!statwo.equals("0")) {//si es mayor igual a 3, la orden esta cerrada (no se puede modificar), solo usuario desarrollo puede mover esta orden
                return true;
            }
        }
        return false;
    }

    public static boolean validarServicio(String itiid, String camion, String fecha, String origen, Component wind, String choferid) {
//        if (global.nivel > 1) {//
//            if (!camion.equals("0") && !fecha.isEmpty() && !origen.equals("0")) {
//                if (utils.dbConsult("SELECT IF(NoEconomico like '%mul%', 0, 1) from camiones_tbl where CamionID = '" + camion + "'").equals("1")) {
//                    if (utils.dbConsult("SELECT IF(Externo = true, 0, 1) from choferes_tbl where ChoferID = '" + choferid + "'").equals("1")) {
//                        if (!utils.dbConsult("SELECT IFNULL((SELECT (SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) from itinerarios_tbl where CamionID = '" + camion + "' and Carga >= DATE_ADD(now(), interval -8 DAY) and Carga < '" + fecha + "' and ItinerarioID != '" + itiid + "' and Status >=1 order by Carga desc limit 1),'" + origen + "')").equals(origen)) {
//                            JOptionPane.showMessageDialog(wind, "El origen de este camion no coincide con el destino del movimiento anterior.\nFavor de revisar el acceso de 'Daily Disp', para mas informacion", "Error", JOptionPane.ERROR_MESSAGE);
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
        return true;
    }

    public static void cargarInspeccion(String id) {
        String query = "SELECT ListadoID, Nombre,true from listadoinspecciones_tbl where Status = true";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                utils.dbInsert("INSERT INTO inspecciones_tbl (ItinerarioID,ListadoID,Fecha,Bien) VALUES('" + id + "','" + rs.getString(1) + "',(now()),true)");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

//    public static boolean itiWOCerrado(String itiid) {
//        boolean abierto = true;
//        if (global.nivel > 1) {
//            if (utils.dbConsult("SELECT IF(UsuarioCierreViaje > 0, 0, 1) from itinerarios_tbl where ItinerarioID = '" + itiid + "'").equals("0")) {
//                abierto = false;
//            }
//        }
//        return abierto;
//    }
    public static void revisionExtrasClientes(String wcontid) {
        String clienteid = utils.dbConsult("SELECT clientefk from workcontenedores_tbl where WContenedorID = '" + wcontid + "'");
        generarCargoFSC(wcontid);
        generarDemoras(wcontid, clienteid);

    }

    public static void revisionExtrasClientes(String wcontid, boolean flujorenta, String fsc) {
        String clienteid = utils.dbConsult("SELECT clientefk from workcontenedores_tbl where WContenedorID = '" + wcontid + "'");
        generarCargoFSC(wcontid, clienteid, flujorenta, fsc);
        if (!flujorenta) {
            generarDemorasDescarga(wcontid, clienteid);
            generarStorage(wcontid, clienteid);
        }
    }

    private static void generarStorage(String wcontid, String clienteid) {
//        String horasdescarga = utils.dbConsult("SELECT IFNULL((SELECT CEIL(TIMESTAMPDIFF(MINUTE, DeliveryDate, EmptyNotify)/60) from workcontenedores_tbl where WContenedorID = '" + wcontid + "' ),0)");//and CajaID > 0
//        if (horasdescarga.length() <= 11) {
//            if (!horasdescarga.equals("0")) {
//                int horasd = Integer.parseInt(horasdescarga);
//                if (horasd > 0) {
//                    String query = "SELECT SUM(Importe) as importec from diasdemoras_tbl where Dias <= '" + horasdescarga + "' and ClienteID = '" + clienteid + "' and Status = true";
//                    Connection con = null;
//                    Statement state = null;
//                    ResultSet rs = null;
//                    try {
//                        con = utils.startConnection();
//                        state = con.createStatement();
//                        rs = state.executeQuery(query);
//
//                        while (rs.next()) {
//                            if (rs.getDouble("importec") > 0) {
//                                utils.dbInsert("INSERT INTO cargosclientes_tbl (ItinerarioID, WContID, Concepto, Cantidad, Importe, Moneda, IVAc, IVARETc, ISRc, Status, Fecha, UsuarioID, CargoSistema) VALUES("
//                                        + "'0', '" + wcontid + "', 'Waiting Time " + horasdescarga + " hours', 1, '" + rs.getString("importec") + "', (SELECT MonedaDemoras from clientes_tbl where clientes_tbl.ClienteID = '" + clienteid + "'), '0', '0', '0', true, now(), '" + global.usuario + "')");
//                            }
//                        }
//                    } catch (SQLException e) {
//                        utils.errorGenerado("utils / generarDemoras / sql ex " + e.toString());
//                    } catch (Exception e) {
//                        utils.errorGenerado("utils / generarDemoras / ex " + e.toString());
//                    } finally {
//                        utils.closeAllConnections(con, state, rs);
//                    }
//
//                }
//            }
//        }
    }

    private static void generarDemorasDescarga(String wcontid, String clienteid) {
        String horasdescarga = utils.dbConsult("SELECT IFNULL((SELECT CEIL(TIMESTAMPDIFF(MINUTE, DeliveryDate, EmptyNotify)/60) from workcontenedores_tbl where WContenedorID = '" + wcontid + "' ),0)");//and CajaID > 0
        if (horasdescarga.length() <= 11) {
            if (!horasdescarga.equals("0")) {
                int horasd = Integer.parseInt(horasdescarga);
                if (horasd > 0) {
                    String query = "SELECT SUM(Importe) as importec from diasdemoras_tbl where Dias <= '" + horasdescarga + "' and ClienteID = '" + clienteid + "' and Status = true";
                    Connection con = null;
                    Statement state = null;
                    ResultSet rs = null;
                    try {
                        con = utils.startConnection();
                        state = con.createStatement();
                        rs = state.executeQuery(query);

                        while (rs.next()) {
                            if (rs.getDouble("importec") > 0) {
                                utils.dbInsert("INSERT INTO cargosclientes_tbl (ItinerarioID, WContID, Concepto, Cantidad, Importe, Moneda, IVAc, IVARETc, ISRc, Status, Fecha, UsuarioID, CargoSistema) VALUES("
                                        + "'0', '" + wcontid + "', 'Waiting Time " + horasdescarga + " hours', 1, '" + rs.getString("importec") + "', (SELECT MonedaDemoras from clientes_tbl where clientes_tbl.ClienteID = '" + clienteid + "'), '0', '0', '0', true, now(), '" + global.usuario + "')");
                            }
                        }
                    } catch (SQLException e) {
                        utils.errorGenerado("utils / generarDemoras / sql ex " + e.toString());
                    } catch (Exception e) {
                        utils.errorGenerado("utils / generarDemoras / ex " + e.toString());
                    } finally {
                        utils.closeAllConnections(con, state, rs);
                    }

                }
            }
        }
    }

    private static void generarCargoFSC(String wcontid, String clienteid, boolean flujorenta, String esfsc) {

//            String fsc = utils.dbConsult("SELECT FSCID from clientes_tbl where clientes_tbl.ClienteID = '" + clienteid + "'");
        String fsc = "0";
        if (!flujorenta) {
            fsc = utils.dbConsult("SELECT IFNULL((SELECT fscid from rutas_tbl "
                    + "left join workcontenedores_tbl on rutas_tbl.RutaID = workcontenedores_tbl.RutaID where WContenedorID = '" + wcontid + "'),0)");
        } else {
            fsc = esfsc;
        }
        String aplicafsc = utils.dbConsult("SELECT AplicaFSC from clientes_tbl where clientes_tbl.ClienteID = '" + clienteid + "'");
        String estado = "";
        if (aplicafsc.equals("0")) {
            estado = " and icont_tbl.Estado = 0 ";
        }
        if (aplicafsc.equals("1")) {
            estado = " and icont_tbl.Estado = 1 ";
        }
        if (aplicafsc.equals("2")) {
            estado = " and (icont_tbl.Estado = 0 or icont_tbl.Estado = 1) ";
        }
        String tipocalculo = "0", llego = "no", importecal = "0", porcentaje = "0";
        String preciod = "0";
        String importeextra = "0";
        if (!fsc.equals("0")) {
            tipocalculo = utils.dbConsult("SELECT TipoCalculo from definicionfsc_tbl where DefinicionID = '" + fsc + "'"); //tipo de calculo 1= millasXgalon, 2= millas, 3 = galon
            if (tipocalculo.equals("3")) {
                estado = "";
            }
            if (!flujorenta) {
                preciod = utils.dbConsult("SELECT getPrecioDiesel(IFNULL((SELECT TablaPreciosID from definicionfsc_tbl where DefinicionID = '" + fsc + "'),0), IFNULL(DATE((SELECT Carga from itinerarios_tbl where WContFK = '" + wcontid + "' and Status > 0 order by Carga asc limit 1)),CURDATE()) ) ");
            } else {
                preciod = utils.dbConsult("SELECT getPrecioDiesel(IFNULL((SELECT TablaPreciosID from definicionfsc_tbl where DefinicionID = '" + fsc + "'),0), IFNULL((SELECT rentaunidades_tbl.Fecha from workcontenedores_tbl left join rentaunidades_tbl on WContenedorID = wccobroid where WContenedorID = '" + wcontid + "'),CURDATE()) ) ");
            }
            importeextra = "0";
            String query = "SELECT ItinerarioID, "
                    + "RutaID, "
                    + "SUM((SELECT IFNULL((SELECT SUM(Millas) from millasrutas_tbl where millasrutas_tbl.RutaID = rutas_tbl.RutaID and millasrutas_tbl.Status = true),0) from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID)) as millas,"
                    + "(SELECT IF(" + flujorenta + " = true, wc.ImporteHoraRegular, wc.CobroCliente) + getExtraFSCWc(WContenedorID, wc.MonedaCliente, getTipoCambio(CURDATE())) from workcontenedores_tbl as wc where WContenedorID = '" + wcontid + "') as importec,"
                    + "(SELECT wc.MonedaCliente from workcontenedores_tbl as wc where WContenedorID = '" + wcontid + "') as monedaimp "
                    + "FROM itinerarios_tbl "
                    + "where ItinerarioID in (SELECT icont_tbl.ItinerarioID from icont_tbl where WContID = '" + wcontid + "' and icont_tbl.Status = true " + estado + ") and Status >= 1 ";//cuando iba definido por tipo de movimiento (SELECT TarifaID from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) in (SELECT TarifaPagoID from rutasfsc_tbl where ClienteID = '" + clienteid + "' and Aplica = true)
//            (SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) = (SELECT LocacionTOID from rutas_tbl left join workcontenedores_tbl on rutas_tbl.RutaID = workcontenedores_tbl.RutaID where WContenedorID = '" + wcontid + "')
            Connection con = null;
            Statement state = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                state = con.createStatement();
                rs = state.executeQuery(query);
                while (rs.next()) {
                    llego = "inicioquery";
                    if (tipocalculo.equals("1")) {
                        importeextra = utils.dbConsult("SELECT ROUND(IFNULL(getPorcentajeFSC('" + fsc + "', '" + preciod + "')*" + rs.getString("millas") + ",0),2)");//milla por galon
                        importecal = rs.getString("millas");
                    }
                    if (tipocalculo.equals("2")) {
                        importeextra = utils.dbConsult("SELECT ROUND(IFNULL(getPorcentajeFSC('" + fsc + "', '" + rs.getString("millas") + "'),0),2)");//milla //quite *" + rs.getString("millas") + "
                        importecal = rs.getString("millas");
                    }
                    if (tipocalculo.equals("3")) {
                        importeextra = utils.dbConsult("SELECT ROUND(IFNULL(getPorcentajeFSC('" + fsc + "', '" + preciod + "')*" + rs.getString("importec") + ",0),2)");// galon
                        importecal = rs.getString("importec");
                    }
                    llego = "tipocalculo";
                    if (Float.parseFloat(importeextra) > 0) {
                        String cargos = utils.dbConsult("SELECT IFNULL((SELECT GROUP_CONCAT(CargoID) from cargosclientes_tbl where WContID = '" + wcontid + "' and Status = true and fsc = true),0)");
                        if (!cargos.equals("0")) {
                            utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where CargoID in (" + cargos + ")");
                        }
                        String idcc = utils.dbInsert("INSERT INTO cargosclientes_tbl (CClienteID, ItinerarioID, WContID, Concepto, Cantidad, Importe, Moneda, IVAc, IVARETc, ISRc, Status, Fecha, UsuarioID, fsc, Justificacion, rentaid) VALUES("
                                + "IFNULL((SELECT CargoID from conceptoscargosclientes_tbl as ccc where ccc.cfsc = true and ccc.Status = true limit 1),0),'" + rs.getString("ItinerarioID") + "', '" + wcontid + "', 'FUEL SURCHARGE', 1, '" + importeextra + "', '" + rs.getString("monedaimp") + "', '0', '0', '0', true, now(), '" + global.usuario + "', true, '" + tipocalculo + "/ P. Comb.:" + preciod + " %: " + porcentaje + ". Flete: " + rs.getString("importec") + "', (SELECT rentaid from workcontenedores_tbl where WContenedorID = '" + wcontid + "'))");
                        if (idcc.length() <= 11) {
                            llego = "insert ok";
                        } else {
                            llego = "insert fail";
                        }
                    }
                }

            } catch (SQLException e) {
                utils.errorGenerado("utils / revisionextrasclientes / sql ex " + e.toString());
            } catch (Exception e) {
                utils.errorGenerado("utils / revisionextrasclientes / ex " + e.toString());
            } finally {

                utils.closeAllConnections(con, state, rs);
            }

        }
        FuncionesTrafico.guardarMods("0", wcontid, "fsc = " + fsc + ", tipocalculo = " + tipocalculo + ", precio = " + preciod + ", porcentaje = " + porcentaje + ", importeflete = " + importecal + ", importeextra = " + importeextra + ", llego = " + llego);

    }

    public static void generarCargosFijos(String wcontid) {
        String query = "SELECT CargoID as defcargo, "
                + "IFNULL((SELECT cfsc from conceptoscargosclientes_tbl as ccc where ccc.CargoID = CClienteID),false) as esfuel, "
                + "CClienteID, "
                + "Concepto, "
                + "Cantidad, "
                + "Importe, "
                + "Moneda,"
                + "objimpdc, "
                + "IVAc, "
                + "IVARETc, "
                + "ISRc,"
                + "(SELECT wc.CobroCliente from workcontenedores_tbl as wc where WContenedorID = '" + wcontid + "') as importec, "
                + "DistanciaFSC "
                + "from defcargosclientes_tbl "
                + "where RutaID = (SELECT workcontenedores_tbl.RutaID from workcontenedores_tbl where WContenedorID = '" + wcontid + "') and tipoufk = (SELECT wc.TipoUID from workcontenedores_tbl as wc where WContenedorID = '" + wcontid + "') and Status = true ";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            String importeextra = "0";
            while (rs.next()) {
                if (!rs.getBoolean("esfuel")) {
                    utils.dbInsert("INSERT INTO cargosclientes_tbl(WContID, CClienteID, Concepto, Cantidad, Importe, Moneda, objimpc, IVAc, IVARETc, ISRc, UsuarioID, fijoid) "
                            + "VALUES('" + wcontid + "', '" + rs.getString("CClienteID") + "', '" + rs.getString("Concepto") + "', '" + rs.getString("Cantidad") + "', '" + rs.getString("Importe") + "', '" + rs.getString("Moneda") + "', " + rs.getBoolean("objimpdc") + ", '" + rs.getString("IVAc") + "', '" + rs.getString("IVARETc") + "', '" + rs.getString("ISRc") + "', '" + global.usuario + "', '" + rs.getString("defcargo") + "')");
                } else {
                    String fsc = utils.dbConsult("SELECT IFNULL((SELECT fscid from rutas_tbl left join workcontenedores_tbl on rutas_tbl.RutaID = workcontenedores_tbl.RutaID where WContenedorID = '" + wcontid + "'),0)");
                    if (!fsc.equals("0")) {
                        String tipocalculo = utils.dbConsult("SELECT TipoCalculo from definicionfsc_tbl where DefinicionID = '" + fsc + "'"); //tipo de calculo 1= millasXgalon, 2= millas, 3 = galon
                        String preciod = utils.dbConsult("SELECT getPrecioDiesel(IFNULL((SELECT TablaPreciosID from definicionfsc_tbl where DefinicionID = '" + fsc + "'),0), CURDATE() ) ");

                        if (tipocalculo.equals("1")) {
                            importeextra = utils.dbConsult("SELECT ROUND(IFNULL(getPorcentajeFSC('" + fsc + "', '" + preciod + "')*" + rs.getString("DistanciaFSC") + ",0),2)");//milla por galon
                        }
                        if (tipocalculo.equals("2")) {
                            importeextra = utils.dbConsult("SELECT ROUND(IFNULL(getPorcentajeFSC('" + fsc + "', '" + rs.getString("DistanciaFSC") + "'),0),2)");//milla //quite *" + rs.getString("millas") + "
                        }
                        if (tipocalculo.equals("3")) {
                            importeextra = utils.dbConsult("SELECT ROUND(IFNULL(getPorcentajeFSC('" + fsc + "', '" + preciod + "')*" + rs.getString("importec") + ",0),2)");// galon
                        }
                        if (Float.parseFloat(importeextra) > 0) {
                            utils.dbInsert("INSERT INTO cargosclientes_tbl (CClienteID, ItinerarioID, WContID, Concepto, Cantidad, Importe, Moneda, IVAc, IVARETc, ISRc, Status, Fecha, UsuarioID, fsc, fijoid, rentaid) VALUES("
                                    + "'" + rs.getString("CClienteID") + "','0', '" + wcontid + "', 'FUEL SURCHARGE', 1, '" + importeextra + "', '" + rs.getString("Moneda") + "', '0', '0', '0', true, now(), '" + global.usuario + "', true, '" + rs.getString("defcargo") + "', (SELECT rentaid from workcontenedores_tbl where WContenedorID = '" + wcontid + "'))");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            utils.errorGenerado("utils / generarCargosFijos / sql ex " + e.toString());
            System.out.println("utils / generarCargosFijos / sql ex " + e.toString());
        } catch (Exception e) {
            utils.errorGenerado("utils / generarCargosFijos / ex " + e.toString());
            System.out.println("utils / generarCargosFijos / ex " + e.toString());
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    private static void generarCargoFSC(String wcontid) {
//        String fsc = utils.dbConsult("SELECT FSCID from clientes_tbl where clientes_tbl.ClienteID = (SELECT wo.ClienteID from workorder_tbl as wo left join workcontenedores_tbl as wc on wo.WorkID = wc.WorkOrderID where WContenedorID = '" + wcontid + "')");
        String fsc = utils.dbConsult("SELECT IFNULL((SELECT fscid from rutas_tbl left join workcontenedores_tbl on rutas_tbl.RutaID = workcontenedores_tbl.RutaID where WContenedorID = '" + wcontid + "'),0)");
        if (!fsc.equals("0")) {
            String tipocalculo = utils.dbConsult("SELECT TipoCalculo from definicionfsc_tbl where DefinicionID = '" + fsc + "'"); //tipo de calculo 1= millasXgalon, 2= millas, 3 = galon
            String preciod = utils.dbConsult("SELECT getPrecioDiesel(IFNULL((SELECT TablaPreciosID from definicionfsc_tbl where DefinicionID = '" + fsc + "'),0), IFNULL(DATE((SELECT Carga from itinerarios_tbl where WContFK = '" + wcontid + "' and Status > 0 and ReposicionMov = false order by Carga asc limit 1)),CURDATE()) ) ");
            String importeextra = "0";
            String query = "SELECT ItinerarioID, "
                    + "RutaID, "
                    + "SUM((SELECT IFNULL((SELECT SUM(Millas) from millasrutas_tbl where millasrutas_tbl.RutaID = rutas_tbl.RutaID and millasrutas_tbl.Status = true),0) from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID)) as millas, "
                    + "(SELECT wc.CobroCliente from workcontenedores_tbl as wc where WContenedorID = WContFK) as importec, "
                    + "(SELECT wc.MonedaCliente from workcontenedores_tbl as wc where WContenedorID = WContFK) as monedaimp "
                    + "FROM itinerarios_tbl "
                    + "where ItinerarioID in (SELECT icont_tbl.ItinerarioID from icont_tbl where WContID = '" + wcontid + "' and icont_tbl.Status = true and icont_tbl.Estado = 1) and Status >= 1  ";//cuando iba definido por tipo de movimiento (SELECT TarifaID from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) in (SELECT TarifaPagoID from rutasfsc_tbl where ClienteID = '" + clienteid + "' and Aplica = true)
//            and "
//                    + "(SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) = (SELECT LocacionTOID from rutas_tbl left join workcontenedores_tbl on rutas_tbl.RutaID = workcontenedores_tbl.RutaID where WContenedorID = '" + wcontid + "')
            Connection con = null;
            Statement state = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                state = con.createStatement();
                rs = state.executeQuery(query);
                while (rs.next()) {
                    if (tipocalculo.equals("1")) {
                        importeextra = utils.dbConsult("SELECT ROUND(IFNULL(getPorcentajeFSC('" + fsc + "', '" + preciod + "')*" + rs.getString("millas") + ",0),2)");//milla por galon
                    }
                    if (tipocalculo.equals("2")) {
                        importeextra = utils.dbConsult("SELECT ROUND(IFNULL(getPorcentajeFSC('" + fsc + "', '" + rs.getString("millas") + "'),0),2)");//milla //quite *" + rs.getString("millas") + "
                    }
                    if (tipocalculo.equals("3")) {
                        importeextra = utils.dbConsult("SELECT ROUND(IFNULL(getPorcentajeFSC('" + fsc + "', '" + preciod + "')*" + rs.getString("importec") + ",0),2)");// galon
                    }
                    if (Float.parseFloat(importeextra) > 0) {
                        if (utils.dbConsult("SELECT IFNULL((SELECT GROUP_CONCAT(CargoID) from cargosclientes_tbl where WContID = '" + wcontid + "' and Status = true and fsc = true and fijoid > 0),0)").equals("0")) {
                            String cargos = utils.dbConsult("SELECT IFNULL((SELECT GROUP_CONCAT(CargoID) from cargosclientes_tbl where WContID = '" + wcontid + "' and Status = true and fsc = true),0)");
                            if (!cargos.equals("0")) {
                                utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where CargoID in (" + cargos + ")");
                            }
                            utils.dbInsert("INSERT INTO cargosclientes_tbl (CClienteID, ItinerarioID, WContID, Concepto, Cantidad, Importe, Moneda, IVAc, IVARETc, ISRc, Status, Fecha, UsuarioID, fsc, Justificacion) VALUES("
                                    + "'28','" + rs.getString("ItinerarioID") + "', '" + wcontid + "', 'FUEL SURCHARGE', 1, '" + importeextra + "', '" + rs.getString("monedaimp") + "', '0', '0', '0', true, now(), '" + global.usuario + "', true, 'Tipo Calculo:" + tipocalculo + ", FSC: " + fsc + ", PrecioD:" + preciod + ", Millas:" + rs.getString("millas") + "')");
                        }
                    }
                }
            } catch (SQLException e) {
                utils.errorGenerado("utils / revisionextrasclientes / sql ex " + e.toString());
            } catch (Exception e) {
                utils.errorGenerado("utils / revisionextrasclientes / ex " + e.toString());
            } finally {
                utils.closeAllConnections(con, state, rs);
            }
        }

    }

    private static void generarDemoras(String wcontid, String clienteid) {
        String diasdescarga = utils.dbConsult("SELECT IFNULL((SELECT TIMESTAMPDIFF(DAY, DATE(DeliveryDate), EmptyNotify) from workcontenedores_tbl where WContenedorID = '" + wcontid + "' ),0)");//and CajaID > 0
        if (diasdescarga.length() <= 11) {
            if (!diasdescarga.equals("0")) {
                int diasd = Integer.parseInt(diasdescarga);
                if (diasd > 0) {
                    String query = "SELECT Importe, Dias from diasdemoras_tbl where Dias <= '" + diasdescarga + "' and ClienteID = '" + clienteid + "' and Status = true order by Dias desc, DDemoraID desc";
                    Connection con = utils.startConnection();
                    try {
                        Statement statement = con.createStatement();
                        ResultSet rs = statement.executeQuery(query);
                        float importecobro = 0;
                        int diasrestante = diasd;
                        int demorascarga = 0;
                        while (rs.next()) {
                            importecobro = importecobro + (rs.getFloat("Importe") * (diasrestante - rs.getInt("Dias")));
                            diasrestante = rs.getInt("Dias");
                            demorascarga = diasd - rs.getInt("Dias");
                        }
                        if (importecobro > 0) {
                            utils.dbInsert("INSERT INTO cargosclientes_tbl (ItinerarioID, WContID, Concepto, Cantidad, Importe, Moneda, IVAc, IVARETc, ISRc, Status, Fecha, UsuarioID) VALUES("
                                    + "'0', '" + wcontid + "', 'Demoras " + demorascarga + " dias', 1, '" + importecobro + "', (SELECT MonedaDemoras from clientes_tbl where clientes_tbl.ClienteID = '" + clienteid + "'), '0', '0', '0', true, now(), '" + global.usuario + "')");
                        }
                        con.close();
                    } catch (SQLException e) {
                        utils.errorGenerado("utils / generarDemoras / sql ex " + e.toString());
                    } catch (Exception e) {
                        utils.errorGenerado("utils / generarDemoras / ex " + e.toString());
                    }

                }
            }
        }
    }

    public static String generaPDFInvoice(String fid) {
        Connection con = utils.startConnection();
        //String file = "C:\\Users\\Alex\\Documents\\Ordenes de compra SERPORT\\Orden No "+jTable1.getValueAt(jTable1.getSelectedRow(), 0)+".pdf ";
        try {
            JasperReport reporte;
            reporte = (JasperReport) JRLoader.loadObject(new File("ReporteInvoice.jasper"));
            Map parametros = new HashMap();
            parametros.put("facturaid", fid);
            //parametros.put("cliente", lblRazonSocial.getText());
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            String filen = global.rectim + "Facturas\\" + utils.dbConsult("SELECT IF(NoFactura = '', CONCAT('Preview',FacturaID), NoFactura) from facturas_tbl where FacturaID = '" + fid + "'") + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, global.rectim + "PDF\\" + fid + ".pdf");
            JasperExportManager.exportReportToPdfFile(jasperPrint, filen);
            con.close();
            return "";
        } catch (SQLException ex) {
            return "sql err " + ex.toString();
        } catch (JRException ex) {
            return "jre err " + ex.toString();
        }
    }

    public static String generaInvoice(String fid, dNuevoDoc aThis) {
        Connection con = utils.startConnection();
        try {
            JasperReport reporte;
            reporte = (JasperReport) JRLoader.loadObject(new File("ReporteInvoice.jasper"));
            Map parametros = new HashMap();
            parametros.put("facturaid", fid);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint, aThis);
            con.close();
            return "";
        } catch (SQLException ex) {
            return "sql err " + ex.toString();
        } catch (JRException ex) {
            return "jre err " + ex.toString();
        }
    }

    public static String generaInvoice(String fid) {
        Connection con = utils.startConnection();
        try {
            JasperReport reporte;
            reporte = (JasperReport) JRLoader.loadObject(new File("ReporteInvoice.jasper"));
            Map parametros = new HashMap();
            parametros.put("facturaid", fid);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint);
            con.close();
            return "";
        } catch (SQLException ex) {
            return "sql err " + ex.toString();
        } catch (JRException ex) {
            return "jre err " + ex.toString();
        }
    }

    public static String generaPDFNC(String fid) {
        Connection con = utils.startConnection();
        //String file = "C:\\Users\\Alex\\Documents\\Ordenes de compra SERPORT\\Orden No "+jTable1.getValueAt(jTable1.getSelectedRow(), 0)+".pdf ";
        try {

            JasperReport reporte;
            reporte = (JasperReport) JRLoader.loadObject(new File("ReporteInvoiceNC.jasper"));

            Map parametros = new HashMap();
            parametros.put("notaid", fid);
            //parametros.put("cliente", lblRazonSocial.getText());
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            String filen = global.rectim + "Notas de Credito\\" + utils.dbConsult("SELECT FolioNota from notascreditoclientes_tbl where NotaID = '" + fid + "'") + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, global.rectim + "PDFnc\\" + fid + ".pdf");
            JasperExportManager.exportReportToPdfFile(jasperPrint, filen);

            con.close();
            return "";
        } catch (SQLException ex) {
            return "sql err " + ex.toString();
        } catch (JRException ex) {
            return "jre err " + ex.toString();
        }
    }

    public static void revisarCarpetas() {
        if (!new File(global.rectim).exists()) {
            if (!global.path.equals(global.rectim)) {
                new File(global.rectim).mkdir();
            }
        }

        if (!new File(global.rectim + "Cartas Porte").exists()) {
            new File(global.rectim + "Cartas Porte").mkdir();
        }
        if (!new File(global.rectim + "PDFcp").exists()) {
            new File(global.rectim + "PDFcp").mkdir();
        }
        if (!new File(global.rectim + "CFDIcp").exists()) {
            new File(global.rectim + "CFDIcp").mkdir();
        }
        if (!new File(global.rectim + "Facturas").exists()) {
            new File(global.rectim + "Facturas").mkdir();
        }
        if (!new File(global.rectim + "CFDI").exists()) {
            new File(global.rectim + "CFDI").mkdir();
        }
        if (!new File(global.rectim + "PDF").exists()) {
            new File(global.rectim + "PDF").mkdir();
        }
        if (!new File(global.rectim + "Pagos").exists()) {
            new File(global.rectim + "Pagos").mkdir();
        }
        if (!new File(global.rectim + "CFDIpago").exists()) {
            new File(global.rectim + "CFDIpago").mkdir();
        }
        if (!new File(global.rectim + "PDFpago").exists()) {
            new File(global.rectim + "PDFpago").mkdir();
        }
        if (!new File(global.rectim + "Notas de Credito").exists()) {
            new File(global.rectim + "Notas de Credito").mkdir();
        }
        if (!new File(global.rectim + "CFDInc").exists()) {
            new File(global.rectim + "CFDInc").mkdir();
        }
        if (!new File(global.rectim + "PDFnc").exists()) {
            new File(global.rectim + "PDFnc").mkdir();
        }
        if (!new File(global.rectim + "CFDIaddendas").exists()) {
            new File(global.rectim + "CFDIaddendas").mkdir();
        }
        if (!new File(global.path + "DocsCobros").exists()) {
            new File(global.path + "DocsCobros").mkdir();
        }
        if (!new File(global.path + "workcfiles").exists()) {
            new File(global.path + "workcfiles").mkdir();
        }
        if (!new File(global.path + "bodys").exists()) {
            new File(global.path + "bodys").mkdir();
        }
        if (!new File(global.path + "addendas").exists()) {
            new File(global.path + "addendas").mkdir();
        }
        if (!new File(global.path + "archivoschoferes").exists()) {
            new File(global.path + "archivoschoferes").mkdir();
        }
        if (!new File(global.path + "archivosclientes").exists()) {
            new File(global.path + "archivosclientes").mkdir();
        }
        if (!new File(global.path + "archivospatios").exists()) {
            new File(global.path + "archivospatios").mkdir();
        }
        if (!new File(global.path + "archivosunidades").exists()) {
            new File(global.path + "archivosunidades").mkdir();
        }
    }

    public static String agregarAddenda(String file, String addedtext, String facturaid) {
        try {
            String filepath = file;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            String xml = convertXMLDocumentToString(doc);

            String replaced = xml.replace("</cfdi:Comprobante>", addedtext + "\n</cfdi:Comprobante>");
            System.out.println("xml = " + replaced);
            doc = convertStringToXMLDocument(replaced);
            // write the content into xml file

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult resultcfdi = new StreamResult(new File(global.rectim + "CFDIaddendas\\" + facturaid + ".xml"));
            StreamResult resultfac = new StreamResult(new File(global.rectim + "Facturas\\" + utils.dbConsult("SELECT NoFactura from facturas_tbl where FacturaID = '" + facturaid + "'") + ".xml"));
            transformer.transform(source, resultcfdi);
            transformer.transform(source, resultfac);

            System.out.println("Done");

        } catch (ParserConfigurationException | TransformerException | IOException | SAXException pce) {
            System.out.println("pce = " + pce);
            return pce.toString();
        }
        return "";
    }

    private static Document convertStringToXMLDocument(String text) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(text)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static String convertXMLDocumentToString(Document xmlDocument) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();

            // Uncomment if you do not require XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            //A character stream that collects its output in a string buffer,
            //which can then be used to construct a string.
            StringWriter writer = new StringWriter();

            //transform document to string
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));

            String xmlString = writer.getBuffer().toString();
            return xmlString;                      //Print to console or logs
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean txtWrite(String file, String body) throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        String[] temp = body.split("\n");
        for (int i = 0; i < temp.length; i++) {
            writer.println(temp[i]);
            System.out.println("temp[i] = " + temp[i]);
        }
        writer.close();
        return true;
    }

    public static String txtOpen(String path) throws IOException {
        String msj = "";
        FileReader fr;
        BufferedReader br = null;
        try {
            String verify;
            File file = new File(path);
            if (file.exists()) {
            } else {
                file.createNewFile();
            }
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while ((verify = br.readLine()) != null) {
                //times.add(verify.split("///")[0]));
                //list.add(new Empl(verify.split("///")[1], Integer.parseInt(verify.split("///")[0])));
                msj = msj + verify + "\n";
                //System.out.println(verify);
            }

            br.close();
            return msj;
        } catch (IOException e) {
            br.close();
            return e.toString();
        } catch (Exception e) {
            br.close();
            return e.toString();
        }

    }

    public static void abrirPDF(String file) {
        try {
            File myFile = new File(file);
            String filePath = myFile.toString();
            SwingController controller = new SwingController();
            SwingViewBuilder factory = new SwingViewBuilder(controller);
            JPanel viewerComponentPanel = factory.buildViewerPanel();
            ComponentKeyBinding.install(controller, viewerComponentPanel);
            controller.getDocumentViewController().setAnnotationCallback(new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController()));
            JFrame window = new JFrame(filePath);
            ImageIcon icon = new ImageIcon("images\\icon.png");
            window.setIconImage(icon.getImage());
            window.getContentPane().add(viewerComponentPanel);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.pack();
            window.setVisible(true);
            controller.openDocument(filePath);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error constructor " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    public static boolean txtWrite(String file, String body) throws FileNotFoundException, UnsupportedEncodingException {
//
//        PrintWriter writer = new PrintWriter(file, "UTF-8");
//        String[] temp = body.split("\n");
//        for (int i = 0; i < temp.length; i++) {
//            writer.println(temp[i]);
//            //System.out.println("temp[i] = " + temp[i]);
//        }
//        writer.close();
//        return true;
//    }
    public static String openFile(File file) {
        if (Desktop.isDesktopSupported()) {
            try {
                String documentspath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();

                if (!new File(documentspath + "\\IntegranetTemp").exists()) {
                    new File(documentspath + "\\IntegranetTemp").mkdir();
                }
                File newpath = new File(documentspath + "\\IntegranetTemp\\" + file.getName());
                if (FileUtils.copyFile(file, newpath)) {
                    Desktop.getDesktop().open(newpath);
                }
                return "";
            } catch (IOException ex) {
                return ex.toString();
            }
        }
        return "not supported";
    }

    public static String openFile(String file) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(file);

                String documentspath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();

                if (!new File(documentspath + "\\IntegranetTemp").exists()) {
                    new File(documentspath + "\\IntegranetTemp").mkdir();
                }

                File newpath = new File(documentspath + "\\IntegranetTemp\\" + myFile.getName());
                if (FileUtils.copyFile(myFile, newpath)) {
                    Desktop.getDesktop().open(newpath);
                }

                return "";
            } catch (IOException ex) {
                return ex.toString();
            }
        }
        return "not supported";
    }

    public static String runProgram(String file) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(file);

                Desktop.getDesktop().open(myFile);

                return "";
            } catch (IOException ex) {
                return ex.toString();
            }
        }
        return "not supported";
    }

    public static GregorianCalendar getDate(String input) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withOffsetParsed();
        DateTime dateTime = formatter.parseDateTime(input);
        GregorianCalendar cal = dateTime.toGregorianCalendar();
        return cal;
    }

    public static boolean copyFile(File afile, File bfile, boolean delete) {
        InputStream inStream = null;
        OutputStream outStream = null;
        boolean val = false;
        try {
            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            //delete the original file
//            if (delete) {
//                afile.delete();
//            }
            val = true;
        } catch (IOException e) {
            System.out.println("e = " + e);
            utils.errorGenerado("utils / copyFile / ioex = " + e.toString());
            val = false;
        } catch (Exception e) {
            System.out.println("e = " + e);
            utils.errorGenerado("utils / copyFile / ex = " + e.toString());
            val = false;
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException ex) {
                    utils.errorGenerado("utils / copyFile / ioex close ins = " + ex.toString());
                }
            }
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException ex) {
                    utils.errorGenerado("utils / copyFile / ioex close ous = " + ex.toString());
                }
            }
        }
        return val;
    }

    public static boolean copyFile(String afile, String bfile, boolean delete) {
        return copyFile(new File(afile), new File(bfile), delete);
    }

    public static Image imageResize(JLabel lbl, File imagen) {
        Image resizedImage = null;
        BufferedImage img = null;
        float imgx, imgy, factor = 1, lblx = lbl.getWidth(), lbly = lbl.getHeight();
        try {
            img = ImageIO.read(imagen);
            imgx = img.getWidth();
            imgy = img.getHeight();
            if (imgx > lblx || imgy > lbly) {
                if (imgx > imgy) {
                    factor = imgx / lblx;
                } else {
                    factor = imgy / lbly;
                }
            }
            resizedImage = img.getScaledInstance((int) (img.getWidth() / factor), (int) (img.getHeight() / factor), 0);
        } catch (IOException e1) {
            resizedImage = null;
        }

        return resizedImage;
    }

    public static String getUnidad(String categoria, String unidad) {
        String query = "SELECT 'hola'";
        if (categoria.equals("1")) {
            query = "SELECT CONCAT_WS(' - ',NoEconomico,PlacasUSA),CamionID from camiones_tbl where CamionID = '" + unidad + "'";
        }
        if (categoria.equals("2")) {
            query = "SELECT CONCAT_WS(' - ',NoEconomico,Modelo),CajaID from cajas_tbl where CajaID = '" + unidad + "'";
        }
        if (categoria.equals("3")) {
            return "Oficina";
        }
        if (categoria.equals("4")) {
            return "Stock";
        }
        return dbConsult(query);
    }

    public static String getCategoria(String cate) {
        int cat = Integer.parseInt(cate);
        cate = "0";
        switch (cat) {
            case 1:
                cate = "Camiones";
                break;
            case 2:
                cate = "Cajas";
                break;
            case 3:
                cate = "Administracion";
                break;
        }
        return cate;
    }

    public static String calcularFechaPago(int periodo, String fechainicial, int iteracion) {
        String fechapago = "";
        fechainicial = dateFromFieldtoDBwoH(fechainicial);
        if (iteracion != 1) {
            iteracion--;
            if (periodo == 0) {
                fechapago = dbConsult("SELECT DATE_ADD('" + fechainicial + "', INTERVAL " + (7 * iteracion) + " Day)");
            }
            if (periodo == 1) {
                fechapago = dbConsult("SELECT DATE_ADD('" + fechainicial + "', INTERVAL " + (14 * iteracion) + " Day)");
            }
            if (periodo == 2) {
                fechapago = dbConsult("SELECT DATE_ADD('" + fechainicial + "', INTERVAL " + (1 * iteracion) + " MONTH)");
            }
            if (periodo == 3) {
                fechapago = dbConsult("SELECT DATE_ADD('" + fechainicial + "', INTERVAL " + (2 * iteracion) + " MONTH)");
            }
            if (periodo == 4) {
                fechapago = dbConsult("SELECT DATE_ADD('" + fechainicial + "', INTERVAL " + (3 * iteracion) + " MONTH)");
            }
            if (periodo == 5) {
                fechapago = dbConsult("SELECT DATE_ADD('" + fechainicial + "', INTERVAL " + (6 * iteracion) + " MONTH)");
            }
            if (periodo == 6) {
                fechapago = dbConsult("SELECT DATE_ADD('" + fechainicial + "', INTERVAL " + (1 * iteracion) + " YEAR)");
            }
        } else {
            fechapago = fechainicial;
        }
        return fechapago;
    }

    public static String FiletoDB(String string) {
        String filetodb = string.replace("\\", "\\\\");
        System.out.println(string);
        return filetodb;
    }

    public static String checarTipo(String stringtipo) {
        int tipo = Integer.parseInt(stringtipo);
        if (tipo == 0) {
            stringtipo = "Interno";
        }
        if (tipo == 1) {
            stringtipo = "Externo";
        }

        return stringtipo;
    }

    public static String checarStatus(String statusID) {
        int status = Integer.parseInt(statusID);
        if (status == 0) {
            statusID = "Revision";
        }
        if (status == 1) {
            statusID = "Por Cotizar";
        }
        if (status == 2) {
            statusID = "Cotizado";
        }
        if (status == 3) {
            statusID = "Autorizado";
        }
        if (status == 4) {
            statusID = "Rechazado";
        }
        if (status == 5) {
            statusID = "Ordenado";
        }
        if (status == 6) {
            statusID = "Recibido";
        }
        if (status == 7) {
            statusID = "Entregado";
        }

        return statusID;
    }

    public static String checarStatusOrden(String statusID) {

        statusID = statusID.replace("1", "Espera").replace("2", "Pedido").replace("3", "Recibido");

        return statusID;
    }

    public static String checarStatusMant(String statusID) {
        statusID = statusID.replace("1", "Espera").replace("2", "Servicio").replace("3", "Espera de Refacciones").replace("4", "Terminado").replace("5", "Sin Solucion");
        return statusID;
    }

    public static String getDays(String string) {
        String days = "";
        String[] splitFechaHora = new String[2];
        String[] splitDias = new String[3];
        splitFechaHora = string.split(" ");
        splitDias = splitFechaHora[0].split("/");
        days = splitDias[0];
        return days;
    }

    public static String addTime(String string, int num) {
        String stringTime = "";
        String[] split1 = new String[3];
        split1 = string.split("\\.");
        split1[2] = (Integer.parseInt(split1[2]) - 2000) + "";
        string = split1[2] + "-" + split1[1] + "-" + split1[0];
        if (num == 0) {

            stringTime = string + " 00:00:00";
        }
        if (num == 1) {
            stringTime = string + " 23:59:59";
        }
        if (num == 2) {

            string = utils.dbConsult("SELECT INTERVAL 1 DAY + '" + split1[2] + "-" + split1[1] + "-" + split1[0] + "'");
            System.out.println(string);
            split1 = string.split("-");
            string = split1[2] + "/" + split1[1] + "/" + split1[0] + " 12:00:00";
            System.out.println(string);
            stringTime = string;
        }

        return stringTime;
    }

    public static String dateFromFieldtoDBwoH(String string) {
        String stringDate = "";
        String[] split = new String[3];
        if (string.contains(".")) {// 01.12.2019
            split = string.split("\\.");
            if (split[2].length() == 2) {
                split[2] = "20" + split[2];
            }
            if (global.marcadorfecha == 0) {
                stringDate = split[2] + "-" + split[1] + "-" + split[0];
            } else {
                stringDate = split[2] + "-" + split[0] + "-" + split[1];
            }
            return stringDate;
        }
        if (string.contains("/")) {// 01/12/2019
            split = string.split("/");

            if (split[2].length() == 2) {
                if (split[0].length() == 4) {
                    return string.replace("/", "-");
                }
                split[2] = "20" + split[2];
            }

            if (global.marcadorfecha == 0) {
                stringDate = split[2] + "-" + split[1] + "-" + split[0];
            } else {
                stringDate = split[2] + "-" + split[0] + "-" + split[1];
            }
            return stringDate;
        }
        if (string.contains("-")) {
            split = string.split("-");

            if (split[2].length() == 2) {// 01-12-2019
                if (split[0].length() == 4) {
                    return string;
                }
                split[2] = "20" + split[2];
            } else {
                //solo necesita hacer flip a los valores
                if (global.marcadorfecha == 0) {
                    stringDate = split[2] + "-" + split[1] + "-" + split[0];
                } else {
                    stringDate = split[2] + "-" + split[0] + "-" + split[1];
                }
                return stringDate;
            }

        }

        Date javaDate = DateUtil.getJavaDate(Double.parseDouble(string));
        return new SimpleDateFormat("yyyy-MM-dd").format(javaDate);

    }

    public static String dateFromFieldtoDBwoH(String fecha, boolean addtime) {
        String datetme = "";
        if (addtime) {
            return dateFromFieldtoDBwoH(fecha) + " " + dbConsult("SELECT CURTIME()");
        } else {
            return dateFromFieldtoDBwoH(fecha);
        }
    }

    //backup
//    public static String dateFromFieldtoDBwoH(String string) {
//        String stringDate = "";
//        String[] split = new String[3];
//        if (string.contains(".")) {
//            split = string.split("\\.");
//            if (split[2].length() == 2) {
//                split[2] = "20" + split[2];
//            }
//        }
//        if (string.contains("/")) {
//            split = string.split("/");
//            if (split[2].length() == 2) {
//                if (split[0].length() == 4) {
//                    return string.replace("/", "-");
//                }
//                split[2] = "20" + split[2];
//            }
//        }
//        if (string.contains("-")) {
//            split = string.split("-");
//            if (split[2].length() == 2) {//01-12-2019
//                if (split[0].length() == 4) {
//                    return string;
//                }
//                split[2] = "20" + split[2];
//            } else {
//                //solo necesita hacer flip a los valores
//            }
//        }
//        
//        Date javaDate = DateUtil.getJavaDate(Double.parseDouble(cfechac.getContents()));
//                    System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(javaDate));
//        
//        stringDate = split[2] + "-" + split[1] + "-" + split[0];
//        return stringDate;
//    }
    public static String leerTxt(String archivo) {
        String texto = "";//192.168.1.2
        try {
            String verify;
            File file = new File(archivo);
            file.createNewFile();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((verify = br.readLine()) != null) {
                texto = texto + verify;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto;
    }

    public static String openTxt(String path) {
        String text = "";
        try {
            String verify;
            File file = new File(path);
            file.createNewFile();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((verify = br.readLine()) != null) {
                text = text + verify + "\n";
            }
            br.close();
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }

    }

    public static String txtToMail(String texto) {

        try {
            texto = texto.replace("á", "&aacute;").replace("í", "&iacute;").replace("é", "&ecute;").replace("ó", "&ocute;").replace("ú", "&ucute;");
            return texto;
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

    }

    public static boolean movimientoCerrado(String itiid) {
        boolean abierto = true;
        if (global.nivel > 1) {
            if (utils.dbConsult("SELECT IF(Status >=3 or UsuarioCierreViaje > 0, 0, 1) from itinerarios_tbl where ItinerarioID = '" + itiid + "'").equals("0")) {
                abierto = false;
            }
        }
        return abierto;
    }

    /*public static void insertarNominaCuentasPorPagar // Recorre toda la tabla de viajes seleccionados e inserta en cuentas por pagar las seleccionadas
            (
                    Component window, // ventana donde se llama
                    JTable table, // tabla con informacion
                    ArrayList<String> itinerariosid, // lista de ids de itinerarios
                    String Moneda, // Moneda 0,1
                    String TipoCambio, // tipo de cambio del momento con decimales
                    int columnaCheck, // columna de la tabla donde tienen el checkbox para marcar que si las enviaran
                    int columnaWO,// opcional para concatenar para el concepto 
                    int columnacliente, // opcional para concatenar para el concepto 
                    int columnaclienteDestino, // opcional para concatenar para el concepto 
                    String proveedor,
                    String fechafactura,
                    String articuloID
            ) {

        int option = JOptionPane.showConfirmDialog(window, "Desea enviar a cuentas por pagar los itinerarios\n"
                + " marcados en la columna Cuentas por Pagar?", "Confirmación", JOptionPane.YES_NO_OPTION);

        String IDFactura = "", itiID = "";
        double ttl = 0;

        if (option == 0) {

            for (int i = 0; table.getRowCount() > i; i++) {
                if (table.getValueAt(i, columnaCheck).equals(true)) {
                    utils.dbUpdate("UPDATE itinerarios_tbl SET Status=Status+2 WHERE ItinerarioID=" + itinerariosid.get(i));

                    ttl = ttl + Double.parseDouble(utils.dbConsult("select IFNULL(PagoSV,0) + "
                            + "IFNULL(getPagoExtraMon(ItinerarioID, MonedaChofer, digits('" + TipoCambio + "')),0) +0 "
                            + "from Itinerarios_tbl WHERE ItinerarioID='" + itinerariosid.get(i) + "'"));

                    itiID = itinerariosid.get(i);
                }
            }

            //Checar nombre del concepto
            IDFactura = utils.dbInsert("INSERT INTO facturacion_tbl (ProveedorID, Total, FechaRegistro, FechaFactura, NoFac,Moneda,TipoCambio,Pedimento,FechaPedimento,"
                    + "Aduana,IvaAduana,OrdenID,FechaVencimiento,Descuento,TipoFactura, Loc, NoFacturaInsertada) "
                    + "VALUES('"+proveedor+"'," //IFNULL((select proveedorID from choferes_tbl where choferID=(select choferID from itinerarios_Tbl where itinerarioID=" + itiID + ")),0)
                    + "(SELECT digits('" + ttl + "')),(now()), '"+utils.dateFromFieldtoDBwoH(fechafactura)+"','', "
                    + "'" + Moneda + "',(SELECT TipoCambio from utils_tbl),'',NULL,'',0, "
                    + "0,now(),0, 0, 0, false) ");

            for (int i = 0; table.getRowCount() > i; i++) {
                if (table.getValueAt(i, columnaCheck).equals(true)) {
                    utils.dbInsert("INSERT INTO cfacturacion_tbl (FacturaID, ArticuloID, Cantidad, Precio,CantidadReal,ConceptoArt,ISR,IVARET,IVA,DescuentoC,IEPS,IEPSp,"
                            + "ConceptoID, ConceptoOrdenID,UNegocioID, itinerarioID) "
                            + "VALUES('" + IDFactura + "','"+articuloID+"','1', "
                            + "'" + utils.dbConsult("select IFNULL(PagoSV,0) + "
                                    + "IFNULL(getPagoExtraMon(ItinerarioID, MonedaChofer, digits('" + TipoCambio + "')),0) +0 "
                                    + "from Itinerarios_tbl WHERE ItinerarioID='" + itinerariosid.get(i) + "'") + "', "
                            + "'" + utils.dbConsult("select IFNULL(PagoSV,0) + "
                                    + "IFNULL(getPagoExtraMon(ItinerarioID, MonedaChofer, digits('" + TipoCambio + "')),0) +0 "
                                    + "from Itinerarios_tbl WHERE ItinerarioID='" + itinerariosid.get(i) + "'") + "', "
                            + "concat('" + table.getValueAt(i, columnaWO).toString() + "',' ', '" + table.getValueAt(i, columnacliente).toString() + "', ' ','" + table.getValueAt(i, columnaclienteDestino).toString() + "'),"
                            + "0,0,0,0," //checar IVA y concepto 
                            + "0,0, 0, 0, 0, " + itinerariosid.get(i) + " ) ");
                }
            }

        }

    }*/

    public static Connection startConnection() {
        Connection con = null;
        String host = "jdbc:mysql://" + global.host + ":" + global.puerto + "/" + global.db;
        String uName = global.userdb;
        String uPass = global.passw;
        try {

            con = DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException e) {
            System.out.println("Error " + e);
            System.out.println("host = " + host);
            System.out.println("uName = " + uName);
            System.out.println("uPass = " + uPass);
        }
        return con;
    }

    public static Connection startConnection(String db) {
        Connection con = null;
        String host = "jdbc:mysql://" + global.host + ":" + global.puerto + "/" + db;
        String uName = global.userdb;
        String uPass = global.passw;
        try {

            con = DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException e) {
            System.out.println("Error " + e);
            System.out.println("host = " + host);
            System.out.println("uName = " + uName);
            System.out.println("uPass = " + uPass);
        }
        return con;
    }

    public static String dbConsult(String db, String query) {
        String queryResult = "";
        int temp = 1;
        try {
            String host = "jdbc:mysql://" + global.host + ":" + global.puerto + "/" + db;
            String uName = global.userdb;
            String uPass = global.passw;
            Connection con = DriverManager.getConnection(host, uName, uPass);

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                queryResult = rs.getString(temp);
            }
            con.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("e = " + e);
            utils.errorGenerado("utils / dbconsult 2 / " + e.toString() + "\n" + query);
            return e.toString();
        }

        return queryResult;
    }

    public static String dbConsult(String string) {
        String queryResult = "";
        int temp = 1;
        String host = "jdbc:mysql://" + global.host + ":" + global.puerto + "/" + global.db;
        String uName = global.userdb;
        String uPass = global.passw;
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            String query = string;
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                queryResult = rs.getString(temp);
            }
            //dbConsultTest(string);
        } catch (SQLException e) {
            errorGenerado("dbConsult / " + e.toString() + "\n" + string);
            System.out.println("Error " + e + "\n" + string);
            queryResult = e.toString();
        } finally {
            closeAllConnections(con, state, rs);
        }

        return queryResult;
    }

    public static ArrayList<String> dbConsults(String string) {
        ArrayList<String> respuesta = new ArrayList<>();
        int temp = 1;
        String host = "jdbc:mysql://" + global.host + ":" + global.puerto + "/" + global.db;
        String uName = global.userdb;
        String uPass = global.passw;
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            String query = string;
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                respuesta.add(rs.getString(temp));
            }
            //dbConsultTest(string);
        } catch (SQLException e) {
            errorGenerado("dbConsult / " + e.toString() + "\n" + string);
            System.out.println("Error " + e + "\n" + string);
        } finally {
            closeAllConnections(con, state, rs);
        }

        return respuesta;
    }

    public static String dbInsert(String string) {
        String idgen = "";
        String host = "jdbc:mysql://" + global.host + ":" + global.puerto + "/" + global.db + "?allowMultiQueries=true";
        String uName = global.userdb;
        String uPass = global.passw;
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {

            con = DriverManager.getConnection(host, uName, uPass);
            String query = string;
            state = con.createStatement();

            state.execute(query, Statement.RETURN_GENERATED_KEYS);
            rs = state.getGeneratedKeys();

            while (rs.next()) {
                idgen = rs.getString(1);
            }
            //dbInsertTest(string);

        } catch (SQLException e) {
            errorGenerado("dbinsert / " + e.toString() + "\n" + string);
            System.out.println("Error " + e + "\n" + string);
            idgen = e.toString();
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
        return idgen;
    }

    public static String dbUpdate(String string) {
        String result = "";
        String host = "jdbc:mysql://" + global.host + ":" + global.puerto + "/" + global.db + "?allowMultiQueries=true";
        String uName = global.userdb;
        String uPass = global.passw;
        Connection con = null;
        Statement state = null;
        try {
            con = DriverManager.getConnection(host, uName, uPass);
            String query = string;
            state = con.createStatement();
            state.executeUpdate(string);
            //dbUpdateTest(string);
        } catch (SQLException e) {
            errorGenerado("dbupdate / " + e.toString() + "\n" + string);
            System.out.println("Error " + e + "\n" + string);
            result = e.toString() + "\n" + string;
        } finally {
            utils.closeConnection(con);
            utils.closeStatement(state);
        }

        return result;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                errorGenerado("Error al cerrar la connecion " + ex);
            }
        }
    }

    public static void closeStatement(Statement state) {
        if (state != null) {
            try {
                state.close();
            } catch (SQLException ex) {
                errorGenerado("Error al cerrar la connecion " + ex);
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                errorGenerado("Error al cerrar la connecion " + ex);
            }
        }
    }

    public static void closeAllConnections(Connection con, Statement state, ResultSet rs) {
        closeConnection(con);
        closeStatement(state);
        closeResultSet(rs);
    }

    public static void insertarNominaCuentasPorPagar // Recorre toda la tabla de viajes seleccionados e inserta en cuentas por pagar las seleccionadas
            ( 
                    Component window, // ventana donde se llama
                    JTable table, // tabla con informacion
                    ArrayList<String> itinerariosid, // lista de ids de itinerarios
                    String Moneda, // Moneda 0,1
                    String TipoCambio, // tipo de cambio del momento con decimales
                    int columnaCheck, // columna de la tabla donde tienen el checkbox para marcar que si las enviaran
                    int columnaWO,// opcional para concatenar para el concepto 
                    int columnacliente, // opcional para concatenar para el concepto 
                    int columnaclienteDestino, // opcional para concatenar para el concepto 
                    String Proveedor,
                    String FechaFac,
                    String artiid/*, 
                    String IVA,  // impuestos deben mandar 0.00 por default si no llevan sus conceptos ya que estan en porcentaje, ejemplo iva 0.00, 0.08 o 0.16
                    String IVARET,
                    String ISR,
                    String IEPS,
                    String IEPSp // este es el unico que no es porcentaje, se manda la cantidad tal cual*/
            ) {

        int option = JOptionPane.showConfirmDialog(window, "Desea enviar a cuentas por pagar los itinerarios\n"
                + " marcados en la columna Cuentas por Pagar?", "Confirmación", JOptionPane.YES_NO_OPTION);

        String IDFactura = "", itiID = "", dcredito = "";
        double ttl = 0;

        if (option == 0) {

            for (int i = 0; table.getRowCount() > i; i++) {
                if (table.getValueAt(i, columnaCheck) != null && (Boolean) table.getValueAt(i, columnaCheck)) {
                    utils.dbUpdate("UPDATE itinerarios_tbl SET Status=Status+2 WHERE ItinerarioID=" + itinerariosid.get(i));

                    ttl = ttl + Double.parseDouble(utils.dbConsult("select IFNULL(getPagoItinerarioMon('" + itinerariosid.get(i) + "','" + Moneda + "', digits('" + TipoCambio + "')),0) + "
                            + "IFNULL(getPagoExtraMon('" + itinerariosid.get(i) + "','" + Moneda + "', digits('" + TipoCambio + "')),0) +0 "
                            + "from Itinerarios_tbl WHERE ItinerarioID='" + itinerariosid.get(i) + "'"));

                    System.out.println("ttl: " + ttl);

                    itiID = itinerariosid.get(i);
                    System.out.println("ID: " + itiID);
                }
            }
            
            ttl = ttl + Double.parseDouble( 
            utils.dbConsult("select ((digits("+ttl+0+")+0)*(IFNULL((SELECT IVA from articulos_tbl where ArticuloID = '" + artiid + "'),0))) - "
                          + "((digits("+ttl+0+")+0)*(IFNULL((SELECT ISR from articulos_tbl where ArticuloID = '" + artiid + "'),0))) - "
                          + "((digits("+ttl+0+")+0)*(IFNULL((SELECT IVARET from articulos_tbl where ArticuloID = '" + artiid + "'),0))) + "
                          + "(digits("+ttl+0+")+0)*(IFNULL((SELECT IEPSp from articulos_tbl where ArticuloID = '" + artiid + "'),0)) + "
                    + "IFNULL((SELECT IEPS from articulos_tbl where ArticuloID = '" + artiid + "'),0)"
            )
            );

            //Checar nombre del concepto
            dcredito = utils.dbConsult("SELECT DiasCredito FROM proveedores_tbl WHERE ProveedorID ='" + Proveedor + "';");
            System.out.println("Dias credito= " + dcredito);

//       IDFactura = utils.dbInsert("INSERT INTO facturacion_tbl (ProveedorID, Total, FechaRegistro, FechaFactura, NoFac, Moneda, TipoCambio, Pedimento, FechaPedimento, "
//        + "Aduana, IvaAduana, OrdenID, FechaVencimiento, Descuento, TipoFactura, Loc, NoFacturaInsertada) " 
//        + "VALUES (IFNULL('" + Proveedor + "', 0), " 
//        + "(SELECT digits('" + ttl + "')), NOW(), NOW(), '', "
//        + "'" + Moneda + "', (SELECT TipoCambio FROM utils_tbl), '', NULL, '', 0, " 
//        + "0, NOW(), 0, 0, 0, FALSE)");
            IDFactura = utils.dbInsert("INSERT INTO facturacion_tbl (ProveedorID, Total, FechaRegistro, FechaFactura, NoFac, Moneda, TipoCambio, Pedimento, FechaPedimento, "
                    + "Aduana, IvaAduana, OrdenID, FechaVencimiento, Descuento, TipoFactura, Loc, NoFacturaInsertada) "
                    + "VALUES (IFNULL('" + Proveedor + "', 0), "
                    + "(SELECT digits('" + ttl + "')),"
                    + " NOW(),STR_TO_DATE('" + FechaFac + "', '%m/%d/%Y'), "
                    + " '', "
                    + "'" + Moneda + "', (SELECT TipoCambio FROM utils_tbl), '', NULL, '', 0, "
                    + "0,"
                    + "DATE_ADD(STR_TO_DATE('" + FechaFac + "', '%m/%d/%Y'), INTERVAL " + dcredito + " DAY), "
                    + "0, 0, 0, FALSE)");

            for (int i = 0; table.getRowCount() > i; i++) {
                if (table.getValueAt(i, columnaCheck) != null && (Boolean) table.getValueAt(i, columnaCheck)) {
                    String cfacturaid = utils.dbInsert("INSERT INTO cfacturacion_tbl (FacturaID, ArticuloID, Cantidad, Precio,CantidadReal,ConceptoArt,ISR,IVARET,IVA,DescuentoC,IEPS,IEPSp,"
                            + "ConceptoID, ConceptoOrdenID,UNegocioID, ItinerarioID) "
                            + "VALUES('" + IDFactura + "','" + artiid + "','1', "
                            + "'" + utils.dbConsult("select IFNULL(getPagoItinerarioMon('" + itinerariosid.get(i) + "','" + Moneda + "', digits('" + TipoCambio + "')),0) + "
                                    + "IFNULL(getPagoExtraMon('" + itinerariosid.get(i) + "','" + Moneda + "', digits('" + TipoCambio + "')),0) +0 "
                                    + "from Itinerarios_tbl WHERE ItinerarioID='" + itinerariosid.get(i) + "'") + "', "
                            + "'" + utils.dbConsult("select IFNULL(getPagoItinerarioMon('" + itinerariosid.get(i) + "','" + Moneda + "', digits('" + TipoCambio + "')),0) + "
                                    + "IFNULL(getPagoExtraMon('" + itinerariosid.get(i) + "','" + Moneda + "', digits('" + TipoCambio + "')),0) +0 "
                                    + "from Itinerarios_tbl WHERE ItinerarioID='" + itinerariosid.get(i) + "'") + "', "
                            + "concat('" + table.getValueAt(i, columnaWO).toString() + "',' ', '" + table.getValueAt(i, columnacliente).toString() + "', ' ',"
                            + "'" + table.getValueAt(i, columnaclienteDestino).toString() + "','[WO: ','"+utils.dbConsult("SELECT WContID FROM noedb.icont_tbl where itinerarioID='"+itinerariosid.get(i)+"'")+"',' | DM: ','"+itinerariosid.get(i)+"',']'),"
                            + "IFNULL((SELECT ISR from articulos_tbl where ArticuloID = '" + artiid + "'),0),IFNULL((SELECT IVARET from articulos_tbl where ArticuloID = '" + artiid + "'),0),"
                            + "IFNULL((SELECT IVA from articulos_tbl where ArticuloID = '" + artiid + "'),0),0," //checar IVA y concepto 
                            + "IFNULL((SELECT IEPS from articulos_tbl where ArticuloID = '" + artiid + "'),0),IFNULL((SELECT IEPSp from articulos_tbl where ArticuloID = '" + artiid + "'),0), 0, 0, 0, " + itinerariosid.get(i) + " ) ");
                    
                    utils.dbUpdate("UPDATE itinerarios_tbl SET cfacturaID='"+cfacturaid+"' WHERE ItinerarioID=" + itinerariosid.get(i));
                }
            }

        }

    }
}
