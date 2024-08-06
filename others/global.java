/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.awt.Color;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author admin
 */
public class global {
    public static int usuario = 0;
    public static int empresa = 0;
    public static String serverdir = "";
    public static String xampdir="";//var/www/html/sanchotenaAPI/
    public static String imageneslinux="";//1 = linux, 0 = windows
    public static String rutaEDI="Edis";
    public static String rutaEDI214=global.serverdir+"Edis214";
    public static String rutaEDI204=global.serverdir+"Edis204";
    public static String meses[] = new String[]{"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    public static int nivel = 100;
    public static int tipouser = 0;
    public static String unidadvolumen ="Litros";
    public static int marcadorvolumen=0;//1 Galones//0 Litros
    public static boolean requiereTipoCambio = true;//Tipo cambio para las api e combustible
    public static String jarfac = "Facturador.bat"; 
    public static String jarcp = "CartasPorteV4.exe"; 
    public static String jarcp_respaldo = "CartasPorteV4res.exe"; 
    public static int marcadordistancia = 0; //1 millas // 0 kilometros
    public static String unidaddistancia = "KM";
    public static String fdatesat = "%Y-%m-%dT%H:%i:%s";
    public static String userdb = "root";
    public static String passw = "Soluciones01";
    public static String server = "1";
    public static String puerto = "3306";
    public static String patio = "0";
    public static String ncodificados = "1";
    public static String db = "prorentasdb";
    public static String host = "localhost";
    public static int cambiodocs = 0;
    public static int marcadorfecha = 1;
    public static String ffecha = "MM/dd/yyyy";//dd.MM.yyyy
    public static String fdatedb = "%m/%d/%Y";//%d.%m.%Y
    public static String psw = "";
    public static String localdir = "";
    public static String carpetaevidencias = "";
    public static String carpetachoferes = "";
    public static String path = "";
    public static String rectim = "";
    public static FileFilter solopdf = new FileNameExtensionFilter("pdf Files", "pdf");
    public static FileFilter soloexcel = new FileNameExtensionFilter("Excel Files", "xls");
    public static String filespath = "Y:\\SystemFiles\\";
    //nombres {
    public static String clientec = "Cliente Cobro";
    public static String equipowo = "Remolque";
    //}
    public static boolean segcamion = false;
    public static boolean destinoscliente = false;
    public static boolean destinocobro = false;
    public static boolean documentoschofer = false;
    public static String cssmail = "<style>"
                                + "p {width:60%}"
                                + "div {width:60%}"
                                + "table { width:70%;}"
                                + "table, th, td {"
                                + "    border-collapse: collapse;"
                                + "}"
                                + "th{"
                                + "    padding: 5px;"
                                + "    text-align: left;"
                                + "    width: 5px"
                                + "}"
                                + "td {"
                                + "    padding: 5px;"
                                + "    text-align: left;"
                                + "    width: 60px"
                                + "}"
                                + "table#t01 tr:nth-child(even) {"
                                + "    background-color: #eee;"
                                + "}"
                                + "table#t01 tr:nth-child(odd) {"
                                + "   background-color:#fff;"
                                + "}"
                                + "table#t01 th	{"
                                + "    background-color: #203566;"
                                + "    color: white;"
                                + "}"
                                + "</style>";
    public static Color headerscolor = new Color(2,36,107);//2,36,107
    public static Color background = new Color(255,255,255);
}
